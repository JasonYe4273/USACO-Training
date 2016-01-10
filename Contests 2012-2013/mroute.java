import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class mroute
{
	public static void main( String[] args ) throws IOException
	{
		BufferedReader in = new BufferedReader( new FileReader( "mroute.in" ) );
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "mroute.out" ) ) );
		
		
        in.close();
        
        out.close();
	}
}
