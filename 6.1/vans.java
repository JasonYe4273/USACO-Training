/*
ID: yuzhou.1
LANG: JAVA
TASK: vans
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class vans
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("vans.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vans.out")));
		
		int n = Integer.parseInt(in.readLine()) - 1;
		in.close();
		
		if (n < 3)
		{
			out.println(2 * n);
			out.close();
			return;
		}
		
		// num[# of columns][Length of indent from the right]
		BigInteger[][] num = new BigInteger[n + 1][n + 1];
		num[0][0] = BigInteger.ZERO;
		num[1][0] = BigInteger.ONE;
		num[2][1] = BigInteger.ONE;
		num[2][0] = BigInteger.ONE;
		for (int i = 2; i < n; i++)
		{
			num[i + 1][1] = num[i][0];
			BigInteger temp = BigInteger.ZERO;
			for (int j = 1; j < i; j++)
			{
				temp = temp.add(num[i - 1][j - 1]);
				num[i + 1][j + 1] = num[i][j];
			}
			num[i + 1][0] = temp.shiftLeft(1).add(num[i - 1][0]).add(num[i][0]).subtract(num[i - 2][0]);
		}
		
		BigInteger total = num[n][0];
		for (int i = 1; i < n; i++)
		{
			total = total.add(num[n][i]);
		}
		out.println(total.shiftLeft(1));
		out.close();
	}
	
	static class BigNumber
	{
		public boolean[] digits;
		public int l;
		public static final BigNumber ZERO = new BigNumber(0, 0);
		public static final BigNumber ONE = new BigNumber(1, 1);
		
		public BigNumber(int length)
		{
			digits = new boolean[length];
			l = length;
		}
		
		public BigNumber(int length, int n)
		{
			digits = new boolean[length];
			for (int i = 0; i < length; i++)
			{
				digits[i] = n % 2 == 1;
				n /= 2;
			}
			l = length;
		}
		
		public BigNumber add(BigNumber other)
		{
			if (l < other.l)
			{
				BigNumber result = new BigNumber(other.l + 1);
				
				boolean carry = false;
				int i;
				for (i = 0; i < l; i++)
				{
					boolean a = digits[i];
					boolean b = other.digits[i];
					
					if (carry)
					{
						result.digits[i] = a && b;
						carry = a || b;
					}
					else
					{
						result.digits[i] = a ^ b;
						carry = a && b;
					}
				}
				
				for (i = l; i < other.l; i++)
				{
					boolean a = other.digits[i];
					
					if (carry && a)
					{
						result.digits[i] = false;
					}
					else
					{
						result.digits[i] = carry || a;
						break;
					}
				}
				
				if (i == other.l) result.digits[other.l] = true;
				else result.l = other.l;
				
				for (int j = i + 1; j < other.l; j++)
				{
					result.digits[j] = other.digits[j];
				}
				
				return result;
			}
			else if (l > other.l)
			{
				BigNumber result = new BigNumber(l + 1);
				
				boolean carry = false;
				int i;
				for (i = 0; i < other.l; i++)
				{
					boolean a = digits[i];
					boolean b = other.digits[i];
					
					if (carry)
					{
						result.digits[i] = a && b;
						carry = a || b;
					}
					else
					{
						result.digits[i] = a ^ b;
						carry = a && b;
					}
				}
				
				for (i = other.l; i < l; i++)
				{
					boolean a = digits[i];
					
					if (carry && a)
					{
						result.digits[i] = false;
					}
					else
					{
						result.digits[i] = carry || a;
						break;
					}
				}
				
				if (i == l) result.digits[l] = true;
				else result.l = l;
				
				for (int j = i + 1; j < l; j++)
				{
					result.digits[j] = digits[j];
				}
				
				return result;
			}
			else
			{
				BigNumber result = new BigNumber(l + 1);
				result.digits[l] = true;
				
				boolean carry = false;
				for (int i = 0; i < l; i++)
				{
					boolean a = digits[i];
					boolean b = other.digits[i];
					
					if (carry)
					{
						result.digits[i] = a && b;
						carry = a || b;
					}
					else
					{
						result.digits[i] = a ^ b;
						carry = a && b;
					}
				}
				
				return result;
			}
		}
		
		public BigNumber addShift(BigNumber other, int shift)
		{
			int ol = other.l + shift;
			if (l < shift)
			{
				BigNumber result = new BigNumber(ol);
				for (int i = 0; i < l; i++) result.digits[i] = digits[i];
				for (int i = shift; i < ol; i++) result.digits[i] = other.digits[i - shift];
				return result;
			}
			else if (l < ol)
			{
				BigNumber result = new BigNumber(ol + 1);
				
				boolean carry = false;
				int i;
				for (i = 0; i < shift; i++) result.digits[i] = digits[i];
				
				for (i = shift; i < l; i++)
				{
					boolean a = digits[i];
					boolean b = other.digits[i - shift];
					
					if (carry)
					{
						result.digits[i] = a && b;
						carry = a || b;
					}
					else
					{
						result.digits[i] = a ^ b;
						carry = a && b;
					}
				}
				
				for (i = l; i < ol; i++)
				{
					boolean a = other.digits[i - shift];
					
					if (carry && a)
					{
						result.digits[i] = false;
					}
					else
					{
						result.digits[i] = carry || a;
						break;
					}
				}
				
				if (i == ol) result.digits[ol] = true;
				else result.l = ol;
				
				for (int j = i + 1; j < ol; j++)
				{
					result.digits[j] = other.digits[j - shift];
				}
				
				return result;
			}
			else if (l > ol)
			{
				BigNumber result = new BigNumber(l + 1);
				
				boolean carry = false;
				int i;
				for (i = 0; i < shift; i++) result.digits[i] = digits[i];
				
				for (i = shift; i < ol; i++)
				{
					boolean a = digits[i];
					boolean b = other.digits[i - shift];
					
					if (carry)
					{
						result.digits[i] = a && b;
						carry = a || b;
					}
					else
					{
						result.digits[i] = a ^ b;
						carry = a && b;
					}
				}
				
				for (i = ol; i < l; i++)
				{
					boolean a = digits[i];
					
					if (carry && a)
					{
						result.digits[i] = false;
					}
					else
					{
						result.digits[i] = carry || a;
						break;
					}
				}
				
				if (i == l) result.digits[l] = true;
				else result.l = l;
				
				for (int j = i + 1; j < l; j++)
				{
					result.digits[j] = digits[j];
				}
				
				return result;
			}
			else
			{
				BigNumber result = new BigNumber(l + 1);
				result.digits[l] = true;
				
				boolean carry = false;
				for (int i = 0; i < shift; i++) result.digits[i] = digits[i];
				
				for (int i = shift; i < l; i++)
				{
					boolean a = digits[i];
					boolean b = other.digits[i - shift];
					
					if (carry)
					{
						result.digits[i] = a && b;
						carry = a || b;
					}
					else
					{
						result.digits[i] = a ^ b;
						carry = a && b;
					}
				}
				
				return result;
			}
		}
	
		public BigNumber multiply(BigNumber other)
		{
			BigNumber result = digits[0] ? other : ZERO;
			
			for (int i = 1; i < l; i++)
			{
				if (digits[i])
				{
					result = result.addShift(other, i);
				}
			}
			
			return result;
		}
		
		public BigNumber shiftLeft(int amount)
		{
			BigNumber result = new BigNumber(l + amount);
			
			for (int i = 0; i < l; i++) result.digits[amount + i] = digits[i];
			
			return result;
		}
		
		@Override
 		public String toString()
		{
			int[] ten = new int[l];
			int len = 0;
			
			for (int i = l - 1; i >= 0; i--)
			{
				int carry = digits[i] ? 1 : 0;
				for (int j = 0; j < len; j++)
				{
					ten[j] = ten[j] * 2 + carry;
					carry = ten[j] / 10;
					ten[j] %= 10;
				}
				
				if (carry > 0)
				{
					ten[len++] = carry;
				}
			}
			
			char[] c = new char[len];
			for (int i = 0; i < len; i++) c[len - i - 1] = (char) (ten[i] + '0');
			return String.valueOf(c);
		}
	}
}