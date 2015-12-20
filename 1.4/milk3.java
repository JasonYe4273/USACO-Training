/*
ID: yuzhou.1
LANG: JAVA
TASK: milk3
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class milk3
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "milk3.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "milk3.out" ) ) );
        StringTokenizer st = new StringTokenizer( in.readLine() );
        
        int a = Integer.parseInt( st.nextToken() );
        int b = Integer.parseInt( st.nextToken() );
        int c = Integer.parseInt( st.nextToken() );
        in.close();
        
        ArrayList<Integer> amounts = new ArrayList<Integer>();
        if( a >= c && b >= c )
        {
            amounts.add( 0 );
            amounts.add( c );
        }
        else if( a < c && b >= c )
        {
            for( int i = 0; i <= c / a; i++ )
            {
                if( !amounts.contains( i * a ) )
                {
                    amounts.add( i * a );
                }
                
                if( !amounts.contains( c - i * a ) )
                {
                    amounts.add( c - i * a );
                }
            }
        }
        else if( a >= c && b < c )
        {
            if( c - b >= b )
            {
                amounts.add( c - ( c % b ) );
                amounts.add( c - b );
                amounts.add( c );
            }
            else
            {
                amounts.add( c - b );
                amounts.add( b );
                amounts.add( c );
            }
        }
        else
        {
            for( int i = 0; i <= b; i += gcd( a, b ) )
            {
                amounts.add( c - i );
            }
        }
        
        int place, temp;
        boolean notDone;
        for( int i = 0; i < amounts.size(); i++ )
        {
            place = i;
            notDone = true;
            while( place > 0 && notDone )
            {
                if( amounts.get( place ) < amounts.get( place - 1 ) )
                {
                    temp = amounts.get( place );
                    amounts.set( place, amounts.get( place - 1 ) );
                    amounts.set( place - 1, temp );
                    
                    place--;
                }
                else
                {
                    notDone = false;
                }
            }
        }
        
        out.print( amounts.get( 0 ) );
        for( int i = 1; i < amounts.size(); i++ )
        {
            out.print( " " + amounts.get( i ) );
        }
        out.println();
        
        out.close();
        System.exit( 0 );
    }
    
    private static int gcd( int a, int b )
    {
        int num = a;
        int temp = b;
        while( num != temp && num != 1 && temp != 1 )
        {
            if( num > temp )
            {
                num -= temp;
            }
            else
            {
                temp -= num;
            }
        }
        if( temp == 1 )
        {
            return temp;
        }
        else
        {
            return num;
        }
    }
}