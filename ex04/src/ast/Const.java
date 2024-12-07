package ast;

public class Const extends Expr<Integer> {

  private int value;

  public Const (int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public Integer accept(Visitor<Integer> visitor) {
    return visitor.visit(this);
  }

  public String toString() {
    return "" + value;
  }
}
