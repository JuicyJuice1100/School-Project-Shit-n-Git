/*
@Justin Espiritu
@Version 1
2/1/2017
George Georgiev
Lab 1
#Class counts the number of palindromes in a given string
*/

public class NumberOfPalindromes
{
	private String str;
	//private String[] words;

	//constructor
	public NumberOfPalindromes(String str)
	{
		this.str = str;
		//words = tokenizer(str);
	}

	public String getStr()
	{
		return str;
	}

	public void setStr(String str)
	{
		this.str = str;
	}
	
	public int getNumberOfPalindromes()
	{
		int numOfPalindromes = 0;
		
		for(String token : str.split("\\s+|\\W+"))
		{	
			token = token.toLowerCase();
			//search each string in array to see if it is a palendrome
			if(isPalindrome(token) == true)
			{
				numOfPalindromes ++;
			}
		}
		return numOfPalindromes;
	}

	private boolean isPalindrome(String str)
	{	
		//check if str is a Palindrome
		if(reverseString(str).equals(str))
		{
			return true;
		}
		return false;
	}

	//reverse each string in the word array 
	private String reverseString(String str)
	{
		String reverse = new StringBuffer(str).reverse().toString();
		return reverse;
	}
	/*
	//tokenizing the string by words
	private String[] tokenizer(String str)
	{
		return str.split("\\s");
	}
	*/
}