// Name: J1-024-21
// Date: 10/21/21

import java.util.*;
import java.io.*;

public class SelectionSort_Driver {
   public static void main(String[] args) throws Exception {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Selection.sort(array);   //students must write
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Selection.sort(arrayStr);  //students must write
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
  
   public static void print(double[] a) {
       // for(int k = 0; k < a.length; k++)
          // System.out.println(a[k]);
      for(double temp: a)         //for-each
         System.out.print(temp+" ");
      System.out.println();
   }
  
   public static void print(Object[] papaya) {
      for(Object temp : papaya)     //for-each
         System.out.print(temp+" ");
   }
  
   public static boolean isAscending(double[] a) {
      for (int x = 1; x < a.length; x++)
         if (a[x] < a[x-1]) 
            return false;      
      return true;
   }
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a) {
      for (int x = 1; x < a.length; x++)
         if (a[x].compareTo(a[x-1]) < 0) 
            return false;      
      return true;
   }
}

/////////////////////////////////////////////////////

class Selection {
   public static void sort(double[] array) {
      for (int x = array.length-1; x >= 0; x--) {
         int maxInd = findMax(array, x);
         swap(array, maxInd, x);
      }
   }
   
   // upper controls where the inner loop of the Selection Sort ends
   private static int findMax(double[] array, int upper) {
      int largestIndex = 0;
      double largestVal = array[0];
      for (int x = 0; x <= upper; x++) {
         if (array[x] > largestVal) {
            largestVal = array[x];
            largestIndex = x;
         }
      }
      return largestIndex;
   }
   
   private static void swap(double[] array, int a, int b) {
      double swapA = array[a];
      double swapB = array[b];
      array[a] = swapB;
      array[b] = swapA;
   }   	
   
	/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array) {
      for (int x = array.length-1; x >= 0; x--) {
         int maxInd = findMax(array, x);
         swap(array, maxInd, x);
      }
   }
   
   @SuppressWarnings("unchecked")
   public static int findMax(Comparable[] array, int upper) {
      int largestIndex = 0;
      Comparable largestVal = array[0];
      for (int x = 0; x <= upper; x++) {
         if (array[x].compareTo(largestVal) > 0) {
            largestVal = array[x];
            largestIndex = x;
         }
      }
      return largestIndex;
   }
   
   public static void swap(Object[] array, int a, int b) {
      Object swapA = array[a];
      Object swapB = array[b];
      array[a] = swapB;
      array[b] = swapA;
   }
}