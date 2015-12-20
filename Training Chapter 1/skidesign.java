/*
ID: yuzhou.1
LANG: JAVA
TASK: skidesign
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class skidesign
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "skidesign.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "skidesign.out" ) ) );
        
        int n = Integer.parseInt( in.readLine() );
        
        int[] height = new int[ n ];
        int[] numChanges = new int[ n ];
        boolean[] isShortest = new boolean[ n ];
        boolean[] isTallest = new boolean[ n ];
        for( int i = 0; i < n; i++ )
        {
            height[ i ] = Integer.parseInt( in.readLine() );
            numChanges[ i ] = 0;
            isShortest[ i ] = false;
            isTallest[ i ] = false;
        }
        in.close();
        
        int cost = 0;
        int shortest = 101;
        int tallest = 0;
        for( int i = 0; i < n; i++ )
        {
            if( height[ i ] < shortest )
            {
                shortest = height[ i ];
            }
            
            if( height[ i ] > tallest )
            {
                tallest = height[ i ];
            }
        }
        
        for( int i = 0; i < n; i++ )
        {
            if( height[ i ] == shortest )
            {
                isShortest[ i ] = true;
            }
            
            if( height[ i ] == tallest )
            {
                isTallest[ i ] = true;
            }
        }
        
        while( tallest - shortest > 17 )
        {
            int shortCost = 0;
            int tallCost = 0;
            for( int i = 0; i < n; i++ )
            {
                if( isShortest[ i ] )
                {
                    shortCost += 2 * numChanges[ i ] + 1;
                }
                
                if( isTallest[ i ] )
                {
                    tallCost += 2 * numChanges[ i ] + 1;
                }
            }
            
            if( shortCost < tallCost )
            {
                cost += shortCost;
                
                for( int i = 0; i < n; i++ )
                {
                    if( height[ i ] == shortest )
                    {
                        height[ i ]++;
                        numChanges[ i ]++;
                    }
                }
                
                shortest++;
                
                for( int i = 0; i < n; i++ )
                {
                    if( height[ i ] == shortest )
                    {
                        isShortest[ i ] = true;
                    }
                }
            }
            else
            {
                cost += tallCost;
                
                for( int i = 0; i < n; i++ )
                {
                    if( height[ i ] == tallest )
                    {
                        height[ i ]--;
                        numChanges[ i ]++;
                    }
                }
                
                tallest--;
                
                for( int i = 0; i < n; i++ )
                {
                    if( height[ i ] == tallest )
                    {
                        isTallest[ i ] = true;
                    }
                }
            }
        }
        
        out.println( cost );
        
        out.close();
        System.exit( 0 );
    }
}