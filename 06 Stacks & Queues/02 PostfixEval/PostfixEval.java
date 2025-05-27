// Name: J1-024-21
// Date: 1/11/2022

import java.util.*;

public class PostfixEval {
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args) {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      /*
      // Test cases given with shell code:
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");      
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      
      // Personal Test Cases
      postfixExp.add("1.3 2.7 + -6 6 * +");
      postfixExp.add("33 -43 + -55 65 + *");
      postfixExp.add("3 4 * 5 2 / + 5 -");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("3 4 5 * 6 + *");
      postfixExp.add("3 4 5 - 6 2 * - +");
      postfixExp.add("3 4 +");
      postfixExp.add("3 4 + 5 *");
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 6 * +");
      postfixExp.add("3 4 5 + * 6 *");
      postfixExp.add("3 4 * 5 + 6 *");
      postfixExp.add("2 7 3 % +");
      postfixExp.add("2 7 + 3 %");
      postfixExp.add("2 -2 ^");
      */
      
      for( String pf : postfixExp ) System.out.println(pf + "\t\t" + eval(pf));
   }
   
   public static double eval(String pf) {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Double> stk = new Stack<Double>();
      for (String s : postfixParts) {
         if (!isOperator(s)) stk.push(Double.parseDouble(s));
         else if (isOperator(s) && !stk.isEmpty()){
            double a = stk.pop();
            double b = 0.0;
            if (!stk.isEmpty()) b = stk.pop();
            stk.push(eval(a, b, s));
         }
      }
      if (stk.isEmpty()) 
         return 0.0;
      return stk.peek();
   }
   
   public static double eval(double a, double b, String ch) {
      if (ch.equals("+")) 
         return a+b;
      else if (ch.equals("-")) 
         return b-a;
      else if (ch.equals("*")) 
         return a*b;
      else if (ch.equals("/")) 
         return b/a;
      else if (ch.equals("%")) {
         int aa = (int)a;
         int bb = (int)b;
         int result = bb%aa;
         return result + 0.0;
      }
      else if (ch.equals("^")) {
         double val = b;
         if (a < 0) {
            a *= -1.0;
            for (double x = a; x > 1; x--) val *= b;
            val = 1 / val;
         }
         else 
            for (double x = a; x > 1; x--) val *= b;
         return val;
      }   
      else if (ch.equals("!")) {
         double val = a;
         for (double x = a-1; x >= 1; x--) val *= x;
         return val;
      }
      return 0.0;
   }
   
   public static boolean isOperator(String op) {
      return operators.indexOf(op) != -1;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/