/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import exceptions.NotImplementedException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author coinbr
 */

@SuppressWarnings("unchecked")

public class Graph {
    
    private Vector[][] matrix;
    private final Vector nullEdgeVec;
    private final Vector<Vertex> vertexes = new Vector<Vertex>();    
    
        
    public Graph() {
        this.nullEdgeVec = new Vector();
        this.nullEdgeVec.add(new Edge(this, null));
        
        this.matrix = new Vector[1][1];
        this.matrix[0][0] = nullEdgeVec;
        
    }
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
     
    // By Yuuta (based on)
    // https://stackoverflow.com/questions/5061912/printing-out-a-2-d-array-in-matrix-format    
    @Override
    public String toString (){
        String rtrn = "";
        int rows = this.vertexes.size();
        int columns = rows;
        String str = "";


        rtrn += "\t|\t";
        for(int i=0;i<rows;i++){
            rtrn += this.vertexes.get(i).toString() + "\t";
        }

        rtrn += "|\n";

        for(int i=0;i<rows;i++){
            rtrn += "---------------";
        }
        rtrn += "\n";

        for(int i=0;i<rows;i++){
            str = this.vertexes.get(i).toString() + "\t|\t";

            
            for(int j=0;j<columns;j++){
                String toPrint = "";
                for (Object edgeI : this.matrix[i][j]){
                    str += toPrint;
                    Edge edge = (Edge) edgeI;
                    str += edge.getMatrixValue();
                    toPrint = ",";                    
                }
                str += "\t";
            }

            rtrn += (str + "|\n");
        }

        return rtrn;
    }
        
    public void print(){
        System.out.print(this.toString());
    }
    
    public Collection<Vertex> getVertexes(Edge edge){
        HashSet<Vertex> vertexes = new HashSet<Vertex>();
        vertexes.add(edge.getFrom());
        vertexes.add(edge.getTo());
        return vertexes;
    }
    
    public Vertex getOppositeVertex(Vertex vertex, Edge edge){
        return this.getOppositeVertex(edge, vertex);
    }
    
    public Vertex getOppositeVertex(Edge edge, Vertex vertex){
        return edge.getFrom() != vertex ? edge.getFrom() : edge.getTo();       
    }
    
    public Integer getVertexIndex(Vertex v){
        return this.vertexes.indexOf(v);
    }
    
    public Boolean areAdjacent(Vertex v1, Vertex v2){
        int i = this.vertexes.indexOf(v1);
        int j = this.vertexes.indexOf(v2);
        
        Edge edge1 = (Edge) this.matrix[i][j].firstElement();
        Edge edge2 = (Edge) this.matrix[j][i].firstElement();
        
        return !edge1.isNull() || !edge2.isNull();                        
    }
    
    public Vertex changeElement(Vertex vertex, Comparable el){
        vertex.changeElement(el);
        return vertex;
    }
    
    public Edge changeElement(Edge edge, Comparable el){
        edge.changeElement(el);
        return edge;
    }
       
    private void growMatrixIfNecessary(){
        
        if (this.matrix.length <= this.vertexes.size() - 1){
            int oldSize = this.matrix.length;
            int newSize = oldSize * 2;
            Vector[][] newMatrix = new Vector[newSize][newSize];
            
            for (int i = 0; i < oldSize; i++){
                for (int j = 0; j < oldSize; j++){
                    newMatrix[i][j] = this.matrix[i][j];
                }                
            }
            
            // Fill the rest with blank Edges
            
            
            for (int i = oldSize; i < newSize; i++){
                for (int j = 0; j < newSize; j++){
                    newMatrix[i][j] = newMatrix[j][i] = nullEdgeVec;
                }                
            }          
            
            this.matrix = newMatrix;
        }    
               
        
    }
    
    public Vertex addVertex(Object el){       
        Vertex vertex = new Vertex(this, el);
        this.vertexes.add(vertex);
        this.growMatrixIfNecessary();
        return this.vertexes.lastElement();
    }
    
    
    private void writeEdgeIntoMatrix(Edge edge, int x, int y){
       Vector baseVector;
       if (this.matrix[x][y] == this.nullEdgeVec){
           baseVector = new Vector();
       }
       else {
           baseVector = this.matrix[x][y];
       }
       
       baseVector.add(edge);
       this.matrix[x][y] = baseVector;
    }
    
    public Edge addEdge(Vertex v1, Vertex v2, Comparable el){
        return this.addEdge(v1, v2, el, false);
    }   
    
    public Edge addDirectedEdge(Vertex v1, Vertex v2, Comparable el){
        return this.addEdge(v1, v2, el, true);
    }
    
    public Edge addEdge(Vertex v1, Vertex v2, Comparable el, Boolean isDirected){
        Edge edge = new Edge(this, v1, v2, el, isDirected);
        
        int x = this.vertexes.indexOf(v1);
        int y = this.vertexes.indexOf(v2);
        
        this.writeEdgeIntoMatrix(edge, x, y);
        if (!isDirected && x != y) this.writeEdgeIntoMatrix(edge, y, x);
        
        return edge;        
    }
    
    public Object delVertex(Vertex vertex){
        int oldSize = this.vertexes.size();       
        int toDel = this.vertexes.indexOf(vertex);
        this.vertexes.remove(toDel);
        
        for (int i = toDel; i < oldSize - 1; i++){
            for(int j = 0; j < oldSize; j++){
                this.matrix[i][j] = this.matrix[i+1][j];
            }
        }  
        
        for (int i = toDel; i < oldSize - 1; i++){
            for(int j = 0; j < oldSize; j++){
                this.matrix[j][i] = this.matrix[j][i+1];
            }
        }
        
        return vertex.getElement();
    }
    
      
    private void fixEmptyEdgeVector(int x, int y){
        if (this.matrix[x][y].size() == 0){
            this.matrix[x][y] = this.nullEdgeVec;
        } 
    }
    
    public Object delEdge(Edge edge){
        Integer fromIndex = edge.getFrom().getIndex();
        Integer toIndex = edge.getTo().getIndex();
        
        Vector edgesVec1 = this.matrix[fromIndex][toIndex];
        Vector edgesVec2 = this.matrix[toIndex][fromIndex];
        
        edgesVec1.remove(edge);
        edgesVec2.remove(edge);
        
        this.fixEmptyEdgeVector(fromIndex, toIndex);
        this.fixEmptyEdgeVector(toIndex, fromIndex);        
        
        return edge.getElement();
    }
    
    
    public Collection<Edge> getSortedEdges(Vertex vertex){
        Vector<Edge> edgeVector = new Vector<Edge>(this.getEdges(vertex));
        Collections.sort((Vector) edgeVector);
        return edgeVector;        
    }    
    
    public Collection<Edge> getEdges(Vertex vertex){
        HashSet<Edge> edges = new HashSet<Edge>();
        for (int x = 0; x < this.vertexes.size(); x++){
            for (Object o : this.matrix[this.vertexes.indexOf(vertex)][x]){
                 Edge edge = (Edge) o;
                 if (edge.getMatrixValue() != "-"){
                     edges.add(edge);
                 }
            }
            for (Object o : this.matrix[x][this.vertexes.indexOf(vertex)]){
                 Edge edge = (Edge) o;
                 if (edge.getMatrixValue() != "-"){
                     edges.add(edge);
                 }
            }            
        }
        return edges;
    }
    
    
    public Integer getDegree(Vertex v){
        return v.getDegree();
    }
    
    public Collection<Vertex> getAllVertexes(){
        return this.vertexes;
    }
    

    
    public Collection<Edge> getAllEdges(){
        HashSet<Edge> edges = new HashSet<Edge>();
        for (int x = 0; x < this.vertexes.size(); x++){
            for (int y = 0; y < this.vertexes.size(); y++){
               for (Object o : this.matrix[x][y]){
                    Edge edge = (Edge) o;
                    if (edge.getMatrixValue() != "-"){
                        edges.add(edge);
                    }
               } 
            }
        }
        return edges;
    }
    
    public Boolean isDirect(Edge edge){
        return edge.isDirected();
    }
    
    
    
    private Collection<Vertex> convertLinkToRouteVector(DijkstraLink link, Vertex from){
        Vector<Vertex> vector = new Vector<Vertex>();
        return convertLinkToRouteVector(link, from, vector);
    } 
    
    private Collection<Vertex> convertLinkToRouteVector(DijkstraLink link, Vertex from, Collection<Vertex> vector){
        if (from == link.getVertex()){
            vector.add(from);
            return vector;
        }
        this.convertLinkToRouteVector(link.getPrevious(), from, vector);
        vector.add(link.getVertex());
        return vector;
    }    
    
    public Collection<Vertex> getRoute(Vertex from, Vertex to){
        Vector<DijkstraLink> links = (Vector<DijkstraLink>) this.getAllRoutesLinks(from);
        DijkstraLink link = this.getLink(links, to);
        return this.convertLinkToRouteVector(link, from);
    }
    
    public Vector<Vector<Vertex>> getAllRoutes(Vertex from){
        Vector<DijkstraLink> links = (Vector<DijkstraLink>) this.getAllRoutesLinks(from);
        Vector<Vector<Vertex>> retrn = new Vector<Vector<Vertex>>();
        
        for (DijkstraLink link : links){
            retrn.add((Vector<Vertex>) this.convertLinkToRouteVector(link, from));
        }
        return retrn;
    }
        
    public DijkstraLink getRouteLinks(Vertex from, Vertex to){
        return this.getLink(this.getAllRoutesLinks(from), to);
    }
    
    public Collection<DijkstraLink> getAllRoutesLinks(Vertex from){
        Vector<DijkstraLink> links = new Vector<DijkstraLink>();
        links.add(new DijkstraLink(this, from, null, 0.0));
        DijkstraLink rootLink = links.get(0);
        rootLink.setPrevious(rootLink);
        
        Vector<Vertex> allVertexesButFrom = (Vector) this.vertexes.clone();
        allVertexesButFrom.remove(from);
        
        for (Vertex vertex : allVertexesButFrom){
            links.add(new DijkstraLink(this, vertex, null));
        }
        
        return this.getAllRoutesLinks(links);
    }
    
    private Collection<DijkstraLink> getAllRoutesLinks(Collection<DijkstraLink> links){
        
        DijkstraLink link = this.getNextLinkToAnalize(links);
        if (link == null) return links;
        
        for (Vertex vertex : this.getAdjacentVertexes(link.getVertex())){
            double newCost = link.getCost() + this.getCostToAdjacentVertex(link.getVertex(), vertex);
            this.updateLinkIfRouteIsBetter(links, vertex, link, newCost);
        }
        
        link.setAnalized();
        Collections.sort((Vector) links);
        return this.getAllRoutesLinks(links);
    }  
    
    private DijkstraLink getNextLinkToAnalize(Collection<DijkstraLink> links){
        for (DijkstraLink link : links){
            if (!link.wasAnalized()){
                return link;
            }
        }
        return null;        
    }
    
    private DijkstraLink getLink(Collection<DijkstraLink> links, Vertex vertex){
        for (DijkstraLink link : links){
            if (link.getVertex() == vertex){
                return link;
            }
        }
        throw new IllegalArgumentException("There's no Link related to this Vertex");  
    }
    
    private void updateLinkIfRouteIsBetter(Collection<DijkstraLink> links, Vertex vertex, DijkstraLink previous, Double cost){
        for (DijkstraLink link : links){
            if (link.getVertex() == vertex){
                if (cost < link.getCost()){
                    link.setPrevious(previous);
                    link.setCost(cost);
                }
                return;    
            }
        }
    }
    
    public Collection<Vertex> getAdjacentVertexes(Vertex vertex){
        
        Vector<Vertex> brothers = new Vector<Vertex>();
        for (Edge edge : this.getSortedEdges(vertex)){
            Vertex toAdd;
            if (edge.isDirected()){
                toAdd = edge.getTo();
            }
            else {
                toAdd = edge.getFrom() == vertex ? edge.getTo() : edge.getFrom();
            }
            brothers.add(toAdd);            
        }
        return brothers;        
    }
    
    public Double getCostToAdjacentVertex(Vertex from, Vertex to){
        if (!this.areAdjacent(from, to)){
            throw new IllegalArgumentException("The Vertexex are not Adjancent");             
        }
        
        Edge selected = null;
        for (Edge edge : this.getEdges(from)){
            if (edge.getTo() == to || !edge.isDirected() && edge.getFrom() == to){
                if (selected == null || (Double) edge.getElement() < (Double) selected.getElement()){
                    selected = edge;
                }
            }
        }
        return (Double) selected.getElement();        
    }
    
    
        // https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
        public static String readFile(String path) throws IOException 
        {
          byte[] encoded = Files.readAllBytes(Paths.get("inputs/" + path));
          return new String(encoded, Charset.forName("UTF-8"));
        }

    private Collection<Vertex> DFSCalc(Vertex vertex, Vertex to, Vector<Vertex> path) {
        // Transverse all the Graph if (to == null)
        
        // Stop when reach the last element. If to is null, it will tranverse the whole graph
        if (path.isEmpty() || path.lastElement() != to){
        
            path.add(vertex);
            
            for (Vertex brother : this.getAdjacentVertexes(vertex)){
                if (!path.contains(brother)){
                    
                    this.DFSCalc(brother, to, path);
                }
            
            }
        }
        return path;
    }    
        
    
   private Collection<Vertex> BFSCalc(Vertex to, Vector<Vertex> path, Queue<Vertex> qu) {
        // Transverse all the Graph if (to == null)
              
        Vertex vertex = qu.poll();    
        if (vertex == null) return path;
        path.add(vertex);
        if (!path.isEmpty() && path.lastElement() == to) return path;      
            
        for (Vertex brother : this.getAdjacentVertexes(vertex)){
            if (!path.contains(brother)){
                qu.add(brother);
            }            
        }
        return this.BFSCalc(to, path, qu);
    }        
    
    public Collection<Vertex> DFS(Vertex vertex) {
        return this.DFS(vertex, null);
    }  
    
    public Collection<Vertex> DFS(Vertex from, Vertex to) {
        return this.DFSCalc(from, to, new Vector<Vertex>());
    }    
    
    public Collection<Vertex> BFS(Vertex vertex) {
        return this.BFS(vertex, null);
    }  
    
    public Collection<Vertex> BFS(Vertex from, Vertex to) {
        Queue<Vertex> qu = new LinkedBlockingQueue<Vertex>();
        qu.add(from);
        Vector<Vertex> path = new Vector<Vertex>();
        return this.BFSCalc(to, path, qu);
    }  
    
 
        
            
    
}
