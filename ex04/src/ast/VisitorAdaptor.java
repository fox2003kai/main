package ast;

public abstract class VisitorAdaptor <E> implements Visitor <E> {

  public E visit(Expr exp) {
    return null;
  }

  public E visit(BinExpr binExpr) {
    return null;
  }

  public E visit(UniExpr uniExpr) {
    return null;
  }

  public E visit(Const intConst) {
    return null;
  }

  public E visit(Var variable) {
    return null;
  }

}
