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

public class LenientDateTimeFieldTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
    }*/
	//------------------------ PCC ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		StrictDateTimeField sdtf = new StrictDateTimeField(BuddhistChronology.getInstanceUTC().monthOfYear());
		String actual = LenientDateTimeField.getInstance(sdtf, BuddhistChronology.getInstanceUTC()).toString();
        String expected = "DateTimeField[monthOfYear]";
        assertEquals(actual,expected);
	}
	//------------------------ PCC ----------------------------------------------------------------------------------

	//------------------------ IOD ----------------------------------------------------------------------------------
    @Test
	public void test2(){
		StrictDateTimeField sdtf = new StrictDateTimeField(BuddhistChronology.getInstanceUTC().monthOfYear());
		DateTimeField ldtf = LenientDateTimeField.getInstance(sdtf, BuddhistChronology.getInstanceUTC());
		ldtf.set(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), 33);
		String actual = ldtf.toString();
        String expected = "DateTimeField[monthOfYear]";
        assertEquals(actual,expected);
	}
	//------------------------ IOD ----------------------------------------------------------------------------------

	//------------------------ PCI ----------------------------------------------------------------------------------
    @Test
	public void test3(){
		SkipDateTimeField sdtf = new SkipDateTimeField(ISOChronology.getInstanceUTC(), ISOChronology.getInstanceUTC().monthOfYear());
		DateTimeField ldtf = LenientDateTimeField.getInstance(sdtf, ISOChronology.getInstanceUTC());
		ldtf.set(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 02, 3, 34, 56, 789), 33);
		String actual = ldtf.toString();
        String expected = "DateTimeField[monthOfYear]";
        assertEquals(actual,expected);
	}
	//------------------------ PCI ----------------------------------------------------------------------------------
}
