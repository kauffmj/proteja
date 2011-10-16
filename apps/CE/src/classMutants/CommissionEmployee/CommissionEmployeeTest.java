package classMutants.CommissionEmployee;
import java.io.*;
import org.junit.Test;
import junit.framework.*;

public class CommissionEmployeeTest extends TestCase {

// For Testing CommissionEmployee4
    private String fn;
    private String ln;
    private String ssn;
    private double gs;
    private double cr;
    CommissionEmployee4 C1 = new CommissionEmployee4(fn,ln,ssn,gs,cr);
    CommissionEmployee4 C2 = new CommissionEmployee4(fn,ln,ssn,gs,cr);

// For Testing CommissionEmployee5 (supplement)	
	private double bs;
	private double sl;
	BasePlusCommissionEmployee5 C4 = new BasePlusCommissionEmployee5(fn,ln,ssn,gs,cr,sl);
	BasePlusCommissionEmployee5 C5 = new BasePlusCommissionEmployee5(fn,ln,ssn,gs,cr,sl);

    public static TestSuite suite() {
	return new TestSuite(CommissionEmployeeTest.class);
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

// 	Testing CommissionEmployee4-----------------------

   @Test
   public void test1(){  // EAM_1 + EAM_2 
        gs = 200.00;
		cr = 0.1;
		C1.setGrossSales(gs);
		C1.setCommissionRate(cr);
	    double actual = C1.earnings();
        double expected = 20.0;
        assertEquals(actual,expected);
   }

   @Test
   public void test2(){  // EAM_3 --10 + PRV7-12 + IOD_1 
    	C1.setFirstName("FN");
		C1.setLastName("LN");
        C1.setSocialSecurityNumber("SSN");
        C1.setFirstName("FN");
	  	C1.setGrossSales(200);
  		C1.setCommissionRate(0.1);
	    String actual = C1.toString();
        String expected = "commission employee: FN LN\nsocial security number: SSN\ngross sales: 200.00\ncommission rate: 0.10";
        assertEquals(actual,expected);
   }
	
   @Test
	public void test3(){  //JSI_1 + PRV_7 + PRV_8 
	  	C1.setFirstName("FN1");
		C2.setFirstName("FN2");
	    String actual = C1.getFirstName();
        String expected = "FN1";
        assertEquals(actual,expected);
   }

   @Test	
	public void test4(){  // JSI_2  + PRV_9 + PRV_10 
	  	C1.setLastName("LN1");
		C2.setLastName("LN2");
	    String actual = C1.getLastName();
        String expected = "LN1";
        assertEquals(actual,expected);
   }

   @Test	
   public void test5(){  //JSI_3 + PRV_11 + PRV_12 
	    C1.setSocialSecurityNumber("SSN1");
		C2.setSocialSecurityNumber("SSN2");
        String actual = C1.getSocialSecurityNumber();
        String expected = "SSN1";
        assertEquals(actual,expected);
   }

   @Test	
   public void test6(){  //JSI_4 
	  	C1.setGrossSales(200);
		C2.setGrossSales(300);;
	    double actual = C1.getGrossSales();
        double expected = 200.0;
        assertEquals(actual,expected);
   }
	
   @Test
   public void test7(){  //JSI_5 
	  	C1.setCommissionRate(0.1);
	    C2.setCommissionRate(1);
	    double actual = C1.getCommissionRate();
        double expected = 0.1;
        assertEquals(actual,expected);
   }
	
   @Test
  public void test8(){  // PRV_1 + PRV_2 
        fn = "FN1";
	  	ln = "LN1";
	    ssn = "SSN1";
		CommissionEmployee4 C3 = new CommissionEmployee4(fn,ln,ssn,gs,cr);
	    String actual = C3.getFirstName();
        String expected = "FN1";
        assertEquals(actual,expected);
   }
	
   @Test
  public void test9(){  // PRV_3 + PRV_4 
        fn = "FN1";
	  	ln = "LN1";
	    ssn = "SSN1";
		CommissionEmployee4 C3 = new CommissionEmployee4(fn,ln,ssn,gs,cr);
	    String actual = C3.getLastName();
        String expected = "LN1";
        assertEquals(actual,expected);
  }

   @Test
  public void test10(){  // PRV_5 + PRV_6 
        fn = "FN1";
	  	ln = "LN1";
	    ssn = "SSN1";
		CommissionEmployee4 C3 = new CommissionEmployee4(fn,ln,ssn,gs,cr);
	    String actual = C3.getSocialSecurityNumber();
        String expected = "SSN1";
        assertEquals(actual,expected);
  }

   @Test
  public void test11(){  // EMM1 + EMM2  ??WHY IT CAN KILL emm2
        gs = 100;
	  	cr = 1;
		CommissionEmployee4 C3 = new CommissionEmployee4(fn,ln,ssn,gs,cr);
	    double actual = C3.getGrossSales();
        double expected = 100.0;
        assertEquals(actual,expected);
  }

   @Test
   public void test12(){  // EMM2
        gs = 10.0;
	  	cr = 0.1;
	 	CommissionEmployee4 C3 = new CommissionEmployee4(fn,ln,ssn,gs,cr);
	    double actual = C3.getCommissionRate();
        double expected = 0.1;
        assertEquals(actual,expected);
  }
  
  // 	Testing CommissionEmployee5-----------------------

   @Test
   public void test13(){  // EAM_1 + EAM_2 + IOD_1  ?? why IOD_1 = 0
 		C5.setGrossSales(200);
		C5.setBaseSalary(100);
	    double actual = C5.earnings();
        double expected = 100.0;
        assertEquals(actual,expected);
   }
	
   @Test
	public void test14(){  // EAM_3 + EAM_4 + IOD_2  
 		C5.setGrossSales(200);
		C5.setBaseSalary(100);
	    String actual = C5.toString();
        String expected = "base-salaried commission employee: null null\nsocial security number: null\ngross sales: 200.00\ncommission rate: 0.00\nbase salary: 100.00";
        assertEquals(actual,expected);
   }
	
   @Test
   public void test15(){  //JSI_1 
	  	C4.setBaseSalary(20);
		C5.setBaseSalary(30);
	    double actual = C4.getBaseSalary();
        double expected = 20.0;
        assertEquals(actual,expected);
   }

}
