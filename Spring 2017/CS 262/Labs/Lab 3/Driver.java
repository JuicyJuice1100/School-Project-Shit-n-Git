/*
@Justin Espiritu
@Version 1
George Georgiev
Lab 2
#Driver to test DataSet
*/

import java.util.Scanner;

public class Driver
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		DataSet data = new DataSet(5);
		int newData, menuOption, index;

		do
		{
			System.out.println("press 1 to enter new data \npress 2 to delete data at given index \npress 3 to display current data \npress 4 to clear all data \npress 5 to insert data into certain index \npress 0 to exit \n");
			menuOption = scan.nextInt();
			switch (menuOption)
			{
				case 0: 
					break;
				case 1: 
					System.out.println("Enter new data");
					newData = scan.nextInt();

					//add to array
					data.addDatem(newData);
	
					break;
				case 2:
					System.out.println("Enter Index of where you to delete data");
					index = scan.nextInt();
					data.deleteItemAt(index);
					break;
				case 3: 
					data.display();
					break;
				case 4: 
					data.clear();
					break;
				case 5: 
					//ask for new data
					System.out.println("Enter new data");
					newData = scan.nextInt();

					//ask for index to place data
					System.out.println("Enter index of where you want to place data");
					index = scan.nextInt();
					data.insert(newData, index);
					break;
				default:  
					System.out.println("Invalid input");
			}
		}while(menuOption != 0);
		
		//print message if positive array is empty
		if(data.isEmpty() == true)
		{
			System.out.println("No Positive Data Entered");
		}

		//print min, max, avg, and standard dev of positive array
		else
		{
			System.out.printf(" Min: %d\n Max: %d\n Avg: %f\n Standard Dev: %f", data.getMin(), data.getMax(), data.getAvg(), data.standardDev());
			System.out.println();
		}
	}
}