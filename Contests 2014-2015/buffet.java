import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class buffet
{
    public static void main( String args[] ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "buffet.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "buffet.out" ) ) );
        
        StringTokenizer st = new StringTokenizer( in.readLine() );
        int n = Integer.parseInt( st.nextToken() );
        
        int[] q = new int[ n ];
        int[] d = new int[ n ];
        for( int i = 0; i < n; i++ )
        {
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            q[ i ] = Integer.parseInt( st2.nextToken() );
            d[ i ] = Integer.parseInt( st2.nextToken() );
            
            for( int j = 0; j < d[ i ]; j++ )
            {
                
            }
        }
        in.close();
        out.close();
    }
}