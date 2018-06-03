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
    private Vertex from;
    private Vertex to;
    private Edge edge;

    public DijkstraLink(Graph graph, Vertex from, Vertex to) {
        this.graph = graph;
        this.from = from;
        this.to = to;
    }

    public DijkstraLink(Graph graph, Vertex from, Vertex to, Edge edge) {
        this.graph = graph;
        this.from = from;
        this.to = to;
        this.edge = edge;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
    
    
    
    
    
}
