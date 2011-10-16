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

public class PartialTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        //System.out.println("test2: " + test2());
        //System.out.println("test3: " + test3());
        //System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
        //System.out.println("test6: " + test6());
        //System.out.println("test7: " + test7());
        System.out.println("test8: " + test8());
        System.out.println("test9: " + test9());
        System.out.println("test10: " + test10());
        System.out.println("test11: " + test11());
        System.out.println("test12: " + test12());
        System.out.println("test13: " + test13());
        System.out.println("test14: " + test14());
        System.out.println("test15: " + test15());
        System.out.println("test16: " + test16());
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String actual = partial.toString();
        String expected = "2000-04-15T13:34:44.234";
        assertEquals(actual,expected);
	}
    /* TODO: Make this work.
@Test(expected=java.lang.IllegalArgumentException.class)
	public void test2(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 10, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		partial.toString();
	}
    @Test(expected=java.lang.IllegalArgumentException.class)
	public void test3(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 10, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		partial.toString();
	}
    @Test(expected=java.lang.IllegalArgumentException.class)
	public void test4(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.yearOfCentury(), DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 10, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		partial.toString();
	}*/
    @Test
	public void test5(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String actual = partial.with(DateTimeFieldType.year(), 2000).toString();
        String expected = "2000-04-15T13:34:44.234";
        assertEquals(actual,expected);
	}
    /* TODO: Make this work.
@Test(expected=java.lang.NullPointerException.class)
	public void test6(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		partial.with(DateTimeFieldType.yearOfCentury(), 455).toString();
	}
    @Test(expected=java.lang.NullPointerException.class)
	public void test7(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.yearOfCentury(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {20, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		partial.with(DateTimeFieldType.year(), 455).toString();
	}*/
    @Test
	public void test8(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 2, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		Partial.Property p = new Partial.Property(partial, 2);
		String actual = p.withMaximumValue().toString();
        String expected = "2000-02-29T13:34:44.234";
        assertEquals(actual,expected);
	}
    @Test
	public void test9(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 2, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		Partial.Property p = new Partial.Property(partial, 0);
		String actual = p.withMinimumValue().toString();
        String expected = "-292275054-02-15T13:34:44.234";
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------

	//------------------------  JSI ----------------------------------------------------------------------------------
    @Test
	public void test10(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		Partial partial1 = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String s = partial1.with(DateTimeFieldType.year(), 2000).toString();
		String actual = partial.with(DateTimeFieldType.dayOfMonth(), 5).toString();
        String expected = "--04-05T13:34:44.234";
	}
	//------------------------  JSI ----------------------------------------------------------------------------------

	//------------------------  OMR ----------------------------------------------------------------------------------
    @Test
	public void test11(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String actual = partial.toString("11");
        String expected = "11";
        assertEquals(actual,expected);
	}
    @Test
	public void test12(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		Locale lc = new Locale("ja", "JP");
		String actual = partial.toString("11", lc);
        String expected = "11";
	}
	//------------------------  OMR ----------------------------------------------------------------------------------
	//------------------------  PCI ----------------------------------------------------------------------------------
    @Test
	public void test13(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String actual = partial.withChronologyRetainFields(ISOChronology.getInstanceUTC()).toString();
        String expected = "2000-04-15T13:34:44.234";
        assertEquals(actual,expected);
	}
    @Test
	public void test14(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String actual = partial.withChronologyRetainFields(BuddhistChronology.getInstanceUTC()).toString();
        String expected = "2000-04-15T13:34:44.234";
        assertEquals(actual,expected);
	}
    @Test
	public void test15(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.yearOfEra(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String actual = partial.with(DateTimeFieldType.yearOfCentury(), 20).toString();
        String expected = "[yearOfCentury=20, yearOfEra=2000, monthOfYear=4, dayOfMonth=15, hourOfDay=13, minuteOfHour=34, secondOfMinute=44, millisOfSecond=234]";
        assertEquals(actual,expected);
	}
    @Test
	public void test16(){
		DateTimeFieldType [] dtft = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		String actual = partial.with(DateTimeFieldType.dayOfYear(), 20).toString();
        String expected = "[year=2000, monthOfYear=4, dayOfYear=20, dayOfMonth=15, hourOfDay=13, minuteOfHour=34, secondOfMinute=44, millisOfSecond=234]";
        assertEquals(actual,expected);
	}
	//------------------------  PCI ----------------------------------------------------------------------------------
/*	public static void main(String[] args){
		DateTimeFieldType [] dtft = {DateTimeFieldType.yearOfEra(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth(), DateTimeFieldType.hourOfDay(), DateTimeFieldType.minuteOfHour(), DateTimeFieldType.secondOfMinute(), DateTimeFieldType.millisOfSecond()};
		int [] values = {2000, 4, 15, 13, 34, 44, 234};
		Partial partial = new Partial(dtft, values, ISOChronology.getInstanceUTC());
		System.out.println(partial.with(DateTimeFieldType.yearOfCentury(), 20).toString());
	}*/
}
