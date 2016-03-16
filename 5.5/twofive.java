/*
ID: yuzhou.1
LANG: JAVA
TASK: twofive
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class twofive
{
	public static final int n = 5;
	public static final char start = 'A';
	public static final char end = 'Y';
	
	public static int[] numWays;
	public static char[][] grid;
	public static int[] numPerRow;
	public static boolean[] used;
	
	public static void main(String[] args) throws IOException
	{
		grid = new char[n][n];
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) grid[i][j] = end;
		numPerRow = new int[n];
		used = new boolean[n * n];
		
		int[] maxArray = new int[n];
		Arrays.fill(maxArray, n);
		int maxCode = encode(maxArray);
		
		int[] minArray = new int[n];
		int minCode = encode(minArray);

		numWays = new int[maxCode + 1];
		numWays[maxCode] = 1;
		
		for (int i = maxCode; i > minCode; i--)
		{
			if (numWays[i] != 0)
			{
				for (int next : findPrev(i, n))
				{
					numWays[next] += numWays[i];
				}
			}
		}
		
		BufferedReader in = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("twofive.out")));
		
		String type = in.readLine();
		if (type.equals("N"))
		{
			int m = Integer.parseInt(in.readLine());

			int index = 1;
			grid[0][0] = start;
			numPerRow[0] = 1;
			used[0] = true;
			for (int i = 1; i < n * n - 1; i++)
			{
				int r = i / n;
				int c = i % n;
				char next;
				for (next = start + 1; used[next - start]; next++);

				numPerRow[r]++;
				for (char ch = next; ch < end; ch++)
				{
					if (used[ch - start] || !canInsert(ch, r, c)) continue;
					
					grid[r][c] = ch;
					used[ch - start] = true;
					
					int num = numSkipped();
					if (index + num <= m) index += num;
					else break;
					
					used[ch - start] = false;
				}
			}
			
			out.println(gridToString());
		}
		else
		{
			char[] s = in.readLine().toCharArray();
			int index = 1;
			
			char min = start;
			for (int i = 0; i < n * n; i++)
			{
				char curr = s[i];
				int r = i / n;
				int c = i % n;
				
				numPerRow[r]++;
				for (char ch = min; ch < curr; ch++)
				{
					if (used[ch - start] || !canInsert(ch, r, c)) continue;
					
					grid[r][c] = ch;
					used[ch - start] = true;
					
					index += numSkipped();
					
					used[ch - start] = false;
				}
				if (curr == min) min++;
				
				grid[r][c] = curr;
				used[curr - start] = true;
			}
			
			out.println(index);
		}
		
		in.close();
		out.close();
	}
	
	public static ArrayList<Integer> findPrev(int code, int n)
	{
		ArrayList<Integer> prev = new ArrayList<Integer>();
		
		int[] decoded = decode(code, n);
		for (int i = 0; i < n - 1; i++)
		{
			if (decoded[i] > decoded[i + 1])
			{
				decoded[i]--;
				prev.add(encode(decoded));
				decoded[i]++;
			}
		}
		if (decoded[n - 1] > 0)
		{
			decoded[n - 1]--;
			prev.add(encode(decoded));
			decoded[n - 1]++;
		}
		
		return prev;
	}
	
	public static int encode(int[] numCells)
	{
		int code = 0;
		for (int i = 0; i < numCells.length; i++)
		{
			code += 1 << numCells[i] + numCells.length - i - 1;
		}
		
		return code;
	}
	
	public static int[] decode(int code, int n)
	{
		int[] decoded = new int[n];
		for (int i = 0; i < n; i++)
		{
			int r = firstFromRight(code);
			code -= r;
			decoded[n - i - 1] = log2(r) - i;
		}
		
		return decoded;
	}
	
	public static int firstFromRight(int num)
	{
		return num & -num;
	}
	
	public static int log2(int num)
	{
		int log = -1;
		while (num >> ++log != 1);
		return log;
	}

	public static boolean canInsert(char letter, int r, int c)
	{
		return (r == 0 || grid[r - 1][c] < letter) && (c == 0 || grid[r][c - 1] < letter);
	}
	
	public static int numSkipped(char max)
	{
		char min;
		for (min = start + 1; used[min - start] && min < max; min++);
		if (min == max) return numWays[encode(numPerRow)];
		
		int skipped = 0;

		used[min - start] = true;
		for (int r = 0; r < n; r++)
		{
			int c = numPerRow[r];
			if (c < n && (r == 0 || c < numPerRow[r - 1]) && canInsert(min, r, c))
			{
				grid[r][c] = min;
				numPerRow[r]++;
				
				skipped += numSkipped(max);
				
				grid[r][c] = end;
				numPerRow[r]--;
			}
		}
		used[min - start] = false;
		return skipped;
	}
	
	public static int numSkipped()
	{
		char max;
		for (max = end - 1; !used[max - start]; max--);
		return numSkipped(max);
	}
	
	public static String gridToString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) sb.append(grid[i]);
		return sb.toString();
	}
}
