import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hopscotch
{
    private static long ways;
    
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "hopscotch.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "hopscotch.out" ) ) );
        StringTokenizer st = new StringTokenizer( in.readLine() );
        
        int r = Integer.parseInt( st.nextToken() );
        int c = Integer.parseInt( st.nextToken() );
        
        int[][] board = new int[ r ][ c ];
        for( int i = 0; i < r; i++ )
        {
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            for( int j = 0; j < c; j++ )
            {
                 board[ i ][ j ] = Integer.parseInt( st2.nextToken() );
            }
        }
        in.close();
        
        ways = 0;
        hopper( board, r, c, 0, 0 );
        
        out.println( ways % 1000000007 );
        out.close();
    }
    
    public static void hopper( int[][] board, int r, int c, int rStart, int cStart )
    {
        if( board[ rStart ][ cStart ] != board[ rStart + r - 1 ][ cStart + c - 1 ] )
        {
            ways++;
        }
        
        
        
        if( r == 2 || c == 2 )
        {
            return;
        }
        else
        {
            for( int i = 1; i < r - 1; i++ )
            {
                for( int j = 1; j < c - 1; j++ )
                {
                    if( board[ rStart ][ cStart ] != board[ rStart + i ][ cStart + j ] )
                    {
                        hopper( board, r - i, c - j, rStart + i, cStart + j );
                    }
                }
            }
        }
    }
}