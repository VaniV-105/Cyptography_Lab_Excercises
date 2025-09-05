
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class VignereServer {
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(4444);
        Socket s = ss.accept();
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        String plainText, keyStream;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter plain text: ");
        plainText = in.nextLine();
        System.out.print("Enter key stream: " );
        keyStream = in.nextLine();

        String encryptedText = CommonUtils.vignere_encrypt(plainText, keyStream);
        pw.println(encryptedText);
        System.out.println("Encrypted Text: " + encryptedText);
        ss.close();
    }
}
