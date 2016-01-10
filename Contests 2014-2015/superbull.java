import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class superbull
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "superbull.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "superbull.out" ) ) );
        
        int n = Integer.parseInt( in.readLine() );
        
        int[] id = new int[ n ];
        int largest2Pow = 0;
        int numL2P = 0;
        ArrayList<Integer> L2PIndex = new ArrayList<Integer>();
        ArrayList<Integer> nL2PIndex = new ArrayList<Integer>();
        for( int i = 0; i < n; i++ )
        {
            id[ i ] = Integer.parseInt( in.readLine() );
            int temp = (int) ( Math.log( (double) id[ i ] ) / Math.log( 2.0 ) );
            if( temp > largest2Pow )
            {
                largest2Pow = temp;
                numL2P = 0;
                L2PIndex.clear();
            }
            
            if( temp == largest2Pow )
            {
                numL2P++;
                L2PIndex.add( i );
            }
            nL2PIndex.add( i );
        }
        in.close();
        
        for( int i = 0; i < numL2P; i++ )
        {
            nL2PIndex.remove( L2PIndex.get( i ) );
        }
        
        int[][] pts = new int[ numL2P ][ n - numL2P ];
        for( int i = 0; i < numL2P; i++ )
        {
            for( int j = 0; j < n - numL2P; j++ )
            {
                pts[ i ][ j ] = id[ L2PIndex.get( i ) ] ^ id[ nL2PIndex.get( j ) ];
            }
        }
        
        int ans = 0;
        for( int i = 0; i < numL2P; i++ )
        {
            for( int j = 0; j < n - numL2P; j++ )
            {
                int tempAns = 0;
                
                for( int k = 0; k < numL2P; k++ )
                {
                    tempAns += pts[ k ][ j ];
                }
                for( int k = 0; k < n - numL2P; k++ )
                {
                    tempAns += pts[ i ][ k ];
                }
                tempAns -= pts[ i ][ j ];
                
                if( tempAns > ans )
                {
                    ans = tempAns;
                }
            }
        }
        
        
        out.println( ans );
        out.close();
    }
}