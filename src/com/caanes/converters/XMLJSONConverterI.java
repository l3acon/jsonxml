package com.caanes.converters;

import java.io.File;
import java.io.IOException;

/**
 * This interface provides methods that are required for creating a converter from XML to JSON.
 */
public interface XMLJSONConverterI {

    /**
     * Reads in the JSON from the given file and outputs teh data, converted to
     * XML, to the given file. Exceptions are thrown by this method so that the
     * caller can clean up the before exiting.
     *
     * @param json {@link java.io.File} from which to read JSON data.
     * @param xml {@link java.io.File} from which to read XML data.
     * @throws java.io.IOException
     */
    public void convertJSONtoXML(File json, File xml) throws IOException;
}
