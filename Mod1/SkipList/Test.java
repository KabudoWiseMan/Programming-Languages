/**
 * Created by vsevolodmolchanov on 28.03.17.
 */
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer,String> a = new SkipList<>(3);
        a.put(20, "beta");
        a.put(10, "alpha");
        a.put(30, "gamma");
        for (int key : a.keySet()) {
            System.out.println(a.get(key));
        }
    }
}
