/*
ID: yuzhou.1
LANG: JAVA
TASK: 
 */

public class Dijkstra
{
	public static void main(String[] args)
	{
		int n = 100;
		int src = 0;
		int[][] adj = new int[n][n];
		
		int[] distances = new int[n];
		for (int i = 0; i < n; i++) distances[i] = Integer.MAX_VALUE;
		boolean[] visited = new boolean[n];
		distances[src] = 0;
		for (int i = 0; i < n; i++)
		{
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for (int j = 0; j < n; j++)
			{
				if (!visited[j] && distances[j] < min)
				{
					min = distances[j];
					idx = j;
				}
			}
			
			visited[idx] = true;
			for (int j = 0; j < n; j++)
			{
				if (adj[idx][j] != Integer.MAX_VALUE && adj[idx][j] + distances[idx] < distances[j])
				{
					distances[j] = adj[idx][j] + distances[idx];
				}
			}
		}
	}
}
