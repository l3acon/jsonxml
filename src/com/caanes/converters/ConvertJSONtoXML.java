package com.caanes.converters;

import java.util.*;
import java.lang.*;
import java.io.*;
import javax.json.JsonValue;

import javax.json.*;

/**
 * A class for converting Json files to XML files.
 * This class impliments the XMLJSONConverterI interface.
 */
public class ConvertJSONtoXML implements XMLJSONConverterI
{
	private String strAccumulator;
	private JsonValue tree;

	/**
	 * Default constructor is used when the class is implementing 
	 * XMLJSONConverterI.
	 */
	public ConvertJSONtoXML()
	{
		strAccumulator = null;
		tree = null;
	}

	/**
	 * Initializing with a JsonValue (javax.json) can be used with getXML() to 
	 * convert without the interface.
	 */
	public ConvertJSONtoXML(JsonValue t)
	{
		tree = t;
		strAccumulator = null;
	}

	/**
	 * cat concatonates the given string onto the internal JsonTree string 
	 * strAccumulator.
	 * In contrast to the built-in function (String.concatonate(String) cat is
	 * shorter.
	 */
	private void cat(String a)
	{
		if( strAccumulator != null) 
			strAccumulator = strAccumulator + a;
		else
			log("strAcc not set");
		return;
	}

	/**
	 * Public method for returning XML in string format.
	 * Call navigateTree with default values to obtain all subtree entries and 
	 * accumulate them in strAccumulator.
	 */
	public String getXML()
	{
		strAccumulator = new String();
		navigateTree(tree, null);
		return strAccumulator;
	}

	/**
	 * Internal method for reseting and converting.
	 */
	private void convert()
	{
		strAccumulator = new String();
		navigateTree(tree, null);
	}

	/**
	 * Logging to System.out for debugging and error messages.
	 */
	private void log(String message)
	{
		System.out.println(getClass().toString() + ": " + message);
	}

	/**
	 * Reads in the JSON from the given file and outputs teh data, converted to
	 * XML, to the given file. Exceptions are thrown by this method so that the
	 * caller can clean up the before exiting.
	 *
	 * @param json {@link java.io.File} from which to read JSON data.
	 * @param xml {@link java.io.File} from which to read XML data.
	 * @throws java.io.IOException
	 */
	public void convertJSONtoXML(File jsonFile, File xmlFile) throws IOException 
	{
		JsonReader reader = null ;
		BufferedWriter writer = null;
		try
		{
			reader = Json.createReader(new FileReader(jsonFile) );
			writer = new BufferedWriter( new FileWriter(xmlFile) );
			tree = null;
			try
			{
				tree = reader.read();
			}
			catch (JsonException e)
			{
				log("Invalid JSON file!" + e.getMessage() );
				return;
			}
			convert();
			writer.write(strAccumulator);
		}  
		catch( IOException e)
		{
			log("IO error!" );
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

	/**
	 * Navigate a JsonValue tree and populate strAccumulator using CaANES 
	 * specification for XML data.
	 * @param t {javax.json.JsonValue} the subtree 
	 * to navigate
	 * @param key {java.String} the key from parent 
	 * node (XML name='key')
	 * see: CaANES specification
	 * see: http://docs.oracle.com/javaee/7/api/javax/json/JsonStructure.html
	 */
	private void navigateTree(JsonValue t, String key ) 
	{
		//	JSON Object {} to XML <object>
		 if( t.getValueType() == JsonValue.ValueType.OBJECT)
		{
			 //	unnamed key
			 //	(similar pattern in subsequent JSON array)
				if(key == null)
					cat("<object>");
				// named key
				else
					cat("<object name=\"" + key + "\">");

				JsonObject object = (JsonObject) t;

				//	go deeper for each sub-entry in object
				for (String name : object.keySet() )
					navigateTree(object.get(name), name);

				//	end this object
				cat("</object>");
		 }

		//	JSON Array [] to XML <array>
		else if( t.getValueType() == JsonValue.ValueType.ARRAY)
		{
			//	nameless string entries
				if(key == null)
					cat("<array>");

				else
					cat("<array name=\"" + key + "\">");

				JsonArray array = (JsonArray) t;
				for (JsonValue val : array)
					navigateTree(val, null );

				cat("</array>");
		}
		// JSON String to XML <string>	
		else if( t.getValueType() == JsonValue.ValueType.STRING)
		{
				JsonString st = (JsonString) t;
				if( key != null)
					cat("<string name=\"" + key + "\">" + st.getString() + "</string>");
				else
					cat("<string>" + st.getString() + "</string>");
		 }
		//	JSON Number to XML <number>
		else if( t.getValueType() == JsonValue.ValueType.NUMBER)
		{
			JsonNumber num = (JsonNumber) t;
			if( key != null)
				cat("<number name=\"" + key + "\">" + num.toString() + "</number>");
			else
				cat("<number>" + num.toString() + "</number>");
		}
		//	JSON Null to XML <null>
		else if( t.getValueType() == JsonValue.ValueType.NULL )
		{
			if( key != null)
				cat("<null name=\"" + key + "\"/>" );
			else
				cat("<null>" + key + "\">" + "</null>");
		}
		else
		{
			if( key != null)
				cat("<boolean name=\"" + key + "\">" + t.getValueType().toString().toLowerCase() + "</boolean>");
			else
				cat("<boolean>" + t.getValueType().toString().toLowerCase() + "</boolean>");
		}
	}
}

