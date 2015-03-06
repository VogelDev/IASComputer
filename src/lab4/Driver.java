package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {

        String[] input = args;
        try {
            //input = readLines(input[0]);
            input = readLines("src/mycode");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Memory memory = new Memory(100);

        for (int i = 0; i < input.length; i++) {
            memory.setMemory(i, Long.parseLong(input[i], 16));
        }
        System.out.println(memory);

        Computer computer = new Computer(memory);
        computer.run();
        System.out.println(memory);
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
}