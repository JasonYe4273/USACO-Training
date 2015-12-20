/*
ID: yuzhou.1
LANG: JAVA
TASK: holstein
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class holstein
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		
		int v = Integer.parseInt(in.readLine());
		int[] needed = new int[v];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < v; i++) needed[i] = Integer.parseInt(st.nextToken());
		
		int g = Integer.parseInt(in.readLine());
		int[][] feeds = new int[g][v];
		for (int i = 0; i < g; i++)
		{
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < v; j++) feeds[i][j] = Integer.parseInt(st.nextToken());
		}
		in.close();
		
		ArrayList<Integer>[] sizes = new ArrayList[g];
		for (int i = 0; i < g; i++) sizes[i] = new ArrayList<Integer>();
		for (int i = 1; i < Math.pow(2, g); i++)
		{
			int temp = i;
			int size = 0;
			while (temp > 0)
			{
				if (temp % 2 == 1) size++;
				temp /= 2;
			}
			sizes[size - 1].add(i);
		}
		
		boolean done = false;
		int best = 0;
		int minOver = Integer.MAX_VALUE;
		for (int i = 0; i < g && !done; i++)
		{
			for (int j : sizes[i])
			{
				int temp = j;
				int[] fed = new int[v];
				for (int k = 0; temp > 0; k++)
				{
					if (temp % 2 == 1)
					{
						for (int l = 0; l < v; l++)
						{
							fed[l] += feeds[k][l];
						}
					}
					temp /= 2;
				}
				
				boolean completelyFed = true;
				int overfed = 0;
				for (int k = 0; k < v; k++)
				{
					if (fed[k] < needed[k])
					{
						completelyFed = false;
					}
					else overfed += fed[k] - needed[k];
				}
				
				if (completelyFed && overfed < minOver) 
				{
					done = true;
					best = j;
					minOver = overfed;
				}
			}
		}
		
		int num = 0;
		ArrayList<Integer> used = new ArrayList<Integer>();
		for (int k = 0; best > 0; k++)
		{
			if (best % 2 == 1)
			{
				num++;
				used.add(k + 1);
			}
			best /= 2;
		}
		
		String output = String.valueOf(num);
		for (int u : used) output += " " + String.valueOf(u);
		
		out.println(output);
		out.close();
	}
}
