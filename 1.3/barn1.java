/*
ID: yuzhou.1
LANG: JAVA
TASK: barn1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class barn1
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "barn1.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "barn1.out" ) ) );
        StringTokenizer st = new StringTokenizer( in.readLine() );
        
        int boards = Integer.parseInt( st.nextToken() );
        int stalls = Integer.parseInt( st.nextToken() );
        int cows = Integer.parseInt( st.nextToken() );
        
        boolean[] occupied = new boolean[ stalls ];
        for( int i = 0; i < stalls; i++ )
        {
            occupied[ i ] = false;
        }
        
        for( int i = 0; i < cows; i++ )
        {
            occupied[ Integer.parseInt( in.readLine() ) - 1 ] = true;
        }
        in.close();
        
        int firstCow = -1;
        int temp = 0;
        while( firstCow == -1 )
        {
            if( occupied[ temp ] )
            {
                firstCow = temp;
            }
            else
            {
                temp++;
            }
        }
        
        int lastCow = -1;
        temp = stalls - 1;
        while( lastCow == -1 )
        {
            if( occupied[ temp ] )
            {
                lastCow = temp;
            }
            else
            {
                temp--;
            }
        }
        
        ArrayList<Integer> unoccupiedChains = new ArrayList<Integer>();
        temp = 0;
        for( int i = firstCow; i <= lastCow; i++ )
        {
            if( !occupied[ i ] )
            {
                temp++;
            }
            else if( occupied[ i ] && temp > 0 )
            {
                unoccupiedChains.add( temp );
                temp = 0;
            }
        }
        
        int place;
        boolean notDone;
        
        for( int i = 0; i < unoccupiedChains.size(); i++ )
        {
            place = i;
            notDone = true;
            while( place > 0 && notDone )
            {
                if( unoccupiedChains.get( place ) > unoccupiedChains.get( place - 1 ) )
                {
                    temp = unoccupiedChains.get( place );
                    unoccupiedChains.set( place, unoccupiedChains.get( place - 1 ) );
                    unoccupiedChains.set( place - 1, temp );
                    
                    place--;
                }
                else
                {
                    notDone = false;
                }
            }
        }
        
        int covered = lastCow - firstCow + 1;
        for( int i = 0; i < boards - 1; i++ )
        {
            if( i < unoccupiedChains.size() )
            {
                covered -= unoccupiedChains.get( i );
            }
        }
        
        out.println( covered );
        
        out.close();
        System.exit( 0 );
    }
}