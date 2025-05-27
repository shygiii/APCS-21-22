// Name: J1-024-21             Date: 4/9/22

import java.util.*;
import java.io.*;
public class deHuffman {
   public static void main(String[] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   
   public static TreeNode huffmanTree(Scanner huffLines) {
      TreeNode head = new TreeNode("");
      TreeNode pointer = head;
      while (huffLines.hasNext()) {
         String line = huffLines.next();
         String leaf = line.charAt(0) + "";
         boolean isSpace = false;
         int lineLen = line.length();
         if (leaf.equals("0") || leaf.equals("1")) {
            leaf = " ";
            isSpace = true;
         }
         for (int x = 1; x < lineLen; x++) {
            if (isSpace) {
               x--;
               isSpace = false;
            }
            if (line.charAt(x) == '0') {
               if (pointer.getLeft() == null) pointer.setLeft(new TreeNode(""));
               pointer = pointer.getLeft();
            }
            else if (line.charAt(x) == '1') {
               if (pointer.getRight() == null) pointer.setRight(new TreeNode(""));
               pointer = pointer.getRight();
            }
         }  
         pointer.setValue(leaf); 
         pointer = head;
      }
      return head;
   }
   
   public static String dehuff(String text, TreeNode root) {
      TreeNode pointer = root;
      String msg = "";
      for (int x = 0; x < text.length(); x++) {
         if (text.charAt(x) == '0') pointer = pointer.getLeft();
         else if (text.charAt(x) == '1') pointer = pointer.getRight();
         if (pointer.getLeft() == null && pointer.getRight() == null) {
            msg += pointer.getValue();
            pointer = root;
         }
      }
      return msg;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode {
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue) { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue() { 
      return value; 
   }
   
   public TreeNode getLeft() { 
      return left; 
   }
   
   public TreeNode getRight() { 
      return right; 
   }
   
   public void setValue(Object theNewValue) { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight) { 
      right = theNewRight;
   }
}