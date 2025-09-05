import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class HillClient{
    public static void main(String[] args) throws IOException{
        Socket s = new Socket("localhost", 4444);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        Scanner in = new Scanner(System.in);
        String cipherText = br.readLine();
        System.out.println("Cipher Text : " + cipherText);

        int n = cipherText.length();
        int[][] key = new int[n][n];

        System.out.println("Enter key matrix (" + n + "x" + n + ")");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                key[i][j] = in.nextInt();
            }
        }
        in.nextLine(); 
        String decryptedText = CommonUtils.hill_decrypt(cipherText, key);
        System.out.println("Decrypted text: " + decryptedText);
        s.close();
        br.close();
        in.close();
    }
}