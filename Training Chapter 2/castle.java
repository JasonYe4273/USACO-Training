/*
ID: yuzhou.1
LANG: JAVA
TASK: castle
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class castle
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		Castle c = new Castle(m, n);
		for (int i = 0; i < n; i++) 
		{
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++)
			{
				int index = j * n + i;
				int wallNum = Integer.parseInt(st.nextToken());
				if ((wallNum / 2) % 2 == 0) c.removeNWall(index);
				if ((wallNum / 4) % 2 == 0) c.removeEWall(index);
			}
		}
		in.close();
		
		c.generateRooms();
		out.println(c.numRooms);
		out.println(c.maxSize);
		
		String[] worstWall = c.getWorstWall();
		out.println(worstWall[0]);
		out.println(worstWall[1]);
		out.close();
	}
	
	public static class Castle
	{
		public int m;
		public int n;
		public int[] parent;
		public int[] depth;
		public int[] room;
		public int[] roomSize;
		public int numRooms;
		public int maxSize;
		public boolean[] nWall;
		public boolean[] eWall;
		
		public Castle(int m, int n)
		{
			this.m = m;
			this.n = n;
			parent = new int[m * n];
			depth = new int[m * n];
			
			room = new int[m * n];
			roomSize = new int[m * n];
			
			nWall = new boolean[m * n];
			eWall = new boolean[m * n];
			
			for (int i = 0; i < m * n; i++)
			{
				parent[i] = i;
				depth[i] = 1;
				nWall[i] = true;
				eWall[i] = true;
			}
		}
		
		public void removeEWall(int node)
		{
			if (!eWall[node]) return;
			
			eWall[node] = false;
			join(node, node + n);
		}
		
		public void removeNWall(int node)
		{
			if (!nWall[node]) return;
			
			nWall[node] = false;
			join(node, node - 1);
		}
		
		public int getRoot(int node)
		{
			int currParent = parent[node];
			while (currParent != node)
			{
				node = currParent;
				currParent = parent[currParent];
			}
			return node;
		}
		
		public void join(int a, int b)
		{
			int aRoot = getRoot(a);
			int bRoot = getRoot(b);
			if (aRoot == bRoot) return;
			
			if (depth[aRoot] > depth[bRoot]) parent[bRoot] = aRoot;
			else 
			{
				parent[aRoot] = bRoot;
				if (depth[bRoot] == depth[aRoot]) depth[bRoot]++;
			}
		}
		
		public void generateRooms()
		{
			numRooms = 0;
			for (int i = 0; i < parent.length; i++) 
			{
				int root = getRoot(i);
				if (i == root) numRooms++;
				roomSize[root]++;
				room[i] = root;
			}
			
			for (int i = 0; i < parent.length; i++) 
			{
				roomSize[i] = roomSize[room[i]];
				if (roomSize[i] > maxSize) maxSize = roomSize[i];
			}
		}
		
		public String[] getWorstWall()
		{
			int newMaxSize = 0;
			int newMaxI = -1;
			int newMaxJ = -1;
			String newMaxDir = "E";
			
			for (int i = m - 1; i >= 0; i--) for (int j = 0; j < n; j++)
			{
				int index = i * n + j;
				if (i < m - 1 && eWall[index])
				{
					int newSize;
					if (room[index] != room[index + n]) newSize = roomSize[index] + roomSize[index + n];
					else newSize = roomSize[index];
					
					if (newSize >= newMaxSize) 
					{
						newMaxSize = newSize;
						newMaxI = i;
						newMaxJ = j;
						newMaxDir = "E";
					}
				}
				
				if (j > 0 && nWall[index])
				{
					int newSize;
					if (room[index] != room[index - 1]) newSize = roomSize[index] + roomSize[index - 1];
					else newSize = roomSize[index];
					
					if (newSize >= newMaxSize) 
					{
						newMaxSize = newSize;
						newMaxI = i;
						newMaxJ = j;
						newMaxDir = "N";
					}
				}
			}
			return new String[] { String.format("%s", newMaxSize), String.format("%s %s %s", newMaxJ + 1, newMaxI + 1, newMaxDir) };
		}
	}
}
