package lexer;

  /**
  * a simple Token class for simple expressions
  * @author Kozo Okano
  */
public class Token {
  private int type;
  private String value;

  /** Token symbol for + */
  public final static byte PLUS       = 0;
  /** Token symbol for - */
  public final static byte MINUS      = 1;
  /** Token symbol for * */
  public final static byte TIME       = 2;
  /** Token symbol for / */
  public final static byte DIV        = 3;
  /** Token symbol for ^ */
  public final static byte HAT        = 4;
  /** Token symbol for % */
  public final static byte MOD        = 5;
  /** Token symbol for ( */
  public final static byte LPAREN     = 6;
  /** Token symbol for ( */
  public final static byte RPAREN     = 7;
  /** Token symbol for = */
  public final static byte EQUAL      = 8;
  /** Token symbol for = */
  public final static byte SEMICOLON  = 9;
  /** Token symbol for , */
  public final static byte COMMA     = 10;
  /** Token symbol for . */
  public final static byte DOT       = 11;
  /** Token symbol for identifier */
  public final static byte IDENT     = 12;
  /** Token symbol for number */
  public final static byte NUMBER    = 13;
  /** Token symbol for End of File or String */
  public final static byte EOF       = 14;


  private String [] text = {
    "+", // 0
    "-", // 1
    "*", // 2
    "/", // 3
    "^", // 4
    "%", // 5
    "(", // 6
    ")", // 7
    "=", // 8
    ";", // 9
    ",", // 10
    ".", // 11
    "identifier",  // 12
    "number",      // 13
    "EOF"          // 14
  };


  /**
   * Constructor for a normal token
   * @param token symbol such as Token.PLUS, Token.MINUS, etc.
   */
  public Token (int type) {
    this.type = type;
    this.value = null;
  }

  /**
   * Constructor for IDENT token or NUMBER token.
   * @param token symbol either Token.IDENT, Token.NUMBER.
   * @param value semantic value for the token.
   *
   */
  public Token (int type, String value) {
    this.type = type;
    this.value = value;
  }


  /**
   * return the token symbol
   * @return token symbol
   *
   */
  public int getType() {
    return this.type;
  }

  /**
   * return the semantic value used for Ident or Number
   * @return semantic value
   *
   */
  public String getValue() {
    return this.value;
  }

  /**
   * return the text representation of the token
   * @return String reprentation
   *
   */
  public String toString() {
    if (this.type >= 0)
      if (this.value != null)
        return "[" + text[this.type] + "]" +
         " value=[" + this.value + "]";
      else
        return "[" + text[this.type] + "]";
    return "";
  }


}
