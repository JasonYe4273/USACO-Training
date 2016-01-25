package platinum2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lightsout
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("lightsout.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));
		
		int n = Integer.parseInt(in.readLine());
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}

		int[] cwDist = new int[n];
		int[] ccwDist = new int[n];
		boolean[] right = new boolean[n];
		for (int i = 0; i < n; i++)
		{
			int prev = (i + n - 1) % n;
			int next = (i + 1) % n;
			
			ccwDist[i] = Math.abs(x[i] + y[i] - x[prev] - y[prev]);
			cwDist[i] = Math.abs(x[next] + y[next] - x[i] - y[i]);
			
			char prevDir;
			if (x[prev] == x[i]) prevDir = y[i] > y[prev] ? 'U' : 'D';
			else prevDir = x[i] > x[prev] ? 'R' : 'L';
			char nextDir;
			if (x[next] == x[i]) nextDir = y[i] < y[next] ? 'U' : 'D';
			else nextDir = x[i] < x[next] ? 'R' : 'L';
			
			if (prevDir == 'U') right[i] = nextDir == 'R';
			else if (prevDir == 'D') right[i] = nextDir == 'L';
			else if (prevDir == 'L') right[i] = nextDir == 'U';
			else right[i] = nextDir == 'D';
		}
		in.close();

		int[] ccwExit = new int[n];
		for (int i = 1; i < n; i++)
		{
			ccwExit[i] = ccwExit[i - 1] + ccwDist[i];
		}
		
		int[] cwExit = new int[n];
		int[] opt = new int[n];
		for (int i = n - 1; i > 0; i--)
		{
			cwExit[i] = cwExit[(i + 1) % n] + cwDist[i];
			opt[i] = Math.min(cwExit[i], ccwExit[i]);
		}
		
		int turningPoint = 0;
		for (int i = 1; i < n; i++)
		{
			if (opt[i] == cwExit[i])
			{
				turningPoint = i;
				break;
			}
		}
		
		String cwCcwStr = "";
		for (int i = 0; i < turningPoint; i++) cwCcwStr += right[i] ? "R" : "L";
		String ccwCcwStr = "";
		for (int i = turningPoint - 1; i >= 0; i--) ccwCcwStr += right[i] ? "R" : "L";
		String ccwCwStr = "";
		for (int i = turningPoint; i < n; i++) ccwCwStr += right[i] ? "R" : "L";
		String cwCwStr = "";
		for (int i = n - 1; i >= turningPoint; i--) cwCwStr += right[i] ? "R" : "L";
		
		// Strategy: walk CW, and then turn if needed; no error starting from after turningPoint
		int maxCWError = 0;
		for (int i = 1; i < turningPoint; i++)
		{
			String seen = "";
			int place = i;
			int error = 0;
			while (place < turningPoint)
			{
				seen += right[place] ? "R" : "L";
				System.out.println(seen);
				if (cwCwStr.indexOf(seen) < 0) break;
				error += cwDist[place];
				place++;
			}
			error += opt[place] - opt[i];
			if (error > maxCWError) maxCWError = error;
		}
		
		// Strategy: walk CCW, and then turn if needed; no error starting from before turningPoint
		int maxCCWError = 0;
		for (int i = n - 1; i >= turningPoint; i--)
		{
			String seen = "";
			int place = i;
			int error = 0;
			while (place >= turningPoint)
			{
				seen += right[place] ? "R" : "L";
				System.out.println(seen);
				if (ccwCcwStr.indexOf(seen) < 0) break;
				error += ccwDist[place];
				place--;
			}
			error += opt[place] - opt[i];
			if (error > maxCCWError) maxCCWError = error;
		}
		
		out.println(Math.min(maxCWError, maxCCWError));
		out.close();
	}
}
