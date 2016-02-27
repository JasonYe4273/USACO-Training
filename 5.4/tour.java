/*
ID: yuzhou.1
LANG: JAVA
TASK: tour
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class tour
{
	public static void main(String[] args) throws IOException
	{
		long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("tour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tour.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		ArrayList<String> cities = new ArrayList<String>();
		for (int i = 0; i < n; i++) cities.add(in.readLine().trim());
		
		boolean[][] adj = new boolean[2 * n][2 * n];
		for (int i = 0; i < n; i++) adj[2 * i][2 * i + 1] = true;
		for (int i = 0; i < v; i++)
		{
			st = new StringTokenizer(in.readLine());
			int a = cities.indexOf(st.nextToken().trim());
			int b = cities.indexOf(st.nextToken().trim());
			if (a < b) adj[2 * a + 1][2 * b] = true;
			else adj[2 * b + 1][2 * a] = true;
		}
		in.close();
		
		out.println(nodeFlow(2 * n, adj, 2) / 2);
		out.close();
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static int nodeFlow(int n, boolean[][] adj, int numPaths)
	{
		int maxNodes = 0;
		for (int k = 0; k < numPaths; k++)
		{
			int[] numNodes = new int[n];
			boolean[] visited = new boolean[n];
			int[] prevNode = new int[n];
			for (int i = 0; i < n; i++)
			{
				numNodes[i] = Integer.MIN_VALUE;
				prevNode[i] = -1;
			}
			numNodes[1] = 1;

			int leftNode;
			while (true)
			{
				leftNode = 1;
				while (leftNode < n - 1 && (visited[leftNode] || numNodes[leftNode] == Integer.MIN_VALUE)) leftNode++;
				if (leftNode == n - 1) break;
				
				visited[leftNode] = true;
				for (int i = 0; i < n; i++)
				{
					if (adj[leftNode][i])
					{
						int maxNext = leftNode > i ? numNodes[leftNode] - 1 : numNodes[leftNode] + 1;
						if (numNodes[i] < maxNext && !isParent(i, leftNode, prevNode))
						{
							visited[i] = false;
							prevNode[i] = leftNode;
							numNodes[i] = maxNext;
						}
					}
				}
			}
			
			int pathNodes = numNodes[n - 2];
			if (pathNodes <= 0) return 2;
			maxNodes += pathNodes;
			
			int currNode = n - 2;
			int nextNode;
			while (currNode != 1)
			{
				System.out.print(" " + currNode);
				nextNode = prevNode[currNode];
				adj[nextNode][currNode] = false;
				adj[currNode][nextNode] = true;
				currNode = nextNode;
			}
			System.out.println();
		}
		
		return maxNodes;
	}
	
	public static boolean isParent(int p, int c, int[] prevNode)
	{
		while (prevNode[c] != -1)
		{
			c = prevNode[c];
			if (c == p) return true;
		}
		return false;
	}
}
