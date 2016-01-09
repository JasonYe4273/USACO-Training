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
		
		StringBuilder sb = new StringBuilder();
		line = in.readLine();
		while (line != null)
		{
			// DO NOT USE += (it's slow enough to cause your time to run out)
			sb.append(line);
			line = in.readLine();
		}
		String s = sb.toString();
		in.close();
		
		int len = s.length();
		boolean[] reachable = new boolean[len + 1];
		reachable[0] = true;
		for (int i = 0; i < len; i++)
		{
			if (!reachable[i]) continue;
			max = Math.min(len - i, max);
			for (int l = 1; l <= max; l++)
			{
				String toMatch = s.substring(i, i + l);
				for (String p : primitives[l])
				{
					if (toMatch.equals(p))
					{
						reachable[i + l] = true;
						break;
					}
				}
			}
		}
		
		int largestReachable = len;
		while (!reachable[largestReachable--]);
		
		out.println(largestReachable + 1);
		out.close();
	}
}
