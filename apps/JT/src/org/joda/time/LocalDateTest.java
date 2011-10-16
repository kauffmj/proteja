package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.text.*;

import org.joda.time.base.*;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PartialConverter;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.DateTimeUtils;
import org.joda.time.Chronology;
import org.joda.time.chrono.*;

import org.joda.time.LocalTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.*;

import org.junit.Test;
import junit.framework.*;

public class LocalDateTest extends TestCase {

	Date d1 = new Date();
	DateTimeUtils dtu = new DateTimeUtils();

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
        //System.out.println("test25: " + test25());
        //System.out.println("test26: " + test26());
        System.out.println("test27: " + test27());
        System.out.println("test28: " + test28());
        System.out.println("test29: " + test29());
        System.out.println("test30: " + test30());
        System.out.println("test32: " + test32());
        System.out.println("test33: " + test33());
        System.out.println("test34: " + test34());
        System.out.println("test35: " + test35());
        System.out.println("test36: " + test36());
        System.out.println("test37: " + test37());
    }*/

 

/*
public static void main(String[] args){
	/*	System.out.println("timezone: " + DateTimeZone.forID("America/Los_Angeles"));
		System.out.println("chronology: " + ISOChronology.getInstance(DateTimeZone.forID("America/Los_Angeles")));
	//	LocalDate ld = new LocalDate(DateTimeZone.forID("America/Los_Angeles"));
		LocalDate ld = new LocalDate(1990, 10, 12, ISOChronology.getInstance(DateTimeZone.forID("America/Los_Angeles")));
		System.out.println("d" + ld.getChronology());
	//	LocalDate ld = new LocalDate(DateTimeZone.forID("America/Los_Angeles"));
		System.out.println(ld1.getLocalMillis());
		LocalDate ld2 = new LocalDate(DateTimeZone.forID("Europe/Paris"));
		System.out.println(ld2.getChronology());
		System.out.println(ld2.getLocalMillis());
		*/
	/*	System.out.println(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		System.out.println(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		System.out.println(ld.getChronology());
		System.out.println(ld.getLocalMillis());
		LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		System.out.println(ld1.getChronology());*/
	/*	LocalDate ld = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstance());
		System.out.println(ld.getLocalMillis());
		System.out.println(ld.getChronology());
		LocalDate ld1 = new LocalDate(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), GregorianChronology.getInstance(null, 4));
		System.out.println(ld1.getLocalMillis());
		LocalDate ld2 = new LocalDate(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), (AssembledChronology)GregorianChronology.getInstance(null, 4));
		System.out.println(ld2.getLocalMillis());
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test1.dat")); 
		ld.writeObject(in);
		 LocalDate ld1 = new LocalDate(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		System.out.println(ld1.getChronology());
		System.out.println(ld1.getLocalMillis());*/
	/*	LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
		LocalDate.Property p = new LocalDate.Property(ld1, BuddhistChronology.getInstanceUTC().year());
		System.out.println(p.getChronology());
			LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	return ld.toLocalDateTime(lt).toString();
LocalDate ld = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(200, 10, 01, 01));
System.out.println(ld.getYearOfEra());
System.out.println(ld.getYear());
	 LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 02, 11, 00));
	LocalDate.Property p = new LocalDate.Property(ld1, BuddhistChronology.getInstanceUTC().yearOfEra());
	  System.out.println(p.withMaximumValue().toString());   
	   System.out.println(p.getMaximumValue());
	  System.out.println(p.getMaximumValueOverall());  
	   System.out.println(p.withMinimumValue().toString()); 
	     System.out.println(p.getMinimumValue()); 
	      System.out.println(p.getMinimumValueOverall()); 
		  	LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	 System.out.println(ld.get(DateTimeFieldType.dayOfYear()));*/
//IOD
/*	LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2001, 10, 01, 00), ISOChronology.getInstanceUTC());
	  LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 00), ISOChronology.getInstanceUTC());
	  System.out.println(ld1.equals(ld));
	  System.out.println(ld1 == ld);
	  System.out.println(ld1.getLocalMillis());
	  System.out.println(ld.getLocalMillis());

	   System.out.println(ld.size());
	   DateTimeField [] fields = ld.getFields();
	   int [] values = ld.getValues();
	    for (int i = 0; i < ld.size(); i++) {
           System.out.println( fields[i].toString() + values[i]);
		}
	   System.out.println(ld1.size());
	   	   DateTimeField [] fields1 = ld1.getFields();
	   int [] values1 = ld1.getValues();
	    for (int i = 0; i < ld1.size(); i++) {
           System.out.println( fields1[i].toString() + values1[i]);
		}
		 System.out.println( fields[0] == fields1[0]);
		 System.out.println( fields[0].toString().equals(fields1[0].toString()));

System.out.println(DateTimeUtils.currentTimeMillis());
//LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(1900, 01, 01, 2001), ISOChronology.getInstanceUTC());
LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(1900, 01, 01, 11, 06, 30, 20), BuddhistChronology.getInstanceUTC());
      String st = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
	  Locale lc = new Locale("ja", "JP");
	   System.out.println(ld1.toString(st,Locale.JAPANESE));
	   System.out.println(ld1.toString(st));
}
*/

//Below 19 test cases for PCI
//------------------------ PCI ----------------------------------------------------------------------------------

 /* TimeDepend   @Test
 public void test1(){   
	LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	long actual = ld.getLocalMillis();
    long expected = Long.valueOf("970272000000").longValue();
    assertEquals(actual,expected);
  }*/
    @Test
  public void test2(){   
	LocalDate ld = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	long actual = ld.getLocalMillis();
    long expected = Long.valueOf("-16164316800000").longValue();
    assertEquals(actual,expected);
  }
    @Test
  public void test3(){   
	LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	String actual = ld.getField(0, ISOChronology.getInstanceUTC()).toString();
    String expected = "DateTimeField[year]";
    assertEquals(actual,expected);
  }
    @Test
  public void test4(){   
	LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	String actual = ld.getField(1, ISOChronology.getInstanceUTC()).toString();
    String expected = "DateTimeField[monthOfYear]";
    assertEquals(actual,expected);
  }
    @Test
  public void test5(){   
	LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	String actual = ld.getField(2, ISOChronology.getInstanceUTC()).toString();
    String expected = "DateTimeField[dayOfMonth]";
    assertEquals(actual,expected);
  }
    @Test
  public void test6(){
	  LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	  LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	  Boolean actual = ld.equals(ld1);
      Boolean expected = new Boolean(false);
      assertEquals(actual,expected);
  }
    @Test
  public void test7(){
	  LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	  LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	  Boolean actual = ld1.equals(ld);
      Boolean expected = new Boolean(false);
      assertEquals(actual,expected);
  }
  /* TimeDepend @Test
  public void test8(){
	  LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	  LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	  int actual = ld1.compareTo(ld);
      int expected = 1;
      assertEquals(actual,expected);
  }
    @Test
  public void test9(){
	  LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	  LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	  int actual = ld.compareTo(ld1);
      int expected = -1;
      assertEquals(actual,expected);
  }
    @Test
  public void test10(){
	 LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	 String actual = ld.toDateTimeAtStartOfDay(null).toString();
     String expected = "2000-09-30T00:00:00.000-04:00";
     assertEquals(actual,expected);
  }*/
    @Test
  public void test11(){
	 LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	 String actual = ld.withLocalMillis(ISOChronology.getInstanceUTC().getDateTimeMillis(2010, 10, 01, 01)).toString();
     String expected = "2010-10-01";
     assertEquals(actual,expected);
  }
  /* TimeDepend  @Test
  public void test12(){   
	DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	LocalDate ld = new LocalDate(dt);
	long actual = ld.getLocalMillis();
    long expected = Long.valueOf("970272000000").longValue();
    assertEquals(actual,expected);
  }*/
    @Test
  public void test13(){   
	DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	LocalDate ld = new LocalDate(dt, GregorianChronology.getInstanceUTC());
	long actual =  ld.getLocalMillis();
    long expected = Long.valueOf("970358400000").longValue();
    assertEquals(actual,expected);
  }
    @Test
  public void test14(){   
	DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	LocalDate ld = new LocalDate(dt, GregorianChronology.getInstanceUTC());
	boolean actual =  ld.isSupported(DurationFieldType.seconds());
    boolean expected = false;
    assertEquals(actual,expected);
  }
    @Test
  public void test15(){
	 LocalDate ld = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	 boolean actual = ld.isSupported(DurationFieldType.millis());   
     boolean expected = false;
     assertEquals(actual,expected);
  }
  /* TimeDepend  @Test
  public void test16(){
	LocalDate ld = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	String actual = ld.toDateTimeAtStartOfDay(null).toString();
    String expected = "2000-10-01T00:00:00.000-04:56:02";
    assertEquals(actual,expected);
  }*/
    @Test
  public void test17(){   
	LocalDate ld = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	String actual =  ld.withLocalMillis(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01)).toString();
    String expected = "2000-10-01";
    assertEquals(actual,expected);
  }
  /* TimeDepend @Test
  public void test18(){   
	DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	LocalDate ld = new LocalDate(dt, DateTimeZone.getDefault());
	long actual = ld.getLocalMillis();
    long expected = Long.valueOf("970272000000").longValue();
    assertEquals(actual,expected);
  }
    @Test
  public void test19(){   
	DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), BuddhistChronology.getInstanceUTC());
	LocalDate ld = new LocalDate(dt, DateTimeZone.getDefault());
	long actual = ld.getLocalMillis();
    long expected = Long.valueOf("-16164403200000").longValue();
    assertEquals(actual,expected);
  }*/

  //------------------------ PCI ----------------------------------------------------------------------------------

  //------------------------ EAM ----------------------------------------------------------------------------------	
@Test
public void test20(){   // EAM-1,2,10-21 (ok)
	 LocalDate ld1 = new LocalDate();
     SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
     String dateStringToParse = "2007-07-12"; 
	 try{
		 d1 = bartDateFormat.parse(dateStringToParse); 
	 }
	 catch (Exception e){};
	 String actual =  ld1.fromDateFields(d1).toString();   //P-5
     String expected = "2007-07-12";
     assertEquals(actual,expected);
  }
/*@Test
  public void test21(){   // EAM_23-25 + EAM_27-30 + EAM_32-52 + OMR_2 (ok)
	   LocalDate ld1 = new LocalDate();
       DateTimeZone dtz = dtu.getZone(null);
	   String actual = ld1.toDateTimeAtMidnight(dtz).toString(); //P22
       String expected = "2011-07-08T00:00:00.000-04:00";
       assertEquals(actual,expected);
  }*/
   /* @Test
  public void test22(){   // EAM_53-55 + EAM_57-82 + OMR_4 (ok)
	 LocalDate ld1 = new LocalDate();
      DateTimeZone dtz = dtu.getZone(null);
	   DateMidnight actual = ld1.toDateMidnight(dtz); //P25
      DateMidnight expected = new DateMidnight(2011,7,8,null);
     assertEquals(actual,expected);
  }*/
/* TimeDepend @Test
public void test23(){   // EAM_23-25 + EAM_27-30 + EAM_32-52 + OMR_2 (ok)
	   LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 01, 01, 00));
       DateTimeZone dtz = dtu.getZone(null);
	   String actual = ld1.toDateTimeAtMidnight(dtz).toString(); //P22
       String expected = "1999-12-31T00:00:00.000-05:00";
       assertEquals(actual,expected);
  }
    @Test
  public void test24(){   
	   LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(200, 01, 01, 00));
       DateTimeZone dtz = dtu.getZone(null);
	   String actual = ld1.toDateTimeAtMidnight(dtz).toString(); 
       String expected = "-0344-12-26T00:00:00.000-04:56:02";
       assertEquals(actual,expected);
  }*/
  /* TODO: Make this work.
@Test(expected=org.joda.time.IllegalFieldValueException.class)
  public void test25() throws Exception{   
	 LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(200, 01, 01, 00));
	 LocalDate.Property p = new LocalDate.Property(ld1, BuddhistChronology.getInstanceUTC().year());
	 p.withMaximumValue().toString();  
  }
  @Test(expected=java.lang.IllegalArgumentException.class)
  public void test26() throws Exception{ 
	 LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(200, 01, 01, 00));
	 LocalDate.Property p = new LocalDate.Property(ld1, BuddhistChronology.getInstanceUTC().year());
	 p.withMinimumValue().toString();
  }*/
  /* TimeDepend @Test
  public void test27(){   
	 LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 01, 01, 00));
     DateTimeZone dtz = dtu.getZone(null);
	 String actual = ld1.toDateMidnight(dtz).toString(); 
     String expected = "1999-12-31T00:00:00.000-05:00";
     assertEquals(actual,expected);
  }
  @Test
  public void test28(){   
	 LocalDate ld1 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(200, 01, 01, 00));
     DateTimeZone dtz = dtu.getZone(null);
	 String actual = ld1.toDateMidnight(dtz).toString(); 
     String expected = "-0344-12-26T00:00:00.000-04:56:02";
     assertEquals(actual,expected);
  }
  @Test
  public void test29(){   
	LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(1200, 02, 01, 00));
	LocalDate.Property p = new LocalDate.Property(ld1, ISOChronology.getInstanceUTC().dayOfMonth());
	String actual = p.withMaximumValue().toString(); 
    String expected = "1200-01-31";
    assertEquals(actual,expected);
  }

  //------------------------ EAM ----------------------------------------------------------------------------------	

  //------------------------ IOD ----------------------------------------------------------------------------------	
@Test
public void test30(){   
	LocalDate ld = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	int actual = ld.get(DateTimeFieldType.dayOfYear());
    int expected = 274;
    assertEquals(actual,expected);
  }*/
  //------------------------ IOD ----------------------------------------------------------------------------------	
   //------------------------ PPD ----------------------------------------------------------------------------------
   
   //------------------------ PPD ----------------------------------------------------------------------------------
  //------------------------ OMR ----------------------------------------------------------------------------------

//This test case should be dealt separately since it returns the current time so that 
//the result every time returned is different
//But this is the correct test because it causes a stackoverflowerror
/*  public DateTime test31(){   // OMR-3  (ok)
  LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), ISOChronology.getInstanceUTC());
      DateTimeZone dtz = dtu.getZone(null);
	   return ld1.toDateTimeAtCurrentTime(dtz); //P23
  }*/
 
  @Test
  public void test32(){  
  LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), ISOChronology.getInstanceUTC());
      DateTimeZone dtz = dtu.getZone(null);
	  String actual =  ld1.toInterval(dtz).toString(); //P28
      String expected = "2000-10-01T00:00:00.000/2000-10-02T00:00:00.000";
      assertEquals(actual,expected);
  }
  @Test
  public void test33(){   
  LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), ISOChronology.getInstanceUTC());
      String st = "2010-08-08";
	  String actual =  ld1.toString(st); //P54
      String expected = "2010-08-08";
      assertEquals(actual,expected);
  }
  @Test
  public void test34(){  
      LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), ISOChronology.getInstanceUTC());
      String st = "2010-08-08";
	  Locale lc = new Locale("CHINESE");
	  String actual = ld1.toString(st,lc); //P54
      String expected = "2010-08-08";
      assertEquals(actual,expected);
  }
  /*@Test
  public static DateTime test35(){   
      LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01), ISOChronology.getInstanceUTC());
	  DateTimeZone dtz = dtu.getZone(null);
	  LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));		
	  DateTime actual =  ld1.toDateTime(lt,dtz); //P27
      DateTime expected = new DateTime(200,10,1,20,0,0,.001,dtu.getZone(null));
      assertEquals(actual,expected);
  }*/
   //------------------------ OMR ----------------------------------------------------------------------------------

   //-------------------------kill mutants for JSI1-4 operator 
// left: 0

 /* TimeDepend @Test
 public void test36(){   // JSI_3 JSI_1 PCI_3-9 PCI_11-14 PCI_17-23 PCI_25-28 PCI_32-38 PCI_4 PCI_40-43 (ok)
	LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	LocalDate.Property P1 = new LocalDate.Property(ld1,ISOChronology.getInstanceUTC().year());
	LocalDate ld2 = new LocalDate(BuddhistChronology.getInstanceUTC().getDateTimeMillis(1988, 10, 01, 01));
	LocalDate.Property P2 = new LocalDate.Property(ld2, BuddhistChronology.getInstanceUTC().year());
	long actual = P1.getMillis();  //P 57
    long expected = Long.parseLong("970272000000");
    assertEquals(actual,expected);
 }*/

 /*@Test
 public void test37(){   // JSI_4 PCI_3-9 PCI_11-14 PCI_17-23 PCI_25-28 PCI_32-43 (ok)
	LocalDate ld1 = new LocalDate(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
	LocalDate.Property P1 = new LocalDate.Property(ld1,ISOChronology.getInstanceUTC().year());
	LocalDate.Property P2 = new LocalDate.Property(ld1, BuddhistChronology.getInstanceUTC().monthOfYear());
	DateTimeField actual = P1.getField();  //P 57
    DateTimeField expected = 
 }*/
   //------------------------ JSI ----------------------------------------------------------------------------------
}
