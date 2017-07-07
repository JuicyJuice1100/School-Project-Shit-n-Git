/*
@Justin Espiritu
@Version 1
George Georgiev
Lab 2
#Class will alow the user to input a data set of integers and get the minimum, maximum, and average
##Data in dataSet equal to 0 means NULL
*/
import java.util.Arrays;

public class DataSet
{
    private int[] dataSet;
    private int count;

    //constructor that specifies the maximum number of items
    public DataSet(int max)
    {
        dataSet = new int[max];
        count = 0 ;
    }

    //default constor that creates a Dataset capable of handling 100 items
    public DataSet()
    {
        this(100);
    }

    public int getMin()
    {
        //set the min value to the largest possible int
        int min = Integer.MAX_VALUE, i;

        //go through each element in the dataSet and fine the smallest value
        for(i = 0; i < count && dataSet[i] != 0; i++)
        {   
            if(dataSet[i] < min)
            {
                min = dataSet[i];
            }
        }
        return min;
    }

    public int getMax()
    {
        //set the max value to the smallest possible int 
        int max = Integer.MIN_VALUE, i;

        //go through each element in the dataSet and find the largest value
        for(i = 0; i < count && dataSet[i] != 0; i ++)
        {
            if(dataSet[i] > max)
            {
                max = dataSet[i];
            }
        }
        return max;
    }

    public double getAvg()
    {
        double sum = 0;
        int i;

        //go through each element in the dataSet to get the sum of all elements
        for(i = 0; i < count && dataSet[i] != 0; i++)
        {
            sum += dataSet[i];
        }
        //get the average by dividing sum by amount of elements not equal to 0
        return sum/i;
    }

    public void addDatem(int item)
    {   
        int tmpDataSet[], i;

        if(isFull() == true)
        {
            //create a new data set to copy old data set
            tmpDataSet = new int[dataSet.length * 2];
        }
        else
        {
            //create a new data set to copy old data set
            tmpDataSet = new int[dataSet.length];
        }

        //add new item to tmp dataSet
        tmpDataSet[count] = item;

        //copy array elements to new array
        for(i = 0; i < count; i++)
        {
            tmpDataSet[i] = dataSet[i];
        }

        dataSet = tmpDataSet;
        count++;

    }

    public double standardDev()
    {
        double avg = getAvg(); 
        double sum = 0;
        double elemMinusMean;
        int i;

        //find the summation of the dataSet
        for(i = 0; i < count; i++)
        {   
            elemMinusMean = Math.pow(dataSet[i] - avg, 2);
            sum += elemMinusMean;
        }

        //divide the summation by the amount of elements
        sum /= dataSet.length;

        //return the standard dev
        return Math.sqrt(sum);
       }

    public boolean isEmpty()
    {
        boolean empty = false;

        if(count == 0)
        {
            empty = true;
        }

        return empty;
    }

    public boolean isFull()
    {
        boolean full = false;

        if(count == dataSet.length - 1)
        {
            full = true;
        }
        return full;
    }

    public void deleteItemAt(int index)
    {   
        int i;

        //copy elements starting from index to tmpDataSet
        for(i = index; i < dataSet.length - 1; i++)
        {
            dataSet[i] = dataSet[i + 1];
        }

        count--;
    }


    public void display()
    {
        int i;

        for(i = 0; i < count; i++)
        {
            System.out.printf("%d, ", dataSet[i]);
        }

        System.out.println();
    }

    public void clear()
    {
        //must reset count to 0 so we know where we are in the array
        count = 0;
    }

    public void insert(int item, int index)
    {
        int i;
        int[] tmpDataSet;
        if(isFull() == true)
        {
            tmpDataSet = new int[dataSet.length + 1];
        }
        else
        {
        //create new dataSet to copy dataSet without the index
        tmpDataSet = new int[dataSet.length];
        }

        //insert new item to index
        tmpDataSet[index] = item;

        //copy elements before index to tmpDataSet
        for(i = index - 1; i >= 0; i--)
        {
            tmpDataSet[i] = dataSet[i];
        }

        //copy elements after index to tmpDataSet
        for(i = index + 1; i < dataSet.length; i++)
        {
            tmpDataSet[i] = dataSet[i - 1];
        }

        //set tmpDataSet to dataSet
        dataSet = tmpDataSet;

        //update count
        count++;
    }
}