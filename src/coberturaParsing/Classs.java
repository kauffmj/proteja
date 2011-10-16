/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package coberturaParsing;

import java.util.ArrayList;
import java.util.Iterator;

public class Classs
{
    private ArrayList<Methodd> methods;
    private boolean isHit;
    private String name;

    public Classs()
    {
	    methods = new ArrayList<Methodd>(10);
	    isHit=false;
    }

    public void setIsHit(boolean isHit)
    {
	    this.isHit = isHit;
    }

    public boolean getIsHit()
    {
	    return isHit;
    }

    public void addMethod(Methodd method)
    {
	    methods.add(method);
    }

    public ArrayList<Methodd> getMethods()
    {
	    return methods;
    }

    public void setName(String name)
    {
	    this.name = name;
    }

    public String getName()
    {
	    return name;
    }

    public void update()
    {
	    Iterator first = methods.iterator();
	    while(first.hasNext())
	    {
		    ((Methodd)first.next()).update();
	    }
	    Iterator iterator = methods.iterator();
	    while(iterator.hasNext())
	    {
		    if(((Methodd)iterator.next()).getIsHit())
		    {
			    isHit=true;
		    }
	    }
    }

    public String toString()
    {
	    Iterator iterator = methods.iterator();
	    String str = "  Class: " + name + "\n  Hit: " + isHit;
	    while(iterator.hasNext())
	    {
		    str = str + "\n" + ((Methodd)iterator.next()).toString();
	    }
	    return str;
    }
}
