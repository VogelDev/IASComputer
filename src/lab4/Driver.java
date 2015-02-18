package lab4;

public class Driver {

    public static void main(String[] args) {
       String hex = "0d0050d005";
       
       long m = Long.valueOf(hex, 16);
       
       System.out.println(m);

       String binary = Long.toBinaryString(m);
       
       System.out.println(binary);
       
       long leftIn = m>>>20;
       long rightIn = m & Integer.valueOf("fffff", 16);
       long leftOp = leftIn >>>12;
       long leftAd = leftIn & Integer.valueOf("fff", 16);
       long rightOp = rightIn >>>12;
       long rightAd = rightIn & Integer.valueOf("fff", 16);

       System.out.println(leftIn + " " + rightIn);
       System.out.println(leftOp + " " + leftAd);
       System.out.println(rightOp + " " + rightAd);
    }
}