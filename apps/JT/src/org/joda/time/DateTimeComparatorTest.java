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

public class DateTimeComparatorTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        System.out.println("test3: " + test3());
    }*/

	//------------------------ OMR ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		DateTimeComparator dtc = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), DateTimeFieldType.year());
		DateTimeComparator dtc1 = DateTimeComparator.getInstance(DateTimeFieldType.secondOfDay(), DateTimeFieldType.dayOfYear());
		boolean actual = dtc.equals(dtc1);
        boolean expected = false;
        assertEquals(actual,expected);
	}
	/*public static String test1(){
		DateTimeComparator dtc = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), DateTimeFieldType.year());
		return dtc.getInstance(DateTimeFieldType.secondOfDay()).toString();
	}*/
	//------------------------ OMR ----------------------------------------------------------------------------------

	//------------------------ IOD ----------------------------------------------------------------------------------
    @Test
	public void test2(){
		DateTimeComparator dtc = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), DateTimeFieldType.year());
		DateTimeComparator dtc1 = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), DateTimeFieldType.year());
		boolean actual = dtc.equals(dtc1);
        boolean expected = true;
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		DateTimeComparator dtc = DateTimeComparator.getInstance(DateTimeFieldType.dayOfYear(), DateTimeFieldType.year());
		String actual = dtc.toString();
        String expected = "DateTimeComparator[dayOfYear-year]";
        assertEquals(actual,expected);
	}
	//------------------------ IOD ----------------------------------------------------------------------------------
}
