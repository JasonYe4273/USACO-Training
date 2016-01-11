/*
ID: yuzhou.1
LANG: JAVA
TASK: inflate
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class inflate
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		Problem[] problems = new Problem[n];
		for (int i = 0; i < n; i++)
		{
			st = new StringTokenizer(in.readLine());
			problems[i] = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(problems);
		in.close();

		int[] points = new int[n];
		int[] time = new int[n];
		for (int i = 0; i < n; i++)
		{
			points[i] = problems[i].points;
			time[i] = problems[i].time;
		}
		
		int[] maxPoints = new int[m + 1];
		for (int i = 0; i < n; i++) if (time[i] <= m) maxPoints[time[i]] = points[i];
		for (int t = 1; t < m; t++)
		{
			if (maxPoints[t] != 0)
			{
				for (int i = 0; i < n; i++)
				{
					int newTime = t + time[i];
					if (newTime > m) break;
					if (maxPoints[t] + points[i] > maxPoints[newTime])
					{
						maxPoints[newTime] = maxPoints[t] + points[i];
					}
				}
			}
		}
		
		int max = 0;
		for (int t = 0; t <= m; t++)
		{
			if (maxPoints[t] > max) max = maxPoints[t];
		}
		out.println(max);
		out.close();
	}
	
	static class Problem implements Comparable<Problem>
	{
		public int points;
		public int time;
		
		public Problem(int p, int t)
		{
			points = p;
			time = t;
		}
		
		public int compareTo(Problem o)
		{
			Problem other = (Problem) o;
			if (time > other.time) return 1;
			if (time < other.time) return -1;
			return 0;
		}
	}
}
