/*
ID: yuzhou.1
LANG: JAVA
TASK: window
 */

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class window
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("window.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));
		
		Windows xp = new Windows(62);
		
		String line = in.readLine();
		while (line != null)
		{
			char command = line.charAt(0);
			if (command == 'w')
			{
				StringTokenizer param = new StringTokenizer(line.substring(2, line.length() - 1));
				xp.w(getIndexFrom(param.nextToken(",").charAt(0)), Integer.parseInt(param.nextToken(",")), Integer.parseInt(param.nextToken(",")), Integer.parseInt(param.nextToken(",")), Integer.parseInt(param.nextToken(",")));
			}
			else if (command == 't')
			{
				xp.t(getIndexFrom(line.charAt(2)));
			}
			else if (command == 'b')
			{
				xp.b(getIndexFrom(line.charAt(2)));
			}
			else if (command == 'd')
			{
				xp.d(getIndexFrom(line.charAt(2)));
			}
			else if (command == 's')
			{
				out.println(String.format("%.3f", xp.s(getIndexFrom(line.charAt(2)))));
			}
			
			line = in.readLine();
		}
		in.close();
		out.close();
	}
	
	public static int getIndexFrom(char c)
	{
		if (c >= '0' && c <= '9') return c - '0';
		if (c >= 'a' && c <= 'z') return 10 + c - 'a';
		if (c >= 'A' && c <= 'Z') return 36 + c - 'A';
		return -1;
	}

	public static class Windows
	{
		public Window top;
		public Window bottom;
		public Window[] windowsByI;
		
		public Windows(int max)
		{
			top = null;
			bottom = null;
			windowsByI = new Window[max];
		}
		
		public void w(int i, int x1, int y1, int x2, int y2)
		{
			Window newTop = new Window(i, Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));	
			windowsByI[i] = newTop;
			
			if (top != null)
			{
				top.above = newTop;
				newTop.below = top;
			}
			else bottom = newTop;
			top = newTop;
		}
		
		public void t(int i)
		{
			Window newTop = windowsByI[i];
			if (newTop == null) return;
			if (newTop.i == top.i) return;
			
			newTop.above.below = newTop.below;
			if (newTop.i != bottom.i) newTop.below.above = newTop.above;
			else bottom = newTop.above;
			
			top.above = newTop;
			newTop.below = top;
			newTop.above = null;
			top = newTop;
		}
		
		public void b(int i)
		{
			Window newBottom = windowsByI[i];
			if (newBottom == null) return;
			if (newBottom.i == bottom.i) return;
			
			newBottom.below.above = newBottom.above;
			if (newBottom.i != top.i) newBottom.above.below = newBottom.below;
			else top = newBottom.below;
			
			bottom.below = newBottom;
			newBottom.above = bottom;
			newBottom.below = null;
			bottom = newBottom;
		}
		
		public void d(int i)
		{
			Window toRemove = windowsByI[i];
			if (toRemove == null) return;
			
			if (toRemove.i == bottom.i)
			{
				if (toRemove.i == top.i)
				{
					bottom = null;
					top = null;
				}
				else
				{
					bottom = toRemove.above;
					bottom.below = null;
				}
			}
			else if (toRemove.i == top.i)
			{
				top = toRemove.below;
				top.above = null;
			}
			else
			{
				toRemove.above.below = toRemove.below;
				toRemove.below.above = toRemove.above;
			}
			
			windowsByI[i] = null;
		}
		
		public float s(int i)
		{
			Window w = windowsByI[i];
			if (w == null) return 0;
			
			ArrayList<Rectangle> components = new ArrayList<Rectangle>();
			components.add(w.rect);
			Window curr = top;
			while (curr.i != w.i)
			{
				ArrayList<Rectangle> toAdd = new ArrayList<Rectangle>();
				for (int j = 0; j < components.size(); j++)
				{
					if (components.get(j).intersects(curr.rect)) toAdd.addAll(coverAndSplit(components.remove(j--), curr.rect));
				}
				components.addAll(toAdd);
				
				curr = curr.below;
			}
			
			float area = 0;
			for (Rectangle r : components)
			{
				area += r.height * r.width;
			}
			return 100F * area / (w.h * w.w);
		}
	}
	
	public static class Window
	{
		public Window above;
		public Window below;
		public int i;
		public int x;
		public int y;
		public int w;
		public int h;
		public Rectangle rect;
		
		public Window(int i, int x, int y, int w, int h)
		{
			this.i = i;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			above = null;
			below = null;
			rect = new Rectangle(x, y, w, h);
		}
	}
	
	public static ArrayList<Rectangle> coverAndSplit(Rectangle toCover, Rectangle cover)
	{
		Rectangle intersect = toCover.intersection(cover);
		ArrayList<Rectangle> split = new ArrayList<Rectangle>();
		
		int[] x = new int[3];
		x[0] = toCover.x;
		x[1] = intersect.x;
		x[2] = intersect.x + intersect.width;
		
		int[] y = new int[3];
		y[0] = toCover.y;
		y[1] = intersect.y;
		y[2] = intersect.y + intersect.height;
		
		int[] w = new int[3];
		w[0] = intersect.x - toCover.x;
		w[1] = intersect.width;
		w[2] = toCover.width - w[1] - w[0];
		
		int[] h = new int[3];
		h[0] = intersect.y - toCover.y;
		h[1] = intersect.height;
		h[2] = toCover.height - h[1] - h[0];
		
		for (int i = 0; i < 3; i++)
		{
			if (w[i] == 0) continue;
			for (int j = 0; j < 3; j++)
			{
				if (h[j] == 0 || (i == 1 && j == 1)) continue;
				split.add(new Rectangle(x[i], y[j], w[i], h[j]));
			}
		}
		return split;
	}
}
