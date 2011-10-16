package org.joda.time;

import java.io.*;
import java.util.*;
import java.text.*;

import org.joda.time.base.*;
import org.joda.time.convert.*;
import org.joda.time.field.*;
import org.joda.time.format.*;
import org.joda.time.chrono.*;
import org.joda.time.*;
import org.junit.Test;
import junit.framework.*;

public class HoursTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 1, 1, 3, 4, 5, 6);
		Interval interval = new Interval(start, end);
		String actual = Hours.hoursIn(interval).toString();
        String expected = "PT171H";
        assertEquals(actual,expected);
	}
    @Test
	public void test2(){
		String actual = Hours.parseHours("PT3H").toString();
        String expected = "PT3H";
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		Hours hours = Hours.hours(3);
		int actual = hours.getHours();
        int expected = 3;
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------
	//------------------------  PCI ----------------------------------------------------------------------------------
    @Test
	public void test4(){
		Hours hours = Hours.hours(3);
		LocalTime start = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), ISOChronology.getInstanceUTC());
		LocalTime end = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), ISOChronology.getInstanceUTC());
		String actual = hours.hoursBetween(start, end).toString();
        String expected = "PT0H";
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		Hours hours = Hours.hours(3);
		LocalTime start = new LocalTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		LocalTime end = new LocalTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		String actual = hours.hoursBetween(start, end).toString();
        String expected = "PT0H";
        assertEquals(actual,expected);
	}
	//------------------------  PCI ----------------------------------------------------------------------------------
}
