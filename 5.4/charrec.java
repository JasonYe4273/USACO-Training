/*
ID: yuzhou.1
LANG: JAVA
TASK: charrec
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class charrec
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader font = new BufferedReader(new FileReader("font.in"));
		
		int f = Integer.parseInt(font.readLine());
		int[][] chars = new int[f / 20][20];
		for (int i = 0; i < f / 20; i++)
		{
			for (int j = 0; j < 20; j++)
			{
				int num = 0;
				char[] c = font.readLine().toCharArray();
				for (int k = 0; k < 20; k++)
				{
					num <<= 1;
					if (c[k] == '1') num++;
				}
				chars[i][j] = num;
			}
		}
		font.close();
		
		String[] str = new String[27];
		str[0] = " ";
		for (int i = 0; i < 26; i++)
		{
			str[i + 1] = String.valueOf((char) ('a' + i));
		}
		
		BufferedReader in = new BufferedReader(new FileReader("charrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("charrec.out")));
		
		int n = Integer.parseInt(in.readLine());
		int[] lines = new int[n];
		for (int i = 0; i < n; i++)
		{
			int num = 0;
			char[] c = in.readLine().toCharArray();
			for (int j = 0; j < 20; j++)
			{
				num <<= 1;
				if (c[j] == '1') num++;
			}
			lines[i] = num;
		}
		in.close();
		
		boolean[] reachable = new boolean[n];
		reachable[0] = true;
		for (int i = 0; i < n - 19; i++)
		{
			if (reachable[i])
			{
				reachable[i + 19] = true;
				if (i + 20 < n) reachable[i + 20] = true;
				if (i + 21 < n) reachable[i + 21] = true;
			}
		}
		
		String[] prefix = new String[n + 1];
		int[] error = new int[n + 1];
		for (int i = 0; i < n; i++) error[i] = Integer.MAX_VALUE;
		for (int i = 0; i <= n; i++) prefix[i] = "";
		error[0] = 0;
		
		boolean blank = false;
		for (int i = 19; i <= n; i++)
		{
			if (!reachable[n - i])
			{
				if (!blank)
				{
					int min = Integer.MAX_VALUE;
					String s = "";
					for (int j = i - 10; j < i; j++)
					{
						if (error[j] < min)
						{
							min = error[j];
							s = prefix[j];
						}
					}
					
					for (int j = i - 10; j < i; j++)
					{
						if (!prefix[j].equals(s)) error[j] = Integer.MAX_VALUE;
					}
					blank = true;
				}
				continue;
			}
			
			blank = false;
			
			String pre = "";
			int dist = Integer.MAX_VALUE;
			
			if (error[i - 19] != Integer.MAX_VALUE)
			{
				int[] to = Arrays.copyOfRange(lines, i - 19, i);
				for (int j = 0; j < 27; j++)
				{
					int d = minDistWithMissing(chars[j], to);// + error[i - 19];
					if (d < dist)
					{
						dist = d;
						pre = prefix[i - 19] + str[j];
					}
				}
			}
			
			if (i > 19 && error[i - 20] != Integer.MAX_VALUE)
			{
				int[] to = Arrays.copyOfRange(lines, i - 20, i);
				for (int j = 0; j < 27; j++)
				{
					int d = dist(chars[j], to);// + error[i - 20];
					if (d < dist)
					{
						dist = d;
						pre = prefix[i - 20] + str[j];
					}
				}
			}
			
			if (i > 20 && error[i - 21] != Integer.MAX_VALUE)
			{
				int[] to = Arrays.copyOfRange(lines, i - 21, i);
				for (int j = 0; j < 27; j++)
				{
					int d = minDistWithDuplicate(chars[j], to);// + error[i - 21];
					if (d < dist)
					{
						dist = d;
						pre = prefix[i - 21] + str[j];
					}
				}
			}
			
			error[i] = dist;
			prefix[i] = pre;
			
			if (dist == Integer.MAX_VALUE)
			{
				if (!blank)
				{
					int min = Integer.MAX_VALUE;
					String s = "";
					for (int j = i - 10; j < i; j++)
					{
						if (error[j] < min)
						{
							min = error[j];
							s = prefix[j];
						}
					}
					
					for (int j = i - 10; j < i; j++)
					{
						if (!prefix[j].equals(s)) error[j] = Integer.MAX_VALUE;
					}
					blank = true;
				}
			}
			else blank = false;
		}
		
		for (int i = 0; i <= n; i++)
		{
			System.out.println(i + ": " + prefix[i]);
		}
		out.println(prefix[n]);
		out.close();
	}
	
	public static int minDistWithDuplicate(int[] actual, int[] to)
	{
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < to.length - 1; i++)
		{
			int dist = 0;
			
			for (int j = 0; j < i; j++)
			{
				dist += dist(actual[j], to[j]);
				//if (dist > 120) continue;
			}
			
			dist += Math.min(dist(actual[i], to[i]), dist(actual[i], to[i + 1]));
			//if (dist > 120) continue;
			
			for (int j = i + 1; j < actual.length; j++)
			{
				dist += dist(actual[j], to[j + 1]);
				//if (dist > 120) continue;
			}

			if (dist > 120) continue;
			if (dist < min) min = dist;
		}
		return min;
	}
	
	public static int minDistWithMissing(int[] actual, int[] to)
	{
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < to.length - 1; i++)
		{
			int dist = 0;
				
			for (int j = 0; j < i; j++)
			{
				dist += dist(actual[j], to[j]);
				//if (dist > 114) continue;
			}
			
			for (int j = i + 1; j < actual.length; j++)
			{
				dist += dist(actual[j], to[j - 1]);
				//if (dist > 114) continue;
			}

			if (dist > 114) continue;
			if (dist < min) min = dist;
		}
		return min;
	}
	
	public static int dist(int[] actual, int[] to)
	{
		int dist = 0;
		for (int i = 0; i < actual.length; i++)
		{
			dist += dist(actual[i], to[i]);
		}
		return dist > 120 ? Integer.MAX_VALUE : dist;
	}
	
	public static int dist(int a, int b)
	{
		int dist = 0;
		while (a + b > 0)
		{
			if (a % 2 != b % 2) dist++;
			a >>= 1;
			b >>= 1;
		}
		return dist;
	}
}
