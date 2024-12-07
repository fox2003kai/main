package ast;

public abstract class Expr<E> implements SyntaxNode {
  protected SyntaxNode parent;

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public abstract E accept(Visitor<E> visitor);

  public abstract String toString();
}
