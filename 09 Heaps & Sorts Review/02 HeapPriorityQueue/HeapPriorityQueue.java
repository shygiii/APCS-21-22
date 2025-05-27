//Name: J1-024-21  
//Date: 3/27/22
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> {
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue() {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap() {  //for Codepost
      return myHeap;
   }
   
   public int lastIndex() {
      return myHeap.size()-1;
   }
   
   public boolean isEmpty() {
      return myHeap.size() < 2;
   }
   
   public boolean add(E obj) {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove() {
      swap(1, lastIndex());
      E val = myHeap.remove(lastIndex());
      heapDown(1, lastIndex());
      return val;
   }
   
   public E peek() {
      if (isEmpty() || myHeap.size() <=1) return null;
      return myHeap.get(1);
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k) {
      int parentInd = k/2;      
      if (parentInd < 1) 
         return;
      else if (myHeap.get(k).compareTo(myHeap.get(parentInd)) < 0) {
         swap(parentInd, k);
         heapUp(k/2);
      }
      return;
   }
   
   private void swap(int a, int b) {
      E aa = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, aa);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex) {
      int childL = 2*k;
      int childR = 2*k + 1;
      int min = 0;      
      if (childL > lastIndex || k > lastIndex) 
         return;
      else if (childR > lastIndex) {
         if (myHeap.get(childL).compareTo(myHeap.get(k)) < 0) swap(k, childL);
         else 
            return;
      }
      else {
         if (myHeap.get(childL).compareTo(myHeap.get(childR)) < 0) min = childL;
         else min = childR;
         if (myHeap.get(k).compareTo(myHeap.get(min)) > 0) {
            swap(k, min);
            heapDown(min, lastIndex);
         }
      }  
   }

   
   public String toString() {
      return myHeap.toString();
   }  
}