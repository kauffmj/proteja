/******************************************************************************
 *
 * CompressMatrix.java
 *
 * Reads in a coverage matrix with or without summary rows or
 * columns and saves the compressed matrix to a new file.
 *
 * Jonathan Miller Kauffman
 *
 *****************************************************************************/

package proteja;

import java.util.Scanner;
import java.math.BigInteger;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CompressMatrix
{
	public static void main(String[] args)
	{
		if(args.length != 2)
		{
			System.out.println("Usage: java CompressMatrix [matrix name] " + 
				"[summary row and column?]");
			System.exit(1);
		}

		// The names of the matrix to be read and the compressed matrix to
		// be written, as well as whether the matrix contains a summary
		// row and column.
		File matrix = new File(args[0]);
		File compressedMatrix = new File(args[0] + "_compressed");
		boolean summaryInfo = Boolean.parseBoolean(args[1]);

		// Read in each row and the numbers in each row.
		Scanner scanRows = null;
		Scanner scanRowSymbols = null;

		// Stores the value at each position in the bitstring.
		BigInteger temp = new BigInteger("0");

		// The current column number.
		int i = 0;

		// Holds the current sum and its corresponding string representation.
		BigInteger sum = new BigInteger("0");
		String str = "";

		// Used to calculate the number of columns (tests) in the matrix.
		boolean firstTime = true;
		int numTests = 0;
		int tempNumTests = 0;

		// Holds the BigInteger 2.
		BigInteger two = new BigInteger("2");

		try
		{
			FileWriter fileOut = new FileWriter(compressedMatrix);
            scanRows = new Scanner(matrix);

            while (scanRows.hasNextLine())
			{
				// Initialize the sum and the current test number.
				sum = new BigInteger("0");
				i = 0;

				String line = scanRows.nextLine();

				// If this row is a summary row, stop looping.
				if(summaryInfo && (!scanRows.hasNextLine()))
					break;

				// Calculate the number of tests if this is the first row read.
				if(firstTime)
				{
					scanRowSymbols = new Scanner(line);

					while (scanRowSymbols.hasNext())
					{
						scanRowSymbols.nextInt();
						numTests++;
					}

					if(summaryInfo)
						numTests--;

					// Write the number of tests to the first line of the file.
					str = String.valueOf(numTests);
				
        			char[] a = new char[str.length()];
       				str.getChars(0, str.length(), a, 0);
        			for (int j = 0; j < a.length; j++)
					{
            			fileOut.write(a[j]);
       				}
					
					fileOut.write('\n');

					numTests--;

					firstTime = false;
				}

				// Calculates the sum of each row.
				scanRowSymbols = new Scanner(line);
				tempNumTests = 0;

				while (scanRowSymbols.hasNext() && tempNumTests <= numTests)
				{
					if(scanRowSymbols.nextInt() == 1)
					{
						temp = two.pow(numTests-i);
						//str = String.valueOf(temp);
						sum = sum.add(temp);
					}

					i++;
					tempNumTests++;
				}

				// Write this portion of the compressed matrix to file.
				str = sum.toString();
				
        		char[] a = new char[str.length()];
       			str.getChars(0, str.length(), a, 0);
        		for (int j = 0; j < a.length; j++)
				{
            		fileOut.write(a[j]);
       			}

				fileOut.write('\n');

            }

			fileOut.close();
        }
		catch (FileNotFoundException e)
		{
        	e.printStackTrace();
       	}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
