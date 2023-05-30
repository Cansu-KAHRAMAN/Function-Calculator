/**
 * an abstract class for functions
 */
public abstract class Function extends Operand {
  //field
  private Operand operand;
  
  //getter method
  //@return operand
  public Operand getOperand(){
    return operand;
  }
  //setter method
  //@param a operand
  public void setOperand(Operand operand){
    this.operand = operand;
  }
}
