import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class PlayFairClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 4444);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Scanner sc = new Scanner(System.in);

        String cipherText = br.readLine();

        System.out.print("Enter Playfair key (uppercase, no spaces): ");
        String key = sc.nextLine().toUpperCase();

        String plainText = CommonUtils.playfair_decrypt(cipherText, key);
     
        System.out.println("Decrypted text: " + plainText);
        br.close();
    }
}
