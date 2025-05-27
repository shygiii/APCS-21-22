// Name: J1-024-21
// Date: 10/26/2021

import java.util.*;
import java.io.*;

public class QuickSort_Driver {
   public static void main(String[] args) throws Exception {
      //Part 1 for doubles
      /*
      int n = (int)(Math.random()*50 + 10);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();
      */
      double[] array = {12, 18,8,4,11,7,6,3,10,1,5,20};
      QuickSort.sort(array);
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2 for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      QuickSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   
   public static void print(double[] a) {
      for(double number : a) System.out.print(number+" ");    //doing something to each element
      System.out.println();
   }
   
   public static void print(Object[] grape) {
      for(Object fruit : grape) System.out.print(fruit +" ");
   }
      
   public static boolean isAscending(double[] a) {
      for(int i = 0; i < a.length - 1; i++) //we must access the index numbers
         if(a[i] > a[i+1])
            return false;
      return true;
   } 
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a) {
      for(int k = 1; k < a.length; k++)
         if(a[k-1].compareTo(a[k]) > 0)
            return false;
      return true;
   }
}

class QuickSort {
   public static void sort(double[] array) {
      sort(array, 0, array.length - 1);
   }
   
   private static void sort(double[] array, int first, int last) {
      int indexOfPivot;
      if (first < last) { // General case
         indexOfPivot = rearrange(array, first, last);
         sort(array, first, indexOfPivot - 1);	// sort left side
         sort(array, indexOfPivot + 1, last);	   // sort right side
      }
   }
  
   /* choose pivot and rearrange data so that
    * array[first] ...array[splitPt - 1] <= pivot and 
    * array[splitPt + 1] ... array[last] >= pivot
    */
   private static int rearrange(double[] array, int first, int last) {
      int indexOfPivot = first;    //save the index   
      double pivot = array[indexOfPivot]; //save the value of the pivot
      first++;
      while (first <= last) {    //find a pair of "out-of-place" items
         if (array[first] < pivot) first++;       //if it's on the correct side, move right      
         else if (array[last] > pivot) last--; //if it's on the correct side, move left 
         else {      //if both on the wrong side, then swap them, update both right and left
            swap(array, first, last);
            first++;
            last--;
         }
      }
      swap(array, indexOfPivot, last); 	// swap pivot with element at indexOfPivot
      indexOfPivot = last;			         // set indexOfPivot to place where the halves meet
      return indexOfPivot;
   }

   private static void swap(double[] array, int a, int b) {
      double swap = array[a];
      array[a] = array[b];
      array[b] = swap;
   }   
   
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array) {
      sort(array, 0, array.length - 1);
   }
   
   @SuppressWarnings("unchecked")
   private static void sort(Comparable[] array, int first, int last) {
      int indexOfPivot = 0;
      if (first < last) { 
         indexOfPivot = rearrange(array, first, last);
         sort(array, first, indexOfPivot - 1);	  
         sort(array, indexOfPivot + 1, last);	    
      }
   }

   @SuppressWarnings("unchecked")
   private static int rearrange(Comparable[] array, int first, int last) {
      int indexOfPivot = first;    
      Comparable pivot = array[indexOfPivot]; 
      first++;
      while (first <= last) { 
         if (array[first].compareTo(pivot) < 0) first++;            
         else if (array[last].compareTo(pivot) > 0) last--;  
         else {   
            swap(array, first, last);
            first++;
            last--;
         }
      }
      swap(array, last, indexOfPivot); 
      indexOfPivot = last;			 
      return indexOfPivot;
   }

   @SuppressWarnings("unchecked")
   private static void swap(Comparable[] array, int a, int b) {
      Comparable swap = array[a];
      array[a] = array[b];
      array[b] = swap;
   }
}