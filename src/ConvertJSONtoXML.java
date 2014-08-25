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
		 if (key != null)
				System.out.print("Key " + key + ": ");

		 if( tree.getValueType() == JsonValue.ValueType.OBJECT)
		 {
			 System.out.println("OBJECT ");
			 JsonObject object = (JsonObject) tree;
			 for (String name : object.keySet() )
				 navigateTree(object.get(name), name);
			 System.out.println(" OBJECT");
		 }
		 else if( tree.getValueType() == JsonValue.ValueType.ARRAY)
		 {
			 System.out.println("ARRAY ");
			 JsonArray array = (JsonArray) tree;
			 for(JsonValue val : array)
				 navigateTree(val, null);
			 System.out.println(" ARRAY");
		 }
		 else if( tree.getValueType() == JsonValue.ValueType.STRING)
		 {
			 JsonString st = (JsonString) tree;
			 System.out.println("STRING " + st.getString() + " STRING");
		 }

		else if( tree.getValueType() == JsonValue.ValueType.NUMBER)
		{
				JsonNumber num = (JsonNumber) tree;
				System.out.println("NUMBER " + num.toString() + " NUMBER");
		}
		else if( tree.getValueType() == JsonValue.ValueType.NULL )
		{
			System.out.print("NULL ");
			System.out.println(tree.getValueType().toString() );
			System.out.print(" NULL");
		}
		else
		{
			System.out.print("BOOLEAN ");
			System.out.println(tree.getValueType().toString() );
			System.out.print(" BOOLEAN ");
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


