package lab4;

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
}
