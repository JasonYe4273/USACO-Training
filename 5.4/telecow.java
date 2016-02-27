/*
ID: yuzhou.1
LANG: JAVA
TASK: telecow
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class telecow
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("telecow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("telecow.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken()) - 1;
		int c2 = Integer.parseInt(st.nextToken()) - 1;
		
		int[][] adj = new int[2 * n][2 * n];
		int[][] temp = new int[2 * n][2 * n];
		for (int i = 0; i < n; i++)
		{
			adj[i][i + n] = 1;
			adj[i + n][i] = 1;
			temp[i][i + n] = 1;
			temp[i + n][i] = 1;
		}
		for (int i = 0; i < m; i++)
		{
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a][b + n] = 1;
			temp[a][b + n] = 1;
			adj[b][a + n] = 1;
			temp[b][a + n] = 1;
		}
		in.close();
		
		int totalFlow = flow(2 * n, temp, c1, c2 + n);
		out.println(totalFlow);
		if (totalFlow == 0)
		{
			out.close();
			return;
		}
		
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
		{
			if (i == c1 || i == c2) continue;

			temp = new int[2 * n][2 * n];
			for (int j = 0; j < 2 * n; j++)
			{
				for (int k = 0; k < 2 * n; k++)
				{
					if (j % n != i && k % n != i) temp[j][k] = adj[j][k];
				}
			}
			
			int newFlow = flow(2 * n, temp, c1, c2 + n);
			if (newFlow < totalFlow)
			{
				/**/
				for (int j = 0; j < 2 * n; j++)
				{
					adj[i][j] = 0;
					adj[j][i + n] = 0;
				}
				totalFlow = newFlow;
				toRemove.add(i);
			}
		}
		
		out.print(toRemove.get(0) + 1);
		for (int i = 1; i < toRemove.size(); i++)
		{
			out.print(" " + (toRemove.get(i) + 1));
		}
		out.println();
		out.close();
	}
	
	public static int flow(int n, int[][] adj, int src, int end)
	{
		int totalFlow = 0;
		while (true)
		{
			int[] flow = new int[n];
			boolean[] visited = new boolean[n];
			int[] prevNode = new int[n];
			flow[src] = Integer.MAX_VALUE;

			int maxFlow;
			int maxNode;
			while (true)
			{
				maxFlow = 0;
				maxNode = -1;
				for (int i = 0; i < n; i++)
				{
					if (!visited[i] && flow[i] > maxFlow)
					{
						maxFlow = flow[i];
						maxNode = i;
					}
				}
				if (maxNode == -1 || maxNode == end) break;
				
				visited[maxNode] = true;
				for (int i = 0; i < n; i++)
				{
					int maxNext = Math.min(maxFlow, adj[maxNode][i]);
					if (flow[i] < maxNext)
					{
						prevNode[i] = maxNode;
						flow[i] = maxNext;
					}
				}
			}
			if (maxNode == -1) break;
			
			int pathCap = flow[end];
			if (pathCap == 0) return totalFlow;
			totalFlow += pathCap;
			
			int currNode = end;
			int nextNode;
			while (currNode != 0)
			{
				nextNode = prevNode[currNode];
				adj[nextNode][currNode] -= pathCap;
				adj[currNode][nextNode] += pathCap;
				currNode = nextNode;
			}
		}
		
		return totalFlow;
	}
}
