/*
ID: yuzhou.1
LANG: JAVA
TASK: kimbits
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class kimbits
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		long i = Long.parseLong(st.nextToken());
		in.close();
		
		// num[# digits][max # ones]
		int[][] num = new int[n + 1][l + 1];
		for (int k = 0; k <= l; k++) num[0][k] = 1;
		for (int j = 1; j <= n; j++)
		{
			num[j][0] = 1;
			for (int k = 1; k <= l; k++)
			{
				num[j][k] = (k <= j ? num[j - 1][k - 1] + num[j - 1][k] : num[j][k - 1]);
			}
		}
		
		out.println(getString(n, l, i, num));
		out.close();
	}
	
	public static String getString(int n, int l, long i, int[][] num)
	{
		if (n == 0) return "";
		if (num[n - 1][l] < i)
		{
			return "1" + getString(n - 1, l - 1, i - num[n - 1][l], num);
		}
		else
		{
			return "0" + getString(n - 1, l, i, num);
		}
	}
}
