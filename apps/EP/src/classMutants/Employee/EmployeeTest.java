package classMutants.Employee;
import java.io.*;
import org.junit.Test;
import junit.framework.*;

public class EmployeeTest extends TestCase {

    private String fn;
    private String ln;
    private String ssn;
	private double s;
	private double r;

    Employee E1 = new Employee(fn,ln,ssn);
	Employee E2 = new Employee(fn,ln,ssn);
	
    CommissionEmployee C1 = new CommissionEmployee(fn,ln,ssn,s,r);
    CommissionEmployee C2 = new CommissionEmployee(fn,ln,ssn,s,r);

	SalariedEmployee s1 = new SalariedEmployee(fn,ln,ssn,s);

    public static TestSuite suite() {
	return new TestSuite(EmployeeTest.class);
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
	

	
// 	Testing Employee-----------------------

    @Test
   public void test1(){  // EAM_1 --6 + PRV7-12 + IOD_1 
    	E1.setFirstName("FN");
		E1.setLastName("LN");
        E1.setSocialSecurityNumber("SSN");
	    String actual = E1.toString();
        String expected  = "FN LN\nsocial security number: SSN";
        assertEquals(actual,expected);
   }

    @Test
	public void test2(){  //JSI_1 + PRV_7 + PRV_8 
	  	E1.setFirstName("FN1");
		E2.setFirstName("FN2");
	    String actual = E1.getFirstName();
        String expected = "FN1";
        assertEquals(actual,expected);
   }
	
    @Test
	public void test3(){  // JSI_2  + PRV_9 + PRV_10 
	  	E1.setLastName("LN1");
		E2.setLastName("LN2");
	    String actual = E1.getLastName();
        String expected = "LN1";
        assertEquals(actual,expected);
   }
	
    @Test
   public void test4(){  //JSI_3 + PRV_11 + PRV_12 
	    E1.setSocialSecurityNumber("SSN1");
		E2.setSocialSecurityNumber("SSN2");
        String actual = E1.getSocialSecurityNumber();
        String expected = "SSN1";
        assertEquals(actual,expected);
   }

    @Test
  public void test5(){  // PRV_1 + PRV_2 
        fn = "FN1";
	  	ln = "LN1";
	    ssn = "SSN1";
		Employee E2 = new Employee(fn,ln,ssn);
		String actual = E2.getFirstName();
        String expected = "FN1";
        assertEquals(actual,expected);
   }
	
    @Test
  public void test6(){  // PRV_3 + PRV_4 
        fn = "FN1";
	    ln = "LN1";
	    ssn = "SSN1";
		Employee E2 = new Employee(fn,ln,ssn);
	    String actual = E2.getLastName();
        String expected = "LN1";
        assertEquals(actual,expected);
  }

    @Test
  public void test7(){  // PRV_5 + PRV_6 
        fn = "FN1";
	  	ln = "LN1";
	    ssn = "SSN1";
		Employee E2 = new Employee(fn,ln,ssn);
		String actual =  E2.getSocialSecurityNumber();
        String expected = "SSN1";
        assertEquals(actual,expected);
  }
  
  // 	Testing CommissionEmployee-----------------------

    @Test
  public void test8(){  // EMM1 + EMM2 
        s = 100;
	  	r = 1;
		CommissionEmployee C3 = new CommissionEmployee(fn,ln,ssn,s,r);
	    double actual = C3.getGrossSales();
        double expected = 100.0;
        assertEquals(actual,expected);
  }
    
    @Test
   public void test9(){  // EAM_1 + EAM_2 + IOD_1  
	  	C1.setGrossSales(100);
		C1.setCommissionRate(0.05);
	    double actual = C1.earnings();
        double expected = 5.0;
        assertEquals(actual,expected);
   }

	@Test
	public void test10(){  // EAM_3 + EAM_4 + IOD_2  
 		C1.setGrossSales(200);
   	    C1.setCommissionRate(100);
	    String actual = C1.toString();
        String expected = "commission employee: null null\nsocial security number: null\ngross sales: $200.00; commission rate: 0.00";
        assertEquals(actual,expected);
   }
	
    @Test
   public void test11(){  //JSI_1 
	  	C1.setGrossSales(20);
		C2.setGrossSales(30);
	    double actual = C1.getGrossSales();
        double expected = 20.0;
        assertEquals(actual,expected);
   }
	
    @Test
	   public void test12(){  //JSI_2 
	  	C1.setCommissionRate(0.02);
		C2.setCommissionRate(0.03);
	    double actual = C1.getCommissionRate();
        double expected = 0.02;
        assertEquals(actual,expected);
   }
	
	// 	Testing SalariedEmployee-----------------------

        @Test
	   public void test13(){  // JSI_1 + IOD_2 
    	s1.setWeeklySalary(10);
		fn = "FN";
		ln = "SN";
		ssn="ssn";
		SalariedEmployee s2 = new SalariedEmployee(fn,ln,ssn,s);
        String actual = s1.toString();
        String expected = "salaried employee: null null\nsocial security number: null\nweekly salary: $10.00";
        assertEquals(actual,expected);
   }

        @Test
	   public void test14(){  // IOD_1
    	s1.setWeeklySalary(10);
		fn = "FN";
		ln = "SN";
		ssn="ssn";
		s=10;
        double actual = s1.earnings();
        double expected = 10.0;
        assertEquals(actual,expected);
   }
}
