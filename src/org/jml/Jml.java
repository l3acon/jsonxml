import java.util.*;
import java.io.*;
import java.lang.*;

import org.jml.*;

public class Jml 
{
	Stack<Character> stack;
	String t;
	JMLTree tjml;
	JMLTree current;

	public void Jml()
	{
		stack = new Stack<Character>();

		// initialize root of JMLTree
		tjml = new JMLTree();
	}

	public void buildTree( String w)
	{
		// keeps track of fields
		// toggles at colon
		// and reset at array or object
		boolean field = false;

		// main loop
		for( int i = 0; i < w.length(); i++)
		{
			System.out.println(i);
			switch( w.charAt(i) )	
			{
				case ':':
					field = !field;
					break;

					// begin an object
				case '{': 
					field = false;
					stack.push(new Character('{') );
					tjml = tjml.addChild();
					tjml.type = "object";
					break;
					
					// begin an array
				case '[':
					field = false;
					stack.push(new Character('['));
					tjml = tjml.addChild();
					tjml.type = "array";

					// some useful text
				case  '"':
					t = new String();
					while( w.charAt(i) != '"' && i < w.length() )
					{
						t = t + w.charAt(i);
						i++;
					}
					if(field)
					{
						tjml.field = t;
						tjml.type = evalType(t);
					}
					else
						tjml.name = t;
					break;
				default:
				
				System.out.println("I'm working hard");
				break;
			}


			if( isPair( w.charAt(i) ))
			{	
					// go up
					tjml = tjml.parent; 
					if (stack.empty())
					{
						System.out.println("Input file not valid!");
						return;
					}
					stack.pop();
			}
		}

		tjml.printTree();
	}

	public String evalType(String field)
	{
		if(field.matches("\\d") )
		{
			return new String("number");
		}
		if(field.matches("true|false") )
		{
			return new String("boolean") ;
		}
		return new String("string");
	}

	public boolean isPair( char in)
	{
		if( (char) stack.peek() == '{')
		{
			if( in == '}');
					{
					return true;
					}
		}
		if( (char) stack.peek()  == '[')

		{
			if( in == ']' )
			{
				return true;
			}
		}
		if( (char) stack.peek() == '"' && in == '"')
		{
			return true;
		}
		return false;
	}
}


