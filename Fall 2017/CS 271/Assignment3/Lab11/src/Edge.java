/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Justin Espiritu
 */
public class Edge {
    private Vertex from;
    private Vertex to;
    private int weight;
    
    public Edge(Vertex from, Vertex to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    public Edge(Vertex from, Vertex to){
        this(from, to, 0);
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public void setFrom(Vertex from){
        this.from = from;
    }
    
    public Vertex getFrom(){
        return from;
    }
    
    public void setTo(Vertex to){
        this.to = to;
    }
    
    public Vertex getTo(){
        return to;
    }
    
}
