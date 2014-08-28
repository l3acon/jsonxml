import java.io.*;
import com.caanes.converters.*;

public class cli 
{	
	public static void main( String[] args)
	{
		String jsonPath = null;
		String xmlPath  = null;

		File jsonFile = null;
		File xmlFile = null;

		try
		{
			if(args.length == 2)
				{
					jsonPath = args[0];
					xmlPath = args[1];
				}
			else
			{
				System.out.println("Usage: converter json-file xml-file");
			}
		}
		catch( IndexOutOfBoundsException e)
		{
			System.out.println("Usage: converter json-file xml-file" + e.getMessage() );
		}

		jsonFile = new File(jsonPath);
		xmlFile = new File(xmlPath);

		XMLJSONConverterI converter = ConverterFactory.createXMLJSONConverter();
		try
		{
			converter.convertJSONtoXML(jsonFile, xmlFile);
		}
		catch( IOException e)
		{ }
	}

}

