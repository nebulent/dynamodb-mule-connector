/**
 * This file was automatically generated by the Mule Development Kit
 */
package com.nebulent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.param.Payload;
import org.slf4j.Logger;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBMapperConfig.ConsistentReads;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodb.datamodeling.KeyPair;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.Condition;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.nebulent.parsers.ExpressionParser;
import com.nebulent.parsers.ParseException;

/**
 * Cloud Connector
 * 
 * @author MuleSoft, Inc.
 */
@Connector(name = "nebulent-dynamodb", schemaVersion = "1.0")
public class DynamoConnector {

	private static Log log = LogFactory.getLog(DynamoConnector.class);

	private DynamoDBMapper mapper;

	private AmazonDynamoDBClient client;

	/**
	 * Configurable
	 */
	@Configurable
	@Optional
	public DynamoDBMapperConfig mapperConfig;

	/**
	 * Configurable
	 */
	@Configurable
	@Optional
	public ClientConfiguration clientConfig;

	public DynamoDBMapperConfig getMapperConfig() {
		mapperConfig = (mapperConfig == null) ? new DynamoDBMapperConfig(null,
				null, null) : mapperConfig;
		return mapperConfig;
	}

	public void setMapperConfig(DynamoDBMapperConfig mapperConfig) {
		this.mapperConfig = mapperConfig;
	}

	public ClientConfiguration getClientConfig() {
		clientConfig = (clientConfig == null) ? new ClientConfiguration()
				: clientConfig;
		return clientConfig;
	}

	public void setClientConfig(ClientConfiguration clientConfig) {
		this.clientConfig = clientConfig;
	}

	/**
	 * Connect
	 * 
	 * @param accessKey
	 *            AWS conection accesKey
	 * @param secretKey
	 *            AWS connection secretKey
	 * @throws ConnectionException
	 */
	@Connect
	public void connect(@ConnectionKey String accessKey, String secretKey)
			throws ConnectionException {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		ClientConfiguration config = new ClientConfiguration();
		client = new AmazonDynamoDBClient(credentials, config);
		mapper = new DynamoDBMapper(client, getMapperConfig());

	}

	/**
	 * Disconnect
	 */
	@Disconnect
	public void disconnect() {
		mapper = null;
		client = null;
	}

	/**
	 * Are we connected
	 */
	@ValidateConnection
	public boolean isConnected() {
		return (mapper != null && client != null);
	}

	/**
	 * Are we connected
	 */
	@ConnectionIdentifier
	public String connectionId() {
		return "001";
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:load}
	 * 
	 * @param key
	 *            AWS record key to be retrived
	 * @param rangeKey
	 *            the rangeKey if it exists
	 * @param recordType
	 *            reference to the record model class anotated with \@DynamoDBTable
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	@Processor
	public Object load(String key, @Optional String rangeKey, String recordType)
			throws ClassNotFoundException {
		Class classToBeLoaded = Class.forName(recordType);
		if (rangeKey != null) {
			return mapper.load(classToBeLoaded, key, rangeKey);
		}
		return mapper.load(classToBeLoaded, key);
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:batch-load}
	 * 
	 * @param itemsToGet
	 *            a map of (tables/types) and the list of primary keys of the
	 *            items wich will be retrived
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	// :TODO fix Type issue

	@Processor
	public Object batchLoad(@Payload Map<String, List<KeyPair>> itemsToGet)
			throws ClassNotFoundException {
		Map<Class<?>, List<KeyPair>> batchLoad = new HashMap<Class<?>, List<KeyPair>>();
		Iterator it = itemsToGet.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<KeyPair>> entry = (Entry<String, List<KeyPair>>) it
					.next();
			Class tableClass = Class.forName(entry.getKey());
			batchLoad.put(tableClass, entry.getValue());
		}
		return mapper.batchLoad(batchLoad);
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:query}
	 * 
	 * @param recordType
	 *            reference to the record model class anotated with \@DynamoDBTable
	 * @param expression
	 *            query expression
	 * @param startKey
	 *            exclusive start key for the search query
	 * @param limit
	 *            the size of the response
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * @throws Exception
	 */
	@Processor
	public Object query(String recordType, String expression,
			@Optional Key startKey, @Optional Integer limit)
			throws ClassNotFoundException, ParseException {
		Class classToBeLoaded = Class.forName(recordType);
		DynamoDBQueryExpression query = ExpressionParser.asQuery(expression);
		query = (limit != null) ? query.withLimit(limit) : query;
		query = (startKey != null) ? query.withExclusiveStartKey(startKey)
				: query;
		return mapper.query(classToBeLoaded, query);
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:count}
	 * 
	 * @param recordType
	 *            reference to the record model class anotated with \@DynamoDBTable
	 * @param scanExpression
	 *            query expression
	 * @param queryExpression
	 *            scan expression
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * @throws Exception
	 */
	@Processor
	public Integer count(String recordType,@Optional String scanExpression,@Optional String queryExpression)
			throws ClassNotFoundException, ParseException {
		Class classToBeLoaded = Class.forName(recordType);
		if(queryExpression!=null) {
			DynamoDBQueryExpression query = ExpressionParser.asQuery(queryExpression);
			return mapper.count(classToBeLoaded, query);
		}
		if(scanExpression!=null) {
			DynamoDBScanExpression scan = ExpressionParser.asScanExpression(scanExpression);
			return mapper.count(classToBeLoaded, scan);
		}
		throw new RuntimeException("Missing Parameter");
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:scan}
	 * 
	 * @param recordType
	 *            reference to the record model class anotated with \@DynamoDBTable
	 * @param expression
	 *            query expression
	 * @param startKey
	 *            exclusive start key for the search query
	 * @param limit
	 *            the size of the response
	 * @return Object
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * @throws Exception
	 */
	@Processor
	public Object scan(String recordType, String expression,
			@Optional Key startKey, @Optional Integer limit)
			throws ClassNotFoundException, ParseException {
		Class classToBeLoaded = Class.forName(recordType);
		DynamoDBScanExpression scan = ExpressionParser
				.asScanExpression(expression);
		if (limit != null) {
			scan.setLimit(limit);
		}
		scan = (startKey != null) ? scan.withExclusiveStartKey(startKey) : scan;
		System.out.println("limit: " + scan.getLimit());
		System.out.println("startKey : " + scan.getExclusiveStartKey());
		return mapper.scan(classToBeLoaded, scan);
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:save}
	 * 
	 * @param payload
	 *            the MuleMessage payload to be stored
	 * @throws Exception
	 */
	@Processor
	public void save(@Payload Object payload) {
		try {
			mapper.save(payload);
		} catch (DynamoDBMappingException ex) {
			log.error("Payload is not of required type or the DataModel class is Malformed");
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:batch-save}
	 * 
	 * @param payload
	 *            the MuleMessage payload to be stored
	 * @throws Exception
	 */
	@Processor
	public void batchSave(@Payload List<?> payload) {
		try {
			mapper.batchSave(payload);
		} catch (DynamoDBMappingException ex) {
			log.error("Payload is not of required type or the DataModel class is Malformed");
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:delete}
	 * 
	 * @param payload
	 *            the MuleMessage payload to be stored
	 * @return PasskeyMessage
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	@Processor
	public Object delete(@Payload Object payload) {
		mapper.delete(payload);
		return payload;
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:batch-delete}
	 * 
	 * @param payload
	 *            the MuleMessage payload to be stored
	 * @return PasskeyMessage
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	@Processor
	public List<?> batchDelete(@Payload List<?> payload) {
		mapper.batchDelete(payload);
		return payload;
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:batch-save}
	 * 
	 * @param write
	 *            the items to store
	 * @param delete
	 *            the items to delete
	 * @throws Exception
	 */
	@Processor
	public void batchWrite(Object write, Object delete) {
		System.out.println(write.toString());
		System.out.println(delete.toString());
		mapper.batchWrite((List<?>)write, (List<?>)delete);
	}

	/**
	 * Custom processor
	 * 
	 * {@sample.xml ../../../doc/dynamodb-connector.xml.sample
	 * nebulent-dynamodb:marshal-into-objects}
	 * 
	 * @param type
	 *            the type of object the attributes will be compress to
	 * @param attributes
	 *            the list of atributes and its values
	 * @return PasskeyMessage
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	@Processor
	public Object marshallIntoObjects(String type, Object attributes)
			throws ClassNotFoundException {
		Class<?> clazz = Class.forName(type);
		
		return mapper.marshallIntoObjects(clazz,(List<Map<String, AttributeValue>>) attributes);
	}

}
