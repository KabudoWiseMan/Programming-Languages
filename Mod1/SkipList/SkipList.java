/**
 * Created by vsevolodmolchanov on 28.03.17.
 */

import java.util.*;

public class SkipList<K extends Comparable<K>,V> extends AbstractMap<K,V> {
    private Elem l;
    private int levels;
    private Random rand;
    private int size;

    private class Elem {
        private ArrayList<Elem> next;
        private K key;
        private V value;

        Elem() {
            next = new ArrayList<Elem>(levels) {{
                for (int i = 0; i < levels; i++) add(null);
            }};
        }

        Elem(K key, V value) {
            next = new ArrayList<>(levels);
            this.key = key;
            this.value = value;
        }

        public ArrayList<Elem> getNext() {
            return next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public SkipList(int levels) {
        this.levels = levels;
        l = new Elem();
        rand = new Random();
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return l.getNext().get(0) == null;
    }

    private void skip(K key, ArrayList<Elem> p) {
        Elem x = l;
        for (int i = 0; i <= levels - 1; i++) {
            p.add(null);
        }
        for (int i = levels - 1; i >= 0; i--) {
            while (x.getNext().get(i) != null && x.getNext().get(i).getKey().compareTo(key) < 0) {
                x = x.getNext().get(i);
            }
            p.set(i, x);
        }
    }

    @Override
    public void clear() {
        size = 0;
        Elem x = l;
        while (x.getNext().get(0) != null) {
            Elem temp = x.getNext().get(0);
            for (int i = 0; i <= x.getNext().size() - 1; i++) {
                x.getNext().set(i, null);
            }
            x = temp;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        ArrayList<Elem> p = new ArrayList<>(levels);
        skip((K) key, p);
        Elem x = p.get(0).getNext().get(0);
        return x != null && x.getKey().equals(key);
    }

    @Override
    public V get(Object key) {
        ArrayList<Elem> p = new ArrayList<>(levels);
        skip((K) key, p);
        Elem x = p.get(0).getNext().get(0);
        if (x == null || !x.getKey().equals(key)) {
            return null;
        }
        return x.getValue();
    }

    @Override
    public V put(K key, V value) {
        ArrayList<Elem> p = new ArrayList<>(levels);
        skip(key, p);
        if (p.get(0).getNext().get(0) != null && p.get(0).getNext().get(0).getKey().equals(key)) {
            V temp = p.get(0).getNext().get(0).getValue();
            p.get(0).getNext().get(0).setValue(value);
            return temp;
        }
        Elem x = new Elem(key, value);
        int r = rand.nextInt() * 2, i;
        for (i = 0; i <= levels - 1 && r % 2 == 0; i++) {
            x.getNext().add(p.get(i).getNext().get(i));
            p.get(i).getNext().set(i, x);
            r /= 2;
        }
        for (; i <= levels - 1; i++) {
            x.getNext().add(null);
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        ArrayList<Elem> p = new ArrayList<>(levels);
        skip((K) key, p);
        Elem x = p.get(0).getNext().get(0);
        if (x == null || !x.getKey().equals(key)) {
            return null;
        }
        for (int i = 0; i <= levels - 1 && p.get(i).getNext().get(i) == x; i++) {
            p.get(i).getNext().set(i, x.getNext().get(i));
        }
        size--;
        return x.getValue();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<Entry<K, V>>() {
                    private Elem currentElem = l;

                    @Override
                    public boolean hasNext() {
                        return currentElem.getNext().get(0) != null;
                    }

                    @Override
                    public Entry<K, V> next() {
                        currentElem = currentElem.getNext().get(0);
                        class skipListEntry extends SimpleEntry<K, V> {

                            private skipListEntry(K key, V value) {
                                super(key, value);
                            }

                            @Override
                            public V setValue(V value) {
                                put(getKey(), value);
                                return value;
                            }
                        }
                        return new skipListEntry(currentElem.getKey(), currentElem.getValue());
                    }

                    @Override
                    public void remove() {
                        SkipList.this.remove(currentElem.getKey());
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }
}