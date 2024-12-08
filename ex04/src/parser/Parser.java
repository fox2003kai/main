import ast.*;
import lexer.*;

import core.*;
/**
 * A simple parser for an expression.
 * parsing by Recursive Descent Parsing
 * @author Kozo Okano
 *
 */
public class Parser {
  private Lexer lexer;
  private Token token;

  public void init(String text) {
    lexer = new Lexer();
    lexer.init(text);
    token = lexer.nextToken();
  }

  /**
  * 数値tokenに対する解析を行い，抽象構文木としてその数値を持つConstノードを返します。
  * @return 解析結果の抽象構文木
  */
  public Const number() {
    return new Const(Integer.parseInt(token.getValue()));
  }

  /**
  * Ident tokenに対する解析を行い，抽象構文木としてその識別子を持つVarノードを返します。
  * @return 解析結果の抽象構文木
  */
  public Var variable() {
    return new Var(token.getValue());
  }


  /**
   * 以下の文法に対応する解析を行い，対応する抽象構文木を返します。
   * @return 解析結果の抽象構文木
   *
   * expr ::= [-] term { ("+" term ) | ("-" term) }*
   */
  public Expr expr() {
    Expr x;
    if (token.getType() == Token.MINUS) {
      token = lexer.nextToken();
      x = new UniExpr("-", term());
    } else {
      x = term();
    }
    while(true) {
      switch (token.getType()) {
        case Token.PLUS:
          token = lexer.nextToken();
          x = new BinExpr(x, "+", term());
          continue;
        case Token.MINUS:
          token = lexer.nextToken();
          x = new BinExpr(x, "-", term());
          continue;
        default:
          return x;
      }
    }
  }


  /**
   * 以下の文法に対応する解析を行い，対応する抽象構文木を返します。
   * @return 解析結果の抽象構文木
   *
   * term ::= factor { ("*" factor) | ("/" factor) }*
   *
   */
  public Expr term() {
    Expr x = factor();
    while(true) {
      switch (token.getType()) {
        case Token.TIME:
          token = lexer.nextToken();
          x = new BinExpr(x, "*", factor());
          continue;
        case Token.DIV:
         token = lexer.nextToken();
          x =  new BinExpr(x, "/", factor());
          continue;
        default:
          return x;
      }
    }
  }

  /**
   * 以下の文法に対応する解析を行い，対応する抽象構文木を返します。
   * @return 解析結果の抽象構文木
   *
   *   // factor ::=  number | ident | "(" expr ")"
   *
   */
  public Expr factor() {
    Expr ret = null;
    if (token.getType() == Token.IDENT) {
      ret = variable();
      token = lexer.nextToken();
    } else if (token.getType() == Token.NUMBER) {
      ret = number();
      token = lexer.nextToken();
    } else {
      if (token.getType() == Token.LPAREN) {
        token = lexer.nextToken();
        ret = expr();
        if (token.getType() == Token.RPAREN) {
          token = lexer.nextToken();
        }
      }
    }
    return ret;
  }
  /**
   * 以下の文法に対応する解析を行い，対応する抽象構文木を返します。
   * @return 解析結果の抽象構文木
   *
   * //assignment ::= ident "=" expr
   *
   * @throws RuntimeException もし、識別子が必要な場合、または、'=' が必要な場合
   */
  public Expr parseAssignment() {
    Token ident = lexer.nextToken();
    if (ident.getType() != Token.IDENT) {
        throw new RuntimeException("識別子が必要です");
    }
    if (ident.getType() != Token.ASSIGN) {
        throw new RuntimeException("'=' が必要です");
    }
    Expr value = expr();
    EvalVisitor visitor = new EvalVisitor();
    Integer result = value.accept(visitor);
    visitor.setVariable(ident.getValue(), result); // 値を記号表に保存
    return value;
}
}