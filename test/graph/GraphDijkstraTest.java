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
        this.graph = new Graph();
        this.a = this.graph.addVertex(new String("A"));
        this.b = this.graph.addVertex(new String("B"));
        this.c = this.graph.addVertex(new String("C"));
        this.d = this.graph.addVertex(new String("D"));
        this.e = this.graph.addVertex(new String("E"));
        this.f = this.graph.addVertex(new String("F"));
        this.g = this.graph.addVertex(new String("G"));
        
	this.graph.addEdge(a, b, 2);
        this.graph.addEdge(a, c, 5);
        this.graph.addEdge(a, e, 8);
        this.graph.addEdge(b, d, 8);
        this.graph.addEdge(c, e, 2);
        this.graph.addEdge(c, g, 13);
        this.graph.addEdge(d, e, 2);
        this.graph.addEdge(d, f, 4);
        this.graph.addEdge(f, g , 3);
        
        
        this.la = new DijkstraLink(graph, a, a, 0.0);
        this.lb = new DijkstraLink(graph, b, a, 2.0);
        this.lc = new DijkstraLink(graph, c, a, 5.0);
        this.le = new DijkstraLink(graph, e, c, 7.0);
        this.ld = new DijkstraLink(graph, d, e, 9.0);
        this.lf = new DijkstraLink(graph, f, d, 13.0);
        this.lg = new DijkstraLink(graph, g, f, 16.0);
        
        this.allLinks = new Vector<DijkstraLink>();
        this.allLinks.add(this.la);
        this.allLinks.add(this.lb);
        this.allLinks.add(this.lc);
        this.allLinks.add(this.le);
        this.allLinks.add(this.ld);
        this.allLinks.add(this.lf);
        this.allLinks.add(this.lg);
        
        
        this.ra = new Vector<Vertex>();
        this.rb = new Vector<Vertex>();
        this.rc = new Vector<Vertex>();
        this.rd = new Vector<Vertex>();
        this.re = new Vector<Vertex>();
        this.rf = new Vector<Vertex>();
        this.rg = new Vector<Vertex>();
        
        this.ra.add(a);
        
        this.rb.add(a);
        this.rb.add(b);
        
        this.rc.add(a);
        this.rc.add(c);
        
        this.re.add(a);
        this.re.add(c);
        this.re.add(e);
        
        this.rd.add(a);
        this.rd.add(c);
        this.rd.add(e);
        this.rd.add(d);
        
        this.rf.add(a);
        this.rf.add(c);
        this.rf.add(e);
        this.rf.add(d);
        this.rf.add(f);
        
        this.rg.add(a);
        this.rg.add(c);
        this.rg.add(e);
        this.rg.add(d);
        this.rg.add(f);
        this.rg.add(g);
        
        this.allRoutes = new Vector<Vector<Vertex>>();
        this.allRoutes.add(this.ra);
        this.allRoutes.add(this.rb);
        this.allRoutes.add(this.rc);
        this.allRoutes.add(this.re);
        this.allRoutes.add(this.rd);
        this.allRoutes.add(this.rf);
        this.allRoutes.add(this.rg); 
    }
    
    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testGetAllRoutes(){
        assert(this.allRoutes.equals(this.graph.getAllRoutes(this.a)));  
    }
    
    @org.junit.Test
    public void testGetAllRoutesLinks(){
        assert(this.allLinks.equals(this.graph.getAllRoutesLinks(this.a)));  
    }
    
    
    @org.junit.Test
    public void testGetRoute(){
        assert(this.ra.equals(this.graph.getRoute(this.a, this.a)));
        assert(this.rb.equals(this.graph.getRoute(this.a, this.b)));
        assert(this.rc.equals(this.graph.getRoute(this.a, this.c)));
        assert(this.rd.equals(this.graph.getRoute(this.a, this.d)));
        assert(this.re.equals(this.graph.getRoute(this.a, this.e)));
        assert(this.rf.equals(this.graph.getRoute(this.a, this.f)));
        assert(this.rg.equals(this.graph.getRoute(this.a, this.g)));  
    }     
    
    @org.junit.Test
    public void testGetRoutesLinks(){     
        assert(this.la.equals(this.graph.getRouteLinks(this.a, this.a)));
        assert(this.lb.equals(this.graph.getRouteLinks(this.a, this.b)));
        assert(this.lc.equals(this.graph.getRouteLinks(this.a, this.c)));
        assert(this.ld.equals(this.graph.getRouteLinks(this.a, this.d)));
        assert(this.le.equals(this.graph.getRouteLinks(this.a, this.e)));
        assert(this.lf.equals(this.graph.getRouteLinks(this.a, this.f)));        
        assert(this.lg.equals(this.graph.getRouteLinks(this.a, this.g)));
    }     
    
    
    
}
