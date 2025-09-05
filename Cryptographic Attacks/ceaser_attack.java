import java.util.*;

public class ceaser_attack 
{
    public static int findkey(String pt, String ct)
    {
        if(pt.length() == 0 || ct.length() == 0)
        {
            System.out.println("Input String must not be empty");
        }
        int key = (ct.charAt(0) - pt.charAt(0) + 26)%26;
        return key;
    }
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Plain Text : ");
        String pt = scan.nextLine();
        System.out.print("Enter Cipher Text : ");
        String ct = scan.nextLine();
        System.out.println("Key for Ceaser Cipher is "+findkey(pt, ct));
    }    
}
