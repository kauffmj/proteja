/*
 * SWE619 
 * ASSIGNMENT 10
 * NAN LI
 * PointTest
 * 
 */

package assignment10;
import java.io.*;
import org.junit.Test;
import junit.framework.*;

public class PointTest extends TestCase {
private Point2 p2,p4;
private Point3 p3,p1;
private Integer i1;
private Object o1, o2;

    public static TestSuite suite() {
	return new TestSuite(PointTest.class);
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }


	//Test for Reflexivity

    @Test
	public void test1(){
		boolean b1;	
		p2 = new Point2(0,-2);
		p1 = new Point3(1,2,4);
		b1 = p2.equals(p1);
		boolean actual = b1;
        boolean expected = false;
        assertEquals(actual,expected);

	}
	
    @Test
	public void test2(){
		boolean b1;
		p2 = new Point2(1,2);
		p1 = new Point3(1,2,4);
		b1 = p1.equals(p2);
		boolean actual = b1;
        boolean expected = true;
        assertEquals(actual,expected);

	}

    @Test
	public void test3(){
		boolean b1;
		p2 = new Point2(1,2);
		p1 = new Point3(1,2,4);
		b1 = p1.hashCode() == p2.hashCode();
		boolean actual = b1;
        boolean expected = false;
        assertEquals(actual,expected);
	}

    @Test
	public void test4(){
		boolean b1;
		p2 = new Point2(1,2);
		p4 = new Point2(1,2);
		b1 = p2.equals(p4);
		boolean actual = b1;
        boolean expected = true;
	}

    @Test
	public void test5(){
		p2 = new Point2(1,2);
		p4 = new Point2(1,3);
		boolean actual = p4.equals(p2);
        boolean expected = false;
        assertEquals(actual,expected);
	}

    @Test
	public void test6(){
		p2 = new Point2(1,2);
		p1 = new Point3(1,2,4);
		int actual = p1.hashCode();
        int expected = 507474;
        assertEquals(actual,expected);
	}

    @Test
	public void test7(){
		p2 = new Point2(1,2);
		i1 = new Integer(4);
		boolean actual = p2.equals(i1);
        boolean expected = false;
        assertEquals(actual,expected);
	}

    @Test
	public void test8(){
	   int n1=1;
		p2 = new Point2(n1,2);
		n1=n1+1;
		p1 = new Point3(n1,1,3);
		int actual = p1.hashCode();
        int expected = 508403;
        assertEquals(actual,expected);
	}

        @Test
		public void test9(){
		p2 = new Point2(1,2);
		p1 = new Point3(1,2,4);
		int actual = p2.hashCode();
        int expected = 16370;
        assertEquals(actual,expected);
	}

        @Test
		public void test10(){
		p2 = new Point2(1,2);
	o1= new Object();
			boolean actual = p2.equals(o1);
            boolean expected = false;
        assertEquals(actual,expected);
	}

    @Test
	public void test11(){
		p2 = new Point2(0,3);
		p4 = new Point2(1,3);
		boolean actual = p4.equals(p2);
        boolean expected = false;
        assertEquals(actual,expected);
	}

        @Test
		public void test12(){
		p2 = new Point2(0,3);
		o1 = new Point2(0,3);
		boolean actual = p2.equals(o1);
        boolean expected = true;
        assertEquals(actual,expected);
	}

        @Test
		public void test13(){
		p2 = new Point2(0,3);
		o2 = (Object)p2;
		boolean actual = p2.equals(o2);
        boolean expected = true;
        assertEquals(actual,expected);
	}
	/*
	//Test for Symmetry
	@Test
	public void Test2(){
		p1 = new Point3(1,2,4);
		p2 = new Point2(1,2);
		p3 = new Point3(1,2,3);
		
		assertEquals(false,p3.equals(p1));
		assertEquals(false,p1.equals(p3));
		assertEquals(true,p1.equals(p2));
		assertEquals(true,p2.equals(p1));
	}
	//Test for Transitivity
	//p1 is equal to p2 and p2 is equal to p3 but p1 is not equalt to p3
	@Test
	public void Test3(){
		p1 = new Point3(1,2,4);
		p2 = new Point2(1,2);
		p3 = new Point3(1,2,3);
		
		assertEquals(true,p1.equals(p2));
		assertEquals(true,p2.equals(p3));	
		assertEquals(false,p1.equals(p3));
	}
	//Test for Consistency
	@Test
	public void Test4(){
		p2 = new Point2(1,2);
		p1 = new Point3(1,2,4);
		assertEquals(true,p2.equals(p2));
		assertEquals(true,p1.equals(p1));
		assertEquals(true,p2.equals(p2));
		assertEquals(true,p1.equals(p1));
		assertEquals(true,p2.equals(p2));
		assertEquals(true,p1.equals(p1));
	}
	//Test for Non-nullity
	@Test
	public void Test5(){
		p2 = new Point2(1,2);
		p1 = new Point3(1,2,4);
		assertEquals(false,p2.equals(null));
		assertEquals(false,p1.equals(null));
	}
		*/
	//Test for breaking hash code
	//p1 is equal to p2 but they produce different hash code



}
