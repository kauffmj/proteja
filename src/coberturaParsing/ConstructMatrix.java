/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package coberturaParsing;

import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.lang.StringBuilder;

public class ConstructMatrix
{
    public static void main(String[] args)
    {
        int[][] coverageMatrix;
        String settingsFile = "settings.out";
        String arrayFile = "coverageReports/";
        String matrixFileName = "coverageReports/" + args[4] + "_" +
            args[1] + "_" + args[2] + "_" + args[3] + "_Coverage.dat";
        int batchFactor = 1;
        proteja.Settings set;

        /**
         * Read in settings file and update values, read in first
         * array so information about the size can be used.
         */
        int numberOfTests = 0;
        int[] current = new int[1];
        try
        {
            set = setRetrieveFromXML(settingsFile);
            numberOfTests = Integer.parseInt(args[0]);
            //matrixFileName += (set.getCoverageType() + 
            //"-coverage.dat");
            batchFactor = set.getBatchFactor();
            current = arrayRetrieveFromXML(arrayFile + "1.out");
        } catch (IOException e)
        {
            System.out.println("There was a problem reading the " +
                "arrays");
            System.out.println("Upper");
            System.exit(1);
        }

        /**
         * if batchFactor is 1 then different naming conventions are
         * used then if batchFacto is larger than 1. This section
         * reads in the arrays and fills the matrix column at a time.
         * THIS SECTION IS OUTDATED
         */
        /**
        if (batchFactor == 1) {
         */
            coverageMatrix =
                new int[current.length + 1][numberOfTests + 1];
            for (int i = 1; i <= numberOfTests; i++) {
                try {
                    current = arrayRetrieveFromXML(
                        arrayFile + i + ".out");
                } catch (IOException e) {
                    System.out.println("There was a problem " +
                        "reading the arrays" + i);
                    System.exit(1);
                }
                for (int j = 0; j < current.length; j++) {
                    coverageMatrix[j][i - 1] = current[j];
                }
            }
            /**
        } else {
            int numberOfFiles;
            if (numberOfTests % batchFactor == 0) {
                numberOfFiles = numberOfTests / batchFactor;
            } else {
                numberOfFiles = numberOfTests / batchFactor + 1;
            }
            coverageMatrix =
                new int[current.length + 1][numberOfFiles + 1];
            int currentColumn = 0;
            for (int i = 1; i <= numberOfTests; i += batchFactor) {
                try {
                    current = arrayRetrieveFromXML(arrayFile + i +
                        "-" + (i + batchFactor - 1) + ".out");
                } catch (IOException e) {
                    System.out.println("There was a problem " +
                        "reading the arrays");
                    System.exit(1);
                }
                for (int j = 0; j < current.length; j++) {
                    coverageMatrix[j][currentColumn] = current[j];
                    System.out.println("updating row: " + j +
                        "column: " + currentColumn);
                }
                currentColumn++;
            }
        }
*/
        //row totals
        int total = 0;
        int j;
        for (int i = 0; i < coverageMatrix.length - 1; i++)
        {
            for (j = 0; j < coverageMatrix[i].length - 1; j++)
            {
                total = total + coverageMatrix[i][j];
            }
            coverageMatrix[i][j] = total;
            total = 0;
        }


        //column totals
        total = 0;
        int k;
        for (int i = 0; i < coverageMatrix[0].length - 1; i++)
        {
            for (k = 0; k < coverageMatrix.length - 1; k++)
            {
                total = total + coverageMatrix[k][i];
            }
            coverageMatrix[k][i] = total;
            total = 0;
        }

        try
        {
            writeMatrix(matrixToString(coverageMatrix),
                matrixFileName);
        } catch (IOException e)
        {
            System.out.println("There was a problem writing the " +
                    "coverage to disk!");
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Writes a coverage matrix to disk.
     */
    public static void writeMatrix(String str, String file)
        throws IOException
    {
        FileWriter fileOut = new FileWriter(file);
        char[] a = new char[str.length()];
        str.getChars(0, str.length(), a, 0);
        for (int i = 0; i < a.length; i++)
        {
            fileOut.write(a[i]);
        }
        fileOut.close();
    }

    /**
     * Reads the settings.out file from the disk.
     */
    public static proteja.Settings setRetrieveFromXML(String name) 
        throws IOException
    {
        XStream xstream = new XStream();
        FileReader fileIn = new FileReader(name);
        BufferedReader read = new BufferedReader(fileIn);
        String complete = "";
        String temp;
        while ((temp = read.readLine()) != null)
        {
            complete = complete + temp;
        }
        proteja.Settings config =
            (proteja.Settings) xstream.fromXML(complete);
        return config;
    }

    /**
     * Reads the array files from disk.
     */
    public static int[] arrayRetrieveFromXML(String name)
        throws IOException
    {
        XStream xstream = new XStream();
        FileReader fileIn = new FileReader(name);
        BufferedReader read = new BufferedReader(fileIn);
        String complete = "";
        String temp;
        while ((temp = read.readLine()) != null)
        {
            complete = complete + temp;
        }
        int[] array = (int[]) xstream.fromXML(complete);
        return array;
    }

    /**
     * Creates a string representation of the coverage matrix.
     */
    public static String matrixToString(int[][] matrix)
    {
        StringBuilder sb = new StringBuilder(
            matrix.length*matrix[0].length*3);
        for (int i = 0; i < matrix.length; i++)
        {
            sb.append(matrix[i][0]);
            for (int j = 1; j < matrix[i].length; j++)
            {
                sb.append(" ");
                sb.append(matrix[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
