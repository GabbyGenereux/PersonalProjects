import java.util.*;

public class Infix2PostfixConverter {
	
	/*
	 * isOperator method - determines whether the given char is an operator
	 * Paramater - char
	 * output - boolean
	 */	
	public boolean isOperator(char c)
	{
		if (c == '+'|| c == '-' || c == '*' || c == '/' || c =='(' || c == ')')
		{
			return true;
		}
		return false;
	}
	
	/*
	 * isSpace method - determines whether the given char is a space
	 * Paramater - char
	 * output - boolean
	 */
	public boolean isSpace(char c)
	{
		if(c == ' ')
		{
			return true;
		}
		return false;
	}

	/*
	 *  precedence method - determines the precedence of the two given operators
	 *  paramater - 2 chars
	 *  output - boolean representing the precendence of the two chars
	 */
	public boolean precedence(char op1, char op2)
	{
		if(op1 == '+' || op1 == '-') //determines whether the first operator is a + or - (same precedence)
		{
			return !(op2 == '+' || op2 == '-'); //return false if operation 2 is also a + or - (same/lower precedence) 
		}
		else if(op1 == '*' || op1 == '/') //determines whether operator 1 is a * or / (same precedence)
		{
			return op2 == '('; //returns true if the second operator is higher precedence
		}
		else if(op1 == '(') //returns true if the first operator is ( (highest precedence)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * convertPostfix method - converts the inputted infix into postfix
	 * paramater - infix string
	 * output - postfix string
	 */
	public String convertPostfix(String infix)
	{
		StackReferenceBased operatorStack = new StackReferenceBased();
		StringTokenizer parse = new StringTokenizer(infix, "+-*/()", true); //splits string up by delimiters (operators)
		StringBuffer postfix = new StringBuffer(infix.length()); //creates a mutable string
		char c;
		while(parse.hasMoreTokens())
		{
			String token = parse.nextToken(); 
			c = token.charAt(0);
			
			if((token.length() == 1) && isOperator(c))
			{
				while(!operatorStack.isEmpty()&& !precedence(((String)operatorStack.peek(0)).charAt(0), c)) //while the stack isn't empty and and top of the stack is at
					//a lower precedence than we are currently at for char c
				{
					postfix.append(" ").append((String)operatorStack.pop()); //append a space and the element at the top of the stack
				}					
				if(c==')') //if the char is )
				{
					String operator = (String)operatorStack.pop(); //typecast the element on the stack to string, and set it to operator
					while (operator.charAt(0)!='(') //while there isn't (
					{
						postfix.append(" ").append(operator); //append a space and the operator
						operator = (String)operatorStack.pop(); //assign the operator to the next element on the stack
					}
				}
				else
				{
					operatorStack.push(token); //push the number
				}
			}
			else if((token.length() == 1) && isSpace(c)) //empty operation, do nothing
			{
				;
			}
			else
			{
				postfix.append(" ").append(token); //append a space and the token
			}
		}
		while(!operatorStack.isEmpty()) //while it's not empty, keep appending from the stack
		{
			postfix.append(" ").append((String)operatorStack.pop());
		}
		return postfix.toString(); //return the string equivilent of the postfix
	}
	
	/*
	 * getPostfix method - gets the result of the postfix operation
	 * Paramater - string postfix expression
	 * Output - the integer result
	 */
	public static int getPostfix(String postfixExpression)
	{
		StackReferenceBased numberStack = new StackReferenceBased(); //new stack for numbers
		int number1 = 0;
		int number2 = 0;
		int result = 0;
		
		postfixExpression = postfixExpression.trim(); //trim off the empty space at the front and back of the postfix
		String[] numbers = postfixExpression.split(" "); //split up the postfix string into an array seperated by spaces
		
		ArrayList<String> tempArrayList = new ArrayList<String>();
		
		for(int q = 0; q<numbers.length;q++)
		{
			tempArrayList.add(numbers[q]); //add the element of the array to the list
		}
				
		while(tempArrayList.contains(""))
		{
			tempArrayList.remove(""); //remove empty spaces
		}
		
		String[] numbers2 = new String[tempArrayList.size()];
		
		for(int y = 0; y < numbers2.length; y++)
		{
			numbers2[y] = tempArrayList.get(y); //add the arrayList back to a new string array
		}
		
		
		for(int k = 0; k<numbers2.length; k++)
		{
			String token = numbers2[k];
			if(!"+".equals(token)&& !"-".equals(token) && !"*".equals(token) && !"/".equals(token)) //if it's not a operator (i.e it's a number)
			{
				numberStack.push(Integer.parseInt(token)); //push the number onto the stack
			}
			
			else
			{
				String operator = numbers2[k];
				number1 = (int) numberStack.pop(); //take the first two values off the stack to use in operations
				number2 = (int) numberStack.pop();				

				if(operator.equals("/"))
				{
					result= number2/number1;
				}
				else if(operator.equals("*"))
				{
					result= number1*number2;
				}
				else if(operator.equals("+"))
				{
					result= number1+number2;
				}
				else if(operator.equals("-"))
				{
					result= number2-number1;
				}
				numberStack.push(result); //push the result back to the stack for future use
			}
		}
		result = (int) numberStack.pop(); //pop the final result off the stack
		return result;
	}
	
	public static void main(String args[])
	{
		Infix2PostfixConverter convert = new Infix2PostfixConverter();
		
		//Test case #1
		String test1 = "10+3*4/6";
		System.out.println("Test for converting #1: ");
		String postfixThing = convert.convertPostfix(test1);
		System.out.println("infix: " + test1);
		System.out.println("Postfix: " + postfixThing);
		System.out.println("Result is: " +getPostfix(postfixThing));
		System.out.println();	
		
		//Test case #2
		String test2 = "12*3-4+(18/6)";
		System.out.println("Test for converting #1: ");
		String postfixThing2 = convert.convertPostfix(test2);
		System.out.println("infix: " + test2);
		System.out.println("Postfix: " + postfixThing2);
		System.out.println("Result is: " +getPostfix(postfixThing2));
		System.out.println();	
		
		//Test case #3
		String test3 = "35-42*17/2+10";
		System.out.println("Test for converting #1: ");	
		String postfixThing3 = convert.convertPostfix(test3);
		System.out.println("infix: " + test3);
		System.out.println("Postfix: " + postfixThing3);
		System.out.println("Result is: " +getPostfix(postfixThing3));
		System.out.println();	
		
		//Test case #4
		String test4 = "3*(4+5)";			
		System.out.println("Test for converting #1: ");
		String postfixThing4 = convert.convertPostfix(test4);
		System.out.println("infix: " + test4);
		System.out.println("Postfix: " + postfixThing4);
		System.out.println("Result is: " +getPostfix(postfixThing4));
		System.out.println();	
		
		//Test case #5
		String test5 = "3*(17-(5+2))/(2+3)";
		System.out.println("Test for converting #1: ");
		String postfixThing5 = convert.convertPostfix(test5);
		System.out.println("infix: " + test5);
		System.out.println("Postfix: " + postfixThing5);
		System.out.println("Result is: " +getPostfix(postfixThing5));
		System.out.println();
	}
}
