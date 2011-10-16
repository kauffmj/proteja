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

public class LocalTimeTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test21: " + test21());
    }*/
//	private java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
//	private LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
/*	private LocalTime lt5 = new LocalTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
	private LocalTime lt1 = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
	private DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
	private LocalTime lt2 = new LocalTime(dt, DateTimeZone.getDefault());
	private LocalTime lt3 = new LocalTime(dt, ISOChronology.getInstanceUTC());
	private LocalTime lt4 = new LocalTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
	private	DateTime dt1 = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
	private LocalTime lt6 = new LocalTime(dt1, GregorianChronology.getInstanceUTC());*/
   //------------------------ EAM ----------------------------------------------------------------------------------

/*	public boolean testeam1(){
		return lt.isSupported(DurationFieldType.millis());
	}*/
/*	public String test1(){   
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		LocalTime.Property p = new LocalTime.Property(lt, BuddhistChronology.getInstanceUTC().year());
		return p.withMaximumValue().toString(); 
	}
    public String test2(){ 
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		LocalTime.Property p = new LocalTime.Property(lt, BuddhistChronology.getInstanceUTC().year());
		return p.withMinimumValue().toString(); 
	}
	public String test3(){   
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		LocalTime.Property p = new LocalTime.Property(lt, ISOChronology.getInstanceUTC().minuteOfHour());
		return p.withMaximumValue().toString(); 
	}
	public String test4(){
		java.util.Date d1 = new Date(1990, 02, 20, 06, 10, 8);
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		return lt.fromDateFields(d1).toString();
	}
	//------------------------ EAM ----------------------------------------------------------------------------------

	//------------------------ IOD ----------------------------------------------------------------------------------
  	public int test5(){  
	LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		return lt.get(DateTimeFieldType.millisOfDay());
	}
	//------------------------ IOD ----------------------------------------------------------------------------------

	//------------------------ OAN ----------------------------------------------------------------------------------
	public long test6(){
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		LocalTime lt2 = new LocalTime(dt, DateTimeZone.getDefault());
		return lt2.getLocalMillis();
	}
	public long test7(){
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		LocalTime lt3 = new LocalTime(dt, ISOChronology.getInstanceUTC());
		return lt3.getLocalMillis();
	}
	//------------------------ OAN ----------------------------------------------------------------------------------

	//------------------------ PCI ----------------------------------------------------------------------------------
	public boolean test8(){
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		LocalTime lt2 = new LocalTime(dt, DateTimeZone.getDefault());
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		return lt.equals(lt2);
	}
	public int test9(){
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		LocalTime lt2 = new LocalTime(dt, DateTimeZone.getDefault());
		return lt2.compareTo(lt);
	}
	public boolean test10(){
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		LocalTime lt2 = new LocalTime(dt, DateTimeZone.getDefault());
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		return lt2.equals(lt);
	}
	public int test11(){
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		LocalTime lt2 = new LocalTime(dt, DateTimeZone.getDefault());
		LocalTime lt = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
		return lt.compareTo(lt2);
	}
	public String test12(){
		LocalTime lt = new LocalTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		return lt.toString();
	}
	public String test13(){
		DateTime dt = new DateTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), ISOChronology.getInstanceUTC());
		LocalTime lt2 = new LocalTime(dt, DateTimeZone.getDefault());
		return lt2.toString();
	}
	public String test14(){
		DateTime dt = new DateTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), BuddhistChronology.getInstanceUTC());
		LocalTime lt = new LocalTime(dt, BuddhistChronology.getInstanceUTC());
		return lt.toString();
	}
	
	//------------------------ PCI ----------------------------------------------------------------------------------


//-------------------------kill mutants for JSI1-4 operator 
// result:
//    test 1  kill  ==> JSI_1 JSI_3 PCI_10 
//    test 2  kill  ==> JSI_2 
//    test 3  kill  ==> 
//    test 4  kill  ==> JSI_4 
// left: 0 
// kill others:: PCI


 public long test15(){ // JSI_1 JSI_3 PCI_10
   LocalTime lt1 = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 10),ISOChronology.getInstanceUTC());
   LocalTime lt2 = new LocalTime(BuddhistChronology.getInstanceUTC().getDateTimeMillis(2010, 11, 11, 8, 10, 10, 00),BuddhistChronology.getInstanceUTC());
   LocalTime.Property p1 = new LocalTime.Property(lt1, ISOChronology.getInstanceUTC().minuteOfHour());
   LocalTime.Property p2 = new LocalTime.Property(lt2, BuddhistChronology.getInstanceUTC().hourOfDay());
   return  p1.getMillis();  
 }

 public DateTimeField test16(){ // JSI-4
    LocalTime ld1 = new LocalTime(ISOChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
    LocalTime.Property P1 = new LocalTime.Property(ld1,ISOChronology.getInstanceUTC().year());
    LocalTime.Property P2 = new LocalTime.Property(ld1, BuddhistChronology.getInstanceUTC().monthOfYear());
    return  P1.getField();  
 }


//------------------------ JSI ----------------------------------------------------------------------------------


//------------------------ OMR 1-5----------------------------------------------------------------------------------
//  result: mujava can not normally work to the end
//  left: 0
//  equ1valent: OMR5
//  kill others: EAM,OAN,IOD

 public LocalTime test17(){// OMR1 EAM_23 IOD_5 OAN(12) PCI(25)
    DateTime dt11 = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90));
    LocalTime lt11 = new LocalTime(dt11, GregorianChronology.getInstanceUTC());
    return lt11.fromMillisOfDay(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 8, 10, 10, 90), GregorianChronology.getInstanceUTC());
}

  public DateTime test18(){// OMR_2 OAN_25 OAN_26 OAN_27 OAN_28 OAN_29 OAN_30 OAN_31 OAN_32 OAN_33 OAN_34 OAN_35 OAN_36 OAN_37 OAN_38 OAN_39 OAN_40 OAN_41 OAN_42 OAN_43 OAN_44 OAN_45 OAN_46 OAN_47 PCI_100 PCI_101 PCI_105 PCI_106 PCI_108 PCI_109 PCI_110 PCI_111 PCI_112 PCI_113 PCI_114 PCI_115 PCI_116 PCI_120 PCI_121 PCI_123 PCI_124 PCI_125 PCI_126 PCI_127 PCI_128 PCI_129 PCI_130 PCI_131 PCI_60 PCI_61 PCI_63 PCI_64 PCI_65 PCI_66 PCI_67 PCI_68 PCI_69 PCI_70 PCI_71 PCI_75 PCI_76 PCI_78 PCI_79 PCI_80 PCI_81 PCI_82 PCI_83 PCI_84 PCI_85 PCI_86 PCI_90 PCI_91 PCI_93 PCI_94 PCI_95 PCI_96 PCI_97 PCI_98 PCI_99 
    DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
    LocalTime lt = new LocalTime(dt, GregorianChronology.getInstanceUTC());  
    return lt.toDateTimeToday();
 }

 public String test19(){// OMR_3 OAN_25 OAN_26 OAN_27 OAN_28 OAN_29 OAN_30 OAN_31 OAN_32 OAN_33 OAN_34 OAN_35 OAN_36 OAN_37 OAN_38 OAN_39 OAN_40 OAN_41 OAN_42 OAN_43 OAN_44 OAN_45 OAN_46 OAN_47  PCI_100 PCI_101 PCI_105 PCI_106 PCI_108 PCI_109 PCI_110 PCI_111 PCI_112 PCI_113 PCI_114 PCI_115 PCI_116 PCI_120 PCI_121 PCI_123 PCI_124 PCI_125 PCI_126 PCI_127 PCI_128 PCI_129 PCI_130 PCI_131 PCI_60 PCI_61 PCI_63 PCI_64 PCI_65 PCI_66 PCI_67 PCI_68 PCI_69 PCI_70 PCI_71 PCI_75 PCI_76 PCI_78 PCI_79 PCI_80 PCI_81 PCI_82 PCI_83 PCI_84 PCI_85 PCI_86 PCI_90 PCI_91 PCI_93 PCI_94 PCI_95 PCI_96 PCI_97 PCI_98 PCI_99 
   DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 02, 01, 01));
   LocalTime lt = new LocalTime(dt, GregorianChronology.getInstanceUTC()); 
   String st = "2010-08-08";
   return lt.toString(st);
 }

 public String test20(){// OMR_4 OAN_25 OAN_26 OAN_27 OAN_28 OAN_29 OAN_30 OAN_31 OAN_32 OAN_33 OAN_34 OAN_35 OAN_36 OAN_37 OAN_38 OAN_39 OAN_40 OAN_41 OAN_42 OAN_43 OAN_44 OAN_45 OAN_46 OAN_47 PCI_100 PCI_101 PCI_105 PCI_106 PCI_108 PCI_109 PCI_110 PCI_111 PCI_112 PCI_113 PCI_114 PCI_115 PCI_116 PCI_120 PCI_121 PCI_123 PCI_124 PCI_125 PCI_126 PCI_127 PCI_128 PCI_129 PCI_130 PCI_131 PCI_60 PCI_61 PCI_63 PCI_64 PCI_65 PCI_66 PCI_67 PCI_68 PCI_69 PCI_70 PCI_71 PCI_75 PCI_76 PCI_78 PCI_79 PCI_80 PCI_81 PCI_82 PCI_83 PCI_84 PCI_85 PCI_86 PCI_90 PCI_91 PCI_93 PCI_94 PCI_95 PCI_96 PCI_97 PCI_98 PCI_99 
   DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
   LocalTime lt = new LocalTime(dt, GregorianChronology.getInstanceUTC());  
   Locale lc = new Locale("CHINESE");
   String st = "2010-08-08";
   return lt.toString(st,lc);
 }*/
  @Test
  public void test21(){
   DateTime dt = new DateTime(GregorianChronology.getInstanceUTC().getDateTimeMillis(2000, 10, 01, 01));
   LocalTime lt = new LocalTime(dt, GregorianChronology.getInstanceUTC());  
   String actual = lt.MIDNIGHT.plusMillis(222).toString();
   String expected = "00:00:00.222";
   assertEquals(actual,expected);
 }

//------------------------ OMR ----------------------------------------------------------------------------------

/*	public static void main(String[] args){
		// created for EAM 24
		DurationFieldType range = DateTimeFieldType.millisOfDay().getDurationType();
		DurationFieldType range1 = DateTimeFieldType.millisOfDay().getRangeDurationType();
		System.out.println(range);
		System.out.println(range1);
		System.out.println(lt.isSupported(range));
		System.out.println(range == DurationFieldType.days());
		System.out.println(lt.isSupported(range1));
		System.out.println(range1 == DurationFieldType.days());
	}
*/	
}
