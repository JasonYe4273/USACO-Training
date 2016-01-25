/*
ID: yuzhou.1
LANG: JAVA
TASK: race3
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class race3
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("race3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));
		
		boolean[][] adj = new boolean[50][50];
		int num = 0;
		String line = in.readLine();
		while (!line.trim().equals("-1"))
		{
			adj[num][num] = true;
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				int to = Integer.parseInt(st.nextToken());
				if (to == -2) break;
				adj[num][to] = true;
			}
			num++;
			line = in.readLine();
		}
		in.close();

		boolean[][] doubleAdj = new boolean[num * 2][num * 2];
		for (int i = 0; i < num; i++)
		{
			doubleAdj[i][i] = true;
			doubleAdj[i + num][i + num] = true;
			doubleAdj[i][i + num] = true;
			doubleAdj[i + num][i] = true;
			for (int j = 0; j < num; j++) doubleAdj[i][j + num] = adj[i][j];
		}
		
		ArrayList<Integer> unavoidable = new ArrayList<Integer>();
		ArrayList<Integer> splitting = new ArrayList<Integer>();
		for (int test = 1; test < num - 1; test++)
		{
			doubleAdj[test][test + num] = false;
			doubleAdj[test + num][test] = false;
			
			int src = 0;
			boolean[] reachable = new boolean[2 * num];
			boolean[] visited = new boolean[2 * num];
			reachable[src] = true;
			for (int i = 0; i < 2 * num; i++)
			{
				int idx;
				for (idx = 0; idx < 2 * num; idx++)
				{
					if (!visited[idx] && reachable[idx]) break;
				}
				if (idx == 2 * num) break;
				
				visited[idx] = true;
				for (int j = 0; j < 2 * num; j++)
				{
					if (doubleAdj[idx][j])
					{
						reachable[j] = true;
					}
				}
			}
			if (!reachable[2 * num - 1]) unavoidable.add(test);
			
			reachable = new boolean[2 * num];
			visited = new boolean[2 * num];
			reachable[src] = true;
			for (int i = 0; i < 2 * num; i++)
			{
				int idx = 0;
				for (idx = 0; idx < 2 * num; idx++)
				{
					if (!visited[idx] && reachable[idx]) break;
				}
				if (idx == 2 * num) break;
				
				visited[idx] = true;
				for (int j = 0; j < 2 * num; j++)
				{
					if ((j != test + num && doubleAdj[idx][j]) || (idx != test + num && doubleAdj[j][idx]))
					{
						reachable[j] = true;
					}
				}
			}
			if (!reachable[2 * num - 1]) splitting.add(test);
			
			doubleAdj[test][test + num] = true;
			doubleAdj[test + num][test] = true;
		}
		
		Integer[] unavoidableArray = unavoidable.toArray(new Integer[0]);
		Integer[] splittingArray = splitting.toArray(new Integer[0]);
		Arrays.sort(unavoidableArray);
		Arrays.sort(splittingArray);
		
		if (unavoidableArray.length > 0)
		{
			out.print(unavoidableArray.length + " ");
			for (int i = 0; i < unavoidableArray.length - 1; i++)
			{
				out.print(unavoidableArray[i] + " ");
			}
			out.println(unavoidableArray[unavoidableArray.length - 1]);
		}
		else out.println(0);
		
		if (splittingArray.length > 0)
		{
			out.print(splittingArray.length + " ");
			for (int i = 0; i < splittingArray.length - 1; i++)
			{
				out.print(splittingArray[i] + " ");
			}
			out.println(splittingArray[splittingArray.length - 1]);
		}
		else out.println(0);
		out.close();
	}
}
