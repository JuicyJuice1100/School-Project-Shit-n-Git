/*
n          SelectionSort (ms)   ArraySort (ms)    
10         0                    0                    
100        0                    0                    
1000       3                    1                    
10000      79                   1                    
100000     3906                 7                    
1000000    405259               16                   
10000000   46910461             121  

Selection sort big O: O[n^2]
Array.sort big O: O[log(log(n))]

N                       Selection (s)                       ArraySort (s)
100,000,000             N^2 * .0079 * 1000(it's huge)       210
10,000,000,000          N^2 * .0079 * 1000                  360
1,000,000,000,000       N^2 * .0079 * 1000                  500
1,000,000,000,000,000   "" ""                               800

calculator can't fit that big of numbers sooo I just estimated without it after
10,000,000,000.  ArraySort I used log(log(n)) * .0079 * 1000 to convert to seconds.
 */
package lab2;

/**
 *
 * @author Justin Espiritu
 */
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sort{
    int[] numberArrayOne, numberArrayClone;
    int size;
    
    public Sort(int size){
        this.size = size;
        numberArrayOne = RandomArray();
        numberArrayClone = numberArrayOne.clone();
        long startSelectionSort = System.currentTimeMillis();
        SelectionSort(numberArrayOne);
        long endSelectionSort = System.currentTimeMillis();
        if(!Verify(numberArrayOne)){
            try {
                throw new InvalidSort();
            } catch (InvalidSort ex) {
                Logger.getLogger(Sort.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
        }
        long startArraySort = System.currentTimeMillis();
        Arrays.sort(numberArrayClone);
        long endArraySort = System.currentTimeMillis();
    System.out.printf("%-10d %-20d %-20d \n", size, endSelectionSort - startSelectionSort, endArraySort - startArraySort);
        }
    
    public static void SelectionSort(int[] arr) {
        int i, j;
        int indexSmallest;
        int temp;
        for(i = 0; i < arr.length; i++){
            indexSmallest = i;
            for(j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[indexSmallest]){
                    indexSmallest = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[indexSmallest];
            arr[indexSmallest] = temp;
        }
    }
    
    public static boolean Verify(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++){
            if (arr[i] > arr[i + 1]){
                return false;
            }
        }
        return true;
    }
    
    public int[] RandomArray () {
        Random random = new Random(542);
        int[] array = new int[size];
        for(int i=0; i<10; i++){
            array[i] = random.nextInt();
        }
        return array;
    }
    
    class InvalidSort extends Exception{
        public InvalidSort() {
        }
    }
}

