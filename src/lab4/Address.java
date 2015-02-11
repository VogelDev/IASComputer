package lab4;

public class Address {
    String hex;
    int decimal;
    
    public Address(){
        decimal = 0;
        hex = new String();
    }
    
    public int getVal(){
        return decimal;
    }
    
    public String getHex(){
        return hex;
    }

    public void setVal(int decimal) {
        this.decimal = decimal;
        hex = Integer.toHexString(decimal);
        if(hex.length() < 3){
            hex = String.format("%3", hex);
        }
    }
}
