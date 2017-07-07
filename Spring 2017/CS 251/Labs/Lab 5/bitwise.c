#include "stdio.h"

unsigned int setBit(unsigned int, unsigned int);
unsigned int toggleBit(unsigned int, unsigned int);
unsigned int unsetBit(unsigned int, unsigned int);

int main()
{
	unsigned int num, bit;

	printf("Enter a positive integer and bit: ");
	scanf("%u%u", &num, &bit);

	printf("The result of setting bit %u is %u\n", bit, setBit(num, bit));
	printf("The result of toggling bit %u is %u\n", bit, toggleBit(num, bit));
	printf("The result of unsetting bit %u is %u\n", bit, unsetBit(num, bit));

	return 0;
}

unsigned int setBit(unsigned int num, unsigned int bit)
{
	unsigned int result, mask = 1;
	result = mask << bit;
	result = result | num;

	return result;
}

unsigned int toggleBit(unsigned int num, unsigned int bit)
{
	unsigned int mask = 1, result;
	result = mask << bit;
	result = result ^ num;
	return result;
}

unsigned int unsetBit(unsigned int num, unsigned int bit)
{
	unsigned int mask = 1, result;
	result = ~(mask << bit);
	result = result & num;

	return result;
}
