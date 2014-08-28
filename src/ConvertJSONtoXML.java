import java.util.*;
import java.lang.*;
import java.io.*;
import javax.json.JsonValue;

import javax.json.*;
import com.caanes.converters.*;

public class ConvertJSONtoXML implements XMLJSONConverterI
{
	public static void log(String message)
	{
		System.out.println(message);
	}

	public static void main( String[] args)
	{
		char [] buffer;
		JsonReader reader = null ;
		BufferedWriter writer = null;
		String jsonPath = null;
		String xmlPath  = null;
		try
		{
			if(args.length == 2)
				{
					jsonPath = args[0];
					xmlPath = args[1];
				}
			else
			{
				log("Usage: converter json-file xml-file");
			}
		}
		catch( IndexOutOfBoundsException e)
		{
			log("Usage: converter json-file xml-file" + e.getMessage() );
		}
		try
		{
			reader = Json.createReader(new FileReader(jsonPath) );
			writer = new BufferedWriter( new FileWriter(xmlPath) );
			JsonTree jTree = null;
			try
			{
				jTree = new JsonTree(reader.read() );
			}
			catch (JsonException e)
			{
				log("Invalid JSON file!" + e.getMessage() );
				return;
			}
			String xml = jTree.getXML();
			writer.write(xml);
		}  
		catch( IOException e)
		{
			log("IO error!" + e.getMessage() );
		}
		finally
		{
			try
			{
				if (writer != null)
					writer.close();
			}
			catch (IOException e)  {}
		}
		return;
	}

	public void convertJSONtoXML(File json, File xml) throws IOException 
	{
		return;
	}

	//JsonObject model = CreateExampleJsonObject();
	//navigateTree(model, null);
	public static JsonObject CreateExampleJsonObject()
	{
		JsonObject value = Json.createObjectBuilder()
				 .add("firstName", "John")
				 .add("lastName", "Smith")
				 .add("age", 25)
				 .add("address", Json.createObjectBuilder()
						 .add("streetAddress", "21 2nd Street")
						 .add("city", "New York")
						 .add("state", "NY")
						 .add("postalCode", "10021"))
				 .add("phoneNumber", Json.createArrayBuilder()
						 .add(Json.createObjectBuilder()
								 .add("type", "home")
								 .add("number", "212 555-1234"))
						 .add(Json.createObjectBuilder()
								 .add("type", "fax")
								 .add("number", "646 555-4567")))
				 .build();
		return value;
	}
}

