import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("localhost",4444);
        System.out.println("Client Connected to Server...");
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String cipherText = br.readLine();
        System.out.println("Encrypted text [Received]: " + cipherText);

        System.out.print("Enter Key 1: ");
        int key1 = sc.nextInt();

        System.out.print("Enter Key 2: ");
        int key2 = sc.nextInt();

        boolean coprime = CommonUtils.isCoPrime(key1, 26);

        if(!coprime){
            System.out.println("Affine Cipher not possible");
        }
        else{
            String plainText = CommonUtils.affine_decrypt(cipherText, key1, key2);
            System.out.println("Decrypted text: " + plainText);
        }
        s.close();
    }
}