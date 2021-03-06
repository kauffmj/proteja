package dataStructures;

//intstackmaxtestsimport junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;import java.util.*;


/* 
   This is an example of a TestSuite, the other component
   in the JUnit Test composite pattern
*/

public class Teststack4 extends TestCase 
{	   private intStackMaxArray numbers;   private intStackMaxArray otherNumbers;   
	
   public Teststack4(String name) {
      super(name);
   }

   public static Test suite() 
   /* Assembles and returns a test suite containing all known tests.
      New tests should be added here!
      @return A non-null test suite.
   */
   {

      TestSuite suite = new TestSuite(Teststack4.class);
	

      return suite;
   }      protected void setUp() 
   /* Sets up the text fixture.	 
      Called before every test case method.
   */	
   {	   numbers = new intStackMaxArray();	   otherNumbers = new intStackMaxArray();
   }

   protected void tearDown() 
   /* Tears down the text fixture.
      Called after every test case method.
   */
   {
      numbers = null;	  otherNumbers = null;
   }      public void testEmptyNew() 
   /* Tests the emptying of the stack.
   */
   {
      assertTrue(numbers.isEmpty());
   }      public void testEmptyPush() 
   /* Tests whether a stack is empty after a push.
	  Two cases: 1) the stack is already full ==> not empty				 2) the stack has at least one element(the one pushed) ==> not empty
   */
   {
	  int k = 3;	  numbers.push(k);
      assertTrue(!numbers.isEmpty());
   }   
   
    public void testPopNew() 
   /* Tests the popping of the empty stack.
   */
   {
	  assertTrue( numbers.equals(otherNumbers) );	  numbers.pop();
      assertTrue( numbers.equals(otherNumbers) );
   }      public void testPopPush() 
   /* Tests the popping of the stack with something being pushed on it.
   */
   {	   //first use a new(empty) stack	   
		int k = 3;		numbers.push(k);		numbers.pop();
		assertTrue( numbers.equals(otherNumbers) );	   //now test the case where stack is full
	   //max is 2		numbers.push(k);		numbers.push(k);
		otherNumbers.push(k);		otherNumbers.push(k);
		assertTrue(numbers.equals(otherNumbers));	   //if the stack is full then the push has no effect.
		numbers.push(k);
		numbers.pop();
		otherNumbers.pop();
		assertTrue( numbers.equals(otherNumbers) );
   }         
  
    public void testTopNew() 
   /* Tests the peeking at the top of the empty stack.
   */
   {
      assertTrue(  numbers.top() == -1 );
   }
	   public void testTopPush() 
   /* Tests the peeking at the top of the stack after a push.
   */
   {
		int k = 3;		numbers.push(k);
		assertTrue( numbers.top() == k );		//now test full stack
		//max size is 2		int e = 2;		numbers.push(k);
		otherNumbers.push(k);		otherNumbers.push(k);
		assertTrue( numbers.equals(otherNumbers) );
		assertTrue( numbers.maxSize() == numbers.getNumberOfElements() );		numbers.push(e);
		assertTrue( numbers.top() == otherNumbers.top() );
   }
   
   public void testFullNew() 
   /* Tests whether a new stack is full.
   */
   {
      assertTrue(!numbers.isFull());
   }      public void testFullPush() 
   /* Tests whether is a stack is full after a push of the stack.
   */
   {	  int k = 3;
	//max size is 2
	  numbers.push(k);				
	//size is now max-1	  assertTrue(!numbers.isFull() );  	  numbers.push(k);	  assertTrue( numbers.isFull() );
   }
   
    public void testGetNumberOfElementsNew() 
   /* Tests num elements of empty stack.
   */
   {
      assertTrue(numbers.getNumberOfElements() == 0);
   }       public void testGetNumberOfElements() 
   /* Tests the num elem of a stack after a push.
   */
	{		int k = 3;		numbers.push(k);
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() + 1  );
		otherNumbers.push(k);
		//size is maxsize-1
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() );		numbers.push(k);
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() + 1 );		otherNumbers.push(k);
		//size now equals maxsize(2) 		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() );		assertTrue( numbers.maxSize() == numbers.getNumberOfElements() );		numbers.push(k);
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() );	}		public void testMaxSizeNew()	/* Tests the max function of empty stack.
	*/
	{		//max is set to two in the constructor		int max = 2;
		assertTrue(numbers.maxSize() == max);	}		public void testMaxSizePush()	/* Tests the max function after a push.
	*/
	{		//max is set to two in the constructor		int max = 2;		int k = 5;		numbers.push(k);
		assertTrue( numbers.maxSize() == max );	}
  

    
   public static void main(String args[])
   /* select which runner to use and fire off the test suite
   */ 
   {
   /* in here we choose which runner to invoke and pass
      it the test case or suite we have built
   */
      String[] testCaseName = {Teststack4.class.getName()};
      //junit.textui.TestRunner.main(testCaseName);
      //junit.swingui.TestRunner.main(testCaseName);
      //junit.ui.TestRunner.main(testCaseName);
   }

}//  public void testEquals() {
//	  int k = 3;//	  numbers.push(k);
   
//    assertTrue(!numbers.equals(null));
//    assertTrue(numbers, numbers);
//    assertTrueEquals(numStack, new intStack()); // (1)
//    assertTrue(!numbers.equals(numStack));
//  }
