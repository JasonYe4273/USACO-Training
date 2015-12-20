/*
ID: yuzhou.1
LANG: JAVA
TASK: test
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class test
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "test.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "test.out" ) ) );
        StringTokenizer st = new StringTokenizer( in.readLine() );
        in.close();
        
        int num1 = Integer.parseInt( st.nextToken() );
        int num2 = Integer.parseInt( st.nextToken() );
        out.println( num1 + num2 );
        out.close();
        System.exit( 0 );
    }
}