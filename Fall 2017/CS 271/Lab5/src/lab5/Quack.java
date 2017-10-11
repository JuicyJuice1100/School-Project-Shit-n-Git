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
    public void insert(T element) throws InvalidInputException{
        
        if(element == null){
            throw new InvalidInputException("Invalid Input");
        }
        
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
    public void flip() {
        if(size != 0){
            T tempData = head.data;
            Node<T> currentHead = head, currentTail = tail;
            int tailIndex = size - 1, headIndex = 0;
            while(headIndex < tailIndex){
                tempData = head.data;
                head.data = tail.data;
                tail.data = tempData;
                head = head.next;
                headIndex++;
                tail = tail.prev;
                tailIndex--;
            }
        head = currentHead;
        tail = currentTail;
        }
    }
    
    //O(n)
    public int size(){
        return size;
    }
}

class InvalidInputException extends RuntimeException{
    public InvalidInputException(){
        
    }
    public InvalidInputException(String message) {
        super(message);
    }
}

