/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

/**
 *
 * @author Justin Espiritu
 */
public class LPHashTable<T> {
    private static final double THRESHOLD = 0.6;
    
    private class Node<T>{
        public int key;
        public T element;
    }
    
    private int totalKeys;
    private Node<T>[] array;
    
    @SuppressWarnings("unchecked")
    public LPHashTable(){
        array = new Node[7];
        totalKeys = 0;
    }
    
    public void insert(int key, T element){
        //check if load factor is too big
        if(totalKeys / array.length > THRESHOLD){
            resize();
        }
        //create new node
        Node<T> temp = new Node<T>();
        temp.key = key;
        temp.element = element;
        //find a place to put it
        //simple hash function, key % tableSize
        int slot = key % array.length;
        while(array[slot] != null){
            if(slot < array.length - 1)
                slot++;
            else
                slot = 0;
        }
        array[slot] = temp;
        totalKeys++;
    }
    
    @SuppressWarnings("unchecked")
    private void resize(){
        Node<T>[] oldArray = array;
        array = new Node[array.length*2];
        for(Node<T> n:oldArray){
            insert(n.key, n.element);
            totalKeys--;
        }
    }
    
    public T delete(int key){
        int slot = key % array.length;
        Node<T> temp = array[slot];
        if(temp==null)
            return null;

        while(temp!=null){
            if(temp.key==key){
                array[slot].element = null;
                totalKeys--;
                return temp.element;
            }
            else{
                if(slot < array.length - 1)
                    slot++;
                else
                    slot = 0;
                temp = array[slot];
            }
        }
        return null;
    }
    
    public T search(int key){
        int slot = key % array.length;
        Node<T> temp = array[slot];
        while(temp!=null){
            if(temp.key==key){
                return temp.element;
            }
            if(slot < array.length - 1)
                slot++;
            else
                slot = 0;
            temp = array[slot];
        }
        return null;
    }
    
    public boolean isEmpty(){
        if(totalKeys == 0){
            return false;
        }
        return true;
    }
    
    public void clear() {
        array = new Node[array.length];
        totalKeys = 0;
    }
    
    public void printOut(){
        //print off every element in table
        System.out.println("Capacity of the hash table: " + array.length);
        System.out.println("Number of elements: " + totalKeys);
        for(Node<T> n:array){
            if(n!=null){
                if(n.element != null)
                    System.out.println(n.key+" "+n.element);
                else
                    System.out.println("Deleted");
            }
            else{
                System.out.println("Empty");
            }
        }
    }
    
    public static void main(String args[]){
        LPHashTable<String> m = new LPHashTable<>();
        m.insert(30, "hello");
        m.insert(21, "world");
        m.insert(10, "quality");
        m.insert(8, "code");
        m.insert(14, "works");
        m.insert(3, "cat");
        m.insert(5, "the");
        m.insert(32, "other");
        m.insert(32, "test");
        m.printOut();
        
        m.delete(30);
        m.delete(14);
        m.delete(1);
        m.printOut();
        
        System.out.println(m.search(3));
        System.out.println(m.search(1));
        System.out.println(m.isEmpty());
        
        m.clear();
        System.out.println(m.isEmpty());
        m.printOut();
        
    }
}
