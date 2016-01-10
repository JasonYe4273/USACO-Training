import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowroute
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
        boolean[] works = new boolean[ n ];
        for( int i = 0; i < n; i++ )
        {
            works[ i ] = false;
            
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            cost[ i ] = Integer.parseInt( st2.nextToken() );
            numCities[ i ] = Integer.parseInt( st2.nextToken() );
            
            StringTokenizer st3 = new StringTokenizer( in.readLine() );
            boolean hasA = false;
            for( int j = 0; j < numCities[ i ]; j++ )
            {
                int temp = Integer.parseInt( st3.nextToken() );
                
                if( temp == a )
                {
                    hasA = true;
                }
                
                if( temp == b && hasA )
                {
                    works[ i ] = true;
                }
            }
        }
        in.close();
        
        int leastCost = 1001;
        for( int i = 0; i < n; i++ )
        {
            if( works[ i ] && cost[ i ] < leastCost )
            {
                leastCost = cost[ i ];
            }
        }
        
        if( leastCost == 1001 )
        {
            leastCost = -1;
        }
        
        out.println( leastCost );
        out.close();
    }
}