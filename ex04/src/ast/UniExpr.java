package ast;

public class UniExpr extends Expr<Integer> {

  private Expr expr;
  private String op;

  public UniExpr (String op, Expr exp) {
    this.expr = exp;
    if (expr != null)
      this.expr.setParent(this);
    this.op = op;
  }

  public Expr getExpr() {
    return expr;
  }

  public String getOp() {
    return op;
  }

  public Integer accept(Visitor<Integer> visitor) {
    return visitor.visit(this);
  }

  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(" " + op + " ");
    if (expr != null)
      buffer.append(expr.toString());
    else
      buffer.append("  null");
    return buffer.toString();
  }
}
