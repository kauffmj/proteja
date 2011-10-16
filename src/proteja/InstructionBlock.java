/**
 * Written by Erik Ostrofsky as part of the proteja system.
 *
 * Modified by Jonathan Miller Kauffman - now called Proteja.
 */

package proteja;

import java.util.ArrayList;

public class InstructionBlock
{
    private ArrayList<Instruction> instructions;

    public InstructionBlock()
    {
        instructions = null;
    }

    public ArrayList<Instruction> getInstructions()
    {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions)
    {
        this.instructions = instructions;
    }
}
