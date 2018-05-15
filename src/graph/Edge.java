/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Collection;

/**
 *
 * @author coinbr
 */
public class Edge {
    
    private Graph graph;
    private Vertex from;
    private Vertex to;
    private Object element;
    private Boolean _isDirected;

    
    public Edge(Graph graph, Vertex from, Vertex to, Object element, Boolean _isDirected) {
        this.graph = graph;
        this.from = from;
        this.to = to;
        this.element = element;
        this._isDirected = _isDirected;
    } 
    
    public Edge(Graph graph, Vertex from, Vertex to, Object element) {
        this.graph = graph;
        this.from = from;
        this.to = to;
        this.element = element;
        this._isDirected = false;
    }

    public Edge(Graph graph, Object element) {
        this.graph = graph;
        
        if (element != null) {
            String err = "This constructor is meant to create null/empty graphs";
            err += " only. Please, pass the graph and a explicit null as arguments";
            throw new IllegalArgumentException("This constructor is meant");
        }
        this.element = this.from = this.to = null;
        this._isDirected = null;
    }

    public Graph getGraph() {
        return graph;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setElement(Object element) {
        this.element = element;
    }
    
    public void changeElement(Object element) {
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

    public Object getElement() {
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
        return prefix + this.element.toString();
    }
    
    @Override
    public String toString(){
        String s = "<EDGE, ";
        s += this.from;
        s += this._isDirected ? " -> " : " - ";
        s += this.to + "(" + this.element + ")>";
        return s;
    }
    
    
    
}
