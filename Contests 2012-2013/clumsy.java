import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class clumsy
{
	public static void main( String[] args ) throws IOException
	{
		BufferedReader in = new BufferedReader( new FileReader( "clumsy.in" ) );
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "clumsy.out" ) ) );
		
		char[] parens = in.readLine().trim().toCharArray();
		int switches = 0;
		int l = parens.length;
        in.close();
		
		int LRDiff = 0;
		for( int i = 0; i < l; i++ )
		{
			char p = parens[ i ];
			if( p == '(' ) LRDiff++;
			else LRDiff--;
			
			if( LRDiff < 0 )
			{
				parens[ i ] = '(';
				switches++;
				LRDiff += 2;
			}
		}

		LRDiff = l;
		for( char p : parens ) if( p == ')' ) LRDiff -= 2;
		switches += LRDiff / 2;
		
		out.println( switches );
		out.close();
	}
}
