/*
ID: yuzhou.1
LANG: JAVA
TASK: concom
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class concom
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		
		int n = Integer.parseInt(in.readLine());
		Network net = new Network(100);
		for (int k = 0; k < n; k++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int p = Integer.parseInt(st.nextToken());
			net.ownage[i][j] = p;
			if (p > 50)
			{
				net.toAddControl.add(new int[]{ i, j });
				net.goingToAdd[i][j] = true;
			}
		}
		in.close();
		
		net.addAllControl();
		
		for (int i = 0; i < 100; i++)
		{
			for (int j = 0; j < 100; j++)
			{
				if (i != j && net.controls[i][j]) out.println((i+1) + " " + (j+1));
			}
		}
		out.close();
	}
	
	static class Network
	{
		public int n;
		public int[][] ownage;
		public boolean[][] controls;
		public int[] parent;
		public ArrayList<int[]> toAddControl;
		public boolean[][] goingToAdd;
		
		public Network(int n)
		{
			this.n = n;
			ownage = new int[n][n];
			controls = new boolean[n][n];
			for (int i = 0; i < n; i++) controls[i][i] = true;
			parent = new int[n];
			for (int i = 0; i < n; i++) parent[i] = i;
			toAddControl = new ArrayList<int[]>();
			goingToAdd = new boolean[n][n];
			for (int i = 0; i < n; i++) goingToAdd[i][i] = true;
		}
		
		public void addControl(int i, int j)
		{
			if (controls[i][j]) return;
			controls[i][j] = true;
			for (int k = 0; k < n; k++)
			{
				ownage[i][k] += ownage[j][k];
				if (!controls[i][k] && ownage[i][k] > 50 && !goingToAdd[i][k])
				{
					toAddControl.add(new int[]{ i, k });
					goingToAdd[i][k] = true;
				}
			}
			if (!controls[i][parent[j]]) parent[j] = i;
			if (parent[i] != i) addControl(parent[i], j);
		}
		
		public void addAllControl()
		{
			while(toAddControl.size() > 0)
			{
				int[] comps = toAddControl.remove(0);
				addControl(comps[0], comps[1]);
			}
		}
	}
}
