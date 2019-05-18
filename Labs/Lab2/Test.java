/**
 * Created by vsevolodmolchanov on 14.02.17.
 */
public class Test {
    public static void main(String[] args) {
        int[] numbers0 = new int[] {};
        int[] numbers1 = new int[] {1, 1, 1};
        int[] numbers2 = new int[] {1, 2, 3};
        int[] numbers3 = new int[] {1, 2, 2, 3, 4, 5, 6, 6, 6, 7};
        int[] numbers4 = new int[] {5, 2, 4, 3, 1};
        Set s0 = new Set(numbers0);
        Set s1 = new Set(numbers1);
        Set s2 = new Set(numbers2);
        Set s3 = new Set(numbers3);
        Set s4 = new Set(numbers4);
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println();

        Set i4 = Set.makeIntersection(s0, s1);
        Set i5 = Set.makeIntersection(s1, s2);
        Set i6 = Set.makeIntersection(s2, s3);
        Set i7 = Set.makeIntersection(s3, s4);
        System.out.println(i4);
        System.out.println(i5);
        System.out.println(i6);
        System.out.println(i7);
    }
}
