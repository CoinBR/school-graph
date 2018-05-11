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

    
    
}
