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
public class ColorsInfo {
    
    private Graph graph;
    private Vector<Vector<Vertex>> colorGroups;

    public ColorsInfo(Graph graph) {
        this.constructor(graph, Boolean.FALSE);
    }
    

    public ColorsInfo(Graph graph, Boolean testOnly) {
        this.constructor(graph, testOnly);
    }
    
    private void constructor(Graph graph, Boolean testOnly){
        this.graph = graph;
        
        this.colorGroups = new Vector<Vector<Vertex>>();
        this.colorGroups.add(new Vector<Vertex>());

        if (!testOnly) this.proccess();
    }
    
    
    private void addVertex(Vertex vertex, Integer color){
        // Create color spot/vector/line, if it doesnt exists yet
        while (color > this.colorGroups.size() - 1){
            this.colorGroups.add(new Vector<Vertex>());
        }       
        colorGroups.get(color).add(vertex);
    }
    
    
    
    public Vector<Vector<Vertex>> getGroups(){
        return  colorGroups;
    }
    
    @Override
    public String toString(){
        // Example:   "[ 3 Colors: 0(v1 v3)  1(v2 v4 v8) 2(v6) ]";
        String s = "";
        s += "[ ";
        s += this.getNumColors();
        s += " Colors:";
        
        for (int i = 0; i < this.colorGroups.size(); i++){
            s += " ";
            s += i;
            s += "(";
            if (!this.colorGroups.get(i).isEmpty()) s += this.getGroups().get(i).get(0).getElement();
            
            for (int j = 1; j < this.colorGroups.get(i).size(); j++){
                s += " ";
                s += this.colorGroups.get(i).get(j).getElement();
            }
            s += ")";
        }       
        s += " ]";
        return s;
    }
    
    public void print(){
        System.out.println(this);
    }

    public int getNumColors() {
        return this.colorGroups.size();
    }
    
    
    
    private Boolean canVertexBePaintedWithColor(Vertex toAdd, int color){
        Boolean answer = true;
        for (Vertex toCompare : this.getGroups().get(color)){
            if (this.graph.areAdjacent(toAdd, toCompare)){
                answer = false;
                break;
            }
        }
        return answer;
    }



    private void proccess() {
        
  
        Boolean added;
        for (Vertex vertex : this.graph.getAllVertexesSortedByDegree()){
            added = false;
            for (int color = 0; color < this.colorGroups.size(); color++){
                if (this.canVertexBePaintedWithColor(vertex, color)){
                    this.addVertex(vertex, color);
                    added = true;
                    break;
                }
            }
            if (!added) this.addVertex(vertex, this.getGroups().size());
        }   
    }
}
