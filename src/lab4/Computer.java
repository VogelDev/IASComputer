package lab4;

public class Computer {

    private long ac, mq, mbr, ibr;
    private int pc, mar, ir;
    private boolean left, ibrLoad;
    private Memory memory;
    private String description;

    public Computer(Memory memory) {
        this.memory = memory;

        ac = mq = mbr = ibr = ir = pc = mar = 0;

        left = true;
        ibrLoad = false;
        description = "";

    }

    public void read(String hex) {
        memory.setMemory(pc++, Long.valueOf(hex, 16));
    }

    public void run() {
        pc = 0;
        left = true;
        if (ibrLoad) {
            // ir = ibr op
            ir = (int) (ibr >>> 12);
            // mar = ibr address
            mar = (int) (ibr & Integer.valueOf("fff", 16));
            // pc++
        } else {
            mar = pc;
            mbr = memory.getMemory(mar);
            if (left) {
                // ibr = mbr right
                ibr = mbr & Integer.valueOf("fffff", 16);
                ibrLoad = true;

                int leftIn = (int) (mbr >>> 20);
                // ir = mbr left op
                ir = leftIn >>> 12;
                // mar = mbr left address
                mar = (int) (leftIn & Integer.valueOf("fff", 16));
            } else {
                int rightIn = (int) (mbr & Integer.valueOf("fffff", 16));
                // ir = mbr right op
                ir = rightIn >>> 12;
                // mar = mbr right address
                mar = (int) (rightIn & Integer.valueOf("fff", 16));
                // pc++
                pc++;
            }
        }

        compute(ir, mar);
    }

    public void compute(int opcode, int address) {
        boolean setDesc = true;

        switch (opcode) {
        case Opcode.HALT:

            break;
        case Opcode.LOADMX:
            // load mx to ac
            break;
        case Opcode.LOADMXNEG:
            // load -mx to ac
            break;
        case Opcode.LOADMXABS:
            // load |mx| to ac
            break;
        case Opcode.LOADMXABSNEG:
            // load -|mx| to ac
            break;
        case Opcode.ADDMX:
            // add mx to ac
            break;
        case Opcode.SUBMX:
            // sub mx from ac
            break;
        case Opcode.ADDMXABS:
            // add |mx| to ac
            break;
        case Opcode.SUBMXABS:
            // sub |mx| from ac
            break;
        case Opcode.LOADMQMX:
            // xfer mx to mq
            break;
        case Opcode.LOADMQ:
            // xfer mq to ac
            break;
        case Opcode.MULMX:
            // multiply ac by mx, most sig in ac, least sig in mq
            break;
        case Opcode.DIVMX:
            // divide ac by mx, quotient to mq, remainder in ac
            break;
        case Opcode.JUMPMXLEFT:
            // left instruction from mx
            break;
        case Opcode.JUMPMXRIGHT:
            // right instruction from mx
            break;
        case Opcode.JUMPMXPOSLEFT:
            // ac >=0 left instruction
            break;
        case Opcode.JUMPMXPOSRIGHT:
            // ac >=0 right instruction
            break;
        case Opcode.STORMX:
            // xfer ac to mx
            break;
        case Opcode.STORMXLEFT:
            // set mx left to ac right
            break;
        case Opcode.STORMXRIGHT:
            // set mx right to ac right
            break;
        case Opcode.LSH:
            // multiply ac by 2
            break;
        case Opcode.RSH:
            // divide ac by 2 (will be floored)
            break;
        default:
            setDesc = false;
        }

        if (setDesc)
            description = Opcode.DESCRIPTION[opcode];
        else
            description = "ERROR, INSTRUCTION NOT FOUND";
    }
}
