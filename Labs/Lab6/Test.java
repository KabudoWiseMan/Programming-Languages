import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        Marks m1 = new Marks(60, 60, 60);
        Marks m2 = new Marks(60, 80, 75);
        Marks m3 = new Marks(90, 98, 80);
        Marks m145 = new Marks(100, 100, 100);
        Marks m165 = new Marks(97, 100, 100);
        Marks m4433 = new Marks(100, 100, 100);
        Marks m1454 = new Marks(50, 60, 55);
        Marks m234 = new Marks(50, 45, 65);

        USEtable t = new USEtable();
        t.put(1, m1);
        t.put(2, m2);
        t.put(3, m3);
        t.put(145, m145);
        t.put(165, m165);
        t.put(4433, m4433);
        t.put(1454, m1454);
        t.put(234, m234);

        Map<Integer, List<Integer>> m = t.numbers(4, 65)
                //.collect(Collectors.groupingBy(Num::getLastDigit));
                .collect(Collectors.groupingBy(x -> x % 10));
        System.out.println(m);

        t.best().ifPresent(System.out::println);
    }
}
