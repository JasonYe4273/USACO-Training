/*
ID: yuzhou.1
LANG: JAVA
TASK: stall4
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class stall4
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("stall4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] matches = new int[n * 2 + m * 2 + 2][n * 2 + m * 2 + 2];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++)
			{
				int idx = Integer.parseInt(st.nextToken()) - 1;
				matches[n + i][2 * n + idx] = 1;
			}
		}
		for (int i = 0; i < n; i++)
		{
			matches[i][n + i] = 1;
			matches[n + i][i] = 1;
			matches[i][2 * n + 2 * m] = 1;
			matches[2 * n + 2 * m][i] = 1;
		}
		for (int i = 0; i < m; i++)
		{
			matches[2 * n + i][2 * n + m + i] = 1;
			matches[2 * n + m + i][2 * n + i] = 1;
			matches[2 * n + m + i][2 * n + 2 * m + 1] = 1;
			matches[2 * n + 2 * m + 1][2 * n + m + i] = 1;
		}
		in.close();
		
		int totalFlow = 0;
		while (true)
		{
			int[] flow = new int[2 * n + 2 * m + 2];
			boolean[] visited = new boolean[2 * n + 2 * m + 2];
			int[] prevNode = new int[2 * n + 2 * m + 2];
			flow[2 * n + 2 * m] = Integer.MAX_VALUE;

			int maxFlow;
			int maxNode;
			while (true)
			{
				maxFlow = 0;
				maxNode = -1;
				for (int i = 0; i < 2 * n + 2 * m + 2; i++)
				{
					if (!visited[i] && flow[i] > maxFlow)
					{
						maxFlow = flow[i];
						maxNode = i;
					}
				}
				if (maxNode == -1 || maxNode == 2 * n + 2 * m + 1) break;
				
				visited[maxNode] = true;
				for (int i = 0; i < 2 * n + 2 * m + 2; i++)
				{
					int maxNext = Math.min(maxFlow, matches[maxNode][i]);
					if (flow[i] < maxNext)
					{
						prevNode[i] = maxNode;
						flow[i] = maxNext;
					}
				}
			}
			if (maxNode == -1) break;
			
			int pathCap = flow[2 * n + 2 * m + 1];
			totalFlow += pathCap;
			
			int currNode = 2 * n + 2 * m + 1;
			int nextNode;
			while (currNode != 2 * n + 2 * m)
			{
				nextNode = prevNode[currNode];
				matches[nextNode][currNode] -= pathCap;
				matches[currNode][nextNode] += pathCap;
				currNode = nextNode;
			}
		}
		
		out.println(totalFlow);
		out.close();
	}
}
