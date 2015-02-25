package lab4;

public class Memory {
    long[] memory;

    public Memory(int wordCount) {
        memory = new long[wordCount];
    }

    public long getMemory(int location) {
        return memory[location];
    }

    public void setMemory(int location, long value) {
        memory[location] = value;
    }

    public void setLeft(int location, long value) {
        value <<= 20;
        long newValue = memory[location] & Long.valueOf("ff000fffff", 16);
        newValue |= value & Long.valueOf("00fff00000", 16);
        memory[location] = newValue;
    }

    public void setRight(int location, long value) {
        long newValue = memory[location] & Long.valueOf("fffffff000", 16);
        newValue |= value & Long.valueOf("fff", 16);
        memory[location] = newValue;
    }

    public long[] getMemory() {
        return memory;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("IAS Memory" + "\n"
                + "===========");
        for (int i = 0; i < memory.length; i++) {
            output.append("\n");
            String value = (Long.toString(memory[i], 16));
            value = "0000000000".substring(0, 10 - value.length())
                    + value.toUpperCase();
            output.append(value.substring(0, 5) + " " + value.substring(5));
        }

        return output.toString();
    }
}
