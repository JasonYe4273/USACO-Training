/*
ID: yuzhou.1
LANG: JAVA
TASK: namenum
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class namenum
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader in = new BufferedReader( new FileReader( "namenum.in" ) );
        BufferedReader dict = new BufferedReader( new FileReader( "dict.txt" ) );
        PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter( "namenum.out" ) ) );
        
        // Read in the sequence of the numbers
        char[] num = in.readLine().toCharArray();
        in.close();
        
        boolean atLeastOne = false;
        char[] name;
        String temp = "a";
        do
        {
        	// Read in the next word in the dictionary
            temp = dict.readLine();
            if( temp != null )
            {
                name = temp.toCharArray();
                if( name.length == num.length )
                {
                    Checker c = new Checker();
                    boolean works = true;
                    
                    // Check each character
                    for( int i = 0; i < name.length; i++ )
                    {
                        if( !c.check( num[ i ], name[ i ] ) )
                        {
                           works = false;
                        }
                    }
                    
                    // Print it out if it works
                    if( works )
                    {
                        atLeastOne = true;
                        out.println( temp );
                    }
                }
            }
        }
        while( temp != null );
        dict.close();
        
        // Print out NONE if there isn't at least one
        if( !atLeastOne )
        {
            out.println( "NONE" );
        }
        
        out.close();
    }
}

class Checker
{
    public Checker()
    {
        
    }
    
    public boolean check( char num, char letter )
    {
        if( num == '2' )
        {
            if( letter == 'A' || letter == 'B' || letter == 'C' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if( num == '3' )
        {
            if( letter == 'D' || letter == 'E' || letter == 'F' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if( num == '4' )
        {
            if( letter == 'G' || letter == 'H' || letter == 'I' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if( num == '5' )
        {
            if( letter == 'J' || letter == 'K' || letter == 'L' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if( num == '6' )
        {
            if( letter == 'M' || letter == 'N' || letter == 'O' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if( num == '7' )
        {
            if( letter == 'P' || letter == 'R' || letter == 'S' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if( num == '8' )
        {
            if( letter == 'T' || letter == 'U' || letter == 'V' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if( num == '9' )
        {
            if( letter == 'W' || letter == 'X' || letter == 'Y' )
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}