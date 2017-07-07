public class DataSet<E>
{
	private E[] dataSet;
	private int count;

	public DataSet()
	{
		this.DataSet(100)
	}

	public DataSet(int a)
	{
		dataSet = new dataSet(a);
		count = 0;
	}

	public int size()
	{
		return count;
	}

	public boolean add(<E> element)
	{
		boolean add = true;

		if(count => dataSet.length)
		{
			reallocate();
			add = false;
		}
		else
		{
			dataSet(count) = element;
			count++;
		}

		return add;
	}

	public boolean add(<E> element, int index)
	{
		boolean add = true;
		int i;

		if(count => dataSet.length)
		{
			reallocate();
			add = false;
		}
		else if(index > count || index < 0)
		{
			add = false;
		}
		else
		{	
			for(i = count; i > index; i--)
			{
				dataSet[i] = dataSet[i - 1];  
			}

			dataSet[index] = element;
			count++;
		}

		return add;
	}

	public <E> get(int index)
	{
		return dataSet[index];
	}

	public <E> set(int index, <E> element)
	{

	} 

	public boolean remove(int index)
	{
		boolean remove = true;
		int i;

		if(index < 0 || index => count)
		{
			remove = false;
		}
		else
		{
			for(i = index; i < count; i++)
			{
				dataSet[index] = i + 1;
			}

			count --;
		}
	}

	public boolean remove(<E> element)
	{

	}

	public int indexOf(<E> element)
	{

	}

	public void clear()
	{
		newDataSet = new E[100];
		dataSet = newDataSet;
	}

	public boolean isEmpty()
	{

	}

	public String toString()
	{

	}

	private void reallocate()
	{
		
	}
}