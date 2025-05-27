// Name: J1-024-21
// Date: 10/25/2021

import java.util.*;
import java.io.*;

public class MergeSort_Driver {
   public static void main(String[] args) throws Exception {
      //Part 1, for doubles
      double[] array = {3,1,4,1,5,9,2,6};    // small example array from the MergeSort handout
      /*
      int n = (int)(Math.random()*50+10);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();
     */    	
      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2, for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      MergeSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }

   
   public static void print(double[] a) {                             
      for(double number : a)    
         System.out.print(number+" ");
      System.out.println();
   }
  
   public static boolean isAscending(double[] a) {
      for (int x = 1; x < a.length; x++) 
         if (a[x] < a[x-1]) 
            return false;
      return true;
   }
  
   public static void print(Object[] peach) {
      for (int x = 0; x < peach.length; x++) System.out.print(peach[x] + " ");
   }
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a) {
      for (int x = 1; x < a.length; x++) 
         if (a[x].compareTo(a[x-1]) < 0) 
            return false;
      return true;
   }
}
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort {
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
  
   public static void sort(double[] array) { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   
   /*  array			array that is being sorted
       copyBuffer		temp space needed during the merge process
       low, high		bounds of subarray
       middle			midpoint of subarray   
   */
   private static void mergeSortHelper(double[] array, double[] copyBuffer, int low, int high) {  
      //if ( high - low  < CUTOFF )                  //switch to selection sort  when
         //SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      //else {
      if (low < high) {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   
   */
   public static void merge(double[] array, double[] copyBuffer, int low, int middle, int high) {
      // To begin, make indexes i1 and i2 point to the first items in each subarray  
      double[] firstHalf = new double[middle + 1 - low];
      double[] secondHalf = new double[high - middle];
      int firstLen = firstHalf.length;
      int secondLen = secondHalf.length;
      for (int x = 0; x < firstLen; x++) firstHalf[x] = array[x + low];
      for (int x = 0; x < secondLen; x++) secondHalf[x] = array[x + middle + 1];
      
      int i1 = 0;
      int i2 = 0;
      int y = low;
      
      // Interleave items between low and high into the copyBuffer's low and high
      //    always taking the lower of the values indexed by i1 and i2 
      while (i1 < firstLen && i2 < secondLen) {
         if (firstHalf[i1] < secondHalf[i2]) {
            copyBuffer[y] = firstHalf[i1];
            i1++;
         }
         else {
            copyBuffer[y] = secondHalf[i2];
            i2++;
         }
         y++;
      }
      
      for (int x = i1; x < firstLen; x++) {
         copyBuffer[y] = firstHalf[x];
         y++;
      }
      for (int x = i2; x < secondLen; x++) {
         copyBuffer[y] = secondHalf[x];
         y++;
      }
      
      //then copy the just-sorted items between low and high
      //  from the copyBuffer back to the array.
      for (int x = low; x <= high; x++) array[x] = copyBuffer[x];
   }	
      
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array) { 
      Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }

   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low, high		bounds of subarray
      middle			midpoint of subarray  */
   @SuppressWarnings("unchecked")
   private static void mergeSortHelper(Comparable[] array, Comparable[] copyBuffer, int low, int high) {
      if (low < high) {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   @SuppressWarnings("unchecked")
   public static void merge(Comparable[] array, Comparable[] copyBuffer, int low, int middle, int high) {
      Comparable[] firstHalf = new Comparable[middle-low+1];
      Comparable[] secondHalf = new Comparable[high-middle];
      int firstLen = firstHalf.length;
      int secondLen = secondHalf.length;
      for (int x = low; x < firstLen + low; x++) firstHalf[x - low] = array[x];
      for (int x = middle + 1; x < secondLen + middle + 1; x++) secondHalf[x - middle - 1] = array[x];
      
      int i1 = 0;
      int i2 = 0;
      int y = low;
      
      while (i1 < firstLen && i2 < secondLen) {
         if (firstHalf[i1].compareTo(secondHalf[i2]) < 0) {
            copyBuffer[y] = firstHalf[i1];
            i1++;
         }
         else {
            copyBuffer[y] = secondHalf[i2];
            i2++;
         }
         y++;
      }
      
      for (int x = i1; x < firstLen; x++) {
         copyBuffer[y] = firstHalf[x];
         y++;
      }
      for (int x = i2; x < secondLen; x++) {
         copyBuffer[y] = secondHalf[x];
         y++;
      }
      
      for (int x = low; x <= high; x++) array[x] = copyBuffer[x];
   }    	
}

/***************************************************
This is the extension.  You will have to uncomment Lines 89-90 above.
***************************************************/

class SelectionLowHigh {
   public static void sort(double[] array, int low, int high) {  
      for (int x = high; x >= low; x--) {
         int maxInd = findMax(array, low, x);
         swap(array, maxInd, x);
      }
   }
   
   private static int findMax(double[] array, int low, int upper) {
      int largestIndex = 0;
      double largestVal = array[0];
      for (int x = low; x <= upper; x++) {
         if (array[x] > largestVal) {
            largestVal = array[x];
            largestIndex = x;
         }
      }
      return largestIndex;
   }
   
   private static void swap(double[] array, int a, int b) {
      double swap = array[a];
      array[a] = array[b];
      array[b] = swap;
   } 
}