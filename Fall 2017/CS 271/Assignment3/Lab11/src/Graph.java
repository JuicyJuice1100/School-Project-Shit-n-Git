/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author Justin Espiritu
 */
public class Graph<T> {
    private boolean isDirected;
    private int edgeCount = 0, vertexCount = 0;
    private HashMap<Vertex, ArrayList<Edge>> graph;
    
    public Graph(boolean isDirected){
        this.isDirected = isDirected;
    }
    
    public void insertVertex(String label, T data){
        Vertex<T> vertex = new Vertex<>(label, data);
        graph.put(vertex, new ArrayList<Edge>());
        vertexCount++;
    }
    
    public void insertEdge(Vertex from, Vertex to){
        Edge edge = new Edge(from, to);
        ArrayList<Edge> edges = graph.get(from);
        edges.add(edge);
        graph.put(from, edges);
        edgeCount++;
    }
    
    public void removeVertex(Vertex vertex){
        graph.remove(vertex);
        for(Map.Entry<Vertex, ArrayList<Edge>> entry : graph.entrySet()){
            for(Edge edge : entry.getValue()){
                if(edge.getFrom().equals(vertex)){
                    entry.getValue().remove(edge);
                }
                if(edge.getTo().equals(vertex)){
                    entry.getValue().remove(edge);
                }
            }
        }
        vertexCount--;
    }
    
    public void removeEdge(Edge edge){
        ArrayList<Edge> edges = graph.get(edge.getFrom());
        edges.remove(edge.getTo());
        graph.put(edge.getFrom(), edges);
        edgeCount--;
    }
    
//    public String BFS(Vertex vertex){
//        
//    }
//    
//    public String DFS(Vertex vertex){
//        
//    }
    
    
}
