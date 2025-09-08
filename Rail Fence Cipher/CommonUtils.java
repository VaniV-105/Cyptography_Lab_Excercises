public class CommonUtils{

    public static String railfence_encrypt(String plainText, int key){
        int n = plainText.length();
        char[][] rail = new char[key][n];
        for(int i = 0; i < key; i++){
            for(int j = 0; j < n; j++){
                rail[i][j] = '\n';
            }
        }

        int row = 0, col = 0;
        boolean dir_down = false;

        for(int i = 0; i < n; i++){
            if (row == 0 || row == key-1){
                dir_down = !dir_down;
            }
            rail[row][col++] = plainText.charAt(i);
            row += dir_down ? 1 : -1;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < key ;i++){
            for(int j = 0 ; j < n; j++){
                if(rail[i][j]!='\n'){
                    sb.append(rail[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static String railfence_decrypt(String cipherText, int key){
        int n = cipherText.length();
        char[][] rail = new char[key][n];
        for(int i = 0; i < key; i++){
            for(int j = 0; j < n; j++){
                rail[i][j] = '\n';
            }
        }

        boolean dir_down = true;
        int row = 0, col = 0;

        for(int i = 0; i < n; i++){
            if (row == 0) dir_down = true;
            if (row == key-1) dir_down = false;
            rail[row][col++] = '*';
            row += dir_down ? 1:-1;
        }

        int index = 0;
        for(int i = 0; i < key; i++){
            for(int j = 0; j < n; j++){
                if(index < n && rail[i][j] == '*'){
                    rail[i][j] = cipherText.charAt(index++);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        row = 0; col = 0;
        dir_down = true;

        for(int i = 0; i < n; i++){
            if (row == 0) dir_down = true;
            if (row == key - 1) dir_down = false;
            if(rail[row][col] != '\n'){
                sb.append(rail[row][col++]);
            }
            row += dir_down ? 1:-1;
        }
        return sb.toString();
    }
}