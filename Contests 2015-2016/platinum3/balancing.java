package platinum3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

import platinum3.balancing.Cow.XComp;
import platinum3.balancing.Cow.YComp;

public class balancing
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		
		int n = Integer.parseInt(in.readLine());
		Cow[] cows = new Cow[n];
		Cow[] cowX = new Cow[n];
		Cow[] cowY = new Cow[n];
		for (int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken()) / 2;
			int y = Integer.parseInt(st.nextToken()) / 2;
			Cow c = new Cow(x, y, i);
			cowX[i] = c;
			cowY[i] = c;
		}
		Arrays.sort(cowX, new XComp());
		Arrays.sort(cowY, new YComp());
		in.close();
		
		int[] xIdx = new int[n];
		int[] yIdx = new int[n];
		for (int i = 0; i < n; i++)
		{
			xIdx[cowX[i].i] = i;
			yIdx[cowY[i].i] = i;
		}
		
		
		
		out.close();
	}
	
	static class Cow
	{
		int x;
		int y;
		int i;
		
		public Cow(int x, int y, int idx)
		{
			this.x = x;
			this.y = y;
			i = idx;
		}
		
		static class XComp implements Comparator<Cow>
		{

			@Override
			public int compare(Cow a, Cow b)
			{
				if (a.x < b.x) return -1;
				if (a.x == b.x)
				{
					if (a.y < b.y) return -1;
					if (a.y == b.y) return 0;
					return 1;
				}
				return 1;
			}
		}
		
		static class YComp implements Comparator<Cow>
		{

			@Override
			public int compare(Cow a, Cow b)
			{
				if (a.y < b.y) return -1;
				if (a.y == b.y)
				{
					if (a.x < b.x) return -1;
					if (a.x == b.x) return 0;
					return 1;
				}
				return 1;
			}
		}
	}
	
	public static int numBelow(HashSet<Integer> list, int n)
	{
		int num = 0;
		for (int i : list) if (i < n) num++;
		return num;
	}
}
