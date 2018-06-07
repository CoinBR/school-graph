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

import graph.MakePublicHelper;
import graph.Vertex;
import graph.Graph;

import java.util.Vector;

/**
 *
 * @author coinbr
 */
public class GraphColorTest {

    static private MakePublicHelper mp;
    private Graph graph;

    // Vertexes
    private Vertex v8;
    private Vertex v7;
    private Vertex v6;
    private Vertex v5;
    private Vertex v4;
    private Vertex v3;
    private Vertex v2;
    private Vertex v1;



    public GraphColorTest() {
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
        // https://imgur.com/a/kTyVIqy
        
        Graph myGraph = new Graph();
        
        this.v8 = myGraph.addVertex(new String("8"));        
        this.v7 = myGraph.addVertex(new String("7"));
        this.v6 = myGraph.addVertex(new String("6"));
        this.v5 = myGraph.addVertex(new String("5"));
        this.v4 = myGraph.addVertex(new String("4"));
        this.v3 = myGraph.addVertex(new String("3"));
        this.v2 = myGraph.addVertex(new String("2"));
        this.v1 = myGraph.addVertex(new String("1"));
      
        myGraph.addEdge(v1, v2, 1.0, true);          
        myGraph.addEdge(v1, v4, 1.0, true);           
        myGraph.addEdge(v2, v4, 1.0, true);          
        myGraph.addEdge(v2, v5, 1.0, true);          
        myGraph.addEdge(v3, v1, 1.0, true);          
        myGraph.addEdge(v3, v6, 1.0, true);          
        myGraph.addEdge(v4, v3, 1.0, true);          
        myGraph.addEdge(v4, v5, 1.0, true);          
        myGraph.addEdge(v4, v6, 1.0, true);          
        myGraph.addEdge(v4, v7, 1.0, true);          
        myGraph.addEdge(v5, v7, 1.0, true);          
        myGraph.addEdge(v7, v6, 1.0, true); 
        
        this.graph = myGraph;
    }

    @After
    public void tearDown() {
    }
    
   

    @org.junit.Test
    public void testBFSAllColeguinha1() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(v1);
        ref.add(v2);
        ref.add(v4);
        ref.add(v5);
        ref.add(v3);
        ref.add(v6);
        ref.add(v7);
        
        String result = this.graph.BFS(v1).toString();        
        //System.out.println(result);
        //System.out.println(ref);        
        assert (result.equals(ref.toString()));
    }  
    
    @org.junit.Test
    public void testBFSAllColeguinha5() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(v5);
        ref.add(v7);
        ref.add(v6);
        
        String result = this.graph.BFS(v5).toString();        
        //System.out.println(result);
        //System.out.println(ref);        
        assert (result.equals(ref.toString()));
    }    
    
    @org.junit.Test
    public void testDFSAllColeguinha1() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(v1);
        ref.add(v2);
        ref.add(v4);
        ref.add(v3);
        ref.add(v6);
        ref.add(v5);
        ref.add(v7);
        
        String result = this.graph.DFS(v1).toString();        
        //System.out.println(result);
        //System.out.println(ref);        
        assert (result.equals(ref.toString()));
    } 
       
    @org.junit.Test
    public void testDFSAllColeguinha2() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(v2);
        ref.add(v4);
        ref.add(v3);
        ref.add(v1);
        ref.add(v6);
        ref.add(v5);
        ref.add(v7);
        
        String result = this.graph.DFS(v2).toString(); 
        // System.out.println(result);
        // System.out.println(ref);        
        assert (result.equals(ref.toString()));
    }     
    
 @org.junit.Test
    public void testDFSAllColeguinha5() {
        Vector<Vertex> ref = new Vector<Vertex>();
        ref.add(v5);
        ref.add(v7);
        ref.add(v6);
        
        String result = this.graph.DFS(v5).toString();        
        //System.out.println(result);
        //System.out.println(ref);        
        assert (result.equals(ref.toString()));
    }   

    


}
