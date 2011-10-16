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

public class DefaultNameProviderTest{
/*	public boolean test1(){
		DefaultNameProvider dnp = new DefaultNameProvider();
		return dnp.getIByLocaleCache() == null;
	}*/

	public static void main(String[] args){
	DefaultNameProvider dnp = new DefaultNameProvider();
	/*System.out.println(dnp.getIByLocaleCache() == null);
	Set set1 = dnp.getIByLocaleCache().entrySet();
	System.out.println(dnp.createCache().size());*/
	System.out.println(dnp.getName(Locale.UK, "UTC", "UTC"));
	System.out.println(dnp.getShortName(Locale.UK, "UTC", "UTC"));
	System.out.println(dnp.getName(Locale.CHINA, "+08:00", "+08:00"));
	System.out.println(dnp.getName(Locale.US, "EST", "EST"));
	System.out.println(dnp.getName(Locale.US, "CST", "CST"));
	System.out.println(dnp.getName(Locale.US, "HST", "HST"));
	System.out.println(dnp.getName(Locale.US, "HST", "HST"));
	System.out.println(dnp.getName(Locale.US, "HST", "HST"));
	System.out.println(dnp.getName(Locale.US, "HST", "HST"));
	System.out.println(dnp.getName(Locale.US, "HST", "HST"));
	System.out.println(dnp.getName(Locale.US, "MST", "MST"));
	System.out.println(dnp.getName(Locale.US, "PST", "PST"));
	System.out.println(dnp.getName(Locale.US, "MET", "MET"));
	System.out.println(dnp.getName(Locale.US, "WET", "WET"));
	System.out.println(dnp.getName(Locale.US, "EET", "EET"));
	System.out.println(dnp.getName(Locale.US, "EST", "EST"));
	}
}
