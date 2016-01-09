/*
ID: yuzhou.1
LANG: JAVA
TASK: cowtour
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowtour
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		
		int n = Integer.parseInt(in.readLine());
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}

		double[][] dist = new double[n][n];
		for (int i = 0; i < n; i++)
		{
			char[] cArray = in.readLine().toCharArray();
			dist[i][i] = 0;
			for (int j = i + 1; j < n; j++)
			{
				if (cArray[j] == '0') dist[i][j] = Double.MAX_VALUE;
				else dist[i][j] = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
				dist[j][i] = dist[i][j];
			}
		}
		in.close();
		
		for (int k = 0; k < n; k++)
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					if (dist[i][k] != Double.MAX_VALUE && dist[k][j] != Double.MAX_VALUE && 
							dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		
		double[] maxReachable = new double[n];
		for (int i = 0; i < n; i++)
		{
			maxReachable[i] = 0;
			for (int j = 0; j < n; j++)
			{
				if (dist[i][j] != Double.MAX_VALUE && dist[i][j] > maxReachable[i]) maxReachable[i] = dist[i][j]; 
			}
		}
		
		int[] field = new int[n];
		for (int i = 0; i < n; i++) field[i] = i;
		for (int i = 0; i < n; i++)
		{
			for (int j = i + 1; j < n; j++)
			{
				if (dist[i][j] != Double.MAX_VALUE)
				{
					field[i] = field[j];
				}
			}
		}

		double[] diameters = new double[n];
		for (int i = 0; i < n; i++) diameters[i] = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = i + 1; j < n; j++)
			{
				if (field[i] == field[j] && dist[i][j] > diameters[field[i]])
				{
					diameters[field[i]] = dist[i][j];
				}
			}
		}
		
		double minDiam = Double.MAX_VALUE;
		for (int i = 0; i < n; i++)
		{
			for (int j = i + 1; j < n; j++)
			{
				if (dist[i][j] == Double.MAX_VALUE)
				{
					double diam = Math.sqrt((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
					diam += maxReachable[i] + maxReachable[j];
					diam = Math.max(diam, Math.max(diameters[field[i]], diameters[field[j]]));
					if (diam < minDiam)
					{
						minDiam = diam;
					}
				}
			}
		}
		
		out.println(String.format("%.6f", minDiam));
		out.close();
	}
}
