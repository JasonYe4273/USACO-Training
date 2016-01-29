/*
ID: yuzhou.1
LANG: JAVA
TASK: starry
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class starry
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("starry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("starry.out")));
		
		int w = Integer.parseInt(in.readLine());
		int h = Integer.parseInt(in.readLine());
		
		char[][] stars = new char[h][];
		for (int i = 0; i < h; i++)
		{
			stars[i] = in.readLine().trim().toCharArray();
		}
		in.close();
		
		Shape[] shapes = new Shape[26];
		char curr = 'a';
		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < w; j++)
			{
				if (stars[i][j] == '1')
				{
					initShape(stars, i, j);
					Shape newShape = new Shape(w, h, stars, curr);
					
					boolean filled = false;
					for (int s = 0; s < curr - 'a' && !filled; s++)
					{
						if (newShape.equals(shapes[s]))
						{
							fill(stars, (char) (s + 'a'));
							filled = true;
							break;
						}
					}
					
					if (!filled)
					{
						shapes[curr - 'a'] = newShape;
						fill(stars, curr);
						curr++;
					}
				}
			}
		}
		
		for (int i = 0; i < h; i++)
		{
			out.println(String.valueOf(stars[i]));
		}
		out.close();
	}
	
	public static void fill(char[][] stars, char c)
	{
		for (int i = 0; i < stars.length; i++)
		{
			for (int j = 0; j < stars[0].length; j++)
			{
				if (stars[i][j] == '?') stars[i][j] = c;
			}
		}
	}
	
	public static void initShape(char[][] stars, int si, int sj)
	{
		stars[si][sj] = '?';
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				int ei = si + i;
				int ej = sj + j;
				if (ei >= 0 && ei < stars.length && ej >= 0 && ej < stars[0].length && stars[ei][ej] == '1') initShape(stars, ei, ej);
			}
		}
	}
	
	static class Shape
	{
		char name;
		int width;
		int height;
		boolean[][] isStar;
		
		public Shape(int w, int h, char[][] stars, char n)
		{
			name = n;
			
			int sh = h;
			int eh = -1;
			int sw = w;
			int ew = -1;
			for (int i = 0; i < h; i++)
			{
				for (int j = 0; j < w; j++)
				{
					if (stars[i][j] == '?')
					{
						if (i < sh) sh = i;
						if (i > eh) eh = i;
						if (j < sw) sw = j;
						if (j > ew) ew = j;
					}
				}
			}
			
			height = eh - sh + 1;
			width = ew - sw + 1;
			isStar = new boolean[height][width];
			for (int i = sh; i <= eh; i++)
			{
				for (int j = sw; j <= ew; j++)
				{
					if (stars[i][j] == '?')
					{
						isStar[i - sh][j - sw] = true;
					}
				}
			}
		}
		
		public boolean equals(Object o)
		{
			Shape other = (Shape) o;
			if (height == other.height && width == other.width)
			{
				boolean matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[i][j])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
				
				matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[height - 1 - i][j])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
				
				matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[height - 1 - i][width - 1 - j])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
				
				matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[i][width - 1 - j])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
			}
			
			if (height == other.width && width == other.height)
			{
				boolean matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[j][i])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
				
				matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[j][height - 1 - i])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
				
				matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[width - 1 - j][height - 1 - i])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
				
				matches = true;
				for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
					{
						if (isStar[i][j] ^ other.isStar[width - 1 - j][i])
						{
							matches = false;
							break;
						}
					}
				}
				if (matches) return true;
			}
			
			return false;
		}
	}
}
