package lab4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * IAS Computer for CIS126
 * 
 * @author Rob Vogel
 * @version 0.0.3
 */
public class Computer {

    private long ac, mq, mbr, ibr;
    private int pc, mar, ir, counter;
    private boolean left, ibrLoad, run;
    private Memory memory;
    private String description;
    private String output;

    /**
     * Constructor for Computer, takes in a memory set.
     * 
     * @param memory
     */
    public Computer(Memory memory) {
        this.memory = memory;

        ac = mq = mbr = ibr = ir = pc = mar = 0;

        left = true;
        ibrLoad = false;
        run = true;
        description = "";
        output = "";
    }

    /**
     * Runs the computer until the opcode 0 is found
     */
    public void run() {
        while (run) {
            counter++;
            fetch();
            System.out.print(Long.toHexString(pc) + ": ");
            execute(ir, mar);
        }

        write();
    }

    private void write() {
        // FileWriter will find the file and add to it if it already exists
        FileWriter fwriter = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int sec = calendar.get(Calendar.SECOND);
        String date = year+"-"+month+"-"+day+"-"+hour+"-"+sec+".txt";
        String fileName = System.getProperty("user.name") + "-" + date;
        try {
            fwriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // PrintWriter would overwrite the file if had already existed without
        // the FileWriter
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println(output);
        outputFile.close();
    }

    /**
     * Fetch cycle for IASComputer
     */
    public void fetch() {
        if (ibrLoad) {
            // ir = ibr op
            ir = (int) (ibr >>> 12);
            // mar = ibr address
            mar = (int) (ibr & Integer.valueOf("fff", 16));

            System.out.println("fetch [" + pc + "R].  IR: " + ir + ", MAR: " + mar + ", PC: " + pc);
            // pc++
            pc++;
            ibrLoad = false;
            left = true;
        } else {
            mar = pc;
            mbr = memory.getMemory(mar);
            if (left) {
                // ibr = mbr right
                ibr = mbr & Integer.valueOf("fffff", 16);
                ibrLoad = true;
                left = false;
                System.out.println("fetch [" + pc + "L].  IR: " + ir + ", MAR: " + mar + ", PC: " + pc);
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
                left = true;
                ibrLoad = false;
                System.out.println("fetch [" + pc + "R].  IR: " + ir + ", MAR: " + mar + ", PC: " + pc);
                pc++;
            }
        }
    }

    /**
     * Execution cycle for IASComputer
     * 
     * @param opcode
     * @param address
     */
    public void execute(int opcode, int address) {
        boolean setDesc = true;

        switch (opcode) {
        case Opcode.HALT:
            run = false;
            break;
        case Opcode.LOADMX:
            // load mx to ac
            ac = memory.getMemory(address);
            break;
        case Opcode.LOADMXNEG:
            // load -mx to ac
            ac = -memory.getMemory(address);
            break;
        case Opcode.LOADMXABS:
            // load |mx| to ac
            ac = Math.abs(memory.getMemory(address));
            break;
        case Opcode.LOADMXABSNEG:
            // load -|mx| to ac
            ac = -Math.abs(memory.getMemory(address));
            break;
        case Opcode.ADDMX:
            // add mx to ac
            ac += memory.getMemory(address);
            break;
        case Opcode.SUBMX:
            // sub mx from ac
            ac -= memory.getMemory(address);
            break;
        case Opcode.ADDMXABS:
            // add |mx| to ac
            ac += Math.abs(memory.getMemory(address));
            break;
        case Opcode.SUBMXABS:
            // sub |mx| from ac
            ac -= Math.abs(memory.getMemory(address));
            break;
        case Opcode.LOADMQMX:
            // xfer mx to mq
            mq = memory.getMemory(address);
            break;
        case Opcode.LOADMQ:
            // xfer mq to ac
            ac = mq;
            break;
        case Opcode.MULMX:
            // multiply ac by mx, most sig in ac, least sig in mq
            break;
        case Opcode.DIVMX:
            // divide ac by mx, quotient to mq, remainder in ac
            mq = ac / memory.getMemory(address);
            ac %= memory.getMemory(address);
            break;
        case Opcode.JUMPMXLEFT:
            // left instruction from mx
            left = true;
            ibrLoad = false;
            pc = mar;
            break;
        case Opcode.JUMPMXRIGHT:
            // right instruction from mx
            left = false;
            ibrLoad = false;
            pc = mar;
            break;
        case Opcode.JUMPMXPOSLEFT:
            // ac >0 left instruction
            if (ac > 0) {
                left = true;
                ibrLoad = false;
                pc = mar;
            }
            break;
        case Opcode.JUMPMXPOSRIGHT:
            // ac >0 right instruction
            if (ac > 0) {
                left = false;
                ibrLoad = false;
                pc = mar;
            }
            break;
        case Opcode.STORMX:
            // xfer ac to mx
            memory.setMemory(address, ac);
            break;
        case Opcode.STORMXLEFT:
            // set mx left to ac right
            memory.setLeft(address, ac);
            break;
        case Opcode.STORMXRIGHT:
            // set mx right to ac right
            memory.setRight(address, ac);
            ibrLoad = false;
            break;
        case Opcode.LSH:
            // multiply ac by 2
            ac <<= 1;
            break;
        case Opcode.RSH:
            // divide ac by 2 (will be floored)
            ac >>= 1;
            break;
        case Opcode.STOR:
            memory.setMemory(address, ac);
            break;
        default:
            setDesc = false;
        }

        if (setDesc) {

            if (opcode < Opcode.DESCRIPTION.length)
                description = Opcode.DESCRIPTION[opcode];
            else
                description = Opcode.DESCRIPTION[17]; // special case for 33

            description = description.replace(
                    "M(X)",
                    "M(" + Long.toHexString(address) + ")[value of: "
                            + Long.toHexString(memory.getMemory(mar)) + "]");
            description = description.replace("AC",
                    "AC(" + Long.toHexString(ac) + ")");

        } else
            description = "ERROR, INSTRUCTION NOT FOUND";

        if (!description.equals(Opcode.DESCRIPTION[Opcode.HALT])) {
            System.out.println(description);
            output += counter + ": " + description + "\n";
        }
    }

    @Override
    public String toString() {
        return "Computer [ac=" + Long.toHexString(ac) + ", mq="
                + Long.toHexString(mq) + ", mbr=" + Long.toHexString(mbr)
                + ", ibr=" + Long.toHexString(ibr) + ", pc=" + pc + ", mar="
                + mar + ", ir=" + Long.toHexString(ac) + ", left=" + left
                + ", ibrLoad=" + ibrLoad + ", run=" + run + ", memory="
                + memory + ", description=" + description + "]";
    }
}
