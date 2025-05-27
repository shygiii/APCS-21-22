import java.util.*;
//import ap.ListNode;

public class LList2 {

   private ListNode front;
   private ListNode rear;

   public static void main(String[] args) {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head))));
                  
      ListNode rear = pointerToLast(head);
      System.out.println("" + rear.getNext().getNext());
   }
   
   public static void print(ListNode head) {
      System.out.print("[");
      while(head != null) {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   
   /* enter your code here */
   //copyNode
   public static ListNode copyNode(ListNode arg) {
      return new ListNode(arg.getValue(), arg.getNext());
   }
   
   //copyList
   public static ListNode copyList(ListNode arg) {
      if (arg == null) 
         return arg;
      return new ListNode(arg.getValue(), copyList(arg.getNext()));
   }
   
   //rest
   public static ListNode rest(ListNode h) {
      if (h == null || h.getNext() == null) 
         return null;
      return new ListNode(h.getNext().getValue(), rest(h.getNext()));
   }
   
   //first
   public static Object first(ListNode head) {
      if (head == null) 
         return null;
      return head.getValue();
   }
      
   //second
   public static Object second(ListNode head) {
      if (head == null || head.getNext() == null) 
         return null;
      return head.getNext().getValue();
   }
   
   //pointerToLast
   public static ListNode pointerToLast(ListNode head) {
      if (head == null) 
         return null;
      ListNode pointer = head;
      while (pointer.getNext() != null) pointer = pointer.getNext();
      return pointer;
   }
   
   //copyOfLast
   public static ListNode copyOfLast(ListNode head) {
      if (head == null) 
         return copyList(head);
      else if (head.getNext() == null) 
         return new ListNode(head.getValue(), head.getNext());
      return copyOfLast(head.getNext());
   }
   
   //insertFirst
   public static ListNode insertFirst(ListNode head, Object arg) {
      return new ListNode(arg, head);
   }

   //insertLast
   public static ListNode insertLast (ListNode head, Object arg) {
      ListNode pointer = head;
      while (pointer.getNext() != null) pointer = pointer.getNext();
      pointer.setNext(new ListNode(arg, null));
      return head;
   }
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 The head has a value "computer" at ListNode@15db9742
 The copy of head has a value of "computer" at ListNode@6d06d69c
 Copy the list: [computer, science, java, coffee, nonsense, boo, foo, hello]
 The rest of the list: [science, java, coffee, nonsense, boo, foo, hello]
 First of the rest = science
 Second of the rest = java
 Pointer to Last = hello at ListNode@7852e922
 Copy of Last =    hello at ListNode@4e25154f
 Insert what? p
 [p, science, java, coffee, nonsense, boo, foo, hello, p]
    
  **********************************************/