package md.uno.game;

public class Memory
{
    private static final Memory memory = new Memory();

    private Memory(){}

    public static Memory getMemory()
    {
        return memory;
    }
}
