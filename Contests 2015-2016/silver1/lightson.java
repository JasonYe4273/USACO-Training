package silver1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lightson
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("lightson.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("lightson.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		boolean[] on = new boolean[n * n];
		for (int i = 0; i < n * n; i++) on[i] = false;
		on[0] = true;
		
		ArrayList<int[]> switches = new ArrayList<int[]>();
		for (int i = 0; i < m; i++)
		{
			st = new StringTokenizer(in.readLine());
			int room = Integer.parseInt(st.nextToken()) - 1 + n * (Integer.parseInt(st.nextToken()) - 1);
			int toLight = Integer.parseInt(st.nextToken()) - 1 + n * (Integer.parseInt(st.nextToken()) - 1);
			switches.add(new int[] { room, toLight });
		}
		
		Network network = new Network(n * n);
		int numLighted = 1;
		int prevNum = 0;
		while (numLighted != prevNum)
		{
			prevNum = numLighted;
			ArrayList<Integer> toRemove = new ArrayList<Integer>();
			for (int i = 0; i < switches.size(); i++)
			{
				if (on[switches.get(i)[0]] && network.connected(switches.get(i)[0], 0) && !on[switches.get(i)[1]])
				{
					int index = switches.get(i)[1];
					on[index] = true;
					if (index % n > 0 && on[index - 1]) network.join(index, index - 1);
					if (index % n < n - 1 && on[index + 1]) network.join(index, index + 1);
					if (index / n > 0 && on[index - n]) network.join(index, index - n);
					if (index / n < n - 1 && on[index + n]) network.join(index, index + n);
					toRemove.add(i);
					numLighted++;
				}
			}
			for (int i = toRemove.size() - 1; i >= 0; i--)
			{
				switches.remove((int) toRemove.get(i));
			}
		}
        in.close();
		
		//for (int i = 0; i < on.length; i++) if(on[i]) System.out.println(i);
		//for (int parent : network.parent) System.out.println(parent);
		
		/*ArrayList<Integer>[] switches = new ArrayList[n * n];
		for(int i = 0; i < n * n; i++)
		{
			switches[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < m; i++)
		{
			st = new StringTokenizer(in.readLine());
			int index = Integer.parseInt(st.nextToken()) + n * Integer.parseInt(st.nextToken());
			switches[index].add(Integer.parseInt(st.nextToken()) + n * Integer.parseInt(st.nextToken()));
		}

		Network lighted = new Network(n * n);
		int numLighted = 1;
		ArrayList<Integer> newRooms = new ArrayList<Integer>();
		newRooms.add(0);
		while (!newRooms.isEmpty())
		{
			ArrayList<Integer> newNewRooms = new ArrayList<Integer>();
			for (int room : newRooms)
			{
				for (int index : switches[room])
				{
					if (!on[index])
					{
						on[index] = true;
						numLighted++;
						if (index % n > 0 && on[index - 1]) lighted.join(index, index - 1);
						if (index % n < n - 1 && on[index + 1]) lighted.join(index, index + 1);
						if (index / n > 0 && on[index - n]) lighted.join(index, n);
						if (index / n < n - 1 && on[index + n]) lighted.join(index, index + n);
					}
				}
			}
		}*/
		
		//System.out.println(numLighted);
		out.write(String.valueOf(numLighted));
		out.close();
	}
	
	public static class Network
	{
		public int size;
		public int[] parent;
		//public ArrayList<Integer>[] children;
		public int[] depth;
		
		public Network(int n)
		{
			size = n;
			parent = new int[n];
			//children = new ArrayList[n];
			depth = new int[n];
			for (int i = 0; i < n; i++)
			{
				parent[i] = i;
				depth[i] = 0;
			}
		}
		
		private int findRoot(int node)
		{
			while (node != parent[node])
			{
				node = parent[node];
			}
			return node;
		}
		
		public void join(int node1, int node2)
		{
			//System.out.println(node1 + " " + node2);
			int root1 = findRoot(node1);
			int root2 = findRoot(node2);
			if (root1 == root2) return;
			if (depth[root1] > depth[root2])
			{
				parent[root2] = root1;
			}
			else
			{
				parent[root1] = root2;
				if (depth[root1] == depth[root2]) depth[root2]++;
			}
		}
		
		public boolean connected(int node1, int node2)
		{
			return findRoot(node1) == findRoot(node2);
		}
	}
}
