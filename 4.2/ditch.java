/*
ID: yuzhou.1
LANG: JAVA
TASK: ditch
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ditch
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("ditch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] ditch = new int[m][m];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int capacity = Integer.parseInt(st.nextToken());
			ditch[from][to] += capacity;
		}
		in.close();
		
		int totalFlow = 0;
		while (true)
		{
			int[] flow = new int[m];
			boolean[] visited = new boolean[m];
			int[] prevNode = new int[m];
			flow[0] = Integer.MAX_VALUE;

			int maxFlow;
			int maxNode;
			while (true)
			{
				maxFlow = 0;
				maxNode = -1;
				for (int i = 0; i < m; i++)
				{
					if (!visited[i] && flow[i] > maxFlow)
					{
						maxFlow = flow[i];
						maxNode = i;
					}
				}
				if (maxNode == -1 || maxNode == m - 1) break;
				
				visited[maxNode] = true;
				for (int i = 0; i < m; i++)
				{
					int maxNext = Math.min(maxFlow, ditch[maxNode][i]);
					if (flow[i] < maxNext)
					{
						prevNode[i] = maxNode;
						flow[i] = maxNext;
					}
				}
			}
			if (maxNode == -1) break;
			
			int pathCap = flow[m - 1];
			totalFlow += pathCap;
			
			int currNode = m - 1;
			int nextNode;
			while (currNode != 0)
			{
				nextNode = prevNode[currNode];
				ditch[nextNode][currNode] -= pathCap;
				ditch[currNode][nextNode] += pathCap;
				currNode = nextNode;
			}
		}
		
		out.println(totalFlow);
		out.close();
	}
}
