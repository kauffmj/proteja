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

public class IntervalTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test7: " + test7());
    }*/
	//------------------------  EAM ----------------------------------------------------------------------------------
/*	public String test1(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2004, 2, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Interval interval1 = new Interval(start1, end1);

		return interval1.overlap(interval).toString();
	}
	public String test2(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2006, 2, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2007, 12, 29, 4, 3, 2, 6);
		Interval interval1 = new Interval(start1, end1);

		return interval1.gap(interval).toString();
	}
	public String test3(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2006, 2, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2007, 12, 29, 4, 3, 2, 6);
		Interval interval1 = new Interval(start1, end1);

		return interval.gap(interval1).toString();
	}
	public boolean test4(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		DateTime end1 = new DateTime(2007, 12, 29, 4, 3, 2, 6);
		Interval interval1 = new Interval(start1, end1);

		return interval.abuts(interval1);
	}
	public boolean test5(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 12, 29, 4, 3, 2, 6);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2003, 12, 29, 4, 3, 2, 6);
		DateTime end1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		Interval interval1 = new Interval(start1, end1);

		return interval.abuts(interval1);
	}
	public boolean test6(){
		DateTime start = new DateTime(DateTimeUtils.currentTimeMillis());
		DateTime end = new DateTime(2010, 12, 29, 4, 3, 2, 6);
		Interval interval = new Interval(start, end);
		return interval.abuts(null);
	}*/
    @Test
	public void test7(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(DateTimeUtils.currentTimeMillis());
		Interval interval = new Interval(start, end);
		boolean actual = interval.abuts(null);
        boolean expected = true;
        assertEquals(actual,expected);
	}
/*	public String test8(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2019, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);
		return interval.withChronology(BuddhistChronology.getInstanceUTC()).toString();
	}
	public String test9(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);
		return interval.withStartMillis(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 1, 1, 1, 1)).toString();
	}
	public String test10(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);
		return interval.withEndMillis(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 1, 1, 1, 1, 1)).toString();
	}
	public String test11(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);
		return interval.withStartMillis(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 1, 1, 1, 1, 1, 1)).toString();
	}
	public String test12(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);
		return interval.withEndMillis(ISOChronology.getInstanceUTC().getDateTimeMillis(2009, 1, 1, 1, 1, 1, 1)).toString();
	}
	public String test13(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 12, 29, 4, 3, 2, 6);
		Duration dur = new Duration(start1, end1);
		return interval.withDurationAfterStart(dur).toString();
	}
	public String test14(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 12, 29, 4, 3, 2, 6);
		Duration dur = new Duration(start1, end1);
		return interval.withDurationBeforeEnd(dur).toString();
	}
	public String test15(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start1.getMillis(), end1.getMillis(), ISOChronology.getInstanceUTC());
		
		return interval.withPeriodAfterStart(period).toString();
	}
	public String test16(){
		DateTime start = new DateTime(2009, 1, 1, 1, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		Interval interval = new Interval(start, end);

		DateTime start1 = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end1 = new DateTime(2006, 1, 1, 0, 0, 0, 0);
		MutablePeriod period = new MutablePeriod(start1.getMillis(), end1.getMillis(), ISOChronology.getInstanceUTC());
		
		return interval.withPeriodBeforeEnd(period).toString();
	}
//------------------------  EAM ----------------------------------------------------------------------------------
/*	public static void main(String[] args){
		DateTime start = new DateTime(2009, 1, 1, 5, 1, 1, 1);
		DateTime end = new DateTime(2010, 1, 1, 1, 1, 1, 1);
		DateTimeZone zone = DateTimeZone.forID("+05:00");
		Interval interval = new Interval(start.getMillis(), end.getMillis(), zone);
		System.out.println(interval.toString());
	}*/
}
