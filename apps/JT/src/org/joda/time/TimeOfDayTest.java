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

public class TimeOfDayTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
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
        System.out.println("test16: " + test16());
        System.out.println("test17: " + test17());
        System.out.println("test18: " + test18());
        System.out.println("test19: " + test19());
        System.out.println("test20: " + test20());
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.toString();
        String expected = "T06:10:08.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test2(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.toLocalTime().toString();
        String expected = "06:10:08.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		TimeOfDay.Property p = new TimeOfDay.Property(tof, 0);
		String actual = p.withMaximumValue().toString();
        String expected = "T23:10:08.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		TimeOfDay.Property p = new TimeOfDay.Property(tof, 0);
		String actual = p.withMinimumValue().toString();
        String expected = "T00:10:08.000";
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------
	//------------------------  IPC ----------------------------------------------------------------------------------
    /*@Test
	public void test5(){
		DateTimeZone zone = DateTimeZone.forID("-05:00");
		TimeOfDay tof = new TimeOfDay(zone);
		int actual = tof.getHourOfDay();
        int expected = 14;
        assertEquals(actual,expected);
	}*/
    /* ProtectError @Test
	public void test6(){
		TimeOfDay tof = new TimeOfDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 02, 3, 34, 56, 789), ISOChronology.getInstanceUTC());
		String actual  = tof.toString();
        String expected = "T03:34:56.789";
        assertEquals(actual,expected);
	}*/
    /* TimeDepend @Test
	public void test7(){
		TimeOfDay tof = new TimeOfDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 02, 3, 34, 56, 789));
		String actual = tof.toString();
        String expected = "T22:34:56.789";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test8(){
		DateTime start = new DateTime(2004, 12, 25, 4, 4, 5, 6);
		TimeOfDay tof = new TimeOfDay(start);
		String actual = tof.toString();
        String expected = "T04:04:05.006";
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test9(){
		DateTime start = new DateTime(2004, 12, 25, 4, 5, 6, 7);
		TimeOfDay tof = new TimeOfDay(start, ISOChronology.getInstanceUTC());
		String actual = tof.toString();
        String expected = "T09:05:06.007";
        assertEquals(actual,expected);
	}*/
    /* ProtectError @Test
	public void test10(){
		TimeOfDay tof = new TimeOfDay(4,5,6,7, ISOChronology.getInstanceUTC());
		int[] values = {5,22,45,567};
		TimeOfDay tof1 = new TimeOfDay(tof, ISOChronology.getInstanceUTC());
		String actual = tof1.toString();
        String expected = "T04:05:06.007";
        assertEquals(actual,expected);
	}*/

	//------------------------  IPC ----------------------------------------------------------------------------------
	//------------------------  OMR ----------------------------------------------------------------------------------
    @Test
	public void test11(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.fromMillisOfDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 02, 23, 34, 56, 789),ISOChronology.getInstanceUTC()).toString();
        String expected = "T23:34:56.789";
        assertEquals(actual,expected);
	}
    @Test
	public void test12(){
		DateTimeZone zone = DateTimeZone.forID("-05:00");
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		int actual = tof.toDateTimeToday(zone).getHourOfDay();
        int expected = 6;
        assertEquals(actual,expected);
	}
	//------------------------  OMR ----------------------------------------------------------------------------------
	//------------------------  PCI ----------------------------------------------------------------------------------
    @Test
	public void test13(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.fromMillisOfDay(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 02, 23, 34, 56, 789),BuddhistChronology.getInstanceUTC()).toString();
        String expected = "T23:34:56.789";
        assertEquals(actual,expected);
	}
    /* ProtectError @Test
	public void test14(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.getField(0, BuddhistChronology.getInstanceUTC()).toString();
        String expected = "DateTimeField[hourOfDay]";
        assertEquals(actual,expected);
	}
    @Test
	public void test15(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.getField(1, BuddhistChronology.getInstanceUTC()).toString();
        String expected = "DateTimeField[minuteOfHour]";
        assertEquals(actual,expected);
	}
    @Test
	public void test16(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.getField(2, BuddhistChronology.getInstanceUTC()).toString();
        String expected = "DateTimeField[secondOfMinute]";
        assertEquals(actual,expected);
	}
    @Test
	public void test17(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.getField(3, BuddhistChronology.getInstanceUTC()).toString();
        String expected = "DateTimeField[millisOfSecond]";
        assertEquals(actual,expected);
	}
    @Test
	public void test18(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.withChronologyRetainFields(ISOChronology.getInstanceUTC()).toString();
        String expected = "T06:11:08.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test19(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.withChronologyRetainFields(BuddhistChronology.getInstanceUTC()).toString();
        String expected = "T06:11:08.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test20(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		String actual = tof.MIDNIGHT.withMillisOfSecond(343).toString();
        String expected = "T00:00:00.343";
        assertEquals(actual,expected);
	}*/
	//Designed for PCD, NOT USED
/*	public static String test20(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 11, 8);
		TimeOfDay tof = TimeOfDay.fromDateFields(d1);
		DateTimeFieldType[] dtft = tof.getFieldTypes();
		static String s = "";
		for(int i = 0; i < dtft.length; i++){
			s = s + dtft[i].toString();
		}
		return s;
	}*/
	//------------------------  PCI ----------------------------------------------------------------------------------
}
