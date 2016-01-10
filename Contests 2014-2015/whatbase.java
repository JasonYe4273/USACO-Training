import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class whatbase
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "whatbase.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "whatbase.out" ) ) );
        
        StringTokenizer st = new StringTokenizer( in.readLine() );
        int n = Integer.parseInt( st.nextToken() );
        
        for( int i = 0; i < n; i++ )
        {
            StringTokenizer st2 = new StringTokenizer( in.readLine() );
            int nX = Integer.parseInt( st2.nextToken() );
            int nY = Integer.parseInt( st2.nextToken() );
            
            boolean done = false;
            
            for( int x = 10; x < 20; x++ )
            {
                for( int y = 10; y < 20; y++ )
                {
                    if( !done )
                    {
                        int temp = ( nX / 100 ) * x * x + ( ( nX % 100 ) / 10 ) * x + ( nX % 10 );
                        int temp2 = ( nY / 100 ) * y * y + ( ( nY % 100 ) / 10 ) * y + ( nY % 10 );
                        
                        if( temp == temp2 )
                        {
                            out.println( x + " " + y );
                            done = true;
                        }
                    }
                }
            }
            
            for( int x = 20; x <= 15000; x++ )
            {
                int temp = ( nX / 100 ) * x * x + ( ( nX % 100 ) / 10 ) * x + ( nX % 10 );
                for( int y = (int) Math.sqrt( (double) temp / (double) ( nY / 100 ) ) - 5; y <= (int) Math.sqrt( (double) temp / (double) ( nY / 100 ) ) + 5; y++ )
                {
                    
                    if( !done )
                    {
                        int temp2 = ( nY / 100 ) * y * y + ( ( nY % 100 ) / 10 ) * y + ( nY % 10 );
                        
                        if( temp == temp2 )
                        {
                            out.println( x + " " + y );
                            done = true;
                        }
                    }
                }
            }
        }

        in.close();
        out.close();
    }
}