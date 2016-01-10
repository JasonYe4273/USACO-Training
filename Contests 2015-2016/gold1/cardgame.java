package gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class cardgame
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("cardgame.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		int[] elsieRound = new int[2 * n];
		for (int i = 0; i < 2 * n; i++)
		{
			elsieRound[i] = 0;
		}
		for (int i = 0; i < n / 2; i++)
		{
			int card = Integer.parseInt(in.readLine()) - 1;
			elsieRound[card] = 1;
		}
		for (int i = 0; i < n / 2; i++)
		{
			int card = Integer.parseInt(in.readLine()) - 1;
			elsieRound[card] = 2;
		}
		in.close();
		
		int[] elsieHigh = new int[n / 2];
		int[] elsieLow = new int[n / 2];
		int[] bessie = new int[n];
		int eHPlace = 0;
		int eLPlace = 0;
		int bPlace = 0;
		for (int i = 0; i < 2 * n; i++)
		{
			if (elsieRound[i] == 1)
			{
				elsieHigh[eHPlace] = i;
				eHPlace++;
			}
			else if (elsieRound[i] == 2)
			{
				elsieLow[eLPlace] = i;
				eLPlace++;
			}
			else
			{
				bessie[bPlace] = i;
				bPlace++;
			}
		}
		
		int points = 0;
		int b = n - 1;
		for (int e = n / 2 - 1; e >= 0; e--)
		{
			if (elsieHigh[e] < bessie[b])
			{
				points++;
				b--;
			}
		}
		
		b = 0;
		for (int e = 0; e < n / 2; e++)
		{
			if (elsieLow[e] > bessie[b])
			{
				points++;
				b++;
			}
		}
		
		out.write(String.valueOf(points));
		out.close();
	}
}
