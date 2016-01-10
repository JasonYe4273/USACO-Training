import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowroute2
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "cowroute.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "cowroute.out" ) ) );
        
        StringTokenizer st = new StringTokenizer( in.readLine() );
        int a = Integer.parseInt( st.nextToken() );
        int b = Integer.parseInt( st.nextToken() );
        int n = Integer.parseInt( st.nextToken() );
        
        int[] cost = new int[ n ];
        int[] numCities = new int[ n ];
        int[][] cities = new int[ n ][];
        for( int i = 0; i < n; i++ )
        {
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            cost[ i ] = Integer.parseInt( st2.nextToken() );
            numCities[ i ] = Integer.parseInt( st2.nextToken() );
            
            StringTokenizer st3 = new StringTokenizer( in.readLine() );
            cities[ i ] = new int[ numCities[ i ] ];
            for( int j = 0; j < numCities[ i ]; j++ )
            {
                cities[ i ][ j ] = Integer.parseInt( st3.nextToken() );
            }
        }
        in.close();
        
        int leastCost = 2001;
        
        int[] placeA = new int[ n ];
        int[] placeB = new int[ n ];
        for( int i = 0; i < n; i++ )
        {
            placeA[ i ] = -1;
            placeB[ i ] = -1;
            
            for( int j = 0; j < numCities[ i ]; j++ )
            {
                if( cities[ i ][ j ] == a )
                {
                    placeA[ i ] = j;
                }
                
                if( cities[ i ][ j ] == b )
                {
                    placeB[ i ] = j;
                }
            }
            
            if( placeA[ i ] != -1 && placeB[ i ] != -1 && placeA[ i ] < placeB[ i ] 
                && cost[ i ] < leastCost )
            {
                leastCost = cost[ i ];
            }
        }
        
        for( int i = 0; i < n; i++ )
        {
            if( placeA[ i ] != -1 && placeA[ i ] > placeB[ i ] )
            {
                for( int j = 0; j < n; j++ )
                {
                    if( placeB[ j ] != -1 && ( placeA[ j ] == -1 || placeA[ j ] > placeB[ j ] )
                        &&i != j && cost[ i ] + cost[ j ] < leastCost )
                    {
                        int place = placeA[ i ] + 1;
                        boolean done = false;
                        
                        while( !done && place < numCities[ i ] )
                        {
                            int place2 = 0;
                            
                            while( !done && place2 < placeB[ j ] )
                            {
                                if( cities[ i ][ place ] == cities[ j ][ place2 ] )
                                {
                                    leastCost = cost[ i ] + cost[ j ];
                                    done = true;
                                }
                                
                                place2++;
                            }
                            
                            place++;
                        }
                    }
                }
            }
        }
        
        if( leastCost == 2001 )
        {
            leastCost = -1;
        }
        
        out.println( leastCost );
        out.close();
    }
}