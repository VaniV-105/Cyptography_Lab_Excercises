






public class CommonUtils {
    public static int gcd(int a, int b){
        if (b == 0){
            return a;
        }
        return gcd(b, a%b);
    }
    public static boolean isCoprime(int a, int b){
        return gcd(a, b) == 1;
    }
    public static boolean isLowerAlpha(char a){
        return 'a' <= a && 'z' >= a;
    }
    public static String affine_encrypt(String plainText, int key1, int key2){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++){
			if(isLowerAlpha(plainText.charAt(i))){
                int offset =  plainText.charAt(i) - 'a';
                int multiplicativeShift = (offset * key1) % 26;
                int additiveShift = (multiplicativeShift + key2)%26;
                char curr = (char) ('a' + additiveShift);
                sb.append(curr);
            }
            else{
                sb.append(plainText.charAt(i));
            }
		}
		return sb.toString();
	}
    public static int multiplicativeInverse(int num, int mod){
        for(int x = 1; x <= mod; x++){
            if((x * num) % mod == 1){
                return x;
            }
        }
        return 0;
    }
    public static void displayMatrix(int[][] a){
        int n = a.length;
        int m = a[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static String affine_decrypt(String cipherText, int key1, int key2){
		StringBuilder sb = new StringBuilder();
        int inv_k1 = multiplicativeInverse(key1, 26);
        int inv_k2 = -key2;
		for (int i = 0; i < cipherText.length(); i++){
            if(isLowerAlpha(cipherText.charAt(i))){
                int offset =  cipherText.charAt(i) - 'a';
                int additiveShift = (offset + inv_k2 + 26)%26;
                int multiplicativeShift = (additiveShift * inv_k1)%26;
        
                char curr = (char) (multiplicativeShift + 'a');
                sb.append(curr);
            }else{
                sb.append(cipherText.charAt(i));
            }
		}
		return sb.toString();
	}
    public static String hill_encrypt(String plainText, int[][] key){
        int n = plainText.length();
        int kn = key.length;
        if (n!=kn){
            return null;
        }
        int[][] text = new int[n][1];
        for(int i = 0; i < n;i++){
            text[i][0] = plainText.charAt(i) - 'a';
        }
        int[][] res = MatrixUtils.multiply(key, text);
        res = MatrixUtils.matrix_mod(res, 26);
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++){
            ans.append((char)(res[i][0] + 'a'));
        }
        return ans.toString();
    }
    public static String hill_decrypt(String cipherText, int[][] key){
        int n = cipherText.length();
        int kn = key.length;
        if(n!=kn){
            return null;
        }
        int[][] inv_key = MatrixUtils.multiplicativeInverse(key);
        if(inv_key == null){
            return "Can't find inverse of the key";
        }
        
        // System.out.println(Arrays.toString(inv_key));
        int[][] text = new int[n][1];
        for(int i = 0; i < n;i++){
            text[i][0] = cipherText.charAt(i) - 'a';
        }
        
        int[][] res = MatrixUtils.multiply(inv_key, text);
        // System.out.println("Before Mod: ");
        // displayMatrix(res);
        res = MatrixUtils.matrix_mod(res, 26);
        // System.out.println("After Mod: ");
        // displayMatrix(res);
        StringBuilder ans = new StringBuilder(); 
        for(int i = 0; i < n; i++){
            ans.append((char)(res[i][0] + 'a'));
        }
        return ans.toString();
    }
    public static char[][] prepare_key_matrix(String key){
        boolean[] exists = new boolean[26];
        for(int i = 0 ; i < 26; i++){
            exists[i] = false;
        }
        StringBuilder properKey = new StringBuilder();
        for (int i = 0; i < key.length(); i++){
            if (key.charAt(i) == 'J'){
                properKey.append('I');
            }
            else{
                properKey.append(key.charAt(i));
            }
        }
        key = properKey.toString();
        StringBuilder uniqueKey = new StringBuilder();
        for(int i = 0; i < key.length(); i++){
            if (!exists[key.charAt(i)-'A']){
                uniqueKey.append(key.charAt(i));
                exists[key.charAt(i)-'A'] = true;
            }
        }
        char[][] key_matrix = new char[5][5];
        int c =0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                key_matrix[i][j] = uniqueKey.charAt(c++);
                if (c == uniqueKey.length()){
                    break;
                }
            }
            if(c == uniqueKey.length()){
                break;
            }
        }
        int rows_covered = uniqueKey.length() / 5;
        int cols_covered = uniqueKey.length() % 5;
        char curr = 'A';
        for(int i = rows_covered; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(i == rows_covered){
                    j = cols_covered;
                }
                while(exists[curr-'A'] || curr == 'J') curr++;
                key_matrix[i][j] = curr++;
            }
        }
        return key_matrix;
    }
    public static  int[] row_column(char letter, char[][] key_matrix){
        int[] pos = new int[2];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(key_matrix[i][j] == letter){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return null;
        
    }
    public static  String pf_encrypt_pair(String pair, char[][] key_matrix){
        int[] first_pos = row_column(pair.charAt(0), key_matrix);
        int[] second_pos = row_column(pair.charAt(1), key_matrix);
        StringBuilder sb = new StringBuilder();
        //both on same row or column
        if(first_pos[0] == second_pos[0]){
            int r = first_pos[0];
            int first_next_idx = (first_pos[1] + 1) % 5;
            int second_next_idx = (second_pos[1] + 1) % 5;
            sb.append(key_matrix[r][first_next_idx]);
            sb.append(key_matrix[r][second_next_idx]);
  
        }
        else if(first_pos[1] == second_pos[1]){
            int c = first_pos[1];
            int first_next_idx = (first_pos[0] + 1) % 5;
            int second_next_idx = (second_pos[0] + 1) % 5;
            sb.append(key_matrix[first_next_idx][c]);
            sb.append(key_matrix[second_next_idx][c]);
            
        }
        else{
            int r1 = first_pos[0], r2 = second_pos[0], c1 = first_pos[1], c2 = second_pos[1];
            sb.append(key_matrix[r1][c2]);
            sb.append(key_matrix[r2][c1]);
        }
        return sb.toString();
    }
    public static  String pf_decrypt_pair(String pair, char[][] key_matrix){
        int[] first_pos = row_column(pair.charAt(0), key_matrix);
        int[] second_pos = row_column(pair.charAt(1), key_matrix);
        StringBuilder sb = new StringBuilder();
        //both on same row or column
        if(first_pos[0] == second_pos[0]){
            int r = first_pos[0];
            int first_next_idx = (first_pos[1] - 1 + 5) % 5;
            int second_next_idx = (second_pos[1] - 1 + 5) % 5;
            sb.append(key_matrix[r][first_next_idx]);
            sb.append(key_matrix[r][second_next_idx]);
  
        }
        else if(first_pos[1] == second_pos[1]){
            int c = first_pos[1];
            int first_next_idx = (first_pos[0] - 1 + 5) % 5;
            int second_next_idx = (second_pos[0] - 1 + 5) % 5;
            sb.append(key_matrix[first_next_idx][c]);
            sb.append(key_matrix[second_next_idx][c]);
            
        }
        else{
            int r1 = first_pos[0], r2 = second_pos[0], c1 = first_pos[1], c2 = second_pos[1];
            sb.append(key_matrix[r1][c2]);
            sb.append(key_matrix[r2][c1]);
        }
        return sb.toString();
    }
    public static String playfair_encrypt(String plainText, String key){
        char[][] key_matrix = prepare_key_matrix(key);
        int n = plainText.length();
        StringBuilder sb = new StringBuilder();
        String pair;
        for(int i = 0; i < n; i++){
            if(i+1 < n) {
                pair = plainText.substring(i,i+2);
                if(pair.charAt(0) == pair.charAt(1)){
                    pair = "" + pair.charAt(0) + 'X';
                }
                else{
                    i += 1;
                }
            }   
            else{
                pair = "" + plainText.charAt(i);
                if(pair.charAt(0) == 'X'){
                    pair = "XQ";
                }
                else{
                    pair = "" + pair.charAt(0) + "X";
                 }
            }
            // System.out.println("PAIR: " + pair);
            String encrypted_pair = pf_encrypt_pair(pair, key_matrix);
            // System.out.println("ENCRYPTED PAIR: " + encrypted_pair);
            sb.append(encrypted_pair);

        }
        return sb.toString();
    }
    public static String playfair_decrypt(String cipherText, String key){
        char[][] key_matrix = prepare_key_matrix(key);
        int n = cipherText.length();
        StringBuilder sb = new StringBuilder();
        String pair;
        for(int i = 0; i < n; i+=2){
            pair = cipherText.substring(i, i+2);
            String decrypted_pair = pf_decrypt_pair(pair, key_matrix);
            sb.append(decrypted_pair);
        }
        return sb.toString();
    }
    public static String caesar_encrypt(String plainText, int key){
        StringBuilder sb  = new StringBuilder();
        int n = plainText.length();
        for(int i = 0; i < n; i++){
            char curr = plainText.charAt(i);
            if('a' <= curr && curr <= 'z'){
                int shift = (curr - 'a' + key)%26;
                sb.append((char)('a' + shift));
            }
            else if('A' <= curr && curr <= 'Z'){
                int shift = (curr - 'A' + key)%26;
                sb.append((char)('A' + shift));
            }
            else{
                sb.append(curr);
            }
        }
        return sb.toString();
    }
    public static String caesar_decrypt(String cipherText, int key){
        key = -key;
        StringBuilder sb  = new StringBuilder();
        int n = cipherText.length();
        for(int i = 0; i < n; i++){
            char curr = cipherText.charAt(i);
            if('a' <= curr && curr <= 'z'){
                int shift = ((curr - 'a' + key)%26 + 26)%26;
                sb.append((char)('a' + shift));
            }
            else if('A' <= curr && curr <= 'Z'){
                int shift = ((curr - 'A' + key)%26 + 26)%26;
                sb.append((char)('A' + shift));
            }
            else{
                sb.append(curr);
            }
        }
        return sb.toString();
    }

    public static String vignere_encrypt(String plainText, String keyStream){
        int n = plainText.length();
        int m = keyStream.length();
        StringBuilder sb = new StringBuilder();
        int c = 0;
        for(int i = 0; i < n; i++){
            int offset;
            int key_i = 0;
            
            if ('a' <= keyStream.charAt(c) && 'z' >= keyStream.charAt(c)){
                key_i = keyStream.charAt(c) - 'a';
            }
            else if ('A' <= keyStream.charAt(c) && 'Z' >= keyStream.charAt(c)){
                key_i = keyStream.charAt(c) - 'A';
            }
            if ('a' <= plainText.charAt(i) && 'z' >= plainText.charAt(i)){
                offset = plainText.charAt(i) - 'a';
                char new_char = (char) ((offset + key_i) % 26 + 'a');
                sb.append(new_char);
            }
            else if ('A' <= plainText.charAt(i) && 'Z' >= plainText.charAt(i)){
                offset = plainText.charAt(i) - 'A';
                char new_char = (char) ((offset + key_i) % 26 + 'A');
                sb.append(new_char);
            }
            else{
                sb.append(plainText.charAt(i));
                c = (c-1+m)%m;
            }

            c = (c+1) % m;
            

        }
        return sb.toString();
    }
    public static String vignere_decrypt(String cipherText, String keyStream){
        int n = cipherText.length();
        int m = keyStream.length();
        StringBuilder sb = new StringBuilder();
        int c = 0;
        for(int i = 0; i < n; i++){
            int offset;
            int key_i = 0;
            
            if ('a' <= keyStream.charAt(c) && 'z' >= keyStream.charAt(c)){
                key_i = keyStream.charAt(c) - 'a';
            }
            else if ('A' <= keyStream.charAt(c) && 'Z' >= keyStream.charAt(c)){
                key_i = keyStream.charAt(c) - 'A';
            }
            
            key_i = -key_i;
            if ('a' <= cipherText.charAt(i) && 'z' >= cipherText.charAt(i)){
                offset = cipherText.charAt(i) - 'a';
                char new_char = (char) ((offset + (key_i%26)+ 26) % 26 + 'a');
                sb.append(new_char);
            }
            else if ('A' <= cipherText.charAt(i) && 'Z' >= cipherText.charAt(i)){
                offset = cipherText.charAt(i) - 'A';
                char new_char = (char) ((offset + (key_i%26) + 26) % 26 + 'A');
                sb.append(new_char);
            }
            else{
                sb.append(cipherText.charAt(i));
                c = (c-1)%m;
            }

            c = (c+1) % m;
            

        }
        return sb.toString();
    }

}
