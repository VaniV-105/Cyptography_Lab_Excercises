import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(4444);
        System.out.println("Server is running at Port 4444...");
        Socket s = ss.accept();
        System.out.println("Client Connected...");
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);

        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine();

        System.out.print("Enter Key 1: ");
        int key1 = sc.nextInt();

        System.out.print("Enter Key 2: ");
        int key2 = sc.nextInt();

        boolean coprime = CommonUtils.isCoPrime(key1, 26);

        if(!coprime){
            System.out.println("Affine Cipher not possible");
        }
        else{
            String cipherText = CommonUtils.affine_encrypt(plainText, key1, key2);
            System.out.println("Encrypted text: " + cipherText);
            pw.println(cipherText);
        }
        ss.close();
    }
}