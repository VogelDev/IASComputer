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
