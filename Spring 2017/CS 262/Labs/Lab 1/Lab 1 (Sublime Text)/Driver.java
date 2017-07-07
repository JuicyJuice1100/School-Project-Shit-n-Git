/*
@Justin Espiritu
@Version 1
2/1/2017
}George Georgiev
Lab 1 - Driver Class
*/
//import java.util.Scanner;

public class Driver
{
	public static void main(String[] args)
	{
		String str = "Wow sos yay/ lol. wow racecar!";
		NumberOfPalindromes sentence = new NumberOfPalindromes(str);

		/*Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a phrase or sentence");
		str = scan.next();
		*/

		sentence.setStr(str);
		System.out.println(sentence.getStr());
		System.out.println("Number of Palindromes = " + sentence.getNumberOfPalindromes());
	}
}