import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class bbreeds
{
	public static void main( String args[] ) throws IOException
	{
		BufferedReader in = new BufferedReader( new FileReader( "bbreeds.in" ) );
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "bbreeds.out" ) ) );
		
		char[] parens = in.readLine().toCharArray();
		int l = parens.length;
		ArrayList<Integer> lIdx = new ArrayList<Integer>();
		ArrayList<Integer> rIdx = new ArrayList<Integer>();
        in.close();
		
		for( int i = 0; i < l; i++ )
		{
			if( parens[ i ] == '(' ) lIdx.add( i );
			else rIdx.add( i );
		}
		
		if( lIdx.size() != rIdx.size() ) out.println( 0 );
		else
		{
			
		}
		
		out.close();
	}
}
