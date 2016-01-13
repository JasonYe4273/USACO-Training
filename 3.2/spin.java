/*
ID: yuzhou.1
LANG: JAVA
TASK: spin
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class spin
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		
		int n = 5;
		int maxDeg = 360;
		
		int[] speed = new int[n];
		int[][] wedgeStart = new int[n][];
		int[][] wedgeEnd = new int[n][];
		for (int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			speed[i] = Integer.parseInt(st.nextToken()) % maxDeg;
			
			int w = Integer.parseInt(st.nextToken());
			wedgeStart[i] = new int[w];
			wedgeEnd[i] = new int[w];
			for (int j = 0; j < w; j++)
			{
				wedgeStart[i][j] = Integer.parseInt(st.nextToken()) % maxDeg;
				wedgeEnd[i][j] = (wedgeStart[i][j] + Integer.parseInt(st.nextToken())) % maxDeg;
			}
		}
		in.close();
		
		boolean[][] clear = new boolean[n][maxDeg];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < wedgeStart[i].length; j++)
			{
				int deg = wedgeStart[i][j];
				while (deg != wedgeEnd[i][j])
				{
					clear[i][deg] = true;
					deg++;
					if (deg == maxDeg) deg = 0;
				}
				clear[i][deg] = true;
			}
		}
		
		for (int sec = 0; sec < maxDeg; sec++)
		{
			for (int d = 0; d < maxDeg; d++)
			{
				boolean allClear = true;
				for (int i = 0; i < n; i++)
				{
					if (!clear[i][(((d - sec * speed[i]) % maxDeg) + maxDeg) % maxDeg]) allClear = false;
				}
				if (allClear)
				{
					out.println(sec);
					out.close();
					return;
				}
			}
		}
		
		out.println("none");
		out.close();
	}
}
