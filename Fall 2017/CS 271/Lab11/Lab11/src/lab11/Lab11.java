/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11;

/**
 *
 * @author Justin Espiritu
 */
public class Lab11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph graph = new Graph(true);
        Vertex a = new Vertex("a", null);
        Vertex b = new Vertex("b", null);
        Vertex c = new Vertex("c", null);
        Vertex d = new Vertex("d", null);
        Vertex e = new Vertex("e", null);
        Vertex f = new Vertex("f", null);
        
        
        graph.insertVertex(a);
        graph.insertVertex(b);
        graph.insertVertex(c);
        graph.insertVertex(d);
        graph.insertVertex(e);
        graph.insertVertex(f);
        
        Edge edge0 = new Edge(a, b);
        Edge edge1 = new Edge(a, f);
        Edge edge2 = new Edge(b, c);
        Edge edge3 = new Edge(c, e);
        Edge edge4 = new Edge(d, a);
        Edge edge5 = new Edge(d, b);
        Edge edge6 = new Edge(f, e);
        
        graph.insertEdge(edge0);
        graph.insertEdge(edge1);
        graph.insertEdge(edge2);
        graph.insertEdge(edge3);
        graph.insertEdge(edge4);
        graph.insertEdge(edge5);
        graph.insertEdge(edge6);
        
        Edge edge7 = new Edge(c, d);
        graph.insertEdge(edge7);
        System.out.println(graph.toString());
        
        graph.removeEdge(edge5);
        System.out.println(graph.toString());
        
        Vertex g = new Vertex("g", null);
        graph.insertVertex(g);
        Edge edge8 = new Edge(e, g);
        Edge edge9 = new Edge(f, g);
        graph.insertEdge(edge8);
        graph.insertEdge(edge9);
        System.out.println(graph.toString());
        
        System.out.println(graph.BFS(b));
        System.out.println(graph.DFS(b));
        
        System.out.println(graph.BFS(f));
        System.out.println(graph.DFS(f));
        
        System.out.println(graph.BFS(g));
        
        graph.removeVertex(d);
        System.out.println(graph.toString());
    }
    
}
