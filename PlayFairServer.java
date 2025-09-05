
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PlayFairServer {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(4444);
        Socket s = ss.accept();
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter plain text (uppercase): ");
        String plainText = sc.nextLine().toUpperCase();

        System.out.print("Enter Playfair key (uppercase): ");
        String key = sc.nextLine().toUpperCase();

        String cipherText = CommonUtils.playfair_encrypt(plainText, key);
        System.out.println("Encrypted Text: " + cipherText);

        pw.println(cipherText);
        ss.close();

    }
}
