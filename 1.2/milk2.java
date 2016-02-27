/*
ID: yuzhou.1
LANG: JAVA
TASK: milk2
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class milk2
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "milk2.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "milk2.out" ) ) );
        
        // Read in input
        int n = Integer.parseInt( in.readLine() );
        
        int[] start = new int[ n ];
        int[] finish = new int[ n ];
        for( int i = 0; i < n; i++ )
        {
            StringTokenizer st = new StringTokenizer( in.readLine() );
            start[ i ] = Integer.parseInt( st.nextToken() );
            finish[ i ] = Integer.parseInt( st.nextToken() );
        }
        in.close();
        
        // Sort by start times
        int place;
        boolean notDone;
        
        for( int i = 0; i < start.length; i++ )
        {
            place = i;
            notDone = true;
            while( place > 0 && notDone )
            {
                if( start[ place ] < start[ place - 1 ] )
                {
                    int temp = start[ place ];
                    start[ place ] = start[ place - 1 ];
                    start[ place - 1 ] = temp;
                    
                    int temp2 = finish[ place ];
                    finish[ place ] = finish[ place - 1 ];
                    finish[ place - 1 ] = temp2;
                    
                    place--;
                }
                else
                {
                    notDone = false;
                }
            }
        }
        
        // Keep track of the times spent milking or idle, initializeing with the first
        ArrayList<Integer> timesMilking = new ArrayList<Integer>();
        ArrayList<Integer> timesIdle = new ArrayList<Integer>();
        timesMilking.add( finish[ 0 ] - start[ 0 ] );
        for( int i = 1; i < n; i++ )
        {
            if( start[ i ] <= finish[ i - 1 ] && finish[ i ] > finish[ i - 1 ] )
            {
            	// If the the current chunk of time overlaps with the previous one, expand the time milking just added
                timesMilking.set( ( timesMilking.size() - 1 ), 
                            timesMilking.get( timesMilking.size() - 1 ) + finish[ i ] - finish[ i - 1 ] );
            }
            else if( start[ i ] <= finish[ i - 1 ] && finish[ i ] <= finish[ i - 1 ] )
            {
            	// If the previous chunk of time completely contains the current one, 
            	// change the finish time of the current chunk of time to keep track of the ending of the larger continuous chunk it's part of
                finish[ i ] = finish[ i - 1 ];
            }
            else
            {
            	// If they are disjoint, add a new chunk
                timesIdle.add( start[ i ] - finish[ i - 1 ] );
                timesMilking.add( finish[ i ] - start[ i ] );
            }
        }
        
        // find the longest chunks of time
        int longestTimeMilking = 0;
        for( int i = 0; i < timesMilking.size(); i++ )
        {
            if( timesMilking.get( i ) > longestTimeMilking )
            {
                longestTimeMilking = timesMilking.get( i );
            }
        }
        
        int longestTimeIdle = 0;
        for( int i = 0; i < timesIdle.size(); i++ )
        {
            if( timesIdle.get( i ) > longestTimeIdle )
            {
                longestTimeIdle = timesIdle.get( i );
            }
        }
        
        out.println( longestTimeMilking + " " + longestTimeIdle );
        out.close();
    }
}