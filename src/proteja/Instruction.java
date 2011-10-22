/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package proteja;

import java.lang.reflect.Method;

public class Instruction
{
    private Class clazz;
    private Method method;

    public Class getClazz()
    {
        return clazz;
    }

    public void setClazz(Class clazz)
    {
        this.clazz = clazz;
    }

    public Method getMethod()
    {
        return method;
    }

    public void setMethod(Method method)
    {
        this.method = method;
    }
}

