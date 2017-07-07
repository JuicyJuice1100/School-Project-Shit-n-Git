/*
@Justin Espiritu
@Version 1
George Georgiev
Lab 2
#Class that checks if the given 2d array/table is a magic square
##magic square is when the sum of each row, each column, and each
##diagonal are equal
*/

import java.util.Arrays;

public class MagicSquare
{
	//i and x keep track of rows, j and y keep track of rows
	private static int i, j, x, y;

	public MagicSquare()
	{

	}

	//check if the square is a magic square
	public static boolean isMagic(int[][] array)
	{
		boolean isTrue = true;
		int sum = sumDiagonal(array);

		for(i = 0; i < array.length; i++)
		{
			if(sumRow(array) != sum)
			{
				isTrue = false;
				break;
			}
			for(j = 0; j < array[i].length; j++)
			{
				if(sumColumn(array, j) != sum)
				{
					isTrue = false;
					break;
				}
			}
		}
		
		return isTrue;
	}

	public static int sumRow(int[][] array)
	{
		int sumRow = 0;

		//go through the current row
		for(y = 0; y < array[i].length; y++)
		{	
			//add each element in the row
			sumRow += array[i][y];
		}

		return sumRow;
	}

	public static int sumColumn(int[][] array, int column)
	{
		int sumColumn = 0;

		//go through the current column
		for(x = 0; x < array.length; x++)
		{	
			//add each element in the column
			sumColumn += array[x][j];
		}

		return sumColumn;
	}

	public static int sumDiagonal(int[][] array)
	{
		int sumDiagonal = 0;

		//add each element in the diagonal
		for(x = 0; x < array.length; x++)
		{
			for(y = 0; y < array[x].length; y++)
			{
			//diagonal is the same if x == y
				if(x == y)
				{
					sumDiagonal += array[x][y];
				}
			}
		}

		return sumDiagonal;
	}

	public static void main(String[] args)
	{	
		String str1, str2;

		int[][] array1 = {{1, 15, 15, 4},
		{12, 6, 7, 9},
		{8, 10, 11, 5},
		{13, 3, 2, 16}};
		int[][] array2 = {{11, 10, 4, 23, 17}, 
		{18, 12, 6, 5, 24}, 
		{25, 19, 13, 7, 1}, 
		{2, 21, 20, 14, 8}, 
		{9, 3, 22, 16, 15}};

		MagicSquare square = new MagicSquare();

		//set results of MagicSquare to a string
		str1 = Boolean.toString(square.isMagic(array1));
		str2 = Boolean.toString(square.isMagic(array2));

		//print array1
		for(int[] row: array1)
		{
			System.out.println(Arrays.toString(row));
		}

		//print an extra line
		System.out.println();

		//print array2
		for(int[] row: array2)
		{
			System.out.println(Arrays.toString(row));
		}

		//print MagicSquare results and square
		System.out.printf("\n%s %s", str1, str2);
	}
}
