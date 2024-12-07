package ast;

public class Var extends Expr<Integer> {

  private String ident;

  public Var (String ident) {
    this.ident = ident;
  }

  public String getId() {
    return ident;
  }

  public Integer accept(Visitor<Integer> visitor) {
    return visitor.visit(this);
  }

  public String toString() {
    return ident;
  }
}
