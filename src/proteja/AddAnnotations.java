/** AddAnnotations.java
 * 
 *  Adds the correct annotation to all methods that might need one.
 *
 *  Jonathan Miller Kauffman
**/

package proteja;

import org.objectweb.asm.*;
import java.io.*;

public class AddAnnotations extends MethodAdapter
{
	private String mName; // the name of the current method
	private boolean annotationPresent = false; // no annotation found as of yet

	public AddAnnotations(MethodVisitor mv, String mName)
	{
		super(mv);
		this.mName = mName;
	}

	// This method is only visited if there is an annotation.  
    // Therefore we can set our annotation flag to true.
	public AnnotationVisitor visitAnnotation(String desc, boolean visible)
	{
		annotationPresent = true;
		AnnotationVisitor av = mv.visitAnnotation(desc,visible);
		return av; // pass the annotation along
	}

	// This method is visited whether there is an annotation or not.
	// If there is none, add the appropriate annotation.
	public void visitCode()
	{
		if(!annotationPresent)
			mv.visitAnnotation(determineAnnotation(),true);
	}

	// Determines the correct annotation via the method name.  We
	// only worry about standard JUnit names, since a user who
	// deviated from this norm would need to add the correct
	// annotation.
	public String determineAnnotation()
	{
		if(mName.equals("setUpClass"))
			return "Lorg/junit/BeforeClass;";
		else if(mName.equals("tearDownClass"))
			return "Lorg/junit/AfterClass;";
		else if(mName.equals("setUp"))
			return "Lorg/junit/Before;";
		else if(mName.equals("tearDown"))
			return "Lorg/junit/After;";
		else
		    return "Lorg/junit/Test;";
	}
}
