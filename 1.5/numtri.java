/*
ID: yuzhou.1
LANG: JAVA
TASK: numtri
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class numtri
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		
		int r = Integer.parseInt(in.readLine());
		int[][] nums = new int[r][];
		int[][] sums = new int[r][];
		nums[0] = new int[]{ Integer.parseInt(in.readLine()) };
		sums[0] = nums[0];
		for (int i = 1; i < r; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			nums[i] = new int[i + 1];
			sums[i] = new int[i + 1];
			
			for (int j = 0; j < i + 1; j++) nums[i][j] = Integer.parseInt(st.nextToken());
			
			sums[i][0] = sums[i - 1][0] + nums[i][0];
			for (int j = 1; j < i; j++)
			{
				sums[i][j] = Math.max(sums[i - 1][j - 1], sums[i - 1][j]) + nums[i][j];
			}
			sums[i][i] = sums[i - 1][i - 1] + nums[i][i];
		}
		in.close();
		
		int max = sums[r - 1][0];
		for (int i = 1; i < r; i++) if (sums[r - 1][i] > max) max = sums[r - 1][i];
		
		out.println(max);
		out.close();
	}
}