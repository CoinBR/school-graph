/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Comparator;

/**
 *
 * @author coinbr
 */
public class VertexDegreeComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o1.getDegree().compareTo(o2.getDegree());
    }
    
}
