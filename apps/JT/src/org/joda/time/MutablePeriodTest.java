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

public class MutablePeriodTest extends TestCase
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
    }*/
	//------------------------  OAN ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start, end);

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		period.setPeriod(start1, end1);
		String actual = period.toString();
        String expected = "P1Y4DT4H3M2.006S";
        assertEquals(actual,expected);
	}
	//------------------------  OAN ----------------------------------------------------------------------------------
	//------------------------  OMR ----------------------------------------------------------------------------------
    @Test
	public void test2(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start.getMillis(), end.getMillis(), ISOChronology.getInstanceUTC());

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Duration dur = new Duration(start1, end1);

		period.setPeriod(dur, BuddhistChronology.getInstanceUTC());
		String actual =  period.toString();
        String expected = "P52W5DT4H3M2.006S";
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start.getMillis(), end.getMillis(), BuddhistChronology.getInstanceUTC());

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 1, 1, 0, 0, 0, 6);
		period.setPeriod(start1.getMillis(), end1.getMillis());
		String actual = period.toString();
        String expected = "P1Y1WT0.006S";
        assertEquals(actual,expected);
	}
	//------------------------  OMR ----------------------------------------------------------------------------------
	//------------------------  EOC ----------------------------------------------------------------------------------
	//not used
/*	public String testeoc5(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start, end);

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		period.setPeriod(start1, end1);
		return period.toString();
	}*/
	//------------------------  EOC ----------------------------------------------------------------------------------

	//------------------------  EAM ----------------------------------------------------------------------------------
    @Test
	public void test4(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start, end);

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Interval interval = new Interval(start1, end1);
		period.setPeriod(interval);
		String actual = period.toString();
        String expected = "P1Y4DT4H3M2.006S";
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 2, 13, 1, 2, 3, 4);
		MutablePeriod period = new MutablePeriod(start, end);

		period.add(1,2,3,4,5,6,7,54);
		String actual = period.toString();
        String expected = "P2Y3M5W9DT6H8M10.058S";
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start, end);

		period.add(12,23,34,45,56,67,78,54);
		String actual = period.toString();
        String expected = "P13Y23M35W45DT56H67M78.054S";
        assertEquals(actual,expected);
	}
    @Test
	public void test7(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 2, 13, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start, end);

		period.add(1,2,3,4,5,6,7,54);
		String actual = period.toString();
        String expected = "P2Y3M5W9DT5H6M7.054S";
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------
/*	public static void main(String[] args){
		DateTime start = new DateTime(2004, 2, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 3, 26, 1, 2, 3, 4);
		MutablePeriod period = new MutablePeriod(start.getMillis(), end.getMillis(), PeriodType.days(), ISOChronology.getInstanceUTC());
		MutablePeriod period1 = new MutablePeriod(start.getMillis(), end.getMillis(), EthiopicChronology.getInstanceUTC());
		System.out.println(period.toString());
		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		period.setPeriod(start1, end1);
		System.out.println(period.toString());

	}*/
}
