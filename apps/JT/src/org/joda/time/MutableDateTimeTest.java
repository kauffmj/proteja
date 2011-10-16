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

public class MutableDateTimeTest extends TestCase
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
        System.out.println("test20: " + test20());
        System.out.println("test21: " + test21());
        System.out.println("test22: " + test22());
        System.out.println("test23: " + test23());
        System.out.println("test24: " + test24());
        System.out.println("test25: " + test25());
        System.out.println("test26: " + test26());
        System.out.println("test27: " + test27());
        System.out.println("test28: " + test28());
        System.out.println("test29: " + test29());
        System.out.println("test30: " + test30());
        System.out.println("test31: " + test31());
        System.out.println("test32: " + test32());
        System.out.println("test33: " + test33());
        System.out.println("test34: " + test34());
        System.out.println("test35: " + test35());
        System.out.println("test36: " + test36());
        System.out.println("test37: " + test37());
        System.out.println("test38: " + test38());
        System.out.println("test39: " + test39());
        System.out.println("test40: " + test40());
        System.out.println("test41: " + test41());
        System.out.println("test42: " + test42());
        System.out.println("test43: " + test43());
        System.out.println("test44: " + test44());
        System.out.println("test45: " + test45());
        System.out.println("test46: " + test46());
        System.out.println("test47: " + test47());
        System.out.println("test48: " + test48());
        System.out.println("test49: " + test49());
        System.out.println("test50: " + test50());
        System.out.println("test51: " + test51());
        System.out.println("test52: " + test52());
        System.out.println("test53: " + test53());
        System.out.println("test54: " + test54());
        System.out.println("test55: " + test55());
        System.out.println("test56: " + test56());
        System.out.println("test57: " + test57());
        System.out.println("test58: " + test58());
        System.out.println("test59: " + test59());
        System.out.println("test60: " + test60());
        System.out.println("test61: " + test61());
        System.out.println("test62: " + test62());
        System.out.println("test63: " + test63());
        System.out.println("test64: " + test64());
        System.out.println("test65: " + test65());
        System.out.println("test66: " + test66());
        System.out.println("test67: " + test67());
        System.out.println("test68: " + test68());
        System.out.println("test69: " + test69());
        System.out.println("test70: " + test70());
        System.out.println("test72: " + test72());
        System.out.println("test73: " + test73());
        System.out.println("test74: " + test74());
        System.out.println("test75: " + test75());
        System.out.println("test76: " + test76());
        System.out.println("test77: " + test77());
        System.out.println("test78: " + test78());
        System.out.println("test79: " + test79());
        System.out.println("test80: " + test80());
        System.out.println("test81: " + test81());
        System.out.println("test82: " + test82());
        System.out.println("test83: " + test83());
        System.out.println("test84: " + test84());
        System.out.println("test85: " + test85());
        System.out.println("test86: " + test86());
        System.out.println("test87: " + test87());
        System.out.println("test88: " + test88());
        System.out.println("test89: " + test89());
        System.out.println("test90: " + test90());
        System.out.println("test91: " + test91());
        System.out.println("test92: " + test92());
        System.out.println("test93: " + test93());
        System.out.println("test94: " + test94());
        System.out.println("test95: " + test95());
        System.out.println("test96: " + test96());
        System.out.println("test97: " + test97());
        System.out.println("test98: " + test98());
        System.out.println("test99: " + test99());
        System.out.println("test100: " + test100());
        System.out.println("test101: " + test101());
        System.out.println("test102: " + test102());
        System.out.println("test103: " + test103());
        System.out.println("test104: " + test104());
        System.out.println("test105: " + test105());
        System.out.println("test106: " + test106());
        System.out.println("test107: " + test107());
        System.out.println("test108: " + test108());
        System.out.println("test109: " + test109());
        System.out.println("test110: " + test110());
        System.out.println("test111: " + test111());
        System.out.println("test112: " + test112());
        System.out.println("test113: " + test113());
        System.out.println("test114: " + test114());
    }*/
	//------------------------ EAM ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		mdt.setDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970387810090");
        assertEquals(actual,expected);
	}
	//------------------------ EAM ----------------------------------------------------------------------------------
	//------------------------ IOP ----------------------------------------------------------------------------------
    @Test
	public void test2(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 23, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().secondOfMinute(), 1);
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970443296000");
        assertEquals(actual,expected);
	}
	//------------------------ IOP ----------------------------------------------------------------------------------

	//------------------------ EMM ----------------------------------------------------------------------------------
	//For EMM1 and 2, seems like equivalent mutants
/*	public long testEMM2(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 23, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().secondOfMinute(), 1);
		return mdt.getMillis();
	}
	public String testEMM2(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(ISOChronology.getInstanceUTC().year(), 2);
		return mdt.toString();
	}*/
	/* TimeDepend @Test
	public void test3(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 23, 34, 56, 789));
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		mdt.setZoneRetainFields(zone);
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970425296789");
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		mdt.set(DateTimeFieldType.millisOfDay(), 6);
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970372800006");
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.set(DateTimeFieldType.dayOfMonth(), 3);
		String actual = mdt.toString();
        String expected = "2000-10-03T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.add(DurationFieldType.seconds(), 3);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:34:59.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test7(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.add(DurationFieldType.days(), 3);
		String actual = mdt.toString();
        String expected = "2000-10-04T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test8(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setYear(3);
		String actual = mdt.toString();
        String expected = "0003-10-01T23:34:56.789-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test9(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addYears(3);
		String actual =  mdt.toString();
        String expected = "2003-10-01T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test10(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setWeekyear(3);
		String actual = mdt.toString();
        String expected = "0003-09-28T23:34:56.789-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test11(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setMonthOfYear(3);
		String actual = mdt.toString();
        String expected = "2000-03-01T23:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test12(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addWeekyears(3);
		String actual = mdt.toString();
        String expected = "2003-09-28T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test13(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addMonths(3);
		String actual = mdt.toString();
        String expected = "2001-01-01T23:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test14(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setWeekOfWeekyear(3);
		String actual = mdt.toString();
        String expected = "2000-01-23T23:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test15(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addWeeks(3);
		String actual = mdt.toString();
        String expected = "2000-10-22T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test16(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 03, 3, 34, 56, 789));
		mdt.setMillis(mdt1);
		String actual = mdt.toString();
        String expected = "2000-10-02T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test17(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 44, 56, 789));
		mdt.setMillis(mdt1);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:44:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test18(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setDayOfYear(3);
		String actual = mdt.toString();
        String expected = "2000-01-03T23:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test19(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setDayOfMonth(3);
		String actual = mdt.toString();
        String expected = "2000-10-03T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test20(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setDayOfWeek(3);
		String actual = mdt.toString();
        String expected = "2000-09-27T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test21(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addDays(3);
		String actual = mdt.toString();
        String expected = "2000-10-04T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test22(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setHourOfDay(4);
		String actual = mdt.toString();
        String expected = "2000-10-01T04:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test23(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addHours(3);
		String actual = mdt.toString();
        String expected = "2000-10-02T02:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test24(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setMinuteOfDay(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T00:03:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test25(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addMinutes(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:37:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test26(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setSecondOfDay(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T00:00:03.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test27(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);
		Duration dur = new Duration(start, end);
		mdt.add(dur);
		String actual = mdt.toString();
        String expected = "2000-10-08T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test28(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2004, 12, 25, 5, 6, 7, 0);
		Duration dur = new Duration(start, end);
		mdt.add(dur);
		String actual = mdt.toString();
        String expected = "2000-10-02T04:41:03.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test29(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setSecondOfMinute(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:34:03.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test30(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addSeconds(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:34:59.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test31(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setMillisOfDay(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T00:00:00.003-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test32(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setMillisOfSecond(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:34:56.003-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test33(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.addMillis(3);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:34:56.792-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test34(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setDate(3);
		String actual = mdt.toString();
        String expected = "1969-12-31T23:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test35(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 12, 22, 13, 34, 56, 789));
		mdt.setDate(mdt1);
		String actual = mdt.toString();
        String expected = "2000-12-22T23:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test36(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setDate(2008,1,2);
		String actual = mdt.toString();
        String expected = "2008-01-02T23:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test37(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		String actual = mdt.toString();
        String expected = "2000-10-01T13:34:56.789-04:00";
        assertEquals(actual,expected);
	}	
	//NOT USED
	public String testemm38(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 02, 3, 34, 56, 789));
		return mdt.toString();
	}
    @Test
public void test38(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().minuteOfHour());
		String actual = p.set(3).toString();
        String expected = "2000-10-01T23:03:56.789-04:00";
        assertEquals(actual,expected);
	}
	@Test
	public void test39(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2003, 10, 12, 3, 34, 56, 789));
		mdt.setTime(mdt1);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test40(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setTime(3, 14, 6, 789);
		String actual = mdt.toString();
        String expected = "2000-10-01T03:14:06.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test41(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setDateTime(2000, 10, 02, 3, 14, 6, 789);
		String actual = mdt.toString();
        String expected = "2000-10-02T03:14:06.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test42(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setDateTime(2000, 11, 12, 3, 34, 56, 789);
		String actual = mdt.toString();
        String expected = "2000-11-12T03:34:56.789-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test43(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual = p.add(3444).toString();
        String expected = "5444-10-01T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test44(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().minuteOfHour());
		String actual = p.add(4).toString();
        String expected = "2000-10-01T23:38:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test45(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		long l1 = 3444444L;
		String actual = p.add(l1).toString();
        String expected = "3446444-10-01T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test46(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual = p.addWrapField(344444).toString();
        String expected = "346444-10-01T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test47(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().minuteOfHour());
		String actual = p.addWrapField(4).toString();
        String expected = "2000-10-01T23:38:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test48(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual = p.set(3444).toString();
        String expected = "3444-10-01T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test49(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		String st = "11";
		Locale lc = new Locale("ja", "JP");
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().hourOfDay());
		String actual = p.set(st, lc).toString();
        String expected = "2000-10-02T07:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test50(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual = p.roundFloor().toString();
        String expected = "1999-12-31T19:00:00.000-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test51(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual = p.roundCeiling().toString();
        String expected = "2000-12-31T19:00:00.000-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test52(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual =  p.roundHalfFloor().toString();
        String expected = "2000-12-31T19:00:00.000-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test53(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual = p.roundHalfCeiling().toString();
        String expected = "2000-12-31T19:00:00.000-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test54(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().year());
		String actual = p.roundHalfEven().toString();
        String expected = "2000-12-31T19:00:00.000-05:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test55(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setMinuteOfHour(3);
		String actual =  mdt.toString();
        String expected = "2000-10-01T23:03:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test56(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2004, 12, 25, 6, 13, 55, 0);		
		Period period = new Period(start, end);
		mdt.add(period, 1);
		String actual = mdt.toString();
        String expected = "2000-10-02T05:48:51.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test57(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 0, 0, 0, 0);		
		Period period = new Period(start, end);
		mdt.add(period, 1);
		String actual = mdt.toString();
        String expected = "2001-10-08T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test58(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().minuteOfHour());
		long l1 = 4L;
		String actual = p.add(l1).toString();
        String expected = "2000-10-01T23:38:56.789-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test59(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		String st = "11";
		Locale lc = new Locale("ja", "JP");
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().dayOfMonth());
		String actual = p.set(st, lc).toString();
        String expected = "2000-10-10T23:34:56.789-04:00";
        assertEquals(actual,expected);
	}
	//------------------------ EMM ----------------------------------------------------------------------------------

	//------------------------ IPC ----------------------------------------------------------------------------------
    @Test
	public void test60(){
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		MutableDateTime mdt = new MutableDateTime(zone);
		int actual =  mdt.getHourOfDay();
        int expected = 18;
        assertEquals(actual,expected);
	}
    @Test
	public void test61(){
		MutableDateTime mdt = new MutableDateTime(2000, 10, 02, 3, 34, 56, 789, DateTimeZone.getDefault());
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970472096789");
        assertEquals(actual,expected);
	}*/
    @Test
	public void test62(){
		MutableDateTime mdt = new MutableDateTime(2000, 10, 02, 3, 34, 56, 789, ISOChronology.getInstanceUTC());
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970457696789");
        assertEquals(actual,expected);
	}
    @Test
	public void test63(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC());
		int actual = mdt.getYear();
        int expected = 2554;
        assertEquals(actual,expected);
	}
    @Test
	public void test64(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), DateTimeZone.getDefault());
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970457696789");
        assertEquals(actual,expected);
	}
    @Test
	public void test65(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), ISOChronology.getInstanceUTC());
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970457696789");
        assertEquals(actual,expected);
	}
    @Test
	public void test66(){
		DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		MutableDateTime mdt = new MutableDateTime(dt);
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970358400001");
        assertEquals(actual,expected);
	}
    @Test
	public void test67(){
		DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		MutableDateTime mdt = new MutableDateTime(dt, DateTimeZone.getDefault());
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970358400001");
        assertEquals(actual,expected);
	}
    @Test
	public void test68(){
		DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		MutableDateTime mdt = new MutableDateTime(dt, ISOChronology.getInstanceUTC());
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970358400001");
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test69(){
		MutableDateTime mdt = new MutableDateTime(2000, 10, 02, 3, 34, 56, 789);
		long actual = mdt.getMillis();
        long expected = Long.parseLong("970472096789");
        assertEquals(actual,expected);
	}
	//------------------------ IPC ----------------------------------------------------------------------------------

	//------------------------ ISI ----------------------------------------------------------------------------------
    @Test
	public void test70(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().hourOfDay(), 2);
		mdt.setMillis(mdt1);
		String actual = mdt.toString();
        String expected = "2000-10-02T10:00:00.000-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test71(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().hourOfDay(), 2);
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2004, 12, 25, 5, 6, 7, 0);
		Duration dur = new Duration(start, end);
		mdt.add(dur);
		String actual = mdt.toString();
        String expected = "2000-10-02T16:00:00.000-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test72(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().hourOfDay(), 2);
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2004, 12, 25, 2, 2, 2, 0);		
		Period period = new Period(start, end);
		mdt.add(period, 1);
		String actual = mdt.toString();
        String expected = "2000-10-02T13:00:00.000-04:00";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test73(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().dayOfMonth(), 2);
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		mdt.setZoneRetainFields(zone);
		String actual = mdt.toString();
        String expected = "2000-10-03T01:00:00.000+01:00";
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test74(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().secondOfMinute(), 2);
		mdt.set(DateTimeFieldType.millisOfSecond(), 18);
		String actual = mdt.toString();
        String expected = "2000-10-02T09:34:58.000-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test75(){
		MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().secondOfMinute(), 2);
		mdt.add(DurationFieldType.millis(), 55);
		String actual = mdt.toString();
        String expected = "2000-10-01T23:34:58.000-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test76(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().yearOfCentury(), 2);
		mdt.setYear(55);
		String actual = mdt.toString();
        String expected = "0055-12-29T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test77(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().yearOfCentury(), 2);
		mdt.addYears(55);
		String actual = mdt.toString();
        String expected = "1513-01-10T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test78(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().weekyearOfCentury(), 2);
		mdt.setWeekyear(50);
		String actual = mdt.toString();
        String expected = "0051-01-01T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test79(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().yearOfCentury(), 2);
		mdt.addWeekyears(5);
		String actual = mdt.toString();
        String expected = "1463-01-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test80(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().year(), 2);
		mdt.setMonthOfYear(2);
		String actual = mdt.toString();
        String expected = "1459-01-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test81(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().year(), 2);
		mdt.addMonths(1);
		String actual = mdt.toString();
        String expected = "1459-01-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test82(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().weekyear(), 2);
		mdt.setWeekOfWeekyear(10);
		String actual = mdt.toString();
        String expected = "1459-01-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test83(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().weekyear(), 2);
		mdt.addWeeks(1);
		String actual = mdt.toString();
        String expected = "1459-01-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test84(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().year(), 2);
		mdt.setDayOfYear(110);
		String actual = mdt.toString();
        String expected = "1459-01-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test85(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().monthOfYear(), 2);
		mdt.setDayOfMonth(11);
		String actual = mdt.toString();
        String expected = "1457-12-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test86(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().monthOfYear(), 2);
		mdt.setDayOfWeek(7);
		String actual = mdt.toString();
        String expected = "1457-12-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test87(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().monthOfYear(), 2);
		mdt.addDays(7);
		String actual = mdt.toString();
        String expected = "1457-12-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test88(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().dayOfYear(), 2);
		mdt.setHourOfDay(7);
		String actual = mdt.toString();
        String expected = "1457-10-11T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test89(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().dayOfYear(), 2);
		mdt.addHours(7);
		String actual = mdt.toString();
        String expected = "1457-10-12T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test90(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().hourOfDay(), 2);
		mdt.setMinuteOfDay(7);
		String actual = mdt.toString();
        String expected = "1457-10-10T01:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test91(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().hourOfDay(), 2);
		mdt.setMinuteOfHour(7);
		String actual = mdt.toString();
        String expected = "1457-10-11T00:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test92(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().hourOfDay(), 2);
		mdt.addMinutes(7);
		String actual = mdt.toString();
        String expected = "1457-10-11T00:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test93(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().minuteOfDay(), 2);
		mdt.setSecondOfDay(7);
		String actual = mdt.toString();
        String expected = "1457-10-10T00:00:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test94(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().minuteOfHour(), 2);
		mdt.setSecondOfMinute(7);
		String actual = mdt.toString();
        String expected = "1457-10-10T22:38:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test95(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().minuteOfHour(), 2);
		mdt.addSeconds(7);
		String actual = mdt.toString();
        String expected = "1457-10-10T22:39:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test96(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().secondOfDay(), 2);
		mdt.setMillisOfDay(7);
		String actual = mdt.toString();
        String expected = "1457-10-10T00:00:01.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test97(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().secondOfMinute(), 2);
		mdt.setMillisOfSecond(7);
		String actual = mdt.toString();
        String expected = "1457-10-10T22:38:56.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test98(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().secondOfMinute(), 2);
		mdt.addMillis(7);
		String actual = mdt.toString();
        String expected = "1457-10-10T22:38:56.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test99(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().year(), 2);
		mdt.setDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 11, 02, 3, 34, 56, 789));
		String actual = mdt.toString();
        String expected = "1458-01-09T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test100(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().dayOfMonth(), 2);
		mdt.setTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 44, 56, 789));
		String actual = mdt.toString();
        String expected = "1457-10-11T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test101(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().dayOfMonth(), 2);
		mdt.setTime(3, 44, 56, 789);
		String actual = mdt.toString();
        String expected = "1457-10-11T19:03:58.000-04:56:02";
        assertEquals(actual,expected);
	}
    @Test
	public void test102(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().dayOfMonth(), 2);
		mdt.setDateTime(2000, 10, 02, 3, 44, 56, 789);
		String actual = mdt.toString();
        String expected = "2000-10-02T20:00:00.000-04:00";
        assertEquals(actual,expected);
	}*/
	//NOT USED
	/*	
	public String testisi76(){
		MutableDateTime mdt = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789));
		mdt.setRounding(BuddhistChronology.getInstanceUTC().hourOfDay(), 2);
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		mdt.setZone(zone);
		return mdt.toString();
	}*/
	//------------------------ ISI ----------------------------------------------------------------------------------

	//------------------------ OAN 1-3---------------------(ok)-----------------------------------------------------------
	//  left: 0
	//  kill others: OMR(3)
	  @Test
	  public void test103(){ //
		MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 11));
		 LocalTime ld1 = new LocalTime(ISOChronology.getInstanceUTC());
		mdt1.setRounding(ld1.getField(2, ISOChronology.getInstanceUTC()),2);
		 mdt1.setRounding(ld1.getField(2, ISOChronology.getInstanceUTC()));
		int actual = mdt1.getRoundingMode(); 
        int expected = 1;
        assertEquals(actual,expected);
	  }
    /*@Test
	 public void test104(){//
		MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 02, 01, 8, 10, 10, 11));
		 MutableDateTime.Property P1 = new MutableDateTime.Property(mdt1,ISOChronology.getInstanceUTC().year());
		Period p = new Period(0L);
		mdt1.add(p);
		DateTimeField actual = P1.getField();
        DateTimeField expected = 
	  }*/


	  //-------------------------kill mutants for JSI1-4 operator ----(OK)
		// result:
		//  test 1  kill  ==> JSI_2 OMR_1 
		//  test 2  kill  ==> JSI_4 
		//  test 3  kill  ==> JSI_1 OAN_1 
		//  test 4  kill  ==> JSI_3 
		// left: 0 
		// kill others:: OAN(1),OMR(1)

		
	/*	 public static DateTimeField test105(){ // 
		   MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 11));
			MutableDateTime mdt2 = new MutableDateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 8, 10, 10, 11)); 
			LocalTime ld1 = new LocalTime(ISOChronology.getInstanceUTC());
			mdt1.setRounding(ld1.getField(1, ISOChronology.getInstanceUTC()));
			mdt2.setRounding(ld1.getField(2, ISOChronology.getInstanceUTC()));
		   return mdt1.getRoundingField();  
		 }

		 public static Chronology test106(){ //
		   MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC());
			MutableDateTime mdt2 = new MutableDateTime(BuddhistChronology.getInstanceUTC());
		   MutableDateTime.Property P1 = new MutableDateTime.Property(mdt1,ISOChronology.getInstanceUTC().year());
		   MutableDateTime.Property P2 = new MutableDateTime.Property(mdt2,BuddhistChronology.getInstanceUTC().monthOfYear());
			return  P1.getChronology();  
		 }

 public static DateTimeField test107(){ // 
    MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 02, 01, 8, 10, 10, 11));
    MutableDateTime.Property P1 = new MutableDateTime.Property(mdt1,ISOChronology.getInstanceUTC().year());
    MutableDateTime.Property P2 = new MutableDateTime.Property(mdt1, BuddhistChronology.getInstanceUTC().monthOfYear());
    return  P1.getField();  
 }*/

	  //-------------------------kill mutants for PCI1-95 operator ----(ok)---

//		result:
		//  test 1  kill  ==> IPC_2 PCI_10 PCI_11 PCI_12 PCI_13 PCI_14 PCI_3 PCI_4 PCI_6 PCI_7 PCI_8 PCI_9 
		//  test 2  kill  ==> PCI_17 PCI_18 PCI_19 PCI_20 PCI_21 PCI_22 PCI_23 PCI_25 PCI_26 PCI_27 PCI_28 PCI_5 
		//  test 3  kill  ==> EAM_1 EAM_10 EAM_11 EAM_12 EAM_13 EAM_14 EAM_15 EAM_16 EAM_17 EAM_18 EAM_2 EAM_3 EAM_4 EAM_5 EAM_6 EAM_7 EAM_8 EAM_9 EMM_61 IPC_3 PCI_31 PCI_32 PCI_33 PCI_34 PCI_35 PCI_36 PCI_37 PCI_39 PCI_40 PCI_41 PCI_42 
		//  test 4  kill  ==> EMM_62 EMM_64 
		//  test 5  kill  ==> EMM_68 EMM_69 EMM_70 PCI_45 PCI_46 PCI_47 PCI_48 PCI_49 PCI_50 PCI_51 PCI_53 PCI_54 PCI_55 PCI_56 
		//  test 6  kill  ==> PCI_60 PCI_63 PCI_64 PCI_65 PCI_66 PCI_67 PCI_78 PCI_79 PCI_80 PCI_81 PCI_82 PCI_83 
		//  test 7  kill  ==> PCI_72 PCI_77 
		// equivalent: 18
		// left:  18 (include?---24,38,52)
		// kill others:: IPC(2),EAM(18),EMM(6)


			
		/*public static Chronology test108(){ //
			MutableDateTime mdt1 = new MutableDateTime(GregorianChronology.getInstanceUTC());
			MutableDateTime.Property P1 = new MutableDateTime.Property(mdt1,GregorianChronology.getInstanceUTC().year());
			DateTimeZone dtz = DateTimeZone.UTC;
			mdt1.setZone(dtz);
		   return P1.getChronology();  
		 }


		 public static Chronology test109(){ // 
			MutableDateTime mdt1 = new MutableDateTime(DateTimeZone.UTC);
			MutableDateTime.Property P1 = new MutableDateTime.Property(mdt1,GregorianChronology.getInstanceUTC().year());
		    DateTimeZone dtz = DateTimeZone.forID("+01:00");
			mdt1.setZone(dtz);
		    return P1.getChronology();  
		 }*/
		/* TimeDepend @Test
		public void test110(){ //  
			MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 8, 10, 10, 11));
			MutableDateTime mdt2 = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 8, 10, 10, 11), BuddhistChronology.getInstanceUTC()); 	  
			mdt1.setDate(mdt2); 
		    String actual = mdt1.toString(); 
            String expected = "1467-10-10T04:10:10.011-04:56:02";
            assertEquals(actual,expected);
		 }
		@Test
		public void test111(){ //    
			MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 8, 10, 10, 11));
			MutableDateTime mdt2 = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 8, 10, 10, 11), BuddhistChronology.getInstanceUTC()); 	  
			mdt1.setTime(mdt2); 
		    String actual = mdt1.toString(); 
            String expected = "2010-10-01T08:10:10.011-04:00";
            assertEquals(actual,expected);
		 }
	     @Test
		 public void test112(){ //    
			MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 8, 10, 10, 11));
			mdt1.property(DateTimeFieldType.hourOfDay()); 
		    String actual = mdt1.toString(); 
            String expected = "2010-10-01T04:10:10.011-04:00";
            assertEquals(actual,expected);
		 }
         @Test
		 public void test113(){ //    
			MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 8, 10, 10, 11));
			mdt1.property(DateTimeFieldType.monthOfYear());  
		    String actual = mdt1.toString();  
            String expected = "2010-10-01T04:10:10.011-04:00";
            assertEquals(actual,expected);
		 }*/
		 /*public static Chronology test114(){ // 
			MutableDateTime mdt1 = new MutableDateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 13, 34, 56, 789),BuddhistChronology.getInstanceUTC());
			MutableDateTime.Property P1 = new MutableDateTime.Property(mdt1,BuddhistChronology.getInstanceUTC().year());
			DateTimeZone dtz = DateTimeZone.forID("+01:00");
			mdt1.setZone(dtz);
		    return P1.getChronology();  
		 }*/
	
/*	public static void main(String[] args){
	/*	MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		System.out.println(mdt.getMillis());
		System.out.println(mdt);
		System.out.println(mdt.getDayOfMonth());
		mdt.setRounding(ISOChronology.getInstanceUTC().dayOfMonth(), 2);	
		//mdt.set(DateTimeFieldType.hourOfDay(), 2);
		System.out.println(mdt.getMillis());
		System.out.println(mdt);
		System.out.println(mdt.getDayOfMonth());

	/*	MutableDateTime mdt1 = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 23, 34, 56, 789));
		mdt1.setRounding(BuddhistChronology.getInstanceUTC().secondOfMinute());
		System.out.println(mdt1.getMillis());
		mdt1.setDate(mdt1.getMillis());
		System.out.println(mdt1.getMillis());*/
		/*	MutableDateTime mdt = new MutableDateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
		 String st = "11";
	  Locale lc = new Locale("ja", "JP");
	  /// System.out.println(ld1.toString(st,Locale.JAPANESE));
		MutableDateTime.Property p = new MutableDateTime.Property(mdt, ISOChronology.getInstanceUTC().hourOfDay());
		System.out.println(p.set(st, lc).toString());
	}*/
}
