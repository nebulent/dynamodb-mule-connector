/* Generated By:JavaCC: Do not edit this line. ExpressionParser.java */
package com.nebulent.parsers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodb.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.ComparisonOperator;
import com.amazonaws.services.dynamodb.model.Condition;

public class ExpressionParser implements ExpressionParserConstants {

 DynamoDBQueryExpression query = new DynamoDBQueryExpression(new AttributeValue());
 DynamoDBScanExpression scan = new DynamoDBScanExpression();

        public static DynamoDBQueryExpression asQuery(String expression) throws ParseException
        {
                ExpressionParser parser = new ExpressionParser(new ByteArrayInputStream(expression.getBytes()));
                return parser.parseQueryExpression();
        }

        public static DynamoDBScanExpression asScanExpression(String expression) throws ParseException
        {
                ExpressionParser parser = new ExpressionParser(new ByteArrayInputStream(expression.getBytes()));
                return parser.parseScanExpression();
        }

  final public DynamoDBQueryExpression parseQueryExpression() throws ParseException {
  query = new DynamoDBQueryExpression( new AttributeValue());
    ExpressionParameter();
    label_1:
    while (true) {
      jj_consume_token(COMMA);
      ExpressionParameter();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
    }
    jj_consume_token(0);
          {if (true) return query;}
    throw new Error("Missing return statement in function");
  }

  final public DynamoDBScanExpression parseScanExpression() throws ParseException {
  scan = new DynamoDBScanExpression();
    scanAttribute();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      scanAttribute();
    }
    jj_consume_token(0);
    {if (true) return scan;}
    throw new Error("Missing return statement in function");
  }

  final public void ExpressionParameter() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HASHKEY:
      hashKey();
      break;
    case RANGEKEY:
      rangeKey();
      break;
    case SIF:
      SIF();
      break;
    case CONSISTENT:
      consistent();
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void scanAttribute() throws ParseException {
  String name;
  Condition condition;
    name = getName();
    jj_consume_token(SEPARATOR);
    condition = condition();
    scan.addFilterCondition(name, condition);
  }

  final public void consistent() throws ParseException {
    jj_consume_token(CONSISTENT);
    jj_consume_token(SEPARATOR);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
      jj_consume_token(TRUE);
    query.withConsistentRead(true);
      break;
    case FALSE:
      jj_consume_token(FALSE);
    query.withConsistentRead(false);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void SIF() throws ParseException {
    jj_consume_token(SIF);
    jj_consume_token(SEPARATOR);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
      jj_consume_token(TRUE);
    query.withScanIndexForward(true);
      break;
    case FALSE:
      jj_consume_token(FALSE);
    query.withScanIndexForward(false);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void rangeKey() throws ParseException {
  Condition condition;
    jj_consume_token(RANGEKEY);
    jj_consume_token(SEPARATOR);
    condition = condition();
    query.withRangeKeyCondition(condition);
  }

  final public void hashKey() throws ParseException {
  AttributeValue value;
    jj_consume_token(HASHKEY);
    jj_consume_token(SEPARATOR);
    value = getValue();
    query.withHashKeyValue(value);
  }

  final public Condition condition() throws ParseException {
  Condition condition = new Condition();
  ComparisonOperator operator;
  List<AttributeValue> arguments;
    operator = operator();
    jj_consume_token(23);
    arguments = params();
    jj_consume_token(24);
    {if (true) return condition.withComparisonOperator(operator).withAttributeValueList(arguments);}
    throw new Error("Missing return statement in function");
  }

  final public List<AttributeValue> params() throws ParseException {
  List<AttributeValue> args = new ArrayList<AttributeValue>();
  List<AttributeValue> tt;
  AttributeValue arg;
    arg = getValue();
    args.add(arg);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      tt = params();
    args.addAll(tt);
    }
    {if (true) return args;}
    throw new Error("Missing return statement in function");
  }

  final public ComparisonOperator operator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQ:
      jj_consume_token(EQ);
    {if (true) return ComparisonOperator.EQ;}
      break;
    case NE:
      jj_consume_token(NE);
    {if (true) return ComparisonOperator.NE;}
      break;
    case LT:
      jj_consume_token(LT);
    {if (true) return ComparisonOperator.LT;}
      break;
    case LE:
      jj_consume_token(LE);
    {if (true) return ComparisonOperator.LE;}
      break;
    case GT:
      jj_consume_token(GT);
    {if (true) return ComparisonOperator.GT;}
      break;
    case GE:
      jj_consume_token(GE);
    {if (true) return ComparisonOperator.GE;}
      break;
    case IN:
      jj_consume_token(IN);
    {if (true) return ComparisonOperator.IN;}
      break;
    case BETWEEN:
      jj_consume_token(BETWEEN);
    {if (true) return ComparisonOperator.BETWEEN;}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String getName() throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HASH:
      t = jj_consume_token(HASH);
      break;
    case HASHKEY:
      t = jj_consume_token(HASHKEY);
      break;
    case RANGEKEY:
      t = jj_consume_token(RANGEKEY);
      break;
    case SIF:
      t = jj_consume_token(SIF);
      break;
    case CONSISTENT:
      t = jj_consume_token(CONSISTENT);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  final public AttributeValue getValue() throws ParseException {
  AttributeValue value = new AttributeValue();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HASH:
      value = getHash();
      break;
    case NUMBER:
      value = getNumber();
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return value;}
    throw new Error("Missing return statement in function");
  }

  final public AttributeValue getHash() throws ParseException {
  Token t;
    t = jj_consume_token(HASH);
    {if (true) return new AttributeValue().withS(t.image);}
    throw new Error("Missing return statement in function");
  }

  final public AttributeValue getNumber() throws ParseException {
  Token t;
    t = jj_consume_token(NUMBER);
    {if (true) return new AttributeValue().withN(t.image);}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public ExpressionParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[9];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000,0x1000,0x1e000,0x60000,0x60000,0x1000,0x7f8,0x9e000,0x180000,};
   }

  /** Constructor with InputStream. */
  public ExpressionParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ExpressionParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public ExpressionParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ExpressionParser(ExpressionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ExpressionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[25];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 25; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
