/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package coberturaParsing;

public class Line
{
    private boolean isHit;
    private boolean isValid;
    private long numberOfHits;
    private int lineNumber;

    public Line()
    {
        isHit=false;
        isValid = false;
        numberOfHits = 0;
    }

    public void setIsHit(boolean isHit)
    {
        this.isHit = isHit;
    }

    public void setIsValid(boolean isValid)
    {
        this.isValid = isValid;
    }

    public void setNumberOfHits(long numberOfHits)
    {
        this.numberOfHits = numberOfHits;
    }

    public void addHits(long hits)
    {
        numberOfHits+=hits;
    }

    public boolean getIsHit()
    {
        return isHit;
    }

    public boolean getIsValid()
    {
        return isValid;
    }

    public long getNumberOfHits()
    {
        return numberOfHits;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public void update()
    {
        if(numberOfHits>0)
            isHit = true;
    }

    public String toString()
    {
        return ("      line" + lineNumber + "\tHit: " + isHit +
            "\tHits: " + numberOfHits);
    }
}

