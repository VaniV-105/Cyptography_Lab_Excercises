import java.util.*;
public class affine_attack
{
    public static int modinverse(int a, int m)
    {
        a = a % m;
        for(int i=1; i<m; i++)
        {
            if((a * i)%m == 1)  return i;
        }
        return -1;
    }

    public static int[] findkey(String pt, String ct)
    {
        int x1 = pt.charAt(0) - 'a';
        int x2 = pt.charAt(1) - 'a';
        int y1 = ct.charAt(0) - 'a';
        int y2 = ct.charAt(1) - 'a';

        int dx = (x1 - x2 + 26)%26;
        int dy = (y1 - y2 + 26)%26;
        int dx_inv = modinverse(dx, 26);
        if(dx_inv == -1)
        {
            System.out.println("Modular inverse does not exist");
        }
        int k1 = (dx_inv * dy) % 26;
        int k2 = (y1 - dx_inv * x1 + 26 * 26)%26;
        return new int[]{k1, k2};
    }
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Plain Text :");
        String pt = scan.nextLine();
        System.out.print("Enter Cipher Text : ");
        String ct = scan.nextLine();

        if(pt.length() < 2 || ct.length() < 2)
        {
            System.out.println("Invalid Cipher text and Plain text, need atleast 2 characters");
        }
        int[] key = findkey(pt, ct);
        System.out.println("Recovered Key : ");
        System.out.println("a : "+key[0] + "| b : "+key[1]);
    }
}