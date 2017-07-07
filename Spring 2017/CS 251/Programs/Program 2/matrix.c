#include "stdio.h"
#include "stdlib.h"

/*****************************************************************************
* Description:  Program will multiply 2 matrices together if able using malloc
	 and only point arithmetic
* Author: Justin Espiritu
* Date: 4/2/2017
******************************************************************************/

void printMatrix(int**, int, int);
int** multiplyMatrices(int**, int, int, int**, int, int);
int getValueAt(int**, unsigned int, unsigned int);
void setValueAt(int**, unsigned int, unsigned int, int);
int** createMatrix(int, int);

int main()
{
	unsigned int columnMatrixA, rowMatrixA, columnMatrixB, rowMatrixB, i, j;
	int** matrixA, matrixB, matrixC;
	int input;

	printf("Enter dimentions of the first matrix\n");
	scanf("%u %u", &rowMatrixA, &columnMatrixA);

	printf("Enter dimentions of second matrix\n");
	scanf("%u %u", &rowMatrixB, &columnMatrixB);

	if(rowMatrixA != rowMatrixB)
	{
		printf("Incompatible dimensions\n");
		return 1;
	}

	matrixA = createMatrix(rowMatrixA, columnMatrixA);

	matrixB = createMatrix(rowMatrixB, columnMatrixB);

	printf("Enter the values for the first matrix\n");

	for(i = 0; i < rowMatrixA; i++)
	{
		for(j = 0; j < columnMatrixA; j++)
		{
			scanf("%d", &input);
			setValueAt(matrixA, i, j, input);
		}
	}

	printf("Enter the values for the second matrix\n");
	for(i = 0; i < rowMatrixB; i++)
	{
		for(j = 0; j < columnMatrixB; j++)
		{
			scanf("%d", &input);
			setValueAt(matrixB, i, j, input);
		}
	}

	matrixC = multiplyMatrices(matrixA, rowMatrixA, columnMatrixA, matrixB,
		 rowMatrixB, columnMatrixB);

	printf("the resulting matrix is: \n");

	printMatrix(matrixC, rowMatrixA, columnMatrixB);

	free(matrixA);
	free(matrixB);
	free(matrixC);
	return 0;
}

//print the value of given matrix
void printMatrix(int** matrix, int row, int column)
{
	int i, j;
	//loop within a loop to access each value of given matrix
	for(i = 0; i < row; i++)
	{
		for(j = 0; j < column; j++)
		{
			printf("%d ", getValueAt(matrix, i, j));
		}
		printf("\n");
	}
}

//multiply matrix A and B together to create matrix C
int** multiplyMatrices(int** matrixA, int rowA, int columnA, int** matrixB,
	int rowB, int columnB)
{
	unsigned int i, j, k ;
	int intA, intB, intC = 0;
	int** matrixC = createMatrix(rowA, columnB);

	for(i = 0; i < rowA; i++)
	{
		for(k = 0; k < columnB; k++)
		{
			for(j = 0; j < rowB; j++)
			{
				intA = getValueAt(matrixA, i, j);
				intB = getValueAt(matrixB, j, k);
				intC += intA * intB;
			}
		setValueAt(matrixC, i, k, intC);
		intC = 0;
		}
	}

	return matrixC;
}

//grab value from matrix at the given row and column
int getValueAt(int** matrix, unsigned int row, unsigned int column)
{
	return *(*(matrix + row) + column);
}

//set value in matrix at the given row and column
void setValueAt(int** matrix, unsigned int row, unsigned int column,
	 int element)
{
	*(*(matrix + row) +column) = element;
}

//create new matrix
int** createMatrix(int numRows, int numColumns)
{
	int i;
	int** theMatrix;

	//find amount bytes needed for matrix
	theMatrix = (int**)malloc(numRows * sizeof(*theMatrix));

	//allocate memory for each row
	for(i = 0; i < numRows; i++)
	{
		*(theMatrix + i) = (int*)malloc(numColumns * sizeof(int));
		if(*theMatrix + i == NULL)
		{
			printf("malloc failed\n");
			return NULL;
		}
	}

	if(theMatrix == NULL)
	{
		printf("malloc failed\n");
		return NULL;
	}

	return theMatrix;
}
