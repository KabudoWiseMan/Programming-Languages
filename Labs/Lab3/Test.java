/**
 * Created by vsevolodmolchanov on 28.02.17.
 */

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Sentence[] a = new Sentence[] {
                new Sentence("a aa aaa"),
                new Sentence("a aa aaaa"),
                new Sentence("a aa aaaaaa"),
                new Sentence("a aa aaaaa"),
                new Sentence("a aa aaaaaa"),
                new Sentence(""),
                new Sentence("aaaaaaa")
        };
        Arrays.sort(a);
        for(Sentence s : a) System.out.println(s);
    }
}
