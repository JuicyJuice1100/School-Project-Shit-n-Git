/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

/**
 * this class just extends Vertex to add another method
 * @author Justin Espiritu
 */
public class Friends<T> extends Vertex<T> {
    public Friends(String label, T data){
        super(label, data);
    }
    
    /**
     * This just returns the number of neighbors this friend has which in this 
     * are the number of friends they have.
     * @return 
     */
    public int numFriends(){
        return getOutgoingNeighbors().size();
    }
}
