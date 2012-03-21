/******************************************************************************
 *
 * DecompressMatrix.java
 *
 * Reads in a compressed coverage or fault matrix with or without summary rows
 * or columns and saves the decompressed matrix to a new file.
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

public class DecompressMatrix
{
	public static void main(String[] args)
	{
		if(args.length != 2)
		{
			System.out.println("Usage: java DecompressMatrix [matrix name] " +
				"[summary row and column?]");
			System.exit(1);
		}

		// The names of the matrix to be read and the decompressed matrix to
		// be written.
		File matrix = new File(args[0]);
		File decompressedMatrix = new File(args[0] + "_decompressed");
		boolean summaryInfo = Boolean.parseBoolean(args[1]);

		// Read in each row.
		Scanner scanRows = null;

		// The BigInteger value of the current bit.
		BigInteger temp = null;

		// Holds the current sum and its corresponding string representation.
		BigInteger sum = new BigInteger("0");
		String str = "";

		// The number of tests, indicated by the first integer.
		int numTests = 0;

		// The sum of one row.
		int rowSum = 0;

		// A BigInteger whose value is always one.
		BigInteger one = new BigInteger("1");

		// A BigInteger whose value is always two.
		BigInteger two = new BigInteger("2");

		try
		{
			FileWriter fileOut = new FileWriter(decompressedMatrix);
            scanRows = new Scanner(matrix);

			// Read the number of tests.
			numTests = Integer.parseInt(scanRows.next());

			// An array where each position represents the sum of one column.
			int[] columnSum = new int[numTests];

            while (scanRows.hasNext())
			{
				// The decimal value of the compressed bitstring.
				sum = new BigInteger(scanRows.next());
				
				rowSum = 0;

				// Decide whether each test/requirement match is covered
				// or not covered.
				for(int j=numTests-1;j>=0;j--)
				{
					//str = "" + (int)Math.pow(2,j);
					temp = two.pow(j);
					//temp = new BigInteger(str);

					// The result of the division is greater than or equal to 1.
					if(sum.divide(temp).compareTo(one) >= 0)
					{
						fileOut.write('1');
						rowSum++;
						columnSum[numTests - j - 1]++;
						if(j > 0)
							fileOut.write(' ');
						else
						{
							if(summaryInfo)
								fileOut.write(" " + rowSum);
							fileOut.write('\n');
						}

						sum = sum.subtract(new BigInteger(temp.toString()));
					}
					else
					{
						fileOut.write('0');
						if(j > 0)
							fileOut.write(' ');
						else	
						{
							if(summaryInfo)
								fileOut.write(" " + rowSum);					
							fileOut.write('\n');
						}
					}
				}

            }

			if(summaryInfo)
			{
				for(int i=0;i<columnSum.length;i++)
					fileOut.write(columnSum[i] + " ");
				fileOut.write('0');
			}

			fileOut.write('\n');
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
