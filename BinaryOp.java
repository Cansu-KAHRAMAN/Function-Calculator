/**
 * a class for Binary Ops
 */
public class BinaryOp extends Operand {
  
  //a enum for operators 
  public enum Op {
    PLUS("+"), SUB("-"), MULT("*"),DIV("/");
    
    //field for save operators
    private String sign;
    
    //constructor of enum
    private Op(String sign) {
      this.sign = sign;
    }
    
    //get method for operator
    public String getSign() {
      return sign;
    }
  }
  //fields
  private Op operator;
  private Operand leftOperand;
  private Operand rightOperand;
  
  //constructor of BinaryOp
  public BinaryOp(Op operator, Operand leftOperand, Operand rightOperand ) { 
    this.operator = operator;
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }
  
  //getter method for operator
  public Op getOperator() {
    return operator;
  }
  
  //getter method for operator
  public Operand getLeftOperand() {
    return leftOperand;
  }
  
  //getter method for operator
  public Operand getRightOperand() {
    return rightOperand;
  }
  
  //setter method for left operand
  public void setLeftOperand(Operand leftOperand){
    this.leftOperand = leftOperand;
  }
    //setter method for right operand
  public void setRightOperand(Operand rightOperand){
    this.rightOperand =rightOperand;
  }
  
  //for BinaryOp instances a value calculator method
  @Override
  public double value() {  
    //if left or right operand is a  variable this is a exception
    if( getLeftOperand() instanceof Variable || getRightOperand() instanceof Variable)
      throw new UnsupportedOperationException();
    //if left operand is a BinarOp 
    else if( getLeftOperand() instanceof BinaryOp) {
      BinaryOp l = (BinaryOp) getLeftOperand();
      //if a variable of letoperand's operands,exception
      if (l.getLeftOperand() instanceof Variable || l.getRightOperand() instanceof Variable)
        throw new UnsupportedOperationException();
      else{
        //left operand calculate and set
        setLeftOperand(new Number(l.value()));      
      }
    }
    //if right operand is a BinarOp and operands of binary is a variable, this is a exception
    if(getRightOperand() instanceof BinaryOp){
      BinaryOp r = (BinaryOp) getRightOperand();
      
      if (r.getLeftOperand() instanceof Variable || r.getRightOperand() instanceof Variable)
        throw new UnsupportedOperationException();
      else{ 
        //right operand calculate and set
        setRightOperand(new Number(r.value())); ;      
      }
    }
    
    if(getOperator() == Op.PLUS)
      return getLeftOperand().value() + getRightOperand().value();
    if(getOperator() == Op.SUB)
      return getLeftOperand().value() - getRightOperand().value();
    if(getOperator() == Op.MULT)
      return getLeftOperand().value() * getRightOperand().value();
    if(getOperator() == Op.DIV)
      return getLeftOperand().value() / getRightOperand().value();
    else
      throw new UnsupportedOperationException();
    
  }
  
  //for BinaryOp instances a value calculator method with value of variable x
  @Override
  public double value(double number){
    //if left and right operand is a  number this is a exception
    if( getLeftOperand() instanceof Number && getRightOperand() instanceof Number)
      this.value();
    
    //if left operand is a variable, x will number
    if(  getLeftOperand() instanceof Variable)
      setLeftOperand(new Number(number));
    
    //if right operand is a variable, x will number
    if(  getRightOperand() instanceof Variable)
      setRightOperand(new Number(number));
    
    //if left operand is a BinarOp 
    if( getLeftOperand() instanceof BinaryOp) {
      BinaryOp l = (BinaryOp) getLeftOperand();
      
      //if everything is a number, 
      if (l.getLeftOperand() instanceof Number && l.getRightOperand() instanceof Number && getRightOperand() instanceof Number)
        this.value();
      
      else{
        //if left operand is a variable, x will number
        if(  l.getLeftOperand() instanceof Variable)
          l.setLeftOperand(new Number(number));
        
        //if right operand is a variable, x will number
        if(  l.getRightOperand() instanceof Variable)
          l.setRightOperand(new Number(number));
      }
      //then (left)binaryop will calculate and set
      this.setLeftOperand(new Number(l.value()));      
    }
    //if right operand is a BinarOp 
    if( getRightOperand() instanceof BinaryOp) {
      BinaryOp r = (BinaryOp) getRightOperand();
      
      //if everything is a number, exception
      if (r.getLeftOperand() instanceof Number && r.getRightOperand() instanceof Number && getLeftOperand() instanceof Number)
        this.value();
      
      else{
        //if left operand is a variable, x will number
        if(  r.getLeftOperand() instanceof Variable)
          r.setLeftOperand(new Number(number));
        
        //if right operand is a variable, x will number
        if(  r.getRightOperand() instanceof Variable)
          r.setRightOperand(new Number(number));
      }
      //then (right)binaryop will calculate and set
      this.setRightOperand(new Number(r.value()));      
    }
    
    //now all the variables are of number type, we can use the value() method
    
    return value();
  }
  
  
  //calculate derivative method
  //@return derivate of binaryOp
  @Override
  public Operand derivative(){
    //if left and right is a number
    if( getLeftOperand() instanceof Number && getRightOperand() instanceof Number)
      return new Number(0);
    //if binaryop x * x return 2*x
    if( getLeftOperand() instanceof Variable && getRightOperand() instanceof Variable)
      return new BinaryOp(BinaryOp.Op.MULT, new Number(2), new Variable());
    
    //if binaryop like 2 + x or x + 2
    if( getLeftOperand() instanceof Number && getRightOperand() instanceof Variable ||   
       getLeftOperand() instanceof Variable && getRightOperand() instanceof Number){
      
      //x + 2 or 2+x
      if( getOperator() == BinaryOp.Op.PLUS){
        return new Number(1);
      }
      
      if( getOperator() == BinaryOp.Op.SUB){
        //2-x
        if(getLeftOperand() instanceof Number)
          return new Number(-1);
        //x-2
        else return new Number(1);
      }
      if(getOperator() == BinaryOp.Op.MULT){
        //2 *x
        if(getLeftOperand() instanceof Number)
          return getLeftOperand();
        //x *  2
        else return getRightOperand();
      }
      if(getOperator() == BinaryOp.Op.DIV){
        //x/2
        if(getLeftOperand() instanceof Variable)
          return new Number(1 / getRightOperand().value());
        
        //2/x
        if(getLeftOperand() instanceof Number){
          return new BinaryOp(BinaryOp.Op.DIV, new Number( -1 * getLeftOperand().value()),
                              new BinaryOp(BinaryOp.Op.MULT, new Variable(), new Variable())); 
        }
      }
    }
    if(getLeftOperand() instanceof BinaryOp){
      BinaryOp b = (BinaryOp) getLeftOperand();
      if(b.getLeftOperand() instanceof Number && b.getRightOperand() instanceof Number){
        //((3*5)/2)' = 0
        if(getRightOperand() instanceof Number)
          return new Number(0);
        
        if(getRightOperand() instanceof Variable){
          //((3*5) +x)' = 1
          if(getOperator() ==  BinaryOp.Op.PLUS || getOperator()== BinaryOp.Op.SUB)
            return new Number(1);
           //((3*5) *x)' = 3*5
          if(getOperator()== BinaryOp.Op.MULT)
            return getLeftOperand();
          //((3*5) /x)' = -15/x*x
          if(getOperator() == BinaryOp.Op.DIV)
            return new BinaryOp(BinaryOp.Op.DIV, new Number((-1) *getLeftOperand().value()),new BinaryOp(
                                                                                                          BinaryOp.Op.MULT, new Variable(),new Variable()));
        }
      }
    }
    throw new ArithmeticException();
  }
  //equals method for compare binaryops
  //@param second object for compare
  @Override
  public boolean equals(Object o) {
    if (o instanceof BinaryOp) {
      BinaryOp e = (BinaryOp)o;
      return this.getOperator() == e.getOperator() &&  this.getLeftOperand().value(1) == e.getLeftOperand().value(1) 
        &&  this.getRightOperand().value() == e.getRightOperand().value(1);
    }
    else
      return false;
  }
  
  //toString method
  //@return string of binaryop
  @Override
  public String toString() {
    if(!(getLeftOperand() instanceof BinaryOp)){
      if(!(getRightOperand() instanceof BinaryOp)){ 
        return getLeftOperand().toString() + " " + getOperator().getSign()+ " " + getRightOperand().toString();
      }
      else{
        return getLeftOperand().toString() + " " + getOperator().getSign()+ " ("+ getRightOperand().toString() +")";
      }
    }
    else if(getRightOperand() instanceof BinaryOp)
      return "("+ getLeftOperand().toString() + ") " + getOperator().getSign()+ " ("+ getRightOperand().toString() +")";
    else return "(" + getLeftOperand().toString() + ") "+  getOperator().getSign() +" "+ getRightOperand().toString();
  }
}