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

    public Vertex(Graph graph, Comparable el) {
        this.graph = graph;
        this.element = el;
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
