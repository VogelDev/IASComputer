package lab4;

import java.util.Arrays;

import lab2.BitTools;
import lab2.LogicGate;

public class Computer {

    private Word mq, ac;
    private Memory memory;
    private String description;

    public Computer() {
        memory = new Memory(100);
        
        mq = new Word();
        ac = new Word();
        description = "";
    }

    public void compute(int instruction, int address) {
        boolean setDesc = true;

        switch (instruction) {
        case Opcode.HALT:
            
            break;
        case Opcode.LOADMX:
            // load mx to ac
            ac = memory.getWord(address);
            break;
        case Opcode.LOADMXNEG:
            // load -mx to ac
            ac = -mx[address];
            
            ac = memory.getWord(address);
            break;
        case Opcode.LOADMXABS:
            // load |mx| to ac
            ac = mx[address] < 0 ? -mx[address] : mx[address];
            break;
        case Opcode.LOADMXABSNEG:
            // load -|mx| to ac
            ac = mx[address] < 0 ? mx[address] : -mx[address];
            break;
        case Opcode.ADDMX:
            // add mx to ac
            ac += mx[address];
            break;
        case Opcode.SUBMX:
            // sub mx from ac
            ac -= mx[address];
            break;
        case Opcode.ADDMXABS:
            // add |mx| to ac
            ac += mx[address] < 0 ? -mx[address] : mx[address];
            break;
        case Opcode.SUBMXABS:
            // sub |mx| from ac
            ac -= mx[address] < 0 ? -mx[address] : mx[address];
            break;
        case Opcode.LOADMQMX:
            // xfer mx to mq
            mq = mx[address];
            break;
        case Opcode.LOADMQ:
            // xfer mq to ac
            ac = mq;
            break;
        case Opcode.MULMX:
            // multiply ac by mx, most sig in ac, least sig in mq
            ac *= mx[address];
            decimalToBin(ac);
            ac = binToDec(left);
            mq = binToDec(right);
            break;
        case Opcode.DIVMX:
            // divide ac by mx, quotient to mq, remainder in ac
            mq = ac / mx[address];
            ac %= mx[address];            
            break;
        case Opcode.JUMPMXLEFT:
            // left instruction from mx
            break;
        case Opcode.JUMPMXRIGHT:
            // right instruction from mx
            break;
        case Opcode.JUMPMXPOSLEFT:
            // ac >=0 left instruction
            if(ac >= 0){
                
            }else{
                
            }
            break;
        case Opcode.JUMPMXPOSRIGHT:
            // ac >=0 right instruction
            if(ac >=0){
                
            }else{
                
            }
            break;
        case Opcode.STORMX:
            // xfer ac to mx
            mx[address] = (int) ac;
            break;
        case Opcode.STORMXLEFT:
            // set mx left to ac right
            storMXLeft(address);            
            break;
        case Opcode.STORMXRIGHT:
            // set mx right to ac right
            storMXRight(address);
            break;
        case Opcode.LSH:
            // multiply ac by 2
            ac *= 2;
            break;
        case Opcode.RSH:
            // divide ac by 2 (will be floored)
            ac /= 2;
            break;
        default:
            setDesc = false;
        }

        if (setDesc)
            description = Opcode.DESCRIPTION[instruction];
        else
            description = "ERROR, INSTRUCTION NOT FOUND";
    }
}
