

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class AffineServer{
	
	public static void main(String[] args) throws IOException{

		ServerSocket ss = new ServerSocket(4444);
		Socket s = ss.accept();
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

		int key1, key2;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter key1: ");
		key1 = in.nextInt();
		System.out.println("Enter key2 : ");
		key2 = in.nextInt();
		boolean coprime = CommonUtils.isCoprime(key1, key2);
		in.nextLine();
		if(!coprime){
			System.out.println("Given keys are not coprime");
		}
		else{
			System.out.print("Enter plain text: ");
			String plainText = in.nextLine();
			String encryptedText = CommonUtils.affine_encrypt(plainText, key1, key2);
			System.out.println("Encrypted Text: " + encryptedText);
			pw.println(encryptedText);
		}
		pw.close();
		s.close();
	}
}