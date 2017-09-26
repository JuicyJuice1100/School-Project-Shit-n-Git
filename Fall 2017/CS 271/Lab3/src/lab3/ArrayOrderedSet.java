/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author ThatA
 */
public class ArrayOrderedSet<T extends Comparable<T>> implements OrderedSet<T>{
    private T[] array;
    private int size;
    
    @SuppressWarnings("unchecked")
    public ArrayOrderedSet(){
        array = (T[])(new Comparable[5]);
        size = 0;
    }

    //O(n) including contains()    
    public boolean insert(T element){
        if(contains(element)){
            return false;
        }
        if(size == array.length){
            resize();
        }
        array[size] = element;
        size++;
        return true;
    };

    //O(n)
    public boolean contains(T  element){
        for (int i = 0; i < size; i++){
            if(array[i].equals(element)){
                return true;
            }
        }
        return false;
    };

    //O(n)
    public boolean remove(T  element){
        int index = -1;
        for(int i = 0; i < size; i++){
            if(array[i].equals(element)){
                index = i;
                break;
            }
        }
        
        if(index > -1){
            for(int i = index; i<size-1; i++){
                array[i] = array[i+1];
            }
            size--;
            return true;
        }
        return false;
    };

    //O(1)
    public T get(int index){
        if(index < 0 || index > size - 1){
            return null;
        }
        return array[index];
    };

    //O(1)
    public int size(){
        return size;
    };
    
    //O(n)
    public void resize(){
        T[] tempArray = (T[])(new Comparable[array.length*2]);
        
        for (int i = 0; i<array.length; i++){
            tempArray[i] = array[i];
        }
        
        array = tempArray;
    };

    //O(n)
    public OrderedSet<T> first(int k){
        OrderedSet<T> orderedSet = new ArrayOrderedSet();
        
        for(int i = 0; i < size && i < k; i++){
            orderedSet.insert(array[i]);
        }
        
        return orderedSet;
    };

    //O(n)
    public OrderedSet<T> last(int k){
        OrderedSet<T> orderedSet = new ArrayOrderedSet();
        
        for(int i = size - 1; i >= size - k && i >= 0; i--){
            orderedSet.insert(array[i]);
        }
        
        return orderedSet;
    };

    //O(n) including contains()
    public OrderedSet<T> union(OrderedSet<T> set){
        OrderedSet<T> orderedSet = new ArrayOrderedSet();
        for(int i = 0; i < set.size(); i++){
            if(!orderedSet.contains(set.get(i))){
                orderedSet.insert(set.get(i));
            }
        }
        for(int i = 0; i < size; i++){
            if(!orderedSet.contains(array[i])){
                orderedSet.insert(array[i]);
            }
        }
        
        return orderedSet;
    };

    //O(n) including contains()
    public OrderedSet<T> inter(OrderedSet<T> set){
        OrderedSet<T> orderedSet = new ArrayOrderedSet();
        
        for(int i = 0; i < set.size(); i++){
           if(contains(set.get(i))){
               if(!orderedSet.contains(set.get(i))){
                   orderedSet.insert(set.get(i));
               }
           }
        }
        
        return orderedSet;
    };

    //O(n)including contains()
    public OrderedSet<T> diff(OrderedSet<T> set){
        OrderedSet<T> orderedSet = new ArrayOrderedSet();
        /*
        for(int i = 0; i < set.size(); i++){
           if(!contains(set.get(i))){
               if(!orderedSet.contains(set.get(i))){
                   orderedSet.insert(set.get(i));
               }
           }
        }
        */
        
        for(int i = 0; i < size; i++){
            if(!set.contains(array[i])){
                if(!orderedSet.contains(array[i])){
                    orderedSet.insert(array[i]);
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
            sb.append(array[i] + "|");
        }
        sb.append("]");
        return sb.toString();
    };
}
