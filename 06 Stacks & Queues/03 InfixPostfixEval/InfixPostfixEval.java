// Name: J1-024-21
// Date: 1/13/2022
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval {
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args) {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
      infixExp.add("5 - 1 - 1");
      infixExp.add("5 - 1 + 1");
      infixExp.add("12 / 6 / 2");
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("1.3 + 2.7 + -6 * 6");
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3");
         
      for( String infix : infixExp ) {
         String pf = infixToPostfix(infix);  //get the conversion to work first
       //  System.out.println(infix + "\t\t\t" + pf + "\t\t\t");  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix) {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      String result = "";
      Stack<String> stk = new Stack<String>();
      for (String token : nums) {
         boolean isOper = PostfixEval.isOperator(token);
         boolean isLeft = (ParenMatch.isLeftParen(token) != -1);
         boolean isRight = (ParenMatch.isRightParen(token) != -1);
         if (!isOper && !isLeft && !isRight) result += (token + " ");
         else if (isLeft) stk.push(token);
         else if (isRight) {
            while (ParenMatch.isLeftParen(stk.peek()) == -1) result += (stk.pop() + " ");
            stk.pop();
         }
         else if (isOper) {
            while (!stk.isEmpty() && getLevel(token) <= getLevel(stk.peek())) result += (stk.pop() + " ");
            stk.push(token);
         }
      }
      while (!stk.isEmpty()) result += (stk.pop() + " ");
      return result.trim();
   }
   
   //enter your precedence method below
   public static int getLevel(String operator) {
      String[] precedence = new String[] {"+ -", "* / %", "^", "!"};
      for (int x = 0; x < precedence.length; x++) {
         if (precedence[x].indexOf(operator) != -1) 
            return x+1;
      }
      return 0;
}
}

/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/