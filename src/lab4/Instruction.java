package lab4;

public class Instruction{
    Opcode opcode;
    Address address;
    
    public Instruction() {
        opcode = new Opcode();
        address = new Address();
    }
    
    public Opcode getOpcode(){
        return opcode;
    }
    
    public Address getAddress(){
        return address;
    }

    public void setOpcode(int value) {
        opcode.setVal(value);
    }

    public void setAddress(int value) {
        address.setVal(value);
    }
}