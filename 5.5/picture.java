/*
ID: yuzhou.1
LANG: JAVA
TASK: picture
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class picture
{
	public static void main(String[] args) throws IOException
	{
		long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("picture.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("picture.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		Rect[] rects = new Rect[n];
		for (int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int sx = Integer.parseInt(st.nextToken()) + 10001;
			int sy = Integer.parseInt(st.nextToken()) + 10001;
			int ex = Integer.parseInt(st.nextToken()) + 10001;
			int ey = Integer.parseInt(st.nextToken()) + 10001;
			
			rects[i] = new Rect(sx, sy, ex, ey);
			
		}
		Arrays.sort(rects);
		in.close();
		
		HashMap<Integer, LineCombo> horizCover = new HashMap<Integer, LineCombo>();
		HashMap<Integer, LineCombo> vertCover = new HashMap<Integer, LineCombo>();
		
		for (int i = n - 2; i >= 0; i--)
		{
			Rect curr = rects[i];
			for (int j = i + 1; j < n; j++)
			{
				Rect prev = rects[j];
				if (prev.minSlope > curr.maxSlope) break;
				
				Line[] iLines = curr.intersection(prev);
				if (iLines != null)
				{
					Line horiz = iLines[0];
					Line vert = iLines[1];
					
					if (horiz.e > horiz.s)
					{
						int y = vert.s;
						if (y == curr.sy)
						{
							curr.bottom.lines.add(horiz);
							if (y == prev.sy)
							{
								prev.bottom.lines.add(horiz);
								if (horizCover.containsKey(y))
								{
									horizCover.get(y).lines.add(horiz);
								}
								else
								{
									LineCombo c = new LineCombo();
									c.lines.add(horiz);
									horizCover.put(y, c);
								}
							}
						}
						else if (y == prev.sy) prev.bottom.lines.add(horiz);
						
						y = vert.e;
						if (y == curr.ey)
						{
							curr.top.lines.add(horiz);
							if (y == prev.ey)
							{
								prev.top.lines.add(horiz);
								if (horizCover.containsKey(y))
								{
									horizCover.get(y).lines.add(horiz);
								}
								else
								{
									LineCombo c = new LineCombo();
									c.lines.add(horiz);
									horizCover.put(y, c);
								}
							}
						}
						else if (y == prev.ey) prev.top.lines.add(horiz);
					}
					
					if (vert.e != vert.s)
					{
						int x = horiz.s;
						if (x == curr.sx)
						{
							curr.left.lines.add(vert);
							if (x == prev.sx)
							{
								prev.left.lines.add(vert);
								if (vertCover.containsKey(x))
								{
									vertCover.get(x).lines.add(vert);
								}
								else
								{
									LineCombo c = new LineCombo();
									c.lines.add(vert);
									vertCover.put(x, c);
								}
							}
						}
						else if (x == prev.sx) prev.left.lines.add(vert);
						
						x = horiz.e;
						if (x == curr.ex)
						{
							curr.right.lines.add(vert);
							if (x == prev.ex)
							{
								prev.right.lines.add(vert);
								if (vertCover.containsKey(x))
								{
									vertCover.get(x).lines.add(vert);
								}
								else
								{
									LineCombo c = new LineCombo();
									c.lines.add(vert);
									vertCover.put(x, c);
								}
							}
						}
						else if (x == prev.ex) prev.right.lines.add(vert);
					}
				}
			}
		}
		
		for (int y : horizCover.keySet())
		{
			for (Rect r : rects)
			{
				if (y > r.sy && y < r.ey)
				{
					horizCover.get(y).toRemove.add(r.horiz);
				}
			}
		}
		
		for (int x : vertCover.keySet())
		{
			for (Rect r : rects)
			{
				if (x > r.sx && x < r.ex)
				{
					vertCover.get(x).toRemove.add(r.vert);
				}
			}
		}
		
		int perim = 0;
		for (Rect r : rects) perim += r.perim();
		
		for (LineCombo c : horizCover.values())
		{
			c.length();
			perim += c.length();
		}
		for (LineCombo c : vertCover.values())
		{
			c.length();
			perim += c.length();
		}

		System.out.println(System.currentTimeMillis() - start);
		out.println(perim);
		out.close();
	}
	
	static class Line implements Comparable<Line>
	{
		int s;
		int e;
		
		public Line(int start, int end)
		{
			s = start;
			e = end;
		}
		
		public boolean intersects(Line l)
		{
			return (s > l.s ? s : l.s) < (e < l.e ? e : l.e);
		}

		public boolean eIntersects(Line l)
		{
			return (s > l.s ? s : l.s) <= (e < l.e ? e : l.e);
		}
		
		public Line union(Line l)
		{
			return new Line(s < l.s ? s : l.s, e > l.e ? e : l.e);
		}
		
		public Line intersection(Line l)
		{
			return new Line(s > l.s ? s : l.s, e < l.e ? e : l.e);
		}
		
		public Line left(Line l)
		{
			if (s < l.s) return new Line(s, l.s);
			return null;
		}
		
		public Line right(Line l)
		{
			if (e > l.e) return new Line(l.e, e);
			return null;
		}
		
		@Override
		public int compareTo(Line l)
		{
			if (s < l.s) return -1;
			if (s > l.s) return 1;
			return 0;
		}
	}
	
	static class LineCombo
	{
		ArrayList<Line> lines;
		ArrayList<Line> toRemove;
		
		public LineCombo()
		{
			lines = new ArrayList<Line>();
			toRemove = new ArrayList<Line>();
		}
		
		public int length()
		{
			boolean remove = !toRemove.isEmpty();
			
			int length = 0;
			if (lines.isEmpty()) return length;
			ArrayList<Line> newSet = new ArrayList<Line>();
			Collections.sort(lines);
			Iterator<Line> it = lines.iterator();
			
			Line curr = it.next();
			Line next = curr;
			while (it.hasNext())
			{
				next = it.next();
				if (curr.eIntersects(next))
				{
					curr = curr.union(next);
				}
				else
				{
					if (remove) newSet.add(curr);
					length += curr.e - curr.s;
					curr = next;
				}
			}
			if (remove) newSet.add(curr);
			length += curr.e - curr.s;
			
			if (remove)
			{
				Collections.sort(newSet);
				lines = newSet;
				
				LineCombo removeCombo = new LineCombo();
				removeCombo.lines = toRemove;
				removeCombo.length();
				toRemove = removeCombo.lines;
				
				it = lines.iterator();
				Iterator<Line> removeIt = toRemove.iterator();
				
				newSet = new ArrayList<Line>();
				curr = it.next();
				it.remove();
				outer:
				while (removeIt.hasNext())
				{
					Line currRemove = removeIt.next();
					
					while (!currRemove.intersects(curr))
					{
						if (curr.s >= currRemove.e) continue outer;
						newSet.add(curr);
						if (!it.hasNext())
						{
							curr = null;
							break outer;
						}
						curr = it.next();
						it.remove();
					}
					
					while (true)
					{
						if (currRemove.intersects(curr))
						{
							length -= curr.e - curr.s;
							Line left = curr.left(currRemove);
							Line right = curr.right(currRemove);
							
							if (left != null)
							{
								newSet.add(left);
								length += curr.e - curr.s;
							}
							
							if (right != null)
							{
								curr = right;
								length += curr.e - curr.s;
								continue outer;
							}
							
							if (!it.hasNext())
							{
								curr = null;
								break outer;
							}
							curr = it.next();
							it.remove();
						}
						else break;
					}
				}
				if (curr != null) newSet.add(curr);
				
				lines = newSet;
				toRemove = new ArrayList<Line>();
			}
			
			return length;
		}
	}
	
	static class Rect implements Comparable<Rect>
	{
		int sx;
		int sy;
		int ex;
		int ey;
		double minSlope;
		double maxSlope;

		Line horiz;
		Line vert;
		
		LineCombo bottom;
		LineCombo top;
		LineCombo left;
		LineCombo right;
		
		public Rect(int sx, int sy, int ex, int ey)
		{
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
			minSlope = (double) sy / (double) ex;
			maxSlope = (double) ey / sx;
			
			horiz = new Line(sx, ex);
			vert = new Line(sy, ey);
			
			top = new LineCombo();
			bottom = new LineCombo();
			left = new LineCombo();
			right = new LineCombo();
		}
		
		@Override
		public int compareTo(Rect r)
		{
			if (minSlope < r.minSlope) return -1;
			if (minSlope > r.minSlope) return 1;
			return 0;
		}
		
		public int perim()
		{
			return 2 * (ex + ey - sx - sy) - bottom.length() - top.length() - left.length() - right.length();
		}
	
		public Line[] intersection(Rect r)
		{
			if (horiz.eIntersects(r.horiz) && vert.eIntersects(r.vert))
			{
				Line iHoriz = horiz.intersection(r.horiz);
				Line iVert = vert.intersection(r.vert);
				
				return new Line[]{ iHoriz, iVert };
			}
			return null;
		}
	}
}