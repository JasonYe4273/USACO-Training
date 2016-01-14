/*
ID: yuzhou.1
LANG: JAVA
TASK: rockers
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rockers
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		int[] time = new int[n];
		int over = 0;
		for (int i = 0; i < n; i++)
		{
			if (time[i] > t) over++;
			else time[i] = Integer.parseInt(st.nextToken());
		}
		n -= over;
		in.close();
		
		int[][][] maxSubset = new int[n][n][m + 1];
		for (int s = 0; s < n; s++)
		{
			for (int e = s; e < n; e++)
			{
				int[] sortedTimes = new int[e - s + 1];
				for (int i = s; i <= e; i++) sortedTimes[i - s] = time[i];
				Arrays.sort(sortedTimes);
				
				int i;
				int cumul = 0;
				for (i = 0; i < sortedTimes.length; i++)
				{
					cumul += sortedTimes[i];
					if (cumul > t)
					{
						break;
					}
				}
				for (int k = 1; k <= m; k++) maxSubset[s][e][k] = i;
			}
		}

		for (int k = 1; k <= m; k++)
		{
			for (int l = 2; l <= n; l++)
			{
				for (int s = 0; s <= n - l; s++)
				{
					int e = s + l - 1;
					for (int s2 = s + 1; s2 <= e; s2++)
					{
						for (int k2 = 0; k2 <= k; k2++)
						{
							if (maxSubset[s][s2 - 1][k - k2] + maxSubset[s2][e][k2] > maxSubset[s][e][k])
							{
								maxSubset[s][e][k] = maxSubset[s][s2 - 1][k - k2] + maxSubset[s2][e][k2];
							}
						}
					}
				}
			}
		}
		
		out.println(maxSubset[0][n - 1][m]);
		out.close();
	}
}
