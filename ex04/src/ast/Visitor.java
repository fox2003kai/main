package ast;

public interface Visitor<E> {

  public E visit(Expr exp);
  public E visit(BinExpr binExp);
  public E visit(UniExpr uniExp);
  public E visit(Const constant);
  public E visit(Var variable);

}
