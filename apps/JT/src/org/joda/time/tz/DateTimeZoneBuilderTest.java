package org.joda.time.tz;

import java.io.*;
import java.util.*;
import java.text.*;

import org.joda.time.base.*;
import org.joda.time.convert.*;
import org.joda.time.field.*;
import org.joda.time.format.*;
import org.joda.time.chrono.*;
import org.joda.time.*;
import org.joda.time.tz.*;

import org.junit.Test;
import junit.framework.*;

public class DateTimeZoneBuilderTest extends TestCase
{
    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
    }*/
	//------------------------  OAN ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		DateTimeZoneBuilder dtzb = new DateTimeZoneBuilder();
		dtzb = dtzb.addCutover(-2147483648, 'w', 1, 1, 0, false, 0);
		DateTimeZone dtz = dtzb.toDateTimeZone("Europe/London", false);
		String actual = dtz.toString();
        String expected = "Europe/London";
        assertEquals(actual,expected);
	}
	/*public static void main(String[] args){
		DateTimeZoneBuilder dtzb = new DateTimeZoneBuilder();
		dtzb = dtzb.addCutover(-2147483648, 'w', 1, 1, 0, false, 0);
		dtzb = dtzb.setStandardOffset(-28378000);
		dtzb = dtzb.addCutover(1883, 'w', 11, 18, 0, false, 43200000);		
		dtzb = dtzb.addRecurringSavings("PST",       0, 1918, 1919, 'w', 10, -1, 7, false, 7200000);
		DateTimeZone dtz = dtzb.toDateTimeZone("Europe/London", false);
		System.out.println(dtz.toString());
	}*/
}
