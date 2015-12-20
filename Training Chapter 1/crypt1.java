/*
ID: yuzhou.1
LANG: JAVA
TASK: crypt1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class crypt1
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "crypt1.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "crypt1.out" ) ) );
        
        int n = Integer.parseInt( in.readLine() );
        
        StringTokenizer st = new StringTokenizer( in.readLine() );
        int[] digits = new int[ n ];
        for( int i = 0; i < n; i++ )
        {
            digits[ i ] = Integer.parseInt( st.nextToken() );
        }
        in.close();
        
        int num1, num2, p1, p2, sum;
        int numSolutions = 0;
        for( int a = 0; a < n; a++ )
        {
            for( int b = 0; b < n; b++ )
            {
                for( int c = 0; c < n; c++ )
                {
                    for( int d = 0; d < n; d++ )
                    {
                        for( int e = 0; e < n; e++ )
                        {
                            if( digits[ a ] != 0 && digits[ d ] != 0 )
                            {
                                num1 = 100 * digits[ a ] + 10 * digits[ b ] + digits[ c ];
                                num2 = 10 * digits[ d ] + digits[ e ];
                                
                                p1 = num1 * digits[ d ];
                                p2 = num1 * digits[ e ];
                                sum = num1 * num2;
                                
                                if( check( digits, p1 ) && check( digits, p2 ) && p1 < 1000 
                                    && check( digits, sum ) && p2 < 1000 && sum < 10000 )
                                {
                                    numSolutions++;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        out.println( numSolutions );
        
        out.close();
        System.exit( 0 );
    }
    
    public static boolean check( int[] digits, int num )
    {
        boolean good = true;
        
        int temp;
        boolean tempBoolean;
        while( num > 0 )
        {
            temp = num % 10;
            num = num / 10;
            
            tempBoolean = false;
            for( int i = 0; i < digits.length; i++ )
            {
                if( temp == digits[ i ] )
                {
                    tempBoolean = true;
                }
            }
            
            if( !tempBoolean )
            {
                good = false;
            }
        }
        
        return good;
    }
}