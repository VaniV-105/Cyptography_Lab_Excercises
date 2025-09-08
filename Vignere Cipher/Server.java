import java.util.*;
import java.net.*;
import java.io.*;

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

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        String cipherText = CommonUtils.vignere_encrypt(plainText, key);
        System.out.println("Encrypted text: " + cipherText);
        pw.println(cipherText);

        ss.close();
    }
}