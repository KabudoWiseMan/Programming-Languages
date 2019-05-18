/**
 * Created by vsevolodmolchanov on 14.02.17.
 */
import java.util.Arrays;
import java.lang.Math;

public class Set {
    private int[] set;
    private int length;

    public Set(int[] numbers) {
        int[] almostSet = new int[numbers.length];
        Arrays.sort(numbers);
        length = 0;
        for(int i = 0; i <= numbers.length - 1; i++) {
            if(i == numbers.length - 1) {
                almostSet[length] = numbers[i];
                length++;
            }
            else if(numbers[i] != numbers[i + 1]) {
                almostSet[length] = numbers[i];
                length++;
            }
        }

        set = new int[length];
        for(int i = 0; i <= length - 1; i++) {
            set[i] = almostSet[i];
        }
    }

    public static Set makeIntersection(Set a, Set b) {
        int[] newSet = new int[Math.min(a.length, b.length)];
        for(int i = 0; i <= a.length - 1; i++) {
            for(int j = 0; j <= b.length - 1; j++) {
                if(a.set[i] == b.set[j]) {
                    newSet[i] = a.set[i];
                    continue;
                }
            }
        }
        Set set = new Set(newSet);
        return set;
    }

    public String toString() {
        String str = "{";
        for(int i = 0; i <= set.length - 1; i++){
            str += set[i];
            if(i != set.length - 1) str += ", ";
        }
        str += "}";
        return str;
    }
}
