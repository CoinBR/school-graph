/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.HashSet;

/**
 *
 * @author coinbr
 */
public class Vertex implements Comparable<Vertex> {
    
    private Graph graph;
    private Comparable element;
    private Double priority;

    public Vertex(Graph graph, Comparable el) {
        this.graph = graph;
        this.element = el;
        this.priority = 1.0;
    }
    
    public Vertex(Graph graph, Comparable label, Double priority) {
        if (priority < 1.0) throw new IllegalArgumentException("Vertex PRIORITY cannot be lesser than 1.0");
        
        this.graph = graph;
        this.element = label;
        this.priority = priority;        
    }    
    
    @Override
    public String toString(){
        return this.element.toString();
    }

    public void setElement(Comparable element) {
        this.element = element;
    }
    
    public void changeElement(Comparable element) {
        this.setElement(element);
    }

    public Graph getGraph() {
        return graph;
    }

    public Comparable getElement() {
        return element;
    }
    
    public Integer getIndex(){
        return this.graph.getVertexIndex(this);
    }

    public Double getPriority() {
        return priority;
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
    
    

    @Override
    public int compareTo(Vertex o) {       
        return this.getElement().compareTo(o.getElement());
    }    
}
