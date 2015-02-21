package lab4;

/**
 * 
 * @author Rob Vogel
 *@version 0.0.2
 */
public class Opcode {
    public static final int HALT = 0, LOADMX = 1, LOADMXNEG = 2, LOADMXABS = 3,
            LOADMXABSNEG = 4, ADDMX = 5, SUBMX = 6, ADDMXABS = 7, SUBMXABS = 8,
            LOADMQMX = 9, LOADMQ = 10, MULMX = 11, DIVMX = 12, JUMPMXLEFT = 13,
            JUMPMXRIGHT = 14, JUMPMXPOSLEFT = 15, JUMPMXPOSRIGHT = 16,
	    STORMX = 17, STORMXLEFT = 18, STORMXRIGHT = 19, LSH = 20, RSH = 21, STOR = 33;
    public static final String[] DESCRIPTION = {
        "HALT",
        "Transfer M(X) to the AC",
        "Transfer -M(X) to the AC",
        "Transfer |M(X)| to the AC",
        "Transfer -|M(X)| to the AC",
        "Add M(X) to the AC, put the result in the AC",
        "Subtract M(X) from the AC, put the result in the AC",
        "Add |M(X)| to the AC, put result in the AC",
        "Subtract |M(X)| from the AC, put result in the AC",
        "Transfer contents of M(X) to MQ",
        "Transfer contents of register MQ to the AC",
        "Multiply M(X) by MQ, put most significant bits of result in AC, put least significant bits in MQ",
        "Divide AC by M(X), put quotient in MQ and the remainder in AC",
        "Take next instruction from left half of M(X)",
        "Take next instruction from right half of M(X)",
        "If the number in AC is nonnegative, take next instruction from left half of M(X)",
        "If the number in AC is nonnegative, take next instruction from right half of M(X)",
        "Transfer contents of AC to M(X)",
        "Replace left address field at M(X) by 12 rightmost bits of AC",
        "Replace right address field at M(X) by 12 rightmost bits of AC",
        "Multiply AC by 2",
        "Divide AC by 2"
    };
    String binary;
    int decimal;
    String description;
    
    public Opcode(){
        decimal = 0;
        binary = new String();
        description = DESCRIPTION[Opcode.HALT];
    }
    
    public int getVal(){
        return decimal;
    }
    
    public String getDesc(){
        return description;
    }

    public void setVal(int decimal) {
        this.decimal = decimal;
        binary = Integer.toBinaryString(decimal);
        description = Opcode.DESCRIPTION[decimal];
    }
}
