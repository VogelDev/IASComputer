package lab4;

import java.util.Arrays;

public class Memory {
    long[] memory;
    
    public Memory(int wordCount){
        memory = new long[wordCount];
    }
    
    public long getMemory(int location){
        return memory[location];
    }
    
    public void setMemory(int location, long value){
        memory[location] = value;
    }
    
    public long[] getMemory(){
        return memory;
    }

    @Override
    public String toString() {
        String output = "";
        
        for(long item: memory){
            output += Long.toHexString(item) + "\n";
        }
        
        return output;
    }
}
