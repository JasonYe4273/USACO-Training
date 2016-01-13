/*
ID: yuzhou.1
LANG: JAVA
TASK: fence
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class fence
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		
		int n = 500;
		int f = Integer.parseInt(in.readLine());
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] neighbors = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) neighbors[i] = new ArrayList<Integer>();
		for (int i = 0; i < f; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			neighbors[a].add(b);
			neighbors[b].add(a);
		}
		for (int i = 0; i <= n; i++)
		{
			for (int k = 0; k < neighbors[i].size(); k++)
			{
				int place = k;
				do  place--; while (place > -1 && neighbors[i].get(place) > neighbors[i].get(k));
				if (place != k - 1) neighbors[i].add(place + 1, neighbors[i].remove(k));
			}
		}
		in.close();
		
		int idx = 0;
		Stack s = new Stack(f + 2);
		int start = -1;
		int end = -1;
		for (int i = 0; i <= n; i++)
		{
			if (neighbors[i].size() % 2 == 1)
			{
				if (start < 0) start = i;
				else end = i;
			}
		}
		if (start > end)
		{
			int temp = start;
			start = end;
			end = temp;
		}
		if (start >= 0)
		{
			neighbors[start].add(end);
			neighbors[end].add(start);
		}
		
		if (start == -1)
		{
			for (int i = 0; i < n; i++)
			{
				if (neighbors[i].size() > 0)
				{
					idx = i;
					break;
				}
			}
		}
		else idx = start;
		
		Stack circuit = new Stack(f + 2);
		while (true)
		{
			if (neighbors[idx].size() > 0)
			{
				s.add(idx);
				int temp = neighbors[idx].remove(0);
				neighbors[temp].remove((Integer) idx);
				idx = temp;
			}
			else
			{
				circuit.add(idx);
				if (s.size == 0) break;
				idx = s.pop();
			}
		}
		
		while (circuit.size > 1) out.println(circuit.pop());
		if (start == -1) out.println(circuit.pop());
		out.close();
	}
	
	static class IntComparator implements Comparator<Integer>
	{
		@Override
		public int compare(Integer i1, Integer i2)
		{
			return i1.compareTo(i2);
		}
	}
	
	static class Stack
	{
		public int[] stack;
		public int size;
		
		public Stack(int maxSize)
		{
			stack = new int[maxSize];
			size = 0;
		}
		
		public void add(int i)
		{
			stack[size++] = i;
		}
		
		public int pop()
		{
			return stack[--size];
		}
	}
}
