package lexer;

import lexer.*;

/**
 * a simple lexer for an expression
 *@author Kozo Okano
 *
 */
public class Lexer {

  /** single lookahead character */
  private int nextChar;
  private String line;
  private int pos;

  /** advance input by one character */
  private void advance() {
    if (pos < line.length()) {
      nextChar = line.charAt(pos);
    } else {
      nextChar = -1;
    }
    pos ++;
  }

  /** initialize the scanner */
  public void init(String text) {
    line = text;
    pos = 0;
    advance();
  }

  /** recognize and return the next complete token */
  public Token nextToken() {
    while (true)
      if (Character.isDigit(nextChar)) {
        StringBuilder sb = new StringBuilder();
        do {
          sb.append((char) nextChar);
          advance();
        } while (Character.isDigit(nextChar));
        return new Token(Token.NUMBER, sb.toString());
      } else if (Character.isAlphabetic(nextChar)) {
        StringBuilder sb = new StringBuilder();
        do {
          sb.append((char) nextChar);
          advance();
        } while (Character.isAlphabetic(nextChar) || Character.isDigit(nextChar) || nextChar == '_');
        return new Token(Token.IDENT, sb.toString());
      } else {
        switch (nextChar) {
/*
        case ';':
          advance();
          return new Token(Token.SEMICOLON);
*/
        case '+':
          advance();
          return new Token(Token.PLUS);
        case '-':
          advance();
          return new Token(Token.MINUS);
        case '*':
          advance();
          return new Token(Token.TIME);
        case '/':
          advance();
          return new Token(Token.DIV);
        case '(':
          advance();
          return new Token(Token.LPAREN);
        case ')':
          advance();
          return  new Token(Token.RPAREN);

        case -1:
          return new Token(Token.EOF);

        default:
          /* in this simple scanner we just ignore everything else */
          advance();
          break;
      }
    }
  }
}