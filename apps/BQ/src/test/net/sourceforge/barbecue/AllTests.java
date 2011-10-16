package net.sourceforge.barbecue;

import junit.framework.*;

public class AllTests 
{
    public static Test suite() 
    {
	    TestSuite suite = new TestSuite("Barbecue");
    suite.addTestSuite(net.sourceforge.barbecue.linear.code39.Code39BarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.BarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.linear.ean.UCCEAN128BarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.BlankModuleTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.linear.LinearBarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.CompositeModuleTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.linear.twoOfFive.Int2of5BarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.env.DefaultEnvironmentTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.linear.twoOfFive.Std2of5BarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.env.EnvironmentFactoryTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.env.HeadlessEnvironmentTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.ModuleTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.env.NonAWTEnvironmentTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.Modulo10Test.class);
    suite.addTestSuite(net.sourceforge.barbecue.linear.codabar.CodabarBarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.output.GraphicsOutputTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.linear.code128.Code128BarcodeTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.linear.code128.ModuleFactoryTest.class);
    suite.addTestSuite(net.sourceforge.barbecue.SeparatorModuleTest.class);

    return suite;
    }
    
    public static void main(String[] args) {
	junit.textui.TestRunner.run(suite());
    }
}
