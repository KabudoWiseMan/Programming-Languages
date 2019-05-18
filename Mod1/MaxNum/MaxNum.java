/**
 * Created by vsevolodmolchanov on 21.02.17.
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class MaxNum {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long numbers[] = new long[n];
        for (int i = 0; i <= n - 1; i++) {
            numbers[i] = in.nextLong();
        }

        String[] strNumbers = new String[n];
        for (int i = 0; i <= n - 1; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strNumbers, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        String finalNumber = "";
        for(int i = 0; i <= n - 1; i++) {
            finalNumber += strNumbers[i];
        }

        System.out.print(finalNumber);
    }
}
