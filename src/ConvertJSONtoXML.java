import java.util.*;
import java.io.*;

import org.jml.*;
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
							Jml jml = new Jml();
							jml.buildTree( new String(buffer) );

							//BufferedWriter fout = new BufferedWriter( new FileWriter( args[1] ) );
							//fout.write(xml);
							//fout.close();
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


