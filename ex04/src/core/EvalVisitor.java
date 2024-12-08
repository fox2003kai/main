package core;

import ast.*;
import java.util.*;


 /**
  * このクラスはVisitorパターンで構成されており，ast/下の抽象構文木が四則演算の式を表している
  * と仮定し，その評価値をもとめる。
  * 式には変数の出現を許しており、変数の値はvariableTableに保持される記号表で管理されていることを仮定している。
  *
  */
public class EvalVisitor extends VisitorAdaptor<Integer> {

  /**
   *
   * このVisitor で使う記号表を保持する。
   *
   * */
  Map<String, Integer> variableTable;

  /**
   *  このメソッドは変数と整数値のマッピングである記号表を登録します。
   *  @param map String, Integerのマップ
   * */
  public void setVariableTable(Map<String, Integer> map) {
    variableTable = map;
  }

  /**
   * このメソッドは、変数名とその値を記号表に登録します。
   *
   * @param key 変数名を表す文字列
   * @param value その変数の値
   */
  public void setVariable(String key, Integer value) {
    variableTable.put(key, value);
  }
  /**
   *  このメソッドは変数名に対応する整数値を返します。
   * @param key 変数名
   * @return key に対応する値 ないときは null
   *
   * */
  public Integer getValue(String key) {
    return variableTable.get(key);
  }


  /**
   *
   * abstract classのvisitメソッドであるため標準的な実装です。
   * @param expr 評価対象の式
   * @return 評価結果
   * */
  public Integer visit(Expr expr) {
    return (Integer) expr.accept(this);
  }

  /**
   *
   * 2項演算であるため演算子に基づいて四則演算をした評価結果を返します。
   *
   * @param binExpr 2項演算
   * @return 評価結果
   * @throws ArithmeticException 0除算のとき
   * */
    public Integer visit(BinExpr binExpr) {
    Integer lv = (Integer) binExpr.getExprL().accept(this);
    Integer rv = (Integer) binExpr.getExprR().accept(this);
    String op = binExpr.getOp();
    if (op.equals("+"))
      return (lv + rv);
    else if (op.equals("-"))
      return (lv - rv);
    else if (op.equals("*"))
      return (lv * rv); // 乗算
    else if (op.equals("/")) {
      if (rv == 0)
        throw new ArithmeticException("0除算");
      return (lv / rv); // 除算
    }
    else
      return null;
  }


  /**
   *
   * -演算の単項であるため正負を反転した評価結果を返します。
   *
   * @param uniExpr 単項演算
   * @return 正負を反転した評価結果
   * */
    public Integer visit(UniExpr uniExpr) {
    Integer v = (Integer) uniExpr.getExpr().accept(this);
    String op = uniExpr.getOp();;
    if (op.equals("-"))
      return -v;
    else
      return v;
  }

  /**
   *
   * 整数値の項に対するvisitメソッドであるためその項の表す値を返します。
   *
   * @param intConst 整数値の項
   * @return 整数値
   * */
  public Integer visit(Const intConst) {
    return intConst.getValue();
  }

  /**
   *
   * 変数項にたいするvisitメソッドであるため記号表から当該変数の値を返します。
   *
   * @param identVar 変数項
   * @return 変数の値
   * */
  public Integer visit(Var identVar) {
    Integer val =  getValue(identVar.getId());
    if (val == null) {
       System.err.println("Undefined Variable " + identVar.getId());
       System.exit(1);
       return null; // not reached
    } else
      return val;
  }
}
