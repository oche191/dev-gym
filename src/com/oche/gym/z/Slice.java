package com.oche.gym.z;

/**
 * @author alex.chekanskyi
 */
public class Slice {

    public static void main(String[] args) {
        Slice sl = new Slice();
        int[] arr1 = new int[] {5, 4, -3, 2, 0, 1, -1, 0, 2, -3, 4, -5};
        int[] arr2 = new int[] {1, 2, 3};
        System.out.println(sl.solution(arr1));
        System.out.println(sl.solution(arr2));
    }
    public int solution(int[] A) {
        int negative = A[0] <=0 ? 1 : 0;
        int positive = A[0] >=0 ? 1 : 0;

        int result = 1 ;
        for(int i=1; i<A.length; i++) {
            if (A[i] < 0) {
                negative = positive + 1;
                positive = 0;
            } else if (A[i] > 0) {
                positive = negative +1;
                negative = 0;
            } else {
                int prevMaxP = positive;
                positive = negative + 1;
                negative = prevMaxP + 1;
            }
            result = Math.max(result, Math.max(negative, positive));
        }
        return result;
    }
}
