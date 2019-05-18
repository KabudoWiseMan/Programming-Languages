
/**
 * Created by vsevolodmolchanov on 28.02.17.
 */

public class Sentence implements Comparable<Sentence> {
    private String s;
    private int maxLengthWord;

    public Sentence(String s) {
        this.s = s;
        maxLengthWord = getWordWithMaxLength(s);
    }

    public String toString() {
        return s;
    }

    private static int getWordWithMaxLength(String s) {
        int max = 0;
        String[] words = s.split(" ");
        for(String word : words) {
            if(word.length() > max) {
                max = word.length();
            }
        }
        return max;
    }

    public int getMaxLengthWord() {
        return maxLengthWord;
    }

    public int compareTo(Sentence obj) {
        return obj.getMaxLengthWord() - getWordWithMaxLength(s);
    }
}
