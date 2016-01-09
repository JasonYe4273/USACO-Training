/*
ID: yuzhou.1
LANG: JAVA
TASK: fracdec
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class fracdec
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		in.close();
		
		if (n % d == 0)
		{
			out.println(String.format("%s.0", n / d));
			out.close();
			return;
		}
		
		int twoPow = 0;
		while ((d >> twoPow) % 2 == 0) twoPow++;
		
		int fivePow = 0;
		int temp = d;
		while (temp % 5 == 0)
		{
			fivePow++;
			temp /= 5;
		}
		
		int tenPow = Math.max(twoPow, fivePow);
		for (int i = 0; i < tenPow; i++)
		{
			if (i < twoPow) d /= 2;
			else n *= 2;
			
			if (i < fivePow) d /= 5;
			else n *= 5;
		}
		
		String intPart = String.valueOf(n / d);
		n = 10 * (n % d);
		
		StringBuilder frac = new StringBuilder();
		if (intPart.length() > tenPow)
		{
			frac.append(intPart.substring(0, intPart.length() - tenPow));
			frac.append('.');
			frac.append(intPart.substring(intPart.length() - tenPow));
		}
		else
		{
			frac.append("0.");
			for (int i = tenPow - intPart.length(); i > 0; i--)
			{
				frac.append('0');
			}
			frac.append(intPart);
		}
		frac.append('(');
		
		boolean[] usedN = new boolean[10 * d];
		while (!usedN[n])
		{
			usedN[n] = true;
			frac.append(String.valueOf(n / d));
			n = 10 * (n % d);
		}
		
		frac.append(')');
		int len = frac.length();
		if (frac.charAt(len - 3) == '(' && frac.charAt(len - 2) == '0') frac.delete(len - 3, len);
		
		len = frac.length();
		int idx = 0;
		while (len - idx > 76)
		{
			out.println(frac.substring(idx, idx + 76));
			idx += 76;
		}
		out.println(frac.substring(idx));
		out.close();
	}
}
