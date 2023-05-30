/**
 * class for logarithms
 */
public class Log extends Function {
  
  public Log(Operand operand) { 
    setOperand(operand);
  }
  
  //tostring method for logarithms like Exp[value]
  @Override
  public String toString() {
    return "Exp["+ getOperand().toString() + "]";
  }
  
  //equals method for compare polynoms
  //@param a object for compare
  @Override
  public boolean equals(Object o) {
    if (o instanceof Log) {
      Log e = (Log)o;
      return this.getOperand() == e.getOperand();
    }
    else
      return false;
  }
   //method for the derivative of a logarithm
  @Override
  public Operand derivative(){
    if(getOperand() instanceof Number)
      return new Number(0);
    else return new BinaryOp(BinaryOp.Op.DIV, getOperand().derivative(),getOperand());
    
  }
  //method for value of a polynom
  //@return value of logarithm in double type
  @Override 
  public double value(){
    if(getOperand() instanceof Number){
      Number e = (Number)getOperand();
      return Math.log10(e.value());
    }
    else if(getOperand() instanceof Variable){
      throw new UnsupportedOperationException();
    }
    else{
      BinaryOp b = (BinaryOp)getOperand();
      if(b.getLeftOperand() instanceof Variable || b.getRightOperand() instanceof Variable)
        throw new UnsupportedOperationException();
      else{
        return Math.log10(b.value());
      }
    }    
  }
  
  //method for value of a polynom with variable x
  //@return value of polynom in double type
  @Override
  public double value(double number){
    if(getOperand() instanceof Number)
      return new Log(getOperand()).value();
    if(getOperand() instanceof Variable)
      return new Log(new Number(number)).value();
    else{
      BinaryOp b = (BinaryOp)getOperand();
      if(b.getLeftOperand() instanceof Variable || b.getRightOperand() instanceof Variable)
        return new Log(new Number(b.value(number))).value();
      else return new Log(new Number(b.value())).value();                 
        
    }
  }
}
