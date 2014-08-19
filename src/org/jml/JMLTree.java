import java.io.*;
import java.util.*;

public class JMLTree
{
	public JMLTree parent;
	public ArrayList<JMLTree> children;

	public String type; 	// can be object, string, number, bool, null, or array
	public String name; 	// can be any identifier string
	public String field; 	// the data
	
	// only used to construct a new tree?
	public void JMLTree()
	{
		children = new ArrayList<JMLTree>();
		this.parent = this;
	}	
	
	public JMLTree addChild()
	{
		JMLTree t = new JMLTree();
		this.children.add(t);
		t.parent = this;
		return t; 
	}	
	
	public boolean isRoot()
	{
		if(this.parent == this)
			return true;
		return false;
	}

	public void printTree()
	{
		if ( !this.isRoot() )
		{
			this.parent.printTree();
		}
		else
		{
			printSubtree();
		}
	}
			
	public void printSubtree()
	{
		// print out parents before children
		String str = new String();
		if( type != null)
			str = "<"+type+">"; 
		if( name != null)
			str = str + "name=\""+name+"\">";
		if( field != null)
			str = str + field;
		System.out.println(str);
		
		// if there are children, print them
		if( this.children.size() > 0 )
		{
			for( int i = 0; i < children.size(); i ++)
			{
				children.get(i).printSubtree();
			}
		}
		// print out closures 
		str = new String();
		if( type != null)
			str = str + "</"+type+">";
		System.out.println(str);

		return;
	}
}


