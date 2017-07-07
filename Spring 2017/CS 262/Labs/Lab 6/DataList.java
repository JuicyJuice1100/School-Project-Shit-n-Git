/*
@Justin Espiritu
@Version 1
## recreate the DataSet class from previous labs using ArrayLists
*/
import java.util.Collections;

public class DataList
{
	private ArrayList<Integer> dataList;

	public DataList()
	{
		dataList = new ArrayList<>(0);
	}

	public ArrayList<Integer> getDataList()
	{
		return dataList;
	}

	//getMin = Collections.min(dataList)
	//getMax = Collections.max(dataList)
	public double getAvg()
	{
		double sum = 0, avg;
		int i;

		for(int i: dataList)
		{
			sum += i;
		}

		avg = sum/dataList.size(); 
	}

	//addDatem = dataList.add(int)
	//deleteItemAt = dataList.remove(index)
	//clear = dataList.clear()
	//insert = dataList.add(index, item)
	//shuffle = dataList.shuffle(dataList)
	public void sort()
	{
		Collections.sort(dataList);
	}

	public void shuffleData()
	{
		Collections.shuffle(dataList);
	}
	//sort = dataList.sort(dataList)

	public void display()
	{
		int count = 0;

		for(int i: dataList)
		{
			System.out.printf(" %d,", i);
			count++;
			if(count%5 == 0)
			{
				System.out.println();
			}
		}		
	}

}