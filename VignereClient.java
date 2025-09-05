import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class VignereClient {
    public static void main(String[] args) throws IOException{
       Socket s = new Socket("localhost", 4444);
       BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
       Scanner in = new Scanner(System.in);
       String keyStream, cipherText;
       System.out.print("Enter key stream: ");
       keyStream = in.nextLine();

       cipherText = br.readLine();

       String plainText = CommonUtils.vignere_decrypt(cipherText, keyStream);
       System.out.println("Decypted Text: " + plainText);
       s.close();
    }
}
