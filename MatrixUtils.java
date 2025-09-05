

public class MatrixUtils{
   public static int[][] multiply(int[][] a, int[][] b){
     int r1 = a.length, c1 = a[0].length;
     int r2 = b.length, c2 = b[0].length;
     if(c1 == r2){
        int[][] res = new int[r1][c2];
        for(int i = 0; i < r1; i++){
            for(int j = 0; j < c2; j++){
                res[i][j] = 0;
                for(int k = 0; k < c1; k++){
                    res[i][j] += (a[i][k] * b[k][j]);
                }
            }
        }
        return res;
     }
     return null;
    }
    public static int[][] get_minor(int[][] a, int n, int r, int c){
        int[][] new_a = new int[n-1][n-1];
        int r1 = 0, c1 = 0;
        for(int j = 0; j < n; j++){
            for (int k = 0; k < n; k++){
                if (j != r && k != c){
                    // System.out.println("r1: " + r1 + "c1: " + c1);
                    new_a[r1][c1] = a[j][k];
                    c1++;
                    if(c1 == n-1){
                        r1++;
                        c1 = 0;
                    }
                }
            }

            
        }
        return new_a;
    }
    public static int determinant(int[][] a){
        int n = a.length;
        int det = 0;
        if (n == 1){
            return a[0][0];
        }
        if (n == 2){
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        }
        for(int i = 0; i < n; i++){
            int sign = (i % 2 == 0)? 1 : -1;
            int[][] new_a = get_minor(a, n, 0, i);
            det += sign * a[0][i] * determinant(new_a);
        }
        return det;
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
    public static int[][] transpose(int[][] a){
        int n = a.length;
        int m = a[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                res[i][j] = a[j][i];
            }
        }
        return res;
    }
    public static int[][] adjoint(int[][] a){
        int n = a.length;
        int[][] res = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j =0;j < n; j++){
                int sign = (i+j) % 2 == 0 ? 1 : -1;
                res[i][j] = sign * determinant(get_minor(a, n, i, j));
            }
        }
        return transpose(res);
    }
    public static int[][] matrix_mod(int[][] a, int mod){
        int n = a.length;
        int m = a[0].length;
        for(int i = 0; i < n;i++){
            for(int j = 0; j < m; j++){
                a[i][j] = a[i][j] % 26;
            }
        }
        return a;
    }
    public static int[][] multiplicativeInverse(int[][] a){
        int det = determinant(a);
        // System.out.println("Determinant : " + det);
        if (det == 0){
            return null;
        }
        int mul_inv_det = CommonUtils.multiplicativeInverse(det, 26);
        if (mul_inv_det == 0){
            return null;
        }
        // System.out.println("Inv of Determinant : " + mul_inv_det);
        int[][] res = adjoint(a);
        // System.out.println("Adjoint");
        // displayMatrix(res);
        int n = a.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                res[i][j] *= mul_inv_det;
                res[i][j] %= 26;
            }
        }
        return res;
    }
}