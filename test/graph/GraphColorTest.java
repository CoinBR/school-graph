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
import java.lang.reflect.InvocationTargetException;

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
    
    private ColorsInfo colorsTest; 
    private ColorsInfo colors;



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
 
        this.v8 = myGraph.addVertex(new String("v8"));        
        this.v7 = myGraph.addVertex(new String("v7"));
        this.v6 = myGraph.addVertex(new String("v6"));
        this.v5 = myGraph.addVertex(new String("v5"));
        this.v4 = myGraph.addVertex(new String("v4"));
        this.v3 = myGraph.addVertex(new String("v3"));
        this.v2 = myGraph.addVertex(new String("v2"));
        this.v1 = myGraph.addVertex(new String("v1"));
      
        myGraph.addEdge(v1, v2, 1.0);          
        myGraph.addEdge(v1, v4, 1.0);           
        myGraph.addEdge(v2, v3, 1.0);            
        myGraph.addEdge(v2, v5, 1.0);          
        myGraph.addEdge(v3, v4, 1.0);          
        myGraph.addEdge(v3, v6, 1.0);          
        myGraph.addEdge(v4, v7, 1.0);          
        myGraph.addEdge(v5, v6, 1.0);          
        myGraph.addEdge(v5, v8, 1.0);          
        myGraph.addEdge(v6, v7, 1.0);          
        myGraph.addEdge(v6, v8, 1.0);          
        myGraph.addEdge(v7, v8, 1.0);
        
        this.graph = myGraph;
        this.colorsTest = new ColorsInfo(new Graph(), true);
        this.colors = new ColorsInfo(myGraph, false);
    }

    @After
    public void tearDown() {
    } 
    
    @org.junit.Test
    public void testAddVertex() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InvocationTargetException, NoSuchMethodException {
        Vector<Vector<Vertex>> ref = new Vector<Vector<Vertex>>();
        
        ref.add(new Vector<Vertex>());
        ref.get(0).add(v1);
        mp.invoke(this.colorsTest, "addVertex", v1, 0);
        assert(this.colorsTest.getGroups().toString().equals(ref.toString()));
        
        
        ref.add(new Vector<Vertex>());
        ref.add(new Vector<Vertex>());
        ref.get(2).add(v6);
        mp.invoke(this.colorsTest, "addVertex", v6, 2);
        assert(this.colorsTest.getGroups().toString().equals(ref.toString()));        
        
     
        ref.get(0).add(v3);
        mp.invoke(this.colorsTest, "addVertex", v3, 0);
        assert(this.colorsTest.getGroups().toString().equals(ref.toString()));        
    }   
    
    @org.junit.Test
    public void testGetNumColors() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InvocationTargetException, NoSuchMethodException {
        Vector<Vector<Vertex>> ref = new Vector<Vector<Vertex>>();
        
        assert(this.colorsTest.getNumColors() == 1);  
        mp.invoke(this.colorsTest, "addVertex", v1, 0);
        mp.invoke(this.colorsTest, "addVertex", v3, 0);
        
        
        assert(this.colorsTest.getNumColors() == 1);
        mp.invoke(this.colorsTest, "addVertex", v2, 1);
        mp.invoke(this.colorsTest, "addVertex", v4, 1);
        mp.invoke(this.colorsTest, "addVertex", v8, 1);
        
        assert(this.colorsTest.getNumColors() == 2);
        mp.invoke(this.colorsTest, "addVertex", v6, 2);
        assert(this.colorsTest.getNumColors() == 3);        
    }  
    
    @org.junit.Test
    public void testToString() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InvocationTargetException, NoSuchMethodException {
       
  
        mp.invoke(this.colorsTest, "addVertex", v1, 0);
        mp.invoke(this.colorsTest, "addVertex", v3, 0);
        mp.invoke(this.colorsTest, "addVertex", v2, 1);
        mp.invoke(this.colorsTest, "addVertex", v4, 1);
        mp.invoke(this.colorsTest, "addVertex", v8, 1);
        mp.invoke(this.colorsTest, "addVertex", v6, 2);       
        
        String ref = "[ 3 Colors: 0(v1 v3) 1(v2 v4 v8) 2(v6) ]";
        String test = this.colorsTest.toString();
        
        //System.out.println(ref);
        //System.out.println(test);
        assert(ref.equals(test));    
    }    
    
    @org.junit.Test
    public void testProcess() {
       String ref = "[ 3 Colors: 0(v1 v3 v5 v7) 1(v2 v4 v8) 2(v6) ]";
       String test = this.colors.toString();
       //System.out.println(ref);
       //System.out.println(test);       
       assert(ref.equals(test));
    }   

}
