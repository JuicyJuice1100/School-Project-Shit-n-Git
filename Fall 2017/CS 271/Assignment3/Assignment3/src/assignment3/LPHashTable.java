/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Justin Espiritu
 * @param <T> 
 * @param <S> 
 */
public class LPHashTable<T,S> {
    private static final double THRESHOLD = 0.5;

    Object keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class Node<T, S>{
        public S key;
        public T element;
    }
    
    private int totalKeys;
    private Node<T, S>[] array;
    /**
     * Overload the constructor for when no size was inputed
     * @param size 
     */
    @SuppressWarnings("unchecked")
    public LPHashTable(int size){
        while(!isPrime(size)){
            size++;
        }
        array = new Node[size];
        totalKeys = 0;
    }
    /**
     * Incase nothing was put in as an argument, will create a hashtable of length 7
     */
    public LPHashTable(){
        this(7);
    }
    
    /**
     * inserts a new hashed key and element
     * @param key
     * @param element 
     */
    public void insert(S key, T element){
        //check if load factor is too big
        if((double)totalKeys /(double)array.length > THRESHOLD){
            resize();
        }
        //create new node
        Node<T, S> temp = new Node<T, S>();
        temp.key = key;
        temp.element = element;
        //find a place to put it
        //simple hash function, key % tableSize, the hexidecimal just prevent the number
        //being negative
        int slot = (key.hashCode()&0x7fffffff) % array.length;
        int hash = 1;
        while(array[slot] != null){
            int prime = nextPrime(array.length - 1);
            slot = prime - ((key.hashCode()&0x7fffffff) % prime);
            slot *= hash;
            if(slot > array.length - 1){
                resize();
                hash = 0;
            }
            hash++;
        }
        array[slot] = temp;
        totalKeys++;
    }
    
    private int nextPrime(int size){
        while(!isPrime(size)){
            size--;
        }
        return size;
    }
    
    private boolean isPrime(int num){
        for(int i=2;i<num;i++){
            if(num%i == 0){
                return false;
            }

        }   
        return true;
    }
    
    @SuppressWarnings("unchecked")
    private void resize(){
        Node<T, S>[] oldArray = array;
        array = new Node[array.length*2];
        for(Node<T, S> n:oldArray){
            if(n != null){
                insert(n.key, n.element);
                totalKeys--;
            }
        }
    }
    
    /**
     * Delete element from hashtable via key
     * @param key
     * @return 
     */
    public T delete(S key){
        int slot = (key.hashCode()&0x7fffffff) % array.length;
        Node<T, S> temp = array[slot];
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
    
    /**
     * returns the paired element from the inserted key
     * @param key
     * @return 
     */
    public T search(S key){
        int slot = (key.hashCode()&0x7fffffff) % array.length;
        Node<T, S> temp = array[slot];
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
    
    /**
     * checks to see if hashtable is empty
     * @return 
     */
    public boolean isEmpty(){
        return totalKeys == 0;
    }
    
    /**
     * clears the hash table
     */
    public void clear() {
        array = new Node[array.length];
        totalKeys = 0;
    }
    
    /**
     * prints the current hashtable's capacity, number of elements and prints
     * empty if no elements were set for that key or deleted if an element has
     * been in that register
     */
    public void printOut(){
        //print off every element in table
        System.out.println("Capacity of the hash table: " + array.length);
        System.out.println("Number of elements: " + totalKeys);
        for(Node<T, S> n:array){
            if(n!=null){
                if(n.element != null)
                    System.out.println(n.key+" "+n.element);
                else
                    System.out.println("Deleted");
            }
        }
    }
    
    /**
     * this was just a main to test this class
     * @param args 
     */
    public static void main(String args[]){
        LPHashTable<String, Integer> m = new LPHashTable<>();
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
