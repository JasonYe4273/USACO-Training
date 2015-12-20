import java.io.IOException;

class Tester
{
    public static void main( String[] args ) throws IOException
    {
        int n = 12;
        int[] connectsTo = new int[ n ];
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
                                
                                for( int i = 0; i < n; i++ )
                                {
                                    System.out.print( connectsTo[ i ] + " " );
                                }
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }
}