public class CommonUtils{

    public static int gcd(int a, int b){
        if (b==0){
            return a;
        }
        return gcd(b, a%b);
    }

    public static boolean isCoPrime(int num, int mod){
        return gcd(num, mod) == 1;
    }

    public static boolean isLowerCase(char curr){
        return ('a' <= curr && curr <= 'z');
    }

    public static boolean isUpperCase(char curr){
        return ('A' <= curr && curr <= 'Z');
    }

    public static int multiplicative_inverse(int key, int mod){
        for(int i = 1; i <= mod; i++){
            if ((key*i)%mod == 1){
                return i;
            }
        }
        return -1;
    }

    public static String affine_encrypt(String plainText, int key1, int key2){
        StringBuilder sb = new StringBuilder();
        int n = plainText.length();
        for(int i = 0; i < n; i++){
            char curr = plainText.charAt(i);
            if (isLowerCase(curr)){
                int offset = curr - 'a';
                int shift = (key1 * offset + key2) % 26;
                sb.append((char)('a' + shift));
            }
            else if (isUpperCase(curr)){
                int offset = curr - 'A';
                int shift = (key1 * offset + key2) % 26;
                sb.append((char)('A' + shift));
            }
            else { 
                sb.append(curr);
            }
        }
        return sb.toString();
    }

    public static String affine_decrypt(String cipherText, int key1, int key2){
        StringBuilder sb = new StringBuilder();
        int n = cipherText.length();
        int inv_key1 = multiplicative_inverse(key1, 26);
        int inv_key2 = -key2;
        for(int i = 0; i < n; i++){
            char curr = cipherText.charAt(i);
            if (isLowerCase(curr)){
                int offset = curr - 'a';
                int shift = (inv_key1 * ((offset + inv_key2) % 26)) % 26;
                sb.append((char)('a' + shift));
            }
            else if (isUpperCase(curr)){
                int offset = curr - 'A';
                int shift = (inv_key1 * ((offset + inv_key2) % 26)) % 26;
                sb.append((char)('A' + shift));
            }
            else { 
                sb.append(curr);
            }
        }
        return sb.toString();
    }
}