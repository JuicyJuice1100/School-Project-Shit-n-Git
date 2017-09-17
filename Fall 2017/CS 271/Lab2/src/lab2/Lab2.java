/*
    Guesses
 */
package lab2;

/**
 *
 * @author Justin Espiritu
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    
    private static int testSize = 10;
    public static void main(String[] args) {
        System.out.printf("%-10s %-20s %-20s \n", "n", "SelectionSort", "ArraySort");
        for(int i = 0; i < 7; i++, testSize = testSize * 10){
            Sort sort = new Sort(testSize);
        }
    }   
    
    
}
