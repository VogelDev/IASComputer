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
        long rightIn = memory[location] & Integer.valueOf("fffff", 16);
        long leftIn = memory[location] >>> 20;
        long leftOp = leftIn >>> 12;

        long newAdd = value << 20;
        newAdd >>>= 20;
        newAdd &= Integer.valueOf("fff", 16);

        long newValue = leftOp << 32;
        newValue |= newAdd << 20;
        newValue |= rightIn;
        
        memory[location] = newValue;
        
    }

    public void setRight(int location, long value) {
        long rightIn = memory[location] & Integer.valueOf("fffff", 16);
        long rightOp = rightIn >>12;
        long leftIn = memory[location] >>> 20;

        long newValue = leftIn << 20;
        newValue |= rightOp << 12;
        newValue |= (value & Integer.valueOf("fff", 16));
        
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
