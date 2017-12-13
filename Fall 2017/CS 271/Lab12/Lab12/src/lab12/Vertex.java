package lab12;

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
public class Vertex <T> implements Comparable<Vertex>{
    private String label;
    private T data;
    private ArrayList<Edge> incoming;
    private ArrayList<Edge> outgoing;
    
    /**
     * Constructor
     * @param label
     * @param data 
     */
    public Vertex(String label, T data){
        this.label = label;
        this.data = data;
        incoming = new ArrayList<>();
        outgoing = new ArrayList<>();
    }
    
    /**
     * get method for incomingNeighbors
     * @return 
     */
    public ArrayList<Edge> getIncomingNeighbors(){
        return incoming;
    }
    /**
     * get method for outgoingNeighbors
     * @return 
     */
    public ArrayList<Edge> getOutgoingNeighbors(){
        return outgoing;
    }
    
    /**
     * insert new incomingNeighbors method
     * @param edge 
     */
    public void insertIncomingNeighbors(Edge edge){
        incoming.add(edge);
    }
    
    /**
     * insert new outgoingNeighbors
     * @param edge 
     */
    public void insertOutgoingNeighbors(Edge edge){
        outgoing.add(edge);
    }
    
    /**
     * remove incomingNeighbors
     * @param edge 
     */
    public void removeIncomingNeighbors(Edge edge){
        incoming.remove(edge);
    }
    
    /**
     * remove outgoingNeighbors
     * @param edge 
     */
    public void removeOutgoingNeighbors(Edge edge){
        outgoing.remove(edge);
    }
    
    /**
     * standard get label
     * @return 
     */
    public String getLabel(){
        return label;
    }
    
    /**
     * standard set label
     * @param label 
     */
    public void setlabel(String label){
        this.label = label;
    }
    
    /**
     * standard get data
     * @return 
     */
    public T getData(){
        return data;
    }
    
    /**
     * standard set data
     * @param data 
     */
    public void setData(T data){
        this.data = data;
    }
    
    /**
     * standard toString method
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(label);
        return str.toString();
    }

    @Override
    public int compareTo(Vertex vertex){
        if(vertex.getLabel().equals(label)){
            return 0;
        }
        return -1;
    }
}
