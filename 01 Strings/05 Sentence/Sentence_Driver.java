// Name: J1-024-21
// Date: 9/14/2021
  
public class Sentence_Driver {
   public static void main(String[] args) {
   
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
     
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      
      
      
      s = new Sentence( "Hannah is? Si, Hannah!" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "??This is a test! tseT a si sihT!!" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "So, I go to Gio's..." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   // Lots more test cases.  Test every line of code.  Test
   // the extremes, test the boundaries.  How many test cases do you need?
   }
}

class Sentence {
   private String mySentence;
   private int myNumWords;
   
   //Precondition:  str is not empty.
   //               Words in str separated by exactly one blank.
   public Sentence( String str ) { 
      mySentence = str;
      String[] splitSentence = str.split(" ");
      myNumWords = splitSentence.length;
   }
   
   public int getNumWords() {
      return myNumWords;  
   }
   
   public String getSentence() {
      return mySentence; 
   }
   
   //Returns true if mySentence is a palindrome, false otherwise.
   public boolean isPalindrome() {
      String s = mySentence;
      s = lowerCase(s);
      s = removePunctuation(s);
      s = removeBlanks(s);
      return isPalindrome(s, 0, s.length()-1);       
   }
   
   //Precondition: s has no blanks, no punctuation, and is in lower case.
   //Returns true if s is a palindrome, false otherwise.
   public static boolean isPalindrome( String s, int start, int end ) {
      if (start >= end) 
         return true;
      else if (s.charAt(start) == s.charAt(end)) 
         return isPalindrome(s, start+1, end-1);
      else 
         return false;
   }
   
   //Returns copy of String s with all blanks removed.
   //Postcondition:  Returned string contains just one word.
   public static String removeBlanks( String s ) {  
      String[] splitted = s.split(" ");
      String noBlanks = "";
      for (int a = 0; a < splitted.length; a++) noBlanks += splitted[a];
      return noBlanks;
   }
   
   //Returns copy of String s with all letters in lowercase.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String lowerCase( String s ) {  
      return s.toLowerCase(); 
   }
   
   //Returns copy of String s with all punctuation removed.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String removePunctuation( String s ) {
      String punct = ".,'?!:;\"(){}[]<>"; 
      String noPunct = "";
      for (int a = 0; a < s.length(); a++) {
         boolean addChar = true;
         for (int b = 0; b < punct.length(); b++) {
            if (s.charAt(a) == punct.charAt(b)) addChar = false; 
            if (b == punct.length()-1 && addChar == true) noPunct += s.charAt(a);
         }
      }
      return noPunct;
   }
}

 /*****************************************
   
 PALINDROME TESTER
 "Hello there!" she said.
 4
 false
 
 A Santa lived as a devil at NASA.
 8
 true
 
 Flo, gin is a sin! I golf.
 7
 true
 
 Eva, can I stab bats in a cave?
 8
 true
 
 Madam, I'm Adam.
 3
 true

 **********************************************/