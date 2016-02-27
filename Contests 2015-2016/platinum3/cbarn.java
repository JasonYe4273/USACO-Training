package platinum3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cbarn
{
	public static int n;
	public static int k;
	public static int[] r;
	public static int[][] sectionDist;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		r = new int[n];
		for (int i = 0; i < n; i++)
		{
			r[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		
		sectionDist = new int[n][n + 1];
		for (int i = 0; i < n; i++)
		{
			sectionDist[i][0] = 0;
			sectionDist[i][1] = 0;
			for (int l = 2; l <= n; l++)
			{
				sectionDist[i][l] = sectionDist[i][l - 1] + (l - 1) * r[(i + l - 1) % n];
			}
		}
		
		int[] minDist = new int[k + 1];
		for (int i = 0; i <= k; i++) minDist[i] = Integer.MAX_VALUE;
		ArrayList<DoorConfig> configs = new ArrayList<DoorConfig>();
		configs.add(new DoorConfig());
		while (configs.size() > 0)
		{
			DoorConfig curr = configs.remove(configs.size() - 1);
			int totalDist = curr.totalDist();
			//if ((curr.numDoors > 0 && totalDist > minDist[curr.numDoors - 1])) continue;
			if (totalDist < minDist[curr.numDoors])
			{
				minDist[curr.numDoors] = totalDist;
			}
			if (curr.numDoors < k) configs.addAll(curr.generateNext());
		}
		
		out.println(minDist[k]);
		out.close();
	}
	
	static class DoorConfig
	{
		public ArrayList<Integer> doors;
		public int lastDoor;
		public int numDoors;
		
		public DoorConfig()
		{
			lastDoor = -1;
			numDoors = 0;
			doors = new ArrayList<Integer>();
		}
		
		public DoorConfig(DoorConfig prev, int newDoor)
		{
			lastDoor = newDoor;
			numDoors = prev.numDoors + 1;
			doors = new ArrayList<Integer>();
			for (int d : prev.doors)
			{
				doors.add(d);
			}
			doors.add(newDoor);
		}
		
		public int totalDist()
		{
			if (numDoors == 0) return Integer.MAX_VALUE;
			int totalDist = sectionDist[doors.get(numDoors - 1)][doors.get(0) + n - doors.get(numDoors - 1)];
			for (int i = 0; i < doors.size() - 1; i++)
			{
				totalDist += sectionDist[doors.get(i)][doors.get(i + 1) - doors.get(i)];
			}
			return totalDist;
		}
		
		public ArrayList<DoorConfig> generateNext()
		{
			ArrayList<DoorConfig> next = new ArrayList<DoorConfig>();
			for (int i = lastDoor + 1; i < n; i++)
			{
				next.add(new DoorConfig(this, i));
			}
			return next;
		}
	}
}
