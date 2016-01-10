import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bgm
{
    public static void main( String args[] ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "bgm.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "bgm.out" ) ) );
        
        int n = Integer.parseInt( in.readLine() );
        
        int[] b = { 0, 0, 0, 0, 0, 0, 0 };
        int[] e = { 0, 0, 0, 0, 0, 0, 0 };
        int[] s = { 0, 0, 0, 0, 0, 0, 0 };
        int[] i = { 0, 0, 0, 0, 0, 0, 0 };
        int[] g = { 0, 0, 0, 0, 0, 0, 0 };
        int[] o = { 0, 0, 0, 0, 0, 0, 0 };
        int[] m = { 0, 0, 0, 0, 0, 0, 0 };
        for( int k = 0; k < n; k++ )
        {
            StringTokenizer st = new StringTokenizer( in.readLine() );
            String variable = st.nextToken();
            int mod = Integer.parseInt( st.nextToken() );
            while( mod < 0 )
            {
                mod += 7;
            }
            while( mod > 6 )
            {
                mod -= 7;
            }
            
            if( variable.equals( "B" ) )
            {
                b[ mod ]++;
            }
            else if( variable.equals( "E" ) )
            {
                e[ mod ]++;
            }
            else if( variable.equals( "S" ) )
            {
                s[ mod ]++;
            }
            else if( variable.equals( "I" ) )
            {
                i[ mod ]++;
            }
            else if( variable.equals( "G" ) )
            {
                g[ mod ]++;
            }
            else if( variable.equals( "O" ) )
            {
                o[ mod ]++;
            }
            else
            {
                m[ mod ]++;
            }
        }
        in.close();
        
        long answer = 0;
        for( int j = 0; j < 343; j++ )
        {
            int temp = j;
            int B = temp % 7;
            temp = temp / 7;
            int E = temp % 7;
            temp = temp / 7;
            int S = temp % 7;
            for( int k = 0; k < 343; k++ )
            {
                temp = k;
                int I = temp % 7;
                temp = temp / 7;
                int G = temp % 7;
                temp = temp / 7;
                int O = temp % 7;
                for( int M = 0; M < 7; M++ )
                {
                    int bessie = B + E + S + S + I + E;
                    int goes = G + O + E + S;
                    int moo = M + O + O;
                    
                    if( ( bessie / 7 ) * 7 == bessie || ( goes / 7 ) * 7 == goes || ( moo / 7 ) * 7 == moo )
                    {
                        long product = b[ B ];
                        product = product * e[ E ];
                        product = product * s[ S ];
                        product = product * i[ I ];
                        product = product * g[ G ];
                        product = product * o[ O ];
                        product = product * m[ M ];
                        answer += product;
                    }
                }
            }
        }
        
        out.println( answer );
        out.close();
    }
}