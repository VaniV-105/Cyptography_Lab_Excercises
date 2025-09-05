import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HillServer{
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(4444);
        Socket s = ss.accept();
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        Scanner in = new Scanner(System.in);
        System.out.println("Enter plain text: ");
        String plainText = in.nextLine();

        int n = plainText.length();
        int[][] key = new int[n][n];

        System.out.println("Enter key matrix (" + n + "x" + n + ")");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                key[i][j] = in.nextInt();
            }
        }
        in.nextLine(); 
        String encryptedText = CommonUtils.hill_encrypt(plainText, key);
        System.out.println("Encrypted text: " + encryptedText);
        pw.println(encryptedText);
        ss.close();
        pw.close();
        in.close();
    }
}