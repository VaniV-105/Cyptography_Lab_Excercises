

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class AffineClient{
	
	public static void main(String[] args) throws IOException{

		Socket s = new Socket("localhost", 4444);
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
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
			String cipherText = br.readLine();
			String plainText = CommonUtils.affine_decrypt(cipherText, key1, key2);
			System.out.println("Plain Text: " + plainText);
			
		}
		br.close();
		s.close();
	}
}