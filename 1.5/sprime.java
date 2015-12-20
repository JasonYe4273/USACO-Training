/*
ID: yuzhou.1
LANG: JAVA
TASK: sprime
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class sprime
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new FileWriter("sprime.out"));
		
		int n = Integer.parseInt(in.readLine());
		in.close();
		
		int[] digits = { 1, 3, 7, 9 };
		int[] sprimes = { 2, 3, 5, 7 };
		int numSprimes = 4;
		for (int i = 1; i < n; i++)
		{
			int[] newSprimes = new int[numSprimes * 4];
			int newNumSprimes = 0;
			for (int s = 0; s < numSprimes; s++)
			{
				for (int p = 0; p < 4; p++)
				{
					int newSprime = sprimes[s] * 10 + digits[p];
					if (isPrime(newSprime)) newSprimes[newNumSprimes++] = newSprime;
				}
			}
			sprimes = newSprimes;
			numSprimes = newNumSprimes;
		}
		
		for (int i = 0; i < numSprimes; i++) out.println(sprimes[i]);
		out.close();
	}
	
	public static boolean isPrime(int num)
	{
		for (int i = 2; i <= Math.pow(num, 0.5); i++) if (num % i == 0) return false;
		return true;
	}
}
