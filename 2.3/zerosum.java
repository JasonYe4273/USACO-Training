/*
ID: yuzhou.1
LANG: JAVA
TASK: zerosum
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class zerosum
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		
		int n = Integer.parseInt(in.readLine());
		in.close();
		
		char[] operators = { ' ', '+', '-' };
		int max = (int) Math.pow(3, n - 1);
		
		ArrayList<char[]> valid = new ArrayList<char[]>();
		for (int i = 0; i < max; i++)
		{
			char[] oper = new char[n - 1];
			int temp = i;
			for (int j = n - 2; j >= 0; j--)
			{
				oper[j] = operators[temp % 3];
				temp /= 3;
			}
			
			if (parse(oper, n) == 0) valid.add(oper);
		}
		
		for (char[] oper : valid)
		{
			String s = "1";
			for (int i = 0; i < n - 1; i++)
			{
				s += String.valueOf(oper[i]) + String.valueOf(i + 2);
			}
			out.println(s);
		}
		
		out.close();
	}
	
	public static int parse(char[] oper, int num)
	{
		int sum = 0;
		int n = 2;
		int currNum = 1;
		int place = 0;
		char lastOper = '+';
		while (n <= num)
		{
			if (oper[place] == ' ')
			{
				currNum *= 10;
				currNum += n;
			}
			else
			{
				if (lastOper == '+')
				{
					sum += currNum;
					currNum = n;
				}
				else
				{
					sum -= currNum;
					currNum = n;
				}
				lastOper = oper[place];
			}
			
			n++;
			place++;
		}
		
		return sum + (lastOper == '+' ? currNum : 0 - currNum);
	}
}
