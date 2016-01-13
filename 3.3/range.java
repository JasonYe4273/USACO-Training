/*
ID: yuzhou.1
LANG: JAVA
TASK: range
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class range
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		boolean[][] good = new boolean[n + 1][n + 1];
		for (int i = 0; i < n; i++)
		{
			char[] cArray = in.readLine().toCharArray();
			for (int j = 0; j < n; j++)
			{
				good[i][j] = cArray[j] == '1';
			}
		}
		for (int i = 0; i <= n; i++)
		{
			good[i][n] = false;
			good[n][i] = false;
		}
		in.close();
		
		int[][] bigSquare = new int[n][n];
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++)
		{
			if (!good[i][j]) continue;
			int s;
			boolean exists = true;
			for (s = i * j == 0 ? 1 : Math.max(bigSquare[i - 1][j - 1] - 1, 1); s <= n; s++)
			{
				int si = i + s - 1;
				for (int sj = j; sj < j + s; sj++)
				{
					if (!good[si][sj]) exists = false;
				}
				
				int sj = j + s - 1;
				for (si = i; si < i + s; si++)
				{
					if (!good[si][sj]) exists = false;
				}
				
				if (!exists) break;
				bigSquare[i][j] = s;
			}
		}
		
		int[] numSquare = new int[n + 1];
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++)
		{
			for (int s = 2; s <= bigSquare[i][j]; s++) numSquare[s]++;
		}
		
		for (int s = 2; s <= n; s++)
		{
			if (numSquare[s] > 0) out.println(s + " " + numSquare[s]);
		}
		out.close();
	}
}
