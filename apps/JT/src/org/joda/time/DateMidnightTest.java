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

public class DateMidnightTest extends TestCase
{

    /*public static void main(String args[])
    {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test7: " + test7());
        System.out.println("test8: " + test8());
        System.out.println("test9: " + test9());
        System.out.println("test10: " + test10());
        System.out.println("test11: " + test11());
        System.out.println("test12: " + test12());
        System.out.println("test13: " + test13());
        System.out.println("test14: " + test14());
    }*/
	//------------------------ EAM ----------------------------------------------------------------------------------
    /* TimeDepend @Test
	public void test1(){
		DateMidnight dm = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 01, 8, 10, 10, 90));
		DateMidnight.Property p = new DateMidnight.Property(dm, ISOChronology.getInstanceUTC().dayOfMonth());
		String actual = p.withMaximumValue().toString();
        String expected = "2000-02-29T00:00:00.000-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test2(){
		DateMidnight dm = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateMidnight.Property p = new DateMidnight.Property(dm, ISOChronology.getInstanceUTC().year());
		String actual = p.withMinimumValue().toString();
        String expected = "-292275054-09-30T00:00:00.000-04:56:02";
        assertEquals(actual,expected);
	}*/
	
	//------------------------ EAM ----------------------------------------------------------------------------------

	//------------------------ IOD ----------------------------------------------------------------------------------
    @Test
	public void test3(){
		DateMidnight dm = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		
		long actual = dm.checkInstant(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
        long expected = Long.valueOf("970358400000").longValue();
        assertEquals(actual,expected);
	}
	//------------------------ IOD ----------------------------------------------------------------------------------

	//------------------------ IPC ----------------------------------------------------------------------------------
    @Test
	public void test4(){
		DateMidnight dm = new DateMidnight(BuddhistChronology.getInstanceUTC());
		int actual = dm.getYear();
        int expected = 2554;
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test5(){
		DateMidnight dm = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), DateTimeZone.getDefault());
		long actual = dm.getMillis();
        long expected = Long.valueOf("970372800000").longValue();
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		DateMidnight dm = new DateMidnight(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		DateMidnight dm1 = new DateMidnight(dm);
		long actual = dm1.getMillis();
        long expected = Long.valueOf("970286400000").longValue();
        assertEquals(actual,expected);
	}
    @Test
	public void test7(){
		DateMidnight dm = new DateMidnight(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		DateMidnight dm1 = new DateMidnight(dm, DateTimeZone.getDefault());
		long actual = dm1.getMillis();
        long expected = Long.valueOf("970286400000").longValue();
        assertEquals(actual,expected);
	}*/
    @Test
	public void test8(){
		DateMidnight dm = new DateMidnight(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		DateMidnight dm1 = new DateMidnight(dm, ISOChronology.getInstanceUTC());
		long actual = dm1.getMillis();
        long expected = Long.valueOf("970272000000").longValue();
	}
    /* TimeDepend @Test
	public void test9(){
		DateMidnight dm = new DateMidnight(2000, 10, 2);
		long actual = dm.getMillis();
        long expected = Long.valueOf("970459200000").longValue();
        assertEquals(actual,expected);
	}
    @Test
	public void test10(){
		DateMidnight dm = new DateMidnight(2000, 10, 2, DateTimeZone.getDefault());
		long actual = dm.getMillis();
        long expected = Long.valueOf("970459200000").longValue();
        assertEquals(actual,expected);
	}*/
    @Test
	public void test11(){
		DateMidnight dm = new DateMidnight(2000, 10, 2, ISOChronology.getInstanceUTC());
		long actual = dm.getMillis();
        long expected = Long.valueOf("970444800000").longValue();
        assertEquals(actual,expected);
	}
	//------------------------ IPC ----------------------------------------------------------------------------------

	//------------------------ PCI ----------------------------------------------------------------------------------
    @Test
	public void test12(){
		DateMidnight dm = new DateMidnight(2000, 10, 2, BuddhistChronology.getInstanceUTC());
		String actual = dm.property(DateTimeFieldType.monthOfYear()).toString();
        String expected = "Property[monthOfYear]";
        assertEquals(actual,expected);
	}
	//------------------------ PCI ----------------------------------------------------------------------------------
	//------------------------ JSI ----------------------------------------------------------------------------------
    @Test
	public void test13(){
		DateMidnight dt = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 8, 10, 10, 90));
		DateMidnight dt1 = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 2, 11, 18, 1, 1, 900));
		DateMidnight.Property p = new DateMidnight.Property(dt, ISOChronology.getInstanceUTC().year());
		DateMidnight.Property p1 = new DateMidnight.Property(dt1, ISOChronology.getInstanceUTC().monthOfYear());
		String actual = p.getField().toString();
        String expected = "DateTimeField[year]";
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test14(){
		DateMidnight dt = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 8, 10, 10, 90));
		DateMidnight dt1 = new DateMidnight(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 2, 11, 18, 1, 1, 900));
		DateMidnight.Property p = new DateMidnight.Property(dt, ISOChronology.getInstanceUTC().year());
		DateMidnight.Property p1 = new DateMidnight.Property(dt1, ISOChronology.getInstanceUTC().monthOfYear());
		long actual = p.getMillis();
        long expected = Long.valueOf("970372800000").longValue();
        assertEquals(actual,expected);
	}*/
	//------------------------ JSI ----------------------------------------------------------------------------------
}
