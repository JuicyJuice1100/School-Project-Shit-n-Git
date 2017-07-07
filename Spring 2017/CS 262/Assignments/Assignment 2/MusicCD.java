/*
@Justin Espiritu
@version 1
Create a CD object, CD is artist, date, and title
*/
import java.io.*;
import javax.swing.*;
import java.lang.String;

public class MusicCD
{
	private String artist, title;
	private int date;

	//input file when user ant to bring import a list of CDs
	//File inFile = openInputFile();
	//BufferedReader br;
	MusicCatalog catalog;

	public MusicCD(String artist, String title, int date)
	{
		this.artist = artist;
		this.title = title;
		this.date = date;
		//inFile = openInputFile();
		//br = new BufferedReader(new FileReader(inFile));
		catalog = new MusicCatalog();
	} 

	public MusicCD()
	{
		this("","", 0);
	}

	public String getArtist()
	{
		return artist;
	}

	public int getDate()
	{
		return date;
	}

	public String getTitle()
	{
		return title;
	}

	public void readFile()throws IOException
	{	
		//variables to seperate string
		String line, artist, title;
		//count keeps track of the index of the split array
		int date, count;
		//variable array for String.split()
		String[] split;
		MusicCD musicCD;
		//input file when user ant to bring import a list of CDs
		File inFile = openInputFile();
		BufferedReader br;

		br = new BufferedReader(new FileReader(inFile));

		count = catalog.getCount();

		//reads in the first line of imported file
		line = br.readLine();
		
		while(line != null)
		{
			//split the long string by artist, title, and date
			split = line.split("\" | \\d");
			System.out.println(split[0].trim());
			System.out.println(split[1].trim());
			System.out.println(split[2]);

			artist = split[0].trim();
			title = split[1].trim();
			date = Integer.parseInt(split[2]);

			//create new MusicCD object and add to the catalog
			musicCD = new MusicCD(artist, title, date);
			catalog.addCD(musicCD);

			line = br.readLine();
		}//while
		br.close();
		catalog.sortCatalog(catalog.getCatalog());
	}

	public static File openInputFile()
	{
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			return chooser.getSelectedFile();
		}
		else
		{
			return null;
		}
	}
}