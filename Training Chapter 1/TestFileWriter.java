import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class TestFileWriter
{
    public static void main( String[] args ) throws IOException
    {
        PrintWriter out = new PrintWriter (new BufferedWriter(new FileWriter("ariprog.in")));
        
        out.println("3");
        out.println("2");
        out.close();
        System.exit(0);
    }
}