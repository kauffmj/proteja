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

public class DateTimeTest extends TestCase
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
        System.out.println("test16: " + test16());
        System.out.println("test17: " + test17());
        System.out.println("test18: " + test18());
        System.out.println("test19: " + test19());
    }*/
	//------------------------ EAM ----------------------------------------------------------------------------------
    /* TimeDepend @Test
	public void test1(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 2, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().dayOfMonth());
		String actual = p.withMaximumValue().toString();
        String expected = "2000-02-29T03:10:10.090-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test2(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		String actual = p.withMinimumValue().toString();
        String expected = "-292275054-10-01T03:14:08.090-04:56:02";
        assertEquals(actual,expected);
	}*/
	//------------------------ EAM ----------------------------------------------------------------------------------

	//------------------------ IPC ----------------------------------------------------------------------------------
    /*@Test
	public void test3(){
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		DateTime dt = new DateTime(zone);
		int actual = dt.getHourOfDay();
        int expected = 18;
        assertEquals(actual,expected);
	}*/
    @Test
	public void test4(){
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC());
		int actual = dt.getYear();
        int expected = 2554;
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), DateTimeZone.getDefault());
		long actual = dt.getMillis();
        long expected = Long.valueOf("970457696789").longValue();
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		DateTime dt1 = new DateTime(dt);
		long actual = dt1.getMillis();
        long expected = Long.valueOf("970358400001").longValue();
        assertEquals(actual,expected);
	}
    @Test
	public void test7(){
		DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		DateTime dt1 = new DateTime(dt, DateTimeZone.getDefault());
		long actual = dt1.getMillis();
        long expected = Long.valueOf("970358400001").longValue();
        assertEquals(actual,expected);
	}
    @Test
	public void test8(){
		DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		DateTime dt1 = new DateTime(dt, ISOChronology.getInstanceUTC());
		long actual = dt1.getMillis();
        long expected = Long.valueOf("970358400001").longValue();
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test9(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789);
		long actual = dt.getMillis();
        long expected = Long.valueOf("970472096789").longValue();
        assertEquals(actual,expected);
	}
    @Test
	public void test10(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, DateTimeZone.getDefault());
		long actual = dt.getMillis();
        long expected = Long.valueOf("970472096789").longValue();
        assertEquals(actual,expected);
	}*/
    @Test
	public void test11(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, ISOChronology.getInstanceUTC());
		long actual = dt.getMillis();
        long expected = Long.valueOf("970457696789").longValue();
        assertEquals(actual,expected);
	}
	//------------------------ IPC ----------------------------------------------------------------------------------
	//------------------------ PCI ----------------------------------------------------------------------------------
    @Test
	public void test12(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, ISOChronology.getInstanceUTC());
		String actual = dt.withDate(2001, 2, 4).toString();
        String expected = "2001-02-04T03:34:56.789Z";
        assertEquals(actual,expected);
	}
    @Test
	public void test13(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, BuddhistChronology.getInstanceUTC());
		String actual = dt.withDate(2001, 2, 4).toString();
        String expected = "2001-02-04T03:34:56.789Z";
        assertEquals(actual,expected);
	}
    @Test
	public void test14(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, ISOChronology.getInstanceUTC());
		String actual = dt.withTime(15, 25, 44, 789).toString();
        String expected = "2000-10-02T15:25:44.789Z";
        assertEquals(actual,expected);
	}
    @Test
	public void test15(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, BuddhistChronology.getInstanceUTC());
		String actual = dt.withTime(15, 25, 44, 789).toString();
        String expected = "2000-10-02T15:25:44.789Z";
        assertEquals(actual,expected);
	}
    @Test
	public void test16(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, BuddhistChronology.getInstanceUTC());
		String actual = dt.property(DateTimeFieldType.monthOfYear()).toString();
        String expected = "Property[monthOfYear]";
        assertEquals(actual,expected);
	}
	/* not used
	public String test19(){
		DateTime dt = new DateTime(2000, 10, 02, 3, 34, 56, 789, BuddhistChronology.getInstanceUTC());
		return dt.property(DateTimeFieldType.hourOfDay()).toString();
	}
	public long test20(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.getMillis();
	}
	public String test21(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.getChronology().toString();
	}
	public String test22(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		long d = 22L;
		return p.addToCopy(d).toString();
	}
	public String test23(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.addWrapFieldToCopy(3).toString();
	}
	public String test24(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.setCopy(3).toString();
	}
	public String test25(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.setCopy("2000-3-2", Locale.US).toString();
	}
	public String test26(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.roundFloorCopy().toString();
	}
	public String test27(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.roundCeilingCopy().toString();
	}
	public String test28(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.roundHalfFloorCopy().toString();
	}
	public String test29(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.roundHalfCeilingCopy().toString();
	}
	public String test30(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		return p.roundHalfEvenCopy().toString();
	}*/
	//------------------------ PCI ----------------------------------------------------------------------------------
	//------------------------ JSI ----------------------------------------------------------------------------------
    @Test
	public void test17(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 8, 10, 10, 90));
		DateTime dt1 = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 2, 11, 18, 1, 1, 900));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		DateTime.Property p1 = new DateTime.Property(dt1, ISOChronology.getInstanceUTC().monthOfYear());
		String actual =  p.getField().toString();
        String expected = "DateTimeField[year]";
        assertEquals(actual,expected);
	}
    @Test
	public void test18(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 8, 10, 10, 90));
		DateTime dt1 = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 2, 11, 18, 1, 1, 900));
		DateTime.Property p = new DateTime.Property(dt, ISOChronology.getInstanceUTC().year());
		DateTime.Property p1 = new DateTime.Property(dt1, ISOChronology.getInstanceUTC().monthOfYear());
		long actual = p.getMillis();
        long expected = Long.valueOf("970387810090").longValue();
        assertEquals(actual,expected);
	}
	//------------------------ JSI ----------------------------------------------------------------------------------
	//------------------------ OMR ----------------------------------------------------------------------------------
    @Test
	public void test19(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 8, 10, 10, 90));
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		String actual =  dt.toDateTime(zone).toString();
        String expected = "2000-10-01T09:10:10.090+01:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test20(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 8, 10, 10, 90));
		String actual = dt.toDateTime(BuddhistChronology.getInstanceUTC()).toString();
        String expected = "2543-10-01T08:10:10.090Z";
        assertEquals(actual,expected);
	}
	//------------------------ OMR ----------------------------------------------------------------------------------
}
