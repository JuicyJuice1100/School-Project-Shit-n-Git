/*
@Justin Espiritu
@Version 1
George Georgiev
Lab 2
#Class will alow the user to input a data set of integers and get the minimum, maximum, and average
##Data in dataSet equal to 0 means NULL
*/

public class DataSet
{
	private int[] dataSet;
	private int count;

	//constructor that specifies the maximum number of items
	public DataSet(int max)
	{
		dataSet = new int[max];
	}

	//default constor that creates a Dataset capable of handling 100 items
	public DataSet()
	{
		this(100);
	}

	public int getMin()
	{
		//set the min value to the largest possible int
		int min = Integer.MAX_VALUE;

		//go through each element in the dataSet and fine the smallest value
		for(int minElem : dataSet)
		{
			if(minElem < min && minElem != 0)
			{	
				min = minElem;
			}
		}
		return min;
	}

	public int getMax()
	{
		//set the max value to the smallest possible int 
		int max = Integer.MIN_VALUE;

		//go through each element in the dataSet and find the largest value
		for(int maxElem : dataSet)
		{
			if(maxElem > max && maxElem != 0)
			{
				max = maxElem;
			}
		}
		return max;
	}

	public double getAvg()
	{
		double sum = 0;

		//go through each element in the dataSet to get the sum of all elements
		for(count = 0; count < dataSet.length && dataSet[count] != 0; count++)
		{
			sum += dataSet[count];
		}
		//get the average by dividing sum by amount of elements not equal to 0
		return sum/(count);
	}

	public void addDatem(int item)
	{	
		//new array to make copy
		int[] newDataSet;
		int index = 0;
		
		/*
		for(count = 0; count < dataSet.length; count++)
		{
			System.out.print(dataSet[count]);
		}
		System.out.println();
		*/

		if(isFull() == true)
		{
			//create a new data set to copy old data set
			newDataSet = new int[dataSet.length + 1];
			//add new element to newDataSet asthe last element
			//newDataSet[newDataSet.length - 1] = item;
		}
		else
		{
			//create a new data set to copy old data set
			newDataSet = new int[dataSet.length];
		}

		/*
		copy old dataSet to newDataSet
		only count until the length of the old dataSet because we add new item above 
		if isFull() is true otherwise length of old and new dataSet are the same
		*/
		
		//System.out.println(newDataSet[index]);

		while(dataSet[index] != 0 && index < dataSet.length)
		{
			index++;
		}

		//System.out.println(index);

		//add new data to new array
		newDataSet[index] = item;

		//copy array elements to new array
		for(count = index - 1; count >= 0; count--)
		{
			newDataSet[count] = dataSet[count];
		}

		/*
		for(count = 0; count < dataSet.length - 1; count++)
		{
			//copy dataSet to new DataSet
			newDataSet[count] = dataSet[count];
		}
		*/
		
		/*
		for(count = 0; count < newDataSet.length; count++)
		{
			System.out.print(newDataSet[count]);
		}
		System.out.println();
		*/

		/*
		//add new element to newDataSet
		newDataSet[newDataSet.length - 1] = item;
		*/
		dataSet = newDataSet;
		
		/*
		for(count = 0; count < dataSet.length; count++)
		{
			System.out.print(dataSet[count]);
		}
		System.out.println();
		*/
	}

	public double standardDev()
	{
		double avg = getAvg(); 
		double sum = 0;
		double elemMinusMean;

		//find the summation of the dataSet
		for(count = 0; count < dataSet.length - 1; count++)
		{	
			//if element is 0 DO NOT ADD TO SUMMATION
			if(dataSet[count] == 0)
			{
				break;
			}

			elemMinusMean = Math.pow(dataSet[count] - avg, 2);
			sum += elemMinusMean;
		}

		//divide the summation by the amount of elements
		sum /= dataSet.length;

		//return the standard dev
		return Math.sqrt(sum);
	}

	public boolean isEmpty()
	{
		boolean isTrue = true;

		//go through each element in dataSet to see if array is empty
		for(count = 0; count < dataSet.length -1; count++)
		{
			if(dataSet[count] != 0)
			{
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}

	public boolean isFull()
	{
		boolean isTrue = true;

		//go through each element in dataSet to see if array is full
		for(count = 0; count < dataSet.length - 1; count++)
		{
			if(dataSet[count] == 0)
			{
				isTrue = false;
				break;
			}
		}
		return isTrue;
	}
}