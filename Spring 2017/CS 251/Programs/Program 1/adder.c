/***************************************************************************
* Description: Program that will add 2 positive decimal inputs together using
* 	       a "Ripple-Carry Adder"
* Author: Justin Espiritu
* 3/13/2017
* Version 1
***************************************************************************/

#include "stdio.h"

//max bit size of integer
#define MAX_BITS 32

//when shifting carryOut bit to next bit location
#define NEXT_BIT 1

unsigned int getBit(unsigned int, unsigned int);
unsigned int sum(unsigned int, unsigned int, unsigned int);
unsigned int carry(unsigned int , unsigned int, unsigned int);
unsigned int setBit(unsigned int, unsigned int, unsigned int);

int main()
{
	unsigned int a, b, i, resultBit, carryIn, carryOut, ai, bi, si;

	resultBit = 0;
	carryIn = 0;

	//grab to unsigned integers from user
	printf("Enter two 32 bit unsigned integers: \n");
	scanf("%u%u", &a, &b);

	/*
	*go through each bit of unsigned integers a and b and
	*run them through Ripple-Carry Adder
	*/
	for(i = 0; i < MAX_BITS; i++)
	{
		//shift carryIn bit to the next bit location
		carryIn = carryIn << NEXT_BIT;

		//get bit of a at i
		ai = getBit(a, i);

		//get bit of b at i
		bi = getBit(b, i);

		//add the bits together
		si = sum(carryIn, ai, bi);

		//get the carryOut bit
		carryOut = carry(carryIn, ai, bi);

		//set bit to appropriate bit
		resultBit = setBit(resultBit, i , si);

		//set carryIn bit to new carryOut
		carryIn = carryOut;
	}

	//check if overflow will occur
	if(carryIn)
	{
		//if overflow occured print following message
		printf("Overflow has occurred\n");
	}

	//print result in decimal and hexadecimal
	else
	{
		printf("Result in decimal: %u\n", resultBit);
		printf("Result in hexadecimal: %X\n", resultBit);
	}

	return 0;
}

//returns the bit at bit i
unsigned int getBit(unsigned int num, unsigned int bit)
{
	unsigned int result, mask;
	mask = 1;

	mask = mask << bit;
	result = num & mask;

	return result;
}

//adds bits together, returns resulting bit
unsigned int sum(unsigned int carry, unsigned int bitA, unsigned int bitB)
{
	unsigned int result;

	//simplified function
	result = ~bitA & ~bitB & carry | ~bitA & bitB & ~carry | bitA & bitB & carry | bitA & ~bitB & ~carry;

	return result;
}

//adds bitA, bitB, and carryIn, returns resulting carryOut bit
unsigned int carry(unsigned int carry, unsigned int bitA, unsigned int bitB)
{
	unsigned int result;

	//simplified function
	result = bitA & bitB | bitA & carry | bitB & carry;

	return result;
}

//sets bit to corresponding set bit from the sum() and carry() result
unsigned int setBit(unsigned int num, unsigned int bit, unsigned int set)
{
	unsigned int result, mask;
	mask = 1;

	mask = mask << bit;

	//set bit to 0 if sum = 0
	if(!set)
	{
		mask = ~mask;
		result = num & mask;
	}

	//set bit to 1 if sum = 1
	else
	{
		result = num | mask;
	}

	return result;
}
