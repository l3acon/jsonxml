import java.util.*;
import java.io.*;
import javax.json.*;

import com.caanes.converters.*;

public class ConvertJSONtoXML implements XMLJSONConverterI
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
													JsonReader reader = Json.createReader(new FileReader("args[0]"));

							JsonStructure jsonst = reader.read();
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
	public void convertJSONtoXML(File json, File xml) throws IOException 
	{
		return;
	}


	public static void PrintUsage()
	{
		System.out.println("Usage: converter json-file xml-file");
	}
}


