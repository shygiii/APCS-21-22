// Name: J1-024-21
// Date: 5/24/2022
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor> {
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d) {
      target = t;
      edgeDistance = d;
   }
   
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   
   public wVertex getTarget() {
      return target;
   }
   
   public double getEdgeDistance() {
      return edgeDistance;
   }
   
   public String toString() {
      return target.getName() + " " + edgeDistance;  
   }
   
   public int compareTo(Neighbor nb) {
      return target.compareTo(nb.getTarget());
   }
   
   public boolean equals(Neighbor n) {
      String name = target.getName();
      wVertex nb = n.getTarget();
      return name.equals(nb.getName());
   }
   
   public int hashCode() {
      return (target.getName()).hashCode();
   } 
}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijksra 7
//compareTo is using the distanceToVertex to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToVertex; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToVertex = d;
   }
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToVertex;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToVertex = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToVertex - other.distanceToVertex);
   }
   
   public wVertex getPrevious() { //Dijkstra 7
      return this.previous;
   }
   public void setPrevious(wVertex v) { //Dijkstra 7
      this.previous = v;
   } 
   
   //implement toString to match the sample output   
   public String toString() { 
      String toReturn = "";
      toReturn += vertex.getName() + " " + distanceToVertex;
      if (previous != null) toReturn += " Previous: " + previous.getName();
      else toReturn += " Previous: null";
      return toReturn;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface {
   public String getName();
   public Set<Neighbor> getNeighbors();
   
   /*  adds to the neighbors set  
       called at the beginning of the lab*/
   public void addAdjacent(wVertex v, double d); 
     
    /*returns an arraylist of PQelements that store distanceToVertex to another wVertex  */
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
   
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double d); 
   public String toString();  
 
}

class wVertex implements Comparable<wVertex>, wVertexInterface { 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */ 
   public wVertex(String n) {
      name = n;
      neighbors = new TreeSet<Neighbor>();
      alDistanceToVertex = new ArrayList<PQelement>();
   }
   
   /* 2 vertexes are equal if and only if they have the same name
      add all methods needed for a HashSet and TreeSet to function with Neighbor objects
      use only target, not distances, since a vertex can't have 2 neighbors that have the same target   
   */
   
   public String getName() {
      return name;
   }
   
   public Set<Neighbor> getNeighbors() {
      return neighbors;
   }

   public ArrayList<PQelement> getAlDistanceToVertex() {
      return alDistanceToVertex;
   }
   
   public void addAdjacent(wVertex v, double d) {
      neighbors.add(new Neighbor(v, d));
   }
        
   public PQelement getPQelement(wVertex v) {
      for (PQelement element : alDistanceToVertex) {
         if ((element.getVertex()).equals(v)) 
            return element;
      }
      return null;
   }
   
   public Double getDistanceToVertex(wVertex v) {
      return getPQelement(v).getDistanceToVertex();
   }
   
   public void setDistanceToVertex(wVertex v, double d) {
      if (getPQelement(v) == null) alDistanceToVertex.add(new PQelement(v, d));
      else getPQelement(v).setDistanceToVertex(d);
   }
   
   public int compareTo(wVertex w) {
      String nameW = w.getName();
      if (name.compareTo(nameW) < 0) 
         return -1;
      else if (name.compareTo(nameW) > 0) 
         return 1;
      return 0;
   }
   
   public String toString() { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " List: " + alDistanceToVertex; 
      return toReturn;
   }
}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface {
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities {       
   public List<String> getShortestPathTo(wVertex vSource, wVertex target);
   public void readData(String vertexNames, String edgeListData) throws FileNotFoundException;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities {
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   
   /* default constructor -- not needed!  */  
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   
   public String toString() {
      String strResult = "";
      for(String vName: vertexMap.keySet())
         strResult += vertexMap.get(vName) + "\n"; 
      return strResult;
   }
      
   public Set<wVertex> getVertices() {
      Set<wVertex> vertices = new TreeSet<wVertex>();
      for (String k : vertexMap.keySet()) vertices.add(vertexMap.get(k));
      return vertices;
   }
      
   public Map<String, wVertex> getVertexMap() {
      return vertexMap;
   }
   
   public wVertex getVertex(String vName) {
      return vertexMap.get(vName);
   }

   public void addVertex(String vName) {
      if ((vertexMap.keySet()).contains(vName)) 
         return;
      else vertexMap.put(vName, new wVertex(vName));
   }

   public void addEdge(String source, String target, double d) {
      (vertexMap.get(source)).addAdjacent(getVertex(target), d);
   }
   
   public void minimumWeightPath(String vertexName) {
      wVertex vN = vertexMap.get(vertexName);   
      for (String key : vertexMap.keySet()) {
         wVertex wV = vertexMap.get(key);
         if (key.equals(vertexName)) vN.setDistanceToVertex(vN, 0);
         else vN.setDistanceToVertex(wV, wVertex.NODISTANCE);
      }
      PriorityQueue<PQelement> pq = new PriorityQueue<PQelement>();
      pq.add(vN.getPQelement(vN));
      while (!pq.isEmpty()) {
         PQelement removedPQ = pq.remove();
         wVertex removedW = removedPQ.getVertex();
         for (Neighbor nn : removedW.getNeighbors()) {
            wVertex target = nn.getTarget();
            if(removedPQ.getDistanceToVertex() + nn.getEdgeDistance() < vN.getDistanceToVertex(target)){
               vN.setDistanceToVertex(target, removedPQ.getDistanceToVertex() + nn.getEdgeDistance());
               pq.add(vN.getPQelement(target));
               vN.getPQelement(target).setPrevious(removedW);
            }
         }
      }
   }
   
   /*  Graphs 7 has two more methods */
   //  getShortestPathTo returns the actual path from source to target.
   public List<String> getShortestPathTo(wVertex vSource, wVertex target) {
      List<String> path = new ArrayList<String>();
      while (target != null) {
         path.add(0, target.getName());
         PQelement targetPQ = vSource.getPQelement(target);
         target = targetPQ.getPrevious();
      }
      return path;
   }
     
   public void readData(String vertexNames, String edgeListData) throws FileNotFoundException {
     /* use a try-catch  */
      Scanner citiesIF = new Scanner(new File(vertexNames));
      Scanner edgesIF = new Scanner(new File(edgeListData));
      int num = citiesIF.nextInt();
      while (citiesIF.hasNext())  {
         String city = citiesIF.next();
         vertexMap.put(city, new wVertex(city));
      }
      while (edgesIF.hasNext()) {
         wVertex source = vertexMap.get(edgesIF.next());
         wVertex target = vertexMap.get(edgesIF.next());
         int distance = edgesIF.nextInt();
         source.addAdjacent(target, distance + 0.0);
      }
   }
}