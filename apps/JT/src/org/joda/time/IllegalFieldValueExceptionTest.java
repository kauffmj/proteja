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

public class IllegalFieldValueExceptionTest extends TestCase
{

    /*public static void main(String[] args)
    {
        System.out.println("test1: " + test1());
        System.out.println("test2: " + test2());
        System.out.println("test3: " + test3());
        //System.out.println("test4: " + test4());
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
	//------------------------ IOD ----------------------------------------------------------------------------------
    @Test
	public void test1(){ // IOD_1 IPC_2 ISD_2 OMR_1 OMR_2 
		IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),1,"problem");
		le1.prependMessage("66");
		String actual = le1.getMessage();  
        String expected = "66: Value 1 for dayOfYear is not supported: problem";
        assertEquals(actual,expected);
	 }
	//------------------------ IOD ----------------------------------------------------------------------------------

	//------------------------ PRV ----------------------------------------------------------------------------------
	//-------------------------kill mutants for PRV operator ---(ok)- 31, 32 LEFT
     @Test
	 public void test2(){ // PRV_4 
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),1,2,365);
	   Number actual =  le1.getLowerBound();  
       Number expected = new Integer(2);
       assertEquals(actual.intValue(),expected.intValue());
	 }
	 
    @Test
	 public void test3(){ // PRV_5 PRV_6 
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),1,2,365);
	   Number actual = le1.getUpperBound();  
       Number expected = new Integer(365);
       assertEquals(actual.intValue(),expected.intValue());
	 }
	 
    @Test
	 public void test4(){ // PRV_12
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
	   Number actual = le1.getLowerBound();  
       Number expected = new Integer(2);
       assertEquals(actual.intValue(),expected.intValue());
	 }
	 @Test
	 public void test5(){ // PRV_13 PRV_14
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
	   Number actual = le1.getUpperBound();  
       Number expected = new Integer(365);
       assertEquals(actual.intValue(),expected.intValue());
	 }
	 @Test
	 public void test6(){ // PRV_16
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
		IllegalFieldValueException le2 = new IllegalFieldValueException(le1.getFieldName(),1,2,365);
	   String actual = le2.getFieldName();  
        String expected = "seconds";
        assertEquals(actual,expected);
	 }
    @Test
	 public void test7(){ // PRV_20
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
		IllegalFieldValueException le2 = new IllegalFieldValueException(le1.getFieldName(),1,2,365);
	   Number actual = le2.getLowerBound();  
       Number expected = new Integer(2);
       assertEquals(actual.intValue(),expected.intValue());
	 }
    @Test
	 public void test8(){ // PRV_21 PRV_22
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
		IllegalFieldValueException le2 = new IllegalFieldValueException(le1.getFieldName(),1,2,365);
	   Number actual =  le2.getUpperBound();  
       Number expected = new Integer(365);
       assertEquals(actual.intValue(),expected.intValue());
	 }
    @Test
	 public void test9(){ // PRV_23 PRV_24
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),"3");
	   String actual = le1.getIllegalStringValue();  
       String expected = "3";
       assertEquals(actual,expected);
	 }
    @Test
	public void test10(){ // PRV_25 PRV_26
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),"1");
	   String actual = le1.getIllegalStringValue();  
       String expected = "1";
       assertEquals(actual,expected);
	 }
    @Test	 
	 public void test11(){ // PRV_28
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),"1");
		IllegalFieldValueException le2 = new IllegalFieldValueException(le1.getFieldName(),"1");
	   String actual = le2.getFieldName();   
       String expected = "seconds";
       assertEquals(actual,expected);
	 }
    @Test
	public void test12(){ // PRV_29 PRV_30
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
		IllegalFieldValueException le2 = new IllegalFieldValueException(le1.getFieldName(),"2");
	   String actual = le2.getIllegalStringValue();  
        String expected = "2";
       assertEquals(actual,expected);
	}
	 // for PRV_31 PRV_32, but failed  they are equialent
	/*public String test29(){
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),1,null);
		le1.prependMessage("66");
	   return le1.getMessage();  
	}*/
	//------------------------ PRV ----------------------------------------------------------------------------------
	//------------------------ JSI ----------------------------------------------------------------------------------
	//-------------------------kill mutants for JSI1 operator ----(OK)
	// left: 0
	// equialent: 2 
	// kill others:: IPC(1),OMR(2),ISD(1)

    @Test
	 public void test13(){ // JSI_8 IPC_2 ISD_2 OMR_1 OMR_2
	   IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),1,"problem");
		IllegalFieldValueException le2 = new IllegalFieldValueException(DateTimeFieldType.monthOfYear(),2,"problem");
	   String actual = le1.getMessage();  
       String expected = "Value 1 for dayOfYear is not supported: problem";
        assertEquals(actual,expected);
	 }
	//------------------------ JSI ----------------------------------------------------------------------------------
	//------------------------ ISD ----------------------------------------------------------------------------------
//-------------------------kill mutants for ISD 1-7 operator---(OK)---

// left:  0
// kill others:: IPC(7),OMR(2)

@Test
public void test14(){ // ISD_1 IPC_1  OMR_1 OMR_2 
   IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),1,2,365);
   String actual = le1.getMessage();  
   String expected = "Value 1 for dayOfYear must be in the range [2,365]";
   assertEquals(actual,expected);
 }
@Test
 public void test15(){ // ISD_3 IPC_3  OMR_1 OMR_2 
   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
   String actual = le1.getMessage();  
   String expected = "Value 1 for seconds must be in the range [2,365]";
   assertEquals(actual,expected);
 }
 @Test
 public void test16(){ // ISD_4 IPC_4  OMR_1 OMR_2 
   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
	IllegalFieldValueException le2 = new IllegalFieldValueException(le1.getFieldName(),1,2,365);
   String actual = le2.getMessage();  
    String expected = "Value 1 for seconds must be in the range [2,365]";
    assertEquals(actual,expected);
 }
@Test
 public void test17(){ // ISD_5 IPC_5 OMR_1 OMR_2
   IllegalFieldValueException le1 = new IllegalFieldValueException(DateTimeFieldType.dayOfYear(),"problem");
   String actual = le1.getMessage();  
   String expected = "Value \"problem\" for dayOfYear is not supported";
   assertEquals(actual,expected);
 }
 @Test
 public void test18(){ // ISD_6 IPC_6  OMR_1 OMR_2 
   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),"problem");
   String actual = le1.getMessage();  
   String expected = "Value \"problem\" for seconds is not supported";
   assertEquals(actual,expected);
 }
 @Test
 public void test19(){ // ISD_7 IPC_7  OMR_1 OMR_2 
   IllegalFieldValueException le1 = new IllegalFieldValueException(DurationFieldType.seconds(),1,2,365);
	IllegalFieldValueException le2 = new IllegalFieldValueException(le1.getFieldName(),"problem");
   String actual = le2.getMessage();
   String expected = "Value \"problem\" for seconds is not supported";
   assertEquals(actual,expected);  
 }
	//------------------------ ISD ----------------------------------------------------------------------------------


/*	public static void main(String[] args){
		  IllegalFieldValueException le1 = new IllegalFieldValueException(null, null,null);
	    le1.prependMessage(null);  
		System.out.println(le1.getMessage());
		System.out.println(le1.getFieldName());
		System.out.println(le1.getIllegalStringValue());
	}*/
}
