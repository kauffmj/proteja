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

public class PeriodTest extends TestCase
{
    
    /*public static void main(String[] args)
    {
        //System.out.println("test2: " + test2());
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
        //System.out.println("test45: " + test45());
        //System.out.println("test46: " + test46());
    }*/
	//------------------------  PMD ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		Period period = Period.ZERO;
		int actual = period.getYears();
        int expected = 0;
        assertEquals(actual,expected);
	}
	//------------------------  PMD ----------------------------------------------------------------------------------

	//------------------------  EAM ----------------------------------------------------------------------------------
	/*public static String test2(){
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2006, 1, 1, 3, 4, 5, 56);
		Period period = new Period(start, end);
		return period.toStandardWeeks().toString();
	}*/
    @Test
	public void test3(){
		Period period = Period.days(8);
		String actual = period.toStandardWeeks().toString();
        String expected = "P1W";
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		Period period = new Period(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		String actual = period.toStandardWeeks().toString();
        String expected = "P1604W";
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		Period period = Period.minutes(12000);
		String actual = period.toStandardWeeks().toString();
        String expected = "P1W";
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		Period period = Period.hours(2000);
		String actual = period.toStandardWeeks().toString();
        String expected = "P11W";
        assertEquals(actual,expected);
	}
    @Test
	public void test7(){
		Period period = Period.seconds(720000);
		String actual = period.toStandardWeeks().toString();
        String expected = "P1W";
        assertEquals(actual,expected);
	}
    @Test
	public void test8(){
		Period period = Period.millis(720000000);
		String actual = period.toStandardWeeks().toString();
        String expected = "P1W";
        assertEquals(actual,expected);
	}
    @Test
	public void test9(){
		Period period = Period.millis(90000000);
		String actual = period.toStandardDays().toString();
        String expected = "P1D";
        assertEquals(actual,expected);
	}
    @Test
	public void test10(){
		Period period = Period.seconds(90000);
		String actual = period.toStandardDays().toString();
        String expected = "P1D";
        assertEquals(actual,expected);
	}
    @Test
	public void test11(){
		Period period = Period.hours(250);
		String actual = period.toStandardDays().toString();
        String expected = "P10D";
        assertEquals(actual,expected);
	}
    @Test
	public void test12(){
		Period period = Period.minutes(1500);
		String actual = period.toStandardDays().toString();
        String expected = "P1D";
        assertEquals(actual,expected);
	}
    @Test
	public void test13(){
		Period period = Period.weeks(3);
		String actual = period.toStandardDays().toString();
        String expected = "P21D";
        assertEquals(actual,expected);
	}
    @Test
	public void test14(){
		Period period = Period.days(8);
		String actual = period.toStandardDays().toString();
        String expected = "P8D";
        assertEquals(actual,expected);
	}
    @Test
	public void test15(){
		Period period = Period.millis(9000000);
		String actual = period.toStandardHours().toString();
        String expected = "PT2H";
        assertEquals(actual,expected);
	}
    @Test
	public void test16(){
		Period period = Period.seconds(9000);
		String actual = period.toStandardHours().toString();
        String expected = "PT2H";
        assertEquals(actual,expected);
	}
    @Test
	public void test17(){
		Period period = Period.hours(25);
		String actual = period.toStandardHours().toString();
        String expected = "PT25H";
        assertEquals(actual,expected);
	}
    @Test
	public void test18(){
		Period period = Period.minutes(150);
		String actual = period.toStandardHours().toString();
        String expected = "PT2H";
        assertEquals(actual,expected);
	}
    @Test
	public void test19(){
		Period period = Period.weeks(3);
		String actual = period.toStandardHours().toString();
        String expected = "PT504H";
        assertEquals(actual,expected);
	}
    @Test
	public void test20(){
		Period period = Period.days(8);
		String actual = period.toStandardHours().toString();
        String expected = "PT192H";
        assertEquals(actual,expected);
	}
    @Test
	public void test21(){
		Period period = Period.millis(9000000);
		String actual = period.toStandardMinutes().toString();
        String expected = "PT150M";
        assertEquals(actual,expected);
	}
    @Test
	public void test22(){
		Period period = Period.seconds(9000);
		String actual = period.toStandardMinutes().toString();
        String expected = "PT150M";
        assertEquals(actual,expected);
	}
    @Test
	public void test23(){
		Period period = Period.hours(25);
		String actual = period.toStandardMinutes().toString();
        String expected = "PT1500M";
        assertEquals(actual,expected);
	}
    @Test
	public void test24(){
		Period period = Period.minutes(150);
		String actual = period.toStandardMinutes().toString();
        String expected = "PT150M";
        assertEquals(actual,expected);
	}
    @Test
	public void test25(){
		Period period = Period.weeks(3);
		String actual = period.toStandardMinutes().toString();
        String expected = "PT30240M";
        assertEquals(actual,expected);
	}
    @Test
	public void test26(){
		Period period = Period.days(8);
		String actual = period.toStandardMinutes().toString();
        String expected = "PT11520M";
        assertEquals(actual,expected);
	}
    @Test
	public void test27(){
		Period period = Period.millis(9000000);
		String actual = period.toStandardSeconds().toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test28(){
		Period period = Period.seconds(9000);
		String actual = period.toStandardSeconds().toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test29(){
		Period period = Period.hours(25);
		String actual = period.toStandardSeconds().toString();
        String expected = "PT90000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test30(){
		Period period = Period.minutes(150);
		String actual = period.toStandardSeconds().toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test31(){
		Period period = Period.weeks(3);
		String actual = period.toStandardSeconds().toString();
        String expected = "PT1814400S";
        assertEquals(actual,expected);
	}
    @Test
	public void test32(){
		Period period = Period.days(8);
		String actual = period.toStandardSeconds().toString();
        String expected = "PT691200S";
        assertEquals(actual,expected);
	}
    @Test
	public void test33(){
		Period period = Period.millis(9000000);
		String actual = period.toStandardDuration().toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test34(){
		Period period = Period.seconds(9000);
		String actual = period.toStandardDuration().toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test35(){
		Period period = Period.hours(25);
		String actual = period.toStandardDuration().toString();
        String expected = "PT90000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test36(){
		Period period = Period.minutes(150);
		String actual = period.toStandardDuration().toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test37(){
		Period period = Period.weeks(3);
		String actual = period.toStandardDuration().toString();
        String expected = "PT1814400S";
        assertEquals(actual,expected);
	}
    @Test
	public void test38(){
		Period period = Period.days(8);
		String actual = period.toStandardDuration().toString();
        String expected = "PT691200S";
        assertEquals(actual,expected);
	}
    @Test
	public void test39(){
		Period period = Period.millis(9000000);
		String actual = period.normalizedStandard(PeriodType.millis()).toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test40(){
		Period period = Period.seconds(9000);
		String actual = period.normalizedStandard(PeriodType.millis()).toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test41(){
		Period period = Period.hours(25);
		String actual = period.normalizedStandard(PeriodType.millis()).toString();
        String expected = "PT90000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test42(){
		Period period = Period.minutes(150);
		String actual = period.normalizedStandard(PeriodType.millis()).toString();
        String expected = "PT9000S";
        assertEquals(actual,expected);
	}
    @Test
	public void test43(){
		Period period = Period.weeks(3);
		String actual = period.normalizedStandard(PeriodType.millis()).toString();
        String expected = "PT1814400S";
        assertEquals(actual,expected);
	}
    @Test
	public void test44(){
		Period period = Period.days(8);
		String actual = period.normalizedStandard(PeriodType.millis()).toString();
        String expected = "PT691200S";
        assertEquals(actual,expected);
	}
	/*public static String test45(){
		Period period = Period.months(8);
		return period.normalizedStandard(PeriodType.millis()).toString();
	}
	public static String test46(){
		Period period = Period.years(8);
		return period.normalizedStandard(PeriodType.millis()).toString();
	}*/
	//------------------------  EAM ----------------------------------------------------------------------------------

/*	public static void main(String[] args){
	//	Period period1 = new Period(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
	BasePeriod period = Period.ZERO;
	System.out.println(period.toPeriod());
		System.out.println(DateTimeConstants.MILLIS_PER_WEEK);
		System.out.println(DateTimeConstants.MILLIS_PER_DAY);
		System.out.println(DateTimeConstants.MILLIS_PER_HOUR);
		System.out.println(DateTimeConstants.MILLIS_PER_MINUTE);
		System.out.println(DateTimeConstants.MILLIS_PER_SECOND);
	}*/
}
