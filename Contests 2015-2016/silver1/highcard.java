package silver1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class highcard
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("highcard.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		boolean[] elsieBool = new boolean[2 * n];
		for (int i = 0; i < 2 * n; i++)
		{
			elsieBool[i] = false;
		}
		for (int i = 0; i < n; i++)
		{
			int card = Integer.parseInt(in.readLine()) - 1;
			elsieBool[card] = true;
		}
        in.close();
		
		int[] elsie = new int[n];
		int[] bessie = new int[n];
		int ePlace = 0;
		int bPlace = 0;
		for (int i = 0; i < 2 * n; i++)
		{
			if (elsieBool[i])
			{
				elsie[ePlace] = i;
				ePlace++;
			}
			else
			{
				bessie[bPlace] = i;
				bPlace++;
			}
		}
		
		int points = 0;
		int b = n - 1;
		for (int e = n - 1; e >= 0; e--)
		{
			if (elsie[e] < bessie[b])
			{
				points++;
				b--;
			}
		}
		
		out.write(String.valueOf(points));
		out.close();
	}
}
