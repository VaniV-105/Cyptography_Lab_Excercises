import java.io.*;
import java.util.*;
import java.net.*;

public class Client{
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("localhost", 4444);
        System.out.println("Connected to Server...");
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String cipherText = br.readLine();
        System.out.println("Encrypted text [Received]: " + cipherText);

        System.out.print("Enter key: ");
        int key = sc.nextInt();
        sc.nextLine();

        String plainText = CommonUtils.caesar_decrypt(cipherText, key);
        System.out.println("Decrypted text: " + plainText);

        s.close();
    }
}