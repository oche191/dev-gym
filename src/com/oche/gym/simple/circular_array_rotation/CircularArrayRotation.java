package com.oche.gym.simple.circular_array_rotation;

import java.util.Arrays;

/**
 * @author alex.chekanskyi
 */
public class CircularArrayRotation {

    public static void main(String[] args) {
        int[] arr = new int[] {0,1,2,3,4,5};
        int[] test = rotateArrayQuickly(arr, 7);
        System.out.println(Arrays.toString(test));

        int[] arr2 = new int[] {0,1};
        int[] test2 = rotateArrayQuickly(arr2, 4);
        System.out.println(Arrays.toString(test2));
    }

    /**
     * O(N) - Complexity
     *
     * Performs an operation called a right circular rotation on an array of integers,[a0, a1, ... aN-1].
     * After performing one right circular rotation operation, the array is transformed from  to [aN-1, a0, ... aN-2].
     * @param arr array to rotate
     * @param spinNum
     * @return rotated array
     */
    public static int[] rotateArrayQuickly(int[] arr, int spinNum) {
        if(spinNum <= 0) {
            return arr;
        }
        int[] res = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
            int futurePosition = getFuturePosition(i, arr.length, spinNum);
            res[futurePosition] = arr[i];
        }
        return res;
    }

    private static int getFuturePosition(int currentPos, int arrSize, int spinNum) {
        int positions = spinNum % arrSize;
        if(positions == 0) {
            return currentPos;
        }
        if(arrSize - positions > currentPos) {
            return currentPos + positions;
        } else {
            return 0 + (positions - (arrSize - currentPos));
        }
    }
}
