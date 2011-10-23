/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package proteja;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestClass
{
    private ArrayList<Method> beforeMethods, afterMethods,
        testMethods;

    public TestClass()
    {
        beforeMethods = null;
        afterMethods = null;
        testMethods = null;
    }

    public void setBeforeMethods(ArrayList<Method> beforeMethods)
    {
        this.beforeMethods = beforeMethods;
    }

    public void setAfterMethods(ArrayList<Method> afterMethods)
    {
        this.afterMethods = afterMethods;
    }

    public void setTestMethods(ArrayList<Method> testMethods)
    {
        this.testMethods = testMethods;
    }

    public ArrayList<Method> getBeforeMethods()
    {
        return beforeMethods;
    }

    public ArrayList<Method> getAfterMethods()
    {
        return afterMethods;
    }

    public ArrayList<Method> getTestMethods()
    {
        return testMethods;
    }
}
