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
public class Driver {
     public static void main(String[] args){
        Quack<Integer> quack = new Quack<>();
        System.out.println("peekFirst: " + quack.peekFirst());
        System.out.println("peekLast: " + quack.peekLast());
        System.out.println("pop: " + quack.pop());
        System.out.println("dequeue: " + quack.dequeue());
        for(int i = 0; i < 10; i++){
            quack.insert(i);
        }
        System.out.println("peekFirst: " + quack.peekFirst());
        System.out.println("peekLast: " + quack.peekLast());
        System.out.println("pop: " + quack.pop());
        System.out.println("dequeue: " + quack.dequeue());
        System.out.println("size: " + quack.size());
        quack.flip();
        while(quack.size() > 0){
            System.out.print(quack.pop() + "->");
            System.out.print(quack.size() + "|");
        }
    }
}
