// Name: J1-024-21
// Date: 10/21/21

import java.util.*;
import java.io.*;

public class InsertionSort_Driver {
   public static void main(String[] args) throws Exception {
      //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      Insertion.sort(array);  //students write
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
   
      Insertion.sort(arrayStr);   //students write
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
      for(Object temp : papaya)    
         System.out.print(temp+" ");
   }
   
   public static boolean isAscending(double[] a) {
      for (int x = 1; x < a.length; x++)
         if (a[x] < a[x-1]) 
            return false;      
      return true;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a) {
      for (int x = 1; x < a.length; x++)
         if (a[x].compareTo(a[x-1]) < 0) 
            return false;      
      return true;
   }
}

//**********************************************************

class Insertion {
   public static void sort(double[] array) { 
      for (int x = 1; x < array.length; x++)
         if (array[x] < array[x-1]) {
            shift(array, x, array[x]);
         }
   }
 
   private static int shift(double[] array, int index, double value) {
      int right = index;
      int left = index-1;
      int indOfValue = index;
      for (int x = index; x > 0; x--) {
         if (array[right] < array[left]) {
            array[right] = array[left];
            array[left] = value;
            right--;
            left--;
         }
         else return x;
      }        
      return indOfValue;
   }
      
 
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array) { 
      for (int x = 1; x < array.length; x++)
         if (array[x].compareTo(array[x-1]) < 0) shift(array, x, array[x]);
   }
   
   @SuppressWarnings("unchecked")
   private static int shift(Comparable[] array, int index, Comparable value) {
      int right = index;
      int left = index-1;
      for (int x = index; x > 0; x--) {
         if (array[right].compareTo(array[left]) < 0) {
            array[right] = array[left];
            array[left] = value;
            right--;
            left--;
         }
         else 
            return x;
      }
      return index;
   }
}