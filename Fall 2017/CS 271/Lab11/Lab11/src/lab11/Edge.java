package lab11;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Justin Espiritu
 */
public class Edge implements Comparable<Edge> {
    private Vertex from;
    private Vertex to;
    private int weight;
    
    /**
     * Constructor
     * @param from
     * @param to
     * @param weight 
     */
    public Edge(Vertex from, Vertex to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    /**
     * Overload constructor for edge if weight is 0
     * @param from
     * @param to 
     */
    public Edge(Vertex from, Vertex to){
        this(from, to, 0);
    }
    
    /**
     * standard set weight
     * @param weight 
     */
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    /**
     * standard get weight
     * @return 
     */
    public int getWeight(){
        return weight;
    }
    
    /**
     * standard set from
     * @param from 
     */
    public void setFrom(Vertex from){
        this.from = from;
    }
    
    /**
     * standard get from
     * @return 
     */
    public Vertex getFrom(){
        return from;
    }
    
    /**
     * standard set to
     * @param to 
     */
    public void setTo(Vertex to){
        this.to = to;
    }
    
    /**
     * standard get to
     * @return 
     */
    public Vertex getTo(){
        return to;
    }
    
    @Override
    public int compareTo(Edge edge){
        return edge.getWeight() - weight;
    }
    /**
     * standard toString method
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("(").append(from).append(", ").append(to).append(")");
        return str.toString();
    }
}
