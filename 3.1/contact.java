/*
ID: yuzhou.1
LANG: JAVA
TASK: contact
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class contact
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int maxNum = 1 << b;
		int[][] num = new int[b + 1][];
		for (int i = 0; i <= b; i++) num[i] = new int[1 << i];
		
		int buf = 0;
		String line = in.readLine();
		char[] cArray = line.toCharArray();
		if (line.length() < a)
		{
			in.close();
			out.close();
			return;
		}
		for (int i = 0; i < a - 1; i++)
		{
			buf *= 2;
			if (cArray[i] == '1') buf++;
		}
		for (int i = a; i <= line.length(); i++)
		{
			buf *= 2;
			if (cArray[i - 1] == '1') buf++;
			buf %= maxNum;
			int max = Math.min(i, b);
			for (int j = a; j <= max; j++)
			{
				num[j][buf % (1 << j)]++;
			}
		}
		
		line = in.readLine();
		while (line != null)
		{
			cArray = line.toCharArray();
			for (char c : cArray)
			{
				buf *= 2;
				if (c == '1') buf++;
				buf %= maxNum;
				for (int j = a; j <= b; j++)
				{
					num[j][buf % (1 << j)]++;
				}
			}
			line = in.readLine();
		}
		in.close();
		
		Frequency[] freqs = new Frequency[(1 << (b + 1)) - 1];
		int max = 0;
		for (int i = 0; i <= b; i++)
		{
			for (int j = 0; j < 1 << i; j++)
			{
				freqs[(1 << i) + j - 1] = new Frequency(num[i][j], intToString(j, i));
				if ((1 << i) + j - 1 > max) max = (1 << i) + j - 1;
			}
		}
		Arrays.sort(freqs);
		
		int numFreq = 0;
		int prevFreq = freqs[0].freq;
		int numSoFar = 1;
		out.println(prevFreq);
		out.print(freqs[0].seq);
		for (int i = 1; numFreq < n && i < freqs.length; i++)
		{
			int freq = freqs[i].freq;
			if (freq == 0) break;
			if (freq == prevFreq)
			{
				if (numSoFar == 6)
				{
					numSoFar = 1;
					out.println();
					out.print(freqs[i].seq);
				}
				else
				{
					numSoFar++;
					out.print(" " + freqs[i].seq);
				}
			}
			else if (numFreq == n - 1) numFreq++;
			else
			{
				numFreq++;
				prevFreq = freq;
				numSoFar = 1;
				out.println();
				out.println(freq);
				out.print(freqs[i].seq);
			}
		}
		
		out.println();
		out.close();
	}
	
	static class Frequency implements Comparable<Frequency>
	{
		public int freq;
		public String seq;
		
		public Frequency(int f, String s)
		{
			freq = f;
			seq = s;
		}
		
		public int compareTo(Frequency o)
		{
			if (freq > o.freq) return -1;
			if (freq < o.freq) return 1;
			return 0;
		}
	}
	
	public static String intToString(int a, int d)
	{
		char[] s = new char[d];
		for (int i = d - 1; i >= 0; i--)
		{
			s[i] = a % 2 == 1 ? '1' : '0';
			a /= 2;
		}
		return String.valueOf(s);
	}
}
