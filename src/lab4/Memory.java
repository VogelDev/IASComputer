package lab4;

public class Memory {
    long[] memory;
    
    public Memory(int wordCount){
        memory = new long[wordCount];
    }
    
    public long getWord(int location){
        return memory[location];
    }
    
    public void setWord(int location, long address){
        memory[location] = address;
    }
    
    public long[] getMemory(){
        return memory;
    }
}
