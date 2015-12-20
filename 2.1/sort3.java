/*
ID: yuzhou.1
LANG: JAVA
TASK: sort3
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class sort3
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		int[] ranks = new int[n];
		ArrayList<Integer> rankIndices = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) 
		{
			ranks[i] = Integer.parseInt(in.readLine());
			if (ranks[i] == 1) rankIndices.add(i);
		}
		in.close();
		
		int size = rankIndices.size();
		int[] num = new int[4];
		for (int i = 0; i < size; i++)
		{
			num[ranks[i]]++;
			ranks[i] = 1;
		}
		int numSwitch = size - num[1];
		
		for (int i = num[1]; i < num[1] + num[2]; i++)
		{
			ranks[rankIndices.get(i)] = 2;
		}
		for (int i = num[1] + num[2]; i < num[1] + num[2] + num[3]; i++)
		{
			ranks[rankIndices.get(i)] = 3;
		}
		
		rankIndices = new ArrayList<Integer>();
		for (int i = size; i < n; i++)
		{
			if (ranks[i] == 2) rankIndices.add(i);
		}
		
		int size2 = rankIndices.size();
		num = new int[4];
		for (int i = size; i < size + size2; i++)
		{
			num[ranks[i]]++;
			ranks[i] = 2;
		}
		numSwitch += size2 - num[2];
		
		for (int i = num[2]; i < num[2] + num[3]; i++)
		{
			ranks[rankIndices.get(i)] = 3;
		}
		
		out.println(numSwitch);
		out.close();
	}
}
