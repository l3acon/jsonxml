import java.util.*;
import java.lang.*;
import java.io.*;

import javax.json.*;
import com.caanes.converters.*;

public class ConvertJSONtoXML implements XMLJSONConverterI
{
	public static void main( String[] args)
	{
		char [] buffer;
		JsonStructure jsonst = null;
		//JsonObject model = CreateExampleJsonObject();
		//navigateTree(model, null);
		try
		{
			if(args.length > 0 && args.length < 3)
				{
					try
					{
						JsonReader reader = Json.createReader(new FileReader(args[0]));
						jsonst = reader.read();
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

		navigateTree(jsonst, null);

		return;
	}

	public void convertJSONtoXML(File json, File xml) throws IOException 
	{
		return;
	}

	public static void navigateTree(JsonValue tree, String key) 
	{
		 if( tree.getValueType() == JsonValue.ValueType.OBJECT)
		 {
				if(key == null)
					System.out.println("<object>");
				else
					System.out.println("<object name=\"" + key + "\">");

				JsonObject object = (JsonObject) tree;
				for (String name : object.keySet() )
					navigateTree(object.get(name), name);

				System.out.println("</object>");
		 }
		 else if( tree.getValueType() == JsonValue.ValueType.ARRAY)
		 {
				if(key == null)
					System.out.println("<array>");
				else
					System.out.println("<array name=\"" + key + "\">");

				JsonArray array = (JsonArray) tree;
				for (JsonValue val : array)
					navigateTree(val, null );

				System.out.println("</array>");
		 }
		 else if( tree.getValueType() == JsonValue.ValueType.STRING)
		 {
				JsonString st = (JsonString) tree;
				if( key != null)
					System.out.println("<string name=\"" + key + "\">" + st.getString() + "</string>");
				else
					System.out.println("<string>" + st.getString() + "</string>");
		 }
		else if( tree.getValueType() == JsonValue.ValueType.NUMBER)
		{
			JsonNumber num = (JsonNumber) tree;
			if( key != null)
				System.out.println("<number name=\"" + key + "\">" + num.toString() + "</number>");
			else
				System.out.println("<number>" + num.toString() + "</number>");
		}
		else if( tree.getValueType() == JsonValue.ValueType.NULL )
		{
			if( key != null)
				System.out.println("<null name=\"" + key + "\"/>" );
			else
				System.out.println("null array goes here");
		}
		else
		{
			if( key != null)
				System.out.println("<boolean name=\"" + key + "\">" + tree.getValueType().toString() + "</boolean>");
			else
				System.out.println("<boolean>" + tree.getValueType().toString() + "</boolean>");
		}
	}

	public static void PrintUsage()
	{
		System.out.println("Usage: converter json-file xml-file");
	}

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


