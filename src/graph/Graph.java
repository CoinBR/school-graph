/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import exceptions.NotImplementedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

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
    
    public Vertex changeElement(Vertex vertex, Object el){
        vertex.changeElement(el);
        return vertex;
    }
    
    public Edge changeElement(Edge edge, Object el){
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
    
    public Edge addEdge(Vertex v1, Vertex v2, Object el){
        return this.addEdge(v1, v2, el, false);
    }   
    
    public Edge addDirectedEdge(Vertex v1, Vertex v2, Object el){
        return this.addEdge(v1, v2, el, true);
    }
    
    public Edge addEdge(Vertex v1, Vertex v2, Object el, Boolean isDirected){
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
        
        /*
            
    public Collection<Edge> getEdges(Vertex vertex){
        HashSet<Edge> edges = new HashSet<Edge>();
        for (Edge edge : this.getAllEdges()){
            if (edge.getFrom() == vertex || edge.getTo() == vertex){
                edges.add(edge);
            }
        }
        
        */
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
    

     
    
    
}
