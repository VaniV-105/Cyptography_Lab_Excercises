public class CommonUtils{

    public static char[][] get_keymatrix(String key){
        char[][] key_matrix = new char[5][5];
        boolean[] filled = new boolean[26];
        for(int i = 0; i < 26; i++){
            filled[i] = false;
        }
        int row = 0, col = 0;
        for(char i : key.toCharArray()){
            if (!filled[i - 'a']){
                key_matrix[row][col] = i;
                col = (col + 1) % 5;
                filled[i - 'a'] = true;
                if (col == 0){
                    row = (row + 1) % 5;
                }
            }
        }

        for(int i = row; i < 5; i++){
            boolean skipped = false;
            for(int j = 0; j < 5; j++){
                if(!skipped && i == row){
                    skipped = true;
                    j = col;
                }
                for(int k = 0; k < 26; k++){
                    if (!filled[k] && (k + 'a')!= 'j'){
                        key_matrix[i][j] = (char)(k + 'a');
                        filled[k] = true;
                        break;
                    }
                }
            }
        }
        return key_matrix;
    }

    public static void display_matrix(char[][] matrix){
        int n = matrix.length, m = matrix[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] find(char[][] key_matrix, char c){
        for(int i = 0 ; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(key_matrix[i][j] == c){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static String select_pair(char[][] key, char a, char b, boolean encrypt){
        int shift = 1;
        StringBuilder sb = new StringBuilder();
        if (!encrypt){
            shift = -1;
        }
        int[] loc1 = find(key,a);
        int[] loc2 = find(key,b);
        if(loc1[0] == loc2[0]){
            int row = loc1[0];
            int lc = (loc1[1] + shift + 5) % 5 , ld = (loc2[1] + shift + 5) % 5;
            sb.append(key[row][lc]);
            sb.append(key[row][ld]);
        }
        else if(loc1[1] == loc2[1]){
            int col = loc1[1];
            int lc = (loc1[0] + shift + 5) % 5 , ld = (loc2[0] + shift + 5) % 5;
            sb.append(key[lc][col]);
            sb.append(key[ld][col]);
        }
        else{
            int row1 = loc1[0];
            int col1 = loc2[1];
            int row2 = loc2[0];
            int col2 = loc1[1];
            sb.append(key[row1][col1]);
            sb.append(key[row2][col2]);
        }
        return sb.toString();
    }

    public static String playfair_encrypt(String plainText, String key){
        char[][] key_matrix = get_keymatrix(key);
        System.out.println("Key Matrix:");
        display_matrix(key_matrix);
        int n = plainText.length();
        char[][] pairs = new char[n][2];
        int i = 0, j = 0;
        while (i < n){
            String pair = plainText.substring(i, Math.min(n, i+2));
            if (pair.length() != 2){
                pair += 'x';
            }
            char c = pair.charAt(0); 
            char d = pair.charAt(1);
            if(c==d){
                i++;
                pairs[j][0] = c;
                pairs[j][1] = 'x';
            }
            else{
                i+=2;
                pairs[j][0] = c;
                pairs[j][1] = d;
            }
            j++;
        }
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < j; k++){
            char c = pairs[k][0] , d = pairs[k][1];

            String enpair = select_pair(key_matrix, c, d, true);
            sb.append(enpair);
        }
        return sb.toString();
    }

    public static String playfair_decrypt(String cipherText, String key){
        char[][] key_matrix = get_keymatrix(key);
        System.out.println("Key Matrix:");
        display_matrix(key_matrix);
        int n = cipherText.length();
        char[][] pairs = new char[n][2];
        int i = 0, j = 0;
        while (i < n){
            String pair = cipherText.substring(i, Math.min(n, i+2));
            if (pair.length() != 2){
                pair += 'c';
            }
            char c = pair.charAt(0); 
            char d = pair.charAt(1);
            if(c==d){
                i++;
                pairs[j][0] = c;
                pairs[j][1] = 'x';
            }
            else{
                i+=2;
                pairs[j][0] = c;
                pairs[j][1] = d;
            }
            j++;
        }
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < j; k++){
            char c = pairs[k][0] , d = pairs[k][1];

            String enpair = select_pair(key_matrix, c, d, false);
            sb.append(enpair);
        }
        return sb.toString();
    }
    
}