/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Set;

/**
 *
 * @author coinbr
 */
public class DijkstraLink implements Comparable<DijkstraLink> {
    
    private Graph graph;
    
    private Vertex vertex;
    private DijkstraLink previous;
    private Double cost;
    private Boolean analized;

    public DijkstraLink(Graph graph, Vertex vertex, DijkstraLink previous, Double cost) {
        this.graph = graph;
        this.vertex = vertex;
        this.previous = previous;
        this.cost = cost;
        this.analized = false;
    }
    
    
    public DijkstraLink(Graph graph, Vertex vertex, DijkstraLink previous) {
        this.graph = graph;
        this.vertex = vertex;
        this.previous = previous;
        this.cost = Double.POSITIVE_INFINITY;
        this.analized = false;
    }
   

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public DijkstraLink getPrevious() {
        return previous;
    }

    public void setPrevious(DijkstraLink previous) {
        this.previous = previous;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
    
    public Boolean wasAnalized(){
        return this.analized;
    }
    
    public void setAnalized(Boolean bool){
        this.analized = bool;
        
    }
    
    public void setAnalized(){
        this.setAnalized(true);
    }

    @Override
    public int compareTo(DijkstraLink o) {
        if (this.cost == o.cost) return 0;
        if (this.cost < o.cost) return -1;
        return 1;
    }
    
    
    @Override
    public String toString(){
        return "[Link " + this.vertex.toString() + " (" + this.previous.vertex.toString() + " " + this.cost.toString() + ")]";
    }    
    
}
