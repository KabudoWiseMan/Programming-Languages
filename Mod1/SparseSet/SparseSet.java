/**
 * Created by vsevolodmolchanov on 07.04.17.
 */

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

public class SparseSet<T extends Hintable> extends AbstractSet<T> {
    private int n = 0;
    private ArrayList<T> dense;

    public SparseSet() {
        this.dense = new ArrayList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < n;
            }

            @Override
            public T next() {
                return dense.get(index++);
            }

            @Override
            public void remove() {
                SparseSet.this.remove(dense.get(index - 1));
            }
        };
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean contains(Object x) {
        return n != 0 && dense.get(((T) x).hint()) == x;
    }

    @Override
    public boolean add(T x) {
        if(!contains(x)) {
            dense.add(x);
            x.setHint(n++);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object x) {
        if(contains(x)) {
            n--;
            dense.set(((T)x).hint(), dense.get(n));
            dense.get(n).setHint(((T)x).hint());
            return true;
        }
        return false;
    }

    @Override
    public void clear(){
        n = 0;
    }
}
