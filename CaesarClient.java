
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class CaesarClient {
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 4444);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        Scanner in = new Scanner(System.in);
        String cipherText = br.readLine();

        System.out.print("Enter key: ");
        int key = in.nextInt();
        in.nextLine();

        String plainText = CommonUtils.caesar_decrypt(cipherText, key);
        System.out.println("Plain Text: " + plainText);
        
        s.close();
    }
}
