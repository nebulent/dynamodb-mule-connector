/* Generated By:JavaCC: Do not edit this line. QueryExpressionParser.java */
package com.nebulent.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.ComparisonOperator;
import com.amazonaws.services.dynamodb.model.Condition;

public class QueryExpressionParser implements QueryExpressionParserConstants {
  public static void main(String args []) throws ParseException
  {
    QueryExpressionParser parser = new QueryExpressionParser(System.in);
    Condition condition = parser.queryExpresssion();
    System.out.println("Parsing Successful"+condition.toString());
  }

  static final public Condition queryExpresssion() throws ParseException {
  Condition condition = new Condition();
  ComparisonOperator operator;
  List<AttributeValue> attr;
    operator = operator();
    jj_consume_token(15);
    attr = attributes();
    jj_consume_token(16);
    {if (true) return condition.withComparisonOperator(operator).withAttributeValueList(attr);}
    throw new Error("Missing return statement in function");
  }

  static final public ComparisonOperator operator() throws ParseException {
 ComparisonOperator operator;
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
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public List<AttributeValue> attributes() throws ParseException {
  List<AttributeValue> attributes;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      attributes = numbers();
    {if (true) return attributes;}
      break;
    case HASH:
      attributes = words();
    {if (true) return attributes();}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public List<AttributeValue> numbers() throws ParseException {
  List<AttributeValue> attr = new ArrayList<AttributeValue>();
  String number;
    number = getNumber();
    attr.add(new AttributeValue().withN(number));
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 17:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      jj_consume_token(17);
      number = getNumber();
    attr.add(new AttributeValue().withN(number));
    }
    {if (true) return attr;}
    throw new Error("Missing return statement in function");
  }

  static final public List<AttributeValue> words() throws ParseException {
  List<AttributeValue> attr = new ArrayList<AttributeValue>();
  String word;
    word = getWord();
    attr.add(new AttributeValue().withS(word));
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 17:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      jj_consume_token(17);
      word = getWord();
    attr.add(new AttributeValue().withS(word));
    }
    {if (true) return attr;}
    throw new Error("Missing return statement in function");
  }

  static final public String getNumber() throws ParseException {
 Token t;
    t = jj_consume_token(NUMBER);
    {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  static final public String getWord() throws ParseException {
 Token t;
    t = jj_consume_token(HASH);
    {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public QueryExpressionParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x3fc,0x4400,0x20000,0x20000,};
   }

  /** Constructor with InputStream. */
  public QueryExpressionParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public QueryExpressionParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new QueryExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public QueryExpressionParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new QueryExpressionParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public QueryExpressionParser(QueryExpressionParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(QueryExpressionParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
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
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[18];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 4; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 18; i++) {
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
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
