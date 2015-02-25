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
    
    public void setLeft(int location, long value){
        long right = memory[location] & Integer.valueOf("fffff", 16);
        long left = value << 20;
        
        memory[location] = left | right;
    }
    
    public void setRight(int location, long value){
        long left = memory[location] >>> 20;
        
        left <<= 20;
        
        memory[location] = left | value;
    }
    
    public long[] getMemory(){
        return memory;
    }
    
    public String toString(){
	StringBuilder output = new StringBuilder("IAS Memory" + "\n" + "===========");
	for(int i = 0;i<memory.length;i++){
	    output.append("\n");
	    String value = (Long.toString(memory[i], 16));
	    value = "0000000000".substring(0,10-value.length()) + value.toUpperCase();
	    output.append(value.substring(0, 5) + " " + value.substring(5));
	}
	
	return output.toString();
    }
}
