import java.io.*;
import core.*;
import ast.*;
import parser.*;
import lexer.*;
import java.util.*;
/*
 * //add import java.util.Scanner;
 */
import java.io.File;

public class Main {
  public static void main(String[] argv) throws Exception {
    // ファイルから数式を読み込む
    File file = new File("./input.txt");
    if (!file.exists()) {
      System.out.println("ファイルが存在しません.input.txtを作成してください");
      return;
    }
    BufferedReader br = new BufferedReader(new FileReader(file));
    String text = "";
    String line;
    while ((line = br.readLine()) != null) {
      text += line + " ";
    }
    text = text.replace("\n", "").trim();
    System.out.println(text);
    br.close();

    // 標準入力から数式を読み込む
    /*
     * Scanner scanner = new Scanner(System.in); System.out.println("数式を入力してください: "); String text =
     * scanner.nextLine(); scanner.close();
     */
    // String text = "-((1+100) *x_1 +z -y) / (-2)";
    // test Lexer

    /*
     * Lexer lex = new Lexer(); lex.init(text); System.out.println(text); Token token;
     *
     * do { token = lex.nextToken(); System.out.println(token); } while (token.getType() !=
     * Token.EOF);
     */

    // end of test Lexer

    Map<String, Integer> variableTable = new HashMap<>();
    variableTable.put("x_1", 100);
    variableTable.put("y", 10);
    variableTable.put("z", 1);

    // Expr exp = new BinExpr(new Const(1), "+", new Const(100));
    // exp = new BinExpr(exp, "*", new Var("x"));
    // exp = new BinExpr(exp, "-", new Var("y"));
    // exp = new UniExpr(exp, "-");


    Parser parser = new Parser();
    parser.init(text);
    Expr exp = parser.expr();


    EvalVisitor visitor = new EvalVisitor();
    visitor.setVariableTable(variableTable);
    Integer result = visitor.visit(exp);

    if (text.contains("=")) {
      parser.parseAssignment();
      System.out.println("変数が設定されました");
    } else {
      Integer results = visitor.visit(exp);
      System.out.println("結果: " + results);
    }

    System.out.println(variableTable.toString());
    System.out.println(exp + " = " + result);

  }
}
