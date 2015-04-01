package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * "Team Awesome"
 * Jon Kilgannon, Karen Kampia, Dave Murray
 * IAS Simulated Machine 
 * 
 * Version: 2/20/15
 * 
 */

public class IASMachine {

	private static long[] memory = new long[100];
	private static boolean LeftInstructionNeeded = true;
	private static boolean IBRhasInstruction = false;
	private static boolean done = false;
	private static long MBR;	// 40 bits
	private static int MAR;		// 12 bits
	private static int IBR;		// 20 bits
	private static int PC;     	// 12 bits
	private static int IR;		//  8 bits
	private static long AC;		// 40 bits   (use long)
	private static long MQ;		// 40 bits   (use long)
	
	public static void main(String[] args) {
		loadProgram();
		
		System.out.println("Before the run...");
		outputProgram();	// Show what's in memory before the program runs.
		
		while ( !done )
		{
			fetch();
			decode();
		}
		
		System.out.println();
		System.out.println("After the run...");
		outputProgram();	// Show what's in memory after the program runs.

	}
	
	private static void fetch() {
		if ( IBRhasInstruction )
		{
			IR = IBR(0,7);
			MAR = IBR(8,19);
			IBRhasInstruction = false;
			System.out.println("fetch [" + PC + "R].  IR: " + IR + ", MAR: " + MAR + ", PC: " + PC);
			PC = PC + 1;
		}
		else
		{
			MAR = PC;
			MBR = memory[MAR];
			if ( LeftInstructionNeeded )
			{
				IBRhasInstruction = true;
				IBR = MBR(20,39);
				IR = MBR(0,7);
				MAR = MBR(8,19);
						System.out.println("fetch [" + PC + "L].  IR: " + IR + ", MAR: " + MAR + ", PC: " + PC);				
			}
			else
			{
				IR = MBR(20,27);
				MAR = MBR(28,39);
						System.out.println("fetch [" + PC + "R].  IR: " + IR + ", MAR: " + MAR + ", PC: " + PC);				
				PC = PC + 1;
			}
		}

	}

	
	private static void decode() {
		
		switch ( IR )
		{
			case 0:
				// halt
				done = true; 
				break;
			case 1:
				// LOAD M(X)
				// Transfer M(X) to the accumulator
				AC = memory[MAR];
				break;
			case 2:
				// LOAD -M(X)
				// Transfer -M(X) to the accumulator				
				AC = -memory[MAR];
				break;
			case 3:
				// LOAD |M(X)|
				// Transfer absolute value of M(X) to the accumulator
				AC = Math.abs(memory[MAR]);
				break;
			case 4:
				// LOAD -|M(X)|
				// Transfer the negative of the absolute value of M(X) to the accumulator
				AC = -Math.abs(memory[MAR]);
				break;
			case 5:
				// ADD M(X)
				// Add M(X) to AC; put the result in AC
				AC = AC + memory[MAR];
				break; 
			case 6:
				// SUB M(X)
				// Subtract M(X) from AC; put the result in AC
				AC = AC - memory[MAR];
				break; 
			case 7:
				// ADD |M(X)|
				// Add the absolute value of M(X) to AC; put the result in AC
				AC = AC + Math.abs(memory[MAR]);
				break; 
			case 8:
				// SUB |M(X)|
				// Subtract |M(X)| from AC; put the result in AC
				AC = AC - Math.abs(memory[MAR]);
				break; 
			case 9:
				// LOAD MQ,M(X)
				// Transfer the contents of memory location X into MQ
				MQ = memory[MAR];
				break;
			case 10:
				// Load MQ
				// Transfer contents of register MQ into the accumulator AC
				AC = MQ;
				break;
			case 11:
				// MUL M(X)
				// Multiply M(X) by MQ; put most significant bits of result in AC; 
				// put least significant bits in MQ
				
/* LEFT FOR TEAM MEMBERS TO FINISH */
				
				break;
			case 12:
				// DIV M(X)
				// Divide AC by M(X); put quotient in MQ and the remainder in AC.
				
/* LEFT FOR TEAM MEMBERS TO FINISH */
				
				break;
			case 13:
				// JUMP M(X,0:19) [unconditional branch]
				// Take next instruction from left half of M(X)
				PC = MAR;
				IBRhasInstruction = false;
				LeftInstructionNeeded = true;
				break;
			case 14:
				// JUMP M(X,20:39) [unconditional branch]
				// Take next instruction from right half of M(X)
				PC = MAR;
				IBRhasInstruction = false;		
				LeftInstructionNeeded = false;
				break;
			case 15:
				// JUMP+ M(X,0:19) [conditional branch]
				// If the number in the accumulator is nonnegative, 
				// take next instruction from left half of M(X)
				if (AC > 0)
				{
					PC = MAR;
					IBRhasInstruction = false;
					LeftInstructionNeeded = true;
				}				
				break;
			case 16:
				// JUMP+ M(X,20:39) [conditional branch]
				// If the number in the accumulator is nonnegative, 
				// take next instruction from right half of M(X)		
				if (AC > 0)
				{
					PC = MAR;
					IBRhasInstruction = false;
					LeftInstructionNeeded = false;
				}
				break;
			case 18:
				// STOR M(X,8:19) [address modify]
				// Replace left address field at M(X) by 12 rightmost
				// bits of AC
				addressModify(MAR, false);
				break;
			case 19:
				// STOR M(X,28:39) [address modify]
				// Replace right address field at M(X) by 12 rightmost
				// bits of AC				
				addressModify(MAR, true);
				IBRhasInstruction = false;
				break;
			case 20:
				// LSH
				// Multiply accumulator by 2; i.e., shift left one position.
				AC = AC * 2;
				break;
			case 21:
				// RSH
				// Divide accumulator by 2; i.e., shift right one position.
				AC = AC / 2;
				break;
			case 33:
				// STOR M(X)
				// Transfer contents of accumulator to memory location X
				memory[MAR] = AC;
				break;
		}
		
		whatIsDecodeDoing(IR);	// Print to console what happened. Remove this for a quieter simulation.
	}
	
	private static void whatIsDecodeDoing(int IR)
	{
		switch ( IR )
		{
			case 0:
				// halt
				System.out.println("Halt");
				break;
			case 1:
				// LOAD M(X)
				// Transfer M(X) to the accumulator
				System.out.println("LOAD M(" + MAR + "), M(X) = " + longToString(memory[MAR]));
				break;
			case 2:
				// LOAD -M(X)
				// Transfer -M(X) to the accumulator				
				System.out.println("LOAD -M(" + MAR + "), M(X) = " + longToString(memory[MAR]));
				break;
			case 3:
				// LOAD |M(X)|
				// Transfer absolute value of M(X) to the accumulator
				System.out.println("LOAD |M(" + MAR + ")|, M(X) = " + longToString(memory[MAR]));
				break;
			case 4:
				// LOAD -|M(X)|
				// Transfer the negative of the absolute value of M(X) to the accumulator
				System.out.println("LOAD -|M(" + MAR + ")|, M(X) = " + longToString(memory[MAR]));
				break;
			case 5:
				// ADD M(X)
				// Add M(X) to AC; put the result in AC
				System.out.println("ADD M(" + MAR + "), M(X) = " + longToString(memory[MAR]));
				break; 
			case 6:
				// SUB M(X)
				// Subtract M(X) from AC; put the result in AC
				System.out.println("SUB M(" + MAR + "), M(X) = " + longToString(memory[MAR]));
				break; 
			case 7:
				// ADD |M(X)|
				// Add the absolute value of M(X) to AC; put the result in AC
				System.out.println("ADD |M(" + MAR + ")|, M(X) = " + longToString(memory[MAR]));
				break; 
			case 8:
				// SUB |M(X)|
				// Subtract |M(X)| from AC; put the result in AC
				System.out.println("SUB |M(" + MAR + ")|, M(X) = " + longToString(memory[MAR]));
				break; 
			case 9:
				// LOAD MQ,M(X)
				// Transfer the contents of memory location X into MQ
				System.out.println("LOAD M(" + MAR + ") into MQ, M(X) = " + longToString(memory[MAR]));
				break;
			case 10:
				// Load MQ
				// Transfer contents of register MQ into the accumulator AC
				System.out.println("LOAD MQ into AC, MQ = " + MQ);
				break;
			case 11:
				// MUL M(X)
				// Multiply M(X) by MQ; put most significant bits of result in AC; 
				// put least significant bits in MQ
				System.out.println(" MUL M(" + MAR + ") by MQ, MQ = " + MQ);
				break;
			case 12:
				// DIV M(X)
				// Divide AC by M(X); put quotient in MQ and the remainder in AC.
				System.out.println("DIV AC by M(" + MAR + "), M(X) = " + longToString(memory[MAR]));
				break;
			case 13:
				// JUMP M(X,0:19) [unconditional branch]
				// Take next instruction from left half of M(X)
				System.out.println("JUMP left unconditionally to M(" + MAR + ")");
				break;
			case 14:
				// JUMP M(X,20:39) [unconditional branch]
				// Take next instruction from right half of M(X)
				System.out.println("JUMP right unconditionally to M(" + MAR + ")");
				break;
			case 15:
				// JUMP+ M(X,0:19) [conditional branch]
				// If the number in the accumulator is nonnegative, 
				// take next instruction from left half of M(X)
				System.out.println("JUMP conditional left to M(" + MAR + ")");
				break;
			case 16:
				// JUMP+ M(X,20:39) [conditional branch]
				// If the number in the accumulator is nonnegative, 
				// take next instruction from right half of M(X)		
				System.out.println("JUMP conditional right to M(" + MAR + ")");
				break;
			case 18:
				// STOR M(X,8:19) [address modify]
				// Replace left address field at M(X) by 12 rightmost
				// bits of AC
				System.out.println("Address modify left M(" + MAR + "), M(X) afterwards = " + longToString(memory[MAR]));
				break;
			case 19:
				// STOR M(X,28:39) [address modify]
				// Replace right address field at M(X) by 12 rightmost
				// bits of AC				
				System.out.println("Address modify right M(" + MAR + "), M(X) afterwards = " + longToString(memory[MAR]));
				break;
			case 20:
				// LSH
				// Multiply accumulator by 2; i.e., shift left one position.
				System.out.println("Multiply AC by 2");
				break;
			case 21:
				// RSH
				// Divide accumulator by 2; i.e., shift right one position.
				System.out.println("Divide AC by 2");
				break;
			case 33:
				// STOR M(X)
				// Transfer contents of accumulator to memory location X
				System.out.println("STOR AC to M(" + MAR + "), M(X) afterwards = " + longToString(memory[MAR]));
				break;
		}		
		System.out.println("  Accumulator: " + AC);
		System.out.println("----");
	}
	
	private static void addressModify(int memoryLocation, boolean replaceRight)
	{
		// Replaces the address field of either the right or left part of the memory location
		// held in memory[memoryLocation] with the 12 least significant bits
		// of the accumulator AC.
		
		String s = Long.toHexString(memory[memoryLocation]);

		s = String.format("%10S",s).replace(" ", "0");

		String leftOpcode = s.substring(0, 2);
		String leftAddr = s.substring(2, 5);
		String rightOpcode = s.substring(5, 7);
		String rightAddr = s.substring(7, 10);
		String buildingNewMemory;
		
		String tempHex = Long.toHexString(bitCaptureAsLong(AC, 0, 11));	// 12 least significant bits of AC
		tempHex = String.format("%3S",tempHex).replace(" ", "0");
		
		if (replaceRight)
			buildingNewMemory = leftOpcode + leftAddr + rightOpcode + tempHex;
		else
			buildingNewMemory = leftOpcode + tempHex + rightOpcode + rightAddr;

		memory[memoryLocation] = Long.parseLong(buildingNewMemory, 16);
	}
	
	
	private static long bitCaptureAsLong(long number, int startPos, int endPos)
	{
		// Express number as a binary number, then get the decimal number 
		// that is represented by the bits from startPos to endPos
		// in that binary number.  Used by addressModify().
		
		boolean[] binaryNumber = new boolean[40];	// Longest possible number we will deal with
		int count = 0;								// Keep count of our position in the array
		long workingDecimal = number;				// Number we will repeatedly divide.
		long answer = 0;							// The long we will return
		
		while ( workingDecimal > 0 )
		{
			// Use remainder and repeated division by 2 to get an array of bits
			long remainder = workingDecimal % 2L;
			workingDecimal = workingDecimal / 2L;
			binaryNumber[count] = (remainder == 1);
			count++;
		}
		
		// Turn the binary number array into a long.
		for (int i = endPos; i >= startPos; i--)
		{
			if (binaryNumber[i])
			{
				answer += (long)  Math.pow(2, (i - startPos));  
			}
		}
		
		return answer;
	}
	
	private static void outputProgram() {
		// Output the memory to the console.
		
		System.out.println("SYSTEM MEMORY DUMP");
		System.out.println("------------------");
		
		for (int i = 0; i < memory.length; i++)
		{
			System.out.println(longToString(memory[i]));
		}
		
		System.out.println("-------------");
		System.out.println();
	}

	private static void loadProgram() {

		// This will loop from 4 to 1 and then stop.  Included as a test of the IAS computer simulator.
		// You can replace this with your own program and the IAS simulator will run it.
		//
		// Jon Kilgannon, 2/20/15
	    
	    String[] input = null;
        try {
            //input = readLines(input[0]);
            input = readLines("src/mycode");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        for (int i = 0; i < input.length; i++) {
            memory[i] = Long.parseLong(input[i], 16);
        }	
	}
	
	public static String[] readLines(String filename) throws IOException {
        FileReader reader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            if(line.contains("#"))
                lines.add(line.substring(0, line.indexOf("#")));
            else if(line.contains("i "))
                lines.add(line.replace("i ", ""));
            else if(line.contains("d "))
                lines.add(line.replace("d ", ""));
            else
                lines.add(line);
        }

        bufferedReader.close();
        
        return lines.toArray(new String[lines.size()]);
    }

	private static String longToString(long value)
	{
		String s = Long.toHexString(value);
		s = String.format("%10S",s).replace(" ", "0");
		String leftOpcode = s.substring(0, 2);
		String leftAddr = s.substring(2, 5);
		String rightOpcode = s.substring(5, 7);
		String rightAddr = s.substring(7, 10);	
		return String.format("%S %S %S %S",	leftOpcode,leftAddr,rightOpcode,rightAddr);
	}

	private static int MBR(int start, int end)
	{
		String s = Long.toHexString(MBR);
		s = String.format("%10S",s).replace(" ", "0");
		String leftOpcode = s.substring(0, 2);
		String leftAddr = s.substring(2, 5);
		String rightOpcode = s.substring(5, 7);
		String rightAddr = s.substring(7, 10);
		String rightInstruction = s.substring(5,10);
		switch ( start )
		{
			case 0 : return Integer.parseInt(leftOpcode,16);
			case 8 : return Integer.parseInt(leftAddr,16);
			case 20 : if (end==27) 
						return Integer.parseInt(rightOpcode,16);
					  else
						return Integer.parseInt(rightInstruction,16);
			case 28 : return Integer.parseInt(rightAddr,16);
			default : return 0;
		}
	}
	
	private static int IBR(int start, int end)
	{
		String s = Long.toHexString(IBR);
		s = String.format("%5S",s).replace(" ", "0");
		String rightOpcode = s.substring(0, 2);
		String rightAddr = s.substring(2, 5);
		switch ( start )
		{
			case 0 : return Integer.parseInt(rightOpcode,16);
			case 8 : return Integer.parseInt(rightAddr,16);
			default : return 0;
		}
	}
}
