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
		DataSet negData = new DataSet();
		DataSet data = new DataSet(5);
		int newData;

		do
		{
			System.out.println("Enter new data (Enter 0 to end)");
			newData = scan.nextInt();

			//add negative integers to negative array
			if(newData < 0)
			{
				negData.addDatem(newData);				
			}

			//add positive integers to positive array
			if(newData > 0)
			{
				data.addDatem(newData);
			}
		}while(newData != 0);
		
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

		//print message if negative array is empty
		if(negData.isEmpty() == true)
		{
			System.out.println("No Negative Data Entered");
		}

		//print min, max, avg, and standard dev of negative array
		else
		{
			System.out.printf(" Min: %d\n Max: %d\n Avg: %f\n Standard Dev: %f\n", negData.getMin(), negData.getMax(), negData.getAvg(), negData.standardDev());
		}
	}
}