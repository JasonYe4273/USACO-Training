package gold1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class dreamtest
{
	public static void main(String[] args) throws IOException
	{
		for (long l = 0; l < Math.pow(5, 16); l++)
		{
			long temp = l;
			int[][] grid = new int[4][4];
			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					grid[i][j] = (int) (temp % 5);
					temp /= 5;
				}
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.in")));
			out.println("4 4");
			for (int i = 0; i < 4; i++)
			{
				out.println(String.format("%s %s %s %s", grid[i][0], grid[i][1], grid[i][2], grid[i][3]));
			}
			out.close();
			dream.main(args);
		}
	}
}
