/**
 * Created by vsevolodmolchanov on 20.02.17.
 */
import java.util.Scanner;

public class Kth {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long k = in.nextLong();
        k += 1;
        long i = 1, j = 1, n = 9;
        while(n < k) {
            i++;
            j *= 10;
            n += i * 9 * j;
        }

        long p = j * 10 - 1 - (n - k) / i;

        int r = (int)((n - k) % i);

        while(r != 0) {
            p /= 10;
            r--;
        }

        System.out.println(p % 10);
    }

    private static long pow(long j) {
        long k = 1;
        while(j != 0) {
            k *= 10;
            j--;
        }
        return k;
    }
}
