package com.interview.hr;

import java.util.Scanner;

public class BirthdayCakeCandles {
    static int birthdayCakeCandles(int n, int[] ar) {
        long max = 0;
        if(n > 0) {
            for(int i = 0; i < ar.length; i++) {
                if(max < ar[i]) {
                    max = ar[i];
                }
            }
            int count = 1;
            for(int j = 0; j < ar.length; j++) {
                if(max == ar[j]) {
                    count++;
                }
            }
            return count - 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int ar_i = 0; ar_i < n; ar_i++){
            ar[ar_i] = in.nextInt();
        }
        int result = birthdayCakeCandles(n, ar);
        System.out.println(result);
    }
}
