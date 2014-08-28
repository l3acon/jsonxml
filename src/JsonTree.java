import java.util.*;
import javax.json.*;
import java.lang.*;

/**
 * A class for operating on JsonStructures.
 * See  http://docs.oracle.com/javaee/7/api/javax/json/JsonStructure.html
 */
public class JsonTree 
{
	public String strAccumulator;
	public JsonValue tree;

	/**
	 * @param structure {@link javax.json.JsonValue} the json structure to work with 
	 */

	public JsonTree(JsonValue structure )
	{
		tree = structure;
		strAccumulator = new String();
	}
	
	/**
	 * cat concatonates the given string
	 * onto the internal JsonTree string
	 * out. In contrast to the built-in 
	 * function (String.concatonate(String)
	 * cat is shorter.
	 */
	private void cat(String a)
	{
		if( strAccumulator != null) 
			strAccumulator = strAccumulator + a;
		else
			return;
	}

	/**
	 * Call navigateTree with default values to 
	 * obtain all subtree entries and accumulate 
	 * them in strAccumulator.
	 */
	public String getXML()
	{
		navigateTree(tree, null);
		return strAccumulator;
	}

	/**
	 * Navigate a JsonValue tree and populate strAccumulator
	 * using CaANES specification for XML data.
	 * @param t {javax.json.JsonValue} the subtree to navigate
	 * @param key {java.String} the key from parent node (XML name='key')
	 * see: CaANES specification
	 * see: http://docs.oracle.com/javaee/7/api/javax/json/JsonStructure.html
	 */
	private void navigateTree(JsonValue t, String key ) 
	{
		//	JSON Object {} to XML <object>
		 if( t.getValueType() == JsonValue.ValueType.OBJECT)
		 {
			 //	unnamed key
				if(key == null)
					cat("<object>");
				// named key
				else
					cat("<object name=\"" + key + "\">");

				JsonObject object = (JsonObject) t;

				//	call for each sub-entry in object
				for (String name : object.keySet() )
					navigateTree(object.get(name), name);

				//	end this object
				cat("</object>");
		 }
		 else if( t.getValueType() == JsonValue.ValueType.ARRAY)
		 {
				if(key == null)
					cat("<array>");
				else
					cat("<array name=\"" + key + "\">");

				JsonArray array = (JsonArray) t;
				for (JsonValue val : array)
					navigateTree(val, null );

				cat("</array>");
		 }
		 else if( t.getValueType() == JsonValue.ValueType.STRING)
		 {
				JsonString st = (JsonString) t;
				if( key != null)
					cat("<string name=\"" + key + "\">" + st.getString() + "</string>");
				else
					cat("<string>" + st.getString() + "</string>");
		 }
		else if( t.getValueType() == JsonValue.ValueType.NUMBER)
		{
			JsonNumber num = (JsonNumber) t;
			if( key != null)
				cat("<number name=\"" + key + "\">" + num.toString() + "</number>");
			else
				cat("<number>" + num.toString() + "</number>");
		}
		else if( t.getValueType() == JsonValue.ValueType.NULL )
		{
			if( key != null)
				cat("<null name=\"" + key + "\"/>" );
			else
				cat("null array goes here");
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

