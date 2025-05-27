// Name: J1-024-21  
// Date: 9/3/2021

import java.util.*;
import java.io.*;
public class PigLatin {
   public static void main(String[] args) {
      // part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig() {
      Scanner sc = new Scanner(System.in);
      while(true) {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1")) {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s) {
   
      if(s.length() == 0) 
         return "";
         
      String original = s;   
      String pigLatin = "";
      
      //remove and store the beginning punctuation 
      String startPunctuation = "";
      int ct = 1;
      boolean beginPunct = true;
      for (int a = 0; a < s.length(); a++) {
         if (!Character.isLetter(s.charAt(a))) {
            for (int b = 0; b < punct.length(); b++) {
               if (s.charAt(a) == punct.charAt(b) && beginPunct == true) startPunctuation += s.charAt(a);
            }
         }
         else if (Character.isLetter(s.charAt(a))) {
            beginPunct = false;
            ct--;
            if (beginPunct == false && ct == 0) s = s.substring(s.indexOf(startPunctuation) + startPunctuation.length());
         }
      }
           
      //remove and store the ending punctuation 
      String endPunctuation = "";
      ct = 1;
      boolean endPunct = true;
      for (int a = s.length()-1; a >= 0; a--) {
         if (!Character.isLetter(s.charAt(a))) {
            for (int b = 0; b < punct.length(); b++) {
               if (s.charAt(a) == punct.charAt(b) && endPunct == true) endPunctuation += s.charAt(a);
            }
         }
         else if (Character.isLetter(s.charAt(a))) {
            endPunct = false;
            ct--;
            if (endPunct == false && ct == 0) a = 0;
         }
      }
      String reverse = "";
      for (int a = endPunctuation.length()-1; a >= 0; a--) reverse += endPunctuation.charAt(a);
      s = s.substring(0, s.lastIndexOf(reverse));
      String woPunct = s;
   
            //START HERE with the basic case:
      //     find the index of the first vowel
      s = Character.toLowerCase(s.charAt(0)) + s.substring(1);
      boolean vowelFound = false;
      boolean hasWay = false;
      for (int a = 0; a < s.length(); a++) {
         for (int b = 0; b < vowels.length(); b++) {
            if (a == 0 && s.charAt(a) == vowels.charAt(b) && vowelFound == false) {
               pigLatin = s + "way"; 
               vowelFound = true;
               hasWay = true;
            }
            else if (a != 0 && s.charAt(a) == vowels.charAt(b) && vowelFound == false) {
               vowelFound = true;
               pigLatin = s.substring(a) + s.substring(0,a) + "ay";
            }
         }
      }
      
      //     y is a vowel if it is not the first letter
      //     qu
      if (s.length() >= 2) {
         for (int a = 0; a < s.length()-1; a++) {
            if (s.substring(a, a+2).equalsIgnoreCase("qu") && hasWay == false) pigLatin = s.substring(a+2) + s.substring(0, a+2) + "ay";
         }
         
         boolean yVowelFound = false;
         boolean vowelFound2 = false;
         for (int a = 0; a < s.length(); a++) {
            for (int b = 0; b < vowels.length(); b++) {
               if (s.charAt(a) == vowels.charAt(b) && vowelFound2 == false) vowelFound2 = true;
               if (vowelFound2 == false && a > 0 && s.charAt(a) == 'y') pigLatin = s.substring(a) + s.substring(0,a).toLowerCase() + "ay";
            }
         }
      }
      
         //if no vowel has been found
      boolean vowelFound3 = false;
      if (pigLatin == "") {
         for (int a = 0; a < s.length(); a++) {
            for (int b = 0; b < vowels.length(); b++) {
               if (s.charAt(a) == vowels.charAt(b)) vowelFound3 = true;
               if (a == s.length()-1 && b == vowels.length()-1 && vowelFound3 == false) 
                  return "**** NO VOWEL ****";
            }
         }
      }
         
         //is the first letter capitalized?
      if (Character.isUpperCase(woPunct.charAt(0)) == true) pigLatin = Character.toUpperCase(pigLatin.charAt(0)) + pigLatin.substring(1);
      
      //return the piglatinized word 
      return startPunctuation + pigLatin + reverse;
   }


   public static void part_2_using_piglatenizeFile() {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while (infile.hasNext()) {
         String line = "";
         String s = infile.nextLine();
         String[] splitS = s.split(" ");
         for (int a = 0; a < splitS.length; a++) splitS[a] = pig(splitS[a]);
         for (int a = 0; a < splitS.length; a++) {
            if (a != splitS.length-1) line += splitS[a] + " ";
            else line += splitS[a];
         }
         if (!infile.hasNext()) outfile.print(line);
         else if (s.equals("")) outfile.println();
         else outfile.println(line);
      }
      outfile.close();
      infile.close();
   }
   
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s) {
      if(s.length() == 0)
         return "";
         
      String startPunctuation = "";
      int ct = 1;
      boolean beginPunct = true;
      for (int a = 0; a < s.length(); a++) {
         if (!Character.isLetter(s.charAt(a))) {
            for (int b = 0; b < punct.length(); b++) {
               if (s.charAt(a) == punct.charAt(b) && beginPunct == true) startPunctuation += s.charAt(a);
            }
         }
         else if (Character.isLetter(s.charAt(a))) {
            beginPunct = false;
            ct--;
            if (beginPunct == false && ct == 0) s = s.substring(s.indexOf(startPunctuation) + startPunctuation.length());
         }
      }
           
      String endPunctuation = "";
      ct = 1;
      boolean endPunct = true;
      for (int a = s.length()-1; a >= 0; a--) {
         if (!Character.isLetter(s.charAt(a))) {
            for (int b = 0; b < punct.length(); b++) {
               if (s.charAt(a) == punct.charAt(b) && endPunct == true) endPunctuation += s.charAt(a);
            }
         }
         else if (Character.isLetter(s.charAt(a))) {
            endPunct = false;
            ct--;
            if (endPunct == false && ct == 0) a = 0;
         }
      }
      String reverse = "";
      for (int a = endPunctuation.length()-1; a >= 0; a--) reverse += endPunctuation.charAt(a);
      s = s.substring(0, s.lastIndexOf(reverse));
      String woPunct = s;
      
      String pigLatin = "";
      for (int a = woPunct.length()-1; a >= 0; a--) pigLatin += woPunct.charAt(a);
      if (Character.isUpperCase(woPunct.charAt(0)) == true) pigLatin = Character.toUpperCase(pigLatin.charAt(0)) + pigLatin.substring(1, pigLatin.length()-1) + Character.toLowerCase(pigLatin.charAt(pigLatin.length()-1));
      
      return startPunctuation + pigLatin + reverse;
   }
}