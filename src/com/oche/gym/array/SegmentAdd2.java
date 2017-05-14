package com.oche.gym.array;

import java.util.Scanner;

/**
 * @author alex.chekanskyi
 */
public class SegmentAdd2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        long times = sc.nextLong();

        long[] a = new long[size+1];
        for(int i=0; i<times; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int sum = sc.nextInt();
            a[from] += sum;
            if(to + 1 <= size) {
                a[to+1] -= sum;
            }
        }
        long x = 0;
        long max = 0;
        for (int i=1; i<=size; i++) {
            x = x+a[i];
            if(x > max) {
                max = x;
            }
        }
        System.out.println(max);
    }
}
