// Name: J1-024-21
// Date: 2/5/2022

import java.util.*;

public class TreeLab {
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args) {
      root = buildTree( s );  //we are building trees of Strings only!
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
 /*  students, do not try to understand this method.
     */
   public static TreeNode buildTree(String s) {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
   
      insert(root, "AAA", 17, 5); 
      insert(root, "BBB", 18, 5); 
      insert(root, "CCC", 37, 6); //BBB's right child
      return root;
   }
   
    /*  students, do not try to understand this method.
     */
   public static void insert(TreeNode t, String s, int pos, int level) {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--) {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
// tilt your head towards your left shoulder to see the tree
   public static String display(TreeNode t, int level) {
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
   
   public static String preorderTraverse(TreeNode t) { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //process root
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t) {
      String str = "";
      if (t == null) 
         return "";
      str += inorderTraverse(t.getLeft()); 	 		//recurse left
      str += t.getValue() + " ";            			//process root
      str += inorderTraverse(t.getRight());			//recurse right
      return str;
   }
   
   public static String postorderTraverse(TreeNode t) {
      String str = "";
      if (t == null) 
         return "";
      str += postorderTraverse(t.getLeft()); 	 		
      str += postorderTraverse(t.getRight());	
      str += t.getValue() + " ";            			    	
      return str;
   }
   
   public static int countNodes(TreeNode t) {
      String str = inorderTraverse(t);
      int nodes = 0;
      for (int x = 0; x < str.length(); x++) {
         if ((str.charAt(x) + "").equals(" ")) nodes++;
      }
      return nodes;
   }
   
   public static int countLeaves(TreeNode t) {
      if (t == null) 
         return 0;
      else if (t.getLeft() == null && t.getRight() == null) 
         return 1;
      int ct = 0;
      ct += countLeaves(t.getRight());
      ct += countLeaves(t.getLeft());
      return ct;
   }
   
   /*  hard way: use t.getLeft().getLeft(), etc.
       clever way:  use height(t)
       */   
   public static int countGrandParents(TreeNode t) {
      if (t == null)
         return 0;
      if (height(t) >= 2) 
         return 1 + countGrandParents(t.getRight()) + countGrandParents(t.getLeft());
      return 0; 
   }
   
   public static int countOnlys(TreeNode t) {
      if (t == null) 
         return 0;
      else if ((t.getLeft() == null && t.getRight() != null) || (t.getLeft() != null && t.getRight() == null)) 
         return 1 + countOnlys(t.getRight()) + countOnlys(t.getLeft());
      return countOnlys(t.getRight()) + countOnlys(t.getLeft());
   }
   
  /** returns the max of the heights to the left and the heights to the right  
      returns -1 in case the tree is null
    */
   public static int height(TreeNode t) {
      if (t == null) 
         return -1;
      else if (t.getLeft() == null && t.getRight() == null) 
         return 0;
      int heightL = 0;
      int heightR = 0;
      heightR += 1 + height(t.getRight());
      heightL += 1 + height(t.getLeft());
      if (heightL > heightR) 
         return heightL;
      return heightR;
   }
   
   /* The length of the longest path connecting any two nodes.  
      Usually connects two bottom-most leaves in the tree.  
      Often goes through root, but not always. 
   */
   public static int longestPath(TreeNode t) {
      if (t == null) 
         return -1;
      int current = 2 + height(t.getLeft()) + height(t.getRight()); 
      int lenL = longestPath(t.getLeft());
      int lenR = longestPath(t.getRight());
      if (lenL > lenR && lenL > current) 
         return lenL;
      if (lenR > lenL && lenR > current) 
         return lenR;
      return current;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning message
   /*  Objects in a TreeNode must be cast to String or Comparable 
           in order to call .compareTo  
       */
   public static String min(TreeNode t) {
      return min(t, "" + t.getValue());  //calls the private recursive methdod
   }
   
   private static String min(TreeNode t, String min) {
      if (t == null) 
         return min;
      String left = min(t.getLeft(), min);
      String right = min(t.getRight(), min);
      if (min.compareTo(right) > 0 && right != null) min = right;
      if (min.compareTo(left) > 0 && left != null) min = left;
      if (min.compareTo(t.getValue() + "") > 0) min = t.getValue() + "";
      return min;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning message
   /*  Objects in a TreeNode must be cast to String or Comparable 
           in order to call .compareTo  */
   public static String max(TreeNode t) {
      return max(t, ""+t.getValue());
   }
   
   private static String max(TreeNode t, String max) {
      if (t == null) 
         return max;
      String left = max(t.getLeft(), max);
      String right = max(t.getRight(), max);
      if (max.compareTo(right) < 0 && right != null) max = right;
      if (max.compareTo(left) < 0 && left != null) max = left;
      if (max.compareTo(t.getValue() + "") < 0) max = t.getValue() + "";
      return max;
   }
      
  /* this method is not recursive.  Use a local queue
     to store the children, if they exist, of the current TreeNode.
     */
   public static String displayLevelOrder(TreeNode t) {
      String str = "";
      Queue<TreeNode> nodes = new LinkedList<TreeNode>();
      nodes.add(root);      
      while (!nodes.isEmpty()) {
         TreeNode current = nodes.remove();      
         if (current.getLeft() != null) nodes.add(current.getLeft());
         if (current.getRight() != null) nodes.add(current.getRight());
         str += current.getValue() + " ";
      }
      return str;
   }
}

/***************************************************
  
       			E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					CCC
 				BBB
 		P
 				AAA
 			R
 
 Preorder: C O P R AAA S BBB CCC U C I M T E N E C E 
 Inorder: R AAA P BBB CCC S O C U I C E T N M C E E 
 Postorder: AAA R CCC BBB S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = AAA
 Max = U
 
 By Level: 
 C O M P U T E R S C I E N C E AAA BBB CCC 
     
 /*******************************************************/