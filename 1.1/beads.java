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
        
        // Read in input
        int n = Integer.parseInt( in.readLine() );
        char[] necklace = new char[ n ];
        necklace = in.readLine().toCharArray();
        in.close();
        
        // Note: all ( ( ( --- ) % n ) + n ) % n can be replaced with ((---) + n) % n
        
        // Call a string of consecutive beads that are the same color (or white) a "chunk"
        // You should never split at r|r or b|b; instead, you could split at the one end of the chunk
        // to get both a red and a blue chunk instead of just one of them
        // Additionally, w|w is the same as splitting on one side of the string of whites
        int longest = 0;
        int place, tempLongest;
        for( int i = 0; i < n; i++ )
        {
        	// Therefore, look for where the color changes
            if( necklace[ ( ( ( i + 1 ) % n ) + n ) % n ] != necklace[ i ] )
            {
            	// If neither are white, then just expand both sides until you hit the opposite color
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
                // If one is white, then treat the string of whites as nonexistent (they can always be counted)
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
                // Same as above
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
    }
}