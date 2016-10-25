package com.oche.gym.sorting.insertion;

import java.util.Arrays;

/**
 * Created by ache on 25.10.16.
 */
public class InsertionSorting {
    public static void main(String[] args) {
        int[] a = new int[] {2, 4, 6, 8, 3};
        insertIntoSorted(a);
    }

    public static void insertIntoSorted(int[] a) {
        for(int i=a.length-1; i>1;i--) {

            if(a[i] < a[i-1]) {
                int temp = a[i-1];
                a[i-1] = a[i];
                a[i] = temp;
                System.out.print(Arrays.toString(a));
            }
        }
    }
}
