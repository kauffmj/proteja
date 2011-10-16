package org.joda.time.field;

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

public class DelegatedDateTimeFieldTest extends TestCase
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
    }*/
	//------------------------  OAN AND OMR ----------------------------------------------------------------------------------
    /* TimeDepend @Test
	public void test1(){		
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		Locale lc = new Locale("ja", "JP");
		String actual = ddtf.getAsText(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), lc);
        String expected = "84896789";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test2(){
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		int actual = ddtf.getMaximumValue(ld, ld.getValues());
        int expected = 86399999;
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		int actual = ddtf.getMaximumValue(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
        int expected = 86399999;
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		int actual = ddtf.getMaximumValue(ld);
        int expected = 86399999;
        assertEquals(actual,expected);
	}
    @Test
	public void test5(){
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		int actual = ddtf.getMinimumValue(ld);
        int expected = 0;
        assertEquals(actual,expected);
	}
    @Test
	public void test6(){
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		int actual = ddtf.getMinimumValue(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789));
        int expected = 0;
        assertEquals(actual,expected);
	}
    /* TimeDepend @Test
	public void test7(){		
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		Locale lc = new Locale("ja", "JP");
		String actual = ddtf.getAsShortText(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), lc);
        String expected = "84896789";
        assertEquals(actual,expected);
	}*/
    @Test
	public void test8(){
		DelegatedDateTimeField ddtf = new DelegatedDateTimeField(ISOChronology.getInstance().millisOfDay(), DateTimeFieldType.dayOfMonth());
		LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		int actual = ddtf.getMaximumValue(ld, ld.getValues());
        int expected = 86399999;
        assertEquals(actual,expected);
	}
	//------------------------  OAN AND OMR ----------------------------------------------------------------------------------
}
