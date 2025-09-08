import java.util.*;
import java.io.*;
import java.net.*;

public class Server{
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(4444);
        System.out.println("Server is running at port 4444....");
        Socket s = ss.accept();
        System.out.println("Client Connected...");
        PrintWriter pw = new PrintWriter(s.getOutputStream(),true);

        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine();

        System.out.print("Enter key: ");
        int key = sc.nextInt();
        sc.nextLine();

        String cipherText = CommonUtils.caesar_encrypt(plainText, key);
        System.out.println("Encrypted text: " + cipherText);

        pw.println(cipherText);

        ss.close();
    }
}