/*
ID: yuzhou.1
LANG: JAVA
TASK: money
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class money
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int v = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] vals = new int[v];
		int index = 0;
		while (index < v)
		{
			st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens())
			{
				vals[index++] = Integer.parseInt(st.nextToken());
				if (index == v) break;
			}
		}
		in.close();
		
		long[] nums = new long[n + 1];
		nums[0] = 1;
		for (int val : vals)
		{
			for (int i = 0; i < n; i++)
			{
				int next = i + val;
				if (next <= n) nums[next] +=nums[i];
			}
		}
		
		out.println(nums[n]);
		out.close();
	}
}
