/*
ID: yuzhou.1
LANG: JAVA
TASK: job
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class job
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("job.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int na = Integer.parseInt(st.nextToken());
		int nb = Integer.parseInt(st.nextToken());
		
		int[] aTime = new int[na];
		int[] bTime = new int[nb];
		boolean isA = true;
		int place = 0;
		String line = in.readLine();
		while (line != null)
		{
			st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				if (isA)
				{
					aTime[place++] = Integer.parseInt(st.nextToken());
					if (place == na)
					{
						place = 0;
						isA = false;
					}
				}
				else bTime[place++] = Integer.parseInt(st.nextToken());
			}
			line = in.readLine();
		}
		Arrays.sort(aTime);
		Arrays.sort(bTime);
		in.close();
		
		int maxTime = n * (aTime[na - 1] + bTime[nb - 1]);
		int[] maxADone = new int[maxTime + 1];
		int allATime = 0;
		while (true)
		{
			maxADone[allATime + 1] = maxADone[allATime++];
			for (int i = 0; i < na; i++) if (maxADone[allATime] < n && allATime % aTime[i] == 0) maxADone[allATime]++;
			if (maxADone[allATime] == n) break;
		}
		out.print(allATime + " ");
		
		int[] aFinish = new int[n];
		place = 0;
		for (int t = 1; t <= allATime; t++)
		{
			int newJobs = maxADone[t] - maxADone[t - 1];
			while (newJobs-- > 0) aFinish[place++] = t;
		}
		
		for (int time = allATime + bTime[0]; time <= maxTime; time++)
		{
			int[] availableBefore = new int[nb];
			for (int j = 0; j < nb; j++) availableBefore[j] = time - bTime[j];
			
			boolean canDo = true;
			for (int j = n - 1; j >= 0; j--)
			{
				canDo = false;
				for (int k = 0; k < nb; k++)
				{
					if (availableBefore[k] >= aFinish[j])
					{
						canDo = aFinish[j] + bTime[k] <= time;
						availableBefore[k] -= bTime[k];
						break;
					}
				}
				if (!canDo) break;
			}
			
			if (canDo)
			{
				out.println(time);
				break;
			}
		}
		out.close();
	}
}
