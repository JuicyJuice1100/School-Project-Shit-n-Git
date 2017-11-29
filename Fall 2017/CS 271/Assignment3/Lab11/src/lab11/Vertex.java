package lab11;

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
    private ArrayList<Edge> incoming;
    private ArrayList<Edge> outgoing;
    
    public Vertex(String label, T data){
        this.label = label;
        this.data = data;
        incoming = new ArrayList<>();
        outgoing = new ArrayList<>();
    }
    
    public ArrayList<Edge> incomingNeighbors(){
        return incoming;
    }
    
    public ArrayList<Edge> outgoingNeighbors(){
        return outgoing;
    }
    
    public void insertIncomingNeighbors(Edge edge){
        incoming.add(edge);
    }
    
    public void insertOutgoingNeighbors(Edge edge){
        outgoing.add(edge);
    }
    
    public void removeIncomingNeighbors(Edge edge){
        incoming.remove(edge);
    }
    
    public void removeOutgoingNeighbors(Edge edge){
        outgoing.remove(edge);
    }
    
    public String getLabel(){
        return label;
    }
    
    public void setlabel(String label){
        this.label = label;
    }
    
    public T getData(){
        return data;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(label);
        return str.toString();
    }
}
