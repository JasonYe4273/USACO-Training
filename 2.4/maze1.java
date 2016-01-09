/*
ID: yuzhou.1
LANG: JAVA
TASK: maze1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class maze1
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		
		final int[] DIR0 = { -1, 0, 1, 0 };
		final int[] DIR1 = { 0, 1, 0, -1 };
		final int UP = 0;
		final int RIGHT = 1;
		final int DOWN = 2;
		final int LEFT = 3;
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		boolean[][][] connected = new boolean[h][w][4];
		int e = 0;
		int[][] exits = new int[2][2];
		
		char[] vWall = in.readLine().toCharArray();
		for (int i = 1; i < vWall.length; i += 2)
		{
			if (vWall[i] == ' ')
			{
				exits[e++] = new int[] { 0, i / 2 };
			}
		}
		
		for (int i = 0; i < h; i++)
		{
			char[] hWall = in.readLine().toCharArray();
			if (hWall[0] == ' ') exits[e++] = new int[] { i, 0 };
			if (hWall[2 * w] == ' ') exits[e++] = new int[] { i, w - 1 };
			for (int j = 1; j < w; j++)
			{
				if (hWall[2 * j] == ' ')
				{
					connected[i][j][LEFT] = true;
					connected[i][j - 1][RIGHT] = true;
				}
			}
			
			if (i == h - 1) continue;
			vWall = in.readLine().toCharArray();
			for (int j = 0; j < w; j++)
			{
				if (vWall[2 * j + 1] == ' ')
				{
					connected[i][j][DOWN] = true;
					connected[i + 1][j][UP] = true;
				}
			}
		}
		
		vWall = in.readLine().toCharArray();
		for (int i = 1; i < vWall.length; i += 2)
		{
			if (vWall[i] == ' ')
			{
				exits[e++] = new int[] { h - 1, i / 2 };
			}
		}
		in.close();
		
		int[][][] distances = new int[2][h][w];
		for (int n = 0; n < 2; n++)
		{
			boolean[][] visited = new boolean[h][w];
			for (int i = 0; i < h; i++) for (int j = 0; j < w; j++) distances[n][i][j] = Integer.MAX_VALUE;
			distances[n][exits[n][0]][exits[n][1]] = 1;
			
			for (int k = 0; k < h * w; k++)
			{
				int min = Integer.MAX_VALUE;
				int[] idx = { 0, 0 };
				for (int i = 0; i < h; i++)
				{
					for (int j = 0; j < w; j++)
					{
						if (!visited[i][j] && distances[n][i][j] < min)
						{
							min = distances[n][i][j];
							idx[0] = i;
							idx[1] = j;
						}
					}
				}
				
				visited[idx[0]][idx[1]] = true;
				for (int d = 0; d < DIR0.length; d++)
				{
					int[] newIdx = { idx[0] + DIR0[d], idx[1] + DIR1[d] };
					if (connected[idx[0]][idx[1]][d] && min + 1 < distances[n][newIdx[0]][newIdx[1]])
					{
						distances[n][newIdx[0]][newIdx[1]] = min + 1;
					}
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				int min = Math.min(distances[0][i][j], distances[1][i][j]);
				if (min > max) max = min;
			}
		}
		
		out.println(max);
		out.close();
	}
}
