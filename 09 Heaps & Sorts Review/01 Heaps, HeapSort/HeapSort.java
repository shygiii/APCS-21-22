// Name: J1-024-21
// Date: 3/23/22

public class HeapSort {
   public static int N;  //9 or 100
	
   public static void main(String[] args) {
      /* Part 1: Given a heap, sort it. Do this part first. */
      /*
      N = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};  // size of array = N+1
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      */
      /* Part 2:  Generate 100 random numbers, make a heap, sort it.  */
      N = 100;
      double[] heap = new double[N + 1];  // size of array = N+1
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, N);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array) {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array) {
      /* enter your code here */
      for (int x = array.length-1; x > 0; x--) {
         swap(array, 1, x);
         heapDown(array, 1, x-1);
      }
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b) {
      double aa = array[a];
      double bb = array[b];
      array[a] = bb;
      array[b] = aa;
   }
   
   public static void heapDown(double[] array, int k, int lastIndex) {
      int childL = 2*k;
      int childR = 2*k + 1;
      int greater = 0;      
      if (childL > lastIndex || k > lastIndex) 
         return;
      else if (childR > lastIndex) {
         if (array[childL] > array[k]) swap(array, k, childL);
         else 
            return;
      }
      else {
         if (array[childL] > array[childR]) greater = childL;
         else greater = childR;
         if (array[k] < array[greater]) {
            swap(array, k, greater);
            heapDown(array, greater, lastIndex);
         }
      }  
   }
   
   public static boolean isSorted(double[] arr) {
      for (int x = 1; x < arr.length; x++) {
         if (arr[x] < arr[x-1]) 
            return false;
      }
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array) {  
      array[0] = -1;   //because it will become a heap
      for (int x = 1; x < array.length; x++) {
         String num = (Math.random() *100.00) + "";
         int dot = num.indexOf(".");
         array[x] = Double.parseDouble(num.substring(0, dot+3));
      }
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int lastIndex) {
      for (int x = lastIndex/2; x > 0; x--) heapDown(array, x, lastIndex);
   }
}