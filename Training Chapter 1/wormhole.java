/*
ID: yuzhou.1
LANG: JAVA
TASK: wormhole
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class wormhole
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "wormhole.in" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "wormhole.out" ) ) );
        
        int n = Integer.parseInt( in.readLine() );
        
        int[] x = new int[ n ];
        int[] y = new int[ n ];
        for( int i = 0; i < n; i++ )
        {
            StringTokenizer st = new StringTokenizer( in.readLine() );
            x[ i ] = Integer.parseInt( st.nextToken() );
            y[ i ] = Integer.parseInt( st.nextToken() );
        }
        in.close();
        
        int numLoops = 0;
        
        int[] connectsTo = new int[ n ];
        if( n == 2 )
        {
            connectsTo[ 0 ] = 1;
            connectsTo[ 1 ] = 0;
            if( check( n, x, y, connectsTo ) )
            {
                numLoops++;
            }
        }
        else if( n == 4 )
        {
            for( int a = 1; a < 4; a++ )
            {
                for( int b = 1; b < 2; b++ )
                {
                    boolean[] used = new boolean[ n ];
                    for( int i = 0; i < n; i++ )
                    {
                        used[ i ] = false;
                    }
                    
                    int place = 0;
                    int place2 = 0;
                    int temp = 0;
                    boolean done;
                    
                    connectsTo[ 0 ] = a;
                    connectsTo[ a ] = 0;
                    used[ 0 ] = true;
                    used[ a ] = true;
                    
                    place = 0;
                    done = false;
                    while( !done )
                    {
                        if( !used[ place ] )
                        {
                            done = true;
                        }
                        else
                        {
                            place = ( place + 1 ) % n;
                        }
                    }
                    
                    place2 = ( place + 1 ) % n;
                    temp = 0;
                    done = false;
                    while( !done )
                    {
                        if( !used[ place2 ] )
                        {
                            temp++;
                        }
                        
                        if( temp == b )
                        {
                            done = true;
                        }
                        else
                        {
                            place2 = ( place2 + 1 ) % n;
                        }
                    }
                    
                    connectsTo[ place ] = place2;
                    connectsTo[ place2 ] = place;
                    used[ place ] = true;
                    used[ place2 ] = true;
                    
                    if( check( n, x, y, connectsTo ) )
                    {
                        numLoops++;
                    }
                }
            }
        }
        else if( n == 6 )
        {
            for( int a = 1; a < 6; a++ )
            {
                for( int b = 1; b < 4; b++ )
                {
                    for( int c = 1; c < 2; c++ )
                    {
                        boolean[] used = new boolean[ n ];
                        for( int i = 0; i < n; i++ )
                        {
                            used[ i ] = false;
                        }
                        
                        int place = 0;
                        int place2 = 0;
                        int temp = 0;
                        boolean done;
                        
                        connectsTo[ 0 ] = a;
                        connectsTo[ a ] = 0;
                        used[ 0 ] = true;
                        used[ a ] = true;
                        
                        place = 0;
                        done = false;
                        while( !done )
                        {
                            if( !used[ place ] )
                            {
                                done = true;
                            }
                            else
                            {
                                place = ( place + 1 ) % n;
                            }
                        }
                        
                        place2 = ( place + 1 ) % n;
                        temp = 0;
                        done = false;
                        while( !done )
                        {
                            if( !used[ place2 ] )
                            {
                                temp++;
                            }
                            
                            if( temp == b )
                            {
                                done = true;
                            }
                            else
                            {
                                place2 = ( place2 + 1 ) % n;
                            }
                        }
                        
                        connectsTo[ place ] = place2;
                        connectsTo[ place2 ] = place;
                        used[ place ] = true;
                        used[ place2 ] = true;
                        
                        place = 0;
                        done = false;
                        while( !done )
                        {
                            if( !used[ place ] )
                            {
                                done = true;
                            }
                            else
                            {
                                place = ( place + 1 ) % n;
                            }
                        }
                        
                        place2 = ( place + 1 ) % n;
                        temp = 0;
                        done = false;
                        while( !done )
                        {
                            if( !used[ place2 ] )
                            {
                                temp++;
                            }
                            
                            if( temp == c )
                            {
                                done = true;
                            }
                            else
                            {
                                place2 = ( place2 + 1 ) % n;
                            }
                        }
                        
                        connectsTo[ place ] = place2;
                        connectsTo[ place2 ] = place;
                        used[ place ] = true;
                        used[ place2 ] = true;
                        
                        if( check( n, x, y, connectsTo ) )
                        {
                            numLoops++;
                        }
                    }
                }
            }
        }
        else if( n == 8 )
        {
            for( int a = 1; a < 8; a++ )
            {
                for( int b = 1; b < 6; b++ )
                {
                    for( int c = 1; c < 4; c++ )
                    {
                        for( int d = 1; d < 2; d++ )
                        {
                            boolean[] used = new boolean[ n ];
                            for( int i = 0; i < n; i++ )
                            {
                                used[ i ] = false;
                            }
                            
                            int place = 0;
                            int place2 = 0;
                            int temp = 0;
                            boolean done;
                            
                            connectsTo[ 0 ] = a;
                            connectsTo[ a ] = 0;
                            used[ 0 ] = true;
                            used[ a ] = true;
                            
                            place = 0;
                            done = false;
                            while( !done )
                            {
                                if( !used[ place ] )
                                {
                                    done = true;
                                }
                                else
                                {
                                    place = ( place + 1 ) % n;
                                }
                            }
                            
                            place2 = ( place + 1 ) % n;
                            temp = 0;
                            done = false;
                            while( !done )
                            {
                                if( !used[ place2 ] )
                                {
                                    temp++;
                                }
                                
                                if( temp == b )
                                {
                                    done = true;
                                }
                                else
                                {
                                    place2 = ( place2 + 1 ) % n;
                                }
                            }
                            
                            connectsTo[ place ] = place2;
                            connectsTo[ place2 ] = place;
                            used[ place ] = true;
                            used[ place2 ] = true;
                            
                            place = 0;
                            done = false;
                            while( !done )
                            {
                                if( !used[ place ] )
                                {
                                    done = true;
                                }
                                else
                                {
                                    place = ( place + 1 ) % n;
                                }
                            }
                            
                            place2 = ( place + 1 ) % n;
                            temp = 0;
                            done = false;
                            while( !done )
                            {
                                if( !used[ place2 ] )
                                {
                                    temp++;
                                }
                                
                                if( temp == c )
                                {
                                    done = true;
                                }
                                else
                                {
                                    place2 = ( place2 + 1 ) % n;
                                }
                            }
                            
                            connectsTo[ place ] = place2;
                            connectsTo[ place2 ] = place;
                            used[ place ] = true;
                            used[ place2 ] = true;
                            
                            place = 0;
                            done = false;
                            while( !done )
                            {
                                if( !used[ place ] )
                                {
                                    done = true;
                                }
                                else
                                {
                                    place = ( place + 1 ) % n;
                                }
                            }
                            
                            place2 = ( place + 1 ) % n;
                            temp = 0;
                            done = false;
                            while( !done )
                            {
                                if( !used[ place2 ] )
                                {
                                    temp++;
                                }
                                
                                if( temp == d )
                                {
                                    done = true;
                                }
                                else
                                {
                                    place2 = ( place2 + 1 ) % n;
                                }
                            }
                            
                            connectsTo[ place ] = place2;
                            connectsTo[ place2 ] = place;
                            used[ place ] = true;
                            used[ place2 ] = true;
                            
                            if( check( n, x, y, connectsTo ) )
                            {
                                numLoops++;
                            }
                        }
                    }
                }
            }
        }
        else if( n == 10 )
        {
            for( int a = 1; a < 10; a++ )
            {
                for( int b = 1; b < 8; b++ )
                {
                    for( int c = 1; c < 6; c++ )
                    {
                        for( int d = 1; d < 4; d++ )
                        {
                            for( int e = 1; e < 2; e++ )
                            {
                                boolean[] used = new boolean[ n ];
                                for( int i = 0; i < n; i++ )
                                {
                                    used[ i ] = false;
                                }
                                
                                int place = 0;
                                int place2 = 0;
                                int temp = 0;
                                boolean done;
                                
                                connectsTo[ 0 ] = a;
                                connectsTo[ a ] = 0;
                                used[ 0 ] = true;
                                used[ a ] = true;
                                
                                place = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place ] )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place = ( place + 1 ) % n;
                                    }
                                }
                                
                                place2 = ( place + 1 ) % n;
                                temp = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place2 ] )
                                    {
                                        temp++;
                                    }
                                    
                                    if( temp == b )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place2 = ( place2 + 1 ) % n;
                                    }
                                }
                                
                                connectsTo[ place ] = place2;
                                connectsTo[ place2 ] = place;
                                used[ place ] = true;
                                used[ place2 ] = true;
                                
                                place = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place ] )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place = ( place + 1 ) % n;
                                    }
                                }
                                
                                place2 = ( place + 1 ) % n;
                                temp = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place2 ] )
                                    {
                                        temp++;
                                    }
                                    
                                    if( temp == c )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place2 = ( place2 + 1 ) % n;
                                    }
                                }
                                
                                connectsTo[ place ] = place2;
                                connectsTo[ place2 ] = place;
                                used[ place ] = true;
                                used[ place2 ] = true;
                                
                                place = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place ] )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place = ( place + 1 ) % n;
                                    }
                                }
                                
                                place2 = ( place + 1 ) % n;
                                temp = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place2 ] )
                                    {
                                        temp++;
                                    }
                                    
                                    if( temp == d )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place2 = ( place2 + 1 ) % n;
                                    }
                                }
                                
                                connectsTo[ place ] = place2;
                                connectsTo[ place2 ] = place;
                                used[ place ] = true;
                                used[ place2 ] = true;
                                
                                place = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place ] )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place = ( place + 1 ) % n;
                                    }
                                }
                                
                                place2 = ( place + 1 ) % n;
                                temp = 0;
                                done = false;
                                while( !done )
                                {
                                    if( !used[ place2 ] )
                                    {
                                        temp++;
                                    }
                                    
                                    if( temp == e )
                                    {
                                        done = true;
                                    }
                                    else
                                    {
                                        place2 = ( place2 + 1 ) % n;
                                    }
                                }
                                
                                connectsTo[ place ] = place2;
                                connectsTo[ place2 ] = place;
                                used[ place ] = true;
                                used[ place2 ] = true;
                                
                                if( check( n, x, y, connectsTo ) )
                                {
                                    numLoops++;
                                }
                            }
                        }
                    }
                }
            }
        }
        else if( n == 12 )
        {
            for( int a = 1; a < 12; a++ )
            {
                for( int b = 1; b < 10; b++ )
                {
                    for( int c = 1; c < 8; c++ )
                    {
                        for( int d = 1; d < 6; d++ )
                        {
                            for( int e = 1; e < 4; e++ )
                            {
                                for( int f = 1; f < 2; f++ )
                                {
                                    boolean[] used = new boolean[ n ];
                                    for( int i = 0; i < n; i++ )
                                    {
                                        used[ i ] = false;
                                    }
                                    
                                    int place = 0;
                                    int place2 = 0;
                                    int temp = 0;
                                    boolean done;
                                    
                                    connectsTo[ 0 ] = a;
                                    connectsTo[ a ] = 0;
                                    used[ 0 ] = true;
                                    used[ a ] = true;
                                    
                                    place = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place ] )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place = ( place + 1 ) % n;
                                        }
                                    }
                                    
                                    place2 = ( place + 1 ) % n;
                                    temp = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place2 ] )
                                        {
                                            temp++;
                                        }
                                        
                                        if( temp == b )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place2 = ( place2 + 1 ) % n;
                                        }
                                    }
                                    
                                    connectsTo[ place ] = place2;
                                    connectsTo[ place2 ] = place;
                                    used[ place ] = true;
                                    used[ place2 ] = true;
                                    
                                    place = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place ] )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place = ( place + 1 ) % n;
                                        }
                                    }
                                    
                                    place2 = ( place + 1 ) % n;
                                    temp = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place2 ] )
                                        {
                                            temp++;
                                        }
                                        
                                        if( temp == c )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place2 = ( place2 + 1 ) % n;
                                        }
                                    }
                                    
                                    connectsTo[ place ] = place2;
                                    connectsTo[ place2 ] = place;
                                    used[ place ] = true;
                                    used[ place2 ] = true;
                                    
                                    place = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place ] )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place = ( place + 1 ) % n;
                                        }
                                    }
                                    
                                    place2 = ( place + 1 ) % n;
                                    temp = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place2 ] )
                                        {
                                            temp++;
                                        }
                                        
                                        if( temp == d )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place2 = ( place2 + 1 ) % n;
                                        }
                                    }
                                    
                                    connectsTo[ place ] = place2;
                                    connectsTo[ place2 ] = place;
                                    used[ place ] = true;
                                    used[ place2 ] = true;
                                    
                                    place = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place ] )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place = ( place + 1 ) % n;
                                        }
                                    }
                                    
                                    place2 = ( place + 1 ) % n;
                                    temp = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place2 ] )
                                        {
                                            temp++;
                                        }
                                        
                                        if( temp == e )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place2 = ( place2 + 1 ) % n;
                                        }
                                    }
                                    
                                    connectsTo[ place ] = place2;
                                    connectsTo[ place2 ] = place;
                                    used[ place ] = true;
                                    used[ place2 ] = true;
                                    
                                    place = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place ] )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place = ( place + 1 ) % n;
                                        }
                                    }
                                    
                                    place2 = ( place + 1 ) % n;
                                    temp = 0;
                                    done = false;
                                    while( !done )
                                    {
                                        if( !used[ place2 ] )
                                        {
                                            temp++;
                                        }
                                        
                                        if( temp == f )
                                        {
                                            done = true;
                                        }
                                        else
                                        {
                                            place2 = ( place2 + 1 ) % n;
                                        }
                                    }
                                    
                                    connectsTo[ place ] = place2;
                                    connectsTo[ place2 ] = place;
                                    used[ place ] = true;
                                    used[ place2 ] = true;
                                    
                                    if( check( n, x, y, connectsTo ) )
                                    {
                                        numLoops++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        out.println( numLoops );
        
        out.close();
        System.exit( 0 );
    }
    
    public static boolean check( int n, int[] x, int[] y, int[] connectsTo )
    {
        boolean[] used = new boolean[ n ];
        for( int i = 0; i < n; i++ )
        {
            used[ i ] = false;
        }
        
        boolean hasLoop = false;
        int temp, place;
        for( int i = 0; i < n; i++ )
        {
            if( !used[ i ] )
            {
                place = i;
                boolean done;
                boolean firstTime = true;
                do
                {
                    done = true;
                    used[ place ] = true;
                    
                    temp = 1000000001;
                    int tempPlace = n;
                    for( int j = 0; j < n; j++ )
                    {
                        if( y[ j ] == y[ place ] && x[ j ] > x[ place ] && x[ j ] < temp )
                        {
                            temp = x[ j ];
                            tempPlace = connectsTo[ j ];
                            done = false;
                        }
                    }
                    place = tempPlace;
                    if( place == i && !firstTime )
                    {
                        done = true;
                        hasLoop = true;
                    }
                    
                    firstTime = false;
                }
                while( !done );
            }
        }
        
        return hasLoop;
    }
}