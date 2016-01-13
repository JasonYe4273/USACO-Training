/*
ID: yuzhou.1
LANG: JAVA
TASK: fact4
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class fact4
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		
		int n = Integer.parseInt(in.readLine());
		in.close();
		
		int[] TWO_POW_MOD_FOUR_MOD_TEN = { 6, 2, 4, 8 };
		
		int mod = 1;
		int numTwo = 0;
		for (int i = 1; i <= n; i++)
		{
			int temp = i;
			while (temp % 5 == 0)
			{
				temp /= 5;
				numTwo++;
			}
			mod *= temp % 10;
			mod %= 10;
		}
		if (numTwo > 0)
		{
			int toDiv = TWO_POW_MOD_FOUR_MOD_TEN[numTwo % 4];
			while (mod % toDiv != 0) mod += 10;
			mod /= toDiv;
		}
		
		out.println(mod);
		out.close();
	}
}
