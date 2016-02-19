/*
ID: yuzhou.1
LANG: JAVA
TASK: schlnet
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class schlnet
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("schlnet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));
		
		int n = Integer.parseInt(in.readLine());
		int[][] adj = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) for (int j = 1; j <= n; j++) adj[i][j] = Integer.MAX_VALUE;
		boolean[][] reachable = new boolean[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(st.nextToken());
			while (s != 0)
			{
				adj[i][s] = 1;
				s = Integer.parseInt(st.nextToken());
			}
		}
		in.close();
		
		for (int k = 1; k <= n; k++)
		{
			for (int i = 1; i <= n; i++)
			{
				for (int j = 1; j <= n; j++)
				{
					if (adj[i][k] != Integer.MAX_VALUE && adj[k][j] != Integer.MAX_VALUE && adj[i][k] + adj[k][j] < adj[i][j])
					{
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
		
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				reachable[i][j] = adj[i][j] < Integer.MAX_VALUE;
			}
		}
		
		boolean[] hasChildren = new boolean[n + 1];
		boolean[] hasParents = new boolean[n + 1];
		for (int i = 1; i < n; i++)
		{
			for (int j = i + 1; j <= n; j++)
			{
				if (reachable[i][j])
				{
					hasChildren[i] = true;
					hasParents[j] = true;
				}
				
				if (reachable[j][i])
				{
					hasChildren[j] = true;
					hasParents[i] = true;
				}
			}
		}
		
		boolean[] received = new boolean[n + 1];
		int numToSend = 0;
		for (int i = 1; i <= n; i++)
		{
			if (!hasParents[i])
			{
				received[i] = true;
				numToSend++;
				for (int j = 1; j <= n; j++) if (reachable[i][j]) received[j] = true;
			}
		}
		
		for (int i = 1; i <= n; i++)
		{
			if (reachable[i][i] && !received[i])
			{
				received[i] = true;
				numToSend++;
				for (int j = 1; j <= n; j++) if (reachable[i][j]) received[j] = true;
			}
		}
		
		out.println(numToSend);
		
		ArrayList<Group> groups = new ArrayList<Group>();
		int[] groupIdx = new int[n + 1];
		for (int i = 1; i <= n; i++) groupIdx[i] = -1;
		for (int i = 1; i <= n; i++)
		{
			if (groupIdx[i] != -1) continue;
			
			if (!hasParents[i])
			{
				Group newGroup = new Group();
				newGroup.addNoParent(i);
				
				groupIdx[i] = groups.size();
				
				for (int j = 0; j < newGroup.nodes.size(); j++)
				{
					int idx = newGroup.nodes.get(j);
					for (int k = 1; k <= n; k++)
					{
						if (groupIdx[k] == -1 && (reachable[idx][k] || reachable[k][idx]))
						{
							groupIdx[k] = groups.size();
							if (!hasParents[k]) newGroup.addNoParent(k);
							else if (!hasChildren[k]) newGroup.addNoChildren(k);
							else newGroup.add(k);
						}
					}
				}
				
				groups.add(newGroup);
			}
		}
		
		for (int i = 1; i <= n; i++)
		{
			if (groupIdx[i] != -1) continue;
			
			Group newGroup = new Group();
			if (!hasChildren[i]) newGroup.addNoChildren(i);
			else newGroup.add(i);
			
			groupIdx[i] = groups.size();
			
			for (int j = 0; j < newGroup.nodes.size(); j++)
			{
				int idx = newGroup.nodes.get(j);
				for (int k = 1; k <= n; k++)
				{
					if (groupIdx[k] == -1 && (reachable[idx][k] || reachable[k][idx]))
					{
						groupIdx[k] = groups.size();
						if (!hasChildren[k]) newGroup.addNoChildren(k);
						else newGroup.add(k);
					}
				}
			}
			
			groups.add(newGroup);
		}
		
		int numToConnect = 0;
		for (Group g : groups)
		{
			if (g.nodes.size() == 1) continue;
			numToConnect += Math.max(g.noChildren.size(), g.noParents.size());
			if (g.noChildren.size() > 0 && g.noParents.size() > 0 && groups.size() > 1) numToConnect--;
		}
		if (groups.size() > 1) numToConnect += groups.size();
		
		out.println(numToConnect);
		
		out.close();
	}
	
	public static void addTo(int size, boolean[][] reachable, int connectPt)
	{
		for (int i = 1; i < size; i++)
		{
			if (reachable[i][connectPt])
			{
				reachable[i][size] = true;
				if (reachable[size][i]) reachable[size][size] = true;
				for (int j = 1; j < size; j++)
				{
					if (reachable[size][j]) reachable[i][j] = true;
				}
			}
		}
	}
	
	public static void addFrom(int size, boolean[][] reachable, int connectPt)
	{
		for (int i = 1; i < size; i++)
		{
			if (reachable[connectPt][i])
			{
				reachable[size][i] = true;
			}
		}
	}
	
	static class Group
	{
		public ArrayList<Integer> nodes;
		public ArrayList<Integer> noParents;
		public ArrayList<Integer> noChildren;
		public boolean simpleCycle;
		
		public Group()
		{
			nodes = new ArrayList<Integer>();
			noParents = new ArrayList<Integer>();
			noChildren = new ArrayList<Integer>();
			simpleCycle = true;
		}
		
		public void addNoParent(int i)
		{
			nodes.add(i);
			noParents.add(i);
			simpleCycle = false;
		}
		
		public void addNoChildren(int i)
		{
			nodes.add(i);
			noChildren.add(i);
			simpleCycle = false;
		}
		
		public void add(int i)
		{
			nodes.add(i);
		}
	}
}
