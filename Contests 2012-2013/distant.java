import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class distant
{
	public static void main( String[] args ) throws IOException
	{
		BufferedReader in = new BufferedReader( new FileReader( "distant.in" ) );
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "distant.out" ) ) );
		
		StringTokenizer st = new StringTokenizer( in.readLine() );
		int n = Integer.parseInt( st.nextToken() );
		
		char[][] grass = new char[ n ][ n ];
		for( int i = 0; i < n; i++ ) grass[ i ] = in.readLine().toCharArray();
        in.close();
		
		
		
		out.close();
	}
}