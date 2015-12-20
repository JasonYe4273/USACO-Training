/*
ID: yuzhou.1
LANG: JAVA
TASK: pprime
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class pprime
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		in.close();
		
		int minDigits = 0;
		int temp = a;
		while (temp > 0)
		{
			minDigits++;
			temp /= 10;
		}
		
		int maxDigits = 0;
		temp = b;
		while (temp > 0)
		{
			maxDigits++;
			temp /= 10;
		}
		
		int d = minDigits;
		int pow = (int) Math.pow(10, d / 2);
		if (d % 2 == 0)
		{
			for (int i = pow / 10; i < pow; i++)
			{
				int num = i * pow;
				temp = i;
				int temp2 = pow / 10;
				while (temp > 0)
				{
					num += temp2 * (temp % 10);
					temp /= 10;
					temp2 /= 10;
				}
				if (num >= a && num <= b && isPrime(num)) out.println(num);
			}
		}
		else
		{
			for (int i = pow / 10; i < pow; i++)
			{
				int num = i * pow * 10;
				temp = i;
				int temp2 = pow / 10;
				while (temp > 0)
				{
					num += temp2 * (temp % 10);
					temp /= 10;
					temp2 /= 10;
				}
				for (int j = 0; j < 10; j++)
				{
					if (num >= a && num <= b && isPrime(num)) out.println(num);
					num += pow;
				}
			}
		}
		for (d = minDigits + 1; d < maxDigits; d++)
		{
			pow = (int) Math.pow(10, d / 2);
			if (d % 2 == 0)
			{
				for (int i = pow / 10; i < pow; i++)
				{
					int num = i * pow;
					temp = i;
					int temp2 = pow / 10;
					while (temp > 0)
					{
						num += temp2 * (temp % 10);
						temp /= 10;
						temp2 /= 10;
					}
					if (isPrime(num)) out.println(num);
				}
			}
			else
			{
				for (int i = pow / 10; i < pow; i++)
				{
					int num = i * pow * 10;
					temp = i;
					int temp2 = pow / 10;
					while (temp > 0)
					{
						num += temp2 * (temp % 10);
						temp /= 10;
						temp2 /= 10;
					}
					for (int j = 0; j < 10; j++)
					{
						if (isPrime(num)) out.println(num);
						num += pow;
					}
				}
			}
		}
		if (minDigits != maxDigits)
		{
			d = maxDigits;
			pow = (int) Math.pow(10, d / 2);
			if (d % 2 == 0)
			{
				for (int i = pow / 10; i < pow; i++)
				{
					int num = i * pow;
					temp = i;
					int temp2 = pow / 10;
					while (temp > 0)
					{
						num += temp2 * (temp % 10);
						temp /= 10;
						temp2 /= 10;
					}
					if (num <= b && isPrime(num)) out.println(num);
				}
			}
			else
			{
				for (int i = pow / 10; i < pow; i++)
				{
					int num = i * pow * 10;
					temp = i;
					int temp2 = pow / 10;
					while (temp > 0)
					{
						num += temp2 * (temp % 10);
						temp /= 10;
						temp2 /= 10;
					}
					for (int j = 0; j < 10; j++)
					{
						if (num <= b && isPrime(num)) out.println(num);
						num += pow;
					}
				}
			}
		}
		
		out.close();
	}
	
	public static boolean isPrime(int num)
	{
		for (int i = 2; i <= Math.pow(num, 0.5); i++) if (num % i == 0) return false;
		return true;
	}
}
