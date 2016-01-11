/*
ID: yuzhou.1
LANG: JAVA
TASK: agrinet
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class agrinet
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		int x = 0;
		int y = 0;
		int[][] adj = new int[n][n];
		String line = in.readLine();
		while (line != null)
		{
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				adj[x][y] = Integer.parseInt(st.nextToken());
				y++;
				if (y == n)
				{
					x++;
					y = 0;
				}
			}
			line = in.readLine();
		}
		in.close();
		
		int[] distance = new int[n];
		for (int i = 0; i < n; i++) distance[i] = adj[i][0];
		boolean[] inTree = new boolean[n];
		int treeCost = 0;
		for (int i = 0; i < n; i++)
		{
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for (int j = 0; j < n; j++)
			{
				if (!inTree[j] && distance[j] < min)
				{
					min = distance[j];
					idx = j;
				}
			}
			
			inTree[idx] = true;
			distance[idx] = 0;
			treeCost += min;
			for (int j = 0; j < n; j++)
			{
				if (adj[idx][j] < distance[j]) distance[j] = adj[idx][j];
			}
		}
		
		out.println(treeCost);
		out.close();
	}
}
