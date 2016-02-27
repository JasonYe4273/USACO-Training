/*
ID: yuzhou.1
LANG: JAVA
TASK: milk
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class milk
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "milk.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "milk.out" ) ) );
        StringTokenizer st = new StringTokenizer( in.readLine() );
        
        // Read in input
        int milkNeeded = Integer.parseInt( st.nextToken() );
        int numFarmers = Integer.parseInt( st.nextToken() );
        
        int[] price = new int[ numFarmers ];
        int[] available = new int[ numFarmers ];
        for( int i = 0; i < numFarmers; i++ )
        {
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            price[ i ] = Integer.parseInt( st2.nextToken() );
            available[ i ] = Integer.parseInt( st2.nextToken() );
        }
        in.close();
        
        // Sort the prices and amounts based on price
        int place;
        boolean notDone;
        
        for( int i = 0; i < price.length; i++ )
        {
            place = i;
            notDone = true;
            while( place > 0 && notDone )
            {
                if( price[ place ] < price[ place - 1 ] )
                {
                    int temp = price[ place ];
                    price[ place ] = price[ place - 1 ];
                    price[ place - 1 ] = temp;
                    
                    int temp2 = available[ place ];
                    available[ place ] = available[ place - 1 ];
                    available[ place - 1 ] = temp2;
                    
                    place--;
                }
                else
                {
                    notDone = false;
                }
            }
        }
        
        // Greedily take buy all milk available from the lowest prices until enough is bought
        int milk = 0;
        int cost = 0;
        place = 0;
        
        while( milk < milkNeeded )
        {
            if( available[ place ] <= milkNeeded - milk )
            {
                cost += price[ place ] * available[ place ];
                milk += available[ place ];
                available[ place ] = 0;
            }
            else
            {
                cost += price[ place ];
                milk++;
                available[ place ]--;
            }
            
            if( available[ place ] == 0 )
            {
                place++;
            }
        }
        
        out.println( cost );
        
        out.close();
        System.exit( 0 );
    }
}