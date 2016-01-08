/*
ID: yuzhou.1
LANG: JAVA
PROB: nocows
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class nocows
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		in.close();
		
		int mod = 9901;
		
		int[][] ways = new int[k+1][n+1];
		ways[1][1] = 1;
		for (int h = 2; h <= k; h++)
		{
			int max = Math.log(n) / Math.log(2) < h ? n : (1 << h) - 1;
			for (int m = 2 * h - 1; m <= max; m += 2)
			{
				int maxL = Math.log(m - 1) / Math.log(2) < h - 1 ? m - 1 : (1 << (h - 1)) - 1;
				for (int l = 2 * h - 3; l <= maxL; l += 2)
				{
					int maxRH = Math.min((m - l + 1) / 2, h - 1);
					for (int rh = 1; rh <= maxRH; rh++)
					{
						ways[h][m] = (ways[h][m] + ways[h - 1][l] * ways[rh][m - l - 1]) % mod;
					}
				}
				
				for (int r = 2 * h - 3; r <= maxL; r += 2)
				{
					int maxLH = Math.min((m - r + 1) / 2, h - 2);
					for (int lh = 1; lh <= maxLH; lh++)
					{
						ways[h][m] = (ways[h][m] + ways[h - 1][r] * ways[lh][m - r - 1]) % mod;
					}
				}
			}
		}
		
		out.println(ways[k][n]);
		out.close();
	}
}
