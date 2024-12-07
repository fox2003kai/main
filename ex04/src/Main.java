import java.io.*;
import core.*;
import ast.*;
import parser.*;
import lexer.*;
import java.util.*;

public class Main {
  public static void main(String[] argv) {

    String text = "-((1+100) *x_1 +z -y) / (-2)";

    // test Lexer

/*     Lexer lex = new Lexer();
    lex.init(text);
    System.out.println(text);
    Token token;

    do {
      token = lex.nextToken();
      System.out.println(token);
    } while (token.getType() != Token.EOF); */

    // end of test Lexer

    Map<String, Integer> variableTable = new HashMap<>();
    variableTable.put("x_1", 100);
    variableTable.put("y", 10);
    variableTable.put("z", 1);

//    Expr exp = new BinExpr(new Const(1), "+", new Const(100));
//    exp = new BinExpr(exp,  "*", new Var("x"));
//    exp = new BinExpr(exp,  "-", new Var("y"));
//    exp = new UniExpr(exp, "-");


    Parser parser = new Parser ();
    parser.init(text);
    Expr exp = parser.expr();


    EvalVisitor visitor = new EvalVisitor();
    visitor.setVariableTable(variableTable);
    Integer result = visitor.visit(exp);

    System.out.println(variableTable.toString());
    System.out.println(exp + " = " + result);

  }
}
