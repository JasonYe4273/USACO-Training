/*
ID: yuzhou.1
LANG: JAVA
TASK: msquare
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class msquare
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		
		int target = 0;
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < 7; i++)
		{
			target += Integer.parseInt(st.nextToken()) - 1;
			target <<= 3;
		}
		target += Integer.parseInt(st.nextToken()) - 1;
		in.close();
		
		HashSet<Integer> done = new HashSet<Integer>();
		ArrayList<Arrangement> toDo = new ArrayList<Arrangement>();
		int start = 0;
		for (int i = 0; i < 7; i++)
		{
			start += i;
			start <<= 3;
		}
		start += 7;
		done.add(start);
		toDo.add(new Arrangement(start, ""));
		
		if (start == target)
		{
			out.println(0);
			out.println();
		}
		
		int prevSize = 0;
		int size = done.size();
		while (prevSize != size)
		{
			prevSize = size;
			ArrayList<Arrangement> next = new ArrayList<Arrangement>();
			for (Arrangement curr : toDo)
			{
				//System.out.println(curr + curr.moves);
				int aInt = curr.generateA();
				if (!done.contains(aInt))
				{
					if (aInt == target)
					{
						String s = curr.moves + "A";
						
						int len = s.length();
						out.println(len);
						
						int i;
						for (i = 0; i + 60 <= s.length(); i += 60)
						{
							out.println(s.substring(i, i + 60));
						}
						out.println(s.substring(i));
						
						out.close();
						return;
					}
					done.add(aInt);
					next.add(new Arrangement(aInt, curr.moves + "A"));
				}
				
				int bInt = curr.generateB();
				if (!done.contains(bInt))
				{
					if (bInt == target)
					{
						String s = curr.moves + "B";
						
						int len = s.length();
						out.println(len);
						
						int i;
						for (i = 0; i + 60 <= s.length(); i += 60)
						{
							out.println(s.substring(i, i + 60));
						}
						out.println(s.substring(i));
						
						out.close();
						return;
					}
					done.add(bInt);
					next.add(new Arrangement(bInt, curr.moves + "B"));
				}
				
				int cInt = curr.generateC();
				if (!done.contains(cInt))
				{
					if (cInt == target)
					{
						String s = curr.moves + "C";
						
						int len = s.length();
						out.println(len);
						
						int i;
						for (i = 0; i + 60 <= s.length(); i += 60)
						{
							out.println(s.substring(i, i + 60));
						}
						out.println(s.substring(i));
						
						out.close();
						return;
					}
					done.add(cInt);
					next.add(new Arrangement(cInt, curr.moves + "C"));
				}
			}
			toDo = next;
			size = done.size();
		}
		
		out.close();
	}
	
	static class Arrangement
	{
		public int code;
		public String moves;
		
		public static final int[] C_SHIFT = { 21, 3, 18, 12, 9, 15, 6, 0 };
		
		public Arrangement(int c, String m)
		{
			code = c;
			moves = m;
		}
		
		public int generateA()
		{
			int toReturn = 0;
			for (int i = 0; i < 7; i++)
			{
				toReturn += (code >> (3 * i)) & 7;
				toReturn <<= 3;
			}
			toReturn += (code >> 21) & 7;
			return toReturn;
		}
		
		public int generateB()
		{
			int toReturn = 0;
			for (int i = 0; i < 4; i++)
			{
				toReturn += (code >> (3 * (7 - (i + 3) % 4))) & 7;
				toReturn <<= 3;
			}
			for (int i = 4; i < 7; i++)
			{
				toReturn += (code >> (3 * (6 - i))) & 7;
				toReturn <<= 3;
			}
			toReturn += (code >> 9) & 7;
			return toReturn;
		}
		
		public int generateC()
		{
			int toReturn = 0;
			for (int i = 0; i < 7; i++)
			{
				toReturn += (code >> C_SHIFT[i]) & 7;
				toReturn <<= 3;
			}
			toReturn += (code >> C_SHIFT[7]) & 7;
			return toReturn;
		}
		
		@Override
		public String toString()
		{
			String toReturn = "";
			for (int i = 7; i >= 0; i--)
			{
				toReturn += ((code >> (3 * i)) & 7) + " ";
			}
			return toReturn;
		}
	}
}
