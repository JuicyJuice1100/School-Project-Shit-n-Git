/*
@Justin Espiritu
@version 1
Create a catalog of CD's by artist, date, and title by alphabetical order of artist
*/
import java.lang.String;
import java.io.*;
import javax.swing.*;



public class MusicCatalog
{
	//declare new MusicCD Array
	private MusicCD[] catalog;
	//declare a count to keep track where we are in array
	private int count;
	
	//output file when user wants to export array to a file
	//File outFile;
	//PrintWriter pw;

	//contruct new array and set count to zero
	public MusicCatalog(int max)
	{
		catalog = new MusicCD[max];
		count = 0;
		//outFile = openOutputFile();
		//pw = new PrintWriter(new FileWriter(outFile));
	}

	//if no argument was input automatically set array size to 100
	public MusicCatalog()
	{
		this(100);
	}

	public void addCD(MusicCD item)
	{
		int i;
		MusicCD[] tmpCatalog;

		//if MusicCDatalog[] is full create a new catalog twice the size and copy orginal catalog
		if(isFull() == true)
		{
			tmpCatalog = new MusicCD[catalog.length * 2];
			for(i = 0; i < count; i++)
			{
				tmpCatalog[i] = catalog[i];
			}
			catalog = tmpCatalog;
		}
		//insert new CD into catalog
		catalog[count] = item;

		//we want to keep catalog sorted at all times
		//sortCatalog(catalog);

		count++;
	}

	//sort catalog using bubblesort
	public void sortCatalog(MusicCD[] a)
	{
		int bottom, i, j;
		MusicCD temp;
		boolean exchanged = true, equalsTo = true;
		//variables to input artist, date, and title
		String str1, str2;

		//set bottom to the last item in catalog
		bottom = count - 2;

		while(exchanged)
		{
			exchanged = false;

			for (i = 0; i <= bottom; i++)
			{
				//combining Artist, Date, Title from object as one string to make it easier to compare
				str1 = a[i].getArtist() + String.valueOf(a[i].getDate()) + a[i].getTitle();
				str2 = a[i+1].getArtist() + String.valueOf(a[i+1].getDate()) + a[i+1].getTitle();
				System.out.println(str1);
				System.out.println(str2);
				equalsTo = true;
				for(j = 0; j < str1.length() && equalsTo == true; j++)
				{
					//set equalsTo to false so that if values are different it will not go through loop again
					equalsTo = false;
					System.out.println(str1.charAt(j));
					System.out.println(str2.charAt(j));
					//if values are equal we want it to go through the loop again
					if(str1.charAt(j) == str2.charAt(j))
					{
						equalsTo = true;
					}
					else
					{
						//if values are not equal, find out which is should be first in array
						//alphabetical by Artist, then Date, then Title
						//swap objects if neccessary
						if(str1.charAt(j) > str2.charAt(j))
						{	
							temp = a[i];
							a[i] = a[i+1];
							a[i+1] = temp;
							exchanged = true;
						}
					}
				}
			}
			bottom--;
		}
		catalog = a;
	}

	public boolean isFull()
	{
		boolean full = false;

		if(count == catalog.length - 1)
		{
			full = true;
		}

		return full;
	}

	public void clearCatalog()
	{	
		//setting to 0 is the same as not having anything in the catalog
		count = 0;
	}

	//change count by one for each line imported/removed
	public void setCount(int count)
	{
		this.count = count;
	}

	public int getCount()
	{
		return count;
	}

	public MusicCD[] getCatalog()
	{
		return catalog;
	}

	public void writeFile()throws IOException
	{
		//output file when user wants to export array to a file
		File outFile;
		PrintWriter pw;
		
		outFile = openOutputFile();
		pw = new PrintWriter(new FileWriter(outFile));

		int i;
		for(i = 0; i < count; i++)
		{
			pw.printf("%s\" %s\" %d\n",catalog[i].getArtist(), catalog[i].getTitle(), catalog[i].getDate());
		}

		pw.close();
	}
	public static File openOutputFile()
	{
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			return chooser.getSelectedFile();
		}
		else return null;
	}
}