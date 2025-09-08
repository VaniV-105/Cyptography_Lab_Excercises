public class CommonUtils{

    public static boolean isLowerCase(char curr){
        return ('a' <= curr && curr <= 'z');
    }

    public static boolean isUpperCase(char curr){
        return ('A' <= curr && curr <= 'Z');
    }

    public static String caesar_encrypt(String plainText, int key){
        StringBuilder sb = new StringBuilder();
        int n = plainText.length();
        for(int i = 0; i < n; i++){
            char curr = plainText.charAt(i);
            if (isLowerCase(curr)){
                int shift = (curr - 'a' + key) % 26;
                sb.append((char)('a'+shift));
            }
            else if (isUpperCase(curr)){
                int shift = (curr - 'A' + key) % 26;
                sb.append((char)('A'+shift));
            }
            else{
                sb.append(curr);
            }
        }
        return sb.toString();
    }

    public static String caesar_decrypt(String cipherText, int key){
        return caesar_encrypt(cipherText, -key);
    }


}