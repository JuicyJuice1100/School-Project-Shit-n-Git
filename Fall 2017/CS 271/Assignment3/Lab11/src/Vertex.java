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
public class Vertex <T>{
    private String label;
    private T data;
    //0 = from neighbor to this, 1 = from this to neighbor
    private HashMap<Edge, Integer> edges = new HashMap<>();
    
    public Vertex(String label, T data){
        this.label = label;
        this.data = data;
    }
    
    public ArrayList<Edge> incomingNeighbors(){
        ArrayList<Edge> temp = new ArrayList<>(); 
        for(Map.Entry<Edge, Integer> entry : edges.entrySet()){
            if(entry.getValue() == 0){
                temp.add(entry.getKey());
            }
        }
        return temp;
    }
    
    public ArrayList<Edge> outgoingNeighbors(){
        ArrayList<Edge> temp = new ArrayList<>(); 
        for(Map.Entry<Edge, Integer> entry : edges.entrySet()){
            if(entry.getValue() == 1){
                temp.add(entry.getKey());
            }
        }
        return temp;
    }
    
    public void insertIncomingNeighbor(Vertex from){
        Edge edge = new Edge(from, this);
        edges.put(edge, 0);
    }
    
    public void insertOutgoingNeighbor(Vertex to){
        Edge edge = new Edge(this, to);
        edges.put(edge, 1);
    }
}
