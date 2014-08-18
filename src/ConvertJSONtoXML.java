import java.util.*;
import java.io.*;

import org.json.*;
//import com.caanes.converters;

public class ConvertJSONtoXML //implements XMLJSONConverterI
{
	public static void main( String[] args)
		{
			char [] buffer;
			try
			{
				if(args.length > 0 && args.length < 3)
					{
						try
						{
							InputStream fin = new FileInputStream(args[0]);
							int fsize = fin.available();
							buffer = new char [fsize];
							for( int i = 0; i < fsize; i++)
							{
								buffer[i] = (char) fin.read();
							}
							fin.close();

							JSONObject json = new JSONObject(new String(buffer) );
							String xml = XML.toString( json );

							BufferedWriter fout = new BufferedWriter( new FileWriter( args[1] ) );
							fout.write(xml);
							fout.close();
						}  
						catch( IOException e)
						{
							System.out.println(e.getMessage() );
						}
					}
				else
				{
					PrintUsage();
				}
			}
			catch( IndexOutOfBoundsException e)
			{
				System.out.println( e.getMessage() );
			}
			return;
		}

	public static void PrintUsage()
	{
		System.out.println("Usage: converter json-file xml-file");
	}
}


