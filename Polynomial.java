//class for polynoms
public class Polynomial extends Function{
  //fields
  private double power;
  
  //constructor
  public Polynomial(Operand operand, double power) {
    setOperand(operand);
    this.power = power;
  }
  
  // getter method the power
  public double getPower() {
    return power;
  } 
  
  //tostring method for polynomials
  @Override
  public String toString() {
    
    
    if (getOperand() instanceof BinaryOp) {
      return "(" + getOperand() + ")^"+getPower();
    } else {
      return getOperand() + "^"+ getPower();
    }
  }
  
  //equals method for compare polynoms
  //@param a object for compare
  @Override
  public boolean equals(Object o) {
    if (o instanceof Polynomial) {
      Polynomial e = (Polynomial)o;
      return this.getOperand().value(1) == e.getOperand().value(1) && this.getPower() == getPower();
    }
    else
      return false;
  }
  
  //method for the derivative of a polynom
  @Override
  public Operand derivative(){
    //(5^2)' = 0
    if(getOperand() instanceof Number)
      return new Number(0);
    else if(getOperand() instanceof Variable){
      //(x^1)' = 1
      if(getPower() == 1)
        return new Number(1);
      //(x^2)' = 2 * (x)^1
      else return new BinaryOp(BinaryOp.Op.MULT, new Number(getPower()),new Polynomial(getOperand(),getPower()-1));                                      
    }
    else{ 
      BinaryOp b = (BinaryOp)getOperand();
      //((2+2)^3)' =0
      if(b.getLeftOperand() instanceof Number && b.getRightOperand() instanceof Number)
        return new Number(0);
      if(b.getLeftOperand() instanceof Variable || b.getRightOperand() instanceof Variable){
        //((x*3)^5)' =  5 * (x*3)^4*(3x^2)
        {
          return new BinaryOp(BinaryOp.Op.MULT,b.derivative(), 
                              new BinaryOp(BinaryOp.Op.MULT, new Number(getPower()), new Polynomial(getOperand(),getPower()-1)));
        }
        
      }
    }
    throw new UnsupportedOperationException();
  }
  
  //method for value of a polynom
  //@return value of polynom in double type
  @Override
  public double value() {
    double count = 1;
    if(getOperand() instanceof Number){
      
      Number e = (Number)getOperand();
      for(int i =1; i <=getPower();i++){
        count = count * e.value();
      }
      return count;
    }
    else if(getOperand() instanceof Variable){
      throw new UnsupportedOperationException();
    }
    else{
      BinaryOp b = (BinaryOp)getOperand();
      if(b.getLeftOperand() instanceof Variable || b.getRightOperand() instanceof Variable)
        throw new UnsupportedOperationException();
      else{
        return new Polynomial( new Number(b.value()) ,power).value();
      }
    }    
  }
  
  //method for value of a polynom with variable x
  //@return value of polynom in double type
  @Override
  public double value(double number){
    if(getOperand() instanceof Number)
      return value();
    if(getOperand() instanceof Variable)
      return new Polynomial(new Number(number), getPower()).value();
    else{
      BinaryOp b = (BinaryOp)getOperand();
      if(b.getLeftOperand() instanceof Variable || b.getRightOperand() instanceof Variable)
        return new Polynomial (new Number(b.value(number)), getPower()).value();
      else return value();                 
      
    }
  }  
}