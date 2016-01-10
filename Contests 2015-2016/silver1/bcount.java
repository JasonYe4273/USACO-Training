package silver1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bcount
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("bcount.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		int[] hTo = new int[n];
		int[] gTo = new int[n];
		int[] jTo = new int[n];
		int currH = 0;
		int currG = 0;
		int currJ = 0;
		for (int i = 0; i < n; i++)
		{
			int breed = Integer.parseInt(in.readLine());
			if (breed == 1) currH++;
			else if (breed == 2) currG++;
			else currJ++;
			
			hTo[i] = currH;
			gTo[i] = currG;
			jTo[i] = currJ;
		}
		
		for (int i = 0; i < q; i++)
		{
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1)
			{
				out.println(String.format("%s %s %s", hTo[b - 1], gTo[b - 1], jTo[b - 1]));
			}
			else
			{
				out.println(String.format("%s %s %s", hTo[b - 1] - hTo[a - 2], gTo[b - 1] - gTo[a - 2], jTo[b - 1] - jTo[a - 2]));
			}
		}
        in.close();
		out.close();
	}
}
