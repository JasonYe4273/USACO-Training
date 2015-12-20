/*
ID: yuzhou.1
LANG: JAVA
TASK: beads
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class beads
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "beads.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "beads.out" ) ) );
        
        int n = Integer.parseInt( in.readLine() );
        char[] necklace = new char[ n ];
        necklace = in.readLine().toCharArray();
        in.close();
        
        int longest = 0;
        int place, tempLongest;
        for( int i = 0; i < n; i++ )
        {
            if( necklace[ ( ( ( i + 1 ) % n ) + n ) % n ] != necklace[ i ] )
            {
                if( necklace[ i ] != 'w' && necklace[ ( ( ( i + 1 ) % n ) + n ) % n ] != 'w' )
                {
                    tempLongest = 2;
                    
                    place = ( ( ( i - 1 ) % n ) + n ) % n;
                    while( necklace[ place ] != necklace[ ( ( ( i + 1 ) % n ) + n ) % n ] )
                    {
                        tempLongest++;
                        place = ( ( ( place - 1 ) % n ) + n ) % n;
                    }
                    
                    place = ( ( ( i + 2 ) % n ) + n ) % n;
                    while( necklace[ place ] != necklace[ i ] )
                    {
                        tempLongest++;
                        place = ( ( ( place + 1 ) % n ) + n ) % n;
                    }
                    
                    if( tempLongest > longest )
                    {
                        longest = tempLongest;
                    }
                }
                else if( necklace[ i ] == 'w' )
                {
                    char firstNonW;
                    tempLongest = 2;
                    
                    place = ( ( ( i - 1 ) % n ) + n ) % n;
                    while( necklace[ place ] == 'w' )
                    {
                        tempLongest++;
                        place = ( ( ( place - 1 ) % n ) + n ) % n;
                    }
                    firstNonW = necklace[ place ];
                    
                    if( firstNonW != necklace[ ( ( ( i + 1 ) % n ) + n ) % n ] )
                    {
                        while( necklace[ place ] != necklace[ ( ( ( i + 1 ) % n ) + n ) % n ] )
                        {
                            tempLongest++;
                            place = ( ( ( place - 1 ) % n ) + n ) % n;
                        }
                        
                        place = ( ( ( i + 2 ) % n ) + n ) % n;
                        while( necklace[ place ] != firstNonW )
                        {
                            tempLongest++;
                            place = ( ( ( place + 1 ) % n ) + n ) % n;
                        }
                        
                        if( tempLongest > longest )
                        {
                            longest = tempLongest;
                        }
                    }
                }
                else if( necklace[ ( ( ( i + 1 ) % n ) + n ) % n ] == 'w' )
                {
                    char firstNonW;
                    tempLongest = 2;
                    
                    place = ( ( ( i + 2 ) % n ) + n ) % n;
                    while( necklace[ place ] == 'w' )
                    {
                        tempLongest++;
                        place = ( ( ( place + 1 ) % n ) + n ) % n;
                    }
                    firstNonW = necklace[ place ];
                    
                    if( firstNonW != necklace[ i ] )
                    {
                        while( necklace[ place ] != necklace[ i ] )
                        {
                            tempLongest++;
                            place = ( ( ( place + 1 ) % n ) + n ) % n;
                        }
                        
                        place = ( ( ( i - 1 ) % n ) + n ) % n;
                        while( necklace[ place ] != firstNonW )
                        {
                            tempLongest++;
                            place = ( ( ( place - 1 ) % n ) + n ) % n;
                        }
                        
                        if( tempLongest > longest )
                        {
                            longest = tempLongest;
                        }
                    }
                }
            }
        }
        
        if( longest == 0 || longest > n )
        {
            longest = n;
        }
        
        out.println( longest );
        out.close();
        System.exit( 0 );
    }
}