/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

/**
 *
 * @author Justin Espiritu
 */
public class Driver {
    public static void main(String args[]){
        MyBST<Integer> tree = new MyBST<>();
        for(int i = 0; i < 5; i++){
            tree.insert(20 + i);
            tree.insert(20 + i);
            tree.insert(20 + i);
            tree.insert(1 + i);
            tree.insert(17 + i);
            tree.insert(10 + i);
            tree.insert(7 + i);
            tree.insert(30 + i);
            tree.insert(40 + i);
            tree.insert(15 + i);
            tree.insert(11 + i);
        }
        tree.inorder();
        tree.levelTraversal();
        tree.inorder();
        tree.levelTraversal();
        System.out.println(tree.contains(40));
        System.out.println(tree.contains(1));
        System.out.println(tree.countOccurances(8));
        System.out.println(tree.countOccurances(20));
        System.out.println(tree.inBetween(1, 4));
        System.out.println(tree.inBetween(10, 40));
        System.out.println(tree.removeLessThan(20));
        tree.inorder();
    }
}
