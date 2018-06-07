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
public class GraphDFS_BFSTest {

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

    private Vector<Vector<Vertex>> allRoutes;
    private Vector<DijkstraLink> allLinks;

    public GraphDFS_BFSTest() {
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
    public void testDFSAll() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(e);
        ref.add(c);
        ref.add(a);
        ref.add(g);
        ref.add(d);
        ref.add(f);
        ref.add(b);
        assert (this.graph.DFS(this.e).toString().equals(ref.toString()));
    }

    @org.junit.Test
    public void testDFS() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(c);
        ref.add(e);
        ref.add(d);
        ref.add(f);
        ref.add(b);
        // System.out.println(this.graph.DFS(this.c, this.b));
        // System.out.println(ref);
        assert (this.graph.DFS(this.c, this.b).toString().equals(ref.toString()));
    }

    @org.junit.Test
    public void testBFSAll() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(c);
        ref.add(e);
        ref.add(a);
        ref.add(g);
        ref.add(d);
        ref.add(f);
        ref.add(b);
        // System.out.println(this.graph.BFS(this.c));
        // System.out.println(ref);        
        assert (this.graph.BFS(this.c).toString().equals(ref.toString()));
    }
     

    @org.junit.Test
    public void testBFS() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(c);
        ref.add(e);
        ref.add(a);
        ref.add(g);
        ref.add(d);
        //System.out.println(this.graph.BFS(this.c, this.d));
        //System.out.println(ref);        
        assert (this.graph.BFS(this.c, this.d).toString().equals(ref.toString()));
    }
    


}
