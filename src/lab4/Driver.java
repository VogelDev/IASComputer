package lab4;

public class Driver {

    public static void main(String[] args) {
        String number = Integer.toHexString(255);
        number = String.format("%3f", number);
        String hex = "ff";
        //number = number.substring(number.length() - 24, number.length());
        int num = Integer.valueOf(hex, 16);
        
        //System.out.println(hex);
        System.out.println(number);
    }

}
