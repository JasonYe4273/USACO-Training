package platinum2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mowing
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("mowing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mowing.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		int[] currPos = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		Queue q = new Queue(t, 4);
		
		for (int i = 2; i < t + 2; i++)
		{
			st = new StringTokenizer(in.readLine());
			int[] nextPos = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			boolean horiz = currPos[0] == nextPos[0];
			
			if (horiz)
			{
				if (currPos[1] > nextPos[1]) q.enqueue(new int[]{ nextPos[0], nextPos[1], currPos[0], currPos[1] });
				else q.enqueue(new int[]{ currPos[0], currPos[1], nextPos[0], nextPos[1] });
			}
			else
			{
				if (currPos[0] > nextPos[0]) q.enqueue(new int[]{ nextPos[0], nextPos[1], currPos[0], currPos[1] });
				else q.enqueue(new int[]{ currPos[0], currPos[1], nextPos[0], nextPos[1] });
			}
			
			currPos = nextPos;
		}
		
		int intersect = 0;
		ArrayList<int[]> horizontal = new ArrayList<int[]>();
		ArrayList<int[]> vertical = new ArrayList<int[]>();
		for (int i = t + 2; i <= n; i++)
		{
			st = new StringTokenizer(in.readLine());
			int[] nextPos = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			boolean horiz = currPos[0] == nextPos[0];
			
			if (horiz)
			{
				int[] toAdd = q.dequeue();
				int place = 0;
				while (place < horizontal.size() && toAdd[0] > horizontal.get(place)[0]) place++;
				horizontal.add(place, toAdd);
				
				if (currPos[1] > nextPos[1])
				{
					for (int[] v : vertical)
					{
						if (v[1] >= currPos[1]) break;
						if (v[1] > nextPos[1] && v[0] < currPos[0] && v[2] > currPos[0])
						{
							intersect++;
						}
					}
					
					q.enqueue(new int[]{ nextPos[0], nextPos[1], currPos[0], currPos[1] });
				}
				else
				{
					for (int[] v : vertical)
					{
						if (v[1] >= nextPos[1]) break;
						if (v[1] > currPos[1] && v[0] < currPos[0] && v[2] > currPos[0])
						{
							intersect++;
						}
					}
					
					q.enqueue(new int[]{ currPos[0], currPos[1], nextPos[0], nextPos[1] });
				}
			}
			else
			{
				int[] toAdd = q.dequeue();
				int place = 0;
				while (place < vertical.size() && toAdd[1] > vertical.get(place)[1]) place++;
				vertical.add(place, toAdd);
				
				if (currPos[0] > nextPos[0])
				{
					for (int[] h : horizontal)
					{
						if (h[0] >= currPos[0]) break;
						if (h[0] > nextPos[0] && h[1] < currPos[1] && h[3] > currPos[1])
						{
							intersect++;
						}
					}
					
					q.enqueue(new int[]{ nextPos[0], nextPos[1], currPos[0], currPos[1] });
				}
				else
				{
					for (int[] h : horizontal)
					{
						if (h[0] >= nextPos[0]) break;
						if (h[0] > currPos[0] && h[1] < currPos[1] && h[3] > currPos[1])
						{
							intersect++;
						}
					}
					
					q.enqueue(new int[]{ currPos[0], currPos[1], nextPos[0], nextPos[1] });
				}
			}
			
			currPos = nextPos;
		}
		in.close();
		
		out.println(intersect);
		out.close();
	}
	
	static class Queue
	{
		int[][] q;
		int t;
		int head;
		int length;
		
		public Queue(int t, int l)
		{
			q = new int[t][l];
			this.t = t;
		}
		
		public void enqueue(int[] line)
		{
			q[(head + length++) % t] = line;
		}
		
		public int[] dequeue()
		{
			int[] toReturn = q[head];
			head = (head + 1) % t;
			length--;
			return toReturn;
		}
	}
}
