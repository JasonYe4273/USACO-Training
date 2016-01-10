import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class meeting
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "meeting.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "meeting.out" ) ) );
        
        StringTokenizer st = new StringTokenizer( in.readLine() );
        int n = Integer.parseInt( st.nextToken() );
        int m = Integer.parseInt( st.nextToken() );
        
        int[] start = new int[ m ];
        int[] end = new int[ m ];
        int[] bTime = new int[ m ];
        int[] eTime = new int[ m ];
        ArrayList<Integer> bTotal = new ArrayList<Integer>();
        ArrayList<Integer> eTotal = new ArrayList<Integer>();
        for( int i = 0; i < m; i++ )
        {
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            start[ i ] = Integer.parseInt( st2.nextToken() );
            end[ i ] = Integer.parseInt( st2.nextToken() );
            bTime[ i ] = Integer.parseInt( st2.nextToken() );
            eTime[ i ] = Integer.parseInt( st2.nextToken() );
        }
        in.close();
        
        for( int i = 0; i < Math.pow( 2, n - 2 ); i++ )
        {
            int temp = i;
            
            ArrayList<Integer> path = new ArrayList<Integer>();
            path.add( 1 );
            for( int j = 2; j < n; j++ )
            {
                if( temp % 2 == 1 )
                {
                    path.add( j );
                }
                temp = temp / 2;
            }
            path.add( n );
            
            boolean valid = true;
            
            int bTemp = 0;
            int eTemp = 0;
            for( int j = 0; j < path.size() - 1; j++ )
            {
                if( valid )
                {
                    boolean bool = false;
                    
                    for( int k = 0; k < m; k++ )
                    {
                        if( start[ k ] == path.get( j ) && end[ k ] == path.get( j + 1 ) )
                        {
                            bool = true;
                            bTemp += bTime[ k ];
                            eTemp += eTime[ k ];
                        }
                    }
                    
                    if( !bool )
                    {
                        valid = false;
                    }
                }
            }
            
            bTotal.add( bTemp );
            eTotal.add( eTemp );
        }
        
        int leastTime = 1000000;
        for( int i = 0; i < bTotal.size(); i++ )
        {
            if( bTotal.get( i ) < leastTime )
            {
                if( eTotal.contains( bTotal.get( i ) ) )
                {
                    leastTime = bTotal.get( i );
                }
            }
        }
        
        if( leastTime == 1000000 )
        {
            out.println( "IMPOSSIBLE" );
        }
        else
        {
            out.println( leastTime );
        }
        
        out.close();
    }
}