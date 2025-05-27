// Name: J1-024-21   
// Date: 5/15/22
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface {
   public String getName();
   public HashSet<Vertex> getAdjacencies();

   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> { //2 vertexes are equal if and only if they have the same name
   private final String name;
   private HashSet<Vertex> adjacencies;
   
  /* enter your code here  */
   public Vertex(String n) {
      name = n;
      adjacencies = new HashSet<Vertex>();
   }
  
   public String getName() {
      return name;
   }
  
   public HashSet<Vertex> getAdjacencies() {
      return adjacencies;
   }
   
   public void addAdjacent(Vertex v) {
      adjacencies.add(v);
   }
   
   public String toString() {
      String str = name + " [";
      Iterator<Vertex> iter = adjacencies.iterator();
      while (iter.hasNext()) {
         str += iter.next().getName();
         if (iter.hasNext()) str += " ";
      }
      return str + "]";
   } 
   
   public int compareTo(Vertex v) {
      if (name.compareTo(v.getName()) < 0) 
         return -1;
      else if (name.compareTo(v.getName()) > 0) 
         return 1;
      return 0;
   }
   
   public boolean equals(Vertex v) {
      return name.equals(v.getName());
   }
   
   public int hashCode() {
      return name.hashCode();
   } 
}

/*************************************************************/
interface AdjListInterface {
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 
}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS {
   public List<Vertex> depthFirstSearch(String name);
   public List<Vertex> breadthFirstSearch(String name);
   /*   extra credit methods */
   //public List<Vertex> depthFirstRecur(String name);
   //public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities {
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities {
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
   /* constructor is not needed because of the instantiation above */
   /* enter your code here  */
   
   // AdjList methods
   public Set<Vertex> getVertices() {
      Set<Vertex> v = new HashSet<Vertex>();
      for (String s : vertexMap.keySet()) v.add(vertexMap.get(s));
      return v;
   }
 
   public Vertex getVertex(String vName) {
      return vertexMap.get(vName);
   }
   
   public Map<String, Vertex> getVertexMap() {
      return vertexMap;
   }
   
   public void addVertex(String vName) {
      if ((vertexMap.keySet()).contains(vName)) 
         return;
      else vertexMap.put(vName, new Vertex(vName));
   }
   
   public void addEdge(String source, String target) {
      (vertexMap.get(source)).addAdjacent(getVertex(target));
   } 
   
   public String toString(){
      String str = "";
      Set<String> vertices = vertexMap.keySet();
      for (String v : vertices) {
         str += vertexMap.get(v).toString() + "\n";
      }
      return str.substring(0, str.length()-1);
   }
   
   // DFS BFS methods
   public List<Vertex> depthFirstSearch(String name) {
      List<Vertex> reachables = new ArrayList<Vertex>();
      Stack<Vertex> stk = new Stack<Vertex>();
      for (Vertex adj : vertexMap.get(name).getAdjacencies()) stk.push(adj);
      while (!stk.isEmpty()) {
         Vertex v = stk.pop();
         if (!reachables.contains(v)) reachables.add(v);
         for (Vertex edges : v.getAdjacencies()) {
            if (!reachables.contains(edges)) stk.push(edges);
         }
      }
      return reachables;
   }
   
   public List<Vertex> breadthFirstSearch(String name) {
      List<Vertex> reachables = new ArrayList<Vertex>();
      Queue<Vertex> q = new LinkedList<Vertex>();
      for (Vertex adj : vertexMap.get(name).getAdjacencies()) q.add(adj);
      while (!q.isEmpty()) {
         Vertex v = q.remove();
         if (!reachables.contains(v)) reachables.add(v);
         for (Vertex edges : v.getAdjacencies()) {
            if (!reachables.contains(edges)) q.add(edges);
         }
      }
      return reachables;
   }
   
   // Edgelist methods
   public void readData(String cities, String edges) throws FileNotFoundException {
      Scanner infile = new Scanner(new File(cities));
      while (infile.hasNext())  {
         String city = infile.next();
         vertexMap.put(city, new Vertex(city));
      }
      Scanner nextfile = new Scanner(new File(edges));
      while (nextfile.hasNext()) 
         addEdge(nextfile.next(), nextfile.next());
   }

   public int edgeCount() {
      int ct = 0;
      for (String s : vertexMap.keySet())
         ct += vertexMap.get(s).getAdjacencies().size();
      return ct;
   }
   
   public int vertexCount() {
      return getVertices().size();
   }
   
   public boolean isReachable(String source, String target) {
      return depthFirstSearch(source).contains(vertexMap.get(target));
   }
   
   public boolean isStronglyConnected() {
      for (String kA : vertexMap.keySet()) {
         for (String kB : vertexMap.keySet()) {
            if (!isReachable(kA, kB)) 
               return false;
         }
      }
      return true;
   } 
}