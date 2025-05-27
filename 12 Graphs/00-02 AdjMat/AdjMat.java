// Name: J1-024-21  
// Date: 5/9/22
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix {
   public int[][] getGrid();
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public String toString();  //returns the grid as a String
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall {     //User-friendly functionality
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability();   // Warshall's Algorithm
   public List<String> getReachables(String from);  //Warshall extension
}

interface Floyd {
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix { //,Warshall//,Floyd 
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  write constructor, accessor method, and instance methods here  */  
   public AdjMat(int size) {
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>();
      nameList = new ArrayList<String>();
   }
   
   // AdjMat methods
   public int[][] getGrid() {
      return grid;
   }
   
   public void addEdge(int source, int target) {
      if (source < grid.length && target < grid.length) grid[source][target] = 1;
   }
   
   public void removeEdge(int source, int target) {
      if (source < grid.length && target < grid.length) grid[source][target] = 0;
   }
   
   public boolean isEdge(int from, int to) {
      if (from < grid.length && to < grid.length) 
         return grid[from][to] == 1;
      return false;
   }
   
   public String toString() {
      String str = "";
      for (int r = 0; r < grid.length; r++) {
         for (int c = 0; c < grid[0].length; c++) str += (grid[r][c] + "  ");
         if (r != grid.length-1) str += "\n";
      }
      return str;
   }
   
   // Modified for Floyd algorithm
   public int edgeCount() {
      int ct = 0;
      for (int[] r : grid) {
         for (int c : r) {
            if (c > 0 && c < 9999) ct++;
         } 
      }
      return ct;
   }
   
   public List<Integer> getNeighbors(int source) {
      List<Integer> neighbors = new ArrayList<Integer>();
      for (int c = 0; c < grid[source].length; c++) {
         if (isEdge(source, c)) neighbors.add(c);
      }  
      return neighbors; 
   }
   
   // Warshall methods
   public boolean isEdge(String from, String to) {
      return isEdge(vertices.get(from), vertices.get(to));
   }
   
   public Map<String, Integer> getVertices() {
      return vertices;
   }     
   
   public void readNames(String fileName) throws FileNotFoundException {
      Scanner infile = new Scanner(new File(fileName));
      int len = Integer.parseInt(infile.next());
      int ct = 0;
      while (infile.hasNext()) {
         String city = infile.next();
         vertices.put(city, ct);
         ct++;
      }
   }
   
   public void readGrid(String fileName) throws FileNotFoundException {
      Scanner infile = new Scanner(new File(fileName));
      int len = Integer.parseInt(infile.next());
      for (int r = 0; r < len; r++) {
         for (int c = 0; c < len; c++) 
            grid[r][c] = infile.nextInt();
      }
   }
   
   public void displayVertices() {
      Set<String> keys = vertices.keySet();
      for (String cities : keys) {
         Integer num = vertices.get(cities);
         System.out.println(num + "-" + cities);
      }
      System.out.println();
   }
   
   public void allPairsReachability() {
      for (int v = 0; v < grid.length; v++) {
         for (int r = 0; r < grid[0].length; r++) {
            for (int c = 0; c < grid[0].length; c++)
               if (isEdge(r, v) && isEdge(v, c)) grid[r][c] = 1;
         }
      }
   }
   
   public List<String> getReachables(String from) {
      List<String> reach = new ArrayList<String>();
      Set<String> keys = vertices.keySet();
      for (String cities : keys) nameList.add(cities);
      int city = vertices.get(from);
      for (int c = 0; c < grid[city].length; c++) {
         if (grid[city][c] == 1) reach.add(nameList.get(c));
      }
      return reach;
   }
   
   // Floyd methods
   public int getCost(int from, int to) {
      if (from < grid.length && to < grid.length) 
         return grid[from][to];
      return 9999;
   }
   
   public int getCost(String from, String to) {
      return getCost(vertices.get(from), vertices.get(to));
   }
   
   public void allPairsWeighted() {
      for (int v = 0; v < grid.length; v++) {
         for (int r = 0; r < grid[0].length; r++) {
         int rv = getCost(r, v);
            for (int c = 0; c < grid[0].length; c++) {
               int vc = getCost(v, c);
               if (rv + vc <= grid[r][c]) grid[r][c] = rv+vc;
            }
         }
         
      }
   }
}