/*
ID: yuzhou.1
LANG: JAVA
TASK: shuttle
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class shuttle
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("shuttle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
		
		int n = Integer.parseInt(in.readLine());
		in.close();
		
		// NOTE: The following is based off of engineer's induction (observe a pattern in low numbers, and assume it holds for all numbers)
		int numPrinted = 0;
		int place = n;
		int dir = 1;
		boolean done = false;
		boolean second = false;
		while (!done)
		{
			if (place == n + 1) break;
			if (dir == 1) for (int i = place; i <= 2 * n + 2 - place; i += 2)
			{
				if (numPrinted % 20 == 19)
				{
					out.println(i);
				}
				else out.print(i + " ");
				numPrinted++;
			}
			else for (int i = place; i >= 2 * n + 2 - place; i -= 2)
			{
				if (numPrinted % 20 == 19)
				{
					out.println(i);
				}
				else out.print(i + " ");
				numPrinted++;
			}
			
			place = 2 * n + 2 - place;
			if (!second && (place == 1 || place == 2 * n + 1))
			{
				second = true;
			}
			else if (second && place >= n && place <= n + 2)
			{
				done = true;
			}
			
			if (!second)
			{
				place += dir;
				dir = -dir;
			}
			else
			{
				dir = -dir;
				place += dir;
			}
		}
		out.println(n + 1);
		out.close();
	}
}
