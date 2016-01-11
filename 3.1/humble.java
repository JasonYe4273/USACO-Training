/*
ID: yuzhou.1
LANG: JAVA
TASK: humble
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class humble
{
	public static void main(String[] args) throws IOException
	{
		long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] primes = new int[k];
		MinMaxHeapHash h = new MinMaxHeapHash(n);
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < k; i++)
		{
			primes[i] = Integer.parseInt(st.nextToken());
			h.add(primes[i]);
		}
		Arrays.sort(primes);
		in.close();
		
		long next;
		for (int i = 0; i < n - 1; i++)
		{
			next = h.removeMin();
			h.n--;
			//if (h.checkHeap()) throw new IOException("EVERYTHING IS ON FIRE! (While removing " + next + ")");
			for (int j = 0; j < k; j++)
			{
				long toAdd = next * primes[j];
				if (!h.set.contains(toAdd))
				{
					if (!h.add(toAdd))
					{
						//if (h.checkHeap()) throw new IOException("EVERYTHING IS ON FIRE! (While adding " + toAdd + ")");
						break;
					}
				}
			}
			//h.n--;
			//if (h.length > h.n) h.removeMax();
		}
		
		out.println(h.minHeap[0]);
		out.close();
		System.out.println(System.currentTimeMillis() - start);
	}
	
	static class MinMaxHeapHash
	{
		public HashSet<Long> set;
		public long[] minHeap;
		public long[] maxHeap;
		public int[] maxToMin;
		public int[] minToMax;
		public int length;
		public int n;
		
		public MinMaxHeapHash(int n)
		{
			this.n = n;
			length = 0;
			minHeap = new long[n];
			maxHeap = new long[n];
			maxToMin = new int[n];
			minToMax = new int[n];
			set = new HashSet<Long>();
		}
		
		public boolean checkHeap()
		{
			boolean toReturn = false;
			for (int i = 1; i < length; i++)
			{
				if (minHeap[i] < minHeap[(i - 1) / 2])
				{
					System.out.println("Heap error in minHeap at " + (i - 1) / 2 + " and " + i + ": " + minHeap[(i - 1) / 2] + " > " + minHeap[i]);
					toReturn = true;
				}
				if (maxHeap[i] > maxHeap[(i - 1) / 2])
				{
					System.out.println("Heap error in maxHeap at " + (i - 1) / 2 + " and " + i + ": " + maxHeap[(i - 1) / 2] + " < " + maxHeap[i]);
					toReturn = true;
				}
			}
			return toReturn;
		}
		
		public void printHeaps()
		{
			System.out.print("Min Heap:   ");
			for (int i = 0; i < length; i++) System.out.print(minHeap[i] + " ");
			System.out.println();
			
			System.out.print("Min To Max: ");
			for (int i = 0; i < length; i++) System.out.print(minToMax[i] + " ");
			System.out.println();
			
			for (int i = 0; i < length; i++) if (minToMax[maxToMin[i]] != i) System.out.println("Connection error at minToMax[" + i + "]");
			
			System.out.print("Max To Min: ");
			for (int i = 0; i < length; i++) System.out.print(maxToMin[i] + " ");
			System.out.println();
			
			System.out.print("Max Heap:   ");
			for (int i = 0; i < length; i++) System.out.print(maxHeap[i] + " ");
			System.out.println();
			System.out.println();
		}
		
		public boolean add(long a)
		{
			if (length >= n)
			{
				if (a >= maxHeap[0]) return false;
				maxHeap[0] = a;
				minHeap[maxToMin[0]] = a;
				
				minHeapify(maxToMin[0]);
				maxHeapify(0);
				
				set.add(a);
				return true;
			}

			set.add(a);
			length++;
			int index = length - 1;
			minHeap[index] = a;
			maxHeap[index] = a;
			minToMax[index] = index;
			maxToMin[index] = index;
			int nextIndex = (index - 1) / 2;
			while (minHeap[nextIndex] > a)
			{
				switchMin(index, nextIndex);
				index = nextIndex;
				nextIndex = (index - 1) / 2;
			}
			
			index = length - 1;
			nextIndex = (index - 1) / 2;
			while (maxHeap[nextIndex] < a)
			{
				switchMax(index, nextIndex);
				index = nextIndex;
				nextIndex = (index - 1) / 2;
			}
			return true;
		}
		
		public long removeMax()
		{
			long toReturn = maxHeap[0];
			set.remove(toReturn);
			length--;
			int minIndex = maxToMin[0];
			switchMin(minIndex, length);
			minHeapify(minIndex);
			switchMax(0, length);
			maxHeapify(0);
			return toReturn;
		}
		
		public long removeMin()
		{
			long toReturn = minHeap[0];
			set.remove(toReturn);
			length--;
			int maxIndex = minToMax[0];
			
			switchMax(maxIndex, length);
			switchMin(0, length);
			maxHeapify(maxIndex);
			minHeapify(0);
			return toReturn;
		}
		
		public void maxHeapify(int i)
		{
			int p = (i - 1) / 2;
			if (maxHeap[p] < maxHeap[i])
			{
				switchMax(i, p);
				maxHeapify(p);
				return;
			}
			
			int max = i;
			int l = 2 * i + 1;
			if (l < length && maxHeap[l] > maxHeap[max]) max = l;
			
			int r = 2 * i + 2;
			if (r < length && maxHeap[r] > maxHeap[max]) max = r;
			
			if (max == i) return;
			
			switchMax(i, max);
			
			maxHeapify(max);
		}
		
		public void minHeapify(int i)
		{
			int p = (i - 1) / 2;
			if (minHeap[p] > minHeap[i])
			{
				switchMin(i, p);
				minHeapify(p);
				return;
			}
			
			int min = i;
			int l = 2 * i + 1;
			if (l < length && minHeap[l] < minHeap[min]) min = l;
			
			int r = 2 * i + 2;
			if (r < length && minHeap[r] < minHeap[min]) min = r;
			
			if (min == i) return;
			
			switchMin(i, min);
			
			minHeapify(min);
		}
		
		public void switchMax(int a, int b)
		{
			long tempLong = maxHeap[a];
			maxHeap[a] = maxHeap[b];
			maxHeap[b] = tempLong;
			
			int aMin = maxToMin[a];
			int bMin = maxToMin[b];
			minToMax[bMin] = a;
			minToMax[aMin] = b;
			maxToMin[a] = bMin;
			maxToMin[b] = aMin;
		}
		
		public void switchMin(int a, int b)
		{
			long tempLong = minHeap[a];
			minHeap[a] = minHeap[b];
			minHeap[b] = tempLong;
			
			int aMax = minToMax[a];
			int bMax = minToMax[b];
			maxToMin[bMax] = a;
			maxToMin[aMax] = b;
			minToMax[a] = bMax;
			minToMax[b] = aMax;
		}
	}
}
