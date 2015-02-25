package lab4;

public class Test {
    public static void main(String[] a){
        Memory memory = new Memory(5);
        
        memory.setMemory(0, Long.valueOf("0d0050d005", 16));
        System.out.println(memory);
        
        memory.setLeft(0, Long.valueOf("0d0050d00f", 16));
        System.out.println(memory);
        
        memory.setRight(0, Long.valueOf("0d0050d00f", 16));
        System.out.println(memory);
        
    }
}
