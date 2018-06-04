/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author coinbr
 */
public class DijkstraLink {
    
    private Graph graph;
    
    private Vertex vertex;
    private Vertex previous;
    private Double cost;

    public DijkstraLink(Graph graph, Vertex vertex, Vertex previous, Double cost) {
        this.graph = graph;
        this.vertex = vertex;
        this.previous = previous;
        this.cost = cost;
    }
    
    
    public DijkstraLink(Graph graph, Vertex vertex, Vertex previous) {
        this.graph = graph;
        this.vertex = vertex;
        this.previous = previous;
        this.cost = Double.POSITIVE_INFINITY;
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

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    } 
    
}
