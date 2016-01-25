/*
ID: yuzhou.1
LANG: JAVA
TASK: nuggets
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class nuggets
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
		
		int gcd = 0;
		int n = Integer.parseInt(in.readLine());
		
		int[] nums = new int[n];
		for (int i = 0; i < n; i++)
		{
			nums[i] = Integer.parseInt(in.readLine());
			if (nums[i] == 1)
			{
				out.println(0);
				in.close();
				out.close();
				return;
			}
			gcd = gcd(gcd, nums[i]);
		}
		Arrays.sort(nums);
		in.close();
		
		if (gcd > 1)
		{
			out.println(0);
			out.close();
			return;
		}
		
		ArrayList<Integer> numList = new ArrayList<Integer>();
		for (int i = 0; i < n; i++)
		{
			if (!canMake(nums[i], numList))
			{
				numList.add(nums[i]);
			}
		}
		
		int smallest = numList.get(0);
		int consecutive = 0;
		int consStart = 0;
		int prev = 0;
		MinHeap h = new MinHeap(256 * 256);
		for (int num : numList)
		{
			h.add(num);
		}
		HashSet<Integer> reachableSet = new HashSet<Integer>(numList);
		
		while (consecutive < smallest)
		{
			int curr = h.removeMin();
			for (int num : numList)
			{
				if (curr + num > 2000000000) break;
				if (!reachableSet.contains(curr + num))
				{
					h.add(curr + num);
					reachableSet.add(curr + num);
				}
			}
			if (curr == prev + 1) consecutive++;
			else
			{
				consStart = curr;
				consecutive = 1;
			}
			prev = curr;
		}
		
		out.println(consStart - 1);
		out.close();
	}
	
	public static int gcd(int a, int b)
	{
		if (a == 0) return b;
		if (b == 0) return a;
		if (a == 1 || b == 1) return 1;
		if (a == b) return a;
		if (a > b) return gcd(a % b, b);
		return gcd(a, b % a);
	}
	
	public static int lcm(int a, int b)
	{
		return a * b / gcd(a, b);
	}
	
	public static boolean canMake(int target, ArrayList<Integer> nuggets)
	{
		boolean[] canMake = new boolean[target + 1];
		canMake[0] = true;
		for (int i = 0; i <= target; i++)
		{
			if (canMake[i])
			{
				for (int n : nuggets)
				{
					if (i + n > target) break;
					canMake[i + n] = true;
				}
			}
		}
		return canMake[target];
	}
	
	static class MinHeap
	{
		public int[] minHeap;
		public int length;
		
		public MinHeap(int n)
		{
			length = 0;
			minHeap = new int[n];
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
			}
			return toReturn;
		}
		
		public void printHeaps()
		{
			System.out.print("Min Heap:   ");
			for (int i = 0; i < length; i++) System.out.print(minHeap[i] + " ");
			System.out.println();
		}
		
		public boolean add(int a)
		{
			length++;
			int index = length - 1;
			minHeap[index] = a;
			int nextIndex = (index - 1) / 2;
			while (minHeap[nextIndex] > a)
			{
				switchMin(index, nextIndex);
				index = nextIndex;
				nextIndex = (index - 1) / 2;
			}
			return true;
		}
		
		public int removeMin()
		{
			int toReturn = minHeap[0];
			length--;
			switchMin(0, length);
			minHeapify(0);
			return toReturn;
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
		
		public void switchMin(int a, int b)
		{
			int tempLong = minHeap[a];
			minHeap[a] = minHeap[b];
			minHeap[b] = tempLong;
		}
	}
}
