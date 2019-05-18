/**
 * Created by vsevolodmolchanov on 21.03.17.
 */

import java.util.Iterator;

public class Sentence implements Iterable<String> {
    private String s;
    private int length;
    private String[] couples;

    public Sentence(String s) {
        this.s = s;
        String[] words = s.split("\\W+");
        for(int i = 0; i <= words.length - 1; i++) {
            if(words[i].length() > 1) {
                length += words[i].length() - 1;
            }
        }
        couples = new String[length];
        int k = 0;
        for(int i = 0; i <= words.length - 1; i++) {
            if(words[i].length() > 1) {
                for(int j = 0; j <= words[i].length() - 2; j++) {
                    couples[k++] = words[i].substring(j, j + 2);
                }
            }
        }
    }

    public Iterator<String> iterator() {
        return new LettersIterator();
    }

    private class LettersIterator implements Iterator<String> {
        private int pos;

        public LettersIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < length;
        }

        public String next() {
            return couples[pos++];
        }
    }
}
