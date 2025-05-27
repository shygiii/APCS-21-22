// Name: J1-024-21
// Date: 2/6/2022

/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT {
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT() {
      root = null;
   }
   
   public TreeNode getRoot() {  //for Codepost
      return root;
   }
    
   public void buildTree(String str) {
      String[] postfix = str.split(" ");
      Stack<TreeNode> terms = new Stack<TreeNode>();
      for (String s:postfix) {
         TreeNode node = new TreeNode(s);
         if (isOperator(s) && !terms.isEmpty()) {
            node.setRight(terms.pop());
            node.setLeft(terms.pop());
            terms.push(node);
         }
         else terms.push(node);
      }
      root = terms.pop();
   }
   
   public double evaluateTree() {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t) { //recursive
      if (t == null) 
         return 0.0;
      else if (isOperator(t.getValue() + "")) 
         return computeTerm(t.getValue() + "", evaluateNode(t.getRight()), evaluateNode(t.getLeft()));
      return Double.parseDouble(t.getValue() + "");
   }
   
   private double computeTerm(String s, double a, double b) {
      if (s.equals("+")) 
         return a+b;
      else if (s.equals("-")) 
         return b-a;
      else if (s.equals("*")) 
         return a*b;
      else if (s.equals("/")) 
         return b/a;
      else if (s.equals("%")) {
         int aa = (int)a;
         int bb = (int)b;
         int result = bb%aa;
         return result + 0.0;
      }
      else if (s.equals("^")) {
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
      else if (s.equals("!")) {
         double val = a;
         for (double x = a-1; x >= 1; x--) val *= x;
         return val;
      }
      return 0.0;
   }

   private boolean isOperator(String s) {
      return operators.indexOf(s) != -1;
   }
   
   public String display() {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level) {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse() {
      return inorderTraverse(root);
   }
   
   private String inorderTraverse(TreeNode t) {
      String str = "";
      if (t == null) 
         return "";
      str += inorderTraverse(t.getLeft()); 	 		
      str += t.getValue() + " ";            		
      str += inorderTraverse(t.getRight());		
      return str;
   }
   
   public String preorderTraverse() {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root) {
      String str = "";
      if (root == null) 
         return "";
      str += root.getValue() + " ";            		
      str += preorderTraverse(root.getLeft()); 	 		
      str += preorderTraverse(root.getRight());		
      return str;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}