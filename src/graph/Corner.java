/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Collection;
import java.util.Vector;

/**
 *
 * @author coinbr
 */
public class Corner implements Comparable<Corner> {
    
    Graph graph;
    Vertex vertex;
    Double cost;

    public void constructor(Vertex vertex, Boolean testOnly){
        this.vertex = vertex;
        graph = vertex.getGraph();
        
        if (!testOnly) this.proccess();        
    }
    
    public Corner(Vertex vertex) {
        this.constructor(vertex, false);
    }

    public Corner(Vertex vertex, Boolean testOnly) {
        this.constructor(vertex, testOnly);
    }

    private void proccess() {
        this.cost = 0.0;
        for (DijkstraLink link : this.graph.getAllRoutesLinks(vertex)){
            this.cost += link.getCost() * link.getVertex().getPriority();
        }        
    }

    public Double getCost() {
        return cost;
    }
    
    @Override
    public String toString(){
        return "[Corner " + this.vertex + ", " + this.cost.toString() + "]";
    }   

    @Override
    public int compareTo(Corner o) {
        return this.cost.compareTo(o.getCost());
    }
}
