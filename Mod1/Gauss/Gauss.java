/**
 * Created by vsevolodmolchanov on 07.04.17.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Gauss {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Matrix matrix = new Matrix(n);
        for(int i = 0; i <= n - 1; i++) {
            for (int j = 0; j <= n; j++) {
                matrix.setNum(i, j, in.nextInt());
            }
        }

        if(isSolution(matrix, n)) {
            printSolution(matrix, n);
        } else {
            System.out.println("No solution");
        }
    }

    private static boolean isSolution(Matrix matrix, int n) {
        for(int i = 0; i <= n - 1; i++) {
            if(matrix.getNum(i, i) == 0) {
                for(int j = i + 1; j <= n - 1; j++) {
                    if(matrix.getNum(j, i) != 0) {
                        for(int k = 0; k <= n; k++) {
                            int buf=matrix.getNum(i, k); matrix.setNum(i, k, matrix.getNum(j, k));matrix.setNum(j, k, buf);
                            buf=matrix.getDenom(i, k);matrix.setDenom(i, k, matrix.getDenom(j, k));matrix.setDenom(j, k, buf);
                        }
                        break;
                    }
                }
                if(matrix.getNum(i, i) == 0) {
                    return false;
                }
            }
            int num = matrix.getNum(i, i);
            int denom = matrix.getDenom(i, i);
            for(int j = 0; j <= n; j++) {
                matrix.setNum(i, j, matrix.getNum(i, j) * denom);
                matrix.setDenom(i, j, matrix.getDenom(i, j) * num);
                int g = gcd(matrix.getNum(i, j), matrix.getDenom(i, j));
                if(g == 0) {
                    return false;
                }
                matrix.setNum(i, j, matrix.getNum(i, j) / g);
                matrix.setDenom(i, j, matrix.getDenom(i, j) / g);
            }
            for(int j = 0; j <= n - 1; j++) {
                if(j == i) {
                    continue;
                }
                num = matrix.getNum(j, i);
                denom = matrix.getDenom(j, i);
                for(int k = 0; k <= n; k++) {
                    matrix.setNum(j, k, matrix.getNum(j, k) * denom * matrix.getDenom(i, k) - num * matrix.getDenom(j, k) * matrix.getNum(i, k));
                    matrix.setDenom(j, k, matrix.getDenom(j, k) * denom * matrix.getDenom(i, k));
                    int g = gcd(matrix.getNum(j, k), matrix.getDenom(j, k));
                    matrix.setNum(j, k, matrix.getNum(j, k) / g);
                    matrix.setDenom(j, k, matrix.getDenom(j, k) / g);
                }
            }
        }
        return true;
    }

    private static void printSolution(Matrix matrix, int n) {
        for (int i = 0; i <= n - 1; i++) {
            if (matrix.getNum(i, n) == 0) {
                System.out.println("0/1");
            } else if(matrix.getNum(i, n) * matrix.getDenom(i, n) < 0) {
                System.out.println(-Math.abs(matrix.getNum(i, n)) + "/" + Math.abs(matrix.getDenom(i, n)));
            } else {
                System.out.println(Math.abs(matrix.getNum(i, n)) + "/" + Math.abs(matrix.getDenom(i, n)));
            }
        }
    }

    private static int gcd(int a, int b) {
        a = Math.abs(a); b = Math.abs(b);
        return b == 0 ? a : gcd(b, a % b);
    }
}

class Matrix {
    private int[][] numerator, denominator;
    private int n;

    public Matrix(int n) {
        this.n = n;
        numerator = new int[n][n + 1];
        denominator = new int[n][n + 1];
        for(int i = 0; i <= n - 1; i++) {
            for(int j = 0; j <= n; j++) {
                denominator[i][j] = 1;
            }
        }
    }

    public void setNum(int i, int j, int num) {
        numerator[i][j] = num;
    }

    public void setDenom(int i, int j, int denom) {
        denominator[i][j] = denom;
    }

    public int getNum(int i, int j) {
        return numerator[i][j];
    }

    public int getDenom(int i, int j) {
        return denominator[i][j];
    }

    public void printMatrix() {
        for(int i = 0; i <= n - 1; i++) {
            for(int j = 0; j <= n; j++) {
                System.out.print(numerator[i][j] + "/" + denominator[i][j] + " ");
            }
            System.out.println();
        }
    }
}