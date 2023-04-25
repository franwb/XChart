package org.knowm.xchart.demo;

import java.util.*;

public class bucketsort {
    // int bucket[];
/* Algorithm for BucketSort
bucketSort(arr[], n)
1) Create n empty buckets (Or lists).
2) Do following for every array element arr[i].
.......a) Insert arr[i] into bucket[n*array[i]]
3) Sort individual buckets using insertion sort.
4) Concatenate all sorted buckets.*/

    public static void bucketSort(int[] array, int bucketSize) {
        if (array.length == 0) {
            return;
        }

        // max and min values of array
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        // make buckets
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // put input values into buckets
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (array[i] - minValue) / bucketSize;
            buckets.get(bucketIndex).add(array[i]);
        }

        // sort buckets and put back in input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            List<Integer> bucket = buckets.get(i);
            Collections.sort(bucket);
            for (int j = 0; j < bucket.size(); j++) {
                array[currentIndex++] = bucket.get(j);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int bucketSize = 10;
        bucketSort(arr, bucketSize);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}





