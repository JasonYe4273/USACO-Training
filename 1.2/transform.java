/*
ID: yuzhou.1
LANG: JAVA
TASK: transform
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class transform
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "transform.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "transform.out" ) ) );
        
        // Read in the characters
        int n = Integer.parseInt( in.readLine() );
        char[][] before = new char[ n ][ n ];
        for( int i = 0; i < n; i++ )
        {
            before[ i ] = in.readLine().toCharArray();
        }
        
        char[][] after = new char[ n ][ n ];
        for( int i = 0; i < n; i++ )
        {
            after[ i ] = in.readLine().toCharArray();
        }
        in.close();
        
        // Simply check all transformations in order
        if( check1( n, before, after ) )
        {
            out.println( "1" );
        }
        else if( check2( n, before, after ) )
        {
            out.println( "2" );
        }
        else if( check3( n, before, after ) )
        {
            out.println( "3" );
        }
        else if( check4( n, before, after ) )
        {
            out.println( "4" );
        }
        else if( check5( n, before, after ) )
        {
            out.println( "5" );
        }
        else if( check6( n, before, after ) )
        {
            out.println( "6" );
        }
        else
        {
            out.println( "7" );
        }
        
        out.close();
    }
    
    // (i, j) goes to (j, n - i - 1)
    public static boolean check1( int n, char[][] before, char[][] after )
    {
        boolean is1 = true;
        for( int i = 0; i < n; i++ )
        {
            for( int j = 0; j < n; j++ )
            {
                if( before[ i ][ j ] != after[ j ][ n - i - 1 ] )
                {
                    is1 = false;
                }
            }
        }
        
        return is1;
    }
    
    // (i, j) goes to (n - i - 1, n - j - 1)
    public static boolean check2( int n, char[][] before, char[][] after )
    {
        boolean is2 = true;
        for( int i = 0; i < n; i++ )
        {
            for( int j = 0; j < n; j++ )
            {
                if( before[ i ][ j ] != after[ n - i - 1 ][ n - j - 1 ] )
                {
                    is2 = false;
                }
            }
        }
        
        return is2;
    }
    
    // (i, j) goes to (n - j - 1, i)
    public static boolean check3( int n, char[][] before, char[][] after )
    {
        boolean is3 = true;
        for( int i = 0; i < n; i++ )
        {
            for( int j = 0; j < n; j++ )
            {
                if( before[ i ][ j ] != after[ n - j - 1 ][ i ] )
                {
                    is3 = false;
                }
            }
        }
        
        return is3;
    }
    
    // Nothing changes
    public static boolean check6( int n, char[][] before, char[][] after )
    {
        boolean is6 = true;
        for( int i = 0; i < n; i++ )
        {
            for( int j = 0; j < n; j++ )
            {
                if( before[ i ][ j ] != after[ i ][ j ] )
                {
                    is6 = false;
                }
            }
        }
        
        return is6;
    }
    
    // (i, j) goes to (i, n - j - 1)
    public static char[][] reflect( int n, char[][] input )
    {
        char[][] temp = new char[ n ][ n ];
        
        for( int i = 0; i < n; i++ )
        {
            for( int j = 0; j < n; j++ )
            {
                temp[ i ][ j ] = input[ i ][ n - j - 1 ];
            }
        }
        
        return temp;
    }
    
    // Check that the reflection is equal
    public static boolean check4( int n, char[][] before, char[][] after )
    {
        boolean is4 = check6( n, reflect( n, before ), after );
        return is4;
    }
    
    // Check that 1, 2, or 3 is satisfied after reflecting before
    public static boolean check5( int n, char[][] before, char[][] after )
    {
        char[][] temp = reflect( n, before );
        
        if( check1( n, temp, after ) || check2( n, temp, after ) || check3( n, temp, after ) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}