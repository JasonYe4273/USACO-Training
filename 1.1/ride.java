/*
ID: yuzhou.1
LANG: JAVA
TASK: ride
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ride
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "ride.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "ride.out" ) ) );
        
        String comet = in.readLine();
        String group = in.readLine();
        comet = ( comet + "AAAAAA" );
        group = ( group + "AAAAAA" );
        in.close();
        
        int[] cometInt = new int[ 6 ];
        int[] groupInt = new int[ 6 ];
        for( int i = 0; i < 6; i++ )
        {
            if( comet.charAt( i ) == 'A' )
            {
                cometInt[ i ] = 1;
            }
            else if( comet.charAt( i ) == 'B' )
            {
                cometInt[ i ] = 2;
            }
            else if( comet.charAt( i ) == 'C' )
            {
                cometInt[ i ] = 3;
            }
            else if( comet.charAt( i ) == 'D' )
            {
                cometInt[ i ] = 4;
            }
            else if( comet.charAt( i ) == 'E' )
            {
                cometInt[ i ] = 5;
            }
            else if( comet.charAt( i ) == 'F' )
            {
                cometInt[ i ] = 6;
            }
            else if( comet.charAt( i ) == 'G' )
            {
                cometInt[ i ] = 7;
            }
            else if( comet.charAt( i ) == 'H' )
            {
                cometInt[ i ] = 8;
            }
            else if( comet.charAt( i ) == 'I' )
            {
                cometInt[ i ] = 9;
            }
            else if( comet.charAt( i ) == 'J' )
            {
                cometInt[ i ] = 10;
            }
            else if( comet.charAt( i ) == 'K' )
            {
                cometInt[ i ] = 11;
            }
            else if( comet.charAt( i ) == 'L' )
            {
                cometInt[ i ] = 12;
            }
            else if( comet.charAt( i ) == 'M' )
            {
                cometInt[ i ] = 13;
            }
            else if( comet.charAt( i ) == 'N' )
            {
                cometInt[ i ] = 14;
            }
            else if( comet.charAt( i ) == 'O' )
            {
                cometInt[ i ] = 15;
            }
            else if( comet.charAt( i ) == 'P' )
            {
                cometInt[ i ] = 16;
            }
            else if( comet.charAt( i ) == 'Q' )
            {
                cometInt[ i ] = 17;
            }
            else if( comet.charAt( i ) == 'R' )
            {
                cometInt[ i ] = 18;
            }
            else if( comet.charAt( i ) == 'S' )
            {
                cometInt[ i ] = 19;
            }
            else if( comet.charAt( i ) == 'T' )
            {
                cometInt[ i ] = 20;
            }
            else if( comet.charAt( i ) == 'U' )
            {
                cometInt[ i ] = 21;
            }
            else if( comet.charAt( i ) == 'V' )
            {
                cometInt[ i ] = 22;
            }
            else if( comet.charAt( i ) == 'W' )
            {
                cometInt[ i ] = 23;
            }
            else if( comet.charAt( i ) == 'X' )
            {
                cometInt[ i ] = 24;
            }
            else if( comet.charAt( i ) == 'Y' )
            {
                cometInt[ i ] = 25;
            }
            else if( comet.charAt( i ) == 'Z' )
            {
                cometInt[ i ] = 26;
            }
            else
            {
                cometInt[ i ] = 1;
            }
            
            if( group.charAt( i ) == 'A' )
            {
                groupInt[ i ] = 1;
            }
            else if( group.charAt( i ) == 'B' )
            {
                groupInt[ i ] = 2;
            }
            else if( group.charAt( i ) == 'C' )
            {
                groupInt[ i ] = 3;
            }
            else if( group.charAt( i ) == 'D' )
            {
                groupInt[ i ] = 4;
            }
            else if( group.charAt( i ) == 'E' )
            {
                groupInt[ i ] = 5;
            }
            else if( group.charAt( i ) == 'F' )
            {
                groupInt[ i ] = 6;
            }
            else if( group.charAt( i ) == 'G' )
            {
                groupInt[ i ] = 7;
            }
            else if( group.charAt( i ) == 'H' )
            {
                groupInt[ i ] = 8;
            }
            else if( group.charAt( i ) == 'I' )
            {
                groupInt[ i ] = 9;
            }
            else if( group.charAt( i ) == 'J' )
            {
                groupInt[ i ] = 10;
            }
            else if( group.charAt( i ) == 'K' )
            {
                groupInt[ i ] = 11;
            }
            else if( group.charAt( i ) == 'L' )
            {
                groupInt[ i ] = 12;
            }
            else if( group.charAt( i ) == 'M' )
            {
                groupInt[ i ] = 13;
            }
            else if( group.charAt( i ) == 'N' )
            {
                groupInt[ i ] = 14;
            }
            else if( group.charAt( i ) == 'O' )
            {
                groupInt[ i ] = 15;
            }
            else if( group.charAt( i ) == 'P' )
            {
                groupInt[ i ] = 16;
            }
            else if( group.charAt( i ) == 'Q' )
            {
                groupInt[ i ] = 17;
            }
            else if( group.charAt( i ) == 'R' )
            {
                groupInt[ i ] = 18;
            }
            else if( group.charAt( i ) == 'S' )
            {
                groupInt[ i ] = 19;
            }
            else if( group.charAt( i ) == 'T' )
            {
                groupInt[ i ] = 20;
            }
            else if( group.charAt( i ) == 'U' )
            {
                groupInt[ i ] = 21;
            }
            else if( group.charAt( i ) == 'V' )
            {
                groupInt[ i ] = 22;
            }
            else if( group.charAt( i ) == 'W' )
            {
                groupInt[ i ] = 23;
            }
            else if( group.charAt( i ) == 'X' )
            {
                groupInt[ i ] = 24;
            }
            else if( group.charAt( i ) == 'Y' )
            {
                groupInt[ i ] = 25;
            }
            else if( group.charAt( i ) == 'Z' )
            {
                groupInt[ i ] = 26;
            }
            else
            {
                groupInt[ i ] = 1;
            }
        }
        
        int cometValue = 1;
        int groupValue = 1;
        for( int i = 0; i < 6; i++ )
        {
            cometValue = cometValue * cometInt[ i ];
            groupValue = groupValue * groupInt[ i ];
        }
        
        if( ( cometValue % 47 ) == ( groupValue % 47 ) )
        {
            out.println( "GO" );
        }
        else
        {
            out.println( "STAY" );
        }
        out.close();
        System.exit( 0 );
    }
}