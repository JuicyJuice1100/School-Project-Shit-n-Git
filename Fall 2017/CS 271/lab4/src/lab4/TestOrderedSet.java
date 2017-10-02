
/**
 * Class that tests an OrderedSet implementation
 * 
 * Lab 3 - 271 F17
 */
package lab4;

public class TestOrderedSet
{
	    public static void main(String[] args){
	        OrderedSet<Integer> t1 = new LinkedOrderedSet<>();
	        OrderedSet<Integer> t2 = new LinkedOrderedSet<>();
	        t1.insert(1);
	        t1.insert(5);
	        t1.insert(2);
	        t1.insert(22);
	        t1.insert(3);
	        t1.insert(32);
	        t1.insert(32);
	        t1.remove(2);
	        System.out.println("OrderedSet 1: " + t1);
	        t2.insert(21);
	        t2.insert(6);
	        t2.insert(5);
	        t2.insert(23);
	        t2.insert(1);
	        t2.insert(4);
	        System.out.println("OrderedSet 2: " + t2);
	        OrderedSet<Integer> t3 = t1.union(t2);
	        System.out.println("Union: " + t3);
	        OrderedSet<Integer> t4 =  t1.inter(t2);
	        System.out.println("Intersection: " + t4);
	        OrderedSet<Integer> t5 =  t1.diff(t2);
	        System.out.println("Difference: OS1 - OS2 = " + t5);
	        OrderedSet<Integer> t6 =  t2.diff(t1);
	        System.out.println("Difference: OS2 - OS1 = " + t6);

	    }

}
