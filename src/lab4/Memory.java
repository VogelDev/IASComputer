package lab4;

public class Memory {
    Word[] memory;
    
    public Memory(int wordCount){
        memory = new Word[wordCount];
    }
    
    public Word getWord(int address){
        return memory[address];
    }
    
    public void setWord(int location, Word word){
        memory[location] = word;
    }
}
