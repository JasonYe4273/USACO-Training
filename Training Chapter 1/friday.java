/*
ID: yuzhou.1
LANG: JAVA
TASK: friday
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class friday
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "friday.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "friday.out" ) ) );
        StringTokenizer st = new StringTokenizer( in.readLine() );
        
        int n = Integer.parseInt( st.nextToken() );
        in.close();
        
        int year = 1900;
        int day = 6;
        int num13s[] = { 0, 0, 0, 0, 0, 0, 0 };
        for( int i = 0; i < n; i++ )
        {
            num13s[ day ]++;
            day = ( day + 31 ) % 7;
            
            num13s[ day ]++;
            if( ( year % 400 == 0 ) || ( year % 100 != 0 && year % 4 == 0 ) )
            {
                day = ( day + 29 ) % 7;
            }
            
            num13s[ day ]++;
            day = ( day + 31 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 30 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 31 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 30 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 31 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 31 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 30 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 31 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 30 ) % 7;
            
            num13s[ day ]++;
            day = ( day + 31 ) % 7;
            
            year++;
        }
        
        out.println( num13s[ 6 ] + " " + num13s[ 0 ] + " " + num13s[ 1 ] + " " + num13s[ 2 ] + 
                    " " + num13s[ 3 ] + " " + num13s[ 4 ] + " " + num13s[ 5 ] );
        out.close();
        System.exit( 0 );
    }
}