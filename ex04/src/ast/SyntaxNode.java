package ast;

public interface SyntaxNode {
  public SyntaxNode getParent();
  public void setParent(SyntaxNode parent);
  public String toString();
}
