/*
ID: yuzhou.1
LANG: JAVA
TASK: theme
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class theme
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("theme.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("theme.out")));
		
		int n = Integer.parseInt(in.readLine());
		if (n < 10)
		{
			in.close();
			out.println(0);
			out.close();
		}
		
		int idx = 0;
		int[] notes = new int[n];
		int[] diff = new int[n];
		String line = in.readLine();
		while (line != null)
		{
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				notes[idx] = Integer.parseInt(st.nextToken());
				if (idx > 0) diff[idx] = notes[idx] - notes[idx - 1];
				idx++;
			}
			line = in.readLine();
		}
		in.close();
		
		int length = 0;
		int[] themes = new int[n];
		for (int i = 1; i < n; i++) themes[i] = 1;
		while (true)
		{
			int nextTheme = 1;
			int[] newThemes = new int[n];
			boolean valid = false;
			for (int i = 1; i < n - length; i++)
			{
				int currTheme = themes[i];
				if (currTheme != 0 && newThemes[i] == 0)
				{
					int toLookFor = diff[i + length];
					boolean found = false;
					for (int j = i + 1; j < n - length; j++)
					{
						if (themes[j] == currTheme && diff[j + length] == toLookFor)
						{
							found = true;
							if (j > i + length + 1) valid = true;
							newThemes[j] = nextTheme;
						}
					}
					if (found)
					{
						newThemes[i] = nextTheme;
						nextTheme++;
					}
				}
			}
			if (!valid) break;
			
			themes = newThemes;
			length++;
		}
		length++;
		
		if (length < 5) out.println(0);
		else out.println(length);
		out.close();
	}
}
