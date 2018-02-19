import java.lang.Math;
import java.math.BigInteger;
 
public class LargestPalindrome{
    public static void main(String args[]){
        BigInteger output = largestNumber(5);
        System.out.println(output);
    }
 
    public static boolean isPalindrome(BigInteger number){
        BigInteger temp = number;
        BigInteger reverse = 0;
 
        while (temp != 0){
            BigInteger remainder = temp % 10;
            reverse = reverse * 10 + remainder;
            temp = temp / 10;
        }
 
        return number == reverse;
    }
 
    public static BigInteger largestPalindrome(BigInteger i, BigInteger j, BigInteger min, BigInteger max){
        if(i.compareTo(min) < 0){
            return max;
        }
        if(isPalindrome(i.multiply(j)) && i.multiply(j).compatreTo(max) > 0){
            return i.multiply(j);
        }
        else{
            return largestPalindrome(i.subtract(1), 1, j, min, max);
        }
    }
 
    public static BigInteger largestNumber(BigInteger digits){
        if(digits <= 1){
            return 1;
        }
 
        BigInteger numberX = (BigInteger)(Math.pow(10, digits) - 1);
        BigInteger numberY = numberX;
        BigInteger min = (BigInteger)(Math.pow(10, digits - 1));
        BigInteger max = Integer.MIN_VALUE;
 
        while(numberY > min){
            max = largestPalindrome(numberX, numberY, min, max);
            numberY--;
        }
 
        return max;
    }
}