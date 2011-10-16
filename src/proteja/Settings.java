/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package proteja;

import java.util.ArrayList;

public class Settings
{
    private ArrayList<Class> testClasses;
    private int batchFactor;
    private boolean resetJVMOnBatch;
    private String coverageType;
    private String listType;
    private ArrayList<PMethod> methodList;

    public Settings()
    {
        testClasses = null;
        batchFactor = 1;
        coverageType = "class";
        resetJVMOnBatch = false;
        listType = "include";
        methodList = new ArrayList<PMethod>();
        methodList.add(new PMethod());
    }

    public void setTestClasses(ArrayList<Class> testClasses)
    {
        this.testClasses = testClasses;
    }

    public void setBatchFactor(int batchFactor)
    {
        this.batchFactor = batchFactor;
    }

    public void setCoverageType(String coverageType)
    {
        this.coverageType = coverageType;
    }

    public void setResetJVMOnBatch(boolean resetJVMOnBatch)
    {
        this.resetJVMOnBatch = resetJVMOnBatch;
    }

    public ArrayList<Class> getTestClasses()
    {
        return testClasses;
    }

    public int getBatchFactor()
    {
        return batchFactor;
    }

    public String getCoverageType()
    {
        return coverageType;
    }

    public boolean getResetJVMOnBatch()
    {
        return resetJVMOnBatch;
    }

    public ArrayList<PMethod> getMethodList()
    {
        return methodList;
    }

    public void setMethodList(ArrayList<PMethod> methodList)
    {
        this.methodList = methodList;
    }

    public void setListType(String listType)
    {
        this.listType = listType;
    }

    public String getListType()
    {
        return listType;
    }
}
