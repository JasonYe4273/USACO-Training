/*
ID: yuzhou.1
LANG: JAVA
TASK: fence9
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class fence9
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		in.close();
		
		int twiceArea = p * m;
		int b = p + gcd(m, n) + gcd(m, Math.abs(p - n));
		
		out.println((twiceArea + 2 - b) / 2);
		out.close();
	}
	
	public static int gcd(int a, int b)
	{
		if (a == 0) return b;
		if (b == 0) return a;
		if (a == b) return a;
		if (a == 1 || b == 1) return 1;
		if (a < b) return gcd(a, b % a);
		return gcd(a % b, b);
	}
}
