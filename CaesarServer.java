
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CaesarServer {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(4444);
        Socket s = ss.accept();
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        System.out.print("Enter plain text: ");
        Scanner in = new Scanner(System.in);
        String plainText = in.nextLine();

        System.out.print("Enter key: ");
        int key = in.nextInt();
        in.nextLine();

        String cipherText = CommonUtils.caesar_encrypt(plainText, key);
        System.out.println("Encrypted text: " + cipherText);
        pw.println(cipherText);
        ss.close();
    }
}
