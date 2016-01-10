import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class wifi
{
	public static void main( String[] args ) throws IOException
	{
		BufferedReader in = new BufferedReader( new FileReader( "wifi.in" ) );
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "wifi.out" ) ) );
		
		StringTokenizer st = new StringTokenizer( in.readLine() );
		int n = Integer.parseInt( st.nextToken() );
		int a = Integer.parseInt( st.nextToken() );
		int b = Integer.parseInt( st.nextToken() );
		
		int[] cows = new int[ n ];
		
		for( int i = 0; i < n; i++ ) cows[ i ] = Integer.parseInt( in.readLine() );
        in.close();
		
		for( int i = 1; i < n; i++ )
		{
			int current = cows[ i ];
			int place = i - 1;
			
			//Shift everything greater one up
			while( place >= 0 && current < cows[ place ] ) cows[ place + 1 ] = cows[ place-- ];
			
			//Place in correct position
			cows[ place + 1 ] = current;
		}
		
		double cost = a + ( ( cows[ n - 1 ] - cows[ 0 ] ) * b * 0.5 );
		
		int[] distance = new int[ n - 1 ];
		
		for( int i = 0; i < n - 1; i++ )
		{
			distance[ i ] = cows[ i + 1 ] - cows[ i ];
			if( distance[ i ] * b * 0.5 > a ) cost -= ( ( distance[ i ] * b * 0.5 ) - a );
		}
		
		if( cost - (int) cost == 0 ) out.println( (int) cost ); 
		else out.println( cost );
		out.close();
	}
}