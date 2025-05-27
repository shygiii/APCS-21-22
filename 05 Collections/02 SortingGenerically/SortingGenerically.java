// Name: J1-024-21  
// Date: 12/5/2021

import java.io.*;      //the File class
import java.util.*;    //ArrayList & the Scanner class in Java 1.5
 
public class SortingGenerically {
   public static void main(String[] args) throws Exception {
      //Widgets
      List<Comparable> apple = inputWidgets("widgets.txt");
      sort(apple);
      output(apple);
      System.out.println("There are " + apple.size() +" widgets, sorted.");
      
      //Strings
      List<Comparable> strList = inputStrings("strings.txt");
      sort(strList);
      output(strList);
      System.out.println("There are " + strList.size() +" strings, alphabetized.");
   }
   
   public static List<Comparable> inputWidgets(String filename) throws Exception {
      List<Comparable> lst = new ArrayList<Comparable>();
      Scanner infile = null;
      try {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e) {
         System.out.println("File not found");
      }
      while (infile.hasNext()) {
         String cubit = infile.nextLine();
         String hand = infile.nextLine();
         Widget w = new Widget(Integer.parseInt(cubit), Integer.parseInt(hand));
         lst.add(w);
      }
      return lst;
   }
   
   public static List<Comparable> inputStrings(String filename) throws Exception {
      List<Comparable> lst = new ArrayList<Comparable>();
      Scanner infile = null;
      try {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e) {
         System.out.println("File not found");
      }
      while (infile.hasNext()) {
         String s = infile.next();
         lst.add(s);
      }
      return lst;   
   }
	
   /*  these methods are all GENERIC   */
   public static <T extends Comparable<T>> void sort(List<T> array) {
      for (int x = array.size()-1; x >= 0; x--) {
         int max = findMax(array, x);
         swap(array, max, x);
      }
   } 
   public static <T extends Comparable<T>> int findMax(List<T> array, int upper) {
      int ind = 0;
      T val = array.get(0);
      for (int x = 0; x <= upper; x++) {
         if (array.get(x).compareTo(val) > 0) {
            val = array.get(x);
            ind = x;
         }
      }
      return ind;
   }
   
   public static <T> void swap(List<T> array, int a, int b) {
      T swapA = array.get(a);
      T swapB = array.get(b);
      array.set(a, swapB);
      array.set(b, swapA);
   }
   
   public static void output(List<?> array) {
      for (int x = 0; x < array.size(); x++) System.out.println((array.get(x).toString()));
   }
}

/*************************************
 0 cubits 14 hands
 1 cubits 3 hands
 2 cubits 14 hands
 5 cubits 14 hands
 10 cubits 14 hands
 11 cubits 11 hands
 12 cubits 0 hands
 12 cubits 7 hands
 13 cubits 9 hands
 15 cubits 12 hands
 17 cubits 5 hands
 18 cubits 13 hands
 19 cubits 13 hands
 19 cubits 13 hands
 22 cubits 6 hands
 23 cubits 7 hands
 24 cubits 15 hands
 24 cubits 15 hands
 26 cubits 2 hands
 28 cubits 5 hands
 28 cubits 12 hands
 29 cubits 15 hands
 31 cubits 0 hands
 32 cubits 1 hands
 32 cubits 11 hands
 32 cubits 11 hands
 32 cubits 12 hands
 35 cubits 3 hands
 39 cubits 2 hands
 39 cubits 5 hands
 41 cubits 10 hands
 43 cubits 2 hands
 43 cubits 5 hands
 43 cubits 6 hands
 51 cubits 2 hands
 54 cubits 14 hands
 55 cubits 8 hands
 56 cubits 3 hands
 57 cubits 12 hands
 62 cubits 15 hands
 63 cubits 0 hands
 64 cubits 13 hands
 67 cubits 3 hands
 70 cubits 0 hands
 73 cubits 5 hands
 74 cubits 7 hands
 75 cubits 9 hands
 81 cubits 5 hands
 85 cubits 14 hands
 86 cubits 3 hands
 90 cubits 13 hands
 91 cubits 3 hands
 92 cubits 1 hands
 92 cubits 8 hands
 96 cubits 1 hands
 98 cubits 8 hands
 99 cubits 5 hands
 There are 57 widgets, sorted.
 APCS
 Encapsulation
 Exam
 Generics
 Inheritance
 Java
 Method
 OOP
 Object
 Oriented
 Polymorphism
 Programming
 There are 12 strings, alphabetized.   
 ****************************************/