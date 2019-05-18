/**
 * Created by vsevolodmolchanov on 26.03.17.
 */

import java.math.BigInteger;
import java.util.Scanner;

public class FastFib {
    public static void main(String[] args) {
        BigInteger a11 = BigInteger.ONE, a12 = BigInteger.ONE, a21 = BigInteger.ONE, a22 = BigInteger.ZERO; //матрица
        BigInteger f1 = BigInteger.ONE, f2 = BigInteger.ZERO; //столбец результата
        BigInteger h11 = BigInteger.ZERO, h12 = BigInteger.ZERO, h21 = BigInteger.ZERO, h22 = BigInteger.ZERO; //вспомогательная матрица

        Scanner in = new Scanner(System.in);
        int n = in.nextInt() - 1;

        while(n != 0) {
            if(n % 2 == 1) {
                h11 = f1.multiply(a11).add(f2.multiply(a21));
                h12 = f1.multiply(a12).add(f2.multiply(a22));
                f1 = h11;
                f2 = h12;
                n--;
            }
            h11 = a11.multiply(a11).add(a12.multiply(a21));
            h12 = a11.multiply(a12).add(a12.multiply(a22));
            h21 = a21.multiply(a11).add(a22.multiply(a21));
            h22 = a21.multiply(a12).add(a22.multiply(a22));
            a11 = h11;
            a12 = h12;
            a21 = h21;
            a22 = h22;
            n /= 2;
        }

        System.out.println(f1);
    }
}