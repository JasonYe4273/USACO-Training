import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Marathon
{
    public static void main( String[] args ) throws Exception
    {
        BufferedReader in = new BufferedReader( new FileReader( "marathon.in" ));
        BufferedWriter out = new BufferedWriter( new FileWriter( "marathon.out" ));
        
        String temp = in.readLine();
        int n = Integer.parseInt( temp );
        
        int[] x = new int[ n ];
        int[] y = new int[ n ];
        String[] split = new String[ 2 ];
        for( int i = 0; i < n; i++ )
        {
            temp = in.readLine();
            split = temp.split( " " );
            x[ i ] = Integer.parseInt( split[ 0 ] );
            y[ i ] = Integer.parseInt( split[ 1 ] );
        }
        in.close();
        
        int totalDistance = 0;
        for( int i = 1; i < n; i++ )
        {
            totalDistance += Math.abs( x[ i ] - x[ i - 1 ] );
            totalDistance += Math.abs( y[ i ] - y[ i - 1 ] );
        }
        
        int[] distance = new int[ n ];
        for( int i = 1; i < n - 1; i++ )
        {
            distance[ i ] = totalDistance;
            distance[ i ] -= Math.abs( x[ i + 1 ] - x[ i ] );
            distance[ i ] -= Math.abs( y[ i + 1 ] - y[ i ] );
            distance[ i ] -= Math.abs( x[ i ] - x[ i - 1 ] );
            distance[ i ] -= Math.abs( y[ i ] - y[ i - 1 ] );
            distance[ i ] += Math.abs( x[ i + 1 ] - x[ i - 1 ] );
            distance[ i ] += Math.abs( y[ i + 1 ] - y[ i - 1 ] );
        }
        
        int minDistance = 1000000000;
        for( int i = 1; i < n - 1; i++ )
        {
            if( distance[ i ] < minDistance )
            {
                minDistance = distance[ i ];
            }
        }
        
        out.write( String.valueOf( minDistance ) );
        out.close();
    }
}