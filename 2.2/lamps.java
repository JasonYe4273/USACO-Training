/*
ID: yuzhou.1
LANG: JAVA
TASK: lamps
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lamps
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		
		int n = Integer.parseInt(in.readLine());
		int c = Integer.parseInt(in.readLine());
		final int ALL = 0;
		final int ODD = 1;
		final int EVEN = 2;
		final int THREE = 3;
		final int ODD3 = 4;
		final int EVEN3 = 5;
		int[] parities = new int[6];
		for (int i = 0; i < parities.length; i++) parities[i] = -1;
		
		StringTokenizer st= new StringTokenizer(in.readLine());
		int num = Integer.parseInt(st.nextToken());
		while (num != -1)
		{
			int index;
			if (num % 2 == 1)
			{
				if (num % 3 == 1) index = ODD3;
				else index = ODD;
			}
			else
			{
				if (num % 3 == 1) index = EVEN3;
				else index = EVEN;
			}
			
			if (parities[index] == 1)
			{
				out.println("IMPOSSIBLE");
				in.close();
				out.close();
				return;
			}
			parities[index] = 0;
			
			num = Integer.parseInt(st.nextToken());
		}
		
		st= new StringTokenizer(in.readLine());
		num = Integer.parseInt(st.nextToken());
		while (num != -1)
		{
			int index;
			if (num % 2 == 1)
			{
				if (num % 3 == 1) index = ODD3;
				else index = ODD;
			}
			else
			{
				if (num % 3 == 1) index = EVEN3;
				else index = EVEN;
			}
			
			if (parities[index] == 0)
			{
				out.println("IMPOSSIBLE");
				in.close();
				out.close();
				return;
			}
			parities[index] = 1;
			
			num = Integer.parseInt(st.nextToken());
		}
		in.close();
		
		int[][] type = new int[16][4];
		int[] switches = new int[]{ 63, 42, 21, 36 };
		int[] six = new int[16];
		for (int i = 0; i < 16; i++)
		{
			six[i] = 63;
			int temp = i;
			int place = 0;
			while (temp > 0)
			{
				if (temp % 2 == 1)
				{
					type[i][place] = 1;
					six[i] ^= switches[place];
				}
				temp = temp >> 1;
				place++;
			}
		}
		
		for (int i = 15; i >= 0; i--)
		{
			int place = i + 1;
			int curr = six[i];
			int[] currType = type[i];
			while (place < 16 && curr > six[place])
			{
				type[place - 1] = type[place];
				six[place - 1] = six[place++];
			}
			type[place - 1] = currType;
			six[place - 1] = curr;
		}
		
		boolean cut = n < 6;
		ArrayList<String> possible = new ArrayList<String>();
		for (int i = 0; i < six.length; i++)
		{
			boolean valid = true;
			if (type[i][ALL] + type[i][ODD] + type[i][EVEN] + type[i][THREE] > c) valid = false;
			if ((type[i][ALL] + type[i][ODD] + type[i][EVEN] + type[i][THREE]) % 2 == 1 - (c % 2)) valid = false;
			if ((type[i][ODD] + type[i][ALL] + type[i][THREE]) % 2 == 1 - parities[ODD3]) valid = false;
			if ((type[i][EVEN] + type[i][ALL] + type[i][THREE]) % 2 == 1 - parities[EVEN3]) valid = false;
			if ((type[i][ODD] + type[i][ALL]) % 2 == 1 - parities[ODD]) valid = false;
			if ((type[i][EVEN] + type[i][ALL]) % 2 == 1 - parities[EVEN]) valid = false;
			if (!valid) continue;
			
			String sixString = intToString(six[i]);
			if (cut) sixString = sixString.substring(0, n);
			if (!possible.contains(sixString)) possible.add(sixString);
		}
		
		if (possible.size() == 0)
		{
			out.println("IMPOSSIBLE");
			out.close();
			return;
		}
		
		for (String p : possible)
		{
			String s = "";
			int length = n;
			while (length >= 6)
			{
				s += p;
				length -= 6;
			}
			s += p.substring(0, length);
			out.println(s);
		}
		
		out.close();
	}
	
	public static String intToString(int n)
	{
		String result = "";
		for (int i = 0; i < 6; i++)
		{
			if (n % 2 == 1) result = "1" + result;
			else result = "0" + result;
			n = n >> 1;
		}
		return result;
	}
}
