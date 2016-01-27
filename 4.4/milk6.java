/*
ID: yuzhou.1
LANG: JAVA
TASK: milk6
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class milk6
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("milk6.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] capacity = new int[n][n];
		int[][] trucks = new int[m][3];
		for (int i = 0; i < m; i++)
		{
			st = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			capacity[s][e] += c;
			trucks[i][0] = s;
			trucks[i][1] = e;
			trucks[i][2] = c;
		}
		in.close();
		
		int minCost = maxFlow(capacity);
		if (minCost == 0)
		{
			out.println("0 0");
			out.close();
			return;
		}
		
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (int i = 0; i < m; i++)
		{
			if (minCost == capacity[trucks[i][0]][trucks[i][1]] && trucks[i][2] == capacity[trucks[i][0]][trucks[i][1]])
			{
				out.println(minCost + " 1");
				out.println(i + 1);
				out.close();
				return;
			}
			
			capacity[trucks[i][0]][trucks[i][1]] -= trucks[i][2];
			if (maxFlow(capacity) + trucks[i][2] == minCost) toRemove.add(i + 1);
			capacity[trucks[i][0]][trucks[i][1]] += trucks[i][2];
		}
		
		out.println(minCost + " " + toRemove.size());
		for (int t : toRemove)
		{
			out.println(t);
		}
		out.close();
	}
	
	public static int maxFlow(int[][] capacity)
	{
		int m = capacity.length;
		int[][] c = new int[m][m];
		for (int i = 0; i < m; i++) for (int j = 0; j < m; j++) c[i][j] = capacity[i][j];
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
					int maxNext = Math.min(maxFlow, c[maxNode][i]);
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
				c[nextNode][currNode] -= pathCap;
				c[currNode][nextNode] += pathCap;
				currNode = nextNode;
			}
		}
		return totalFlow;
	}
	
	static class RemoveTrucks
	{
		public ArrayList<Integer> toRemove;
		public int flow;
		
		public RemoveTrucks(int maxFlow)
		{
			toRemove = new ArrayList<Integer>();
			flow = maxFlow;
		}
		
		@SuppressWarnings("unchecked")
		public RemoveTrucks(ArrayList<Integer> remove, ArrayList<Integer> toAdd, int maxFlow)
		{
			toRemove = (ArrayList<Integer>) remove.clone();
			toRemove.addAll(toAdd);
			Collections.sort(toRemove);
			flow = maxFlow;
		}
		
		@Override
		public boolean equals(Object o)
		{
			RemoveTrucks other = (RemoveTrucks) o;
			if (toRemove.size() != other.toRemove.size()) return false;
			for (int i = 0; i < toRemove.size(); i++)
			{
				if (toRemove.get(i) != other.toRemove.get(i)) return false;
			}
			return true;
		}
	}
}
