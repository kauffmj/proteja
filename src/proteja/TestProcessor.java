/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package proteja;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class TestProcessor
{
    private static Settings set;
    private static int groupNumber = 1; //for coberturaFileManip
    private static boolean cobertura = false;
    private static boolean testTimer = false;
    private static String applicationName = "default";

    public static void main(String[] args)
    {
        if(args[0].equals("true"))
            cobertura = true;
        if(args[1].equals("true"))
            testTimer=true;
        applicationName = args[2];

        set = readSettings("settings.out");
        System.out.println("Settings in.");

        ArrayList<InstructionBlock> runList = createRunList();
        beginningOfProgramFileManip();
        for (InstructionBlock block : runList)
        {
            beforeFileManip();
            LaunchBlock(block, args);
            afterFileManip();
        }
        endOfProgramFileManip();
    }

    /**
     * This method creates an arraylist of instructionBlock's listing what
     * methods to run in what order.  Each instructionBlock is designed to run
     * in its own jvm. So if reset jvm=false there should only be one
     * instructionBlock, and if reset jvm=true, there will be one instruction
     * block for each batch. An instructionBlock is just an arrayList of
     * instructions. An instruction can be a test method, setupClass method or
     * teardownClass method. JUnit adds the Setup and teardown for methods.
     *
     * Modified by Jonathan Miller Kauffman.
     *
     * After changes to the createRunList method, need to check the class of the
     * Before and After methods.  Also, there is no longer any need to loop
     * through the test classes.
     */
    public static ArrayList<InstructionBlock> createRunList()
    {
        ArrayList<InstructionBlock> runList = new ArrayList<InstructionBlock>();
        int batchFactor = set.getBatchFactor();
        if (set.getResetJVMOnBatch())
        {
            TestClass info = populateContents(set.getTestClasses());
            Iterator iterator = info.getTestMethods().iterator();
            int startTest = 1;
            int currentTest = 1;
            while (iterator.hasNext())
            {
                InstructionBlock instructions = new InstructionBlock();
                ArrayList<Instruction> current = new ArrayList<Instruction>();

                for (startTest = currentTest; currentTest < startTest + 
                    batchFactor && iterator.hasNext(); currentTest++)
                {
                    Method method = (Method) iterator.next();
                    Class clazz = method.getDeclaringClass();

                    for (Method m : info.getBeforeMethods())
                    {
                        if(clazz.getName().equals(
                            m.getDeclaringClass().getName()))
                        {
                            SetupMethod start = new SetupMethod();
                            start.setClazz(clazz);
                            start.setMethod(m);
                            current.add(start);
                        }
                    }

                    Test test = new Test();
                    test.setClazz(clazz);
                    test.setMethod(method);
                    current.add(test);

                    for (Method m : info.getAfterMethods())
                    {
                        if(clazz.getName().equals(
                            m.getDeclaringClass().getName()))
                        {
                            TeardownMethod end = new TeardownMethod();
                            end.setClazz(clazz);
                            end.setMethod(m);
                            current.add(end);
                        }
                    }
                }
                instructions.setInstructions(current);
                runList.add(instructions);
            }
        }
        else
        {
            InstructionBlock instructions = new InstructionBlock();
            ArrayList<Instruction> current = new ArrayList<Instruction>();
            TestClass info = populateContents(set.getTestClasses());
            Iterator iterator = info.getTestMethods().iterator();
            int startTest = 1;
            int currentTest = 1;
            while (iterator.hasNext())
            {
                for (startTest = currentTest; currentTest < startTest +
                    batchFactor && iterator.hasNext(); currentTest++)
                {
                    Method method = (Method) iterator.next();
                    Class clazz = method.getDeclaringClass();

                    for (Method m : info.getBeforeMethods())
                    {
                        if(clazz.getName().equals(
                            m.getDeclaringClass().getName()))
                        {
                            SetupMethod start = new SetupMethod();
                            start.setClazz(clazz);
                            start.setMethod(m);
                            current.add(start);
                        }
                    }

                    Test test = new Test();
                    test.setClazz(clazz);
                    test.setMethod(method);
                    current.add(test);

                    for (Method m : info.getAfterMethods())
                    {
                        if(clazz.getName().equals(
                            m.getDeclaringClass().getName()))
                        {
                            TeardownMethod end = new TeardownMethod();
                            end.setClazz(clazz);
                            end.setMethod(m);
                            current.add(end);
                        }
                    }
                }
            }

            instructions.setInstructions(current);
            runList.add(instructions);
        }
        return runList;
    }

    /**
     * This method handles any exclusions or ordering asked for in settings.out.
     *
     * Modified by Jonathan Miller Kauffman.
     *
     * Before this method would only consider one test class at a time when
     * adding methods.  Now all of the test methods from all classes are
     * considered.
     */
    public static TestClass populateContents(ArrayList<Class> classes)
    {
        TestClass tc = new TestClass();
        if (set.getListType().equals("exclude"))
        {
            System.out.println("exclude list found!");

            // Get all Before, After, and Test methods from all classes.
            TestClass temp;
            ArrayList<Method> beforeMethods = new ArrayList<Method>();
            ArrayList<Method> afterMethods = new ArrayList<Method>();
            ArrayList<Method> testMethods = new ArrayList<Method>();

            for(Class clazz : classes)
            {
                temp = parseTestClass(clazz);
        
                for(Method m : temp.getBeforeMethods())
                    beforeMethods.add(m);
                for(Method m : temp.getAfterMethods())
                    afterMethods.add(m);
                for(Method m : temp.getTestMethods())
                    testMethods.add(m);
            }

            tc.setBeforeMethods(beforeMethods);
            tc.setAfterMethods(afterMethods);
            tc.setTestMethods(testMethods);

            // Remove Test methods that are on the exclude list.
            ArrayList<Method> testList = new ArrayList<Method>();
            for(Method method : tc.getTestMethods())
                testList.add(method);
            for (PMethod pmethod : set.getMethodList())
            {
                for (int i = 0; i < testList.size(); i++)
                {
                    if ((testList.get(i).getName()).equals(
                        pmethod.getMethodName()) && (testList.get(i).
                        getDeclaringClass().getName()).equals(
                        pmethod.getClazz().getName()))
                    {
                        testList.remove(i);
                    }
                }
            }
            tc.setTestMethods(testList);
        }
        else if (set.getListType().equals("include"))
        {
            System.out.println("include list found!");

            // Get all Before, After, and Test methods from all classes.
            TestClass temp;
            ArrayList<Method> beforeMethods = new ArrayList<Method>();
            ArrayList<Method> afterMethods = new ArrayList<Method>();
            ArrayList<Method> testMethods = new ArrayList<Method>();

            for(Class clazz : classes)
            {
                temp = parseTestClass(clazz);
        
                for(Method m : temp.getBeforeMethods())
                    beforeMethods.add(m);
                for(Method m : temp.getAfterMethods())
                    afterMethods.add(m);
                for(Method m : temp.getTestMethods())
                    testMethods.add(m);
            }

            tc.setBeforeMethods(beforeMethods);
            tc.setAfterMethods(afterMethods);
            tc.setTestMethods(testMethods);

            // Add Test methods that are on the include list.
            ArrayList<Method> newList = new ArrayList<Method>();
            for (PMethod pmethod : set.getMethodList())
            {
                for(Method m : tc.getTestMethods())
                {
                    if(m.getName().equals(pmethod.getMethodName()) && 
                        m.getDeclaringClass().getName().equals(
                        pmethod.getClazz().getName()))
                    {
                        newList.add(m);
                    }
                }
            }
            tc.setTestMethods(newList);
        }
        else
        {
            ArrayList<Method> beforeMethods = new ArrayList<Method>();
            ArrayList<Method> afterMethods = new ArrayList<Method>();
            ArrayList<Method> testMethods = new ArrayList<Method>();

            for(Class clazz : classes)
            {
                // Get all Before, After, and Test methods from all classes.
                TestClass temp = parseTestClass(clazz);
        
                for(Method m : temp.getBeforeMethods())
                    beforeMethods.add(m);
                for(Method m : temp.getAfterMethods())
                    afterMethods.add(m);
                for(Method m : temp.getTestMethods())
                    testMethods.add(m);
            }

            tc.setBeforeMethods(beforeMethods);
            tc.setAfterMethods(afterMethods);
            tc.setTestMethods(testMethods);

            System.out.println("ignoring list!");
        }
        return tc;
    }

    /**
     * Used for debugging.
     */
    public static void printContents(TestClass info)
    {
        System.out.println("beforeClass: " + info.getBeforeMethods());
        System.out.println("afterClass: " + info.getAfterMethods());
        System.out.println("tests: " + info.getTestMethods());
    }

    public static TestClass parseTestClass(Class clazz)
    {
        ArrayList<Method> beforeList = new ArrayList<Method>();
        ArrayList<Method> afterList = new ArrayList<Method>();
        ArrayList<Method> testList = new ArrayList<Method>();

        for (Method m : clazz.getMethods())
        {
            for (Annotation a : m.getDeclaredAnnotations())
            {
                if (a instanceof proteja.Setup)
                {
                    beforeList.add(m);
                }
                else if (a instanceof proteja.Teardown)
                {
                    afterList.add(m);
                }
                else if (a instanceof org.junit.Test)
                {
                    testList.add(m);
                }
            }
        }

        TestClass info = new TestClass();
        info.setBeforeMethods(beforeList);
        info.setAfterMethods(afterList);
        info.setTestMethods(testList);

        return info;
    }

    /**
     * Reads the settings file from disk.
     */
    public static Settings readSettings(String name)
    {
        try
        {
            XStream xstream = new XStream();
            FileReader fileIn = new FileReader(name);
            BufferedReader read = new BufferedReader(fileIn);
            String complete = "";
            String temp;

            System.out.println("Reading file");
            while ((temp = read.readLine()) != null)
            {
                complete = complete + temp;
            }

            System.out.println("File read");
            Settings config = (Settings) xstream.fromXML(complete);
            return config;
        } catch (IOException exc)
        {
            System.out.println("There was a problem reading the settings file");
            exc.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    /**
     * writes an instuctionBlock to disk.
     */
    public static void storeInstructionBlock(InstructionBlock ins,
        String filename)
    {
        try
        {
            XStream xstream = new XStream();
            String str = xstream.toXML(ins);
            FileWriter fileOut = new FileWriter(filename);
            char[] a = new char[str.length()];
            str.getChars(0, str.length(), a, 0);
            for (int i = 0; i < a.length; i++)
            {
                fileOut.write(a[i]);
            }

            fileOut.close();
        } catch (IOException exc)
        {
            System.out.println("There was a problem writing the instructions.");
            exc.printStackTrace();
            System.exit(1);
        }
    }

    public static void LaunchBlock(InstructionBlock ins, String[] args)
    {
        storeInstructionBlock(ins, "instructions.out");

        /**
         * Setup ant.
         */
        File buildFile = new File("build.xml");
        Project p = new Project();
        ProjectHelper helper;

        p.setUserProperty("ant.file", buildFile.getAbsolutePath());
        p.init();
        helper = ProjectHelper.getProjectHelper();
        p.addReference("ant.projectHelper", helper);
        helper.parse(p, buildFile);
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
        p.addBuildListener(consoleLogger);

        p.setProperty("booleanCobertura", "" + cobertura);

        System.out.println("Starting new JVM");
        System.out.println();
    
        // testTimer property value depends on whether timing information is
        // desired other properties are set in case we want to output a timing
        // file

        p.setProperty("testTimer", Boolean.toString(testTimer));
        p.setProperty("batchFactor", Integer.toString(set.getBatchFactor()));
        p.setProperty("coverageType", set.getCoverageType());
        p.setProperty("resetJVMOnBatch", Boolean.toString(
            set.getResetJVMOnBatch()));
        p.setProperty("applicationName", applicationName);
        p.executeTarget("run");
    }

    /**
     * Include any needed file manipulations here.  The code here will execute
     * after each JVM shutdown.
     */
    public static void afterFileManip()
    {
        if (cobertura)
        {
            coberturaAfterFileManip();
        }
    }

    /**
     * Include any needed file manipulations here. The code here will execute
     * once, just before shutdown of the proteja system.
     */
    public static void endOfProgramFileManip()
    {
        if (cobertura)
        {
            coberturaAfterProgram();
        }
    }

    public static void beforeFileManip()
    {

    }

    public static void beginningOfProgramFileManip()
    {
        if (cobertura)
        {
            coberturaBeforeProgram();
        }
    }

    /***************************************************************************
     *  After this point are optional methods that make the system work with
     *  Cobertura.
     */
    public static void coberturaBeforeProgram()
    {
        File buildFile = new File("build.xml");
        Project p = new Project();
        ProjectHelper helper;

        p.setUserProperty("ant.file", buildFile.getAbsolutePath());
        p.init();
        helper = ProjectHelper.getProjectHelper();
        p.addReference("ant.projectHelper", helper);
        helper.parse(p, buildFile);
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
        p.addBuildListener(consoleLogger);

        p.executeTarget("inst");
        p.executeTarget("createCopy");

    }

    /**
     * launches ant which moves and renames the files cobertura produces.
     */
    public static void coberturaAfterFileManip()
    {
        File buildFile = new File("build.xml");
        Project p = new Project();
        ProjectHelper helper;

        p.setUserProperty("ant.file", buildFile.getAbsolutePath());
        p.init();
        helper = ProjectHelper.getProjectHelper();
        p.addReference("ant.projectHelper", helper);
        helper.parse(p, buildFile);
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
        p.addBuildListener(consoleLogger);

        p.setProperty("groupNumber", Integer.toString(groupNumber));

        p.executeTarget("coberturaFileManip");
        p.executeTarget("coberturaReportGeneration");
        p.executeTarget("coberturaReportParser");
        p.executeTarget("moveToHome");
        groupNumber++;
    }

    public static void coberturaAfterProgram()
    {
        File buildFile = new File("build.xml");
        Project p = new Project();
        ProjectHelper helper;

        p.setUserProperty("ant.file", buildFile.getAbsolutePath());
        p.init();
        helper = ProjectHelper.getProjectHelper();
        p.addReference("ant.projectHelper", helper);
        helper.parse(p, buildFile);
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
        p.addBuildListener(consoleLogger);

        p.setProperty("numberOfGroups", Integer.toString(groupNumber - 1));
        p.setProperty("batchFactor", Integer.toString(set.getBatchFactor()));
        p.setProperty("coverageType", set.getCoverageType());
        p.setProperty("resetJVMOnBatch", Boolean.toString(
            set.getResetJVMOnBatch()));
        p.setProperty("applicationName", applicationName);
        p.executeTarget("constructMatrix");
        p.executeTarget("cleanup");
    }
}
