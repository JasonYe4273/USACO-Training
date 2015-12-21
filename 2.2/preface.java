/*
ID: yuzhou.1
LANG: JAVA
TASK: preface
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class preface
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		
		int n = Integer.parseInt(in.readLine());
		int d = 7;
		in.close();
		
		String[] numerals = new String[]{ "I", "V", "X", "L", "C", "D", "M" };
		int[] num = new int[d];
		
		for (int i = 1; i <= n; i++)
		{
			int[] currNum = getNumNumerals(i, d);
			for (int j = 0; j < d; j++) num[j] += currNum[j];
		}
		
		for (int i = 0; i < d; i++) if (num[i] > 0) out.println(String.format("%s %s", numerals[i], num[i]));
		
		out.close();
	}
	
	public static int[] getNumNumerals(int n, int d)
	{
		int[] num = new int[d];
		for (int i = 0; i < d; i += 2)
		{
			num[i] = n % 10;
			n /= 10;
		}
		
		int[] toAdd = new int[d];
		for (int i = 0; i < d; i += 2)
		{
			if (num[i] == 4)
			{
				num[i] = 1;
				num[i + 1]++;
			}
			else if (num[i] == 9)
			{
				num[i] = 1;
				toAdd[i + 2]++;
			}
			else if (num[i] >= 5)
			{
				num[i] -= 5;
				num[i + 1]++;
			}
			num[i] += toAdd[i];
		}
		
		return num;
	}
}
