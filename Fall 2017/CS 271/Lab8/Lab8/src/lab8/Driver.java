/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

/**
 *
 * @author Juice
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
        public static void main(String args[]){
        MyTreeSet<Integer> tree = new MyTreeSet<>();
        tree.insert(7);
        tree.insert(10);
        tree.insert(11);
        tree.insert(15);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);

        tree.inorder();
        tree.levelTraverse();
        tree.checkBalance();

        tree.inorder();
        tree.levelTraverse();
        tree.checkBalance();
        System.out.println(tree.first());
        System.out.println(tree.last());
        System.out.println(tree.higher(15));
        System.out.println(tree.lower(8));
        System.out.println(tree.tailSet(15).toString());
        System.out.println(tree.headSet(15).toString());
    }
    
}
