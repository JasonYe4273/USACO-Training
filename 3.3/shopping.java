/*
ID: yuzhou.1
LANG: JAVA
TASK: shopping
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class shopping
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		
		int s = Integer.parseInt(in.readLine());
		String[] offers = new String[s];
		for (int i = 0; i < s; i++)
		{
			offers[i] = in.readLine();
		}
		
		int maxCode = 1000;
		int b = Integer.parseInt(in.readLine());
		int[] codes = new int[maxCode];
		for (int i = 0; i < maxCode; i++) codes[i] = -1;
		int[] toBuy = new int[b];
		int[] price = new int[b];
		for (int i = 0; i < b; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			codes[Integer.parseInt(st.nextToken())] = i;
			toBuy[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		DeEnCoder d = new DeEnCoder(b, toBuy);
		
		ArrayList<int[]> validOffers = new ArrayList<int[]>();
		for (int i = 0; i < b; i++)
		{
			int[] regOffer = new int[b];
			regOffer[i] = 1;
			validOffers.add(new int[]{ d.encode(regOffer), price[i] });
		}
		
		for (int i = 0; i < s; i++)
		{
			StringTokenizer st = new StringTokenizer(offers[i]);
			int n = Integer.parseInt(st.nextToken());
			
			boolean valid = true;
			int[] nums = new int[b];
			for (int j = 0; j < n; j++)
			{
				int c = Integer.parseInt(st.nextToken());
				if (codes[c] == -1) valid = false;
				else nums[codes[c]] = Integer.parseInt(st.nextToken());
			}
			
			int offerCode = d.encode(nums);
			if (valid && offerCode != -1) validOffers.add(new int[]{ offerCode, Integer.parseInt(st.nextToken()) });
		}
		in.close();
		
		maxCode = d.encode(toBuy);
		int[] minPrice = new int[maxCode + 1];
		minPrice[0] = 0;
		for (int i = 1; i <= maxCode; i++) minPrice[i] = Integer.MAX_VALUE;
		for (int i = 0; i < maxCode; i++)
		{
			for (int[] o : validOffers)
			{
				int nextIdx = d.addCodes(i, o[0]);
				if (nextIdx > -1 && minPrice[i] + o[1] < minPrice[nextIdx]) minPrice[nextIdx] = minPrice[i] + o[1];
			}
		}
		
		out.println(minPrice[maxCode]);
		out.close();
	}
	
	static class DeEnCoder
	{
		public int n;
		public int[] max;
		
		public DeEnCoder(int num, int[] maxPer)
		{
			n = num;
			max = new int[n];
			for (int i = 0; i < n; i++) max[i] = maxPer[i] + 1;
		}
		
		public int addCodes(int code, int toAdd)
		{
			int[] decoded = decode(code);
			int[] decodedToAdd = decode(toAdd);
			for (int i = 0; i < n; i++)
			{
				decoded[i] += decodedToAdd[i];
			}
			
			for (int i = 0; i < n; i++) if (decoded[i] >= max[i]) return -1;
			
			return encode(decoded);
		}
		
		public int encode(int[] nums)
		{
			int encoded = 0;
			for (int i = n - 1; i >= 0; i--)
			{
				encoded *= max[i];
				encoded += nums[i];
			}
			return encoded;
		}
		
		public int[] decode(int code)
		{
			int[] decoded = new int[n];
			for (int i = 0; i < n; i++)
			{
				decoded[i] = code % max[i];
				code /= max[i];
			}
			return decoded;
		}
	}
}
