/*
ID: yuzhou.1
LANG: JAVA
TASK: runround
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class runround
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		
		long m = Long.parseLong(in.readLine()) + 1;
		in.close();
		
		while (m < 987654322L)
		{
			if (isRunAround(m))
			{
				out.println(m);
				break;
			}
			m++;
		}
		
		out.close();
	}
	
	public static boolean isRunAround(long m)
	{
		int d = 0;
		int[] digits = new int[10];
		int[] places = new int[9];
		while (m > 0)
		{
			d++;
			int digit = (int) (m % 10);
			if (digits[digit] != 0 || digit == 0) return false;
			digits[digit] = d;
			places[d - 1] = digit;
			m /= 10;
		}
		
		boolean[] visited = new boolean[d];
		int place = d - 1;
		for (int i = 0; i < d; i++)
		{
			if (visited[place]) return false;
			else
			{
				visited[place] = true;
				place = (place + (d << 3) - places[place]) % d;
			}
		}
		if (place != d - 1) return false;
		
		return true;
	}
}
