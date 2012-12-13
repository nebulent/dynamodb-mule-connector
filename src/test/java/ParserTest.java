import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.springframework.util.Assert;

import com.amazonaws.services.dynamodb.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBScanExpression;
import com.nebulent.parsers.ExpressionParser;
import com.nebulent.parsers.ParseException;

public class ParserTest {

	@Test
	public void parseQueryExpressionTest() throws ParseException {
		String queryString = "hashKey:4 ,rangeKey:eq(3) consistent:true, scanIndexForward:false";
		ExpressionParser parser = new ExpressionParser(new ByteArrayInputStream(queryString.getBytes()));
		DynamoDBQueryExpression query = parser.parseQueryExpression();
		System.out.println("---------------------------------------------------------------------------------------");
		Assert.notNull(query);
		System.out.println("Expression: "+queryString);
		System.out.println("HashKey: "+query.getHashKeyValue());
		System.out.println("Condition: "+query.getRangeKeyCondition());
		System.out.println("scanIndexForward: "+query.isScanIndexForward());
		System.out.println("Consistent: "+query.isConsistentRead());
		System.out.println("----------------------------------------------------------------------------------------");
	}
	
	@Test
	public void parseScanExpressionTest() throws ParseException {
		String scanString = "dates:<=(current) , salary:==(3500)";
		ExpressionParser parser = new ExpressionParser(new ByteArrayInputStream(scanString.getBytes()));
		DynamoDBScanExpression scan = parser.parseScanExpression();
		System.out.println("---------------------------------------------------------------------------------------");
		Assert.notNull(scan);
		System.out.println("Expression: "+scanString);
		System.out.println("scanFilter: "+scan.getScanFilter());
		System.out.println("----------------------------------------------------------------------------------------");
	}
	
}
