/*
ID: yuzhou.1
LANG: JAVA
TASK: hamming
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hamming
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		in.close();
		
		int num = 1 << b;
		ArrayList<Integer> codes = new ArrayList<Integer>();
		for (int i = 0; i < num && codes.size() < n; i++)
		{
			boolean fits = true;
			for (int c : codes)
			{
				if (distance(i, c) < d) fits = false;
			}
			if (fits) codes.add(i);
		}
		
		num = 0;
		for (int i = 0; i < codes.size(); i++)
		{
			num++;
			if (num == 10 || i == codes.size() - 1) 
			{
				out.println(codes.get(i));
				num = 0;
			}
			else out.print(codes.get(i) + " ");
		}
		
		out.close();
	}
	
	public static int distance(int a, int b)
	{
		int temp = a ^ b;
		int num = 0;
		while (temp > 0)
		{
			if (temp % 2 == 1) num++;
			temp /= 2;
		}
		return num;
	}
}
