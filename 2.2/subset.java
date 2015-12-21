/*
ID: yuzhou.1
LANG: JAVA
TASK: subset
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class subset
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		
		int n = Integer.parseInt(in.readLine());
		in.close();
		
		int sum = n * (n + 1) / 2;
		if (sum % 2 == 1)
		{
			out.println(0);
			out.close();
			return;
		}
		int goal = sum / 2;
		
		int[][] numSubset = new int[n + 1][goal + 1];
		numSubset[1][1] = 1;
		for (int i = 2; i <= n; i++)
		{
			numSubset[i][i] = 1;
			for (int j = 1; j < i; j++)
			{
				int max = j * (j + 1) / 2;
				for (int k = 1; k <= max && k + i <= goal; k++)
				{
					numSubset[i][k + i] += numSubset[j][k];
				}
			}
		}
		
		out.println(numSubset[n][goal]);
		out.close();
	}
	
	public static int numSubset(int n, int sum)
	{
		sum -= n;
		if (sum == 0) return 1;
		int num = 0;
		for (int i = 1; i < n && i <= sum; i++)
		{
			num += numSubset(i, sum);
		}
		return num;
	}
}
