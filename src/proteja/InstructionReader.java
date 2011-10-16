/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package proteja;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

public class InstructionReader
{
    private static JUnitCore runner;
    private static File results; // where to store timing information
    private static long time1, time2; // before and after times
    private static Writer out;

    public static void main(String[] args)
    {
        runner = new JUnitCore();
	    results = new File(args[5] + "_Timing.dat");

        InstructionBlock instructions = readInstructionBlock("instructions.out");
        for (Instruction ins : instructions.getInstructions())
        {
            if (ins instanceof SetupMethod)
            {
                runNonTestMethod(ins);
            }
            else if (ins instanceof TeardownMethod)
            {
                runNonTestMethod(ins);
            }
            else if (ins instanceof Test)
            {
                runTestMethod(ins, args[1], args[2], args[3], args[4], args[5]);
            }
        }
        if (args[0].equals("true"))
        {
            net.sourceforge.cobertura.coveragedata.ProjectData.saveGlobalProjectData();
        }
    }

    public static void runNonTestMethod(Instruction ins)
    {
        try
        {
            ins.getMethod().invoke(ins);
        } catch (Exception e)
        {
            System.out.println("There was a problem invoking a method");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void runTestMethod(Instruction ins, String timer, String batchFactor, String coverageType, String resetJVMOnBatch, String applicationName)
    {
        Request req = Request.method(ins.getClazz(), ins.getMethod().getName());

	    // get the starting time if the test timer is to be run
	    if(timer.equals("true"))
	    {
		    time1 = System.nanoTime();
	    }

        Result result = runner.run(req);

	    // get the ending time and write the method name, execution time, batch factor, coverage type, JVM restart, application name,
        // and pass/fail information to a file

	    if(timer.equals("true"))
	    {
		    time2 = System.nanoTime();

		    try
            {
			    out = new BufferedWriter(new FileWriter(results, true));
			    out.write(ins.getMethod().getDeclaringClass().getName() + "." + ins.getMethod().getName() + "\t" +Long.toString(time2 - time1) + 
				    "\t" + batchFactor + "\t" + coverageType + "\t" + resetJVMOnBatch + "\t" + applicationName 
				    + "\t" + (!result.wasSuccessful()) + "\n");
			    out.flush();
		    } catch(FileNotFoundException e){}
		      catch(IOException e){}
        }

	    // the test did not fail
	    if(result.getFailureCount() == 0)
		    System.out.println(ins.getMethod().getName() + " passed.");

	    // print any failures for the current test case
	    else
	    {
		    for(Failure fail : result.getFailures())
			    System.out.println(fail.getMessage());
	    }		
    }

    /**
     * Read the Instruction block from disk.
     */
    public static InstructionBlock readInstructionBlock(String filepath)
    {
        try
        {
            XStream xstream = new XStream();
            FileReader fileIn = new FileReader(filepath);
            BufferedReader read = new BufferedReader(fileIn);
            String complete = "";
            String temp;
            while ((temp = read.readLine()) != null)
            {
                complete = complete + temp;
            }
            InstructionBlock instructions = (InstructionBlock) xstream.fromXML(complete);
            return instructions;
        } catch (IOException exc)
        {
            System.out.println("There was a problem reading the instructions file");
            exc.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
