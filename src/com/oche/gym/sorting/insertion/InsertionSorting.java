package com.oche.gym.sorting.insertion;

import java.util.Arrays;

/**
 * Created by ache on 25.10.16.
 */
public class InsertionSorting {

    public static void main(String[] args) {
        int[] abc = new int[]{10, 9, 8, 7, 5, 6, 4, 1, 2, 3, 3};
        insertionSort(abc);
        System.out.println(Arrays.toString(abc));
    }

    public static void insertionSort(int[] a) {
        for(int i=1; i<a.length; i++){
            insertIntoSortedWithStartPosition(a, i);
        }
    }

    /**
     *
     * @param a array which is already partially sorted
     * @param position position of a first unsorted entry
     */
    public static void insertIntoSortedWithStartPosition(int[] a, int position) {
        int unsortedVal = a[position];
        for(int i=position; i>0;i--) {
            if(a[i-1] > unsortedVal) {
                a[i] = a[i-1];
            } else {
                a[i] = unsortedVal;
                break;
            }
            if(i == 1) {
                a[0] = unsortedVal;
            }
        }
    }
}
