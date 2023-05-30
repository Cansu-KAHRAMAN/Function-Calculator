//class for variables with no input
public class Variable extends Operand {
  
  //value method with no input
  //this is a exception
  public double value() {
    throw new UnsupportedOperationException();
  }
  
  //value method
  //@return number field
  public double value(double number){
    return number;
  }
  
  //calculate derivative method
  //@return 1 because derivative of a variable's is 1.
  @Override
  public Operand derivative(){
    return new Number(1);
  }
  //equals method for compare variables
  //@param second object for compare
  @Override
  public boolean equals(Object o) {
    if (o instanceof Variable) {
      Variable e = (Variable)o;
      return this == e;
    }
    else
      return false;
  }
  
  //toString method
  //@return string of variable
  @Override
  public String toString() {
    return "x";
  }  
}
