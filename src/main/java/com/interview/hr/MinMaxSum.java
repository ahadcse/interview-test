package com.interview.hr;

import java.util.Scanner;

public class MinMaxSum {

    static void miniMaxSum(int[] arr) {
        long max = 0;
        long min = Long.MAX_VALUE;
        long sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
            if (min > arr[i]) {
                min = arr[i];
            }
            sum += arr[i];
        }
        System.out.println((sum - max) + " " + (sum - min));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[5];
        for (int arr_i = 0; arr_i < 5; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        miniMaxSum(arr);
        in.close();
    }
}