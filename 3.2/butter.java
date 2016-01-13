/*
ID: yuzhou.1
LANG: JAVA
TASK: butter
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class butter
{
	public static void main(String[] args) throws IOException
	{
		long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] numCows = new int[p];
		for (int i = 0; i < n; i++)
		{
			numCows[Integer.parseInt(in.readLine()) - 1]++;
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<int[]>[] edges = new ArrayList[p];
		for (int i = 0; i < p; i++) edges[i] = new ArrayList<int[]>();
		for (int i = 0; i < c; i++)
		{
			st = new StringTokenizer(in.readLine());
			int j = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			edges[j].add(new int[]{ k, w });
			edges[k].add(new int[]{ j, w });
		}
		in.close();
		
		int minCost = Integer.MAX_VALUE;
		int[][] distances = new int[p][p];
		for (int src = 0; src < p; src++)
		{
			if (edges[src].size() == 0) continue;
			int cost = 0;
			for (int i = 0; i < p; i++) distances[src][i] = Integer.MAX_VALUE;
			distances[src][src] = 0;
			ArrayList<Integer> unvisited = new ArrayList<Integer>();
			for (int i = 0; i < p; i++) if (edges[i].size() > 0) unvisited.add(i);
			for (int i = 0; i < p; i++)
			{
				int min = Integer.MAX_VALUE;
				int idx = 0;
				for (int j = 0; j < unvisited.size(); j++)
				{
					int index = unvisited.get(j);
					if (distances[src][index] < min)
					{
						min = distances[src][index];
						idx = j;
					}
				}
				
				idx = unvisited.remove(idx);
				cost += numCows[idx] * distances[src][idx];
				for (int[] e : edges[idx])
				{
					if (e[1] + distances[src][idx] < distances[src][e[0]])
					{
						distances[src][e[0]] = e[1] + distances[src][idx];
					}
				}
			}
			
			if (cost < minCost) minCost = cost;
		}
		
		out.println(minCost);
		out.close();
		System.out.println(System.currentTimeMillis() - start);
	}
}
