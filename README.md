# Function-Calculator
This program is a function calculator. Each function can be a function of one variable (ex: 3x^2-6)
or zero variables (ex: 57^4-10). The variable will always be "x". You can use the calculator to give the
value of the function at any point or to compute the derivative of the function.

The types are Variable, Number, BinaryOp, Polynomial, and Log.
Variable: represents the variable x.
Number: represents a number as a double.
BinaryOp: represents a binary operator (+, -, *, /) and two function operands. The constructor should take three  values: an enum that represents the operator, the left and right operands.
Polynomial: represents a function raised to a power. The constructor should take two values: a
function that is the operand and a double that is the power.
Log: Represents the natural logarithm function.

Operand (Imethods)		 		     	
            / \	     \	        \			
     Number Variable BinaryOp	  Function  
				/	\							 
Polynomial    Log 
this is our hiearchy.

Imethods is a interface. Imethods has value() and value(double) methods.
Operand and Function abstract classes.
BinaryOp has inner class named Op.
Op is a enum.

