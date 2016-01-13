/*
ID: yuzhou.1
LANG: JAVA
TASK: ratios
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ratios
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		
		int c = 3;
		int f = 3;
		
		int[] target = new int[c];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < c; i++) target[i] = Integer.parseInt(st.nextToken());
		
		int[][] feeds = new int[f][c];
		for (int i = 0; i < f; i++)
		{
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < c; j++) feeds[i][j] = Integer.parseInt(st.nextToken());
		}
		in.close();
		
		boolean[] allZero = new boolean[c];
		for (int j = 0; j < c; j++)
		{
			allZero[j] = true;
			for (int i = 0; i < f; i++)
			{
				if (feeds[i][j] != 0) allZero[j] = false;
			}
			if (allZero[j])
			{
				if (target[j] != 0)
				{
					out.println("NONE");
					out.close();
					return;
				}
			}
		}
		
		int[] mapTo = new int[c];
		for (int i = 0; i < c; i++)
		{
			for (int j = 0; j < c; j++)
			{
				for (int k = 0; k < c; k++)
				{
					if (i != j && j != k && k != i)
					{
						if (feeds[0][i] != 0 && feeds[1][j] != 0 && feeds[2][k] != 0)
						{
							mapTo[i] = 0;
							mapTo[j] = 1;
							mapTo[k] = 2;
						}
					}
				}
			}
		}
		
		IntFrac zero = new IntFrac(0, 1);
		
		IntFrac[][] augMatrix = new IntFrac[f + 1][c];
		for (int i = 0; i < c; i++) augMatrix[f][mapTo[i]] = new IntFrac(target[i], 1);
		for (int i = 0; i < f; i++) for (int j = 0; j < c; j++) augMatrix[i][mapTo[j]] = new IntFrac(feeds[i][j], 1);
		/*
		for (int j = 0; j < f; j++)
		{
			for (int i = 0; i <= f; i++) System.out.print(augMatrix[i][j] + " ");
			System.out.println();
		}
		System.out.println();
		*/
		for (int j = 0; j < c; j++)
		{
			for (int i = 0; i < f; i++)
			{
				if (i < j && !augMatrix[i][j].equals(zero))
				{
					IntFrac mult = augMatrix[i][j].div(augMatrix[i][i]).neg();
					for (int k = 0; k <= f; k++)
					{
						augMatrix[k][j] = augMatrix[k][j].add(augMatrix[k][i].mult(mult));
					}
				}
			}
		}
		/*
		for (int j = 0; j < f; j++)
		{
			for (int i = 0; i <= f; i++) System.out.print(augMatrix[i][j] + " ");
			System.out.println();
		}
		System.out.println();
		*/
		for (int j = 0; j < c; j++)
		{
			for (int i = 0; i < f; i++)
			{
				if (i > j && !augMatrix[i][j].equals(zero))
				{
					IntFrac mult = augMatrix[i][j].div(augMatrix[i][i]).neg();
					for (int k = 0; k <= f; k++)
					{
						augMatrix[k][j] = augMatrix[k][j].add(augMatrix[k][i].mult(mult));
					}
				}
			}
		}
		/*
		for (int j = 0; j < f; j++)
		{
			for (int i = 0; i <= f; i++) System.out.print(augMatrix[i][j] + " ");
			System.out.println();
		}
		System.out.println();
		*/
		int k = 1;
		IntFrac[] kMult = new IntFrac[f];
		for (int i = 0; i < f; i++)
		{
			kMult[i] = augMatrix[f][i].div(augMatrix[i][i]);
			if (kMult[i].num < 0)
			{
				out.println("NONE");
				out.close();
				return;
			}
			k = IntFrac.lcm(k, kMult[i].denom);
		}
		
		for (int i = 0; i < f; i++) out.print(kMult[i].mult(k).num + " ");
		out.println(k);
		out.close();
	}
	
	static class IntFrac
	{
		int num;
		int denom;
		
		public IntFrac(int n, int d)
		{
			int div = gcd(Math.abs(n), Math.abs(d));
			num = n / div;
			denom = d / div;
		}
		
		public double toDouble()
		{
			return ((double) num) / denom;
		}
		
		public IntFrac neg()
		{
			return new IntFrac(-num, denom);
		}
		
		public IntFrac reciprocal()
		{
			if (num < 0) return new IntFrac(-denom, -num);
			return new IntFrac(denom, num);
		}
		
		public IntFrac add(IntFrac other)
		{
			int newDenom = denom * other.denom;
			int newNum = num * other.denom + other.num * denom;
			int div = IntFrac.gcd(Math.abs(newNum), Math.abs(newDenom));
			return new IntFrac(newNum / div, newDenom / div);
		}
		
		public IntFrac add(int num)
		{
			return add(new IntFrac(num, 1));
		}
		
		public IntFrac subtract(IntFrac other)
		{
			return add(other.neg());
		}
		
		public IntFrac subtract(int num)
		{
			return subtract(new IntFrac(num, 1));
		}
		
		public IntFrac mult(IntFrac other)
		{
			int newDenom = denom * other.denom;
			int newNum = num * other.num;
			int div = IntFrac.gcd(Math.abs(newNum), Math.abs(newDenom));
			return new IntFrac(newNum / div, newDenom / div);
		}
		
		public IntFrac mult(int num)
		{
			return mult(new IntFrac(num, 1));
		}
		
		public IntFrac div(IntFrac other)
		{
			return mult(other.reciprocal());
		}
		
		public IntFrac div(int num)
		{
			return div(new IntFrac(num, 1));
		}
		
		public static int gcd(int a, int b)
		{
			if (a == 0) return b;
			if (b == 0) return a;
			if (a == 1 || b == 1) return 1;
			if (a == b) return a;
			if (a > b) return gcd(a % b, b);
			return gcd(a, b % a);
		}
		
		public static int lcm(int a, int b)
		{
			return a * b / gcd(a, b);
		}
		
		@Override
		public boolean equals(Object o)
		{
			IntFrac other = (IntFrac) o;
			return denom == other.denom && num == other.num;
		}
		
		@Override
		public String toString()
		{
			return num + "/" + denom;
		}
	}
}
