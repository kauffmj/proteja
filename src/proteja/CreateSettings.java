/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package proteja;

import com.thoughtworks.xstream.XStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateSettings
{
    public static void main(String[] args)
    {
        Settings set = new Settings();
        set.setBatchFactor(1);
        set.setCoverageType("line");
        ArrayList<Class> testClasses = new ArrayList<Class>();
        testClasses.add(proteja.Setup.class);
        set.setTestClasses(testClasses);
        set.setResetJVMOnBatch(true);
        storeSettings(set, "settings.out");
    }

    /**
     * Writes the Settings to disk.
     */
    public static void storeSettings(Settings set, String name)
    {
        try
        {
            XStream xstream = new XStream();
            String str = xstream.toXML(set);
            FileWriter fileOut = new FileWriter(name);
            char[] a = new char[str.length()];
            str.getChars(0, str.length(), a, 0);
            for (int i = 0; i < a.length; i++)
            {
                fileOut.write(a[i]);
            }
            fileOut.close();
        } catch (IOException exc)
        {
            System.out.println("There was a problem writing the " +
                "file to disk!");
            exc.printStackTrace();
        }
    }
}
