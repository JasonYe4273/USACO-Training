/*
ID: yuzhou.1
LANG: JAVA
TASK: comehome
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class comehome
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		
		int p = Integer.parseInt(in.readLine());
		int n = 52;
		
		int[][] distances = new int[n][n];
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) distances[i][j] = Integer.MAX_VALUE;
		for (int i = 0; i < p; i++)
		{
			String s = in.readLine();
			int f = s.charAt(0) <= 'Z' ? s.charAt(0) - 'A' : s.charAt(0) - 'A' - 6;
			int t = s.charAt(2) <= 'Z' ? s.charAt(2) - 'A' : s.charAt(2) - 'A' - 6;
			
			int newDist = Integer.parseInt(s.substring(4));
			if (newDist < distances[f][t])
			{
				distances[f][t] = newDist;
				distances[t][f] = newDist;
			}
		}
		in.close();
		
		for (int k = 0; k < n; k++)
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE && 
							distances[i][k] + distances[k][j] < distances[i][j]) distances[i][j] = distances[i][k] + distances[k][j];
				}
			}
		}
		
		int min = 0;
		for (int i = 1; i < 25; i++)
		{
			if (distances[i][25] < distances[min][25])
			{
				min = i;
			}
		}
		out.println((char) (min + 'A') + " " + distances[min][25]);
		out.close();
	}
}
