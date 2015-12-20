/*
ID: yuzhou.1
LANG: JAVA
TASK: gift1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class gift1
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "gift1.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "gift1.out" ) ) );
        
        int np = Integer.parseInt( in.readLine() );
        
        int[] profit = new int[ np ];
        ArrayList<String> name = new ArrayList<String>();
        for( int i = 0; i < np; i++ )
        {
            profit[ i ] = 0;
            name.add( in.readLine() );
        }
        
        int temp, temp2;
        int index;
        for( int i = 0; i < np; i++ )
        {
            index = name.indexOf( in.readLine() );
            StringTokenizer st = new StringTokenizer( in.readLine() );
            temp = Integer.parseInt( st.nextToken() );
            temp2 = Integer.parseInt( st.nextToken() );
            if( temp2 != 0 )
            {
                temp = temp / temp2;
                profit[ index ] -= ( temp * temp2 );
                for( int j = 0; j < temp2; j++ )
                {
                    profit[ name.indexOf( in.readLine() ) ] += temp;
                }
            }
        }
        in.close();
        
        for( int i = 0; i < np; i++ )
        {
            out.println( name.get( i ) + " " + profit[ i ] );
        }
        out.close();
        System.exit( 0 );
    }
}