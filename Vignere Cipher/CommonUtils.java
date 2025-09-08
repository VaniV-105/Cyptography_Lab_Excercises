public class CommonUtils{
    
    public static boolean isLowerCase(char curr){
        return ('a' <= curr && curr <= 'z');
    }

    public static boolean isUpperCase(char curr){
        return ('A' <= curr && curr <= 'Z');
    }

    public static String vignere_encrypt(String plainText, String key){
        StringBuilder sb = new StringBuilder();
        int c = 0;
        int n = plainText.length();
        int m = key.length();
        for(int i = 0; i < n; i++){
            int key_shift = 0;
            char curr = plainText.charAt(i);
            char key_curr = key.charAt(c);

            if (isLowerCase(key_curr)){
                key_shift = key_curr - 'a';
            }
            else if(isUpperCase(key_curr)){
                key_shift = key_curr - 'A';
            }

            if(isLowerCase(curr)){
                int offset = curr - 'a';
                int shift = (offset + key_shift) % 26;
                sb.append((char)('a'+shift));
            }
            else if (isUpperCase(curr)){
                int offset = curr - 'A';
                int shift = (offset + key_shift) % 26;
                sb.append((char)('A'+shift));
            }
            else{
                c = (c-1+m) % m;
                sb.append(curr);
            }
            c = (c + 1) % m;
        }
        return sb.toString();
    }

    public static String vignere_decrypt(String cipherText, String key){
        StringBuilder sb = new StringBuilder();
        int c = 0;
        int n = cipherText.length();
        int m = key.length();
        for(int i = 0; i < n; i++){
            int key_shift = 0;
            char curr = cipherText.charAt(i);
            char key_curr = key.charAt(c);

            if (isLowerCase(key_curr)){
                key_shift = key_curr - 'a';
            }
            else if(isUpperCase(key_curr)){
                key_shift = key_curr - 'A';
            }

            if(isLowerCase(curr)){
                int offset = curr - 'a';
                int shift = (offset - key_shift + 26) % 26;
                sb.append((char)('a'+shift));
            }
            else if (isUpperCase(curr)){
                int offset = curr - 'A';
                int shift = (offset - key_shift + 26) % 26;
                sb.append((char)('A'+shift));
            }
            else{
                c = (c-1+m) % m;
                sb.append(curr);
            }
            c = (c + 1) % m;
        }
        return sb.toString();
    }
}