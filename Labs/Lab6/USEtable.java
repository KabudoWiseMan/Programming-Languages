import java.util.*;
import java.util.stream.Stream;

public class USEtable {
    private Hashtable<Integer, Marks> table;

    public USEtable() {
        table = new Hashtable<>();
    }

    public USEtable(Hashtable<Integer, Marks> table) {
        this.table = table;
    }

    public void put(int k, Marks m) {
        table.put(k, m);
    }

    public Marks get(int k) {
        return table.get(k);
    }

    public void remove(int k) {
        table.remove(k);
    }

    public void clear() {
        table.clear();
    }

    public boolean contains(Marks m) {
        return table.contains(m);
    }

    public boolean containsKey(int k) {
        return table.containsKey(k);
    }

    public Object clone() {
        return table.clone();
    }

    public Enumeration<Marks> elements() {
        return table.elements();
    }

    public Set<Map.Entry<Integer, Marks>> entrySet() {
        return table.entrySet();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public Enumeration<Integer> keys() {
        return table.keys();
    }

    public void putAll(Map<? extends Integer,? extends Marks> t) {
        table.putAll(t);
    }

    public int size() {
        return table.size();
    }

    public Collection<Marks> values() {
        return table.values();
    }

    /*public Stream<Num> numbers(int n, int s) {
        return table.entrySet().stream()
                .filter(t -> t.getValue().sum() > s)
                .sorted(Comparator.comparingInt(o -> o.getValue().sum()))
                //.sorted((o1, o2) -> Integer.compare(o2.getValue().sum(), o1.getValue().sum()))
                .limit(n)
                .map(map -> new Num(map.getKey()));
    }*/

    public Stream<Integer> numbers(int n, int s) {
        return table.entrySet().stream()
                .filter(t -> t.getValue().sum() > s)
                .sorted((o1, o2) -> Integer.compare(o2.getValue().sum(), o1.getValue().sum()))
                .limit(n)
                .map(Map.Entry::getKey);
    }

    public Optional<Integer> best() {
        int maxMath = table.entrySet().stream()
                .max((t1, t2) -> Integer.compare(t1.getValue().getMath(), t2.getValue().getMath())).get().getKey();
        int maxComp = table.entrySet().stream()
                .max((t1, t2) -> Integer.compare(t1.getValue().getComp(), t2.getValue().getComp())).get().getKey();
        int maxRus = table.entrySet().stream()
                .max((t1, t2) -> Integer.compare(t1.getValue().getRus(), t2.getValue().getRus())).get().getKey();
        long repeat = table.entrySet().stream()
                .filter(t -> table.get(maxMath).equals(t.getValue())).count();
        if (maxMath == maxComp && maxMath == maxRus && repeat < 2) {
            return Optional.ofNullable(maxMath);
        }
        System.out.println("Не существует абитуриента, баллы которого по каждому ЕГЭ превышают баллы любого другого абитуриента по этому ЕГЭ");
        return Optional.empty();
    }
}
