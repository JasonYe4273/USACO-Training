/*
ID: yuzhou.1
LANG: JAVA
TASK: bigbrn
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bigbrn
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("bigbrn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		Paths p = new Paths(n);
		int[] treeX = new int[t];
		int[] treeY = new int[t];
		for (int i = 0; i < t; i++)
		{
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			treeX[i] = x;
			treeY[i] = y;
			p.addTree(x, y);
		}
		in.close();
		
		int max = 0;
		for (int i = 0; i < t; i++)
		{
			max = p.maxAtTree(treeX[i], treeY[i], max);
		}
		
		// p.print();
		out.println(max);
		out.close();
	}
	
	static class Paths
	{
		public int n;
		public ArrayList<VertPath>[] vertPaths;
		public int[][] vertIdx;
		
		@SuppressWarnings("unchecked")
		public Paths(int n)
		{
			this.n = n;
			
			vertPaths = new ArrayList[n];
			for (int i = 0; i < n; i++)
			{
				vertPaths[i] = new ArrayList<VertPath>();
				vertPaths[i].add(new VertPath(i, 0, n - 1));
			}
			
			vertIdx = new int[n][n];
		}
		
		public void addTree(int x, int y)
		{
			int vert = vertIdx[x][y];
			if (vert != -1)
			{
				VertPath vertPath = vertPaths[x].get(vert);
				if (y == vertPath.sy)
				{
					vertPath.sy++;
					vertPath.length--;
				}
				else if (y == vertPath.ey)
				{
					vertPath.ey--;
					vertPath.length--;
				}
				else
				{
					VertPath aPath = new VertPath(x, vertPath.sy, y - 1);
					VertPath bPath = new VertPath(x, y + 1, vertPath.ey);
					vertPaths[x].set(vert, aPath);

					int bIdx = vertPaths[x].size();
					vertPaths[x].add(bPath);
					for (int i = y + 1; i <= vertPath.ey; i++)
					{
						vertIdx[x][i] = bIdx;
					}
				}
				
				vertIdx[x][y] = -1;
			}
		}
	
		public int maxAtTree(int x, int y, int max)
		{
			return Math.max(max, Math.max(maxAtLeft(x, y, max), maxAtRight(x, y, max)));
		}
		
		public int maxAtRight(int x, int y, int max)
		{
			if (n - x - 1 <= max) return max;
			
			int sy = 0;
			int ey = n;
			for (int i = 1; i <= max; i++)
			{
				int idx = vertIdx[x + i][y];
				if (idx == -1) return max;

				VertPath p = vertPaths[x + i].get(idx);
				if (p.ey < ey) ey = p.ey;
				if (p.sy > sy) sy = p.sy;
				if (ey - sy + 1 < i) return max;
			}
			
			for (int i = max + 1; i <= n - x - 1; i++)
			{
				int idx = vertIdx[x + i][y];
				if (idx == -1) return i - 1;

				VertPath p = vertPaths[x + i].get(idx);
				if (p.ey < ey) ey = p.ey;
				if (p.sy > sy) sy = p.sy;
				if (ey - sy + 1 < i) return i - 1;
			}
			
			return n - x - 1;
		}
		
		public int maxAtLeft(int x, int y, int max)
		{
			if (x <= max) return max;
			
			int ey = n;
			int sy = 0;
			for (int i = 1; i <= max; i++)
			{
				int idx = vertIdx[x - i][y];
				if (idx == -1) return max;
				
				VertPath p = vertPaths[x - i].get(idx);
				if (p.ey < ey) ey = p.ey;
				if (p.sy > sy) sy = p.sy;
				if (ey - sy + 1 < i) return max;
			}
			
			for (int i = max + 1; i <= x; i++)
			{
				int idx = vertIdx[x - i][y];
				if (idx == -1) return i - 1;

				VertPath p = vertPaths[x - i].get(idx);
				if (p.ey < ey) ey = p.ey;
				if (p.sy > sy) sy = p.sy;
				if (ey - sy + 1 < i) return i - 1;
			}
			
			return x;
		}
		
		public void print()
		{
			System.out.println("Trees:");
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					if (vertIdx[i][j] < 0) System.out.println(i + " " + j);
				}
			}
			
			System.out.println("vertIdx:");
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					System.out.print(vertIdx[i][j] + " ");
					if (vertIdx[i][j] >= 0 && vertIdx[i][i] < 10) System.out.print(" ");
				}
				System.out.println();
			}
			
			System.out.println("Trees:");
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					System.out.print(vertIdx[i][j] < 0 ? 1 : 0);
				}
				System.out.println();
			}
			
			System.out.println("Vert lengths:");
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < vertPaths[i].size(); j++)
				{
					System.out.print(vertPaths[i].get(j).length + " ");
					if (vertPaths[i].get(j).length >= 0 && vertPaths[i].get(j).length < 10) System.out.print(" ");
				}
				System.out.println();
			}
		}
	}
	
	static class HorizPath
	{
		public int y;
		public int sx;
		public int ex;
		public int length;
		public HorizPath pathRight;
		public HorizPath pathLeft;
		
		public HorizPath(int y, int sx, int ex)
		{
			this.y = y;
			this.sx = sx;
			this.ex = ex;
			length = ex - sx + 1;
		}
		
		public boolean contains(int x, int y)
		{
			return this.y == y && sx <= x && ex >= x;
		}
		
		public boolean contains(int x)
		{
			return sx <= x && ex >= x;
		}
	}
	
	static class VertPath
	{
		public int x;
		public int sy;
		public int ey;
		public int length;
		public VertPath pathBelow;
		public VertPath pathAbove;
		
		public VertPath(int x, int sy, int ey)
		{
			this.x = x;
			this.sy = sy;
			this.ey = ey;
			length = ey - sy + 1;
		}
		
		public boolean contains(int x, int y)
		{
			return this.x == x && sy <= y && ey >= y;
		}
		
		public boolean contains(int y)
		{
			return sy <= y && ey >= y;
		}
	}
}
