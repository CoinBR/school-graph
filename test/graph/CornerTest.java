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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author coinbr
 */
public class CornerTest {

    static private MakePublicHelper mp;
    private Graph graph;

    // Vertexes
    private Vertex a;
    private Vertex b;
    private Vertex c;
    private Vertex d;
    private Vertex e;
    private Vertex f;
    private Vertex g;

    // Routes
    private Vector<Vertex> ra;
    private Vector<Vertex> rb;
    private Vector<Vertex> rc;
    private Vector<Vertex> rd;
    private Vector<Vertex> re;
    private Vector<Vertex> rf;
    private Vector<Vertex> rg;

    // Dijkstra`s Links
    private DijkstraLink la;
    private DijkstraLink lb;
    private DijkstraLink lc;
    private DijkstraLink ld;
    private DijkstraLink le;
    private DijkstraLink lf;
    private DijkstraLink lg;
    
    // Corners
    private Corner ca;
    private Corner cb;
    private Corner cc;
    private Corner cd;
    private Corner ce;
    private Corner cf;
    private Corner cg;
    

    private Vector<Vector<Vertex>> allRoutes;
    private Vector<DijkstraLink> allLinks;

    public CornerTest() {
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
        // http://imgur.com/gallery/TPvH5iy
        this.graph = new Graph();
        this.a = this.graph.addVertex(new String("A"));
        this.b = this.graph.addVertex(new String("B"));
        this.c = this.graph.addVertex(new String("C"));
        this.d = this.graph.addVertex(new String("D"));
        this.e = this.graph.addVertex(new String("E"));
        this.f = this.graph.addVertex(new String("F"));
        this.g = this.graph.addVertex(new String("G"));

        this.graph.addEdge(a, c, 5.0);
        this.graph.addEdge(b, d, 8.0);
        this.graph.addEdge(c, e, 2.0);
        this.graph.addEdge(c, g, 13.0);
        this.graph.addEdge(d, e, 7.0);
        this.graph.addEdge(d, f, 4.0);

    }

    @After
    public void tearDown() {
    }
    
    @org.junit.Test
    public void testToString() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Corner corner = new Corner(a, true);
        mp.set(corner, "cost", 41.0);
        
        String test = corner.toString();
        String ref = "[Corner A, 41.0]";
        //System.out.println(test.toString());
        //System.out.println(ref.toString());
        assert(test.toString().equals(ref.toString()));        
    }  
    
    @org.junit.Test
    public void testProccess0() {
        Corner corner = new Corner(a);
        
        String test = corner.toString();
        String ref = "[Corner A, 84.0]";
        //System.out.println(test.toString());
        //System.out.println(ref.toString());
        assert(test.toString().equals(ref.toString()));        
    }   
    
    @org.junit.Test
    public void testProccess1() throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {        
        mp.set(b, "priority", 2.0);
        mp.set(g, "priority", 5.0);
        
        Corner corner = new Corner(a);
        
        String ref = "[Corner A, 178.0]";
        String test = corner.toString();
        //System.out.println(ref.toString());
        //System.out.println(test.toString()); 
        // for (Corner c : this.graph.getCornersRanking()) System.out.println(c);
        assert(test.toString().equals(ref.toString()));        
    }  
    
    @org.junit.Test
    public void testProccess2() throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {        
        mp.set(b, "priority", 2.0);
        mp.set(g, "priority", 5.0);
        
        String ref = "";       
        ref += "[Corner C, 128.0]\n";
        ref += "[Corner E, 132.0]\n";        
        ref += "[Corner G, 154.0]\n";
        ref += "[Corner D, 160.0]\n";
        ref += "[Corner A, 178.0]\n";
        ref += "[Corner F, 200.0]\n";
        ref += "[Corner B, 224.0]\n";
        
        String test = "";
        for (Corner c : this.graph.getCornersRanking()) test += c.toString() + "\n";
        //System.out.println(ref.toString());
        //System.out.println(test.toString());        
        assert(test.toString().equals(ref.toString()));        
    }

}
