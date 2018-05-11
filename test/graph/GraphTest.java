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
public class GraphTest {
    
    static private MakePublicHelper mp;
    private Graph g;
    
    public GraphTest() {
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
        this.g = new Graph();
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
    
    @org.junit.Test
    public void testPrint() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        
        Vector<Vertex> vertexes = getSampleVertexes01();
        Vector nullEdgeVec = (Vector) mp.get(g, "nullEdgeVec");
        mp.set(g, "vertexes", vertexes);
        
        Edge edge = new Edge(g, null);
        
        
        Vector[][] matrix = new Vector[4][4];
        for(int i = 0; i < vertexes.size(); i++){
            for(int j = 0; j < vertexes.size(); j++){
                Vector vecc = new Vector();
                vecc.add(new Edge(g, null));
                matrix[i][j] = nullEdgeVec;
            }    
        }
        
        mp.set(g, "matrix", matrix);
        
        // g.print();        
        assert(g.toString().equals("	|	Abelha	Cactos	Feijao	|\n" +
                                   "---------------------------------------------\n" +
                                   "Abelha	|	-	-	-	|\n" +
                                   "Cactos	|	-	-	-	|\n" +
                                   "Feijao	|	-	-	-	|\n"));
    }
    
    @org.junit.Test
    public void testAddVertex() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        g.addVertex("Abelha");
        g.addVertex("Cactos");
        g.addVertex("Feijao");        
        
        Graph ref = new Graph();
        mp.set(ref, "vertexes", this.getSampleVertexes01());
        mp.invoke(ref, "growMatrixIfNecessary");
        mp.invoke(ref, "growMatrixIfNecessary");
        
        assert(g.toString().equals(ref.toString()));        
    }   
  /*  
    @org.junit.Test
    public void testDelVertex() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Vertex v0 = g.addVertex("Abelha");
        Vertex v1 = g.addVertex("Cactos");
        Vertex v2 = g.addVertex("Feijao");
        g.delVertex(v2);
        
        Vector<Vertex> refVertexes = new Vector<Vertex>();
        refVertexes.add(v0);
        refVertexes.add(v1);
        
        Graph ref = new Graph();
        mp.set(ref, "vertexes", this.getSampleVertexes01());
        mp.invoke(ref, "growMatrixIfNecessary");
        mp.invoke(ref, "growMatrixIfNecessary");
        
        assert(g.toString().equals(ref.toString()));        
    }
*/
    @org.junit.Test
    public void testAddEdge() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Vertex a = g.addVertex("Abelha");
        Vertex c = g.addVertex("Cactos");
        Vertex f = g.addVertex("Feijao");
        Vertex m = g.addVertex("Mada");
        
        g.addEdge(a, f, 5);
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                    "------------------------------------------------------------\n" +
                                    "Abelha	|	-	-	5	-	|\n" +
                                    "Cactos	|	-	-	-	-	|\n" +
                                    "Feijao	|	5	-	-	-	|\n" +
                                    "Mada	|	-	-	-	-	|\n"));


        g.addEdge(c, c, 7);
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                    "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	5	-	|\n" +
                                "Cactos	|	-	7	-	-	|\n" +
                                "Feijao	|	5	-	-	-	|\n" +
                                "Mada	|	-	-	-	-	|\n")); 
        
        g.addEdge(m, a, 30, true);
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                    "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	5	-	|\n" +
                                "Cactos	|	-	7	-	-	|\n" +
                                "Feijao	|	5	-	-	-	|\n" +
                                "Mada	|	_30	-	-	-	|\n")); 
        
        g.addEdge(a, m, 14, true);
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                    "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	5	_14	|\n" +
                                "Cactos	|	-	7	-	-	|\n" +
                                "Feijao	|	5	-	-	-	|\n" +
                                "Mada	|	_30	-	-	-	|\n")); 
        
        g.addEdge(a, m, 50);
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                    "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	5	_14,50	|\n" +
                                "Cactos	|	-	7	-	-	|\n" +
                                "Feijao	|	5	-	-	-	|\n" +
                                "Mada	|	_30,50	-	-	-	|\n"));         
        
    }
    
    @org.junit.Test
    public void testGetAllEdges() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Vertex a = g.addVertex("Abelha");
        Vertex c = g.addVertex("Cactos");
        Vertex f = g.addVertex("Feijao");
        Vertex m = g.addVertex("Mada");
               
        HashSet ref = new HashSet<Edge>();
        
        ref.add(g.addEdge(a, f, 5));
        ref.add(g.addEdge(c, c, 7));
        ref.add(g.addEdge(m, a, 30, true));
        ref.add(g.addEdge(a, m, 14, true));        
        ref.add(g.addEdge(a, m, 50));
        
        assert(g.getAllEdges().equals(ref));
    }    
    
    @org.junit.Test
    public void testGetEdges() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Vertex a = g.addVertex("Abelha");
        Vertex c = g.addVertex("Cactos");
        Vertex f = g.addVertex("Feijao");
        Vertex m = g.addVertex("Mada");
               
        HashSet ref = new HashSet<Edge>();
        ref.add(g.addEdge(a, f, 5));
        g.addEdge(c, c, 7);
        ref.add(g.addEdge(m, a, 30, true));
        ref.add(g.addEdge(a, m, 14, true));        
        ref.add(g.addEdge(a, m, 50));
        
        assert(g.getEdges(a).equals(ref));
    }    
    
    @org.junit.Test
    public void testAreAdjacent() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Vertex a = g.addVertex("Abelha");
        Vertex c = g.addVertex("Cactos");
        Vertex f = g.addVertex("Feijao");
        Vertex m = g.addVertex("Mada");
               
        HashSet ref = new HashSet<Edge>();
        ref.add(g.addEdge(a, f, 5));
        g.addEdge(c, c, 7);
        g.addEdge(c, f, 8, true);
        ref.add(g.addEdge(m, a, 30, true));
        ref.add(g.addEdge(a, m, 14, true));        
        ref.add(g.addEdge(a, m, 50));
        
        assert(!g.areAdjacent(a, a));
        assert(!g.areAdjacent(a, c));
        assert(g.areAdjacent(a, f));
        assert(g.areAdjacent(a, m));
          
        assert(!g.areAdjacent(c, a));
        assert(g.areAdjacent(c, c));
        assert(g.areAdjacent(c, f));
        assert(!g.areAdjacent(c, m));
          
        assert(g.areAdjacent(f, a));
        assert(g.areAdjacent(f, c));
        assert(!g.areAdjacent(f, f));
        assert(!g.areAdjacent(f, m));
          
        assert(g.areAdjacent(m, a));
        assert(!g.areAdjacent(m, c));
        assert(!g.areAdjacent(m, f));
        assert(!g.areAdjacent(m, m)); 
    }
   
    @org.junit.Test
    public void testDelVertex() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Vertex a = g.addVertex("Abelha");
        Vertex c = g.addVertex("Cactos");
        Vertex f = g.addVertex("Feijao");
        Vertex m = g.addVertex("Mada");
               
        g.addEdge(a, f, 5);
        g.addEdge(c, c, 7);
        g.addEdge(c, f, 8, true);
        g.addEdge(m, a, 30, true);
        g.addEdge(a, m, 14, true);        
        g.addEdge(a, m, 50);
        
        Graph ref = new Graph();
        Vertex aa = ref.addVertex("Abelha");
        Vertex cc = ref.addVertex("Cactos");
        Vertex mm = ref.addVertex("Mada");
               
        ref.addEdge(cc, cc, 7);
        ref.addEdge(mm, aa, 30, true);
        ref.addEdge(aa, mm, 14, true);        
        ref.addEdge(aa, mm, 50);
               
        g.delVertex(f);
        assert(g.toString().equals(ref.toString()));
       

    }
       
    @org.junit.Test
    public void testDelEdge() throws NoSuchFieldException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Vertex a = g.addVertex("Abelha");
        Vertex c = g.addVertex("Cactos");
        Vertex f = g.addVertex("Feijao");
        Vertex m = g.addVertex("Mada");
               
        Edge e0 = g.addEdge(a, f, 5);
        Edge e1 = g.addEdge(c, c, 7);
        Edge e2 = g.addEdge(c, f, 8, true);
        Edge e3 = g.addEdge(m, a, 30, true);
        Edge e4 = g.addEdge(a, m, 14, true);        
        Edge e5 = g.addEdge(a, m, 50);

        g.delEdge(e0);       
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	-	_14,50	|\n" +
                                "Cactos	|	-	7	_8	-	|\n" +
                                "Feijao	|	-	-	-	-	|\n" +
                                "Mada	|	_30,50	-	-	-	|\n"));         
        

        g.delEdge(e2);     
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	-	_14,50	|\n" +
                                "Cactos	|	-	7	-	-	|\n" +
                                "Feijao	|	-	-	-	-	|\n" +
                                "Mada	|	_30,50	-	-	-	|\n"));         
     
 
        g.delEdge(e1);      
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	-	_14,50	|\n" +
                                "Cactos	|	-	-	-	-	|\n" +
                                "Feijao	|	-	-	-	-	|\n" +
                                "Mada	|	_30,50	-	-	-	|\n"));         
          
 
        g.delEdge(e4);    
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	-	50	|\n" +
                                "Cactos	|	-	-	-	-	|\n" +
                                "Feijao	|	-	-	-	-	|\n" +
                                "Mada	|	_30,50	-	-	-	|\n"));         
                   
 
        g.delEdge(e5);  
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	-	-	|\n" +
                                "Cactos	|	-	-	-	-	|\n" +
                                "Feijao	|	-	-	-	-	|\n" +
                                "Mada	|	_30	-	-	-	|\n"));         
                        
 
        g.delEdge(e3); 
        assert(g.toString().equals( "	|	Abelha	Cactos	Feijao	Mada	|\n" +
                                "------------------------------------------------------------\n" +
                                "Abelha	|	-	-	-	-	|\n" +
                                "Cactos	|	-	-	-	-	|\n" +
                                "Feijao	|	-	-	-	-	|\n" +
                                "Mada	|	-	-	-	-	|\n"));               
    }



   

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
