/*
ID: yuzhou.1
LANG: JAVA
TASK: camelot
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class camelot
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] king = new int[2];
		st = new StringTokenizer(in.readLine());
		king[1] = st.nextToken().charAt(0) - 'A';
		king[0] = Integer.parseInt(st.nextToken()) - 1;

		boolean[][] knights = new boolean[r][c];
		String line = in.readLine();
		while (line != null)
		{
			st = new StringTokenizer(line);
			while (st.hasMoreTokens())
			{
				int col = st.nextToken().charAt(0) - 'A';
				int row = Integer.parseInt(st.nextToken()) - 1;
				knights[row][col] = true;
			}
			line = in.readLine();
		}
		in.close();
		
		int[][] baseKnight = new int[r][c];
		for (int ir = 0; ir < r; ir++) for (int ic = 0; ic < c; ic++) baseKnight[ir][ic] = Integer.MAX_VALUE;
		baseKnight[0][0] = 0;
		boolean[][] visited = new boolean[r][c];
		for (int i = 0; i < r * c; i++)
		{
			int min = Integer.MAX_VALUE;
			int[] idx = new int[2];
			for (int ir = 0; ir < r; ir++) for (int ic = 0; ic < c; ic++)
			{
				if (!visited[ir][ic] && baseKnight[ir][ic] < min)
				{
					min = baseKnight[ir][ic];
					idx[0] = ir;
					idx[1] = ic;
				}
			}
			
			visited[idx[0]][idx[1]] = true;
			for (int ir = 0; ir < r; ir++) for (int ic = 0; ic < c; ic++)
			{
				if (!visited[ir][ic] && (isKnightMove(idx[0], idx[1], ir, ic) || isKnightMove(idx[0], idx[1], -ir, ic) || isKnightMove(idx[0], idx[1], -ir, -ic)) && baseKnight[idx[0]][idx[1]] < baseKnight[ir][ic] - 1)
				{
					baseKnight[ir][ic] = baseKnight[idx[0]][idx[1]] + 1;
				}
			}
		}
		
		int[][][][] knightDist = new int[r][c][r][c];
		for (int ir = 0; ir < r; ir++) for (int ic = 0; ic < c; ic++) for (int jr = 0; jr < r; jr++) for (int jc = 0; jc < c; jc++)
		{
			int dr = Math.abs(ir - jr);
			int dc = Math.abs(ic - jc);
			knightDist[ir][ic][jr][jc] = baseKnight[dr][dc];
			if (dr == 1 && dc == 1)
			{
				if ((ir == 0 && ic == 0) || (ir == 0 && ic == c - 1) || (ir == r - 1 && ic == 0) || (ir == r - 1 && ic == c - 1) ||
						(jr == 0 && jc == 0) || (jr == 0 && jc == c - 1) || (jr == r - 1 && jc == 0) || (jr == r - 1 && jc == c - 1))
				{
					knightDist[ir][ic][jr][jc] = 4;
				}
			}
			else if (dr + dc == 2)
			{
				if (dr == 2 && ic == 1 && c == 3)
				{
					knightDist[ir][ic][jr][jc] = 4;
				}
				else if (dc == 2 && ir == 1 && r == 3)
				{
					knightDist[ir][ic][jr][jc] = 4;
				}
			}
		}
		
		int minMoves = Integer.MAX_VALUE;
		for (int tr = 0; tr < r; tr++) for (int tc = 0; tc < c; tc++)
		{
			int moves = 0;
			for (int kr = 0; kr < r; kr++) for (int kc = 0; kc < c; kc++)
			{
				if (knights[kr][kc])
				{
					if (knightDist[kr][kc][tr][tc] == Integer.MAX_VALUE || moves == Integer.MAX_VALUE) moves = Integer.MAX_VALUE;
					else moves += knightDist[kr][kc][tr][tc];
				}
			}
			
			int minKingMoves = Integer.MAX_VALUE;
			for (int kr = 0; kr < r; kr++) for (int kc = 0; kc < c; kc++)
			{
				if (knights[kr][kc])
				{
					int kingMoves = knightDist[king[0]][king[1]][tr][tc] + knightDist[kr][kc][king[0]][king[1]] - knightDist[kr][kc][tr][tc];
					if (kingMoves < 0) kingMoves = Integer.MAX_VALUE;
					if (kingMoves < minKingMoves) minKingMoves = kingMoves;
				}
			}
			for (int mr = 0; mr < r && minKingMoves > 1; mr++) for (int mc = 0; mc < c && minKingMoves > 1; mc++) for (int kr = 0; kr < r; kr++) for (int kc = 0; kc < c; kc++)
			{
				if (knights[kr][kc])
				{
					int kingMoves = knightDist[mr][mc][tr][tc] + knightDist[kr][kc][mr][mc] + kingDist(king[0], king[1], mr, mc) - knightDist[kr][kc][tr][tc];
					if (kingMoves < 0) kingMoves = Integer.MAX_VALUE;
					if (kingMoves < minKingMoves) minKingMoves = kingMoves;
				}
			}
			if (minKingMoves == Integer.MAX_VALUE || moves == Integer.MAX_VALUE) moves = Integer.MAX_VALUE;
			else moves += minKingMoves;
			
			if (moves < minMoves)
			{
				minMoves = moves;
			}
		}
		if (minMoves == Integer.MAX_VALUE) minMoves = 0;
		
		out.println(minMoves);
		out.close();
	}
	
	public static boolean isKnightMove(int ir, int ic, int jr, int jc)
	{
		int dr = Math.abs(ir - jr);
		int dc = Math.abs(ic - jc);
		return dr * dc == 2;
	}
	
	public static int kingDist(int ir, int ic, int jr, int jc)
	{
		return Math.max(Math.abs(ir - jr), Math.abs(ic - jc));
	}
}
