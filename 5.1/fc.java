/*
ID: yuzhou.1
LANG: JAVA
TASK: fc
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class fc
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("fc.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fc.out")));
		
		int n = Integer.parseInt(in.readLine());
		
		double[] x = new double[n];
		double[] y = new double[n];
		double cx = Double.MAX_VALUE;
		double cy = Double.MAX_VALUE;
		for (int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			x[i] = Double.parseDouble(st.nextToken());
			y[i] = Double.parseDouble(st.nextToken());
			if (y[i] < cy)
			{
				cy = y[i];
				cx = x[i];
			}
			else if (y[i] == cy && x[i] < cx) cx = x[i];
		}
		in.close();
		
		TransPoint[] translated = new TransPoint[n];
		for (int i = 0; i < n; i++) translated[i] = new TransPoint(x[i], y[i], cx, cy);
		Arrays.sort(translated);
		
		Heap h = new Heap(n);
		h.push(translated[0]);
		for (int i = 1; i < n; i++)
		{
			h.push(translated[i]);
			while (h.size > 1 && isRightTurn(h.peekFurther(), h.peek(), translated[(i + 1) % n])) h.pop();
		}
		System.out.println();

		double perim = translated[0].dist(h.peek());
		while (h.size > 1)
		{
			perim += h.pop().dist(h.peek());
		}
		
		out.println(String.format("%.2f", perim));
		out.close();
	}
	
	static class Heap
	{
		public TransPoint[] h;
		public int size;
		
		public Heap(int max)
		{
			h = new TransPoint[max];
		}
		
		public void push(TransPoint p)
		{
			h[size++] = p;
		}
		
		public TransPoint pop()
		{
			return h[--size];
		}
		
		public TransPoint peek()
		{
			return h[size - 1];
		}
		
		public TransPoint peekFurther()
		{
			return h[size - 2];
		}
	}
	
	static class TransPoint implements Comparable<TransPoint>
	{
		public double x;
		public double y;
		public double dist;
		public double slope;
		public int quad;
		
		public TransPoint(double x, double y, double cx, double cy)
		{
			this.x = x - cx;
			this.y = y - cy;
			dist = Math.sqrt(this.x * this.x + this.y * this.y);
			
			if (this.x == 0 && this.y == 0)
			{
				quad = 0;
				return;
			}
			
			if (this.x == 0)
			{
				quad = this.y > 0 ? 2 : 4;
				slope = Double.MIN_VALUE;
				return;
			}
			if (this.y == 0)
			{
				quad = this.x > 0 ? 1 : 3;
				slope = 0;
				return;
			}
			
			slope = this.y / this.x;
			quad = this.x > 0 ? (this.y > 0 ? 1 : 4) : (this.y > 0 ? 2 : 3);
		}
		
		@Override
		public int compareTo(TransPoint p)
		{
			if (x == p.x && y == p.y) return 0;
			if (quad < p.quad) return -1;
			if (quad > p.quad) return 1;
			if (slope < p.slope) return -1;
			if (slope > p.slope) return 1;
			return y < p.y ? -1 : 1;
		}
		
		public double dist(TransPoint p)
		{
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}
	}
	
	public static void check(int idx, ArrayList<Integer> hull, TransPoint[] points)
	{
		int size = hull.size();
		if (size == 2) return;
		int nextIdx = (idx + 1) % size;
		int prevIdx = (idx - 1 + size) % size;
		
		TransPoint middle = points[hull.get(idx)];
		TransPoint next = points[hull.get(nextIdx)];
		TransPoint prev = points[hull.get(prevIdx)];
		
		if (!isRightTurn(prev, middle, next)) return;
		hull.remove(idx);
		
		check(idx % (size - 1), hull, points);
		check(idx == 0 ? size - 2 : idx - 1, hull, points);
	}
	
	public static boolean isRightTurn(TransPoint prev, TransPoint middle, TransPoint next)
	{
		TransPoint nextTrans = new TransPoint(next.x, next.y, middle.x, middle.y);
		TransPoint prevTrans = new TransPoint(prev.x, prev.y, middle.x, middle.y);
		return prevTrans.x * nextTrans.y > prevTrans.y * nextTrans.x;
	}
}
