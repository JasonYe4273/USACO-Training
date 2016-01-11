/*
ID: yuzhou.1
LANG: JAVA
TASK: stamps
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class stamps
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int max = 0;
		int idx = 0;
		int[] stamps = new int[n];
		String line = in.readLine();
		while (line != null)
		{
			st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				stamps[idx] = Integer.parseInt(st.nextToken());
				if (stamps[idx] > max)
				{
					max = stamps[idx];
				}
				idx++;
			}
			line = in.readLine();
		}
		in.close();
		
		int[] minStamps = new int[k * max + 1];
		for (int i = 1; i < minStamps.length; i++) minStamps[i] = Integer.MAX_VALUE;
		minStamps[0] = 0;
		
		int maxCons;
		for (maxCons = 0; maxCons < minStamps.length && minStamps[maxCons] <= k; maxCons++)
		{
			for (int s = 0; s < n; s++)
			{
				int newVal = maxCons + stamps[s];
				if (newVal >= minStamps.length) continue;
				if (minStamps[maxCons] + 1 < minStamps[newVal])
				{
					minStamps[newVal] = minStamps[maxCons] + 1;
				}
			}
		}
		
		out.println(maxCons - 1);
		out.close();
	}
}
