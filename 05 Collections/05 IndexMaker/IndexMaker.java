// Name: J1-024-21      
// Date: 12/13/2021
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker {
   public static void main(String[] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile) {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine()) {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index) outputFile.println(entry);
   }   
}

class DocumentIndex extends ArrayList<IndexEntry> {
  /* EXTENSION ONLY */
   public static int linearCount = 0;//every time in the linearSearch a comparison is done, increase this variable.                    
   public static int binaryCount = 0;//every time in the binarySearch a comparison is done, increase this variable. 

 //constructors
   public DocumentIndex() {
      super();
   }
   
   public DocumentIndex(int len) {
      super(len);
   }
 
  /** extracts all the words from str, skipping punctuation and whitespace 
      and for each word calls addWord().  In this situation, a good way to 
      extract while also skipping punctuation is to use String's split method, 
      e.g., str.split("[., \"!?]")       
      */
   public void addAllWords(String str, int lineNum)  {
      String[] words = str.split("[., \"!?]");
      for (String w : words) {
         addWord(w, lineNum);
      }
    /*  ignore the next 4 lines, unless you are coding the EXTENSION  */   
      if( linearCount > 0 )
         System.out.println("total number of comparisons using linear search " + linearCount);
      if( binaryCount > 0 )
         System.out.println("total number of comparisons using binary search: " + binaryCount);     
   }
    
   /** calls foundOrInserted, which returns a position.  At that position,  
       updates that IndexEntry's list of line numbers with lineNum. 
       EXTENSION: calls foundOrInsertedBinary instead
   */
   public void addWord(String word, int lineNum) {
      int ct = foundOrInserted(word);
      add(lineNum);
   }
        
    /** linear-search this DocumentIndex, comparing word to the words in the 
        IndexEntry objects in this list, looking for the correct position of 
        word. If an IndexEntry with word is not already in that position, the 
        method creates and inserts a new IndexEntry at that position. The 
        method returns the position of either the found or the inserted 
        IndexEntry.
        */
   private int foundOrInserted(String word) {
      word = word.toUpperCase();
      int position = 0;
      for (int x = 0; x < size(); x++) { 
         if (word.equals(get(i))) 
            return i;
      }
      add(size(), new IndexEntry(word));
      return size();
   }
   
     /** EXTENSION
       binary-search this DocumentIndex comparing word to the words in the 
       IndexEntry objects in this list, looking for the correct position of 
       word. If the IndexEntry for that word is already there, return its  
       position. If an IndexEntry with word is not in the list, then instantiate
       and insert a new IndexEntry at the correct position. Then return that   
       position.   
       */
   public int foundOrInsertedBinary(String word) {
      return 0;
   }
}
   
class IndexEntry implements Comparable<IndexEntry> {
   //fields
   private String word;
   private ArrayList<Integer> numsList;// = new ArrayList<Integer>();
   
     //constructors
   public IndexEntry(String w) {
      word = w.toUpperCase();
      numsList = new ArrayList<Integer>();
   }
   
    /**  appends num to numsList, but only if it is not already in that list.    
      */
   public void add(int num) {
      boolean inList = false;
      ListIterator<Integer> iter = numsList.listIterator();
      while (iter.hasNext() && !inList) {
         if (iter.next() == num) inList = true;
      }
      if (!inList) numsList.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord() {
      return word;
   }
      
     /**  returns a string representation of this Index Entry.  */
   public String toString() {
      String str = word;
      int ct = 0;
      for (int x : numsList) {
         str += (" " + x);
         if (ct < numsList.length()-1) str += ",";
      }
      return str;
   }
}

/******************  SAMPLE RUN  **********
 
 Enter input file name: fish.txt
 Done.
 
******************************************************/

/******************  EXTENSION  **************************

 Enter input file name: fish.txt
 total number of comparisons using linear search 2
 total number of comparisons using linear search 6
 total number of comparisons using linear search 10
 total number of comparisons using linear search 13
 total number of comparisons using linear search 13
 total number of comparisons using linear search 17
 total number of comparisons using linear search 22
 total number of comparisons using linear search 29
 total number of comparisons using linear search 36
 total number of comparisons using linear search 36
 total number of comparisons using linear search 54
 total number of comparisons using linear search 73
 total number of comparisons using linear search 73
 total number of comparisons using linear search 110
 total number of comparisons using linear search 146
 total number of comparisons using linear search 179
 Done.
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java IndexMaker_teacher
 
 Enter input file name: fish.txt
 total number of comparisons using binary search: 4
 total number of comparisons using binary search: 11
 total number of comparisons using binary search: 16
 total number of comparisons using binary search: 22
 total number of comparisons using binary search: 22
 total number of comparisons using binary search: 26
 total number of comparisons using binary search: 30
 total number of comparisons using binary search: 37
 total number of comparisons using binary search: 44
 total number of comparisons using binary search: 44
 total number of comparisons using binary search: 58
 total number of comparisons using binary search: 75
 total number of comparisons using binary search: 75
 total number of comparisons using binary search: 98
 total number of comparisons using binary search: 119
 total number of comparisons using binary search: 141
 Done.
 ************************************************/