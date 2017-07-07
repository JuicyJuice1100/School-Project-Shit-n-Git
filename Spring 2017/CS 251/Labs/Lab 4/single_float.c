#include "stdio.h"
#include "string.h"
#include "math.h"

#define MAX_BITS 32
#define BASE 2
#define BIAS 127

int main()
{
   	char input_bits[MAX_BITS + 1];
   	int sinal, i, count = 0, sum = 0, expoente, absE;

  	printf("enter IEEE 754 single-precision float in binary: \n");
	scanf("%s", input_bits);

	sinal = input_bits[0] - '0';

	for(i = 8; i > 0; i--, count ++)
	{
		sum += (input_bits[i] - '0') * (int)(pow(BASE, count));
	}

	expoente = sum - BIAS;
	absE = abs(expoente);

	printf("sinal = %d\nexpoente = %d\nmantisa = ", sinal, expoente);

	for(i = 9; i <= MAX_BITS; i++)
	{
		printf("%c", input_bits[i]);
	}

	printf("\n");

	if(sinal == 1)
	{
		printf("-");
	}

	if(expoente < 0)
	{
		printf("0.");
		for(i = absE - 1; i > 0; i--)
		{
			printf("0");
		}

		printf("1");

		for(i = 9; i <= MAX_BITS; i++)
		{
			printf("%c", input_bits[i]);
		}
	}
	if(expoente > 0)
	{
		printf("1");
		for(i = 9; absE > 0; i++, absE--)
		{
			if(i <= MAX_BITS)
			{
				printf("%c", input_bits[i]);
			}
			else
			{
				printf("0");
			}
		}

		if(expoente < 23)
		{
			printf(".");
		}

		for(i = 9 + expoente; i <=  MAX_BITS; i++)
		{
			printf("%c", input_bits[i]);
		}
	}
	if(expoente == 0)
	{
		printf("0.");
		for(i = 9; i <= MAX_BITS; i++)
		{
			printf("%c", input_bits[i]);
		}
	}
	printf("\n");
	return 0;
}

