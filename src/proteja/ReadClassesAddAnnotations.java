/**
 * ReadClassesAddAnnotations.java
 * 
 * Reads the list of classes from settings.out and adds JUnit
 * annotations to each of them using AnnotationAdderASM.
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

public class ReadClassesAddAnnotations
{
    public static void main(String[] args)
    {
        // read in the settings
        Settings set = TestProcessor.readSettings("settings.out");
          System.out.println("Settings in.");
        
        // go through each class
        for(Class clazz : set.getTestClasses())
        {
            Runtime rt = null;
            File file = null;
            Process proc = null;
            InputStreamReader reader = null, reader2 = null;
            BufferedReader buf_reader = null, buf_reader2 = null;
            try
            {        
                rt = Runtime.getRuntime();
                String[] envp = new String[1];
                envp[0] = "CLASSPATH=" +
                    System.getProperty("java.class.path");

                // run AnnotationAdder
                proc = rt.exec("java proteja.AnnotationAdderASM " +
                    clazz.getName() + " " + args[0], envp);
                reader = new InputStreamReader(proc.getInputStream());
                buf_reader = new BufferedReader(reader);
    
                String line;        
                while((line = buf_reader.readLine()) != null)
                    System.out.println(line);
    
                reader2 = new InputStreamReader(
                    proc.getErrorStream());
                buf_reader2 = new BufferedReader(reader2);
    
                String line2;
                while((line2 = buf_reader2.readLine()) != null)
                    System.out.println(line2);
            
                // close readers and the process
                reader.close();
                buf_reader.close();
                proc.destroy();
                reader2.close();
                buf_reader2.close();
    
            } catch(IOException e)
            {
                System.out.println(e);
            }
            catch(NullPointerException e)
            {
                System.out.println(e);
            }
        }
        System.out.println("Annotations added.");
    }    
}
