/**
 * Class for numbers
 */
public class Number extends Operand {
  
  //field for number
  private double number;
  
  //constructor
  //@param a number
  public Number(double number) { 
    this.number = number;
  }
  
  //value method with a input
  //@param does not matter returns 
  @Override
  public double value(double n) {
    return number;
  }
  
  //value method
  //@return number field
  @Override
  public double value(){
    return number;
  }
  
  //calculate derivative method
  //@return 0 because derivative of a number's is 0.
  @Override
  public Operand derivative(){
    return new Number(0);
  }
  
  //equals method for compare numbers
  //@param second object for compare
  @Override
  public boolean equals(Object o) {
    if (o instanceof Number) {
      Number e = (Number)o;
      return this.value() == e.value();
    }
    else
      return false;
  }
  
  //toString method
  //@return string of number
  @Override
  public String toString() {
    return this.value() +"";
  }
}
