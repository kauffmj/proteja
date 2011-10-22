/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package coberturaParsing;

import java.util.ArrayList;
import java.util.Iterator;

public class Methodd
{
    private ArrayList<Line> lines;
    private boolean isHit;
    private String name;

    public Methodd()
    {
        lines=new ArrayList<Line>(100);
        isHit = false;
    }

    public void addLine(Line Line)
    {
        lines.add(Line);
    }

    public ArrayList<Line> getLines()
    {
        return lines;
    }

    public void setIsHit(boolean isHit)
    {
        this.isHit = isHit;
    }

    public void setName(String name)
    {
        this.name= name;
    }

    public boolean getIsHit()
    {
        return isHit;
    }

    public String getName()
    {
        return name;
    }

    public void update()
    {
        Iterator first = lines.iterator();
        while(first.hasNext())
        {
            ((Line)first.next()).update();
        }
        Iterator iterator = lines.iterator();
        while(iterator.hasNext())
        {
            if(((Line)iterator.next()).getIsHit())
            {
                isHit = true;
            }
        }
    }

    public String toString()
    {
        Iterator iterator = lines.iterator();
        String str = "    Method: " + name + "\n    Hit: " + isHit;
        while(iterator.hasNext())
        {
            str = str + "\n" + ((Line)iterator.next()).toString();
        }
        return str;
    }
}
