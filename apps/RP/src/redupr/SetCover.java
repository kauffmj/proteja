/*---------------------------------------------------------------------
 *  File: $Id: SetCover.java,v 1.46 2007/03/10 21:02:22 gkapfham Exp $   
 *  Version:  $Revision: 1.46 $
 *
 *  Project: DIATOMS, Database drIven Application Testing tOol ModuleS
 *
 *--------------------------------------------------------------------*/


/*   The following methods were commented out by Adam M. Smith to 
 *   removed Mathematica dependencies.
 *
 *   public double getHarmonicNumberBound(boolean useTMax)
 *   
 *   public Set reduceUsingRandom(int desiredReductionSize,
 *				 int desiredReductions)
 *
 *   public Set prioritizeUsingRandom(int desiredPermutations)
 *   
 *   public SetCover deriveWorstCase()
 */

package redupr;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.lang.Cloneable;

import java.io.Serializable;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream; 
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

//import com.thoughtworks.xstream.XStream;

// Commented out by Adam Smith
//import com.wolfram.jlink.*;

/**
 *  This class represents an instance of the SetCover problem.
 *  This instance is the dual of the HittingSet instance.
 *
 *  @author Gregory M. Kapfhammer 9/17/2005
 */

public class SetCover implements Cloneable, Serializable
{

    /** The dual of the subsets of the HittingSet (i.e., S in
      HittingSet). This represents all of the different Requirement
      Subsets that were inside of the HittingSet instance. -- U -- */
    private LinkedHashSet requirementSubsetUniverse;
    //private Set requirementSubsetUniverse;

    /** The dual of the universe of the HittingSet (i.e., U in the
	HittingSet).  This represents all of the different subsets 
	as they correspond to what the SingleTests actually hit. 
	-- S -- */
    private LinkedHashSet testSubsets;
    //private Set testSubsets;

    /** The number of times that the outerWhile was executed */
    private int outerWhileExecute;

    /** A listing of the number of times that the outerWhile was
	executed in reduction when we are performing
	prioritization! */
    private ArrayList outerWhileExecuteList;

    /** keep track of the number of times that we execute the 
      outer while loop of the prioritization algorithm */
    private int outerWhileExecutePrioritization;

    /** The Set that includes all of the SingleTestSubsets inside
	of the reduced test suite */
    private LinkedHashSet reducedSingleTestSubsets;
    //private Set reducedSingleTestSubsets;

    /** This is the instance of the SetCover that existed before 
	the reduction was performed */
    private SetCover beforeReduction;

    /** This is the random number generator that is set with a default
	seed in order to ensure repeatable results */
    private Random randomizer;

    /** The default seed for the random number generator */
    public static final long DEFAULT_SEED = 4160;

    /** The cost of the last test case */
    private static final double LAST_TEST_COST = 1.0;

    /** The numerator of the effectiveness fractions */
    private static final double NUMERATOR_EFFECTIVE = 1.0;

    /** The cost of a test case that does not cover anything */
    private static final int NO_COVER_COST = Integer.MAX_VALUE;

    /** The time that was required to execute the reduction
     algorithm by itself */
    private long reductionTime;

    /** The time that was required to execute the prioritization
     algorithm */
    private long prioritizationTime;

    /** The seed that we should use for randomizer */
    public static long chosenSeed;

    /**
     *  Default constructor that initializes all sets to the empty set.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public SetCover()
    {

	requirementSubsetUniverse = new LinkedHashSet();
	testSubsets = new LinkedHashSet();
	reducedSingleTestSubsets = new LinkedHashSet();
	randomizer = new Random(DEFAULT_SEED);

	outerWhileExecute = 0;
	outerWhileExecutePrioritization = 0;
	outerWhileExecuteList = new ArrayList();

	reductionTime = (long)0;
	prioritizationTime = (long)0;

// 	requirementSubsetUniverse = 
// 	    Collections.synchronizedSet(new LinkedHashSet());
// 	testSubsets = 
// 	    Collections.synchronizedSet(new LinkedHashSet());
// 	reducedSingleTestSubsets =
// 	    Collections.synchronizedSet(new LinkedHashSet());

    }

    /**
     *  Returns the SetCover instance that existed before the
     *  reduction was performed.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public SetCover getBeforeReduction()
    {

	return beforeReduction;

    }
    
   /**
     *  Set the SetCover instance that existed before the reduction
     *  was performed.  Can be used before running the reduction
     *  algorithm and also for the purposes of testing.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public void setBeforeReduction(SetCover cover)
    {

	beforeReduction = cover;

    }

    /**
     *  @author Gregory M. Kapfhammer 3/7/2007
     */
    public long getReductionTime()
    {

	return reductionTime;

    }

    /**
     *  @author Gregory M. Kapfhammer 3/7/2007
     */
    public long getPrioritizationTime()
    {

	return prioritizationTime;

    }

    /**
     *  Constructs a SetCover instance from a LinkedHashSet of 
     *  SingleTestSubsets.  This method might not be directly useful
     *  during experimentation.  
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public static SetCover constructSetCover(LinkedHashSet answer)
    {

	SetCover cover = new SetCover();

	// go through all of the SingleTestSubsets and add in the 
	// tests and the Requirements
	Iterator answerIterator = answer.iterator();
	while( answerIterator.hasNext() )
	    {

		// extract the currentTestSubset from the iterator
		SingleTestSubset currentTestSubset = 
		    (SingleTestSubset) answerIterator.next();

		// add in the SingleTestSubset
		cover.addSingleTestSubset(currentTestSubset);

		// add in the requirementSubset to the Universe
		cover.
		    addToRequirementSubsetUniverse(currentTestSubset.
						   getRequirementSubsetSet());

	    }

	return cover;

    }

	/**
	 *	Checks to see if the given LinkedHashSet of SingleTests 
	 * covers the requirementSubsetUnivers
	 *
	 * @author Adam M. Smith
	 */
	 
	 public boolean coversRequirementSubsetUniverse(LinkedHashSet tests)
	 {
	 	Iterator testIterator = tests.iterator();
	 	
	 	LinkedHashSet requirements = new LinkedHashSet();
	 	
	 	requirements.addAll(requirementSubsetUniverse);
	 	
	 	while(testIterator.hasNext())
	 	{
	 		SingleTestSubset currentSingleTestSubset = getSingleTestSubsetFromSingleTest((SingleTest)testIterator.next());
	 		
	 		Iterator coveredRequirementsIterator = currentSingleTestSubset.getRequirementSubsetSet().iterator();
	 		
	 		while(coveredRequirementsIterator.hasNext())
	 		{
	 			RequirementSubset currentRS = (RequirementSubset)coveredRequirementsIterator.next();
	 			if(requirements.contains(currentRS))
	 				requirements.remove(currentRS);
	 		}
	 	}
	 	
	 	if(requirements.size() == 0)
	 		return true;
	 	
	 	else
	 		return false;
	 		 	
	 }


	/*----------------------------------------------------------------------
	* This method takes a SingleTest object and returns the corresponding
	* SingleTestSubject object
	*--------------------------------------------------------------------*/
	private SingleTestSubset getSingleTestSubsetFromSingleTest(SingleTest test) {
		// Extract an iterator for the testSubsets
		Iterator testSubsetsIterator = testSubsets.iterator();
	
		// While there are more TestSubsets...
		while (testSubsetsIterator.hasNext()) {
			// Get the current SingleTestSubset
			SingleTestSubset currentSingleTestSubset = 
				(SingleTestSubset) testSubsetsIterator.next();
							
			// Get the SingleTest object that corresponds to the 
			// currentSingleTestSubset object
			SingleTest currentSingleTestFromTS = currentSingleTestSubset.getTest();
							

			// If the current SingleTest from the testSubset iterator
			// has the same name as the current SingleTest from the 
			// requirementSubset iterator, then we have a match. 	
			if ((currentSingleTestFromTS.getName()).equals(test.getName()))
				return currentSingleTestSubset;	
			}
		
		return null;	
	}

	/**
	 * Saves a setcover as two files that can be read in
	 * using the R class CoverageEffectiveness.R
	 * 
	 * This method calls the class GenerateCoverageEffectivenessData.java
	 * that was written by Gavilan Steinman
	 *
	 * @author Adam M. Smith
	 */

	public static void saveSetCoverAsCoverageAndTimeFile(SetCover cover,String coverage,String time)
	{
		GenerateCoverageEffectivenessData g = new GenerateCoverageEffectivenessData(cover);
 	   g.saveTimingData(time);
      g.saveCoverageData(coverage);
	}


	/**
	 * Constructs an instance of SetCover from an xml file.
	 * 
	 * This method returns a SetCover object
	 *
	 * @author Adam M. Smith
	 */
	/* 
	 public static SetCover constructSetCoverFromXML(String fileName)
	 {	
		XStream xstream = new XStream();
	 	FileInputStream input = null;
	 	try 
	 	{
	 		input = new FileInputStream(fileName);
	 	}
	 	catch(java.io.FileNotFoundException e)
	 	{
	 		System.out.println("File "+fileName+" not found");
	 	}
		return ((SetCover) xstream.fromXML((InputStream) input));
	 }
	*/

	/**
	 * Constructs an instance of SetCover from a binary matrix coverage
	 * representation.
	 * 
	 * This method returns a SetCover object
	 *
	 * @author Adam M. Smith
	 */
	 
	 public static SetCover constructSetCoverFromBinary(String matrix, String time) {
	 	 	
	 	 SetCover cover = new SetCover();
	 	 	
	 	// Initialize tests to -1 because of the summary column.  The first row is popped before
	 	// counting so it starts at 0
		int numTests = -1;
		int numReqs = 0;
	
		ArrayList requirementSubsets = new ArrayList();
		ArrayList singleTests = new ArrayList();
		ArrayList singleTestSubsets = new ArrayList();
	
		Scanner matrixScanner = null;
		Scanner timeScanner = null;
	
		File matrixFile = new File(matrix);
		File timeFile = new File(time);
	
		// Try to read from the file, else print an error
		try 
		{
			matrixScanner = new Scanner(matrixFile).useDelimiter("\n");
			timeScanner = new Scanner(timeFile);
		}
		catch(java.io.FileNotFoundException e) 
		{
			System.out.println("File Not Found");

			return null;	 
		}		
		
		// Ignore the first two label entries
		timeScanner.next();
		timeScanner.next(); 	
				
		// get the first row.  The first row must be preprocessed to get the 
		// total number of tests.  
		
		// This is extremely sloppy in my opinion. 
		// I could change the way I find the number of tests by using the
		// time file, but the way I do it now only counts on the matrix file being correct.
		// I need to find a way to count the number of requirements in a better way as well.
		
		String firstRow = (String) matrixScanner.next();
		Scanner firstRowScanner = new Scanner(firstRow);
		
		while (firstRowScanner.hasNext()) 
		{
			firstRowScanner.next();
			numTests++;
		}
		
		while (matrixScanner.hasNext())
		{
			matrixScanner.next();
			numReqs++;
		}
		
		// Make the singleTest Objects 
		
		for(int i = 0; i < numTests;i++)
		{
			//Ignore the test label
			timeScanner.next();
			double testCost = Double.parseDouble(timeScanner.next());
			SingleTest currentTest = new SingleTest("SingleTest"+i,i,testCost);
			singleTests.add(currentTest);
			singleTestSubsets.add(new SingleTestSubset(currentTest));
		}
		
		
		// reset the scanner
		try 
		{
			matrixScanner = new Scanner(matrixFile).useDelimiter("\n");
		}
		catch(Exception e) {System.out.println("File Not Found");}
		
		// while there is another line to scan
		for(int i = 0; i < numReqs;i++) 
		{			
			// Create a new RequirementSubset
			RequirementSubset currentRS = new RequirementSubset("RequirementSubset"+i,i);
			
			// Get the next line
			String row = (String) matrixScanner.next();
			
			// Create a Scanner to scan the line
			Scanner rowScanner = new Scanner(row);
			
			// For each test column			
			for(int j=0; j < numTests; j++)
			{
				// If the test is covered
	
				if(Integer.parseInt(rowScanner.next())==1)
				{
					// Add the coresponding SingleTest object to the RS
					currentRS.addCoveringTest( (SingleTest) singleTests.get(j));
					
					// Add the RS to the to the corresponding STS object
					((SingleTestSubset) singleTestSubsets.get(j)).addRequirementSubset(currentRS);
				}
			}
			
			// Add the RequirementSubset to the ArrayList
			requirementSubsets.add(currentRS);
		}		
		
		for(int i = 0; i < numTests; i++) 
		{
			cover.addSingleTestSubset((SingleTestSubset) singleTestSubsets.get(i));
		}
		
		for(int i = 0; i < numReqs; i++)
		{
			cover.addRequirementSubset((RequirementSubset) requirementSubsets.get(i));
		}
		
		return cover;
	
	}

    /**
     *  Constructs an instance of SetCover from a HashMap that lists
     *  the costs for all of the different tests.  Currently, we are
     *  assuming that the output that is stored inside of the file
     *  with the fileName is in the Ant JUnit output format.  This
     *  does not produce any coverage information and as such coverage
     *  details will have to be included in the SetCover instance
     *  through some type of alternate means.
     *  
     *  Later this should probably be extended with a new parameter
     *  that points to coverage information.  But, we do not yet know
     *  what the format of this second parameter would look like.
     *
     *  @author Gregory M. Kapfhammer 10/4/2005
     */
    public static SetCover constructSetCover(String fileName)
    {

	SetCover cover = new SetCover();

	// note that the HashMap is storing the (test name, test time)
	// pairs and we can use this to build up the SetCover instance
	HashMap testTimingsMap = Util.readTestTimings(fileName);

// 	System.out.println("ok,here -- " + testTimingsMap);
		
	// the test index is simply incremented as we create the 
	// new SingleTests ; this index is arbitrary and just used
	// for organization and debugging purposes
	int testIndex = 1;

	// extract an iterator of all of the values inside of the 
	// HashMap (each pair represents a new SingleTest for which
	// we should also have some coverage information)
	Iterator testTimingsIterator = testTimingsMap.keySet().iterator();
	while( testTimingsIterator.hasNext() )
	    {

		// extract the name of the test
		String currentTestName = 
		    (String) testTimingsIterator.next();

		// extract the time overhead of the test
		Double currentTestTime = 
		    (Double) testTimingsMap.get(currentTestName);

		// create a new SingleTest that has this name
		// and the current value of the testIndex
		SingleTest currentTest =
		    new SingleTest(currentTestName, testIndex);

		// go to the next value for the testIndex
		testIndex++;

		// set the time for this new SingleTest
		currentTest.setCost(currentTestTime.doubleValue());

		// create a new SingleTestSubset that is supposed
		// to be placed inside of this SetCover instance that
		// we are trying to produce
		SingleTestSubset currentTestSubset = 
		    new SingleTestSubset(currentTest);

		// place the STS inside of the SetCover
		cover.addSingleTestSubset(currentTestSubset);

	    }


	return cover;

    }

    /**
     *  This method sums the purchase prices in a listing of
     *  SingleTests.  Note that it is likely more useful to sum the
     *  costs because from a testing perspective the costs are
     *  directly related to either the time or the space overheads.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public static double sumPurchasePrices(Set answer)
    {

	double priceSum = 0.0;

	// extract an iterator of the SingleTests
	Iterator testsIterator = answer.iterator();
	while( testsIterator.hasNext() )
	    {

		SingleTest currentTest = (SingleTest) testsIterator.next();
		priceSum = priceSum + currentTest.getPurchasePrice();

	    }

	return priceSum;

    }

    /**
     *  This method sums the costs in a listing of SingleTests.  Note
     *  that this is likely very useful from a testing perspective
     *  because it is these costs that correspond to the time and
     *  space overhead that will be incurred when testing occurs.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public static double sumCosts(Set answer)
    {

	double costSum = 0.0;

	// extract an iterator of the SingleTests
	Iterator testsIterator = answer.iterator();
	while( testsIterator.hasNext() )
	    {

		SingleTest currentTest = (SingleTest) testsIterator.next();
		costSum = costSum + currentTest.getCost();

	    }

	return costSum;

    }

    /**
     *  Determine the size of the answer.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public static int size(Set answer)
    {

	return answer.size();

    }

    /**
     *  This method sums the costs of a SetCover that represents an
     *  entire test suite.  This method can be used for a SetCover that
     *  has not already been reduced and for which no answer has yet
     *  to be produced.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public double sumInnerCosts()
    {

	double sumInnerCosts = 0.0;

	// extract an iterator of the testSubsets
	Iterator testSubsetsIterator = testSubsets.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		// extract the current SingleTestSubset
		SingleTestSubset currentSubset = 
		    (SingleTestSubset) testSubsetsIterator.next();
		
		// extract the SingleTest for the STS
		SingleTest currentTest = currentSubset.getTest();

		// add to the current value for the costs
		sumInnerCosts = sumInnerCosts + 
		    currentTest.getCost();

	    }

	return sumInnerCosts;

    }

    /**
     *  This method determines the size for the test suite that
     *  the SetCover represents.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public int innerSize()
    {

	return testSubsets.size();

    }

    /**
     *  Returns the listing of reducedSingleTestSubsets.  This can be
     *  used along side the LinkedHashSet returned by the
     *  reduceUsingGreedy method in order to analyze the final output.
     *  
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    public LinkedHashSet getReducedSingleTestSubsets()
	//public Set getReducedSingleTestSubsets()
    {

	return reducedSingleTestSubsets;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void newRandomizer()
    {

	randomizer = new Random();

    }

    /**
     *  Add in a new RequirementSubset to the universe.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public void addRequirementSubset(RequirementSubset s)
    {

		requirementSubsetUniverse.add(s);

    }

	/**
     *  Add in a set of RequirementSubsets to the universe.
     *  
     *  @author Adam M. Smith 07/19/2007
     */
    public void addRequirementSubsets(LinkedHashSet s)
    {
	 	requirementSubsetUniverse.addAll(s);
    }

	
    /**
     *  Returns the requirementSubsetsUniverse.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public Set getRequirementSubsetUniverse()
    {

	return requirementSubsetUniverse;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setRequirementSubsetUniverse(LinkedHashSet universe)
    {

	requirementSubsetUniverse = universe;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
   public void addToRequirementSubsetUniverse(Set moreToUniverse)
    {

	requirementSubsetUniverse.addAll(moreToUniverse);

    }
	 
	/**
 	 *  Remove a requirementSubset
	 *
	 *	 @author Adam M. Smith 7/09/2007
	 */
	public void removeRequirementSubset(RequirementSubset requirementSubsetToRemove)
	{
		
		requirementSubsetUniverse.remove(requirementSubsetToRemove);
	
	}

	/**
 	 *  Remove multiple requirementSubsets
	 *
	 *	 @author Adam M. Smith 7/09/2007
	 */
	public void removeRequirementSubsets(LinkedHashSet requirementSubsetsToRemove)
	{
		
		requirementSubsetUniverse.removeAll(requirementSubsetsToRemove);
	
	}
	
	/**
 	 *  Remove a SingleTestSubset
	 *
	 *	 @author Adam M. Smith 7/30/2007
	 */
	public void clearSingleTestSubsets()
	{
		
		testSubsets.clear();
	
	}
	
	/**
 	 *  Remove a SingleTestSubset
	 *
	 *	 @author Adam M. Smith 7/09/2007
	 */
	public void removeSingleTestSubset(SingleTestSubset singleTestSubsetToRemove)
	{
		
		testSubsets.remove(singleTestSubsetToRemove);
	
	}
	
	/**
 	 *  Remove multiple SingleTestSubsets
	 *
	 *	 @author Adam M. Smith 7/09/2007
	 */
	public void removeSingleTestSubsets(LinkedHashSet singleTestSubsetsToRemove)
	{
		
		testSubsets.removeAll(singleTestSubsetsToRemove);
	
	}
	
	
	
    /**
     *  Add in a new SingleTestSubset.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
   public void addSingleTestSubset(SingleTestSubset sts)
    {

	testSubsets.add(sts);

    }
	 
	 /**
     *  Add SingleTestSubsets.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
   public void addSingleTestSubsets(LinkedHashSet sts)
    {

	testSubsets.addAll(sts);

    }



    /**
     *  Returns the entire set of SingleTestSubsets.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public LinkedHashSet getTestSubsets()
	//public Set getTestSubsets()
    {

	return testSubsets;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public void setTestSubsetsSet(LinkedHashSet set)
    {

	testSubsets = set;

    }

    /**
     *  Returns the number of times that the outer while loop 
     *  executed for a call to the greedy approximation.  This is 
     *  useful as a measure of the performance of the greedy test
     *  suite reduction algorithm.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */



    public int getOuterWhileIterations()
    {

	return outerWhileExecute;

    }

    /**
     *  @author Gregory M. Kapfhammer 10/13/2005
     */
    public int getOuterWhileIterationsPrioritization()
    {

	return outerWhileExecutePrioritization;

    }

    /**
     *  @author Gregory M. Kapfhammer 3/7/2007
     */
    public ArrayList getOuterWhileExecuteList()
    {

	return outerWhileExecuteList;

    }

    /**
     *  This method calculates the nth Harmonic Number that gives a
     *  bound (upper, I think) on the cost of a reduced test suite.
     *  
     *  NOTE: I am not sure that this method will serve a practical
     *  purpose during experimentation.
     *
     *  @author Gregory M. Kapfhammer 10/3/2005
     */
    
/*

//	Commented out by Adam M. Smith on Nov 30th to avoid MathLink, KernelLink, and Jlink


   public double getHarmonicNumberBound(boolean useTMax)
    {

	double harmonicNumberBound = 0.0;

	// we are assuming the request is for the nth harmonic number
	// and we will only change this if useTMax is true
	double harmonicNumberRequest = testSubsets.size();

// 	System.out.println("Normal Hn = " + testSubsets.size());

	// this is the size of the test suite that covers the most
	// requirements; we will only change the value of this
	// variable if useTMax is true and we have to iterate through
	// all of the tests and see what they cover
	int testSuiteMax = 0;

	// we are supposed to use the size of the requirement subset
	// of the largest covering test and thus we have to find this
	// SingleTest first
	if( useTMax )
	    {

		Iterator testSubsetsIterator = testSubsets.iterator();
		while( testSubsetsIterator.hasNext() )
		    {

			SingleTestSubset currentTestSubset = 
			    (SingleTestSubset) testSubsetsIterator.next();

			int currentTestSuiteSize = 
			    currentTestSubset.
			    getRequirementSubsetSet().size(); 

			if( currentTestSuiteSize > testSuiteMax )
			    {

				testSuiteMax = currentTestSuiteSize;

			    }

		    }

		harmonicNumberRequest = testSuiteMax;

// 		System.out.println("test suite max: " + 
// 				   testSuiteMax);

	    }

	KernelLink ml = null;
	String mathematicaConnect = 
	    "-linkmode launch -linkname \'math -mathlink\'";

	try 
	    {
		
		ml = MathLinkFactory.
		    createKernelLink(mathematicaConnect);
		
// 		System.out.println("after kernel link");

	    } 

	catch (MathLinkException e) 
	    {
		
		System.out.println("Fatal error opening link: " + 
				   e.getMessage());
		e.printStackTrace();
	    
	    }

	try 
	    {
			
		// Get rid of the initial InputNamePacket the kernel
		// will send when it is launched.
		ml.discardAnswer();
			
		// this will store the final string that we will
		// send to Mathematica
		StringBuffer harmonicBuffer = new StringBuffer();

		harmonicBuffer.append("HarmonicNumber[");
		harmonicBuffer.append(harmonicNumberRequest);
		harmonicBuffer.append("]");

		// send the final string to Mathematica through 
		// J/Link and retrieve the answer as a string
		String solve = ml.
		    evaluateToOutputForm( harmonicBuffer.toString(), 0 );
		    
// 		System.out.println("solve = " + solve);

		Double finalHarmonic = new Double(solve);
		harmonicNumberBound = finalHarmonic.doubleValue();

	    }

	catch(Exception e)
	    {

		System.out.println("Problem computing harmonic number");
		e.printStackTrace();

	    }

	
	finally 
	    {
		
		ml.close();
		
	    }

	return harmonicNumberBound;

    }
*/

    /**
     *  Produces a random reduction of the test suite that is guaranteed
     *  to be the same size as the reduced test suite that was produced
     *  using the greedy approach.
     *
     *  This technique allows for the creation of an experimental
     *  control when we are evaluating different reduction techniques.
     *  Note that the size of the test suite and thus the percent
     *  reduction in space will be the same as the test that was 
     *  greedily reduced.  
     *
     *  However, the reduction in time overhead can vary considerably
     *  and the final change in the defect detection ratio can also 
     *  change.  This type of experimental control is also used in 
     *  papers by Memon and Rothermel.
     *  
     *  NOTE: Note that this just produces a single Set that
     *  represents a test suite.  This is not really that useful from
     *  an experimental standpoint because we need a large number of
     *  random test suites in order to account for the variability in
     *  the random reductions.
     * 
     *  @author Gregory M. Kapfhammer 10/13/2005
     */
    public Set reduceUsingRandom(int targetSize)
    {

	// take the timing before
	long reductionTimeBefore = System.currentTimeMillis();

	LinkedHashSet coverPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);

	System.out.println("ChosenSeed = " + chosenSeed);

	// initialize the randomizer (comment out if you want default)
	randomizer = new Random(chosenSeed);

	// shuffle the array using our randomizer that has been 
	// initialized with a specifc random seed
	Collections.shuffle(testSubsetsList, randomizer);
	
	// this is the number of tests that we have already included
	// inside of the complete listing; initially we have not
	// included any tests in the coverPickSets
	int includedTests = 0;

	// extract an Iterator of the shuffled testSubsetsList
	// and then select the first targetSize tests and 
	// place them inside of coverPickSets
	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() && 
	       includedTests < targetSize )
	    {

// 		SingleTestSubset currentTestSubset = 
// 		    (SingleTestSubset) testSubsetsIterator.next();

// 		coverPickSets.add(currentTestSubset.getTest());

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		coverPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

		includedTests++;

	    }

	// take the timing after
	long reductionTimeAfter = System.currentTimeMillis();
	reductionTime = (reductionTimeAfter - reductionTimeBefore);

	return coverPickSets;

    }

    /**
     *  Produces a random prioritization of the test suite.
     *
     *  This technique allows for the creation of an experimental
     *  control when we are evaluating different prioritization
     *  techniques.  This type of experimental control is also used in
     *  papers by Memon and Rothermel.
     *  
     *  NOTE: Note that this just produces a single Set that
     *  represents a test suite.  This is not really that useful from
     *  an experimental standpoint because we need a large number of
     *  random test suites in order to account for the variability in
     *  the random reductions.
     * 
     *  @author Gregory M. Kapfhammer 12/7/2005
     */
    public Set prioritizeUsingRandom()
    {

	// take the timing before
	long prioritizationTimeBefore = System.currentTimeMillis();

	LinkedHashSet coverPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);

	//System.out.println("ChosenSeed = " + chosenSeed);

	// initialize the randomizer (comment out if you want default)
	//randomizer = new Random(chosenSeed);

		randomizer = new Random(13245);
		
	// shuffle the array using our randomizer that has been 
	// initialized with a specifc random seed
	Collections.shuffle(testSubsetsList, randomizer);

	// comment out for now!

	// add everything inside of the shuffled list
// 	coverPickSets.addAll(testSubsetsList);

// 	// added by gmk on march 6
// 	reducedSingleTestSubsets.
// 	    addAll( testSubsetsList );

	// extract an Iterator of the shuffled testSubsetsList
	// and then select the first targetSize tests and 
	// place them inside of coverPickSets
	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

// 		SingleTestSubset currentTestSubset = 
// 		    (SingleTestSubset) testSubsetsIterator.next();

// 		coverPickSets.add(currentTestSubset.getTest());

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		coverPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

	    }

	// take the timing after
	long prioritizationTimeAfter = System.currentTimeMillis();
	prioritizationTime = (prioritizationTimeAfter -
			      prioritizationTimeBefore);

	return coverPickSets;

    }

    /**
     *  Reduces the test suite by taking tests cases from the rear 
     *  of the test suite until we simply reach the target size.
     *  This is supposed to be paired with the concept of reverse
     *  prioritization.
     *  
     *  @author Gregory M. Kapfhammer 12/3/2005
     */
    public Set reduceUsingReverse(int targetSize)
    {

	// take the timing before
	long reductionTimeBefore = System.currentTimeMillis();

	LinkedHashSet coverPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);

	// reverse this ArrayList 
	Collections.reverse(testSubsetsList);
	
	// this is the number of tests that we have already included
	// inside of the complete listing; initially we have not
	// included any tests in the coverPickSets
	int includedTests = 0;

	// extract an Iterator of the testSubsetsList and then select
	// the first targetSize tests and place them inside of
	// coverPickSets
	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() && 
	       includedTests < targetSize )
	    {

// 		SingleTestSubset currentTestSubset = 
// 		    (SingleTestSubset) testSubsetsIterator.next();

// 		coverPickSets.add(currentTestSubset.getTest());

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		coverPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

		includedTests++;

	    }

	// take the timing after
	long reductionTimeAfter = System.currentTimeMillis();
	reductionTime = (reductionTimeAfter - reductionTimeBefore);

	return coverPickSets;

    }

    /**
     *  Prioritize the test suite by reversing the order.  This basic
     *  idea was suggested by Chandra Krintz when I met with her at
     *  UCSB.
     *  
     *  @author Gregory M. Kapfhammer 12/3/2005
     */
    public Set prioritizeUsingReverse()
    {

	// take the timing before
	long prioritizationTimeBefore = System.currentTimeMillis();
	
	LinkedHashSet coverPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);

	// reverse this ArrayList 
	Collections.reverse(testSubsetsList);
       
	// we have to go through them all and extract the 
	// SingleTests since that what we are included.  This
	// is not so good in terms of performance because
	// we end up creating a O(n2) algorithm
	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

// 		coverPickSets.
// 		    add( ( (SingleTestSubset) testSubsetsIterator.next() ).
// 			 getTest() );

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		coverPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

	    }	

	// take the timing after
	long prioritizationTimeAfter = System.currentTimeMillis();
	prioritizationTime = (prioritizationTimeAfter - 
			      prioritizationTimeBefore);

	return coverPickSets;

    }

    /**
     *  Prioritize the test suite by simply keeping the same 
     *  order.  This is the untreated ordering.  We need this 
     *  as an experiment control.
     *  
     *  @author Gregory M. Kapfhammer 7/10/2007
     */
    public Set prioritizeUsingOriginal()
    {

	// take the timing before
	long prioritizationTimeBefore = System.currentTimeMillis();
	
	LinkedHashSet coverPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);
       
	// we have to go through them all and extract the SingleTests
	// since that what we are included.  This is not so good in
	// terms of performance because we end up creating a O(n2)
	// algorithm
	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		coverPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

	    }	

	// take the timing after
	long prioritizationTimeAfter = System.currentTimeMillis();
	prioritizationTime = (prioritizationTimeAfter - 
			      prioritizationTimeBefore);

	return coverPickSets;

    }

    /**
     *  Reduces the test suite by taking tests cases from the front
     *  of the test suite until we simply reach the target size.
     *  This is supposed to be paired with the concept of reverse
     *  prioritization.
     *  
     *  @author Gregory M. Kapfhammer 3/10/2007
     */
    public Set reduceUsingOriginal(int targetSize)
    {

	// take the timing before
	long reductionTimeBefore = System.currentTimeMillis();

	LinkedHashSet coverPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);

	// this is the number of tests that we have already included
	// inside of the complete listing; initially we have not
	// included any tests in the coverPickSets
	int includedTests = 0;

	// extract an Iterator of the testSubsetsList and then select
	// the first targetSize tests and place them inside of
	// coverPickSets
	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() && 
	       includedTests < targetSize )
	    {

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		coverPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

		includedTests++;

	    }

	// take the timing after
	long reductionTimeAfter = System.currentTimeMillis();
	reductionTime = (reductionTimeAfter - reductionTimeBefore);

	return coverPickSets;

    }

   /**
     *  Produces a random reduction of the test suite that is guaranteed
     *  to be the same size as the reduced test suite that was produced
     *  using the greedy approach.
     *
     *  This technique allows for the creation of an experimental
     *  control when we are evaluating different reduction techniques.
     *  Note that the size of the test suite and thus the percent
     *  reduction in space will be the same as the test that was 
     *  greedily reduced.  
     *
     *  However, the reduction in time overhead can vary considerably
     *  and the final change in the defect detection ratio can also 
     *  change.  This type of experimental control is also used in 
     *  papers by Memon and Rothermel.
     *  
     *  NOTE: this version using Mathematica to create a specified
     *  number of test suites that are all randomly reduced.
     *
     *  @author Gregory M. Kapfhammer 10/13/2005
     */
/*

 * Commented out on Nov 30, 2007 by Adam M. Smith to avoid MathLink, Jlink, KernelLink dependencies

    public Set reduceUsingRandom(int desiredReductionSize,
				 int desiredReductions)
    {

	// this is the final "set of sets" that was produced it must
	// included a total of desiredReductions that are all of size
	// desiredReductionSize
	LinkedHashSet setOfReducedSets = new LinkedHashSet();
	
	// this HashMap will store the test names and the actual
	// SingleTestSubsets so that they can be retrieved later
	LinkedHashMap fullTestSuite = new LinkedHashMap();

	// create the Strings that are going to be essentially
	// constants for every call to Mathematica and only operate on
	// the other variables that are indeed going to change
	String loadCombinatorica = "<< DiscreteMath`Combinatorica`;";
	String finalTestSuiteReductions = "finalTestSuiteReductions = {};";
	String bigCodeSegment = 
     "testSubsets = Subsets[testSuiteReduction, {desiredReductionSize}];" + 
     "randomTestSubsetIndex = Random[Integer, Length[testSubsets]];" + 
     "currentTestSubset = testSubsets[[randomTestSubsetIndex]];" + 
     "indexIntoTestSubsets = 0;" + 
     "While[Length[finalTestSuiteReductions] < desiredReductions," +  
     "randomTestSubsetIndex = Random[Integer, {1,Length[testSubsets]}];" + 
     "currentTestSubset = testSubsets[[randomTestSubsetIndex]];" + 
     "currentTestSubsetPermutation = RandomPermutation[currentTestSubset];" + 
     "If[MemberQ[finalTestSuiteReductions, currentTestSubsetPermutation]," +
        "duplicate," +  
     "AppendTo[finalTestSuiteReductions, currentTestSubsetPermutation]]];" + 
//"SameQ[Sort[finalTestSuiteReductions], Union[finalTestSuiteReductions]]" +
     "finalTestSuiteReductions";

	// the size of the reductions that is being requested
	String desiredReductionSizeMath = "desiredReductionSize = " + 
	    desiredReductionSize + ";";

	// the number of reductions that are being requested
	String desiredReductionsMath = "desiredReductions = " + 
	    desiredReductions + ";";
	
	// this StringBuffer will store the final listing of the 
	// tests that will be submitted to the Mathematica
	StringBuffer testSuiteReduction = new StringBuffer();

	// we have to add the beginning of this String 
	testSuiteReduction.append("testSuiteReduction = {");

	// go through the testSubsets and build up a String that 
	// represents them all -- on the side, build up a HashMap
	// that is for the String and the SingleTestSubset that 
	// is associated with the String
	
	Iterator testSetIterator = testSubsets.iterator();
	while( testSetIterator.hasNext() )
	    {

		// extract the next SingleTestSubset
		SingleTestSubset nextTestSubset = 
		    (SingleTestSubset) testSetIterator.next();

		// place this inside the HashMap using the key 
		// and value pair that is formatted at (test name,
		// nextTestSubset)
		fullTestSuite.put(nextTestSubset.getTest().getName(),
				  nextTestSubset.getTest());

		// put the name of the test inside of the StringBuffer
		// so that we can submit it to Mathematica
		testSuiteReduction.append(nextTestSubset.getTest().
					  getName());

		// there is still going to be another test that
		// we have to append and thus we need to include
		// the final comma at the end
		if( testSetIterator.hasNext() )
		    {

			testSuiteReduction.append(", ");

		    }

	    }

	// we have to add on the closing to the test suite for reduction
	testSuiteReduction.append("};");

	//System.out.println(fullTestSuite);
	
	// we are now done building up everything that has to be sent
	// to Mathematica and we just to initialize the mathematica
	// connection and then send the information off and handle
	// the result (more String parsing required)

	KernelLink ml = null;
	String mathematicaConnect = 
	    "-linkmode launch -linkname \'math -mathlink\'";

	try 
	    {
		
		ml = MathLinkFactory.
		    createKernelLink(mathematicaConnect);
		
	    } 

	catch (MathLinkException e) 
	    {
		
		System.out.println("Fatal error opening link: " + 
				   e.getMessage());
		e.printStackTrace();
	    
	    }

	try 
	    {
			
		// Get rid of the initial InputNamePacket the kernel
		// will send when it is launched.
		ml.discardAnswer();
			
// 		System.out.println( //loadCombinatorica + 
// 				    //"Pause[2];" + 
// 				    finalTestSuiteReductions +
// 				    desiredReductionsMath + 
// 				    desiredReductionSizeMath + 
// 				    testSuiteReduction.toString() + 
// 				    bigCodeSegment );

		// load the combinatorica package separately; this
		// will be "resident" when we execute the next command
		// (timing might dictate that this approach does not
		// always execute correctly)
		ml.evaluate(loadCombinatorica);
		ml.discardAnswer();
		
		//Thread.currentThread().sleep(3000);

		// send the final string to Mathematica through 
		// J/Link and retrieve the answer as a string
		String solve = ml.
		    evaluateToOutputForm( //loadCombinatorica + 
					  //"Pause[3];" + 
					  finalTestSuiteReductions +
					  desiredReductionsMath + 
					  desiredReductionSizeMath + 
					  testSuiteReduction.toString() + 
					  bigCodeSegment, 0 );
		    
		//System.out.println("solve = " + solve);

		// we have the final String from Mathematica and 
		// now we need to parse it correctly and then 
		// return the final "set of sets" back to the 
		// calling program
		ArrayList testList = Util.
		    generateTestNames(solve);

		// we need an ArrayList that is going to store each of
		// the individual reductions; NOTE that this cannot be
		// a Set because then they will appear to be the same
		// since the set does not preserve order!
		ArrayList internalSet = new ArrayList();
		
		// for debugging purposes (corresponds to println)
		int i = 1;

		// go through each one of the separate ArrayLists and 
		// retrieve the SingleTests that are inside of the 
		// HashMap in order to make the final "Set of Sets"
		Iterator testListIterator = testList.iterator();
		while( testListIterator.hasNext() )
		    {

			// extract an ArrayList and an Iterator the
			// current internal listing
			ArrayList internalTestList = 
			    (ArrayList) testListIterator.next();
			Iterator internalTestListIterator = 
			    internalTestList.iterator();
			while( internalTestListIterator.hasNext() )
			    {

				// extract the current name of the
				// test and then check into the
				// HashMap
				String currentTestName = 
				    (String) internalTestListIterator.next();

				//System.out.println(currentTestName);

				// put in the real SingleTest
				internalSet.add(fullTestSuite.
						get(currentTestName));

			    }			

			//System.out.println(i + " finished: " + 
			//		   internalSet);
			i++;

			// add in the current internalSet and then
			// clone it and clear it so that it can 
			// be used again while avoiding the Java
			// reference problem
			setOfReducedSets.add(internalSet);
			internalSet = (ArrayList)internalSet.clone();
			internalSet.clear();

		    }

	    }

	catch(Exception e)
	    {

		System.out.println("Problem reducing in a random fashion.");
		e.printStackTrace();

	    }

	
	finally 
	    {
		
		ml.close();
		
	    }

	return setOfReducedSets;

    }
*/
    /**
     *  Produces a random prioritization of the test suite that is
     *  guaranteed to be the same size as the original test suite.
     *
     *  This technique allows for the creation of an experimental
     *  control when we are evaluating different prioritization
     *  techniques.  
     *  
     *  NOTE: this version is using Mathematica to create a specified
     *  number of test suites that are all randomly prioritized.
     *
     *  @author Gregory M. Kapfhammer 12/1/2005
     */

/*

 * Commented out on Nov 30, 2007 by Adam M. Smith to avoid MathLink, Jlink, KernelLink dependencies

    public Set prioritizeUsingRandom(int desiredPermutations)
    {

	// this is the final "set of sets" that was produced it must
	// included a total of desiredReductions that are all of size
	// desiredReductionSize
	LinkedHashSet setOfPrioritizedSets = new LinkedHashSet();
	
	// this HashMap will store the test names and the actual
	// SingleTestSubsets so that they can be retrieved later
	LinkedHashMap fullTestSuite = new LinkedHashMap();

	// create the Strings that are going to be essentially
	// constants for every call to Mathematica and only operate on
	// the other variables that are indeed going to change
	String loadCombinatorica = "<< DiscreteMath`Combinatorica`;";
	String finalTestSuitePermutations = 
	    "finalTestSuitePermutations = {};";
	String bigCodeSegment = 
	    "While[Length[finalTestSuitePermutations] < desiredPermutations," +
	    "currentPermutation = RandomPermutation[testSuitePrioritize];" +
    "If[MemberQ[finalTestSuitePermutations, currentPermutation], duplicate," +
    "AppendTo[finalTestSuitePermutations, currentPermutation]]];" + 
            "finalTestSuitePermutations";

	// the number of prioritizations that are being requested
	String desiredPermutationsMath = "desiredPermutations =" + 
	    desiredPermutations + ";";

	// this StringBuffer will store the final listing of the 
	// tests that will be submitted to the Mathematica
	StringBuffer testSuitePrioritize = new StringBuffer();

	// we have to add the beginning of this String 
	testSuitePrioritize.append("testSuitePrioritize = {");

	// go through the testSubsets and build up a String that 
	// represents them all -- on the side, build up a HashMap
	// that is for the String and the SingleTestSubset that 
	// is associated with the String
	
	Iterator testSetIterator = testSubsets.iterator();
	while( testSetIterator.hasNext() )
	    {

		// extract the next SingleTestSubset
		SingleTestSubset nextTestSubset = 
		    (SingleTestSubset) testSetIterator.next();

		// place this inside the HashMap using the key 
		// and value pair that is formatted at (test name,
		// nextTestSubset)
		fullTestSuite.put(nextTestSubset.getTest().getName(),
				  nextTestSubset.getTest());

		// put the name of the test inside of the StringBuffer
		// so that we can submit it to Mathematica
		testSuitePrioritize.append(nextTestSubset.getTest().
					  getName());

		// there is still going to be another test that
		// we have to append and thus we need to include
		// the final comma at the end
		if( testSetIterator.hasNext() )
		    {

			testSuitePrioritize.append(", ");

		    }

	    }

	// we have to add on the closing to the test suite for reduction
	testSuitePrioritize.append("};");

// 	System.out.println("testSuitePrioritize = " + 
// 			   testSuitePrioritize);

	//System.out.println(fullTestSuite);
	
	// we are now done building up everything that has to be sent
	// to Mathematica and we just to initialize the mathematica
	// connection and then send the information off and handle
	// the result (more String parsing required)

		KernelLink ml = null;
	String mathematicaConnect = 
	    "-linkmode launch -linkname \'math -mathlink\'";

	try 
	    {
		
		ml = MathLinkFactory.
		    createKernelLink(mathematicaConnect);
		
	    } 

	catch (MathLinkException e) 
	    {
		
		System.out.println("Fatal error opening link: " + 
				   e.getMessage());
		e.printStackTrace();
	    
	    }

	try 
	    {
			
		// Get rid of the initial InputNamePacket the kernel
		// will send when it is launched.
		ml.discardAnswer();
			
		System.out.println( //loadCombinatorica + 
				    //"Pause[2];" + 
				    finalTestSuitePermutations +
				    desiredPermutationsMath + 
				    testSuitePrioritize.toString() + 
				    bigCodeSegment );

		// load the combinatorica package separately; this
		// will be "resident" when we execute the next command
		// (timing might dictate that this approach does not
		// always execute correctly)
		ml.evaluate(loadCombinatorica);
		ml.discardAnswer();
		
		//Thread.currentThread().sleep(3000);

		// send the final string to Mathematica through 
		// J/Link and retrieve the answer as a string
		String solve = ml.
		    evaluateToOutputForm(finalTestSuitePermutations +
					 desiredPermutationsMath + 
					 testSuitePrioritize.toString() + 
					 bigCodeSegment, 0 );
		    
// 		System.out.println("solve = " + solve);

		// we have the final String from Mathematica and 
		// now we need to parse it correctly and then 
		// return the final "set of sets" back to the 
		// calling program
		ArrayList testList = Util.
		    generateTestNames(solve);

		// we need an ArrayList that is going to store each of
		// the individual reductions; NOTE that this cannot be
		// a Set because then they will appear to be the same
		// since the set does not preserve order!
		ArrayList internalSet = new ArrayList();
		
		// for debugging purposes (corresponds to println)
		int i = 1;

		// go through each one of the separate ArrayLists and 
		// retrieve the SingleTests that are inside of the 
		// HashMap in order to make the final "Set of Sets"
		Iterator testListIterator = testList.iterator();
		while( testListIterator.hasNext() )
		    {

			// extract an ArrayList and an Iterator the
			// current internal listing
			ArrayList internalTestList = 
			    (ArrayList) testListIterator.next();
			Iterator internalTestListIterator = 
			    internalTestList.iterator();
			while( internalTestListIterator.hasNext() )
			    {

				// extract the current name of the
				// test and then check into the
				// HashMap
				String currentTestName = 
				    (String) internalTestListIterator.next();

				//System.out.println(currentTestName);

				// put in the real SingleTest
				internalSet.add(fullTestSuite.
						get(currentTestName));

			    }			

			//System.out.println(i + " finished: " + 
			//		   internalSet);
			i++;

			// add in the current internalSet and then
			// clone it and clear it so that it can 
			// be used again while avoiding the Java
			// reference problem
			setOfPrioritizedSets.add(internalSet);
			internalSet = (ArrayList)internalSet.clone();
			internalSet.clear();

		    }

	    }

	catch(Exception e)
	    {

		System.out.println("Problem reducing in a random fashion.");
		e.printStackTrace();

	    }

	
	finally 
	    {
		
		ml.close();
		
	    }

	return setOfPrioritizedSets;

    }
*/
    /**
     *  Reduce the test suite in a Kanonizo-like fashion that simply
     *  requires that we sort the test suite **according to COST** and
     *  then select those first ones that are withing our targetSize.
     *  
     *  @author Gregory M. Kapfhammer 12/1/2005
     */
    public Set reduceUsingKanonizo(int targetSize, Comparator compare)
    {

	// take the timing before
	long reductionTimeBefore = System.currentTimeMillis();

	LinkedHashSet costPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);

	// create a new Comparator that can handle cost information
	//	SingleTestSubsetCostComparator compare = 
	//    new SingleTestSubsetCostComparator();

	// sort this ArrayList according to COST 
	Collections.sort(testSubsetsList, compare);
       
	// keep track of the total number of picks
	int numberPicks = 0;

	// pick the ones at the begining up to the targetSize
 	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() && 
	       numberPicks < targetSize )
	    {

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		costPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

		numberPicks++;

	    }	

	// take the timing after
	long reductionTimeAfter = System.currentTimeMillis();
	reductionTime = (reductionTimeAfter - reductionTimeBefore);

	return costPickSets;

    }

    /**
     *  Prioritize the test suite in a Kanonizo-like fashion that
     *  simply requires that we sort the test suite **according to
     *  COST** or any of the other comparators.
     *  
     *  @author Gregory M. Kapfhammer 12/1/2005
     */
    public Set prioritizeUsingKanonizo(Comparator compare)
    {

	// take the timing before
	long prioritizeTimeBefore = System.currentTimeMillis();

	LinkedHashSet costPickSets = new LinkedHashSet();

	// produce an ArrayList from the set of the testSubsets
	ArrayList testSubsetsList = new ArrayList(testSubsets);

// 	System.out.println("TSL Before: " + 
// 			   testSubsetsList);

	// sort this ArrayList according to COST 
	Collections.sort(testSubsetsList, compare);

// 	System.out.println("TSL After: " + 
// 			   testSubsetsList);
	
// 	System.out.println("TSL size inside PWK : " + 
// 			   testSubsetsList.size());

	// argh, this is horrible, but we have to go through the 
	// testSubsetsList and extract the SingleTests that are 
	// currently inside of the SingleTestSubsets (we can 
	// probably not include this in the timing because it is
	// just an accident of how we have implemented the system)
	Iterator testSubsetsIterator = testSubsetsList.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		SingleTestSubset stsJerk = 
		    (SingleTestSubset) testSubsetsIterator.next();

		costPickSets.
		    add( stsJerk.getTest() );

		// added by gmk on march 6
		reducedSingleTestSubsets.
		    add( stsJerk );

	    }

	// take the timing after
	long prioritizeTimeAfter = System.currentTimeMillis();
	prioritizationTime = (prioritizeTimeAfter - prioritizeTimeBefore);

	// debugging output for now
// 	System.out.println("RSTS size inside PWK : " + 
// 			   reducedSingleTestSubsets.size());

	return costPickSets;

    }

    /**
     *  Uses repeated calls to the reduceUsingGreedy method in order
     *  to perform a prioritization of the test suite.  Information
     *  about the reduction algorithm that is being called:
     *
     *  Approximates the solution to the SetCover instance using the 
     *  Greedy Set Cover Algorithm on pg 16 of Vazirani's _Approximation
     *  Algorithms_.  This is a O(m*n) algorithm and it can be extended
     *  to perform regression test suite prioritization as well.
     *  
     *  @author Gregory M. Kapfhammer 12/5/2005
     */
    public Set prioritizeUsingGreedy()
    {

	// calculate the time before we begin executing the algorithm
	long prioritizeTimeBefore = System.currentTimeMillis();

	// this is the final set that will store all of the
	// prioritized test suite; note that this must be equal to the
	// same size as the initial test suite that is represented as
	// a set cover instance
	LinkedHashSet prioritizedSet = new LinkedHashSet();

	// debugging output
// 	System.out.println("testSubsets size (before) = " + 
// 			   testSubsets.size());

	// this is the initial size of the testSubset; I was
	// collecting this information for debugging purposes;
	// does not really need to be used in this implementation
	int aimingForSize = testSubsets.size();
	
	// keep performing the reduction until we have included
	// all of the test cases inside of the prioritizedSet
	while( testSubsets.size() != 0 )
	//	while( prioritizedSet.size() < aimingForSize )
	    {

		// debugging information for now 

// 		System.out.println("BEG testSubsets size (inside) = " + 
// 				   testSubsets.size());
// 		System.out.println("BEG prioritizedSet size (inside) = " + 
// 				   prioritizedSet.size());

// 		System.out.println("prioritizeSet: " + prioritizedSet);
// 		System.out.println("testSubsets: " + testSubsets);

// 		System.out.println("SC = " + this);

		// perform the reduction on whatever is 
		// currently inside of the SetCover instance
		Set internalPrioritizedSet = 
		    this.reduceUsingGreedy();

// 		System.out.println("internalPrioritizeSet size: " + 
// 				   internalPrioritizedSet.size());

		// this is the listing of the RequirementSubsets
		// that are still considered to be "live" because
		// they are covered by tests that have not yet 
		// been included inside of the prioritized test suite
		LinkedHashSet liveRequirementSubsets = 
		    new LinkedHashSet();

		// gather up a listing of all of the Requirement
		// Subsets that are currently still being covered
		// by the test cases that are still inside of 
		// the instance of the SetCover
		Iterator testSubsetsIterator = testSubsets.iterator();
		while( testSubsetsIterator.hasNext() )
		    {

			// extract the current SingleTestSubset
			SingleTestSubset currentSingleTestSubset = 
			    (SingleTestSubset) testSubsetsIterator.next();

// 			System.out.println("current STS: " + 
// 					   currentSingleTestSubset);

			// extract the requirementSubsetSet
			LinkedHashSet currentReqSubsetSet = 
			    currentSingleTestSubset.getRequirementSubsetSet();
			
			// this is an example of a live requirement
			// and we have to store it inside the set
			liveRequirementSubsets.addAll(currentReqSubsetSet);

		    }

// 		System.out.println("live requirements: " + 
// 				   liveRequirementSubsets);

// 		System.out.println("RSU Before: " + 
// 				   requirementSubsetUniverse);

		// we now have a complete listing of all of the
		// requirement subsets that are live and we have to
		// remove all those dead requirements using the set
		// intersection operator
		requirementSubsetUniverse.retainAll(liveRequirementSubsets);

// 		System.out.println("RSU After: " + 
// 				   requirementSubsetUniverse);

		// these are test cases that we want to keep 
		// and we want them in exactly the order that 
		// they appear inside of the current output
		// from the reduction algorithm
		prioritizedSet.addAll(internalPrioritizedSet);

		// keep track of the number of times that we executed
		// the outer while loop; this corresponds to the
		// number of times that we actually perform the
		// reduction procedure
		outerWhileExecutePrioritization++;

// 		System.out.println("END testSubsets size (inside) = " + 
// 				   testSubsets.size());
// 		System.out.println("END prioritizedSet size (inside) = " + 
// 				   prioritizedSet.size());
		
		// keep track of the number of times that we had to 
		// execute the outer while loop of the reduction 
		// algorithm; this is another good way to measure
		// performance and it is nice for checking that 
		// the prioritized test suite is correct
		outerWhileExecuteList.add(outerWhileExecute);

 	    }

//  	System.out.println("prioritized set: " + prioritizedSet);
// 	System.out.println("**********size = " + prioritizedSet.size());
// 	System.out.println("outer while? " + 
// 			   outerWhileExecutePrioritization);

	// calculate the time after executing the algorithm
	long prioritizeTimeAfter = System.currentTimeMillis();

	// set the actual time for prioritization
	prioritizationTime = (prioritizeTimeAfter - 
			      prioritizeTimeBefore);

	// return the final prioritized set; note that this set is
	// added to every time that we run the reduction algorithm
	return prioritizedSet;

    }


	/**
     *  Uses repeated calls to the reduceUsingGreedy method in order
     *  to perform a prioritization of the test suite.  Information
     *  about the reduction algorithm that is being called:
     *
     *  Approximates the solution to the SetCover instance using the 
     *  Greedy Set Cover Algorithm on pg 16 of Vazirani's _Approximation
     *  Algorithms_.  This is a O(m*n) algorithm and it can be extended
     *  to perform regression test suite prioritization as well.
     *  
     *  @author Gregory M. Kapfhammer 12/5/2005
     */
     
     
    /* Commented out by AMS on 03/17/08 because it is no longer needed and
     * may not be working 100% correctly.
     * 
     * Use the ReduceUsingGreedy.java class instead
     */
     
     /*
    public Set prioritizeUsingGreedy(String metric)
    {

	// calculate the time before we begin executing the algorithm
	long prioritizeTimeBefore = System.currentTimeMillis();

	// this is the final set that will store all of the
	// prioritized test suite; note that this must be equal to the
	// same size as the initial test suite that is represented as
	// a set cover instance
	LinkedHashSet prioritizedSet = new LinkedHashSet();

	// debugging output
// 	System.out.println("testSubsets size (before) = " + 
// 			   testSubsets.size());

	// this is the initial size of the testSubset; I was
	// collecting this information for debugging purposes;
	// does not really need to be used in this implementation
	int aimingForSize = testSubsets.size();
	
	// keep performing the reduction until we have included
	// all of the test cases inside of the prioritizedSet
	while( testSubsets.size() != 0 )
	//	while( prioritizedSet.size() < aimingForSize )
	    {

		// debugging information for now 

// 		System.out.println("BEG testSubsets size (inside) = " + 
// 				   testSubsets.size());
// 		System.out.println("BEG prioritizedSet size (inside) = " + 
// 				   prioritizedSet.size());

// 		System.out.println("prioritizeSet: " + prioritizedSet);
// 		System.out.println("testSubsets: " + testSubsets);

// 		System.out.println("SC = " + this);

		// perform the reduction on whatever is 
		// currently inside of the SetCover instance
		Set internalPrioritizedSet = 
		    this.reduceUsingGreedy(metric);

// 		System.out.println("internalPrioritizeSet size: " + 
// 				   internalPrioritizedSet.size());

		// this is the listing of the RequirementSubsets
		// that are still considered to be "live" because
		// they are covered by tests that have not yet 
		// been included inside of the prioritized test suite
		LinkedHashSet liveRequirementSubsets = 
		    new LinkedHashSet();

		// gather up a listing of all of the Requirement
		// Subsets that are currently still being covered
		// by the test cases that are still inside of 
		// the instance of the SetCover
		Iterator testSubsetsIterator = testSubsets.iterator();
		while( testSubsetsIterator.hasNext() )
		    {

			// extract the current SingleTestSubset
			SingleTestSubset currentSingleTestSubset = 
			    (SingleTestSubset) testSubsetsIterator.next();

// 			System.out.println("current STS: " + 
// 					   currentSingleTestSubset);

			// extract the requirementSubsetSet
			LinkedHashSet currentReqSubsetSet = 
			    currentSingleTestSubset.getRequirementSubsetSet();
			
			// this is an example of a live requirement
			// and we have to store it inside the set
			liveRequirementSubsets.addAll(currentReqSubsetSet);

		    }

// 		System.out.println("live requirements: " + 
// 				   liveRequirementSubsets);

// 		System.out.println("RSU Before: " + 
// 				   requirementSubsetUniverse);

		// we now have a complete listing of all of the
		// requirement subsets that are live and we have to
		// remove all those dead requirements using the set
		// intersection operator
		requirementSubsetUniverse.retainAll(liveRequirementSubsets);

// 		System.out.println("RSU After: " + 
// 				   requirementSubsetUniverse);

		// these are test cases that we want to keep 
		// and we want them in exactly the order that 
		// they appear inside of the current output
		// from the reduction algorithm
		prioritizedSet.addAll(internalPrioritizedSet);

		// keep track of the number of times that we executed
		// the outer while loop; this corresponds to the
		// number of times that we actually perform the
		// reduction procedure
		outerWhileExecutePrioritization++;

// 		System.out.println("END testSubsets size (inside) = " + 
// 				   testSubsets.size());
// 		System.out.println("END prioritizedSet size (inside) = " + 
// 				   prioritizedSet.size());
		
		// keep track of the number of times that we had to 
		// execute the outer while loop of the reduction 
		// algorithm; this is another good way to measure
		// performance and it is nice for checking that 
		// the prioritized test suite is correct
		outerWhileExecuteList.add(outerWhileExecute);

 	    }

//  	System.out.println("prioritized set: " + prioritizedSet);
// 	System.out.println("**********size = " + prioritizedSet.size());
// 	System.out.println("outer while? " + 
// 			   outerWhileExecutePrioritization);

	// calculate the time after executing the algorithm
	long prioritizeTimeAfter = System.currentTimeMillis();

	// set the actual time for prioritization
	prioritizationTime = (prioritizeTimeAfter - 
			      prioritizeTimeBefore);

	// return the final prioritized set; note that this set is
	// added to every time that we run the reduction algorithm
	return prioritizedSet;

    }
    */

    /**
     *  Approximates the solution to the SetCover instance using the 
     *  Greedy Set Cover Algorithm on pg 16 of Vazirani's _Approximation
     *  Algorithms_.  This is a O(m*n) algorithm and it can be extended
     *  to perform regression test suite prioritization as well.
     *  
     *  @author Gregory M. Kapfhammer 9/17/2005
     */
    public Set reduceUsingGreedy()
    {

	// take the timing before running the algorithm
	long reductionTimeBefore = System.currentTimeMillis();

// 	System.out.println("Reducing using Greedy");

	// this is the Set that contains the final covering; 
	// actually this is not the case; we just use this 
	// to store intermediate results -- we do return
	// the variable called coverPickSet
	LinkedHashSet cover = new LinkedHashSet();

	// this is the Picked sets; this corresponds to the answer to
	// the HittingSet problem that we have solved via the greedy
	// approximation to the SetCover problem
	LinkedHashSet coverPickSets = new LinkedHashSet();

// 	System.out.println("requirementSubsetUniverse = " +
// 			   requirementSubsetUniverse);

	// A NOTE TO UNDERSTAND THE PURPOSE OF REMOVAL: You can remove
	// a test case whenever **all** of the test requirements that
	// it covers are already covered by another test case.
	// However, you have to be careful because we want to add
	// these back in when we are done.  Adding them in does not
	// impact the reduced test suite in a negative fashion.  But,
	// it does enable prioritization by repeated reduction

	// note that initially there is nothing to remove ; 
	// we just build up this list over time and re-init 
	// after we execute the removal
	LinkedHashSet goingToRemove = new LinkedHashSet();

	// we need to keep track of all tests that were removed
	// so that we can add them back in right before we return
	// from this method.  We need to do this so that we can 
	// properly support prioritization via repeated reduction
	LinkedHashSet goingToRemoveTrack = new LinkedHashSet();

	// this variable will keep track of the number of iterations
	// of the while loop ; this gives us some basic information
	// about the performance of the algorithm without using
	// profiling instrumentation (my observation is that this
	// should always be equal to the number of tests in the
	// reduced test suite)
	outerWhileExecute = 0;
	
	// we are going to clone the current instance so that we can
	// store it and use it whenever we need to calculate
	// reductions and percent reductions
	beforeReduction = (SetCover) this.clone();

	// keep iterating until we have picked a set of test cases
	// that will cover the entire universe of test requirements
	// note that each test case corresponds to the coverage of 
	// certain test requirements
	while( ! cover.equals(requirementSubsetUniverse) )
	    {

// 		System.out.println("Cover = " + cover);
// 		System.out.println();

// 		System.out.println("U = " + requirementSubsetUniverse);
// 		System.out.println();

		// NOTE: we would like to do the following step inside
		// of the while() loop but Java will throw a
		// ConcurrentModificationException because the
		// iterator that is returned is fail-fast

		// NOTE: this might not be any faster in terms of Java
		// performance because of the allocation and deallocation
		// (this is probably open to experimentation) but note
		// that it does result in different reduced test suites
		// that still all cover the test requirements

		// debugging output for now
// 		System.out.println("Going to Remove Size (inside): " + 
// 				   goingToRemove.size());
		
		// remove all of the sets inside of the TestSubsets
		// that no longer ever need to be considered (this
		// cuts down on the number of iterations of the next
		// while loop)
		testSubsets.removeAll(goingToRemove);
		goingToRemoveTrack.addAll(goingToRemove);
		goingToRemove = new LinkedHashSet();
		
		// extract an iterator of the TestSubsets; this is 
		// the S for the SetCover instance ; we have to keep
		// going through these until we have selected enough
		// of them to equal the entire universe U
		Iterator testSubsetsIterator = 
		    testSubsets.iterator();

		// create the MinCost RequirementSubset because this
		// is the one that we are going to select for this
		// iteration of the algorithm
		Set minCostRequirementSubsetSet = new LinkedHashSet();

		// this corresponds to the SingleTestSubset that 
		// is defined to have the minimum cost 
		SingleTestSubset minCostSingleTestSubset = null;
		double minimumTestSubsetCost = Double.MAX_VALUE;

		// if there are SingleTestSubsets that have exactly
		// the same cost, we go ahead and store all of them
		// so that we can pick one of them afterwards
		ArrayList equalCostSubsets = new ArrayList();

		// go through all of the remaining RequirementSubsets
		// and measure their cost
		while( testSubsetsIterator.hasNext() )
		    {

			// this is the listing of the selected tests
			// that have the same cost (we have to 
			// select from these some how)
			//equalCostSubsets = new ArrayList();

			// go to the next SingleTestSubset and
			// determine if it has any elements left
			// within it and if so, what its cost might be
			SingleTestSubset currentTestSubset = 
			    (SingleTestSubset)testSubsetsIterator.next();

// 			System.out.println("considering " + 
// 					   currentTestSubset);

// 			System.out.println("current cover " + 
// 					   cover);

			// we have found a SingleTestSubset that still
			// has some test requirements that have not
			// been covered and thus we should keep it
			// running
			if( currentTestSubset.containsElements(cover) )
			    {


				
					// calculate the cost of the currentTestSubset (ratio)
					double currentTestSubsetCost = 
				   	 currentTestSubset.
				   	 costEffectiveness(cover);

// 				System.out.println("curr calculated cost: " + 
// 						   currentTestSubsetCost);

				// we found one that is cheaper 
				// according to the cost function
				if( currentTestSubsetCost <  
				    minimumTestSubsetCost )
				    {

// 					System.out.println("Pick you!");

					// extract the set of
					// requirements that are
					// covered by this test case
					// that is of the new minimum
					// cost
					minCostRequirementSubsetSet = 
					    currentTestSubset.
					    getRequirementSubsetSet();

					// reinitialize the list of
					// test cases that are of
					// equal cost since we have
					// found a new minium
					equalCostSubsets = new ArrayList();

					// reinitialize the test case
					// that is of minimum cost;
					// this is the one that we
					// will currently be
					// considering
					minCostSingleTestSubset = 
					    currentTestSubset;

					// set the cost for this 
					// specific test cse
					minimumTestSubsetCost = 
					    currentTestSubsetCost;
					
					// add this test case in as
					// the first of those that
					// might be of equal cost (of
					// course, we might not find
					// any others and then we have
					// a singleton set)
					equalCostSubsets.
					    add(minCostSingleTestSubset);
				
				    }

				// we found a TestSubset that is the 
				// same cost as the previous one that 
				// was considered the minimum
				else if( currentTestSubsetCost == 
					 minimumTestSubsetCost )
				    {

// 					System.out.println("== cost!");
					equalCostSubsets.
					    add(currentTestSubset);

				    }

			    }

			// we have found a set that does not any longer
			// contain viable test requirements
			else
			    {

// 				System.out.println("Ready to Remove: " +
// 						   currentTestSubset);
				
				// next run through the algorithm go
				// ahead and remove this test case
				goingToRemove.add(currentTestSubset);
				
				// NOTE: would like to do this, but 
				// cannot because of the CME in Java
				//testSubsets.remove(currentTestSubset);
				
			    }
			
			// I think that it would be at this point that
			// we would resolve any of the types of ties
			// that could occur; this could occur with
			// respect to granularity or randomly; this could
			// be triggered by some type of flag to the 
			// method itself although this would break a lot
			// of the test cases. SO, JUST NOTE THAT I AM
			// REALLY NOT HANDLING TIES!

		    }

// 		System.out.println("minCostRequirementSubsetSet = " + 
// 				   minCostRequirementSubsetSet);

// 		System.out.println("same costs " + equalCostSubsets);

		// add the selected minCostRequirementSubsetSet items
		// to the cover listing (ONCE AGAIN, IGNORING TIES)
		cover.addAll(minCostRequirementSubsetSet);

		// set the purchase price of this test case that we
		// have just selected and then add it to the set of
		// the picked test case (by keeping track of purchase
		// price we can know the order of selection)

// 		System.out.println("minCostSingleTestSubset = " +
// 				   minCostSingleTestSubset);

// 		System.out.println("getTest = " + 
// 				   minCostSingleTestSubset.getTest());

		// purchase the test which means applying the cost
		// for the entire TestSubset to this specific test
		minCostSingleTestSubset.getTest().
		    purchase(minimumTestSubsetCost);

		// add this **SingleTest** into the final coverPickSets
		// note that this might be better as a SingleTestSubset
		// but we are keeping this approach because it breaks
		// the tests and the clients
		coverPickSets.add(minCostSingleTestSubset.getTest());

		// add the SingleTestSubset to the listing that can
		// be accessed after the reduction has been completed
		reducedSingleTestSubsets.add(minCostSingleTestSubset);

		// WRONG: this would take them out of U and we have to
		// compare to U as the algorithm continues to execute

		// remove the one with the minimum cost from 
		// the testSubsets
// 		boolean foundRsu = requirementSubsetUniverse.
// 		   removeAll(minCostRequirementSubsetSet);
		
// 		System.out.println("found rSU ? = " + foundRsu);
// 		System.out.println();

		// we were able to remove this test case from the 
		// overall set of test cases (this flag is really
		// just used for debugging purposes)
		boolean found = testSubsets.remove(minCostSingleTestSubset);

// 		System.out.println("found tS ? = " + found);
// 		System.out.println();

		// increment counter; really just used for the
		// purposes of performance evaluation (always equal to
		// the number of final tests in the test suite)
		outerWhileExecute++;

	    }

	// debugging output for now
// 	System.out.println("size of goingtoRemoveTrack = " + 
// 			   goingToRemoveTrack.size());

	// add back all of the test cases that were removed because
	// their set of test requirements were completely exhausted;
	// this will support prioritization by repeated reduction
	testSubsets.addAll(goingToRemoveTrack);

	// take the timing after running the algorithm
	long reductionTimeAfter = System.currentTimeMillis();

	// calculate the execution time and store the result
	reductionTime = (reductionTimeAfter - reductionTimeBefore);

	// return the result to the calling method; this actually 
	// represents the test cases that we chose to KEEP and 
	// thus this is the reduced test suite that we want!
	return coverPickSets;

    }
   


 	/**								CLONE
     *  Approximates the solution to the SetCover instance using the 
     *  Greedy Set Cover Algorithm on pg 16 of Vazirani's _Approximation
     *  Algorithms_.  This is a O(m*n) algorithm and it can be extended
     *  to perform regression test suite prioritization as well.
     *  
     *  This method is a clone of the reduceUsingGreedy() one above.  
     *  This method allows for the greedy metric to be selected, and
     *  the test cases that use the above version will still pass.
     *
     *  @author Gregory M. Kapfhammer 9/17/2007
     *  @author Adam M. Smith	3/11/2008
     *
     */
    
    
    /* Commented out by AMS on 03/17/08 because it is no longer needed and
     * may not be working 100% correctly.
     * 
     * Use the ReduceUsingGreedy.java class instead
     */
     
     /*
    public Set reduceUsingGreedy(String metric)
    {

	// take the timing before running the algorithm
	long reductionTimeBefore = System.currentTimeMillis();

// 	System.out.println("Reducing using Greedy");

	// this is the Set that contains the final covering; 
	// actually this is not the case; we just use this 
	// to store intermediate results -- we do return
	// the variable called coverPickSets
	LinkedHashSet cover = new LinkedHashSet();

	// this is the Picked sets; this corresponds to the answer to
	// the HittingSet problem that we have solved via the greedy
	// approximation to the SetCover problem
	LinkedHashSet coverPickSets = new LinkedHashSet();

// 	System.out.println("requirementSubsetUniverse = " +
// 			   requirementSubsetUniverse);

	// A NOTE TO UNDERSTAND THE PURPOSE OF REMOVAL: You can remove
	// a test case whenever **all** of the test requirements that
	// it covers are already covered by another test case.
	// However, you have to be careful because we want to add
	// these back in when we are done.  Adding them in does not
	// impact the reduced test suite in a negative fashion.  But,
	// it does enable prioritization by repeated reduction

	// note that initially there is nothing to remove ; 
	// we just build up this list over time and re-init 
	// after we execute the removal
	LinkedHashSet goingToRemove = new LinkedHashSet();

	// we need to keep track of all tests that were removed
	// so that we can add them back in right before we return
	// from this method.  We need to do this so that we can 
	// properly support prioritization via repeated reduction
	LinkedHashSet goingToRemoveTrack = new LinkedHashSet();

	// this variable will keep track of the number of iterations
	// of the while loop ; this gives us some basic information
	// about the performance of the algorithm without using
	// profiling instrumentation (my observation is that this
	// should always be equal to the number of tests in the
	// reduced test suite)
	outerWhileExecute = 0;
	
	// we are going to clone the current instance so that we can
	// store it and use it whenever we need to calculate
	// reductions and percent reductions
	beforeReduction = (SetCover) this.clone();

	// keep iterating until we have picked a set of test cases
	// that will cover the entire universe of test requirements
	// note that each test case corresponds to the coverage of 
	// certain test requirements
	while( ! cover.equals(requirementSubsetUniverse) )
	    {

// 		System.out.println("Cover = " + cover);
// 		System.out.println();

// 		System.out.println("U = " + requirementSubsetUniverse);
// 		System.out.println();

		// NOTE: we would like to do the following step inside
		// of the while() loop but Java will throw a
		// ConcurrentModificationException because the
		// iterator that is returned is fail-fast

		// NOTE: this might not be any faster in terms of Java
		// performance because of the allocation and deallocation
		// (this is probably open to experimentation) but note
		// that it does result in different reduced test suites
		// that still all cover the test requirements

		// debugging output for now
// 		System.out.println("Going to Remove Size (inside): " + 
// 				   goingToRemove.size());
		
		// remove all of the sets inside of testSubsets (data field for **SetCover**)
		// that no longer ever need to be considered (this
		// cuts down on the number of iterations of the next
		// while loop)
		testSubsets.removeAll(goingToRemove);
		goingToRemoveTrack.addAll(goingToRemove);
		goingToRemove = new LinkedHashSet();
		
		// extract an iterator of the TestSubsets; this is 
		// the S for the SetCover instance ; we have to keep
		// going through these until we have selected enough
		// of them to equal the entire universe U
		Iterator testSubsetsIterator = 
		    testSubsets.iterator();

		// create the MinCost RequirementSubset because this
		// is the one that we are going to select for this
		// iteration of the algorithm
		Set minCostRequirementSubsetSet = new LinkedHashSet();

		// this corresponds to the SingleTestSubset that 
		// is defined to have the minimum cost 
		SingleTestSubset minCostSingleTestSubset = null;
		
		double minimumTestSubsetCost;
		
		if(metric.equals("coverage"))
		{
			minimumTestSubsetCost = Double.MIN_VALUE;
		}
		else
		{
			minimumTestSubsetCost = Double.MAX_VALUE;
		}
		// if there are SingleTestSubsets that have exactly
		// the same cost, we go ahead and store all of them
		// so that we can pick one of them afterwards
		ArrayList equalCostSubsets = new ArrayList();

		// go through all of the remaining RequirementSubsets
		// and measure their cost
		while( testSubsetsIterator.hasNext() )
		    {

			// this is the listing of the selected tests
			// that have the same cost (we have to 
			// select from these some how)
			//equalCostSubsets = new ArrayList();

			// go to the next SingleTestSubset and
			// determine if it has any elements left
			// within it and if so, what its cost might be
			SingleTestSubset currentTestSubset = 
			    (SingleTestSubset)testSubsetsIterator.next();

// 			System.out.println("considering " + 
// 					   currentTestSubset);

// 			System.out.println("current cover " + 
// 					   cover);

			// we have found a SingleTestSubset that still
			// has some test requirements that have not
			// been covered and thus we should keep it
			// running
			if( currentTestSubset.containsElements(cover) )
			    {

				if(metric.equals("ratio"))
				{
					//System.out.println("Using ratio");
					// calculate the cost of the currentTestSubset (ratio)
					double currentTestSubsetCost = 
				   	 currentTestSubset.
				   	 costEffectiveness(cover);

// 				System.out.println("curr calculated cost: " + 
// 						   currentTestSubsetCost);
				
					// we found one that is cheaper 
					// according to the cost function
					if( currentTestSubsetCost <  
				   	 minimumTestSubsetCost )
				    {

	// 					System.out.println("Pick you!");

						// extract the set of
						// requirements that are
						// covered by this test case
						// that is of the new minimum
						// cost
						minCostRequirementSubsetSet = 
						    currentTestSubset.
						    getRequirementSubsetSet();

						// reinitialize the list of
						// test cases that are of
						// equal cost since we have
						// found a new minium
						equalCostSubsets = new ArrayList();

						// reinitialize the test case
						// that is of minimum cost;
						// this is the one that we
						// will currently be
						// considering
						minCostSingleTestSubset = 
						    currentTestSubset;

						// set the cost for this 
						// specific test cse
						minimumTestSubsetCost = 
						    currentTestSubsetCost;
						
						// add this test case in as
						// the first of those that
						// might be of equal cost (of
						// course, we might not find
						// any others and then we have
						// a singleton set)
						equalCostSubsets.
						    add(minCostSingleTestSubset);
					
					    }
					
					// we found a TestSubset that is the 
					// same cost as the previous one that 
					// was considered the minimum
					else if( currentTestSubsetCost == 
						 minimumTestSubsetCost )
					    {

	// 					System.out.println("== cost!");
						equalCostSubsets.
						    add(currentTestSubset);

					    }
					}
			    
			    else if(metric.equals("coverage"))
				{
					
					//System.out.println("Using coverage");
					// calculate the cost of the currentTestSubset (coverage)
					double currentTestSubsetCost = 
				   	 currentTestSubset.
				   	 coverageValue(cover);

// 				System.out.println("curr calculated cost: " + 
// 						   currentTestSubsetCost);
					
					// we found one that covers more 
					if( currentTestSubsetCost >  
				   	 minimumTestSubsetCost )
				    {

	// 					System.out.println("Pick you!");

						// extract the set of
						// requirements that are
						// covered by this test case
						// that is of the new minimum
						// cost
						minCostRequirementSubsetSet = 
						    currentTestSubset.
						    getRequirementSubsetSet();

						// reinitialize the list of
						// test cases that are of
						// equal cost since we have
						// found a new minium
						equalCostSubsets = new ArrayList();

						// reinitialize the test case
						// that is of minimum cost;
						// this is the one that we
						// will currently be
						// considering
						minCostSingleTestSubset = 
						    currentTestSubset;

						// set the cost for this 
						// specific test cse
						minimumTestSubsetCost = 
						    currentTestSubsetCost;
						
						// add this test case in as
						// the first of those that
						// might be of equal cost (of
						// course, we might not find
						// any others and then we have
						// a singleton set)
						equalCostSubsets.
						    add(minCostSingleTestSubset);
					
					    }
					
					// we found a TestSubset that is the 
					// same cost as the previous one that 
					// was considered the minimum
					else if( currentTestSubsetCost == 
						 minimumTestSubsetCost )
					    {

	// 					System.out.println("== cost!");
						equalCostSubsets.
						    add(currentTestSubset);

					    }
					}
			    
			    else if(metric.equals("time"))
				 {
				 
				 //	System.out.println("Using time");
					
					// calculate the cost of the currentTestSubset (time)
					double currentTestSubsetCost = 
				   	 currentTestSubset.getTest().getCost();
	// 				System.out.println("curr calculated cost: " + 
// 						   currentTestSubsetCost);
					// we found one that is cheaper 

					if( currentTestSubsetCost <  
				   	 minimumTestSubsetCost )
				    {

	// 					System.out.println("Pick you!");

						// extract the set of
						// requirements that are
						// covered by this test case
						// that is of the new minimum
						// cost
						minCostRequirementSubsetSet = 
						    currentTestSubset.
						    getRequirementSubsetSet();

						// reinitialize the list of
						// test cases that are of
						// equal cost since we have
						// found a new minium
						equalCostSubsets = new ArrayList();

						// reinitialize the test case
						// that is of minimum cost;
						// this is the one that we
						// will currently be
						// considering
						minCostSingleTestSubset = 
						    currentTestSubset;

						// set the cost for this 
						// specific test cse
						minimumTestSubsetCost = 
						    currentTestSubsetCost;
						
						// add this test case in as
						// the first of those that
						// might be of equal cost (of
						// course, we might not find
						// any others and then we have
						// a singleton set)
						equalCostSubsets.
						    add(minCostSingleTestSubset);
					
					    }
					
					// we found a TestSubset that is the 
					// same cost as the previous one that 
					// was considered the minimum
					else if( currentTestSubsetCost == 
						 minimumTestSubsetCost )
					    {

	// 					System.out.println("== cost!");
						equalCostSubsets.
						    add(currentTestSubset);

					    }
					}
				}

			// we have found a set that does not any longer
			// contain viable test requirements
			else
			    {

// 				System.out.println("Ready to Remove: " +
// 						   currentTestSubset);
				
				// next run through the algorithm go
				// ahead and remove this test case
				goingToRemove.add(currentTestSubset);
				
				// NOTE: would like to do this, but 
				// cannot because of the CME in Java
				//testSubsets.remove(currentTestSubset);
				
			    }
			
			// I think that it would be at this point that
			// we would resolve any of the types of ties
			// that could occur; this could occur with
			// respect to granularity or randomly; this could
			// be triggered by some type of flag to the 
			// method itself although this would break a lot
			// of the test cases. SO, JUST NOTE THAT I AM
			// REALLY NOT HANDLING TIES!

		    }

// 		System.out.println("minCostRequirementSubsetSet = " + 
// 				   minCostRequirementSubsetSet);

// 		System.out.println("same costs " + equalCostSubsets);

		// add the selected minCostRequirementSubsetSet items
		// to the cover listing (ONCE AGAIN, IGNORING TIES)
		cover.addAll(minCostRequirementSubsetSet);

		// set the purchase price of this test case that we
		// have just selected and then add it to the set of
		// the picked test case (by keeping track of purchase
		// price we can know the order of selection)

// 		System.out.println("minCostSingleTestSubset = " +
// 				   minCostSingleTestSubset);

// 		System.out.println("getTest = " + 
// 				   minCostSingleTestSubset.getTest());

		// purchase the test which means applying the cost
		// for the entire TestSubset to this specific test
		minCostSingleTestSubset.getTest().
		    purchase(minimumTestSubsetCost);

		// add this **SingleTest** into the final coverPickSets
		// note that this might be better as a SingleTestSubset
		// but we are keeping this approach because it breaks
		// the tests and the clients
		coverPickSets.add(minCostSingleTestSubset.getTest());

		// add the SingleTestSubset to the listing that can
		// be accessed after the reduction has been completed
		reducedSingleTestSubsets.add(minCostSingleTestSubset);

		// WRONG: this would take them out of U and we have to
		// compare to U as the algorithm continues to execute

		// remove the one with the minimum cost from 
		// the testSubsets
// 		boolean foundRsu = requirementSubsetUniverse.
// 		   removeAll(minCostRequirementSubsetSet);
		
// 		System.out.println("found rSU ? = " + foundRsu);
// 		System.out.println();

		// we were able to remove this test case from the 
		// overall set of test cases (this flag is really
		// just used for debugging purposes)
		boolean found = testSubsets.remove(minCostSingleTestSubset);

// 		System.out.println("found tS ? = " + found);
// 		System.out.println();

		// increment counter; really just used for the
		// purposes of performance evaluation (always equal to
		// the number of final tests in the test suite)
		outerWhileExecute++;

	    }

	// debugging output for now
// 	System.out.println("size of goingtoRemoveTrack = " + 
// 			   goingToRemoveTrack.size());

	// add back all of the test cases that were removed because
	// their set of test requirements were completely exhausted;
	// this will support prioritization by repeated reduction
	testSubsets.addAll(goingToRemoveTrack);

	// take the timing after running the algorithm
	long reductionTimeAfter = System.currentTimeMillis();

	// calculate the execution time and store the result
	reductionTime = (reductionTimeAfter - reductionTimeBefore);

	// return the result to the calling method; this actually 
	// represents the test cases that we chose to KEEP and 
	// thus this is the reduced test suite that we want!
	return coverPickSets;

    }

*/

    /**
     *  Calculates the redundancies for each of the requirements.
     *  This corresponds to how many tests cover an individual
     *  requirement.  This will populate the individual
     *  RequirementSubsets inside of the universe with their own
     *  redundancy information.  This method also returns the maximum
     *  redundancy factor for all of the test requirements.
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public int calculateRequirementRedundancy()
    {

	// set the maximum frequency for the entire test suite that is
	// represented as a set cover instance Note that the
	// maxFrequency is a measure of the redundancy of a test
	// suite's ability to cover the test requirements -- higher
	// values are indicators that this test suite has a good
	// potential for reduction
	int maxFrequency = 0;

	// Note that this is inefficient in the circumstance when we
	// have converted a HittingSet into a SetCover but absolutely
	// needed in the circumstance when we produce the SetCover
	// directly (which it seems likely that we will now do!)

	// go through the tests and increment the frequencies for 
	// each of the test requirements for the current SingleTest

	// extract an Iterator of the SingleTestSubsets
	Iterator testSubsetIterator = testSubsets.iterator();
	while( testSubsetIterator.hasNext() )
	    {

		// extract the currentTestSubset and the SingleTest
		// that it contains so we can tell the test requirement
		// that it is covered by this test case
		SingleTestSubset currentTestSubset = 
		    (SingleTestSubset) testSubsetIterator.next();

		SingleTest currentTest = 
		    currentTestSubset.getTest();

		// extract an iterator of all of the requirements
		Iterator requirementsIterator = 
		    currentTestSubset.getRequirementSubsetSet().iterator();
		while( requirementsIterator.hasNext() )
		    {

			// indicate that this requirement is covered
			// by this current test case
			RequirementSubset currentRequirement = 
			    (RequirementSubset) requirementsIterator.next();

			currentRequirement.addCoveringTest(currentTest);

		    }

	    }	

	// extract an Iterator of the requirement subset universe
	Iterator reqSubsetUniverseIterator = 
	    requirementSubsetUniverse.iterator();

	// go through all of the requirements and calculate the
	// requirement redundancy levels
	while ( reqSubsetUniverseIterator.hasNext() )
	    {

		// extract the current subset from the listing
		RequirementSubset currentSubset = 
		    (RequirementSubset) reqSubsetUniverseIterator.next();

		// the frequency of this given subset is just the
		// number of test cases that cover it ; the idea here
		// is that if the frequency is high then there is a
		// lot of overlap among the tests and thus there is a
		// strong potential for test suite reduction
		int numberOfCoveringTests = 
		    currentSubset.getCoveringTests().size();

		// set the redundancy for each of the requirements
		currentSubset.setRedundancyFactor(numberOfCoveringTests);

		// we found a more frequent test requirement and 
		// thus we discard the old frequency and use new one
		if( maxFrequency < numberOfCoveringTests )
		    {

			maxFrequency = numberOfCoveringTests;

		    }

	    }

 	return maxFrequency;

    }

    /**
     *  Calculates the redundancies for each of the requirements.
     *  This corresponds to how many tests cover an individual
     *  requirement.  This will populate the individual
     *  RequirementSubsets inside of the universe with their own
     *  redundancy information.  This method also returns the
     *  redundancy factor for all of the test requirements.
     *  
     *  This method was added much after the fact because it allows us
     *  to make really cool ECDFs to characterize the reduction
     *  potential.
     *
     *  @author Gregory M. Kapfhammer 3/3/2007
     */


    public ArrayList calculateAllRequirementRedundancy()
    {

	ArrayList allFrequency = new ArrayList();

	// set the maximum frequency for the entire test suite that is
	// represented as a set cover instance Note that the
	// maxFrequency is a measure of the redundancy of a test
	// suite's ability to cover the test requirements -- higher
	// values are indicators that this test suite has a good
	// potential for reduction
	int maxFrequency = 0;

	// Note that this is inefficient in the circumstance when we
	// have converted a HittingSet into a SetCover but absolutely
	// needed in the circumstance when we produce the SetCover
	// directly (which it seems likely that we will now do!)

	// go through the tests and increment the frequencies for 
	// each of the test requirements for the current SingleTest

	// extract an Iterator of the SingleTestSubsets
	Iterator testSubsetIterator = testSubsets.iterator();
	while( testSubsetIterator.hasNext() )
	    {

		// extract the currentTestSubset and the SingleTest
		// that it contains so we can tell the test requirement
		// that it is covered by this test case
		SingleTestSubset currentTestSubset = 
		    (SingleTestSubset) testSubsetIterator.next();

		SingleTest currentTest = 
		    currentTestSubset.getTest();

// 		System.out.println("Current test: " + currentTest.
// 				   toString());

		// extract an iterator of all of the requirements
		Iterator requirementsIterator = 
		    currentTestSubset.getRequirementSubsetSet().iterator();
		int i = 0;
		while( requirementsIterator.hasNext() )
		    {

// 			System.out.println("i = " + i);
			i++;

			// indicate that this requirement is covered
			// by this current test case
			RequirementSubset currentRequirement = 
			    (RequirementSubset) requirementsIterator.next();

			currentRequirement.addCoveringTest(currentTest);

		    }

	    }	

	// extract an Iterator of the requirement subset universe
	Iterator reqSubsetUniverseIterator = 
	    requirementSubsetUniverse.iterator();

	// go through all of the requirements and calculate the
	// requirement redundancy levels
	while ( reqSubsetUniverseIterator.hasNext() )
	    {

		// extract the current subset from the listing
		RequirementSubset currentSubset = 
		    (RequirementSubset) reqSubsetUniverseIterator.next();

// 		System.out.println("Requirement: " + 
// 				   currentSubset);

		// the frequency of this given subset is just the
		// number of test cases that cover it ; the idea here
		// is that if the frequency is high then there is a
		// lot of overlap among the tests and thus there is a
		// strong potential for test suite reduction
		int numberOfCoveringTests = 
		    currentSubset.getCoveringTests().size();

// 		System.out.println("Covering Tests: " + 
// 				   numberOfCoveringTests);

		allFrequency.add( new Integer(numberOfCoveringTests) );

		// set the redundancy for each of the requirements
		currentSubset.setRedundancyFactor(numberOfCoveringTests);

		// we found a more frequent test requirement and 
		// thus we discard the old frequency and use new one
// 		if( maxFrequency < numberOfCoveringTests )
// 		    {

// 			maxFrequency = numberOfCoveringTests;

// 		    }

	    }

 	return allFrequency;

    }

    /**
     *  Constructs the HGS worst case (tight) problem instance from
     *  the current instance of the set cover problem.
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */


/*

 * Commented out on Nov 30, 2007 by Adam M. Smith to avoid MathLink, Jlink, KernelLink dependencies

    public SetCover deriveWorstCase()
    {

	//SetCover derived = new SetCover();
	SetCover derived = (SetCover)this.clone();
	
	// note that we do not have to make any changes to the 
	// requirementSubsetUniverse because these are going 
	// to stay the same in the initial instance and the 
	// worst case instance

	// note that we might be able to do this in a single
	// step (this would be more efficient) but I am going
	// to first try to implement in multiple steps

	// **** YOU HAVE TO OPERATE ON DERIVED **** 

	// Step 1. clear out all of the requirements that are inside
	// of the SingleTest requirements inside of the testSubsets
	
	// extract an Iterator of all the testSubsets
	Iterator testSubsetsIterator = derived.getTestSubsets().iterator();

	// go through all of the testSubsets and clear 
	// out their requirements listings (we have to repartition
	// these to create a worst case behavior of the algorithm)
	while( testSubsetsIterator.hasNext() )
	    {

		// extract the current SingleTestSubset
		SingleTestSubset currentTestSubset = 
		    (SingleTestSubset) testSubsetsIterator.next();

		// clear out the requirement set 
		currentTestSubset.clearRequirementSubset();

	    }

	// Step 2. assign all of the test requirements to the
	// first n - 1 test cases, leaving the last test case
	// to cover all of the test requirements
	
	// extract an iterator of all of the test requirements
	Iterator requirementsIterator = 
	    derived.getRequirementSubsetUniverse().iterator();

	// this variable can be used to store the last test case
	// that we are going to load with all of the requirements
	// the null is the flag that we are testing against so
	// that we only perform an assignment to this variable once
	SingleTestSubset lastTestCaseSubset = null;

	// keep assigning all of the tests cases to the first
	// n - 1 tests.  Note that we will have to execute the 
	// outer while loop multiple times in order to perform
	// this part of the derivation
	while( requirementsIterator.hasNext() )
	    {

		// extract an iterator of the testSubsets
		Iterator testSubsetsIteratorInner = 
		    derived.getTestSubsets().iterator();

		// go through all of the n - 1 first test case
		// and assign the requirements to them (note 
		// that we might have to do this multiple times)
		while( testSubsetsIteratorInner.hasNext() )
		    {

			// extract the current SingleTestSubset
			SingleTestSubset currentTestSubset = 
			    (SingleTestSubset) 
			    testSubsetsIteratorInner.next();

			// this is the last SingleTest and thus we 
			// are going to reserve it for all of the 
			// tests; do not place any requirement in it
			if( !testSubsetsIteratorInner.hasNext() )
			    {

				// we need to keep track of this test
				// case and we have not done so already
				if( lastTestCaseSubset == null )
				    {

					lastTestCaseSubset = 
					    currentTestSubset;

				    }

				continue;

			    }

			// there is still a requirement and thus 
			// we should assign it to the current test 
			// case by putting it in the subset
			if( requirementsIterator.hasNext() )
			    {

				// extract the next RequirementSubset
				RequirementSubset currentRequirement = 
				    (RequirementSubset) 
				    requirementsIterator.next();

				// we need to clear the set of covering
				// tests because we are re-assigning the
				// requirements to new sets and the 
				// old information is no longer valid 
				// (note that the clone carries it over)
				currentRequirement.clearCoveringTestSet();
				
				// add the current requirement into the 
				// currentTestSubset (which indicates that
				// this SingleTest covers this requirement)
				currentTestSubset.
				    addRequirementSubset(currentRequirement);
			
			    }

		    }

	    }

	// Step 3.  All of the test cases have to be placed into
	// the last SingleTestSubset.  We can also calculate the 
	// cost of this test case to be the inverse of its size
	// (and, then, plus the extraCost that we must calculate)

// 	System.out.println("Last test case subset : " + 
// 			   lastTestCaseSubset);
	
	// set the requirements inside of the last test
	lastTestCaseSubset.
	    setRequirementSubsetSet( (LinkedHashSet) derived.
				     getRequirementSubsetUniverse());
	
	// set the base cost of the last test
// 	lastTestCaseSubset.
// 	    getTest().
// 	    setCost( ( (double) 1 / (double) derived.
// 		       getRequirementSubsetUniverse().size() ) );	

	lastTestCaseSubset.
	    getTest().setCost(1);

	// Step 4. Calculate the costs of the tests with respect to 
	// what must have already been removed.  Note that we know
	// that the test cases that cover the most requirements are
	// at the beginning of the test suite

	// **** YOU HAVE TO OPERATE ON DERIVED **** 

	if( !( derived.getRequirementSubsetUniverse().size() <= 
	       derived.getTestSubsets().size() - 1 ) )  
	    {

// 		System.out.println("Inside the BIG IF");

		// extract an iterator of all of the SingleTestSubsets
		Iterator testSubsetCostIterator = 
		    derived.getTestSubsets().iterator();

		// flag to indicate whether or not we have calculated the 
		// cost for the first test case; we start this at false
		// and then set to true once we have encountered first test
		boolean calculatedFirstCost = false;

		// this is the number of requirements that have been 
		// "removed" because of selection in previous rounds 
		int removedRequirements = 0;

		// this must be the 
		int totalRequirementNumber = 
		    derived.getRequirementSubsetUniverse().size();

		// go through all of the tests in order and calculate the
		// cost that would be associated with picking them 
		while( testSubsetCostIterator.hasNext() )
		    {

			SingleTestSubset currentTestSubset = 
			    (SingleTestSubset) testSubsetCostIterator.next();

			// this must be the first test case and thus its
			// cost has to be the inverse of the size of the 
			// nth test case
			if( !calculatedFirstCost )
			    {

				// we are currently assuming that the first
				// test case must have one or more
				// requirements assigned to it and we will not
				// produce a NaN cost for this test
				// (otherwise, we would have a test suite that
				// does not cover anything)
				
				int currentSize = currentTestSubset.
				    getRequirementSubsetSet().size();

				currentTestSubset.getTest().
				    setCost( ( (double) currentSize / 
					       (double) lastTestCaseSubset.
					       getRequirementSubsetSet().
					       size() ) );
				      
				currentTestSubset.getTest().
				    setDesiredEffectiveness( 
							    lastTestCaseSubset.
						     getRequirementSubsetSet().
							    size() );
 
				removedRequirements += currentSize;	
				
				calculatedFirstCost = true;
				
			    }

			// we have encountered the last test and we do 
			// not need to consider it since its cost has 
			// already been calculated
			else if( !testSubsetCostIterator.hasNext() )
			    {

				continue;

			    }  

			// we are not calculating the cost of the first 
			// test case and we are not at the last test case
			else
			    {
			
				int currentSize = currentTestSubset.
				    getRequirementSubsetSet().size();
				
				double everyCost = 
				    ( (double) currentSize / 
				      (double) 
				      (totalRequirementNumber - 
				       removedRequirements) );

				currentTestSubset.getTest().
				    setCost( everyCost );
				
				currentTestSubset.
				    getTest().
				    setDesiredEffectiveness( 
						  (totalRequirementNumber - 
						   removedRequirements) );

				removedRequirements += currentSize;
				
			    }

		    }

		// Step 5. Calculate the extra cost for T_n which will ensure
		// that we always select the smaller size test suite instead
		// of picking the largest test suite that in the end would
		// ensure the lowest cost for executing the test suite
	
		KernelLink ml = null;

		String mathematicaConnect = 
		    "-linkmode launch -linkname \'math -mathlink\'";

		//		ml = Util.getMathematicaLink();

		try 
		    {
		
			ml = MathLinkFactory.
			    createKernelLink(mathematicaConnect);
		
// 			System.out.println("after kernel link");

		    } 

		catch (MathLinkException e) 
		    {
		
			System.out.println("Fatal error opening link: " + 
					   e.getMessage());
			e.printStackTrace();
	    
		    }

		try 
		    {
			
			// Get rid of the initial InputNamePacket the kernel
			// will send when it is launched.
			ml.discardAnswer();

			// these are the StringBuffers that will contain the 
			// final Mathematica code that we will send 
			StringBuffer solveFirstLine = new StringBuffer();
			StringBuffer solveAfterFirst = new StringBuffer();

			// call FindInstance and give numerical approximation
			solveFirstLine.append("N[FindInstance[");

			// get an iterator of the costs and build up string
			Iterator testSolveIterator = 
			    derived.getTestSubsets().iterator();
		
// 			System.out.println("before the while tSI.hasNext");

			while( testSolveIterator.hasNext() )
			    {

				SingleTestSubset currentTestSolve = 
				    (SingleTestSubset) 
				    testSolveIterator.next();

				// there is still another term and thus we
				// have to include a plus sign 
				if( testSolveIterator.hasNext() )
				    {

					double currentCost = 
					    currentTestSolve.getTest().
					    getCost();

					// add to the first conjunct
					// related to the costs of
					// each test case
					solveFirstLine.
					    append(currentCost);

					// add the the last
					// conjunctions for the
					// relationships between each
					// cost effective ness value
					// (currently, F in the notes)
					solveAfterFirst.
					    append( " (" + "1 / " +
						   currentTestSolve.getTest().
						   getDesiredEffectiveness() + 
						    ") < ");

					solveAfterFirst.
					    append( "(" + 
						    NUMERATOR_EFFECTIVE + 
						    " + ec)/");
				
					solveAfterFirst.
					    append(currentTestSolve.getTest().
						   getDesiredEffectiveness() );
				
					// we can assume that the last test
					// case will always have a cost of 1.0
					// and thus we should only append the
					// plus sign if this is not where we
					// are currently looking
					if( currentCost != LAST_TEST_COST )
					    {
						
						solveFirstLine.append(" + ");
						solveAfterFirst.append(" &&");

					    }

				    }
				
				// we are on the last test case and it
				// does not get appended to this part
				// of the inequality
				else
				    {

					continue;

				    }

			    }
		
			// add on the appropriate inequality ; we are
			// always assuming default cost for the last
			// test case and the free variable ec for the
			// "extra cost"
			solveFirstLine.append(" > 1 + ec &&");

			solveAfterFirst.append(", ec]]");

			// debugging output for now
// 			System.out.println("solveFirstLine = " +
// 					   solveFirstLine.toString());
// 			System.out.println("solveAfterFirst = " +
// 					   solveAfterFirst.toString());
// 			System.out.println(solveFirstLine.toString() + " " +
// 					   solveAfterFirst.toString() );

			// send the final string to Mathematica through 
			// J/Link and retrieve the answer as a string
			String solveForEc = ml.
			    evaluateToOutputForm( solveFirstLine.
						  toString() + " " +
						  solveAfterFirst.
						  toString(), 0 );

// 			System.out.println("solveForEc = " + solveForEc);

			// use Java regular expressions to isolate the decimal
			// that is inside of the String that is returned 
			// from Mathematica 
			Pattern decimalPattern = Pattern.
			    compile("[0-9]*[\\.][0-9]*");
			Matcher decimalMatcher = decimalPattern.
			    matcher(solveForEc);
			boolean decimalMatches = decimalMatcher.find();
			
			// we found the decimal inside of the String that is
			// returned by Mathematica and we must 
			if( decimalMatches )
			    {

				String finalDecimal = decimalMatcher.group();
				
// 				System.out.println( "Decimal = " + 
// 						    finalDecimal );

				Double extraCostD = new Double(finalDecimal);
				
				lastTestCaseSubset.getTest().
				    setExtraCost( extraCostD.doubleValue() );

			    }

			else
			    {
				
				// for debugging only!
				//System.out.println("did not match");

			    }

			ml.close();

		    }

		catch (MathLinkException e) 
		    {
		
			System.out.println("MathLinkException occurred: " + 
					   e.getMessage());
			e.printStackTrace();
		
		    } 

		finally 
		    {
		
			ml.close();
		
		    }

	    }

	// there are less test requirements than there are test cases
	else
	    {

// 		System.out.println("In LITTLE else");

		lastTestCaseSubset.getTest().setCost(LAST_TEST_COST);

		int maxDenominator = 
		    derived.getRequirementSubsetUniverse().size();
		int initialMaxDenominator = maxDenominator;
		
		Iterator testSubsetCostIterator = 
		    derived.getTestSubsets().iterator();

		while( testSubsetCostIterator.hasNext() )
		    {

			SingleTestSubset currentTestSubset = 
			    (SingleTestSubset) 
			    testSubsetCostIterator.next();

			if( maxDenominator >= 1 )
			    {

				currentTestSubset.getTest().
				    setCost( (double) 1 / 
					     (double) maxDenominator );

				maxDenominator--;

			    }

			else
			    {

				if( currentTestSubset != 
				    lastTestCaseSubset )
				    {

					currentTestSubset.getTest().
					    setCost( NO_COVER_COST );

				    }

			    }

		    }

		// build up the string that has to be sent to Mathematica
		// in order to calculate the extra cost
		StringBuffer extraCost = new StringBuffer();
		extraCost.append("N[FindInstance[");

		extraCost.append("(" + 1 + "/" + 
				 initialMaxDenominator + ") < " + 
				 "((" + 1 + "/" + 
				 initialMaxDenominator + ") + ec) &&");

		extraCost.append("HarmonicNumber[" + 
				 initialMaxDenominator + "] > " +  
				  "((" + 1 + "/" + 
				 initialMaxDenominator + ") + ec)");
		
		extraCost.append(", ec ]]");

// 		System.out.println("extraCost = ");
// 		System.out.println();
// 		System.out.println(extraCost);

		KernelLink ml = null;

		String mathematicaConnect = 
		    "-linkmode launch -linkname \'math -mathlink\'";

		try 
		    {
		
			ml = MathLinkFactory.
			    createKernelLink(mathematicaConnect);
		
// 			System.out.println("after kernel link");

		    } 

		catch (MathLinkException e) 
		    {
		
			System.out.println("Fatal error opening link: " + 
					   e.getMessage());
			e.printStackTrace();
	    
		    }

		try 
		    {
			
			// Get rid of the initial InputNamePacket the kernel
			// will send when it is launched.
			ml.discardAnswer();

			// send the final string to Mathematica through 
			// J/Link and retrieve the answer as a string
			String solveForEc = ml.
			    evaluateToOutputForm( extraCost.
						  toString(), 0 );

// 			System.out.println("solveForEc = " + solveForEc);

			// use Java regular expressions to isolate the decimal
			// that is inside of the String that is returned 
			// from Mathematica 
			Pattern decimalPattern = Pattern.
			    compile("[0-9]*[\\.][0-9]*");
			Matcher decimalMatcher = decimalPattern.
			    matcher(solveForEc);
			boolean decimalMatches = decimalMatcher.find();
			
			// we found the decimal inside of the String that is
			// returned by Mathematica and we must 
			if( decimalMatches )
			    {

				String finalDecimal = decimalMatcher.group();
				
// 				System.out.println( "Decimal = " + 
// 						    finalDecimal );

				Double extraCostD = new Double(finalDecimal);
				
				lastTestCaseSubset.getTest().
				    setExtraCost( extraCostD.doubleValue() );

			    }

			else
			    {
				
				// for debugging only!
// 				System.out.println("did not match");

			    }

		    }

		catch (MathLinkException e) 
		    {
		
			System.out.println("MathLinkException occurred: " + 
					   e.getMessage());
			e.printStackTrace();
		
		    } 

		finally 
		    {
		
			ml.close();
		
		    }

	    }

	return derived;

    }

*/
    /**
     *  Finds the maximum cost test cases.  
     *  
     *  @author Gregory M. Kapfhammer 10/12/2005
     */
    public Set findMaximumCostTests()
    {

	// this is the listing of all of the tests that have maximum
	// cost within the test suite; most likely for most tests this
	// will end up being a Singleton set
	LinkedHashSet maximumCostTests = new LinkedHashSet();

	// the initial cost of the maximum cost test case
	double maxTestCost = 0.0;

	// extract an iterator of all of the test subsets
	Iterator testSubsetsIterator = testSubsets.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		SingleTestSubset currentSubset = 
		    (SingleTestSubset) testSubsetsIterator.next();

		SingleTest currentTest = 
		    currentSubset.getTest();

		double currentTestCost = currentTest.getCost();

		// we found a test case that covers more than the 
		// one(s) that we found previously
		if( currentTestCost > maxTestCost )
		    {

			// clear out the set of max covering tests
			maximumCostTests.clear();

			// add in this new test case to the set
			// of maximum covering tests
			maximumCostTests.add(currentSubset);

			// assign the new maximum covering number
			maxTestCost = currentTestCost;

		    }

		// there is another test case that covers the same
		// NUMBER of test requirements (not checking here to 
		// actually see what the requirements ARE) and thus
		// we have to add it to the listing
		else if( currentTestCost == maxTestCost )
		    {

			maximumCostTests.add(currentSubset);
			
		    }


	    }

	return maximumCostTests;

    }

    /**
     *  Finds the minimum cost test cases.  
     *  
     *  @author Gregory M. Kapfhammer 10/12/2005
     */
    public Set findMinimumCostTests()
    {

	// this is the listing of all of the tests that have maximum
	// cost within the test suite; most likely for most tests this
	// will end up being a Singleton set
	LinkedHashSet minimumCostTests = new LinkedHashSet();

	// the initial cost of the maximum cost test case
	double minTestCost = Double.MAX_VALUE;

	// extract an iterator of all of the test subsets
	Iterator testSubsetsIterator = testSubsets.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		SingleTestSubset currentSubset = 
		    (SingleTestSubset) testSubsetsIterator.next();

		SingleTest currentTest = 
		    currentSubset.getTest();

		double currentTestCost = currentTest.getCost();

		// we found a test case that covers more than the 
		// one(s) that we found previously
		if( currentTestCost < minTestCost )
		    {

			// clear out the set of max covering tests
			minimumCostTests.clear();

			// add in this new test case to the set
			// of maximum covering tests
			minimumCostTests.add(currentSubset);

			// assign the new maximum covering number
			minTestCost = currentTestCost;

		    }

		// there is another test case that covers the same
		// NUMBER of test requirements (not checking here to 
		// actually see what the requirements ARE) and thus
		// we have to add it to the listing
		else if( currentTestCost == minTestCost )
		    {

			minimumCostTests.add(currentSubset);
			
		    }

	    }

	return minimumCostTests;

    }

    /**
     *  Finds the maximum redundancy factor test cases.  This
     *  indicates that these tests are likely to be included in the
     *  final reduced test suite because they cover lots of
     *  requirements that are covered by the other test cases.
     *  
     *  @author Gregory M. Kapfhammer 10/12/2005
     */
    public Set findMaximumRedundancyFactorTests()
    {
	
	// this is the listing of all of the tests that have maximum
	// cost within the test suite; most likely for most tests this
	// will end up being a Singleton set
	LinkedHashSet maxRedundancyTests = new LinkedHashSet();

	// the initial cost of the maximum cost test case
	double maxTestRedundancy = 0.0;

	// note that we are still keeping track of the tests and not
	// the requirements themselves; this means we look at all of
	// the individual redundancy factors for all of the
	// requirements for a given test and use this to determine the
	// max for the entire test suite

	// extract an iterator of all of the test subsets
	Iterator testSubsetsIterator = testSubsets.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		// this is the current SingleTestSubset
		SingleTestSubset currentSubset = 
		    (SingleTestSubset) testSubsetsIterator.next();
		
		// extract an iterator of the requirement subsets
		// so that we can measure their individual costs
		Iterator requirementSubsetIterator = 
		    currentSubset.getRequirementSubsetSet().iterator();

		// go through all of the individual requirement
		// subsets ; each one of these has an already computed
		// redundancy factor
		while( requirementSubsetIterator.hasNext() )
		    {

			// extract the current requirement
			RequirementSubset currentRequirement = 
			    (RequirementSubset) 
			    requirementSubsetIterator.next();

			// the redundancy factor for this requirement
			int currentRedundancyFactor = 
			    currentRequirement.getRedundancyFactor();

			// we found a test case that covers more than the 
			// one(s) that we found previously
			if( currentRedundancyFactor > maxTestRedundancy )
			    {

				// clear out the set of max RF tests
				maxRedundancyTests.clear();

				// add in this new test case to the
				// set of maximum RF tests (might be
				// adding multiple times, but this is
				// okay because we are treating it as
				// a set)
				maxRedundancyTests.add(currentSubset);

				// assign the new maximum covering
				// number
				maxTestRedundancy = currentRedundancyFactor;

			    }

			// there is another test case that covers the same
			// NUMBER of test requirements (not checking here to 
			// actually see what the requirements ARE) and thus
			// we have to add it to the listing
			else if( currentRedundancyFactor == maxTestRedundancy )
			    {
				
				maxRedundancyTests.add(currentSubset);
				
			    }

		    }

	    }

	return maxRedundancyTests;

    }

    /**
     *  Finds the maximum AVERAGE redundancy factor test cases.  This
     *  indicates that these tests are likely to be included in the
     *  final reduced test suite because they cover lots of
     *  requirements that are covered by the other test cases.
     *  
     *  @author Gregory M. Kapfhammer 10/12/2005
     */
    public Set findMaximumAverageRedundancyFactorTests()
    {
	
	// this is the listing of all of the tests that have maximum
	// cost within the test suite; most likely for most tests this
	// will end up being a Singleton set
	LinkedHashSet maxRedundancyTests = new LinkedHashSet();

	// the initial cost of the maximum cost test case
	double maxTestRedundancy = 0.0;

	// note that we are still keeping track of the tests and not
	// the requirements themselves; this means we look at all of
	// the individual redundancy factors for all of the
	// requirements for a given test and use this to determine the
	// max for the entire test suite

	// extract an iterator of all of the test subsets
	Iterator testSubsetsIterator = testSubsets.iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		// this is the current SingleTestSubset
		SingleTestSubset currentSubset = 
		    (SingleTestSubset) testSubsetsIterator.next();
		
		// extract an iterator of the requirement subsets
		// so that we can measure their individual costs
		Iterator requirementSubsetIterator = 
		    currentSubset.getRequirementSubsetSet().iterator();

		// the total number of requirements for this SingleTest
		int totalNumberRequirements = 0;

		// the sum of all of the redundancy factors for this 
		// listing of requirements
		int redundancyFactorSum = 0;

		// go through all of the individual requirement
		// subsets ; each one of these has an already computed
		// redundancy factor
		while( requirementSubsetIterator.hasNext() )
		    {

			// extract the current requirement
			RequirementSubset currentRequirement = 
			    (RequirementSubset) 
			    requirementSubsetIterator.next();

			// the redundancy factor for this requirement
			int currentRedundancyFactor = 
			    currentRequirement.getRedundancyFactor();

			redundancyFactorSum = redundancyFactorSum +
			    currentRedundancyFactor;

			totalNumberRequirements++;
		
		    }

		// this is the average redundancyFactor
		double averageRedundancyFactor = 
		    ( (double) redundancyFactorSum ) /
		    ( (double) totalNumberRequirements );

		// we found a test case that covers more than the 
		// one(s) that we found previously
		if( averageRedundancyFactor > maxTestRedundancy )
		    {

			// clear out the set of max RF tests
			maxRedundancyTests.clear();

			// add in this new test case to the set of
			// maximum RF tests (might be adding multiple
			// times, but this is okay because we are
			// treating it as a set)
			maxRedundancyTests.add(currentSubset);

			// assign the new maximum covering number
			maxTestRedundancy = averageRedundancyFactor;

		    }

		// there is another test case that covers the same
		// NUMBER of test requirements (not checking here to 
		// actually see what the requirements ARE) and thus
		// we have to add it to the listing
		else if( averageRedundancyFactor == maxTestRedundancy )
		    {
				
			maxRedundancyTests.add(currentSubset);
			
		    }

	    }

	return maxRedundancyTests;

    }

    /**
     *  Produces a set of the test cases that achieve maximum coverage 
     *  of the test requirements.  Intuitively, these are the tests that 
     *  are likely to be included in the final reduced test suite as 
     *  long as they are not too costly.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */
    public Set findMaximumCoverageTests()
    {

	LinkedHashSet maximumCoverageTests = new LinkedHashSet();
	int maxCoveringNumber = 0;

	// extract an iterator of all of the SingleTestSubsets
	Iterator singleTestSubsetIterator = testSubsets.iterator();

	// look through the iterator for the test case that 
	// covers the most test requirements
	while( singleTestSubsetIterator.hasNext() )
	    {

		SingleTestSubset currentSubset = 
		    (SingleTestSubset) singleTestSubsetIterator.next();

		int currentCoveringNumber = 
		    currentSubset.getRequirementSubsetSet().size();

		// we found a test case that covers more than the 
		// one(s) that we found previously
		if( currentCoveringNumber > maxCoveringNumber )
		    {

			// clear out the set of max covering tests
			maximumCoverageTests.clear();

			// add in this new test case to the set
			// of maximum covering tests
			maximumCoverageTests.add(currentSubset);

			// assign the new maximum covering number
			maxCoveringNumber = currentCoveringNumber;

		    }

		// there is another test case that covers the same
		// NUMBER of test requirements (not checking here to 
		// actually see what the requirements ARE) and thus
		// we have to add it to the listing
		else if( currentCoveringNumber == maxCoveringNumber )
		    {

			maximumCoverageTests.add(currentSubset);
			
		    }

	    }

	return maximumCoverageTests;

    }

    /**
     *  Produces a set of the test cases that achieve minimum coverage
     *  of the test requirements.  Intuitively, these are the tests
     *  that are not as likely to be included in the final reduced
     *  test suite because they do not cover as much.
     *  
     *  @author Gregory M. Kapfhammer 9/24/2005
     */
    public Set findMinimumCoverageTests()
    {

	LinkedHashSet minimumCoverageTests = new LinkedHashSet();
	int minCoveringNumber = Integer.MAX_VALUE;

	// extract an iterator of all of the SingleTestSubsets
	Iterator singleTestSubsetIterator = testSubsets.iterator();

	// look through the iterator for the test case that 
	// covers the most test requirements
	while( singleTestSubsetIterator.hasNext() )
	    {

		SingleTestSubset currentSubset = 
		    (SingleTestSubset) singleTestSubsetIterator.next();

		int currentCoveringNumber = 
		    currentSubset.getRequirementSubsetSet().size();

		// we found a test case that covers more than the 
		// one(s) that we found previously
		if( currentCoveringNumber < minCoveringNumber )
		    {

			// clear out the set of max covering tests
			minimumCoverageTests.clear();

			// add in this new test case to the set
			// of maximum covering tests
			minimumCoverageTests.add(currentSubset);

			// assign the new maximum covering number
			minCoveringNumber = currentCoveringNumber;

		    }

		// there is another test case that covers the same
		// NUMBER of test requirements (not checking here to 
		// actually see what the requirements ARE) and thus
		// we have to add it to the listing
		else if( currentCoveringNumber == minCoveringNumber )
		    {

			minimumCoverageTests.add(currentSubset);
			
		    }

	    }

	return minimumCoverageTests;

    }

    /**
     *  Finds the test cases that have high redundancy and low cost.
     *  
     *  Note that selection to be included in the set of tests that 
     *  match the "high redundancy and low cost" description include:
     *
     *  1. test cost <= mininum cost + (minCostFactor * minimum cost)
     *
     *  2. test redundancy factor >= maximum redundancy * percent redundancy
     *  
     *  Finally note that the minCostFactor in equation (1) is
     *  included in the list of the paramters as percentMinCost.  This
     *  is a slight misnomer because it is not really a percent; it
     *  can be greater than one (and potentially less than 0?).  See
     *  the test cases for some examples.
     *
     *  @author Gregory M. Kapfhammer 10/12/2005
     */
    public Set findHighRedundancyLowCostTests(double maxRedundancyFactor,
					      double percentMaxRedundancy,
					      double minCost,
					      double percentMinCost)
    {

	// this is the listing of the tests that have high redundancy
	// and low cost according to the established criterion
	LinkedHashSet maxRedundancyMinCost = new LinkedHashSet();

	// extract an iterator of all of the SingleTestSubsets
	Iterator singleTestSubsetIterator = testSubsets.iterator();

	// calculate the threshold values that we must see
	
	// redundancy threshold sets the bar that a test's redundancy
	// factor must be greater than (or equal to) for inclusion
	double redundancyIncludeThreshold = 
	    percentMaxRedundancy * maxRedundancyFactor;

// 	System.out.println("redundancyInclude = " + 
// 			   redundancyIncludeThreshold);

	// cost threshold sets the bar that a test's cost must be less
	// than (or equal to) for inclusion
	double costIncludeThreshold = 
	    minCost + (percentMinCost * minCost);

// 	System.out.println("costIncludeThreshold = " +
// 			   costIncludeThreshold);

	// look through the iterator for the test case that 
	// covers the most test requirements
	while( singleTestSubsetIterator.hasNext() )
	    {

		// extract the current SingleTestSubset
		SingleTestSubset currentTestSubset = 
		    (SingleTestSubset) singleTestSubsetIterator.next();

		// get the current test out of the SingleTestSubset
		SingleTest currentTest = currentTestSubset.getTest();

		// this current test has a cost that is under the 
		// threshold and thus we must check to see about its
		// redundancy factor (this is probably a good screen)
		if( currentTest.getCost() <= costIncludeThreshold )
		    {

			// look through the different redundancy
			// factors for this SingleTest and find 
			// the maximum, ***average***, what?
			Iterator testSubsets = 
			    currentTestSubset.
			    getRequirementSubsetSet().
			    iterator();
			
			// note that our current implementation looks
			// at the average redundancy factor for a test
			// case; this requires that we look at them
			// all and then compute the average

			// it might be desirable to refactor this code
			// so that we take it into a separate method
			// that is just responsible for computing the
			// average redundancy factor

			// keep running total of the sum of the
			// redundancy factor and the total number of
			// requirements that have already been
			// analyzed
			double sumRedundancyFactor = 0.0;	       	
			int totalNumberRequirements = 0;

			// go through all of the test subsets for each
			// of the requirements and extract the already
			// calculated redundancy factor
			while( testSubsets.hasNext() )
			    {

				// this is the current requirement
				RequirementSubset currentRequirementSubset = 
				    (RequirementSubset) testSubsets.next();

				// add in this redundancy factor
				// to the running sum and increment
				// the counter that keeps track of 
				// how many requirements we have analyzed
				// so far
				sumRedundancyFactor = 
				    sumRedundancyFactor + 
				    currentRequirementSubset.
				    getRedundancyFactor();
				totalNumberRequirements++;

			    }
			
			// calculate the average redundancy factor
			// (note that we want this to be a double)
			double averageRedundancyFactor = 
			    ( (double) sumRedundancyFactor ) /
			    ( (double) totalNumberRequirements );

			// we have found a test with redundancy that has
			// a greater redundancy factor than the threshold
			// since we already know that its cost is under
			// the threshold, we should go ahead and add it 
			// to the listing of "good" test cases
			if( averageRedundancyFactor >= 
			    redundancyIncludeThreshold )
			    {

				maxRedundancyMinCost.
				    add(currentTestSubset);

			    }

		    }

	    }

	return maxRedundancyMinCost;

    }

    /**
     *  Finds the test cases that have high coverage and low cost.
     *  
     *  Note that selection to be included in the set of tests that 
     *  match the "high redundancy and low cost" description include:
     *
     *  1. test cost <= mininum cost + (minCostFactor * minimum cost)
     *
     *  2. test coverage >= maximum coverage * percent maximum coverage
     *
     *  Note that the term percentMinCost is also a misnomer in this
     *  paramter listing because it is not a percentage per se.  See
     *  the test cases for an example of what this value can really
     *  be.
     *  
     *  @author Gregory M. Kapfhammer 10/12/2005
     */
    public Set findHighCoverageLowCostTests(double maxCoverage,
					    double percentMaxCoverage,
					    double minCost,
					    double percentMinCost)
    {

	// this is the listing of the tests that have high coverage
	// and low cost according to the established criterion
	LinkedHashSet maxCoverageMinCost = new LinkedHashSet();

	// extract an iterator of all of the SingleTestSubsets
	Iterator singleTestSubsetIterator = testSubsets.iterator();

	// calculate the threshold values that we must see
	
	// redundancy threshold sets the bar that a test's redundancy
	// factor must be greater than (or equal to) for inclusion
	double coverageIncludeThreshold = 
	    percentMaxCoverage * maxCoverage;

// 	System.out.println("coverageInclude = " + 
// 			   coverageIncludeThreshold);

	// cost threshold sets the bar that a test's cost must be less
	// than (or equal to) for inclusion
	double costIncludeThreshold = 
	    minCost + (percentMinCost * minCost);

// 	System.out.println("costIncludeThreshold = " +
// 			   costIncludeThreshold);

	// look through the iterator for the test case that 
	// covers the most test requirements
	while( singleTestSubsetIterator.hasNext() )
	    {

		// extract the current SingleTestSubset
		SingleTestSubset currentTestSubset = 
		    (SingleTestSubset) singleTestSubsetIterator.next();

		// get the current test out of the SingleTestSubset
		SingleTest currentTest = currentTestSubset.getTest();

// 		System.out.println("currentTest = " + 
// 				   currentTest.getName());

		// this current test has a cost that is under the 
		// threshold and thus we must check to see about its
		// redundancy factor (this is probably a good screen)
		if( currentTest.getCost() <= costIncludeThreshold )
		    {

// 			System.out.println("cost is under threshold");

			// we just need to determine how many test 
			// cases this current test covers; this is 
			// just the size of the requirementsSubsetsSet
			int testCoverage = 
			    currentTestSubset.
			    getRequirementSubsetSet().size();

			// TESTING RESEARCHER NOTE: this was an example
			// of a nice bug!  I had the conditional logic
			// statement 

			// if( testCoverage >= 
			//    costIncludeThreshold )
			
			// and it should have been the
			// coverageIncludeThresh several test cases
			// passed and did not reveal this bug.  This
			// might be a good example to include in 
			// future writing

			// we have found a test whose coverage is
			// greater than the threshold; we already know
			// that its cost is under the threshold; thus
			// go ahead and add it into the set of the
			// "best" test cases
			if( testCoverage >= 
			    coverageIncludeThreshold )
			    {

				maxCoverageMinCost.
				    add(currentTestSubset);

			    }

		    }

	    }

	return maxCoverageMinCost;

    }

    /**
     *  Determines the size of the overfilled sets in worst-case
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public double worstCaseMaxSize()
    {

	int numberOfTests = testSubsets.size();
	int totalRequirementNum = requirementSubsetUniverse.size();

	return Math.ceil( ( (double)totalRequirementNum /  
			    (double) (numberOfTests-1)  ) );
	

    }

    /**
     *  Determines the size of the underfilled sets in worst-case
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public double worstCaseMinSize()
    {

	int numberOfTests = testSubsets.size();
	int totalRequirementNum = requirementSubsetUniverse.size();

	return Math.floor( ( (double)totalRequirementNum /  
			    (double) (numberOfTests-1) ) );

    }

    /**
     *  Determines the number of the overfilled sets in worst-case
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public int worstCaseMaxNumber()
    {

	int numberOfTests = testSubsets.size();
	int totalRequirementNum = requirementSubsetUniverse.size();

	return totalRequirementNum % (numberOfTests-1);

    }    

    /**
     *  Determines the number of the overfilled sets in worst-case
     *  
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public int worstCaseMinNumber()
    {

	int numberOfTests = testSubsets.size();
	int totalRequirementNum = requirementSubsetUniverse.size();

	return ( numberOfTests - (totalRequirementNum % numberOfTests) );

    }    

   
   /**
    * Serializes a SetCover to a byte array, retaining the object graph.
    * @author Adam M. Smith 08/01/2007
    *
    * 
    */
   
   public FastByteArrayOutputStream createFastByteArrayOutputStream() {
   	FastByteArrayOutputStream fbos = null;
   	try {
   		// Write the object out to a byte array
           fbos = new FastByteArrayOutputStream();
           ObjectOutputStream out = new ObjectOutputStream(fbos);
           out.writeObject(this);
           out.flush();
           out.close();
		}
		
		catch(IOException e) { 
			e.printStackTrace();
		}
		     
      return fbos;
   
   }
   
   
   /**
    * Clones a SetCover from the bottom up
    * @author Adam M. Smith 07/31/2007
    *
    * This code did not retain the cloned objects graph and so is not used.  Serialization using JOS is 
    * used instead.
    */
  
  
  /* 
   
   public SetCover cloneBottomUp() {
   
   	SetCover clone = new SetCover();
   	
   	LinkedHashSet newRequirements = new LinkedHashSet(); 
		LinkedHashSet newSingleTestSubsets = new LinkedHashSet();
		LinkedHashSet newTests = new LinkedHashSet();
		
		
		Iterator getRequirementsIterator = requirementSubsetUniverse.iterator();
		
		// copy the requirementSubsetUniverse
		while (getRequirementsIterator.hasNext()){
			newRequirements.add( (RequirementSubset) ( (RequirementSubset) getRequirementsIterator.next()).clone() );
   	}
   	
   	// Make the list of Singletests
     	Iterator newRequirementsIterator = newRequirements.iterator();
     	
     	System.out.println("about to add in clone ************\n\n\n");
     	
     	while (newRequirementsIterator.hasNext()){
     		Iterator getTestsIterator = ((RequirementSubset) newRequirementsIterator.next()).getCoveringTests().iterator();
     		while (getTestsIterator.hasNext()){
     			SingleTest currentSingleTest = (SingleTest) getTestsIterator.next();
     			if (!newTests.contains(currentSingleTest)){
     				newTests.add(currentSingleTest);
     				System.out.println(currentSingleTest);
     			}
     		}
     	}
   		
   	Iterator testSuiteUniverseIterator = newTests.iterator();
   	
   	while (testSuiteUniverseIterator.hasNext()){
   		SingleTest currentTest = (SingleTest) testSuiteUniverseIterator.next();
   		
   		Iterator newRequirementsIteratorAgain = newRequirements.iterator();
   		
   		SingleTestSubset sts = new SingleTestSubset(currentTest);
   		
   		while (newRequirementsIteratorAgain.hasNext()){
   			RequirementSubset currentSubset = (RequirementSubset) newRequirementsIteratorAgain.next();
   			
   			if (currentSubset.containsSingleTest(currentTest)){
   				sts.addRequirementSubset(currentSubset);
   			}
   			
   			clone.addSingleTestSubset(sts);
   			
   		}
   	}
   	   
   	   clone.setRequirementSubsetUniverse(newRequirements);
   	return clone;
   }
   */
   
    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
     
    public Object clone()
    {

	

	SetCover clone = new SetCover();
	
	// create a universe for the clone
// 	clone.
// 	    setRequirementSubsetUniverse(this.
// 					 getRequirementSubsetUniverse());

	LinkedHashSet newRSU = new LinkedHashSet();
	Iterator rsuIterator = 
	    this.getRequirementSubsetUniverse().iterator();
	while( rsuIterator.hasNext() )
	    {

		RequirementSubset currentSubset = 
		    (RequirementSubset)rsuIterator.next();

		newRSU.add(currentSubset.clone());

	    }

	clone.setRequirementSubsetUniverse(newRSU);
	
	// create a new SingleTestSubset for the clone
// 	clone.
// 	    setTestSubsetsSet( (LinkedHashSet) 
// 			       this.getTestSubsets().clone() );

	LinkedHashSet newSet = new LinkedHashSet();
	Iterator testSubsetsIterator = this.getTestSubsets().iterator();
	while( testSubsetsIterator.hasNext() )
	    {

		SingleTestSubset currentTs = 
		    (SingleTestSubset) testSubsetsIterator.next();

		newSet.add(currentTs.clone());
		
	    }

	clone.setTestSubsetsSet(newSet);

// 	if( this.getBeforeReduction() != null )
// 	    {

// 		SetCover beforeInThis = this.getBeforeReduction();
// 		SetCover beforeInClone = (SetCover)beforeInThis.clone();
// 		clone.setBeforeReduction(beforeInClone);

// 	    }

	return clone;

    }

    /**
     *  @author Gregory M. Kapfhammer 9/20/2005
     */
    public String toString()
    {

	return "SetCover(U = " + requirementSubsetUniverse + 
	    " U.size = " + requirementSubsetUniverse.size() + ", S=" + 
	    testSubsets + " S.size = " + testSubsets.size() + ")";

//  	return "SetCover(U = " + requirementSubsetUniverse +  ", S=" + 
//  	    testSubsets + ")";

    }
    
    public static String getCoveringTestSetString(Set inSet,String separator)
	{
		String output="";
		Iterator coverPickSetsIterator = inSet.iterator();
		
		if (coverPickSetsIterator.hasNext())
		{
			int index = (((SingleTest) coverPickSetsIterator.next()).getIndex()+1);
			output = (output+index);
		}
		
		while (coverPickSetsIterator.hasNext())	
		{
			int index = (((SingleTest) coverPickSetsIterator.next()).getIndex()+1);
			output = (output+separator+index );
		}
		
		return output;
	
	}

}
