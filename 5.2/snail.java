/*
ID: yuzhou.1
LANG: JAVA
TASK: snail
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class snail
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("snail.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int[][] isBarrier = new int[n + 2][n + 2];
		ArrayList<Barrier> barriers = new ArrayList<Barrier>();
		isBarrier[0][n + 1] = 0;
		barriers.add(new Barrier(0, -1));
		isBarrier[n + 1][0] = 1;
		barriers.add(new Barrier(-1, 0));
		
		for (int i = 0; i < b; i++)
		{
			String s = in.readLine();
			int x = s.charAt(0) - 'A';
			int y = Integer.parseInt(s.substring(1)) - 1;
			isBarrier[x][y] = barriers.size();
			barriers.add(new Barrier(x, y));
		}
		in.close();

		b += 2;
		
		Path[][] paths = new Path[b + 4 * n][8];
		for (int k = 0; k < b; k++)
		{
			int x = barriers.get(k).x;
			int y = barriers.get(k).y;
			
			if (y < n - 1)
			{
				int i = x + 1;
				while (i < n && isBarrier[i][y + 1] == 0) i++;
				if (i == n && isBarrier[n][y + 1] == 0)
				{
					isBarrier[n][y + 1] = b;
					barriers.add(new Barrier(n, y + 1));
					b++;
				}
				if (i > x + 1) paths[k][0] = new Path(x, y + 1, i - 1, y + 1, 0, 3, k, isBarrier[i][y + 1]);
				
				i = x - 1;
				while (i >= 0 && isBarrier[i][y + 1] == 0) i--;
				if (i == -1 && isBarrier[n + 1][y + 1] == 0)
				{
					isBarrier[n + 1][y + 1] = b;
					barriers.add(new Barrier(-1, y + 1));
					b++;
				}
				if (i < x - 1) paths[k][1] = new Path(x, y + 1, i + 1, y + 1, 0, 2, k, i == -1 ? isBarrier[n + 1][y + 1] : isBarrier[i][y + 1]);
			}
			
			if (y > 0)
			{
				int i = x + 1;
				while (i < n && isBarrier[i][y - 1] == 0) i++;
				if (i == n && isBarrier[n][y - 1] == 0)
				{
					isBarrier[n][y - 1] = b;
					barriers.add(new Barrier(n, y - 1));
					b++;
				}
				if (i > x + 1) paths[k][2] = new Path(x, y - 1, i - 1, y - 1, 1, 3, k, isBarrier[i][y - 1]);
				
				i = x - 1;
				while (i >= 0 && isBarrier[i][y - 1] == 0) i--;
				if (i == -1 && isBarrier[n + 1][y - 1] == 0)
				{
					isBarrier[n + 1][y - 1] = b;
					barriers.add(new Barrier(-1, y - 1));
					b++;
				}
				if (i < x - 1) paths[k][3] = new Path(x, y - 1, i + 1, y - 1, 1, 2, k, i == -1 ? isBarrier[n + 1][y - 1] : isBarrier[i][y - 1]);
			}
			
			if (x < n - 1)
			{
				int i = y + 1;
				while (i < n && isBarrier[x + 1][i] == 0) i++;
				if (i == n && isBarrier[x + 1][n] == 0)
				{
					isBarrier[x + 1][n] = b;
					barriers.add(new Barrier(x + 1, n));
					b++;
				}
				if (i > y + 1) paths[k][4] = new Path(x + 1, y, x + 1, i - 1, 2, 1, k, isBarrier[x + 1][i]);
				
				i = y - 1;
				while (i >= 0 && isBarrier[x + 1][i] == 0) i--;
				if (i == -1 && isBarrier[x + 1][n + 1] == 0)
				{
					isBarrier[x + 1][n + 1] = b;
					barriers.add(new Barrier(x + 1, -1));
					b++;
				}
				if (i < y - 1) paths[k][5] = new Path(x + 1, y, x + 1, i + 1, 2, 0, k, i == -1 ? isBarrier[x + 1][n + 1] : isBarrier[x + 1][i]);
			}
			
			if (x > 0)
			{
				int i = y + 1;
				while (i < n && isBarrier[x - 1][i] == 0) i++;
				if (i == n && isBarrier[x - 1][n] == 0)
				{
					isBarrier[x - 1][n] = b;
					barriers.add(new Barrier(x - 1, n));
					b++;
				}
				if (i > y + 1) paths[k][6] = new Path(x - 1, y, x - 1, i - 1, 3, 1, k, isBarrier[x - 1][i]);
				
				i = y - 1;
				while (i >= 0 && isBarrier[x - 1][i] == 0) i--;
				if (i == -1 && isBarrier[x - 1][n + 1] == 0)
				{
					isBarrier[x - 1][n + 1] = b;
					barriers.add(new Barrier(x - 1, -1));
					b++;
				}
				if (i < y - 1) paths[k][7] = new Path(x - 1, y, x - 1, i + 1, 3, 0, k, i == -1 ? isBarrier[x - 1][n + 1] : isBarrier[x - 1][i]);
			}
		}
		
		HashSet<Path> pathsSoFar = new HashSet<Path>();
		out.println(Math.max(longest(pathsSoFar, paths, 0, 0, barriers), longest(pathsSoFar, paths, 1, 2, barriers)) + 1);
		out.close();
	}
	
	public static int longest(HashSet<Path> pathsSoFar, Path[][] paths, int currBarrier, int currFace, ArrayList<Barrier> barriers)
	{
		//for (Path p : pathsSoFar) System.out.println(String.format("(%s, %s) to (%s, %s) {%s} [%s of (%s, %s) to %s of (%s, %s)]", p.sx, p.sy, p.ex, p.ey, 
			//p.length, p.faceFrom, barriers.get(p.from).x, barriers.get(p.from).y, p.faceTo, barriers.get(p.to).x, barriers.get(p.to).y));
		
		boolean atLeastOne = false;
		int longest = 0;
		Path one = paths[currBarrier][2 * currFace];
		if (one != null)
		{
			//System.out.println(String.format("Trying %s of %s...", one.faceFrom, one.from));
			boolean valid = true;
			for (Path p : pathsSoFar)
			{
				if ((p.to != one.from || p.faceTo != one.faceFrom) && one.intersects(p))
				{
					valid = false;
					break;
				}
			}
			
			if (valid)
			{
				atLeastOne = true;
				pathsSoFar.add(one);
				longest = longest(pathsSoFar, paths, one.to, one.faceTo, barriers) + one.length;
				pathsSoFar.remove(one);
			}
		}
		
		Path two = paths[currBarrier][2 * currFace + 1];
		if (two != null)
		{
			//System.out.println(String.format("Trying %s of %s...", two.faceFrom, two.from));
			boolean valid = true;
			for (Path p : pathsSoFar)
			{
				if ((p.to != two.from || p.faceTo != two.faceFrom) && two.intersects(p))
				{
					valid = false;
					break;
				}
			}
			
			if (valid)
			{
				atLeastOne = true;
				pathsSoFar.add(two);
				longest = Math.max(longest, longest(pathsSoFar, paths, two.to, two.faceTo, barriers) + two.length);
				pathsSoFar.remove(two);
			}
		}

		//System.out.println(longest);
		//System.out.println();
		
		if (!atLeastOne)
		{
			int partialOne = Integer.MAX_VALUE;
			int partialTwo = Integer.MAX_VALUE;
			if (currFace < 2)
			{
				if (one != null)
				{
					for (Path p : pathsSoFar)
					{
						if ((p.to != one.from || p.faceTo != one.faceFrom) && one.intersects(p) && p.sx - one.sx - 1 < partialOne) partialOne = p.sx - one.sx - 1;
					}
				}
				else partialOne = 0;
				
				if (two != null)
				{
					for (Path p : pathsSoFar)
					{
						if ((p.to != two.from || p.faceTo != two.faceFrom) && two.intersects(p) && two.ex - p.ex - 1 < partialTwo) partialTwo = two.ex - p.ex - 1;
					}
				}
				else partialTwo = 0;
			}
			else
			{
				if (one != null)
				{
					for (Path p : pathsSoFar)
					{
						if ((p.to != one.from || p.faceTo != one.faceFrom) && one.intersects(p) && p.sy - one.sy - 1 < partialOne) partialOne = p.sy - one.sy - 1;
					}
				}
				else partialOne = 0;
				
				if (two != null)
				{
					for (Path p : pathsSoFar)
					{
						if ((p.to != two.from || p.faceTo != two.faceFrom) && two.intersects(p) && two.ex - p.ex - 1 < partialTwo) partialTwo = two.ex - p.ex - 1;
					}
				}
				else partialTwo = 0;
			}
			
			//System.out.println("(+" + Math.max(partialOne, partialTwo) + ")");
			longest += Math.max(partialOne, partialTwo);
		}
		return longest;
	}
	
	static class Barrier
	{
		public int x;
		public int y;
		
		public Barrier(int xCoord, int yCoord)
		{
			x = xCoord;
			y = yCoord;
		}
	}
	
	static class Path
	{
		public int sx;
		public int sy;
		public int ex;
		public int ey;
		public char dir;
		public int length;
		public int faceFrom;
		public int faceTo;
		public int from;
		public int to;
		
		public Path(int sx, int sy, int ex, int ey, int faceFrom, int faceTo, int from, int to)
		{
			this.faceFrom = faceFrom;
			this.faceTo = faceTo;
			this.from = from;
			this.to = to;
			
			if (sy == ey)
			{
				if (sx < ex)
				{
					this.sx = sx;
					this.ex = ex;
				}
				else
				{
					this.ex = sx;
					this.sx = ex;
				}
				this.sy = sy;
				this.ey = ey;
				dir = 'x';
				length = this.ex - this.sx;
			}
			else
			{
				if (sy < ey)
				{
					this.sy = sy;
					this.ey = ey;
				}
				else
				{
					this.ey = sy;
					this.sy = ey;
				}
				this.sx = sx;
				this.ex = ex;
				dir = 'y';
				length = this.ey - this.sy;
			}
		}
		
		public boolean intersects(Path p)
		{
			if (dir == 'x')
			{
				return sy <= p.ey && sy >= p.sy && p.sx <= ex && p.sx >= sx;
			}
			else
			{
				return sx <= p.ex && sx >= p.sx && p.sy <= ey && p.sy >= sy;
			}
		}
	}
}
