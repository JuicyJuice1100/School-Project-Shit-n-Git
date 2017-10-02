/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author ThatA
 */
public class LinkedOrderedSet <T extends Comparable<T>> implements OrderedSet<T>{
    @SuppressWarnings("unchecked")
    private class MyNode<T>{
        private T data;
        private MyNode<T> next;
    }
    
    MyNode<T> head;
    int size;
    
    @SuppressWarnings("unchecked")
    public LinkedOrderedSet(){
        head = null;
        size = 0;
    }
    
    //O(n)
    public boolean insert(T element){
        boolean isInsert = false;
        
        if (head == null){
            head = new MyNode<>();
            head.data = element;
            head.next = null;
            isInsert = true;
        }
        else{
            MyNode<T> temp = new MyNode<>();
            temp.data = element;
            temp.next = null;
            
            MyNode<T> currentNode = head;
            while(currentNode.next != null){
                currentNode = currentNode.next;
            }
            
            currentNode.next = temp;
            isInsert = false;
        }
        size++;
        
        return isInsert;
    }
    
    //O(n)
    public boolean contains(T element){
        MyNode<T> currentNode = head;
        while (currentNode != null){
            if((currentNode).data.equals(element)){
                return true;
            }
            else{
                currentNode = currentNode.next;
            }
        }
        return false;
    }
    
    //O(n)
    public boolean remove(T element){
        MyNode<T> currentNode = head;
        MyNode<T> previousNode = null;
        while(currentNode != null){
            if((currentNode.data).equals(element)){
                if(previousNode == null){
                    head = currentNode.next;
                }
                else{
                    previousNode.next = currentNode.next;
                }
                size--;
                return true;
            }
            else{
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        return false;
    }
    
    //O(n)
    public T get(int index){
        if (index < 0 || index > size - 1){
            return null;
        }
        MyNode<T> currentNode = head;
        for (int i = 0; i < index; i++){
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }
    
    //O(1)
    public int size(){
        return size;
    }
    
    //O(n)
    public OrderedSet<T> first(int k){
        OrderedSet<T> orderedSet = new LinkedOrderedSet();
        for(int i = 0; i < k && i < size; i++){
            orderedSet.insert(get(i));
        }
        return orderedSet;
    }
    
    //O(n)
    public OrderedSet<T> last(int k){
        OrderedSet<T> orderedSet = new LinkedOrderedSet();
        for(int i = size - 1; i >= size - k && i >= 0; i--){
            orderedSet.insert(get(i));
        }
        return orderedSet;
    }
    
    //O(n)
    public OrderedSet<T> union(OrderedSet<T> set){
        OrderedSet<T> orderedSet = new LinkedOrderedSet();
        for(int i = 0; i < set.size() && set != null; i++){
            if(!orderedSet.contains(set.get(i))){
                orderedSet.insert(set.get(i));
            }
        }
        for(int i = 0; i < size; i++){
            if(!orderedSet.contains(get(i))){
                orderedSet.insert(get(i));
            }
        }
        return orderedSet;
    }
    
    //O(n)
    public OrderedSet<T> inter(OrderedSet<T> set){
        OrderedSet<T> orderedSet = new LinkedOrderedSet();
        for(int i = 0; i < set.size(); i++){
           if(contains(set.get(i))){
               if(!orderedSet.contains(set.get(i))){
                   orderedSet.insert(set.get(i));
               }
           }
        }
        return orderedSet;
    }
    
    //O(n)
    public OrderedSet<T> diff(OrderedSet<T> set){
        OrderedSet<T> orderedSet = new LinkedOrderedSet();
        
        for(int i = 0; i < size; i++){
            if(!set.contains(get(i))){
                if(!orderedSet.contains(get(i))){
                    orderedSet.insert(get(i));
                }
            }
        }
        return orderedSet;
    };
    
    @Override
    //O(n)
    public String toString() {
        StringBuilder sb = new StringBuilder("[|");
        for(int i = 0; i < size; i++){
            sb.append(get(i) + "|");
        }
        sb.append("]");
        return sb.toString();
    };
    
}
