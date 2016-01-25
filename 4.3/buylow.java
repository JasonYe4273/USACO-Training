/*
ID: yuzhou.1
LANG: JAVA
TASK: buylow
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class buylow
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("buylow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		int[] prices = new int[n];
		int place = 0;
		String line = in.readLine();
		while (line != null)
		{
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens()) prices[place++] = Integer.parseInt(st.nextToken());
			line = in.readLine();
		}
		in.close();
		
		int maxOverall = 0;
		int[] maxAt = new int[n];
		BigNum[] numMaxAt = new BigNum[n];
		for (int i = n - 1; i >= 0; i--)
		{
			maxAt[i] = 1;
			for (int j = i + 1; j < n; j++)
			{
				if (prices[j] < prices[i] && maxAt[j] >= maxAt[i])
				{
					maxAt[i] = maxAt[j] + 1;
				}
			}

			numMaxAt[i] = new BigNum(0);
			HashSet<Integer> pricesUsed = new HashSet<Integer>();
			for (int j = i + 1; j < n; j++)
			{
				if (prices[j] < prices[i] && maxAt[j] == maxAt[i] - 1 && !pricesUsed.contains(prices[j]))
				{
					numMaxAt[i].add(numMaxAt[j]);
					pricesUsed.add(prices[j]);
				}
			}
			if (numMaxAt[i].toString().equals("0")) numMaxAt[i].add(1);
			
			if (maxAt[i] > maxOverall) maxOverall = maxAt[i];
		}
		
		HashSet<Integer> pricesUsed = new HashSet<Integer>();
		BigNum numMax = new BigNum(0);
		for (int i = 0; i < n; i++)
		{
			if (maxAt[i] == maxOverall && !pricesUsed.contains(prices[i]))
			{
				numMax.add(numMaxAt[i]);
				pricesUsed.add(prices[i]);
			}
		}
		
		out.println(maxOverall + " " + numMax);
		out.close();
	}
	
	static class BigNum
	{
		int[] digits;
		
		public BigNum(int n)
		{
			digits = new int[100];
			for (int i = digits.length - 1; i >= 0; i--)
			{
				digits[i] = n % 10;
				n /= 10;
			}
		}
		
		public void add(int other)
		{
			digits[digits.length - 1] += other;
			int place = digits.length - 1;
			while (digits[place] >= 10)
			{
				digits[place - 1] += digits[place] / 10;
				digits[place--] %= 10;
			}
		}
		
		public void add(BigNum other)
		{
			int carry = 0;
			for (int i = digits.length - 1; i >= 0; i--)
			{
				this.digits[i] += other.digits[i] + carry;
				carry = this.digits[i] / 10;
				this.digits[i] %= 10;
			}
			if (carry > 0) this.add(null);
		}
		
		@Override
		public String toString()
		{
			String s = "";
			int place = 0;
			while (place < digits.length && digits[place++] == 0);
			for (int i = place - 1; i < digits.length; i++)
			{
				s += String.valueOf(digits[i]);
			}
			return s;
		}
	}
}
