package org.joda.time.tz;

import java.io.*;
import java.util.*;
import java.text.*;

import org.joda.time.base.*;
import org.joda.time.convert.*;
import org.joda.time.field.*;
import org.joda.time.format.*;
import org.joda.time.chrono.*;
import org.joda.time.*;
import org.joda.time.tz.*;

import org.junit.Test;
import junit.framework.*;

public class CachedDateTimeZoneTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
    }*/
	//------------------------ IOD ----------------------------------------------------------------------------------
    @Test
	public void test1(){
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		CachedDateTimeZone z1 = CachedDateTimeZone.forZone(zone);
		int actual = z1.hashCode();	
        int expected = 1521699534;
        assertEquals(actual,expected);
	}
	//------------------------ IOD ----------------------------------------------------------------------------------

	//------------------------ PCI ----------------------------------------------------------------------------------
    @Test
	public void test2(){
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		CachedDateTimeZone z1 = CachedDateTimeZone.forZone(zone);
		boolean actual = z1.isFixed();	
        boolean expected = true;
        assertEquals(actual,expected);
	}
    @Test
	public void test3(){
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		CachedDateTimeZone z1 = CachedDateTimeZone.forZone(zone);
		int actual = z1.getOffset(ISOChronology.getInstanceUTC().getDateTimeMillis(1999, 10, 02, 3, 34, 56, 789));	
        int expected = 3600000;
        assertEquals(actual,expected);
	}
    @Test
	public void test4(){
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		CachedDateTimeZone z1 = CachedDateTimeZone.forZone(zone);
		int actual = z1.getStandardOffset(ISOChronology.getInstanceUTC().getDateTimeMillis(1999, 10, 02, 3, 34, 56, 789));	
        int expected = 3600000;
        assertEquals(actual,expected);
	}
	//not used
	/*public boolean test4(){
		DateTimeZone zone = DateTimeZone.forID("+01:00");
		//FixedDateTimeZone z2 = new FixedDateTimeZone("id", "nameKey", 2, 1);
		CachedDateTimeZone z1 = CachedDateTimeZone.forZone(zone);
		CachedDateTimeZone z2 = CachedDateTimeZone.forZone(z1);
		return z2.isFixed();	
	}*/
	//------------------------ PCI ----------------------------------------------------------------------------------
/*	public static void main(String[] args){
		DateTimeZone zone = DateTimeZone.forID("-05:00");
		DateTimeZone zone2 = DateTimeZone.forID("+02:00");
		CachedDateTimeZone z1 = CachedDateTimeZone.forZone(zone);
		CachedDateTimeZone z2 = CachedDateTimeZone.forZone(zone2);
		System.out.println(z1.isFixed());
		System.out.println(z1.getNameKey(ISOChronology.getInstanceUTC().getDateTimeMillis(2009, 12, 1, 3, 34, 56, 789)));
		System.out.println(z2.getNameKey(ISOChronology.getInstanceUTC().getDateTimeMillis(2009, 12, 1, 3, 34, 56, 789)));
		for(int i = 1; i < 32; i++)
		{
			long l1 = ISOChronology.getInstanceUTC().getDateTimeMillis(2009, 12, i, 3, 34, 56, 789);
			long l2 = z1.nextTransition(ISOChronology.getInstanceUTC().getDateTimeMillis(2009, 12, i, 3, 34, 56, 789));
			long l3 = z1.previousTransition(ISOChronology.getInstanceUTC().getDateTimeMillis(2009, 12, i, 3, 34, 56, 789));
			if(l1 == l2 && l1 == l3)
				System.out.println("true");

		}
	}*/
}
