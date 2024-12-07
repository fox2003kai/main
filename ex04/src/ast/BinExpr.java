package ast;

public class BinExpr extends Expr<Integer> {

  private Expr exprL;
  private String op;
  private Expr exprR;

  public BinExpr (Expr exp, String op, Expr exp2) {
    this.exprL = exp;
    if (exprL != null)
      this.exprL.setParent(this);
    this.op = op;
    this.exprR = exp2;
    if (exprR != null)
      this.exprR.setParent(this);
  }


  public Expr getExprL() {
    return exprL;
  }

  public Expr getExprR() {
    return exprR;
  }

  public String getOp() {
    return op;
  }

  public Integer accept(Visitor<Integer> visitor) {
    return visitor.visit(this);
  }

  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append("(");
    if (exprL != null)
      buffer.append(exprL.toString());
    else
      buffer.append("  no left term");
    buffer.append(" " + op + " ");
    if (exprR != null)
      buffer.append(exprR.toString());
    else
      buffer.append("  no right term");
    buffer.append(")");
    return buffer.toString();
  }
}
