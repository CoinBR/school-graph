/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import graph.MakePublicHelper;
import graph.Vertex;
import graph.Graph;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author coinbr
 */
public class GraphDijkstraTest {
    
    static private MakePublicHelper mp;
    private Graph g;
    
    public GraphDijkstraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        mp = new MakePublicHelper();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // The Graph: (Image)
        // https://imgur.com/gallery/nXfQegh
        this.g = new Graph();
        Vertex a = this.g.addVertex(new String("A"));
        Vertex b = this.g.addVertex(new String("B"));
        Vertex c = this.g.addVertex(new String("C"));
        Vertex d = this.g.addVertex(new String("D"));
        Vertex e = this.g.addVertex(new String("E"));
        Vertex f = this.g.addVertex(new String("F"));
        Vertex g = this.g.addVertex(new String("G"));
        
        this.g.addEdge(a, b, 2);
        this.g.addEdge(a, c, 5);
        this.g.addEdge(a, e, 8);
        this.g.addEdge(b, d, 8);
        this.g.addEdge(c, e, 2);
        this.g.addEdge(c, g, 13);
        this.g.addEdge(d, e, 2);
        this.g.addEdge(d, f, 4);
        this.g.addEdge(f, g, 3);     
        
    }
    
    @After
    public void tearDown() {
    }
    
    private Vector<Vertex> getSampleVertexes01(){
        Vector<Vertex> vertexes = new Vector<Vertex>();
        Vertex abelhudo = new Vertex(this.g, "Abelha");
        Vertex cactos = new Vertex(this.g, "Cactos");
        Vertex feijao = new Vertex(this.g, "Feijao"); 
        
        vertexes.add(abelhudo);
        vertexes.add(cactos);
        vertexes.add(feijao);

        return vertexes;        
    }
 
}
