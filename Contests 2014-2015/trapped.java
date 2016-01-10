import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class trapped
{
    public static void main( String args[] ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "trapped.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "trapped.out" ) ) );
        
        StringTokenizer st = new StringTokenizer( in.readLine() );
        int n = Integer.parseInt( st.nextToken() );
        int b = Integer.parseInt( st.nextToken() );
        
        int[] posL = new int[ n ];
        int[] sizeL = new int[ n ];
        int[] posR = new int[ n ];
        int[] sizeR = new int[ n ];
        int l = 0;
        int r = 0;
        while( l + r < n )
        {
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            int size = Integer.parseInt( st2.nextToken() );
            int pos = Integer.parseInt( st2.nextToken() );
            if( pos < b )
            {
                posL[ l ] = pos;
                sizeL[ l ] = size;
                l++;
            }
            else
            {
                posR[ r ] = pos;
                sizeR[ r ] = size;
                r++;
            }
        }
        in.close();
        
        int[] leastLD = new int[ l ];
        int[] leastRD = new int[ r ];
        for( int i = 0; i < l; i++ )
        {
            leastLD[ i ] = 1000000001;
        }
        for( int i = 0; i < r; i++ )
        {
            leastRD[ i ] = 1000000001;
        }
        
        int least = 1000000001;
        for( int i = l - 1; i >= 0; i-- )
        {
            for( int j = 0; j < r; j++ )
            {
                int d = posR[ j ] - posL[ i ];
                if( !( d > sizeL[ i ] && d > sizeR[ j ] ) )
                {
                    if( d > sizeL[ i ] && d < leastLD[ i ] && d - sizeL[ i ] < least )
                    {
                        least = d - sizeL[ i ];
                        leastLD[ i ] = d;
                    }
                    else if( d > sizeR[ j ] && d < leastRD[ j ] && d - sizeR[ j ] < least )
                    {
                        least = d - sizeR[ j ];
                        leastRD[ j ] = d;
                    }
                    
                    if( !( d > sizeL[ i ] || d > sizeR[ j ] ) )
                    {
                        least = 0;
                        break;
                    }
                }
            }
        }
        
        if( least == 1000000001 )
        {
            least = -1;
        }
        
        out.println( least );
        out.close();
    }
}