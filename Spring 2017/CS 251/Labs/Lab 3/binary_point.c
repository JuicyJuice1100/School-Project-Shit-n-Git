#include "stdio.h"

#define MAX_BITS 32

void startBinary(double);
void endBinary(double);

void main()
{
	static double input;

	printf("Enter a positive real value: ");
	scanf("%lf", &input); /* Read a 'long' floating point,
							a.k.a., a double */

	startBinary(input);
	printf(".");
	endBinary(input);
	printf("\n");
}

void startBinary(double val)
{
	int number;
	int array[MAX_BITS];
	int x = 0, y;

	number = (int)val;

	if (number > 0)
	{
		//convert each digit to binary
		while (number > 0)
		{
			int rem = number % 2;
			array[x] = rem;

			number /= 2;
			x++;
		}

		for(y = x - 1; y >= 0; y--)
		{
			printf("%d", array[y]);
		}
	}

	else
	{
		printf("0");
	}
}

void endBinary(double val)
{
	int number, count = 0, i;
	double fraction;
	int array[MAX_BITS];


	number = (int)val;
	fraction = val - number;

	if(fraction > 0)
	{
		while(count < MAX_BITS)
		{
			fraction *= 2;
			if(fraction >= 1)
			{
				array[count] = 1;
				fraction--;
			}
			else
			{
				array[count] = 0;
			}
			count++;
		}
		for(i = 0; i < MAX_BITS; i++)
		{
			printf("%d", array[i]);
		}
	}
	else
	{
		printf("0");
	}
}