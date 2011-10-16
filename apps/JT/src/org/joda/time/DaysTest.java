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

public class DaysTest extends TestCase
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
		DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);
		Interval interval = new Interval(start, end);
		String actual = Days.daysIn(interval).toString();
        String expected = "P7D";
        assertEquals(actual,expected);
	}
    @Test
	public void test2(){
		String actual = Days.parseDays("P3D").toString();
        String expected = "P3D";
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		Days days = Days.days(5);
		int actual = days.getDays();
        int expected = 5;
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------

	//------------------------  PCI ----------------------------------------------------------------------------------
    @Test
	public void test4(){
		Days days = Days.days(5);
		LocalDate start = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), ISOChronology.getInstanceUTC());
		LocalDate end = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), ISOChronology.getInstanceUTC());
		String actual =  days.daysBetween(start, end).toString();
        String expected = "P365D";
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		Days days = Days.days(5);
		LocalDate start = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		LocalDate end = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		String actual = days.daysBetween(start, end).toString();
        String expected = "P365D";
        assertEquals(actual,expected);
	}
	//------------------------  PCI ----------------------------------------------------------------------------------



}
