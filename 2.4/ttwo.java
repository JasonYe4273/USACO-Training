/*
ID: yuzhou.1
LANG: JAVA
TASK: ttwo
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ttwo
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		
		final int[] DIR0 = { -1, 0, 1, 0 };
		final int[] DIR1 = { 0, 1, 0, -1 };
		final int n = 10;
		
		int[] cPos = new int[0];
		int[] fPos = new int[0];
		boolean[][] obstacle = new boolean[n][n];
		for (int i = 0; i < n; i++)
		{
			char[] inChar = in.readLine().toCharArray();
			for (int j = 0; j < n; j++)
			{
				if (inChar[j] == '*') obstacle[i][j] = true;
				else if (inChar[j] == 'C') cPos = new int[]{ i, j };
				else if (inChar[j] == 'F') fPos = new int[]{ i, j };
			}
		}
		in.close();
		
		int min = 0;
		int cDir = 0;
		int fDir = 0;
		boolean[][][][][][] done = new boolean[n][n][n][n][DIR0.length][DIR0.length];
		while (!done[cPos[0]][cPos[1]][fPos[0]][fPos[1]][cDir][fDir])
		{
			done[cPos[0]][cPos[1]][fPos[0]][fPos[1]][cDir][fDir] = true;
			int[] newCPos = { cPos[0] + DIR0[cDir], cPos[1] + DIR1[cDir] };
			if (newCPos[0] < 0 || newCPos[0] >= n || newCPos[1] < 0 || newCPos[1] >= n || obstacle[newCPos[0]][newCPos[1]])
			{
				cDir = (cDir + 1) % DIR0.length;
			}
			else
			{
				cPos = newCPos;
			}
			
			int[] newFPos = { fPos[0] + DIR0[fDir], fPos[1] + DIR1[fDir] };
			if (newFPos[0] < 0 || newFPos[0] >= n || newFPos[1] < 0 || newFPos[1] >= n || obstacle[newFPos[0]][newFPos[1]])
			{
				fDir = (fDir + 1) % DIR0.length;
			}
			else
			{
				fPos = newFPos;
			}
			
			min++;
			if (cPos[0] == fPos[0] && cPos[1] == fPos[1])
			{
				out.println(min);
				out.close();
				return;
			}
		}
		
		out.println(0);
		out.close();
	}
}
