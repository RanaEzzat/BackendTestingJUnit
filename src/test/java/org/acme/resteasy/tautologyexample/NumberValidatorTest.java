package org.acme.resteasy.tautologyexample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberValidatorTest {

	/*@Test
	public void checkPrimeNumbers() {
		//Example of tautology where we are repeating the test code as the code that we are testing
		//Tests should not include logic(No calculation because this might cause incorrect answers)
		//You must be sure of your test
		Integer numbers[]= {1,15,23,25,60,61,63,79,207};
		NumberValidator validator=new NumberValidator();
		
		for(int i=0;i<numbers.length;i++)
		{
			boolean isPrime=true;
			int maxDivisor=(int)Math.sqrt(numbers[i]);
			for(int counter=2;counter<maxDivisor;counter++)
			{
				if(numbers[i]%counter==0)
					isPrime=false;
			}
			assertEquals(isPrime,validator.isItPrime(numbers[i]));
		}
	}*/
	
	@Test
	public void checkPrimeNumbers()
	{
		Integer numbers[]= {1,23,61,79};
		NumberValidator validator = new NumberValidator();
		for(int i=0;i<numbers.length;i++)
		{
			assertEquals(true,validator.isItPrime(numbers[i]));
		}
	}
	
	@Test
	public void checkNonPrimeNumbers()
	{
		//This showed us that we have a mistake in our code
		//because we added results that we are sure they
		//are correct, therefore this is why avoiding
		//tautology is very important
		Integer numbers[]= {15,25,60,63,207};
		NumberValidator validator = new NumberValidator();
		for(int i=0;i<numbers.length;i++)
		{
			assertEquals(false,validator.isItPrime(numbers[i]));
		}
	}

}
