/******************************************************************************
 * Justin Espiritu
 * Version 1
 * Redue DataSet class using Generic Classes
 *******************************************************************************/
import java.util.NoSuchElementException;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class DataSet<E>
{
    private E[] dataSet;
    private int count;

    public DataSet()
    {
        this(100);
    }

    public DataSet(int data)
    {
        this.dataSet = new E[data];
        count = 0;
    }
    
    //return number of data in dataSet
    public int size()
    {
        return count;
    }

    public boolean add(E element)
    {
        add(element, count);
    }

    //insert new element at given index
    public boolean add(E element, int index)
    {
        boolean add = false;
        int i;

        if(element = NULL)
        {
            throw new NullPointerException();
        }
        else if(index > count || index < 0) 
        {
            throw new IndexOutOfBoundsException();
        }
        else if(contains(element))
        {
            throw new DataAlreadyExistsException();
        }
        else
        {
            if(count > dataSet.length)
            {
                reallocate();
            }

            for(i = count; i > index; i--)
            {
                dataSet[i] = dataSet[i - 1];
            }

            dataSet[index] = element;
            count++;
            add = true;
        }
        return add;
    }

    public E get(int index)
    {   
        if(index > count || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        return dataSet[index];
    }

    public E set(int index, E element)
    {
        if(element = NULL)
        {
            throw new NullPointerException();
        }
        else if(index > count || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }

        dataSet[index] = element;

        return dataSet[index];
    }

    public boolean remove(int index)
    {
        boolean remove = false;

        if(index >= count || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
        for(i = index; i < count - 1; i++)
        {
            dataSet[i] = dataSet[i + 1];
        }
        remove = true;
        dataSet[count] = null;
        count--;
        return remove;
        }
    }

    public boolean remove(E element)
    {
        //boolean remove = false;
        boolean found = false;
        int i = 0;

        if(element == NULL)
        {
            throw new NullPointerException();
        }
        else
        {
            for(i = 0; !found && i < count; i++)
            {
                if(dataSet[i] = element)
                {
                    found = true;
                }
            }

            if(!found)
            {
                throw new NoSuchDataException();  
            }

            return remove(i);
        }
    }

    public int indexOf(E element)
    {
        boolean contains = false;
        int i = 0, index;

        if(element == NULL)
        {
            throw new NullPointerException();
        }

        while(!contains)
        {
            if(dataSet[i] = element)
            {
                contains = true;
            }
            else
            {
                i++;
            }
        }

        index = i;

        if(contains = false)
        {
            throw new NoSuchElementException();
        }
        else
        {
            return index;
        }
    }

    public boolean contains(E element)
    {
        boolean contains = false;
        int i = 0;

        if(element == NULL)
        {
            throw new NullPointerException();
        }

        while(!contains)
        {
            if(dataSet[i] = element)
            {
                contains = true;
            }
            i++;
        }

        return contains;
    }

    public void clear()
    {
        DataSet();
    }

    public void isEmpty()
    {
        boolean empty = false;

        if(count = 0)
        {
            empty = true;
        }

        return empty;
    }

    private void reallocate()
    {
        int i;
        E[] dataSet = new E[this.dataSet.length + 100];

        for(i = 0; i < count; i++)
        {
            dataSet[i] = this.dataSet[i];
        }

        this.dataSet = dataSet;

    }

    @Override
    public String toString()
    {
        return dataSet.toString();
    }

    public class NoSuchDataException extends RuntimeException
    {
        public NoSuchDataException()
        {
            super();
            System.out.println("No Such Data");
        }
    }

    public class DataAlreadyExistsException extends RuntimeException
    {
        public DataAlreadyExistsException()
        {
            super();
            System.out.println("Data already exists");
        }
    }
}