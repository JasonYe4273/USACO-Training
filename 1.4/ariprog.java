/*
ID: yuzhou.1
LANG: JAVA
TASK: ariprog
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ariprog
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("ariprog.in" ));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        
        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());
        
        boolean[] isBisquare = new boolean[2 * m * m + 1];
        for (int i = 0; i <= m; i++) for (int j = i; j <= m; j++) isBisquare[i * i + j * j] = true;
        
        in.close();
        
        int temp;
        boolean done;
        boolean atLeastOne = false;
        for (int i = 1; i <= 2 * m * m / ( n - 1 ); i++)
        {
            for (int j = 0; j <= 2 * m * m - ( n - 1 ) * i; j++)
            {
                boolean valid = true;
                done = false;
                temp = 0;
                while (!done && temp < n)
                {
                    if (!isBisquare[j + i * temp])
                    {
                        valid = false;
                        done = true;
                    }
                    
                    temp++;
                }
                
                if (valid)
                {
                    out.println(j + " " + i);
                    atLeastOne = true;
                }
            }
        }
        
        if (!atLeastOne) out.println("NONE");
        
        out.close();
        System.exit( 0 );
    }
}