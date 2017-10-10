/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author Justin Espiritu
 */

public class Quack<T> implements Cloneable{
    @SuppressWarnings("unchecked")
    private class Node<T>{
        private T data;
        private Node<T> next;
        private Node<T> prev;
    }
    
//    public Quack clone() throws CloneNotSupportedException{
//        return (Quack)super.clone();
//    }
    
    Node<T> head;
    Node<T> tail;
    int size;
    
    @SuppressWarnings("unchecked")
    //O(1)
    public Quack(){
        head = tail = null;
        size = 0;
    }
    
    //O(1)
    public void insert(T element){
        if (head == null){
            head = new Node<>(); 
            head.data = element;
            tail = head;
        }
        else{
            Node<T> newNode = new Node<>();
            newNode.data = element;
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;  
    }
    
    //O(1)
    public T pop(){
        T poppedElement = null;
        if(size != 0){
            poppedElement = head.data;
            if(size > 1){
                head = head.next;
//                if(head == tail){
//                    tail = head;
//                }
                head.prev = null; 
            }
            else{
                head = tail = null;
            }
            size--;
        }
        return poppedElement;
    }
    
    //O(1)
    public T peekFirst(){
        T peekedElement = null;
        if(size > 0){
            peekedElement = tail.data;
        }
        return peekedElement;
    }
    
    //O(1)
    public T peekLast(){
        T peekedElement = null;
        if(size > 0){
            peekedElement = head.data;
        }
        return peekedElement;
    }
    
    //O(1)
    public T dequeue(){
        T poppedElement = null;
        if(size != 0){
            poppedElement = tail.data;
            if(size > 1){
                tail = tail.prev;
//                if(tail == head){
//                    head.next = null;
//                }
                tail.next = null;
            }
            else{
                tail = head = null;
            }
            size--;
        }
        return poppedElement;
    }
    
    //O(n)
    public void flip(){
        Quack<T> temp = new Quack();
        while(size > 0){
            temp.insert(pop());
        }
        while(temp.size() > 0){
            insert(temp.dequeue());
        }
    }
    
    //O(n)
    public int size(){
        return size;
    }
    
//    @Override
//    //O(n)
//    public String toString(){
//        try{
//            Quack<T> clone = (Quack)this.clone(); 
//        }
//        catch(CloneNotSupportedException ex){
//            
//        }
//        StringBuilder sb = new StringBuilder("[|");
//        for(int i = 0; i < size; i++){
//            sb.append(clone.pop() + "|");
//        }
//        sb.append("]");
//        return sb.toString();
//    };
}

