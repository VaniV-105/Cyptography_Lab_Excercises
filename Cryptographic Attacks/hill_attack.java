import java.util.*;

public class hill_attack {

    // Function to compute determinant of 2x2 matrix mod 26
    static int determinant(int[][] m) {
        return ((m[0][0] * m[1][1]) - (m[0][1] * m[1][0])) % 26;
    }

    // Function to compute modular inverse
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return -1;
    }

    // Function to find inverse of 2x2 matrix mod 26
    static int[][] inverseMatrix(int[][] m) {
        int det = determinant(m);
        det = (det + 26) % 26;

        int invDet = modInverse(det, 26);
        if (invDet == -1) {
            throw new ArithmeticException("Matrix is not invertible mod 26.");
        }

        int[][] inv = new int[2][2];
        inv[0][0] = m[1][1];
        inv[1][1] = m[0][0];
        inv[0][1] = -m[0][1];
        inv[1][0] = -m[1][0];

        // Apply mod and inverse determinant
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                inv[i][j] = (inv[i][j] + 26) % 26;
                inv[i][j] = (inv[i][j] * invDet) % 26;
            }

        return inv;
    }

    // Convert 4-character string into 2x2 matrix
    static int[][] textToMatrix(String text) {
        int[][] matrix = new int[2][2];
        text = text.toUpperCase();
        for (int i = 0; i < 4; i++) {
            matrix[i / 2][i % 2] = text.charAt(i) - 'A';
        }
        return matrix;
    }

    // Multiply 2 matrices mod 26
    static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
                result[i][j] %= 26;
            }
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter 4-letter plaintext: ");
        String pt = scan.nextLine();
        System.out.print("Enter 4-letter ciphertext: ");
        String ct = scan.nextLine();

        if (pt.length() != 4 || ct.length() != 4) {
            System.out.println("Exactly 4 characters of plaintext and ciphertext required.");
            return;
        }

        int[][] P = textToMatrix(pt);  // Plaintext matrix
        int[][] C = textToMatrix(ct);  // Ciphertext matrix

        try {
            int[][] P_inv = inverseMatrix(P);
            int[][] K = multiplyMatrices(C, P_inv);

            System.out.println("Recovered 2x2 Key Matrix:");
            for (int[] row : K) {
                for (int val : row) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
