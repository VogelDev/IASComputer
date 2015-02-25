package lab4;
import java.io.IOException;  
      
    public class JavaReadTextFile   
    {  
        public static void main(String[] args)   
        {  
            TextToString rf = new TextToString();  
              
            // The text file location  
            //String filename = "e:/TextString/Hex.txt";  
              
            try  
            {  
                String[] lines = rf.readLines(args[0]);  
              
                for (String line : lines)   
                {  
                    System.out.println(line);  
                }  
            }  
            catch(IOException e)  
            {  
                // Print out the exception that occurred  
                System.out.println("Unable to create "+args[0]+": "+e.getMessage());                
            }  
        }  
    }  