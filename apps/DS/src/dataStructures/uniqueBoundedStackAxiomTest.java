package dataStructures;

//intstackmaxtests
import junit.framework.TestCase;
import junit.framework.TestSuite;


/* 
   This is an example of a TestSuite, the other component
   in the JUnit Test composite pattern
*/

public class uniqueBoundedStackAxiomTest extends TestCase 
{
	
   public uniqueBoundedStackAxiomTest(String name) {
      super(name);
   }

   public static Test suite() 
   /* Assembles and returns a test suite containing all known tests.
      New tests should be added here!
      @return A non-null test suite.
   */
   {

      TestSuite suite = new TestSuite(uniqueBoundedStackAxiomTest.class);
	

      return suite;
   }
   /* Sets up the text fixture.	 
      Called before every test case method.
   */	
   {
   }

   protected void tearDown() 
   /* Tears down the text fixture.
      Called after every test case method.
   */
   {
      numbers = null;
   }
   /* Tests the emptying of the stack.
   */
   {
      assertTrue(numbers.isEmpty());
   }
   /* Tests whether a stack is empty after a push.
	 */
   {
	  int k = 3;
      assertTrue(!numbers.isEmpty());
   }
   
   public void testFullNew() 
   /* Tests whether a new stack is full.
   */
   {
      assertTrue(!numbers.isFull());
   }
       public void testFullPush() 
   /* Tests whether is a stack is full after a push of the stack.
    */
    {
	   /*
	   if ( otherNumbers.isMember(k) )
		  assertTrue(numbers.isFull());
	  }
	  else
	  {
		  assertTrue(!numbers.isFull());
	  }
	   */
	//max size is 2
	  numbers.push(k);
	  assertTrue(!numbers.isFull());
	  assertTrue(numbers.isFull());
	  assertTrue( numbers.isFull() == otherNumbers.isFull() );
   }
   
   public void testMemberNew() 
   /* Tests membership in a new stack
   */
   {
      assertTrue(!numbers.isMember(k));
   }
   /* Tests membership after a push.
	 */
   {
	  int k = 3;
	  int j = 2;
	  int n = 1;
      assertTrue(numbers.isMember(k));
	  numbers.push(j);
	  numbers.push(n);
	  otherNumbers.push(j);
	  assertTrue( numbers.isMember(n) == otherNumbers.isMember(n) );
   }
   
    public void testPopNew() 
   /* Tests the popping of the empty stack.
   */
   {
       //assertTrue( numbers.equals(otherNumbers) );
   }
   /* Tests the popping of the stack with something being pushed on it.
   */
   {
		int k = 3;
		int j = 2;
		int n = 1;
		assertTrue( numbers.equals(otherNumbers) );
	   //max is 2
		numbers.push(k);
		otherNumbers.push(k);
		assertTrue(numbers.equals(otherNumbers));
		numbers.push(n);
		numbers.pop();
		otherNumbers.pop();
		assertTrue( numbers.equals(otherNumbers) );
		otherNumbers.pop();
		assertTrue( numbers.equals(otherNumbers) );
		
		numbers.push(k);
		otherNumbers.pop();
		assertTrue( numbers.equals(otherNumbers) );	
   }
  
    public void testTopNew() 
   /* Tests the peeking at the top of the empty stack.
   */
   {
      assertTrue(  numbers.top() == -1 );
   }
	
   /* Tests the peeking at the top of the stack after a push.
   */
   {
		int k = 3;
		int j = 2;
		int n = 1;
		assertTrue( numbers.top() == k );
		//max size is 2
		otherNumbers.push(k);
		assertTrue( numbers.equals(otherNumbers) );
		assertTrue( numbers.maxSize() == numbers.getNumberOfElements() );
		assertTrue( numbers.top() == otherNumbers.top() );
		numbers.push(k);
		assertTrue( numbers.top() == k );
   }
   
    public void testGetNumberOfElementsNew() 
   /* Tests num elements of empty stack.
   */
   {
      assertTrue(numbers.getNumberOfElements() == 0);
   }
   /* Tests the num elem of a stack after a push.
   */
	{
		int j = 2;
		int n = 1;
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() + 1  );
		otherNumbers.push(k);
		//size is maxsize-1
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() );
		//size now equals maxsize(2) 
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() );
	//two new stacks	
		assertTrue(numbers.equals(otherNumbers));
    }
		//new stack: numbers, push stack: otherNumbers
		int j = 4;
		assertTrue(!numbers.equals(otherNumbers));
    }
	
	public void testPushNewEquals() {
	//push stack: numbers, new stack: otherNumbers
		int k = 3;
		assertTrue(!numbers.equals(otherNumbers));
    }
	
	public void testPushPushEquals() {
	//push stack: numbers, push stack: otherNumbers
		uniqueBoundedStack oldNumbers = new uniqueBoundedStack();
		uniqueBoundedStack oldOtherNumbers = new uniqueBoundedStack();
		int k = 3;
		int j = 4;
		int n = 1;
		//if the stack doesn't contain the pushed element
		assertTrue(!numbers.equals(otherNumbers));
		
		oldNumbers = numbers;
		oldOtherNumbers = otherNumbers;
		numbers.push(n);
		assertTrue( numbers.equals(otherNumbers) == oldNumbers.equals(oldOtherNumbers) );
		
		oldNumbers = numbers;
		oldOtherNumbers = otherNumbers;
		numbers.push(j);
		assertTrue( numbers.equals(otherNumbers) == oldNumbers.equals(oldOtherNumbers) );
		
		otherNumbers.pop();
		oldNumbers = numbers;
		oldOtherNumbers = otherNumbers;
		numbers.push(j);
		assertTrue( numbers.equals(otherNumbers) == oldNumbers.equals(otherNumbers) );
		
		oldNumbers = numbers;
		oldOtherNumbers = otherNumbers;
		numbers.push(j);
		//since both full and isMember is true for otherNumbers but not numbers
		assertTrue( !numbers.equals(otherNumbers) );
		
		numbers.pop();
		oldNumbers = numbers;
		oldOtherNumbers = otherNumbers;
		numbers.push(j);
		otherNumbers.push(j);
		//since j equals top of otherNumbers
		oldOtherNumbers.pop();
		//if neither tops equal j and they equal each other then stacks are equal
		assertTrue( numbers.equals(otherNumbers) == ( oldNumbers.top() == oldOtherNumbers.top() ) );
		
		oldNumbers = numbers;
		oldOtherNumbers = otherNumbers;
		numbers.push(j);
		//since both full and isMember is true for both and j != k .
		assertTrue( !numbers.equals(otherNumbers) );
				
		
		
		
		
    }
  

    
   public static void main(String args[])
   /* select which runner to use and fire off the test suite
   */ 
   {
   /* in here we choose which runner to invoke and pass
      it the test case or suite we have built
   */
      String[] testCaseName = {uniqueBoundedStackAxiomTest.class.getName()};
      //junit.textui.TestRunner.main(testCaseName);
      //junit.swingui.TestRunner.main(testCaseName);
      //junit.ui.TestRunner.main(testCaseName);
   }

}
//	  int k = 3;
   
//    assertTrue(!numbers.equals(null));
//    assertTrue(numbers, numbers);
//    assertEquals(numStack, new intStack()); // (1)
//    assertTrue(!numbers.equals(numStack));
//  }

/*
		if (oldNumbers.isFull() )
		{
			if ( oldOtherNumbers.isFull() )
			{
				if(oldNumbers.equals(oldOtherNumbers))
				{
					assertTrue(numbers.equals(otherNumbers));
				}
				else
				{
					assertTrue(!numbers.equals(otherNumbers));	
				}
			}
			else 
			{
				if ( oldNumbers.equals(otherNumbers))
				{
					assertTrue(numbers.equals(otherNumbers));
				}
				else
				{
					assertTrue(!numbers.equals(otherNumbers));	
				}
			}
		}
		else
		{
			if ( oldOtherNumbers.isFull() )
			{
				if( numbers.equals(oldOtherNumbers))
				{
					assertTrue(numbers.equals(otherNumbers));
				}
				else
				{
					assertTrue(!numbers.equals(otherNumbers));	
				}
			}
			else
			{
				if(k == j)
				{
					assertTrue(numbers.equals(otherNumbers)== oldNumbers.equals(oldOtherNumbers));
				}
				else
				{
					assertTrue(!numbers.equals(otherNumbers));	
				}
			}
		}
		*/