package org.joda.time.format;

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

public class DateTimeFormatterTest extends TestCase
{

    //public static void main(String[] args)
    //{
        //System.out.println("test1: " + test1());
        //System.out.println("test2: " + test2());
        //System.out.println("test3: " + test3());
        //System.out.println("test4: " + test4());
        //System.out.println("test5: " + test5());
    //}
	//------------------------ EOC ----------------------------------------------------------------------------------
	//2 EOC mutants are equivalent
/*	public DateTimeFormatter teset1(){
		Locale lc = new Locale("ja", "JP");
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
		DateTimeFormatter dtf = dtfb.toFormatter();
		return dtf.withZone(zone);
	}
	public DateTimeFormatter teset2(){
		Locale lc = new Locale("ja", "JP");
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
		DateTimeFormatter dtf = dtfb.toFormatter();
		return dtf.withPivotYear(new Integer(4));
	}*/
	//------------------------ EOC ----------------------------------------------------------------------------------

	//------------------------ OAN ----------------------------------------------------------------------------------
	/*public static long test1(){
		DateTimeFormatter dtf = ISODateTimeFormat.dateParser();
		Locale lc = new Locale("ja", "JP");
		DateTimeFormatter dtf1 = dtf.withLocale(lc);
		return dtf1.parseMillis("2000-10-03T01:00:00.000+01:00");
	}
	public static DateTime test2(){
		//DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
		//DateTimeFormatter dtf = dtfb.toFormatter();
		DateTimeFormatter dtf = ISODateTimeFormat.dateParser();
		return dtf.parseDateTime("2000-10-03T01:00:00.000+01:00");
	}
	public static MutableDateTime test3(){
		DateTimeFormatter dtf = ISODateTimeFormat.dateParser();
		return dtf.parseMutableDateTime("2000-10-03T01:00:00.000+01:00");
	}
	public static long test4(){
		DateTimeFormatter dtf = ISODateTimeFormat.dateParser();
		return dtf.parseMillis("2000-10-03T01:00:00.000+01:00;fd");
	}
	//------------------------ OAN ----------------------------------------------------------------------------------

	//------------------------ PCI ----------------------------------------------------------------------------------
	public static String test5(){
		DateTimeFormatter dtf = ISODateTimeFormat.dateParser();
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		dtf.print(mdt);
		return dtf.toString();
	}*/
    @Test
	public void test6(){
		DateTimeFormatter dtf = ISODateTimeFormat.dateParser();
		ReadWritableInstant mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		int actual = dtf.parseInto(mdt, "2000-10-03T01:00:00.000+01:00", 2);
        int expected = 10;
        assertEquals(actual,expected);
	}
	//------------------------ PCI ----------------------------------------------------------------------------------
/*	public static void main(String[] args){
		//DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();	
		DateTimeFormatter dtf = ISODateTimeFormat.dateParser();
		System.out.println(dtf);
		ReadWritableInstant mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		System.out.println(dtf.parseInto(mdt, "2000-10-03T01:00:00.000+01:00", 2));
	}*/
}

