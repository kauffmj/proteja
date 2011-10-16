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

public class InstantTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        //System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        System.out.println("test4: " + test4());
        System.out.println("test5: " + test5());
    }*/
    /* TimeDepend @Test
	public void test1(){
		Instant instant = new Instant(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 1, 1, 1, 1));
		String actual = instant.toDateTimeISO().toString();
        String expected = "2000-09-30T21:01:01.001-04:00";
        assertEquals(actual,expected);
	}*/
	
    /* TODO: Make this work.
    @Test(expected=java.lang.IllegalArgumentException.class)
	public void test2() throws Exception{
		LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
		Instant instant = new Instant(ld1);
		instant.toDateTime().toString();
	}*/
    /* TimeDepend @Test
	public void test3(){
		Instant instant = new Instant(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 1, 1, 1, 1));
		String actual = instant.toMutableDateTimeISO().toString();
        String expected = "2000-09-30T21:01:01.001-04:00";
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		Instant instant = new Instant(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 1, 1, 1, 1));
		String actual = instant.toMutableDateTime().toString();
        String expected = "2000-09-30T21:01:01.001-04:00";
        assertEquals(actual,expected);
	}*/
    @Test		
	public void test5(){
		Instant instant = new Instant(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 1, 1, 1, 1, 1));
		String actual = instant.toInstant().toString();
        String expected = "2000-10-01T01:01:01.001Z";
        assertEquals(actual,expected);
	}
}
