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

public class YearMonthDayTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
        System.out.println("test6: " + test6());
        System.out.println("test7: " + test7());
        System.out.println("test8: " + test8());
        System.out.println("test9: " + test9());
        System.out.println("test10: " + test10());
        System.out.println("test11: " + test11());
        System.out.println("test12: " + test12());
        System.out.println("test13: " + test13());
        System.out.println("test14: " + test14());
        System.out.println("test15: " + test15());
        System.out.println("test17: " + test17());
        System.out.println("test18: " + test18());
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		YearMonthDay ymd = new YearMonthDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
		String actual = ymd.fromDateFields(d1).toString();
        String expected = "3890-03-20";
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test2(){
		YearMonthDay ymd = new YearMonthDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		String actual = ymd.toLocalDate().toString();
        String expected = "2000-10-01";
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		YearMonthDay ymd = new YearMonthDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		String actual = ymd.toDateTimeAtMidnight(zone).toString();
        String expected = "2000-10-01T00:00:00.000+01:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		YearMonthDay ymd = new YearMonthDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		String actual = ymd.toDateMidnight(zone).toString();
        String expected = "2000-10-01T00:00:00.000+01:00";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test5(){
		YearMonthDay ymd = new YearMonthDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 02, 3, 34, 56, 789));
		YearMonthDay.Property ymdp = new YearMonthDay.Property(ymd, 2);
		String actual = ymdp.withMaximumValue().toString();
        String expected = "2000-02-29";
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		YearMonthDay ymd = new YearMonthDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		YearMonthDay.Property ymdp = new YearMonthDay.Property(ymd, 2);
		String actual = ymdp.withMinimumValue().toString();
        String expected = "2000-10-01";
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------

	//------------------------  IPC ----------------------------------------------------------------------------------
    @Test	
    public void test7(){
		YearMonthDay ymd = new YearMonthDay(BuddhistChronology.getInstanceUTC());
		int actual = ymd.getYear();
        int expected = 2554;
        assertEquals(actual,expected);
	}
    @Test
	public void test8(){
		YearMonthDay ymd = new YearMonthDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 02, 3, 34, 56, 789), ISOChronology.getInstanceUTC());
		String actual = ymd.toString();
        String expected = "2000-02-02";
        assertEquals(actual,expected);
	}
    @Test
	public void test9(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		YearMonthDay ymd = new YearMonthDay(start);
		String actual = ymd.toString();
        String expected = "2004-12-25";
        assertEquals(actual,expected);
	}
    @Test
	public void test10(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		YearMonthDay ymd = new YearMonthDay(start, ISOChronology.getInstanceUTC());
		String actual = ymd.toString();
        String expected = "2004-12-25";
        assertEquals(actual,expected);
	}
    @Test
	public void test11(){
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, ISOChronology.getInstanceUTC());
		YearMonthDay ymd1 = new YearMonthDay(ymd, ISOChronology.getInstanceUTC());
		String actual = ymd1.toString();
        String expected = "2004-12-25";
        assertEquals(actual,expected);
	}
	//------------------------  IPC ----------------------------------------------------------------------------------
	//------------------------  OMR ----------------------------------------------------------------------------------
    @Test	
    public void test12(){
		DateTimeZone zone = DateTimeZone.forID("-05:00");
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, ISOChronology.getInstanceUTC());
		int actual = ymd.toDateTimeAtCurrentTime(zone).getDayOfMonth();
        int expected = 25;
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test13(){
		DateTimeZone zone = DateTimeZone.forID("-05:00");
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, ISOChronology.getInstanceUTC());
		java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
		TimeOfDay tod = new TimeOfDay(d1);
		String actual = ymd.toDateTime(tod, zone).toString();
        String expected = "2004-12-25T06:10:08.000-05:00";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test14(){
		DateTimeZone zone = DateTimeZone.forID("-05:00");
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, ISOChronology.getInstanceUTC());
		String actual = ymd.toInterval(zone).toString();
        String expected = "2004-12-25T00:00:00.000/2004-12-26T00:00:00.000";
        assertEquals(actual,expected);
	}
	//------------------------  OMR ----------------------------------------------------------------------------------
	//------------------------  PCI ----------------------------------------------------------------------------------
    @Test
	public void test15(){
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, ISOChronology.getInstanceUTC());
		String actual = ymd.getField(2, BuddhistChronology.getInstanceUTC()).toString();
        String expected = "DateTimeField[dayOfMonth]";
        assertEquals(actual,expected);
	}
     @Test
	public void test16(){
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, BuddhistChronology.getInstanceUTC());
		String actual = ymd.getField(2, ISOChronology.getInstanceUTC()).toString();
        String expected = "DateTimeField[dayOfMonth]";
        assertEquals(actual,expected);
	}
    @Test
	public void test17(){
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, BuddhistChronology.getInstanceUTC());
		String actual = ymd.withChronologyRetainFields(ISOChronology.getInstanceUTC()).toString();
        String expected = "2004-12-25";
        assertEquals(actual,expected);
	}
    @Test
	public void test18(){
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, ISOChronology.getInstanceUTC());
		String actual = ymd.withChronologyRetainFields(BuddhistChronology.getInstanceUTC()).toString();
        String expected = "2004-12-25";
        assertEquals(actual,expected);
	}
	//not used
/*	public static String test33(){
		YearMonthDay ymd = new YearMonthDay(2004, 12, 25, ISOChronology.getInstanceUTC());
		DateTimeFieldType[] dtft = ymd.getFieldTypes();
		static String s ="";
		for(int i = 0; i < dtft.length; i++){
			s = s + dtft[i];
		}
		return s;
	}*/
	//------------------------  PCI ----------------------------------------------------------------------------------
	/*public static void main(static String[] args){
		//DateTimeZone zone = DateTimeZone.forID("-05:00");
		YearMonthDay ymd = new YearMonthDay(BuddhistChronology.getInstanceUTC());
		System.out.println(ymd.getYear());

	}*/
}
