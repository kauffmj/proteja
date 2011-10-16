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

public class WeeksTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
    /* ProtectError @Test
	public void test1(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);
		Interval interval = new Interval(start, end);
		String actual = Weeks.weeksIn(interval).toString();
        String expected = "P1W";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test2(){
		String actual = Weeks.parseWeeks("P3W").toString();
        String expected = "P3W";
        assertEquals(actual,expected);
	}
    /* ProtectError @Test
	public void test3(){
		Weeks weeks = Weeks.weeks(5);
		int actual = weeks.getWeeks();
        int expected = 5;
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------

	//------------------------  PCI ----------------------------------------------------------------------------------
    @Test
	public void test4(){
		Weeks weeks = Weeks.weeks(5);
		LocalDate start = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), ISOChronology.getInstanceUTC());
		LocalDate end = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), ISOChronology.getInstanceUTC());
		String actual = weeks.weeksBetween(start, end).toString();
        String expected = "P52W";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test5(){
		Weeks weeks = Weeks.weeks(5);
		LocalDate start = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		LocalDate end = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		String actual = weeks.weeksBetween(start, end).toString();
        String expected = "P52W";
        assertEquals(actual,expected);
	}
	//------------------------  PCI ----------------------------------------------------------------------------------

}
