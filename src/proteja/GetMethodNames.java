/** GetMethodNames.java
 * 
 *  Determines which methods need annotated and passes them along to
 *  the MethodAdapter 'AddAnnotations'.
 *
 *  Jonathan Miller Kauffman
**/

package proteja;

import org.objectweb.asm.*;
import java.io.*;

public class GetMethodNames extends ClassAdapter
{
    public GetMethodNames(ClassVisitor cv)
    {
        super(cv);
    }

    // If the method does not need to be annotated, just return it.
    // Otherwise, visit it.
    public MethodVisitor visitMethod(int access, String name,
        String desc, String signature, String[] exceptions)
    {
         MethodVisitor mv = cv.visitMethod(access, name, desc,
            signature, exceptions);
         if(!methodNeedsAnnotated(name))
         {
                 return mv;
         }
         mv = new AddAnnotations(mv,name);
         return mv;
      }

    // If the method is either a constructor, main method, or the
    // method to run a JUnit test suite, it does not need to be
    // annotated.
    public boolean methodNeedsAnnotated(String name)
    {
           if(name.equals("<init>") || name.equals("main") ||
                name.equals("suite"))
            return false;
        else
            return true;
    }
}
