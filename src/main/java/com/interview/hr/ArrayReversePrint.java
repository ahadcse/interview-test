package com.interview.hr;

import java.util.Scanner;

public class ArrayReversePrint {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i = 0; i < n; i++) {
            ar[i] = in.nextInt();
        }
        for(int j = n-1 ; j >= 0; j--) {
            System.out.print(ar[j] + " ");
        }
    }
}
