package lab4;
import java.io.IOException;  
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.ArrayList;  
import java.util.List;  
  
public class TextToString   
{  
    public String[] readLines(String filename) throws IOException   
    {  
        FileReader reader = new FileReader(filename);  
          
        BufferedReader bufferedReader = new BufferedReader(reader);  
        List<String> lines = new ArrayList<String>();  
        String line = null;  
          
        while ((line = bufferedReader.readLine()) != null)   
        {  
            lines.add(line);  
        }  
          
        bufferedReader.close();  
          
        return lines.toArray(new String[lines.size()]);  
    }     
}  