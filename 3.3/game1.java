/*
ID: yuzhou.1
LANG: JAVA
TASK: game1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class game1
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		int place = 0;
		int[] board = new int[n];
		String line = in.readLine();
		while (line != null)
		{
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				board[place++] = Integer.parseInt(st.nextToken());
			}
			line = in.readLine();
		}
		in.close();

		int[] cumul = new int[n + 1];
		cumul[0] = 0;
		for (int i = 1; i <= n; i++) cumul[i] = cumul[i - 1] + board[i - 1];
		
		int[][][] subset = new int[n][n][2];
		for (int i = 0; i < n; i++) subset[i][i] = new int[]{ board[i], 0 };
		for (int num = 1; num <= n; num++)
		{
			for (int i = num; i < n; i++)
			{
				subset[i - num][i] = new int[2];
				subset[i - num][i][0] = Math.max(subset[i - num + 1][i][1] + board[i - num], subset[i - num][i - 1][1] + board[i]);
				subset[i - num][i][1] = cumul[i + 1] - cumul[i - num] - subset[i - num][i][0];
			}
		}
		
		out.println(subset[0][n - 1][0] + " " + subset[0][n - 1][1]);
		out.close();
	}
}
