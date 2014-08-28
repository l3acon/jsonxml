package com.caanes.converters;

/**
 * Factory class for creating instances of {@link XMLJSONConverterI}.
 */
public final class ConverterFactory {

    /**
     * You should implement this method having it return your version of
     * {@link com.caanes.converters.XMLJSONConverterI}.
     *
     * @return {@link com.caanes.converters.XMLJSONConverterI} implementation you created.
		 * @return {this is a good point that's going to work well
		 *
     */
    public static final XMLJSONConverterI createXMLJSONConverter() 
		{
			return new ConvertJSONtoXML();
    }
}
