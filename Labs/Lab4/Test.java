/**
 * Created by vsevolodmolchanov on 21.03.17.
 */
public class Test {
    public static void main(String[] args) {
        String b = "    I like     programming    ";
        Sentence stuff = new Sentence(b);
        for(String s : stuff) {
            System.out.println(s);
        }
    }
}
