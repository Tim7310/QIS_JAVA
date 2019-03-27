package others;
import java.util.*; 
  
public class barCode 
{ 
    public static void main(String[] args) 
    { 
        // Length of your password as I have choose 
        // here to be 8 
        int length = 8; 
        System.out.println(geek_barcode(length)); 
    } 
  
    
    static char[] geek_barcode(int len) 
    { 
        System.out.print("Barcode : "); 
        String numbers = "0123456789"; 
        String values =  numbers; 
  
        // Using random method 
        Random rndm_method = new Random(); 
  
        char[] bCode = new char[len]; 
  
        for (int i = 0; i < len; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            bCode[i] = 
              values.charAt(rndm_method.nextInt(values.length())); 
  
        } 
        return bCode; 
    } 
} 