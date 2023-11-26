package IOSDriverManager;

import java.io.IOException;
import java.util.Random;

import com.jcraft.jsch.JSchException;

public class GetRandomNumber {

    public String number (String number) throws JSchException, IOException, InterruptedException { 
        Random rand = new Random();

        // Generate a random 6-digit suffix for the phone number
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            suffix.append(rand.nextInt(10));
        }

        // Append the prefix "0800" to the suffix
        return "+62800" + suffix.toString();
    }

    public static void main(String[] args) throws JSchException, IOException, InterruptedException {
        GetRandomNumber generator = new GetRandomNumber();
    	String  Hasilnya = generator.number("number1");
         
        // Generate and print a random phone number with "0800" prefix
      
        System.out.println("Generated Phone Number: " + Hasilnya);
    }
}

