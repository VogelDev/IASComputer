package lab4;

public class Driver {

    public static void main(String[] args) {

	Memory memory = new Memory(100);

	for(int i =0;i<args.length;i++){
	    memory.setMemory(i, Long.parseLong(args[i], 16));
	}
	
	Computer computer = new Computer(memory);
	computer.run();
	System.out.println(memory);
    }
}
