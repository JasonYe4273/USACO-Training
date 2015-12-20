/*
ID: yuzhou.1
LANG: JAVA
TASK: frac1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class frac1
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		
		int n = Integer.parseInt(in.readLine());
		in.close();
		
		ArrayList<Integer> denom = new ArrayList<Integer>();
		ArrayList<Integer> numer = new ArrayList<Integer>();
		ArrayList<Double> val = new ArrayList<Double>();
		for( int q = 2; q <= n; q++ )
		{
			for( int p = 1; p <= q / 2; p++ )
			{
				if( gcd( p, q ) == 1 )
				{
					denom.add( q );
					numer.add( p );
					val.add( (double) p / (double) q );
				}
			}
		}
		
		int size = val.size();
		int[] denomArray = new int[size];
		int[] numerArray = new int[size];
		double[] valArray = new double[size];
		for (int i = 0; i < size; i++)
		{
			denomArray[i] = denom.get(i);
			numerArray[i] = numer.get(i);
			valArray[i] = val.get(i);
		}
		
		mergeSort(valArray, denomArray, numerArray);
		
		out.println("0/1");
		for (int i = 0; i < size; i++)
		{
			out.println(String.format("%s/%s", numerArray[i], denomArray[i]));
		}
		for (int i = size - 2; i >= 0; i--)
		{
			out.println(String.format("%s/%s", denomArray[i] - numerArray[i], denomArray[i]));
		}
		out.println("1/1");
		
		out.close();
	}
	
	public static int gcd( int a, int b )
	{
		if( a == 1 || b == 1 )
		{
			return 1;
		}
		
		if( a == b || a == 0 || b == 0 )
		{
			return a;
		}
		
		if( a > b )
		{
			return gcd( a % b, b );
		}
		return gcd( a, b % a );
	}
	

	public static void mergeSort( double[] unsorted, int[] other1, int[] other2 )
	{
		//Create auxiliary array to avoid needless array creation 
		double[] aux = new double[ unsorted.length ];
		int[] aux1 = new int[ other1.length ];
		int[] aux2 = new int[ other2.length ];
		
		//Sort
		mergeSort( unsorted, aux, 0, unsorted.length - 1, other1, other2, aux1, aux2 );
	}
	
	private static void mergeSort( double[] unsorted, double[] aux, int lo, int hi, int[] other1, int[] other2, int[] aux1, int[] aux2 )
	{
		//Base case
		if( hi <= lo ) return;
		
		//Calculate mid
		int mid = lo + ( hi - lo ) / 2;
		
		//Sort both halves
		mergeSort( unsorted, aux, lo, mid, other1, other2, aux1, aux2 );
		mergeSort( unsorted, aux, mid + 1, hi, other1, other2, aux1, aux2 );
		
		//Check to see if merge is necessary
		if( unsorted[ mid ] <= unsorted[ mid + 1 ] ) return;
		
		//Merge
		merge( unsorted, aux, lo, mid, hi, other1, other2, aux1, aux2 );
	}
	
	private static void merge( double[] unsorted, double[] aux, int lo, int mid, int hi, int[] other1, int[] other2, int[] aux1, int[] aux2 )
	{
		//Copy into aux
		for( int i = lo; i <= hi; i++ ) 
		{
			aux[ i ] = unsorted[ i ];
			aux1[ i ] = other1[ i ];
			aux2[ i ] = other2[ i ];
		}
		
		//Merge into unsorted
		int leftCount = lo, rightCount = mid + 1;
		for( int i = lo; i <= hi; i++ )
		{
			if( leftCount > mid ) 
			{
				other1[ i ] = aux1[ rightCount ];
				other2[ i ] = aux2[ rightCount ];
				unsorted[ i ] = aux[ rightCount++ ];
			}
			else if( rightCount > hi ) 
			{
				other1[ i ] = aux1[ leftCount ];
				other2[ i ] = aux2[ leftCount ];
				unsorted[ i ] = aux[ leftCount++ ];
			}
			else if( aux[ rightCount ] < aux[ leftCount ] )
			{
				other1[ i ] = aux1[ rightCount ];
				other2[ i ] = aux2[ rightCount ];
				unsorted[ i ] = aux[ rightCount++ ];
			}
			else 
			{
				other1[ i ] = aux1[ leftCount ];
				other2[ i ] = aux2[ leftCount ];
				unsorted[ i ] = aux[ leftCount++ ];
			}
		}
	}
}
