/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package coberturaParsing;

import java.util.ArrayList;
import java.util.Iterator;

public class Report
{
    private String name;
    private ArrayList<PackageGroup> packages;

    public Report()
    {
	    packages = new ArrayList<PackageGroup>(1);
    }

    public void addPackage(PackageGroup pack)
    {
	    packages.add(pack);
    }

    public ArrayList<PackageGroup> getPackages()
    {
	    return packages;
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
	    Iterator iterator = packages.iterator();
	    while(iterator.hasNext())
	    {
		    ((PackageGroup)iterator.next()).update();
	    }
    }

    public String toString()
    {
	    String str = "Report " + name;
	    Iterator iterator = packages.iterator();
	    while(iterator.hasNext())
	    {
		    str = str + "\n" + ((PackageGroup)iterator.next()).toString();
	    }
	    return str;
    }
}
