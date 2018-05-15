/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author coinbr
 */
public class Vertex {
    
    private Graph graph;
    private Object element;

    public Vertex(Graph graph, Object el) {
        this.graph = graph;
        this.element = el;
    }
    
    @Override
    public String toString(){
        return this.element.toString();
    }

    public void setElement(Object element) {
        this.element = element;
    }
    
    public void changeElement(Object element) {
        this.setElement(element);
    }

    public Graph getGraph() {
        return graph;
    }

    public Object getElement() {
        return element;
    }
    
    public Integer getIndex(){
        return this.graph.getVertexIndex(this);
    }
    
    // https://en.wikipedia.org/wiki/Degree_(graph_theory)
    // In graph theory, the degree (or valency) of a vertex of a graph is the number of edges incident to the vertex, with loops counted twice.
    
    
    public Integer getDegree(){
      
        HashSet<Edge> edges = (HashSet<Edge>) this.graph.getEdges(this);
        
        Integer degree = 0;
        for (Edge edge : edges){
            degree += edge.isLoop() ? 2 : 1;
        }
        return degree;        
    }
    

    
    
}
