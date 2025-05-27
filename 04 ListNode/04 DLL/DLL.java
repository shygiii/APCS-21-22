// Name: J1-024-21
// Date: 11/21/2021

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL {       //DoubleLinkedList
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   //no constructor needed
   
   /* two accessor methods  */
   public int size() {
      return size;
   }
   
   public DLNode getHead() {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj) {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException { //this the way the real LinkedList is coded
      if (index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      if (size == 0) head.setNext(new DLNode(obj, head, head));
      else {
         DLNode pointer = head;
         for (int x = 0; x < index; x++) pointer = pointer.getNext();
         DLNode insert = new DLNode(obj, pointer, pointer.getNext());
         pointer.setNext(insert);
         pointer.getNext().setPrev(insert);
      }
      size++; 
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head;
      for (int x = 0; x <= index; x++) pointer = pointer.getNext();
      return pointer.getValue();  
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode pointer = head;
      for (int x = 0; x <= index; x++) pointer = pointer.getNext();
      Object rep = pointer.getValue();
      pointer.setValue(obj);
      return rep;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      if (size == 0) {
         head.setValue(null);
         head.setPrev(null);
         head.setNext(null);
         return head;
      }
      DLNode pointer = head;
      for (int x = 0; x < index; x++) pointer = pointer.getNext();
      Object removed = pointer.getNext().getValue();  
      pointer.setNext(pointer.getNext().getNext());
      (pointer.getNext().getNext()).setPrev(pointer);
      size--;
      return removed;
   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj) {
      DLNode pointer = head;
      DLNode insert = new DLNode(obj, pointer, pointer.getNext());
      pointer.setNext(insert);
      pointer.getNext().setPrev(insert);
      size++; 
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj) {
      if (size == 0) head.setNext(new DLNode(obj, head, head));
      else {
         DLNode pointer = head;
         for (int x = 0; x < size; x++) pointer = pointer.getNext();
         DLNode insert = new DLNode(obj, pointer, pointer.getNext());
         pointer.setNext(insert);
         pointer.getNext().setPrev(insert);
      }
      size++; 
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst() {
      if (size == 0) 
         return null;
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  
     */
   public Object getLast() {
      DLNode pointer = head;
      if (size == 0) 
         return pointer.getValue();
      for (int x = 0; x < size; x++) pointer = pointer.getNext();
      return pointer.getValue();  
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst() {
      if (head == null || size == 0) 
         return null;
      else {
         DLNode pointer = head;
         Object removed = pointer.getValue();  
         pointer = pointer.getPrev();
         pointer.setNext(pointer.getNext().getNext());
         (pointer.getNext().getNext()).setPrev(pointer);
         size--;
         return removed;
      }
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast() {
      if (head == null || size == 0) 
         return null;
      else {
         DLNode pointer = head;
         for (int x = 0; x < size-1; x++) pointer = pointer.getNext();
         Object removed = pointer.getNext().getValue();  
         pointer.setNext(pointer.getNext().getNext());
         (pointer.getNext().getNext()).setPrev(pointer);
         size--;
         return removed;
      }
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString() {
      DLNode pointer = head.getNext();
      String str = "[";
      int x = 0;
      while (x < size-1 && pointer.getValue() != null) {
         str += (pointer.getValue() + ", ");
         pointer = pointer.getNext(); 
         x++;
      }
      str += (pointer.getValue() + "]");
      return str;
   }
}