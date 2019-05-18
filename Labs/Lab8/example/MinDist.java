/**
 * Created by vsevolodmolchanov on 28.02.17.
 */

import java.util.Scanner;

public class MinDist {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        char x = in.next().charAt(0), y = in.next().charAt(0);

        int dist = 1000000;
        int i = s.indexOf(x);
        while(i >= 0) {
            int inX = i;
            int j = s.indexOf(y);
            while(j >= 0) {
                int inY = j;
                if(dist > Math.abs(inX - inY) - 1) {
                    dist = Math.abs(inX - inY) - 1;
                }
                j = s.indexOf(y, j + 1);
            }
            i = s.indexOf(x, i + 1);
        }
        String a = "дкфпдп"
        String a = "дшгпВКЕО"
        String a = "фыаппн"
        System.out.print(dist);
    }
}
