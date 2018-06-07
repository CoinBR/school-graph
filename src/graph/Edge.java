/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author coinbr
 */
public class Edge implements Comparable<Edge>{
    
    private Graph graph;
    private Vertex from;
    private Vertex to;
    private Comparable element;
    private Boolean _isDirected;

    
    public Edge(Graph graph, Vertex from, Vertex to, Comparable element, Boolean _isDirected) {
        this.graph = graph;
        this.from = from;
        this.to = to;
        this.element = element;
        this._isDirected = _isDirected;
    } 
    
    public Edge(Graph graph, Vertex from, Vertex to, Comparable element) {
        this.graph = graph;
        this.from = from;
        this.to = to;
        this.element = element;
        this._isDirected = false;
    }

    public Edge(Graph graph, Comparable element) {
        this.graph = graph;
        
        if (element != null) {
            String err = "This constructor is meant to create null/empty graphs";
            err += " only. Please, pass the graph and a explicit null as arguments";
            throw new IllegalArgumentException("This constructor is meant");
        }
        this.element = null;
        this.from = this.to = null;
        this._isDirected = null;
    }

    public Graph getGraph() {
        return graph;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setElement(Comparable element) {
        this.element = element;
    }
    
    public void changeElement(Comparable element) {
        this.setElement(element);
    }
  
    public Boolean isNull(){
        return this.from == null;
    }    
    
    public Boolean hasVertex(Vertex v){
        if (this.from == null) return false;
        return this.from == v || this.to == v;
    }

    
    public Boolean hasAnyOfTheseVertexes(Collection<Vertex> vertexes){
        for (Vertex v : vertexes){
            if (this.hasVertex(v)){
                return true;
            }
        }
        return false;
    }
    
    

    public Vertex getTo() {
        return to;
    }

    public Comparable getElement() {
        return element;
    }  

    public Boolean isDirected() {
        return _isDirected;
    }
    
    public Boolean isLoop(){
        return this.from == this.to;
    }

    /*
    @Override
    public String toString(){
        return this.element == null ? 0 : this.element.toString();
    }
    */
    
    public String getMatrixValue(){
        if (this.to == null){
            return  "-";
        } 
        String prefix = this._isDirected ? "_" : "";
        return prefix + String.valueOf(this.element);
    }
    
    @Override
    public String toString(){
        String s = "<EDGE, ";
        s += this.from;
        s += this._isDirected ? " -> " : " - ";
        s += this.to + "(" + this.element + ")>";
        return s;
    }

    @Override
    public int compareTo(Edge o) {
        Integer compareEdgeCosts = this.getElement().compareTo(o.getElement());
        
        if (compareEdgeCosts != 0) return compareEdgeCosts;
        if (this.from.compareTo(o.from) != 0) return this.from.compareTo(o.from);
        return this.to.compareTo(o.to);     

        /*

        
        if (compareEdgeCosts == 0){
            Vertex smallVertexThis = this.from.compareTo(this.to) <= 0 ? this.from : this.to;
            Vertex smallVertexO = o.from.compareTo(o.to) < 0 ? o.from : o.to;
            return smallVertexThis.compareTo(smallVertexO);            
        }
        return compareEdgeCosts;  
        */
    }
        
 
        
}
