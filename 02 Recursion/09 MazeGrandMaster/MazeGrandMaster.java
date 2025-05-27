// Name: J1-024-21
// Date: 10/5/2021

import java.util.*;
import java.io.*;
import java.io.File;

public class MazeGrandMaster {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next() + ".txt");
      // Maze m = new Maze();    
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all paths.");
      System.out.println("1: Find the shortest path\n\tIf no path exists, say so.");
      System.out.println("2: Mark only the shortest correct path and display the count of STEPs.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
   } 
}

class Maze {
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   
   //Instance Fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This no a arg constructor that generates a random maze
	 */
   public Maze() {
   
   }
	
	/* 
	 * Copy Constructor  
	 */
   public Maze(char[][] m) {
      maze = m;
      for(int r = 0; r < maze.length; r++) {
         for(int c = 0; c < maze[0].length; c++) {
            if(maze[r][c] == START) {     //identify start
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
   public Maze(String filename) {
      Scanner infile = null;
      try {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e) {
         System.out.println("File not found");
      }
      int r = infile.nextInt();
      int c = infile.nextInt();
      char[][] m = new char[r][c];
      infile.nextLine();
      int rowCt = 0;
      while (infile.hasNext()) {
         String line = infile.next();
         for (int x = 0; x <= line.length()-1; x++) {
            m[rowCt][x] = line.charAt(x);
            if (x == line.length()-1) rowCt++;
         }
      }
      maze = m;
      for(int x = 0; x < maze.length; x++) {
         for(int y = 0; y < maze[0].length; y++) { 
            if(maze[x][y] == START) { 
               startRow = x;
               startCol = y;
            }
         }        
      }     
   }

   
   public char[][] getMaze() {
      return maze;
   }
   
   public void display() {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++) {
         for(int b = 0; b<maze[0].length; b++) {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }
   
   public void solve(int n) {
      switch(n) {
         case 1: {
            int shortestPath = findShortestLengthPath(startRow, startCol);
            if( shortestPath!=-1 )
               System.out.println("Sortest path is " + shortestPath);
            else
               System.out.println("No path exists."); 
            break;
         }   
            
         case 2: {
            String strShortestPath = findShortestPath(startRow, startCol);
            if( strShortestPath.length()!=0 ) {
               System.out.println("Sortest lenght path is: " + getPathLength(strShortestPath));
               System.out.println("  Sortest path is: " + strShortestPath);
               markPath(strShortestPath);
               display();  //display solved maze
            }
            else
               System.out.println("No path exists."); 
            break;
         }
         default:
            System.out.println("File not found");   
      }
   }
   
 /*  1   recur until you find E, then return the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
 */ 
   public int findShortestLengthPath(int r, int c) {
   //System.out.println(r + "    " + c);
      int minimum = 1000;
      if (r < 0 || r >= maze.length ||c < 0 || c >= maze[0].length) 
         return 1000;
      if ((r >= 0 && r < maze.length) && (c >= 0 && c < maze[0].length)) {      
         if (maze[r][c] == WALL || maze[r][c] == 'O') 
            return 1000;
         else if (maze[r][c] == EXIT) {
            return 0;
         }
         else if (maze[r][c] == START || maze[r][c] == DOT) {
            
            if (maze[r][c] == DOT) maze[r][c] = 'O';
            if (maze[r][c] != START) {
               int nextR = findShortestLengthPath(r+1, c);
               int prevR = findShortestLengthPath(r-1, c);
               int nextC = findShortestLengthPath(r, c+1);
               int prevC = findShortestLengthPath(r, c-1);
               int[] mins = new int[] {nextR, prevR, nextC, prevC};
               for (int x = 0; x < mins.length; x++) {
                  if (x == 0 && mins[0] != -1) minimum = mins[0];
                  minimum = Math.min(mins[x], minimum);
               }
               System.out.println(minimum);
               if (minimum != -1) 
                  return 1 + minimum;
            }
         }
         else {
            maze[r][c] = DOT;
            if (minimum != -1) 
               return 1 + minimum;
         }
            
      }
      return -1;
   }


   
/*  2   recur until you find E, then build the True path 
     use only the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
 */
   public String findShortestPath(int r, int c)
   {
      return ""; //replace this with something useful
   }	


   //a recursive method that takes an argument created by the method 2 in the form of 
   //((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
   //and it marks the actual path in the maze with STEP
   //precondition:   the String is either an empty String or one that has the correct format above
   //                the indexes must be correct for this method to work  
   public void markPath(String strPath)
   {
   
   }
   
   public int getPathLength(String shortestPath) {
      return 1;
   }
   
}

 // Enter the maze's filename (no .txt): maze0
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW.E
 // S.W.WW.W
 // W......W
 // WWWWWWWW
 // 
 // Options: 
 // 1: Find the shortest path
 // 	If no path exists, say so.
 // 2: Mark only the shortest correct path and display the count of STEPs.
 // 	If no path exists, say so.
 // Please make a selection: 2
 // Sortest lenght path is: 10
 //   Sortest path is: ((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW*E
 // S*W.WW*W
 // W******W
 // WWWWWWWW