import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CowJog
{
    public static void main( String[] args ) throws Exception
    {
        BufferedReader in = new BufferedReader( new FileReader( "cowjog.in" ));
        BufferedWriter out = new BufferedWriter( new FileWriter( "cowjog.out" ));
        
        String temp = in.readLine();
        int n = Integer.parseInt( temp );
        int groups = n;
        
        int[] v = new int[ n ];
        String[] split = new String[ 2 ];
        for( int i = 0; i < n; i++ )
        {
            temp = in.readLine();
            split = temp.split( " " );
            v[ i ] = Integer.parseInt( split[ 1 ] );
        }
        in.close();
        
        int min = 1000000001;
        for( int i = n - 1; i >= 0; i-- )
        {
            if( v[ i ] <= min )
            {
                min = v[ i ];
            }
            else
            {
                groups--;
            }
        }
        
        out.write( String.valueOf( groups ) );
        out.close();
    }
}