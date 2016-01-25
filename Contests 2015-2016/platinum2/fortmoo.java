package platinum2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class fortmoo
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("fortmoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fortmoo.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		boolean[][] swamp = new boolean[n][m];
		for (int i = 0; i < n; i++)
		{
			char[] cArray = in.readLine().toCharArray();
			for (int j = 0; j < m; j++) swamp[i][j] = cArray[j] == 'X';
		}
		in.close();
		
		int[][] maxDown = new int[n][m];
		int[][] maxRight = new int[n][m];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				for (maxDown[i][j] = 0; maxDown[i][j] < n - i && !swamp[i + maxDown[i][j]][j]; maxDown[i][j]++);
				for (maxRight[i][j] = 0; maxRight[i][j] < m - j && !swamp[i][j + maxRight[i][j]]; maxRight[i][j]++);
			}
		}
		
		int maxArea = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				int area = maxDown[i][j] * maxRight[i][j];
				if (area <= maxArea) continue;
				
				area = 0;
				for (int d = maxDown[i][j]; d > 0; d--)
				{
					for (int r = Math.min(maxRight[i][j], maxRight[i + d - 1][j]); r > 0; r--)
					{
						if (maxDown[i][j + r - 1] >= d)
						{
							if (r * d > area)
							{
								area = r * d;
							}
							break;
						}
					}
				}
				
				if (area > maxArea) maxArea = area;
			}
		}
		
		out.println(maxArea);
		out.close();
	}
}
