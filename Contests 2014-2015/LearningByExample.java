import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class LearningByExample
{
    public static void main( String[] args ) throws Exception
    {
        BufferedReader in = new BufferedReader( new FileReader( "learning.in" ));
        BufferedWriter out = new BufferedWriter( new FileWriter( "learning.out" ));
        
        String line = in.readLine();
        String[] split = new String[ 3 ];
        split = line.split( " " );
        int n = Integer.parseInt( split[ 0 ] );
        int a = Integer.parseInt( split[ 1 ] );
        int b = Integer.parseInt( split[ 2 ] );
        in.close();
        
        int weight[] = new int[ n ];
        boolean hasSpots[] = new boolean[ n ];
        for( int i = 0; i < n; i++ )
        {
            line = in.readLine();
            split = line.split( " " );
            weight[ i ] = Integer.parseInt( split[ 0 ] );
            if( split[ 1 ].equals( "S" ) )
            {
                hasSpots[ i ] = true;
            }
            else
            {
                hasSpots[ i ] = false;
            }
        }
        
        //Insert sorter by weight here.
        
        int index = 0;
        boolean notDone = true;
        while( notDone )
        {
            if( a < weight[ index ] )
            {
                index--;
                notDone = false;
            }
            else
            {
                index++;
            }
        }
        
        boolean[] predictSpots = new boolean[ b - a + 1 ];
        for( int i = a; i < b; i++ )
        {
            if( i == weight[ index + 1 ] )
            {
                predictSpots[ i - a ] = hasSpots[ index + 1 ];
                index++;
            }
            else if( i - weight[ index ] < weight[ index + 1 ] - 1 )
            {
                predictSpots[ i - a ] = hasSpots[ index ];
            }
            else if( i - weight[ index ] > weight[ index + 1 ] - 1 )
            {
                predictSpots[ i - a ] = hasSpots[ index + 1 ];
            }
            else if( i - weight[ index ] == weight[ index + 1 ] - 1 )
            {
                predictSpots[ i - a ] = ( hasSpots[ index ] || hasSpots[ index + 1 ] );
            }
        }
        
        int spotsPredicted = 0;
        for( int i = 0; i < b - a + 1; i++ )
        {
            if( predictSpots[ i ] )
            {
                spotsPredicted++;
            }
        }
        
        out.write( String.valueOf( spotsPredicted ) );
        out.close();
    }
}