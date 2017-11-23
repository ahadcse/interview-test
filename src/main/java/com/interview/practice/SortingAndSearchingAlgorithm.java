package com.interview.practice;

import java.util.Arrays;

public class SortingAndSearchingAlgorithm {

    public static int[] input = {1, 5, 9, 3, 7, 2, 1, 8};

    public static void main(String args[]) {
        System.out.println("Insertion sort: ");
        insertionSort(Arrays.copyOf(input, 8));

        System.out.println("Bubble sort: ");
        bubbleSort(Arrays.copyOf(input, 8));

        System.out.println("Selection sort: ");
        selectionSort(Arrays.copyOf(input, 8));

        System.out.println("Quick sort: ");
        quickSort(Arrays.copyOf(input, 8));

        System.out.println("Merge sort: ");
        mergeSort(Arrays.copyOf(input, 8));

        System.out.println("Binary search D&C (The position of the item): "
                + binarySearchDivideAndConquer(selectionSort(Arrays.copyOf(input, 8)), 7));
        System.out.println("Binary search Rec (The position of the item): "
                + binarySearchRecursive(selectionSort(Arrays.copyOf(input, 8)), 7));
    }

    private static int[] insertionSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    swapNumbers(j, j - 1, input);
                }
            }
            printArray(input);
        }
        return input;
    }

    private static int[] bubbleSort(int input[]) {
        int n = input.length;
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (input[i] > input[k]) {
                    swapNumbers(i, k, input);
                }
            }
            printArray(input);
        }
        return input;
    }

    private static int[] selectionSort(int input[]) {
        for(int i = 0; i < input.length - 1; i++) {
            int index = i;
            for(int j = i+1; j < input.length; j++) {
                if(input[j] < input[index]) {
                    index = j;
                }
            }
            int smallerNumber = input[index];
            input[index] = input[i];
            input[i] = smallerNumber;
            printArray(input);
        }
        return input;
    }

    private static void quickSort(int[] input) {
        quickSort(input, 0, input.length - 1);
    }

    private static void quickSort(int[] input, int left, int right) {
        while(left >= right) {
            return;
        }
        int pivot = input[(left+right)/2];
        int index = partition(input, left, right, pivot);
        quickSort(input, left, index - 1);
        quickSort(input, index, right);
        printArray(input);
    }

    private static  void mergeSort(int[] input) {
        mergeSort(input, new int[input.length], 0, input.length - 1);
    }

    private static void mergeSort(int[] input, int[] temp, int leftStart, int rightEnd) {
        if(leftStart >= rightEnd) {
            return;
        }
        int middle = (leftStart + rightEnd) / 2;
        mergeSort(input, temp, leftStart, middle);
        mergeSort(input, temp, middle + 1, rightEnd);
        mergeHalves(input, temp, leftStart, rightEnd);
        printArray(input);
    }

    private static void mergeHalves(int[] input, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while (left <= leftEnd && right <= rightEnd) {
            if(input[left] < input[right]) {
                temp[index] = input[left];
                left++;
            } else {
                temp[index] = input[right];
                right++;
            }
            index++;
        }

        System.arraycopy(input, left, temp, index, leftEnd - left + 1);
        System.arraycopy(input, right, temp, index, rightEnd - right + 1);
        System.arraycopy(temp, leftStart, input, leftStart, size);
    }

    // Binary search with Divide and Conquer. Returns item position or index in array.
    private static int binarySearchDivideAndConquer(int[] input, int item2Search) {
        int start = 0;
        int end  = input.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if(item2Search == input[mid]) {
                return mid;
            }
            if(item2Search < input[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // Binary Search Recursive.
    private static int binarySearchRecursive(int[] input, int item2Search) {
        return binarySearchRecursive(input, 0, input.length - 1, 7);
    }
    private static int binarySearchRecursive(int[] input, int start, int end, int item2Search) {
        if(start < end) {
            int mid = start + (end - start) / 2;
            if(item2Search < input[mid]) {
                return binarySearchRecursive(input, start, mid, item2Search);
            } else if( item2Search > input[mid]) {
                return binarySearchRecursive(input, mid + 1, end, item2Search);
            } else {
                return mid;
            }
        }
        return -(start + 1);
    }

    private static int partition(int[] input, int left, int right, int pivot) {
        while (left <= right) {
            while (input[left] < pivot) {
                left++;
            }
            while (input[right] > pivot) {
                right--;
            }
            while (left <= right) {
                swapNumbers(left, right, input);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swapNumbers(int i, int j, int[] array) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void printArray(int[] arrayToPrint) {
        for (int i : arrayToPrint) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}