/**
 * ModifyTestSuite.java
 * 
 * Reads the modified test case list and modifies the settings.out file so the 
 * test suite executes according to the modification.
 * 
 * Jonathan Miller Kauffman
*/

package proteja;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class ModifyTestSuite
{
	public static void main(String[] args)
	{
		// read in the settings
		Settings set = TestProcessor.readSettings("settings.out");
      	System.out.println("Settings in.");

		FileReader fileIn = null;
		try
        {
		    fileIn = new FileReader(args[0]);
		} catch(FileNotFoundException e)
        {
			System.out.println("File not found.");
		}
        BufferedReader read = new BufferedReader(fileIn);
        String temp;
		ArrayList<PMethod> list = new ArrayList<PMethod>();
	
        System.out.println("Reading file");
		PMethod method = new PMethod();
		try
        {
        	while ((temp = read.readLine()) != null)
            {
		        int lastPeriod = temp.lastIndexOf(".");
		        method.setClazz(Class.forName(temp.substring(0,lastPeriod)));
        	    method.setMethodName(temp.substring(lastPeriod+1));
		        list.add(method);
		        method = new PMethod();
        	}
		} catch(IOException e)
        {
			System.out.println("There was a problem reading the file.");
		}
		catch(ClassNotFoundException e)
        {
			System.out.println("The class could not be found.");
		}

		set.setMethodList(list);
		set.setListType("include");

		CreateSettings.storeSettings(set, "settings.out");
	}
}
