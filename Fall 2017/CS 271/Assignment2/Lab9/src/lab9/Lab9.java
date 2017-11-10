/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.util.HashMap;

/**
 *
 * @author Juice
 */
public class Lab9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaxHeap<Integer> m = new MaxHeap<>();
        java.util.Random r = new java.util.Random();

        int[] data=new int[20];
        for(int i=0; i<data.length; i++)
                data[i]=r.nextInt(100);

        for(int i=0; i<data.length; i++)
                m.insert(data[i]);
        m.levelTraversal();
        String s = "";
        for(int j=0; j<data.length; j++){
                //m.levelTraversal();
                System.out.println(m.size());
                int next = m.deleteMin();
                s += next + " ";
        }
        System.out.println("\n"+s);

        System.out.print("Heap sort:");
        System.out.println(java.util.Arrays.toString(data));
    }
    
}
