package gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dream
{
	public static final int RED = 0;
	public static final int PINK = 1;
	public static final int ORANGE = 2;
	public static final int BLUE = 3;
	public static final int PURPLE = 4;
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int[] X_DIR = { 0, 0, -1, 1 };
	public static final int[] Y_DIR = { -1, 1, 0, 0 };
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("dream.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> notVisited = new ArrayList<int[]>();
		int[][] tiles = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		int[][] weights = new int[n][m];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++)
			{
				tiles[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = false;
				weights[i][j] = Integer.MAX_VALUE;
				if (tiles[i][j] % 3 != 0) notVisited.add(new int[]{ i, j });
			}
		}
		weights[0][0] = 0;
		in.close();
		
		boolean done = false;
		int prevX = 0;
		int prevY = 0;
		int currX = 0;
		int currY = 0;
		while (!done)
		{
			visited[currX][currY] = true;
			prevX = currX;
			prevY = currY;
			ArrayList<int[]> connected = new ArrayList<int[]>();
			int[] temp;
			if (currX < n - 1)
			{
				temp = tryConnect(currX, currY, n, m, RIGHT, tiles);
				if (temp != null) connected.add(temp);
			}
			if (currX > 0)
			{
				temp = tryConnect(currX, currY, n, m, LEFT, tiles);
				if (temp != null) connected.add(temp);
			}
			if (currY < m - 1)
			{
				temp = tryConnect(currX, currY, n, m, DOWN, tiles);
				if (temp != null) connected.add(temp);
			}
			if (currY > 0)
			{
				temp = tryConnect(currX, currY, n, m, UP, tiles);
				if (temp != null) connected.add(temp);
			}
			
			for (int[] node : connected)
			{
				if (!visited[node[0]][node[1]] && weights[node[0]][node[1]] > weights[currX][currY] + node[2])
				{
					weights[node[0]][node[1]] = weights[currX][currY] + node[2];
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int[] node : connected)
			{
				if (!visited[node[0]][node[1]] && weights[node[0]][node[1]] < min)
				{
					min = weights[node[0]][node[1]];
					currX = node[0];
					currY = node[1];
				}
			}
			
			if (prevX == currX && prevY == currY)
			{
				min = Integer.MAX_VALUE;
				for (int[] not : notVisited)
				{
					if (!visited[not[0]][not[1]] && weights[not[0]][not[1]] < min)
					{
						min = weights[not[0]][not[1]];
						currX = not[0];
						currY = not[1];
					}
				}
				if (min == Integer.MAX_VALUE) done = true;
			}
		}
		
		out.println(weights[n - 1][m - 1] == Integer.MAX_VALUE ? -1 : weights[n - 1][m - 1]);
		out.close();
	}
	
	public static int[] tryConnect(int x, int y, int n, int m, int dir, int[][] types)
	{
		if (types[x][y] % 3 == 0) return null;
		int x2 = x + X_DIR[dir];
		int y2 = y + Y_DIR[dir];
		int numMove = 0;
		while (x2 >= 0 && x2 < n && y2 >= 0 && y2 < m && types[x2][y2] == PURPLE)
		{
			x2 += X_DIR[dir];
			y2 += Y_DIR[dir];
			numMove++;
		}
		if (x2 == -1) x2 = 0;
		else if (x2 == n) x2 = n - 1;
		else if (y2 == -1) y2 = 0;
		else if (y2 == m) y2 = m - 1;
		else if (types[x2][y2] % 3 == 0)
		{
			x2 -= X_DIR[dir];
			y2 -= Y_DIR[dir];
		}
		else
		{
			numMove++;
		}
		return new int[]{ x2, y2, numMove };
	}
}

/*import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dream
{
	public static final int RED = 0;
	public static final int PINK = 1;
	public static final int ORANGE = 2;
	public static final int BLUE = 3;
	public static final int PURPLE = 4;
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int[] X_DIR = { 0, 0, -1, 1 };
	public static final int[] Y_DIR = { -1, 1, 0, 0 };
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("dream.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> notVisited = new ArrayList<int[]>();
		int[][] tiles = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		int[][] weights = new int[n][m];
		OrangeNode[][] orange = new OrangeNode[n][m];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++)
			{
				tiles[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = false;
				if (tiles[i][j] == ORANGE) orange[i][j] = new OrangeNode(true, 1);
				else orange[i][j] = new OrangeNode(false, 0);
				weights[i][j] = Integer.MAX_VALUE;
				if (tiles[i][j] != RED) notVisited.add(new int[]{ i, j });
			}
		}
		weights[0][0] = 0;
		in.close();
		
		boolean done = false;
		int prevX = 0;
		int prevY = 0;
		int currX = 0;
		int currY = 0;
		boolean currOrange = orange[0][0].orange;
		while (!done)
		{
			visited[currX][currY] = true;
			prevX = currX;
			prevY = currY;
			ArrayList<int[]> connected = new ArrayList<int[]>();
			int[] temp;
			if (currX < n - 1)
			{
				temp = tryConnect(currX, currY, n, m, RIGHT, tiles, currOrange);
				if (temp != null) connected.add(temp);
			}
			if (currX > 0)
			{
				temp = tryConnect(currX, currY, n, m, LEFT, tiles, currOrange);
				if (temp != null) connected.add(temp);
			}
			if (currY < m - 1)
			{
				temp = tryConnect(currX, currY, n, m, DOWN, tiles, currOrange);
				if (temp != null) connected.add(temp);
			}
			if (currY > 0)
			{
				temp = tryConnect(currX, currY, n, m, UP, tiles, currOrange);
				if (temp != null) connected.add(temp);
			}
			
			for (int[] node : connected)
			{
				if (!visited[node[0]][node[1]] && weights[node[0]][node[1]] > weights[currX][currY] + node[2])
				{
					weights[node[0]][node[1]] = weights[currX][currY] + node[2];
					OrangeNode prev = orange[node[0]][node[1]].prev;
					if (prev != null)
					{
						int index = prev.next.indexOf(orange[node[0]][node[1]]);
						prev.next.remove(index);
						prev.purple.remove(index);
					}
					orange[node[0]][node[1]].prev = orange[currX][currY];
					orange[currX][currY].next.add(orange[node[0]][node[1]]);
					orange[currX][currY].purple.add(node[3] == 1);
				}
				orange[currX][currY].update(currOrange);
			}
			
			int min = Integer.MAX_VALUE;
			for (int[] node : connected)
			{
				if (!visited[node[0]][node[1]] && weights[node[0]][node[1]] < min)
				{
					min = weights[node[0]][node[1]];
					currX = node[0];
					currY = node[1];
					currOrange = orange[node[0]][node[1]].orange;
				}
			}
			
			if (prevX == currX && prevY == currY)
			{
				min = Integer.MAX_VALUE;
				for (int[] not : notVisited)
				{
					if (!visited[not[0]][not[1]] && weights[not[0]][not[1]] < min)
					{
						min = weights[not[0]][not[1]];
						currX = not[0];
						currY = not[1];
						currOrange = orange[not[0]][not[1]].orange;
					}
				}
				if (min == Integer.MAX_VALUE) done = true;
			}
		}
		
		for (int i = 0; i < n; i++)
		{
			for (int j= 0; j < m; j++)
			{
				System.out.println(i + " " + j + ": " + orange[i][j].orange);
			}
		}
		
		out.println(weights[n - 1][m - 1] == Integer.MAX_VALUE ? -1 : weights[n - 1][m - 1]);
		out.close();
	}
	
	public static int[] tryConnect(int x, int y, int n, int m, int dir, int[][] types, boolean currOrange)
	{
		if (types[x][y] == RED || (types[x][y] == BLUE && !currOrange)) return null;
		int x2 = x + X_DIR[dir];
		int y2 = y + Y_DIR[dir];
		int numMove = 0;
		boolean purple = false;
		while (x2 >= 0 && x2 < n && y2 >= 0 && y2 < m && types[x2][y2] == PURPLE)
		{
			x2 += X_DIR[dir];
			y2 += Y_DIR[dir];
			numMove++;
			purple = true;
		}
		if (x2 == -1) x2 = 0;
		else if (x2 == n) x2 = n - 1;
		else if (y2 == -1) y2 = 0;
		else if (y2 == m) y2 = m - 1;
		else if (types[x2][y2] == RED || types[x2][y2] == BLUE && !currOrange)
		{
			x2 -= X_DIR[dir];
			y2 -= Y_DIR[dir];
		}
		else
		{
			numMove++;
		}
		if (numMove == 0) return null;
		return new int[]{ x2, y2, numMove, purple ? 1 : 0 };
	}
	
	public static class OrangeNode
	{
		public ArrayList<OrangeNode> next;
		public ArrayList<Boolean> purple;
		public OrangeNode prev;
		public boolean orange;
		public int change;
		
		public OrangeNode(boolean o, int c)
		{
			orange = o;
			change = c;
			next = new ArrayList<OrangeNode>();
			purple = new ArrayList<Boolean>();
			prev = null;
		}
		
		public void update(boolean prevOrange)
		{
			if (change == 0) orange = prevOrange;
			for (int i = 0; i < next.size(); i++)
			{
				if (purple.get(i)) next.get(i).update(false);
				else next.get(i).update(orange);
			}
		}
	}
}*/
