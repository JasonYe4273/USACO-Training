import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class censor
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "censor.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "censor.out" ) ) );
        
        String s = in.readLine();
        String t = in.readLine();
        in.close();
        
        int tLength = t.length();
        
        boolean done = false;
        while( !done )
        {
            int place = s.indexOf( t );
            if( place != -1 )
            {
                s = s.substring( 0, place ) + s.substring( place + tLength, s.length() );
            }
            else
            {
                done = true;
            }
        }
        
        out.println( s );
        out.close();
    }
}