package platinum1;

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
		
		int[] elsieOrdered = new int[n];
		int[] eSortedIndex = new int[n];
		boolean[] elsieHas = new boolean[n];
		for (int i = 0; i < n; i++)
		{
			elsieOrdered[i] = Integer.parseInt(in.readLine()) - 1;
			elsieHas[elsieOrdered[i]] = true;
			eSortedIndex[elsieOrdered[i]] = i;
		}
		
		int eIndex = 0;
		int bIndex = 0;
		int[] elsieSorted = new int[n];
		int[] bessieSorted = new int[n];
		for (int i = 0; i < 2 * n; i++)
		{
			if (elsieHas[i])
			{
				elsieSorted[eIndex] = i;
			}
			else bessieSorted[bIndex++] = i;
		}
		in.close();
		
		out.close();
	}
}
