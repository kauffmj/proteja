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

public class MutableIntervalTest extends TestCase
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
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		
		DateTime start1 = new DateTime(2005, 2, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 5, 29, 4, 3, 2, 6);
		MutableInterval interva1l = new MutableInterval(start1, end1);

		interval.setInterval(interval);
		String actual = interval.toString();
        String expected = "2004-12-25T00:00:00.000/2005-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test2(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);

		interval.setChronology(BuddhistChronology.getInstanceUTC());
		String actual = interval.toString();
        String expected = "2547-12-25T05:00:00.000/2548-12-29T09:03:02.006";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test3(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime start1 = new DateTime(204, 12, 25, 0, 0, 0, 0);
		interval.setStartMillis(start1.getMillis());
		String actual = interval.toString();
        String expected = "0204-12-25T00:00:00.000/2005-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime start1 = new DateTime(204, 12, 25, 0, 0, 0, 0);
		interval.setStart(start1);
		String actual = interval.toString();
        String expected = "0204-12-25T00:00:00.000/2005-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime end1 = new DateTime(2007, 12, 25, 0, 0, 0, 0);
		interval.setEndMillis(end1.getMillis());
		String actual = interval.toString();
        String expected = "2004-12-25T00:00:00.000/2007-12-25T00:00:00.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime end1 = new DateTime(2007, 12, 25, 0, 0, 0, 0);
		interval.setEnd(end1);
		String actual = interval.toString();
        String expected = "2004-12-25T00:00:00.000/2007-12-25T00:00:00.000";
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test7(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime end1 = new DateTime(2007, 12, 25, 0, 0, 0, 0);
		interval.setDurationAfterStart(end1.getMillis());
		String actual = interval.toString();
        String expected = "2004-12-25T00:00:00.000/2042-12-18T05:00:00.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test8(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime end1 = new DateTime(2005, 12, 25, 0, 0, 0, 0);
		interval.setDurationBeforeEnd(end1.getMillis());
		String actual = interval.toString();
        String expected = "1970-01-04T23:03:02.006/2005-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test9(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 12, 29, 4, 3, 2, 6);
		Duration dur = new Duration(start1, end1);
		interval.setDurationAfterStart(dur);
		String actual = interval.toString();
        String expected = "2004-12-25T00:00:00.000/2006-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}
    @Test
	public void test10(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 12, 29, 4, 3, 2, 6);
		Duration dur = new Duration(start1, end1);
		interval.setDurationBeforeEnd(dur);
		String actual = interval.toString();
        String expected = "2003-12-26T00:00:00.000/2005-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}
    @Test
	public void test11(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start1.getMillis(), end1.getMillis(), ISOChronology.getInstanceUTC());
		interval.setPeriodAfterStart(period);
		String actual = interval.toString();
        String expected = "2004-12-25T00:00:00.000/2006-01-01T00:00:00.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test12(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start1.getMillis(), end1.getMillis(), ISOChronology.getInstanceUTC());
		interval.setPeriodBeforeEnd(period);
		String actual = interval.toString();
        String expected = "2004-12-22T04:03:02.006/2005-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}
    @Test
	public void test13(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		interval.setPeriodAfterStart(null);
		String actual =  interval.toString();
        String expected = "2004-12-25T00:00:00.000/2004-12-25T00:00:00.000";
        assertEquals(actual,expected);
	}
    @Test
	public void test14(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		MutableInterval interval = new MutableInterval(start, end);
		interval.setPeriodBeforeEnd(null);
		String actual =  interval.toString();
        String expected = "2005-12-29T04:03:02.006/2005-12-29T04:03:02.006";
        assertEquals(actual,expected);
	}
	//------------------------  EAM ----------------------------------------------------------------------------------
}
