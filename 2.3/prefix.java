/*
ID: yuzhou.1
LANG: JAVA
TASK: prefix
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class prefix
{
	public static void main(String[] args) throws IOException
	{
		//long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		
		@SuppressWarnings("unchecked")
		ArrayList<String>[] primitives = new ArrayList[11];
		for (int i = 1; i <= 10; i++) primitives[i] = new ArrayList<String>();
		String line = in.readLine().trim();
		while (!line.equals("."))
		{
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				String p = st.nextToken();
				primitives[p.length()].add(p);
			}
			line = in.readLine().trim();
		}
		
		int max = 0;
		for (int i = 10; i > 0; i--)
		{
			if (primitives[i].size() > 0)
			{
				max = i;
				break;
			}
		}
		
		String s = "";
		line = in.readLine();
		while (line != null)
		{
			s += line.trim();
			line = in.readLine();
		}
		in.close();
		
		out.println(longestPrefix(s, primitives, max));
		out.close();
		//System.out.println(System.currentTimeMillis() - start);
	}
	
	public static int longestPrefix(String s, ArrayList<String>[] primitives, int maxPrimitive)
	{
		int l = s.length();
		if (l == 0) return 0;
		int half = l / 2;
		if (half <= maxPrimitive)
		{
			int max = 0;
			if (l <= maxPrimitive)
			{
				for (String p : primitives[l])
				{
					if (s.equals(p)) return l;
				}
			}
			for (int i = Math.min(maxPrimitive, l - 1); i > 0; i--)
			{
				String sis = s.substring(0, i);
				String sie = s.substring(i);
				for (String p : primitives[i])
				{
					if (sis.equals(p))
					{
						int length = i + longestPrefix(sie, primitives, maxPrimitive);
						if (length == l) return l;
						if (length > max) max = length;
					}
				}
			}
			return max;
		}
		
		int firstHalfPrefix = longestPrefix(s.substring(0, half), primitives, maxPrimitive);
		if (firstHalfPrefix <= half - maxPrimitive) return firstHalfPrefix;
		return firstHalfPrefix + longestPrefix(s.substring(firstHalfPrefix), primitives, maxPrimitive);
	}
}
