/**
 * Created by vsevolodmolchanov on 27.03.17.
 */

import java.util.Scanner;

public class Econom {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int index, oper = 0;
        while((index = s.indexOf(')')) != -1) {
            String expr = s.substring(index - 4, index + 1);
            s = s.replace(expr, "1");
            oper++;
        }
        System.out.println(oper);
    }
}
