/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package coberturaParsing;

import org.apache.commons.digester.*;
import org.apache.commons.digester.xmlrules.*;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.*;

public class XmlParser
{
    /**
     * To generate a matrix using hit counts instead of a binary true/false, 
     * change any place these variables apear to the getNumberOfHits method call
     */
    private static int HAS_BEEN_HIT = 1;
    private static int HAS_NOT_BEEN_HIT = 0;
    private static String filename;

    public static void main(String[] args)
    {
        String settingsFilename = "settings.out";

        /**
         * Setup apache digester and parse the xml file passed as args[0] using 
         * rules passed as args[1]
         */
        try
        {
            filename = args[0];
            File input = new File(filename + ".xml");
            System.out.println(filename + ".xml");
            File rules = new File("rules.xml");
            System.out.println(args[1]);
            System.out.println("Files in");

            Digester digester = DigesterLoader.createDigester(
                (rules.toURI()).toURL());
            System.out.println("File Converted");
            Report report = (Report) digester.parse(input);
            System.out.println("Objects Created");
            report.update();
            proteja.Settings set = retrieveFromXML(settingsFilename);
            System.out.println(report.toString());

            String coverage = set.getCoverageType();
            if (coverage.equals("class"))
            {
                createClassCoverage(report);
            }
            else
            {
                if (coverage.equals("method"))
                {
                    createMethodCoverage(report);
                }
                else
                {
                    if (coverage.equals("line"))
                    {
                        createLineCoverage(report);
                    }
                    else
                    {
                        System.out.println("Invalid coverage type. Valid types"+
                                " are: 'class', 'method', 'line'.");
                        System.exit(1);
                    }
                }
            }
        } catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }

    /**
     * Create a coverage array for the current file using class coverage as the
     * requirements.
     */
    public static void createClassCoverage(Report report)
    {
        /**
         * Count the number of classes.
         */
        Iterator packages = (report.getPackages().iterator());
        Iterator classes;
        int count = 0;
        while (packages.hasNext())
        {
            classes = ((PackageGroup) packages.next()).getClasses().iterator();
            while (classes.hasNext())
            {
                count++;
                classes.next();
            }
        }

        /**
         * Place a 1 for each class that is covered and a 0 for each class that
         * is not covered.
         */
        int[] array = new int[count];
        int currentClass = 0;
        packages = report.getPackages().iterator();
        while (packages.hasNext())
        {
            classes = ((PackageGroup) packages.next()).getClasses().iterator();
            while (classes.hasNext())
            {
                if (((Classs) classes.next()).getIsHit())
                {
                    array[currentClass] = HAS_BEEN_HIT;
                }
                else
                {
                    array[currentClass] = HAS_NOT_BEEN_HIT;
                }
                currentClass++;
            }
        }

        /**
         * Write array to disk.
         */
        try
        {
            System.out.println("Writing file!");
            storeToXML(array, filename + ".out");
        } catch (IOException e)
        {
            System.out.println("There was a problem writing the arrays.!");
        }

    }

    /**
     * Create a coverage array for the current file using method coverage as the
     * requirements.
     */
    public static void createMethodCoverage(Report report)
    {
        /**
         * Count the number of methods.
         */
        Iterator packages = (report.getPackages().iterator());
        Iterator classes, methods;
        int count = 0;
        while (packages.hasNext())
        {
            classes = ((PackageGroup) packages.next()).getClasses().iterator();
            while (classes.hasNext())
            {
                methods = ((Classs) classes.next()).getMethods().iterator();
                while (methods.hasNext())
                {
                    count++;
                    methods.next();
                }
            }
        }

        /**
         * Place a 1 for each method that is covered and a 0 for each class that
         * is not covered.
         */
        int[] array = new int[count];
        int currentMethod = 0;
        packages = (report.getPackages().iterator());
        while (packages.hasNext())
        {
            classes = ((PackageGroup) packages.next()).getClasses().iterator();
            while (classes.hasNext())
            {
                methods = ((Classs) classes.next()).getMethods().iterator();
                while (methods.hasNext())
                {
                    if (((Methodd) methods.next()).getIsHit())
                    {
                        array[currentMethod] = HAS_BEEN_HIT;
                    }
                    else
                    {
                        array[currentMethod] = HAS_NOT_BEEN_HIT;
                    }
                    currentMethod++;
                }
            }
        }

        /**
         * Write the array to disk.
         */
        try
        {
            storeToXML(array, filename + ".out");
        } catch (IOException e)
        {
            System.out.println("There was a problem writing the arrays");
            System.exit(1);
        }
    }

    /**
     * Create a coverage array for the current file using line coverage as the
     * requirements.
     */
    public static void createLineCoverage(Report report)
    {
        /**
         * Count number of lines.
         */
        Iterator packages = report.getPackages().iterator();
        Iterator classes, methods, lines;
        int count = 0;
        while (packages.hasNext())
        {
            classes = ((PackageGroup) packages.next()).getClasses().iterator();
            while (classes.hasNext())
            {
                methods = ((Classs) classes.next()).getMethods().iterator();
                while (methods.hasNext())
                {
                    lines = ((Methodd) methods.next()).getLines().iterator();
                    while (lines.hasNext())
                    {
                        count++;
                        lines.next();
                    }
                }
            }
        }

        /**
         * Place a 1 for each line that is covered and a 0 for each class that
         * is not covered.
         */
        int[] array = new int[count];
        int currentLine = 0;
        packages = report.getPackages().iterator();
        while (packages.hasNext())
        {
            classes = ((PackageGroup) packages.next()).getClasses().iterator();
            while (classes.hasNext())
            {
                methods = ((Classs) classes.next()).getMethods().iterator();
                while (methods.hasNext())
                {
                    lines = ((Methodd) methods.next()).getLines().iterator();
                    while (lines.hasNext())
                    {
                        if (((Line) lines.next()).getIsHit())
                        {
                            array[currentLine] = HAS_BEEN_HIT;
                        }
                        else
                        {
                            array[currentLine] = HAS_NOT_BEEN_HIT;
                        }
                        currentLine++;
                    }
                }
            }
        }

        /**
         * Write array to disk.
         */
        try
        {
            System.out.println("Writing File!");
            storeToXML(array, filename + ".out");
        } catch (IOException e)
        {
            System.out.println("There was a problem writing the arrays");
            System.exit(1);
        }
    }

    /**
     * Writes the coverage array to disk.
     */
    public static void storeToXML(int[] array, String name) throws IOException
    {
        XStream xstream = new XStream();
        String str = xstream.toXML(array);
        FileWriter fileOut = new FileWriter(name);
        char[] a = new char[str.length()];
        str.getChars(0, str.length(), a, 0);
        for (int i = 0; i < a.length; i++)
        {
            fileOut.write(a[i]);
        }
        fileOut.close();
    }

    /**
     * Reads the settings file from disk.
     */
    public static proteja.Settings retrieveFromXML(String name)
        throws IOException
    {
        XStream xstream = new XStream();
        FileReader fileIn = new FileReader(name);
        BufferedReader read = new BufferedReader(fileIn);
        String complete = "";
        String temp;
        while ((temp = read.readLine()) != null)
        {
            complete = complete + temp;
        }
        proteja.Settings config = (proteja.Settings) xstream.fromXML(complete);
        return config;
    }
}
