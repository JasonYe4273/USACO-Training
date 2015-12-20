/*
ID: yuzhou.1
LANG: JAVA
TASK: dualpal
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class dualpal
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "dualpal.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "dualpal.out" ) ) );
        StringTokenizer st = new StringTokenizer( in.readLine() );
        
        int n = Integer.parseInt( st.nextToken() );
        int s = Integer.parseInt( st.nextToken() );
        in.close();
        
        int numDualPals = 0;
        int num = s + 1;
        int numPals = 0;
        int temp;
        while( numDualPals < n )
        {
            numPals = 0;
            
            for( int b = 2; b <= 10; b++ )
            {
                ArrayList<Integer> digits = new ArrayList<Integer>();
                temp = num;
                while( temp > 0 )
                {
                    digits.add( temp % b );
                    temp = temp / b;
                }
                
                boolean isPal = true;
                for( int i = 0; i < digits.size(); i++ )
                {
                    if( digits.get( i ) != digits.get( digits.size() - i - 1 ) )
                    {
                        isPal = false;
                    }
                }
                
                if( isPal )
                {
                    numPals++;
                }
            }
            
            if( numPals > 1 )
            {
                numDualPals++;
                out.println( num );
            }
            
            num++;
        }
        
        out.close();
        System.exit( 0 );
    }
}