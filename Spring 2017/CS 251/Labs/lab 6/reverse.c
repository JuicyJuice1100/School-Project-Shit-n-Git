#include "stdio.h"
#include "stdlib.h"

void swap(int*, int*);
void reverse(int*, int);

int main()
{
	int howMany, i;
	int* numbers;

	printf("How many numbers are you going to input\n");
	scanf("%d", &howMany);

	numbers = (int*)malloc(howMany * sizeof(int));
	if (numbers == NULL)
		{
			printf("malloc failed\n");
			return 1;
		}

	printf("Enter numbers: \n");

	for(i = 0; i < howMany; i++)
	{
		scanf("%d", numbers + i);
	}

	printf("Here is the array: \n");
	for(i = 0; i < howMany; i++)
	{
		printf("%d ", *(numbers + i));
	}

	printf("\n");

	reverse(numbers, howMany);

	printf("Here is the array reversed: \n");

	for(i = 0; i < howMany; i++)
	{
		printf("%d ", *(numbers + i));
	}

	printf("\n");

	free(numbers);

	return 0;

}

void swap(int* ptrA, int* ptrB)
{
	int tmp;

	tmp = *ptrA;
	*ptrA = *ptrB;
	*ptrB = tmp;
}

void reverse(int* inArr, int inNum)
{
	int i;

	for(i = 0; i < inNum/2; i++)
	{
		swap(inArr + i, inArr + inNum - 1 - i);
	}
}
