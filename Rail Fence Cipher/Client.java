import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("localhost", 4444);
        System.out.println("Client Connected to Server...");
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String cipherText = br.readLine();
        System.out.println("Encrypted text [Receievd]: " + cipherText);

        System.out.print("Enter the number of rails: ");
        int key = sc.nextInt();
        sc.nextLine();

        String plainText = CommonUtils.railfence_decrypt(cipherText, key);
        System.out.println("Decrypted text: " + plainText);

        s.close();
    }
}