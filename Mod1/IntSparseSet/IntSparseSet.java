/**
 * Created by vsevolodmolchanov on 27.03.17.
 */

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Objects;

public class IntSparseSet extends AbstractSet<Integer> {
    private int[] sparse, dense;
    private int n, low, high;

    public IntSparseSet(int low, int high) {
        sparse = new int[high - low + 1];
        dense = new int[high - low + 1];
        n = 0;
        this.low = low;
        this.high = high;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < n;
            }

            @Override
            public Integer next() {
                return dense[index++];
            }

            @Override
            public void remove() {
                IntSparseSet.this.remove(dense[index - 1]);
            }
        };
    }

    @Override
    public int size() {
        return n;
    }

    public boolean contains(Object x) {
        int number = (Integer)x;
        int index = number - low;
        return number >= low && number < high && 0 <= sparse[index] && sparse[index] < n && dense[sparse[index]] == number;
    }

    @Override
    public boolean add(Integer y) {
        if(y >= low && y < high && !this.contains(y)) {
            dense[n] = y;
            sparse[y - low] = n;
            n++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object z) {
        int number = (Integer)z;
        if(contains(z)) {
            n--;
            int index = number - low;
            dense[sparse[index]] = dense[n];
            sparse[dense[n] - low] = sparse[index];
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        n = 0;
    }
}