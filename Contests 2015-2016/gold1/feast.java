package gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class feast
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("feast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		in.close();
		
		int large = a > b ? a : b;
		int small = a > b ? b : a;
		int kLarge = (2 * t + 1) / large;
		int maxT = large * kLarge;
		int corr = 1 - t % 2;
		for (int l = kLarge; l >= 0; l--)
		{
			int s = (2 * t + corr - l * large) / small;
			if (maxT < l * large + s * small)
			{
				maxT = l * large + s * small;
			}
		}
		out.println(maxT / 2);
		out.close();
	}
}
