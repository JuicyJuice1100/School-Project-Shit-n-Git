/*
@Justin Espiritu
@version 1
Menu-based client that will call other classes to sort and merge a catalog of CD objects
*/
import java.util.Scanner;
import java.lang.String;
import java.io.*;
import javax.swing.*;

public class MenuClient
{
	public static void main(String[] args)throws IOException
	{
		Scanner scan = new Scanner(System.in);
		MusicCD musicCD = new MusicCD();
		String artist, title, line;
		int date, menuOption;

		do
		{
			System.out.printf("Enter 1 to read file\nEnter 2 to clear collection\nEnter 3 to write output\nEnter 0 to quit\n");
			menuOption = scan.nextInt();

			switch(menuOption)
			{
				case 0:
					break;
				case 1:
					musicCD.readFile();
					break;
				case 2:
					musicCD.catalog.clearCatalog();
					break;
				case 3:
					musicCD.catalog.writeFile();
					break;
				default:
					System.out.println("Invalid Input");
			}
		}while(menuOption != 0);
	}
}