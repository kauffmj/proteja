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

public class MonthsTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
    @Test	
    public void test1(){
		DateTime start = new DateTime(2004, 1, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 3, 1, 3, 4, 5, 6);
		Interval interval = new Interval(start, end);
		String actual = Months.monthsIn(interval).toString();
        String expected = "P13M";
        assertEquals(actual,expected);
	}
    @Test
	public void test2(){
		String actual = Months.parseMonths("P3M").toString();
        String expected = "P3M";
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		Months months = Months.months(3);
		int actual = months.getMonths();
        int expected = 3;
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------
	
	//------------------------  PCI ----------------------------------------------------------------------------------
    @Test
	public void test4(){
		Months months = Months.months(3);
		LocalDate start = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), ISOChronology.getInstanceUTC());
		LocalDate end = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), ISOChronology.getInstanceUTC());
		String actual = months.monthsBetween(start, end).toString();
        String expected = "P12M";
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		Months months = Months.months(3);
		LocalDate start = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		LocalDate end = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), BuddhistChronology.getInstanceUTC());
		String actual =  months.monthsBetween(start, end).toString();
        String expected = "P12M";
        assertEquals(actual,expected);
	}
	//------------------------  PCI ----------------------------------------------------------------------------------*/
}
