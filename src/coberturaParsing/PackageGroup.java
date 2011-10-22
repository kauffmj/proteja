/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package coberturaParsing;

import java.util.ArrayList;
import java.util.Iterator;

public class PackageGroup
{
    private ArrayList<Classs> classes;
    private String name;

    public PackageGroup()
    {
        classes = new ArrayList<Classs>(5);
    }

    public void addClass(Classs classb)
    {
        classes.add(classb);
    }

    public ArrayList<Classs> getClasses()
    {
        return classes;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void update()
    {
        Iterator iterator = classes.iterator();
        while(iterator.hasNext())
        {
            ((Classs)iterator.next()).update();
        }
    }

    public String toString()
    {
        Iterator iterator = classes.iterator();
        String str= "package: "+name;
        while(iterator.hasNext())
        {
            str = str + "\n" + ((Classs)iterator.next()).toString();
        }
        return str;
    }
}
