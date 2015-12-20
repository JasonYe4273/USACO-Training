/*
ID: yuzhou.1
LANG: JAVA
TASK: combo
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class combo
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "combo.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "combo.out" ) ) );
        
        int n = Integer.parseInt( in.readLine() );
        
        StringTokenizer st = new StringTokenizer( in.readLine() );
        int[] john = new int[ 3 ];
        for( int i = 0; i < 3; i++ )
        {
            john[ i ] = Integer.parseInt( st.nextToken() );
        }
        
        StringTokenizer st2 = new StringTokenizer( in.readLine() );
        int[] master = new int[ 3 ];
        for( int i = 0; i < 3; i++ )
        {
            master[ i ] = Integer.parseInt( st2.nextToken() );
        }
        in.close();
        
        int numCombos = 0;
        for( int i = 1; i <= n; i++ )
        {
            for( int j = 1; j <= n; j++ )
            {
                for( int k = 1; k <= n; k++ )
                {
                    if( check( i, j, k, john, n ) || check( i, j, k, master, n ) )
                    {
                        numCombos++;
                    }
                }
            }
        }
        
        out.println( numCombos );
        
        out.close();
        System.exit( 0 );
    }
    
    public static boolean singleCheck( int num, int ref, int n )
    {
        return ( Math.abs( num - ref ) <= 2 || Math.abs( num - ref ) >= n - 2 );
    }
    
    public static boolean check( int a, int b, int c, int[] combo, int n )
    {
        return ( singleCheck( a, combo[ 0 ], n ) && singleCheck( b, combo[ 1 ], n ) 
                && singleCheck( c, combo[ 2 ], n ) );
    }
}