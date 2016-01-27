/*
ID: yuzhou.1
LANG: JAVA
TASK: frameup
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class frameup
{
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("frameup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		int maxNum = 26;
		int[][] letters = new int[h][w];
		for (int i = 0; i < h; i++)
		{
			char[] charLetters = in.readLine().toCharArray();
			for (int j = 0; j < w; j++) letters[i][j] = charLetters[j] - 'A';
		}
		in.close();
		
		int[] top = new int[maxNum];
		for (int l = 0; l < maxNum; l++) top[l] = h;
		
		int[] left = new int[maxNum];
		for (int l = 0; l < maxNum; l++) left[l] = w;
		
		int[] bottom = new int[maxNum];
		for (int l = 0; l < maxNum; l++) bottom[l] = -1;
		
		int[] right = new int[maxNum];
		for (int l = 0; l < maxNum; l++) right[l] = -1;

		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				if (letters[i][j] < 0) continue;
				
				if (i < top[letters[i][j]]) top[letters[i][j]] = i;
				if (i > bottom[letters[i][j]]) bottom[letters[i][j]] = i;
				if (j < left[letters[i][j]]) left[letters[i][j]] = j;
				if (j > right[letters[i][j]]) right[letters[i][j]] = j;
			}
		}

		boolean[] used = new boolean[maxNum];
		boolean[][] below = new boolean[maxNum][maxNum];
		for (int l = 0; l < maxNum; l++)
		{
			if (top[l] == h)
			{
				used[l] = true;
				continue;
			}
			
			for (int j = left[l]; j <= right[l]; j++)
			{
				below[l][letters[top[l]][j]] = true;
				below[l][letters[bottom[l]][j]] = true;
			}
			
			for (int i = top[l] + 1; i < bottom[l]; i++)
			{
				below[l][letters[i][left[l]]] = true;
				below[l][letters[i][right[l]]] = true;
			}
			
			below[l][l] = false;
		}
		
		print("", below, used, out);
		out.close();
	}
	
	public static void print(String prefix, boolean[][] below, boolean[] used, PrintWriter out)
	{
		boolean allUsed = true;
		for (int l = 0; l < used.length; l++)
		{
			if (!used[l])
			{
				allUsed = false;
				boolean toPrint = true;
				for (int l2 = 0; l2 < used.length; l2++)
				{
					if (below[l2][l] && !used[l2]) toPrint = false;
				}
				if (toPrint)
				{
					used[l] = true;
					print(prefix + (char) (l + 'A'), below, used, out);
					used[l] = false;
				}
			}
		}
		if (allUsed) out.println(prefix);
	}
	
	static class LevelPrinter
	{
		public ArrayList<Character>[] lettersByLevel;
		
		@SuppressWarnings("unchecked")
		public LevelPrinter(int maxNum)
		{
			lettersByLevel = new ArrayList[maxNum];
			for (int l = 0; l < maxNum; l++) lettersByLevel[l] = new ArrayList<Character>();
		}
		
		public void add(int letter, int level)
		{
			lettersByLevel[level].add((char) ('A' + letter));
		}
		
		public void print(PrintWriter out)
		{
			print("", 0, out);
		}
		
		public void print(String prefix, int fromLevel, PrintWriter out)
		{
			if (fromLevel >= lettersByLevel.length || lettersByLevel[fromLevel].size() == 0) out.println(prefix);
			else
			{
				if (lettersByLevel[fromLevel].size() > 1)
				{
					for (int i = 0; i < lettersByLevel[fromLevel].size(); i++)
					{
						Character l = lettersByLevel[fromLevel].remove(i);
						print(prefix + l, fromLevel, out);
						lettersByLevel[fromLevel].add(i, l);
					}
				}
				else
				{
					print(prefix + lettersByLevel[fromLevel].get(0), fromLevel + 1, out);
				}
			}
		}
	}
}
