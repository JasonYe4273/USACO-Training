import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Crosswords
{
    public static void main( String[] args ) throws Exception
    {
        BufferedReader in = new BufferedReader( new FileReader( "crosswords.in" ));
        BufferedWriter out = new BufferedWriter( new FileWriter( "crosswords.out" ));
        
        String line = in.readLine();
        String[] nums = new String[ 2 ];
        nums = line.split( " " );
        int n = Integer.parseInt( nums[ 0 ] );
        int m = Integer.parseInt( nums[ 1 ] );
        
        boolean[][] isEmpty = new boolean[ n + 2 ][ n + 2 ];
        boolean[][] isClue = new boolean[ n + 2 ][ m + 2 ];
        int clues = 0;
        
        for( int i = 0; i < n + 2; i++ )
        {
            for( int j = 0; j < m + 2; j++ )
            {
                if( i == 0 || i == n + 1 || j == 0 || j == m + 1 )
                {
                    isEmpty[ i ][ j ] = false;
                }
                else if( (char) in.read() == '.' )
                {
                    isEmpty[ i ][ j ] = true;
                }
                else
                {
                    isEmpty[ i ][ j ] = false;
                }
                
                isClue[ i ][ j ] = false;
            }
            if( i != 0 )
            {
                in.skip( 1 );
            }
        }
        in.close();
        
        for( int i = 1; i <= n; i++ )
        {
            for( int j = 1; j <= m; j++ )
            {
                if( isEmpty[ i ][ j ] && ( ( !isEmpty[ i - 1 ][ j ] && isEmpty[ i + 1 ][ j ] && isEmpty[ i + 2 ][ j ] ) 
                    || ( !isEmpty[ i ][ j - 1 ] && isEmpty[ i ][ j + 1 ] && isEmpty[ i ][ j + 2 ] ) ) )
                {
                    isClue[ i ][ j ] = true;
                    clues++;
                }
            }
        }
        
        out.write( String.valueOf( clues ) );
        out.newLine();
        for( int i = 1; i <= n; i++ )
        {
            for( int j = 1; j <= m; j++ )
            {
                if( isClue[ i ][ j ] )
                {
                    out.write( String.valueOf( i ) + ' ' + String.valueOf( j ) );
                    out.newLine();
                }
            }
        }
        out.close();
    }
}