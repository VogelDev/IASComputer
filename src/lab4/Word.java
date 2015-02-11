package lab4;

public class Word {
    public static final int LEFT = 0;
    public static final int RIGHT = 0;
    Instruction left;
    Instruction right;
    int value;
    String hex;
    
    public Word(){
        left = new Instruction();
        right = new Instruction();
        value = 0;
        hex = new String();
    }
    
    public Instruction getInstruction(int dir){
        if(dir == 0){
            return left;
        }else{
            return right;
        }
    }
    
    public void setLeft(int opcode, int address){
        left.setOpcode(opcode);
        left.setAddress(address);
        setValue();
    }

    public void setRight(int opcode, int address){
        right.setOpcode(opcode);
        right.setAddress(address);
        setValue();
    }
    
    private void setValue() {
        
        hex = left.getAddress().getHex() + right.getAddress().getHex();
    }
    
    public void setValue(int value){
        
        int left = 0;
        int right = 0;
        
        
    }
}
