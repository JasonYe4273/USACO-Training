/*
ID: yuzhou.1
LANG: JAVA
TASK: fence6
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

public class fence6
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("fence6.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		int[] length = new int[n];
		int[][] end1 = new int[n][];
		int[][] end2 = new int[n][];
		for (int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			length[idx] = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			end1[idx] = new int[n1 + 1];
			end1[idx][0] = idx;
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= n1; j++) end1[idx][j] = Integer.parseInt(st.nextToken()) - 1;
			
			end2[idx] = new int[n2 + 1];
			end2[idx][0] = idx;
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= n2; j++) end2[idx][j] = Integer.parseInt(st.nextToken()) - 1;
		}
		in.close();
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		Fence[] fences = new Fence[n];
		for (int i = 0; i < n; i++) fences[i] = new Fence();
		for (int i = 0; i < n; i++)
		{
			Node node1 = new Node(end1[i], i, 1);
			int idx1 = nodes.indexOf(node1);
			if (idx1 == -1)
			{
				nodes.add(node1);
				fences[i].setEnd(1, node1);
			}
			else
			{
				nodes.get(idx1).setEnd(i, 1);
				fences[i].setEnd(1, nodes.get(idx1));
			}
			
			Node node2 = new Node(end2[i], i, 2);
			int idx2 = nodes.indexOf(node2);
			if (idx2 == -1)
			{
				nodes.add(node2);
				fences[i].setEnd(2, node2);
			}
			else
			{
				nodes.get(idx2).setEnd(i, 2);
				fences[i].setEnd(2, nodes.get(idx2));
			}
		}
		
		int size = nodes.size();
		int[][] adj = new int[size][size];
		for (int i = 0; i < size; i++) for (int j = 0; j < size; j++) adj[i][j] = Integer.MAX_VALUE;
		for (int i = 0; i < size; i++)
		{
			Node curr = nodes.get(i);
			for (int j = 0; j < curr.fences.length; j++)
			{
				int otherIdx = nodes.indexOf(fences[curr.fences[j]].getEnd(3 - curr.ends[j]));
				adj[i][otherIdx] = length[curr.fences[j]];
			}
		}
		
		int minPerim = Integer.MAX_VALUE;
		for (int i = 0; i < size; i++)
		{
			boolean[] visited = new boolean[size];
			int[] dist = new int[size];
			for (int j = 0; j < size; j++) dist[j] = Integer.MAX_VALUE;
			dist[i] = 0;
			for (int j = 0; j < size; j++)
			{
				int minDist = Integer.MAX_VALUE;
				int idx = 0;
				for (int k = 0; k < size; k++)
				{
					if (!visited[k] && dist[k] < minDist)
					{
						idx = k;
						minDist = dist[k];
					}
				}
				if (minDist == Integer.MAX_VALUE) break;
				
				visited[idx] = true;
				for (int k = 0; k < size; k++)
				{
					if (visited[k]) continue;
					if (adj[idx][k] != Integer.MAX_VALUE)
					{
						if (dist[k] != Integer.MAX_VALUE)
						{
							int perim = dist[idx] + dist[k] + adj[idx][k];
							if (perim < minPerim)
							{
								minPerim = perim;
							}
						}
						
						if (dist[idx] + adj[idx][k] < dist[k])
						{
							dist[k] = dist[idx] + adj[idx][k];
						}
					}
				}
			}
		}
		
		out.println(minPerim);
		out.close();
	}
	
	static class Fence
	{
		public Node end1;
		public Node end2;
		
		public Fence(){}
		
		public void setEnd(int end, Node n)
		{
			if (end == 1) end1 = n;
			else end2 = n;
		}
		
		public Node getEnd(int end)
		{
			return end == 1 ? end1 : end2;
		}
	}
	
	static class Node
	{
		public int[] fences;
		public int[] ends;
		
		public Node(int[] fences, int fence, int end)
		{
			this.fences = fences;
			ends = new int[fences.length];
			Arrays.sort(fences);
			setEnd(fence, end);
		}
		
		public void setEnd(int fence, int end)
		{
			for (int i = 0; i < fences.length; i++)
			{
				if (fences[i] == fence)
				{
					ends[i] = end;
					return;
				}
			}
		}
		
		@Override
		public boolean equals(Object o)
		{
			Node other = (Node) o;
			if (fences.length != other.fences.length) return false;
			for (int i = 0; i < fences.length; i++)
			{
				if (fences[i] != other.fences[i]) return false;
			}
			return true;
		}
	}
}
