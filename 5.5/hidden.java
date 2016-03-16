/*
ID: yuzhou.1
LANG: JAVA
TASK: hidden
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class hidden
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("hidden.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hidden.out")));
		
		int l = Integer.parseInt(in.readLine());
		char[] s = new char[l];
		
		int place = 0;
		String line = in.readLine();
		while (line != null)
		{
			for (char c : line.toCharArray()) s[place++] = c;
			line = in.readLine();
		}
		in.close();
		
		ArrayList<Integer> lowest = new ArrayList<Integer>();
		
		char min = 'z';
		for (int i = 0; i < l; i++) if (s[i] < min) min = s[i];
		
		int max = 0;
		for (int i = 0; i < l; i++)
		{
			int num = 0;
			while (s[i % l] == min && num < l)
			{
				num++;
				i++;
			}
			
			if (max < num) max = num;
		}
		
		for (int i = 0; i < l; i++)
		{
			int start = i;
			int num = 0;
			while (s[i % l] == min && num < l)
			{
				num++;
				i++;
			}
			
			if (num == max) lowest.add(start);
		}
		
		int it = 1;
		while (it < l)
		{
			ArrayList<Integer> nextLow = new ArrayList<Integer>();
			min = 'z';
			for (int idx : lowest)
			{
				int i = (idx + it) % l;
				if (s[i] < min) min = s[i];
			}
			
			max = 0;
			for (int idx : lowest)
			{
				int i = idx + it;
				int num = 0;
				while (s[i % l] == min && num < l)
				{
					num++;
					i++;
				}
				
				if (max < num) max = num;
			}
			
			int last = Integer.MIN_VALUE;
			for (int idx : lowest)
			{
				if (idx < last + it) continue;
				last = idx;
				
				int i = idx + it;
				int start = idx;
				int num = 0;
				while (s[i % l] == min && num < l)
				{
					num++;
					i++;
				}
				
				if (num == max) nextLow.add(start);
			}
			
			lowest = nextLow;
			it += max;
		}
		
		out.println(lowest.get(0));
		out.close();
	}
}
