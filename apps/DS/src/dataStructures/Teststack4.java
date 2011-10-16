package dataStructures;

//intstackmaxtests
import junit.framework.TestCase;
import junit.framework.TestSuite;


/* 
   This is an example of a TestSuite, the other component
   in the JUnit Test composite pattern
*/

public class Teststack4 extends TestCase 
{
	
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
	  Two cases: 1) the stack is already full ==> not empty
   */
   {
	  int k = 3;
      assertTrue(!numbers.isEmpty());
   }
   
    public void testPopNew() 
   /* Tests the popping of the empty stack.
   */
   {
	  assertTrue( numbers.equals(otherNumbers) );
      assertTrue( numbers.equals(otherNumbers) );
   }
   /* Tests the popping of the stack with something being pushed on it.
   */
   {
		int k = 3;
		assertTrue( numbers.equals(otherNumbers) );
	   //max is 2
		otherNumbers.push(k);
		assertTrue(numbers.equals(otherNumbers));
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
	
   /* Tests the peeking at the top of the stack after a push.
   */
   {
		int k = 3;
		assertTrue( numbers.top() == k );
		//max size is 2
		otherNumbers.push(k);
		assertTrue( numbers.equals(otherNumbers) );
		assertTrue( numbers.maxSize() == numbers.getNumberOfElements() );
		assertTrue( numbers.top() == otherNumbers.top() );
   }
   
   public void testFullNew() 
   /* Tests whether a new stack is full.
   */
   {
      assertTrue(!numbers.isFull());
   }
   /* Tests whether is a stack is full after a push of the stack.
   */
   {
	//max size is 2
	  numbers.push(k);				
	//size is now max-1
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
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() + 1  );
		otherNumbers.push(k);
		//size is maxsize-1
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() );
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() + 1 );
		//size now equals maxsize(2) 
		assertTrue( numbers.getNumberOfElements() ==  otherNumbers.getNumberOfElements() );
	*/
	{
		assertTrue(numbers.maxSize() == max);
	*/
	{
		assertTrue( numbers.maxSize() == max );
  

    
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

}
//	  int k = 3;
   
//    assertTrue(!numbers.equals(null));
//    assertTrue(numbers, numbers);
//    assertTrueEquals(numStack, new intStack()); // (1)
//    assertTrue(!numbers.equals(numStack));
//  }