//Name: J1-024-21
//Date: 2/9/2022

interface BSTinterface {
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   //public boolean remove(String obj);
   public String min();
   public String max();
   public String toString();
}

/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface {
   private TreeNode root;
   private int size;
   
   public BST() {
      root = null;
      size = 0;
   }
   
   public int size(){
      return size;
   }
   
   public TreeNode getRoot() {  //for Grade-It
      return root;   
   }
   
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) {
      root = add(root, s);
   }
   
   private TreeNode add(TreeNode t, String s) { //recursive helper method
      if (t == null) {
         TreeNode newNode = new TreeNode(s);
         size++;
         return newNode;     
      }   
      else if ((t.getValue() + "").compareTo(s) < 0)
         t.setRight(add(t.getRight(), s));
      else if ((t.getValue() + "").compareTo(s) >= 0)
         t.setLeft(add(t.getLeft(), s));
      return t;   
   }
   
   public String display() {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level) { //recursive helper method
      String toRet = "";
      if(t == null) 
         return "";
      toRet += display(t.getRight(), level + 1); 
      for(int k = 0; k < level; k++) toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); 
      return toRet;
   }
   
   public boolean contains(String obj) {
      return contains(root, obj);
   }
   
   private boolean contains(TreeNode t, String x) { //recursive helper method
      if (t == null) 
         return false;
      else if ((t.getValue() + "").compareTo(x) == 0) 
         return true;
      else if ((t.getValue() + "").compareTo(x) > 0) 
         return contains(t.getLeft(), x);
      else if ((t.getValue() + "").compareTo(x) < 0) 
         return contains(t.getRight(), x);
      return false;   
   }
   
   public String min() {
      return min(root);
   }
   
   private String min(TreeNode t) { //use iteration
      if (t == null) 
         return null;
      while (t.getLeft() != null)
         t = t.getLeft();
      return t.getValue() + "";      
   }
   
   public String max() {
      return max(root);
   }
   
   private String max(TreeNode t) { //recursive helper method
      if (t == null) 
         return null;
      if (t.getRight() == null) 
         return t.getValue() + "";
      return max(t.getRight());  
   }
   
   public String toString() {
      return toString(root);
   }
   
   private String toString(TreeNode t) {  //an in-order traversal.  Use recursion.
      String str = "";
      if (t == null) 
         return "";
      str += toString(t.getLeft()); 	 		
      str += t.getValue() + " ";            		
      str += toString(t.getRight());		
      return str;
   }
}