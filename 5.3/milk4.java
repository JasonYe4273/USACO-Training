/*
ID: yuzhou.1
LANG: JAVA
TASK: milk4
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class milk4
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("milk4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
		
		int q = Integer.parseInt(in.readLine());
		int p = Integer.parseInt(in.readLine());
		
		int[] pails = new int[p];
		for (int i = 0; i < p; i++)
		{
			pails[i] = Integer.parseInt(in.readLine());
			if (q % pails[i] == 0)
			{
				out.println("1 " + pails[i]);
				in.close();
				out.close();
				return;
			}
		}
		in.close();
		
		Arrays.sort(pails);
		
		for (int i = 0; i < p; i++)
		{
			if (q % pails[i] == 0)
			{
				out.println("1 " + pails[i]);
				out.close();
				return;
			}
		}
		
		int totalGCD = 0;
		for (int i = 0; i < p; i++)
		{
			totalGCD = gcd(totalGCD, pails[i]);
		}
		if (totalGCD > 1)
		{
			q /= totalGCD;
			for (int i = 0; i < p; i++)
			{
				pails[i] /= totalGCD;
			}
		}
		
		for (int i = 0; i < p; i++)
		{
			for (int j = i + 1; j < p; j++)
			{
				PailCombo c = new PailCombo(pails[i], pails[j], q, j);
				if (c.valid())
				{
					out.println(String.format("2 %s %s", totalGCD * pails[i], totalGCD * pails[j]));
					out.close();
					return;
				}
			}
		}
		
		ArrayList<PailCombo> curr = new ArrayList<PailCombo>();
		for (int i = 0; i < p; i++)
		{
			for (int j = i + 1; j < p; j++)
			{
				PailCombo c = new PailCombo(pails[i], pails[j], q, j);
				if (c.valid())
				{
					out.println(String.format("2 %s %s", totalGCD * pails[i], totalGCD * pails[j]));
					out.close();
					return;
				}
				
				for (int k = c.largestIdx + 1; k < p; k++)
				{
					PailCombo newC = new PailCombo(c, pails[k], k);
					if (newC.numUnreachable < c.numUnreachable)
					{
						if (newC.valid())
						{
							out.print(newC.pails.size());
							for (int pail : newC.pails)
							{
								out.print(" " + pail * totalGCD);
							}
							out.println();
							out.close();
							return;
						}
					}
				}
				curr.add(c);
			}
		}

		for (int n = 3; n <= p; n++)
		{
			ArrayList<PailCombo> next = new ArrayList<PailCombo>();
			for (PailCombo c : curr)
			{
				for (int i = c.largestIdx + 1; i < p; i++)
				{
					PailCombo newC = new PailCombo(c, pails[i], i);
					if (newC.numUnreachable < c.numUnreachable)
					{
						if (newC.valid())
						{
							out.print(newC.pails.size());
							for (int pail : newC.pails)
							{
								out.print(" " + pail * totalGCD);
							}
							out.println();
							out.close();
							return;
						}
						next.add(newC);
					}
				}
			}
			curr = next;
		}
		
		out.close();
	}
	
	public static int gcd(int a, int b)
	{
		if (a == b) return 1;
		if (a == 0) return b;
		if (b == 0) return a;
		if (a == 1 || b == 1) return 1;
		if (a > b) return gcd(a % b, b);
		return gcd(a, b % a);
	}
	
	static class PailCombo
	{
		public ArrayList<Integer> pails;
		public int q;
		public boolean[] reachable;
		public int largestIdx;
		public int numUnreachable;
		
		public PailCombo(int a, int b, int q, int bIdx)
		{
			this.q = q;
			
			pails = new ArrayList<Integer>();
			pails.add(a);
			pails.add(b);
			
			numUnreachable = 0;
			reachable = new boolean[q + 1];
			reachable[0] = true;
			for (int i = 0; i <= q - a; i++)
			{
				if (reachable[i])
				{
					reachable[i + a] = true;
					if (i + b <= q) reachable[i + b] = true;
				}
				else numUnreachable++;
			}
			
			for (int i = q - a + 1; i <= q; i++)
			{
				if (!reachable[i]) numUnreachable++;
			}
			
			largestIdx = bIdx;
		}
		
		@SuppressWarnings("unchecked")
		public PailCombo(PailCombo old, int toAdd, int addIdx)
		{
			pails = (ArrayList<Integer>) old.pails.clone();
			reachable = Arrays.copyOf(old.reachable, old.reachable.length);
			q = old.q;

			for (int i = 0; i <= q - toAdd; i++)
			{
				if (reachable[i]) reachable[i + toAdd] = true;
				else numUnreachable++;
			}
			
			for (int i = q - toAdd + 1; i <= q; i++)
			{
				if (!reachable[i]) numUnreachable++;
			}
			
			pails.add(toAdd);
			
			largestIdx = addIdx;
		}
		
		public boolean valid()
		{
			return reachable[q];
		}
	}
}
