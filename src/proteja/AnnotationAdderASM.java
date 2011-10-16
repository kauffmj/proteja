/** AnnotationAdder.java
 * 
 *  Adds JUnit annotations to a java class file using the ASM bytecode manipulation framework.
 *
 *  Jonathan Miller Kauffman
**/

package proteja;

import org.objectweb.asm.*;
import java.io.*;

public class AnnotationAdderASM
{
	public static void main(String[] args)
	{
		// Check for the correct number of arguments.	
		if(args.length < 1)
		{
			System.out.println("Usage: java AnnotationAdderASM [test suite name]");
			System.exit(0);
		}

		ClassWriter cw = new ClassWriter(0); // Writes a class's bytes.
		GetMethodNames gmn = new GetMethodNames(cw); // Adapter to add annotations.
		ClassReader cr = null; // Reads in the class.

		try
        {
			cr = new ClassReader(args[1] + args[0]);

		} catch(IOException e)
		{
			System.out.println("There was a problem reading the file.");
			e.printStackTrace();
			System.exit(0);
		}

		cr.accept(gmn, 0); // Perform the transformation.
		byte[] b2 = cw.toByteArray(); // Store the result.

		try
        {
			File file = new File(args[1] + "/" + args[0].replaceAll("\\.","/") + ".class");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(b2); // Write the class to file.

		} catch(IOException e)
		{
			System.out.println("There was a problem writing to file.");
		}
	}
}

