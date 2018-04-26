/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import exceptions.NotImplementedException;

/**
 *
 * @author coinbr
 */
public class Graph {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public Vertex[] getVertexes(Edge edge){
        throw new NotImplementedException();
    }
    
    public Vertex getOppositeVertex(Vertex vertex, Edge edge){
        return this.getOppositeVertex(edge, vertex);
    }
    
    public Vertex getOppositeVertex(Edge edge, Vertex vertex){
        throw new NotImplementedException();    
    }
    
    public Boolean areAdjacent(Vertex v1, Vertex v2){
        throw new NotImplementedException();
    }
    
    public Vertex changeElement(Vertex vertex, Object el){
        throw new NotImplementedException();
    }
    
    public Edge changeElement(Edge edge, Object el){
        throw new NotImplementedException();
    }
    
    public Vertex addVertex(Object el){       
        throw new NotImplementedException();
    }
    
    public Edge addEdge(Vertex v1, Vertex v2, Object el){
        throw new NotImplementedException();
    }
    
    public Object delVertex(Vertex vertex){       
        throw new NotImplementedException();
    }
    
    public Object delEdge(Edge edge){
        throw new NotImplementedException();
    }
    
    public Edge[] getEdges(Vertex vertex){
        throw new NotImplementedException();
    }
    
    public Vertex[] getAllVertexes(){
        throw new NotImplementedException();
    }
    
    public Edge[] getAllEdges(){
        throw new NotImplementedException();
    }
    
    public Boolean isDirect(Edge edge){
        throw new NotImplementedException();
    }
    
    public Edge addDirectedEdge(Vertex v1, Vertex v2, Object el){
        throw new NotImplementedException();
    }   
    
}
