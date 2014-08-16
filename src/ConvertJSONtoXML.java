import java.util.*;
import java.io.*;

import org.json.*;
import com.canes.converters;

public class ConvertJSONtoXML implements XMLJSONConverterI
{
	public static void main( String[] args)
		{
			try
			{
				if(args.length > 0 && args.length < 2)
					{
						try
						{
							BufferedReader reader = new BufferedReader( new FileReader( args[0] ));
						}
						catch( IOException e)
						{
							System.out.println(e.getMessage() );
						}

					}
			}
			catch( IndexOutOfBoundsException e)
			{
				System.out.println( e.getMessage() );
			}
		}

	public static void PrintUsage()
	{
		System.out.println( "This is how you use this program");
	}
}


