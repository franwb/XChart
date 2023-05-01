package org.knowm.xchart.demo;
import java.util.*;
import java.util.List;
import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

// Created by Francesca Brown and Cecelia Kaufmann
// Credit to XChart Library for allowing us to plot our data 

public class sortingComparison {
    
    public static void main(String[] args) throws IOException{
        int n = 6;

        double[] xDataQ = new double[n];
        double[] yDataQ = new double[n];

        double[] xDataR = new double[n];
        double[] yDataR = new double[n];

        double[] xDataB = new double[n];
        double[] yDataB = new double[n];

        for(int i = 1; i < n+1; i++){
            int[] arr = generateRandomArray((int)Math.pow(10, i), 0, (int)Math.pow(10, i+1));
            List<Integer> list=new ArrayList<>();
                for(int j=0;j<arr.length;j++){
                    list.add(arr[j]);
                }
            double quickSortTime = measureSortTime(arr, "Quick Sort", (a) -> quickSort(a, 0, a.length - 1));
            double radixSortTime = measureSortTime(arr, "Radix Sort", (a) -> radixSort(a, 10));
            double bucketSortTime = measureSortTime(arr, "Bucket Sort", (a) -> bucketSort(a, (Collections.max(list)-Collections.min(list))/arr.length));

            xDataQ[i-1] = (int)Math.pow(10, i);
            xDataR[i-1] = (int)Math.pow(10, i);
            xDataB[i-1] = (int)Math.pow(10, i);

            yDataQ[i-1] = quickSortTime;
            yDataR[i-1] = radixSortTime;
            yDataB[i-1] = bucketSortTime;

        }
  
        XYChart chart = QuickChart.getChart("Plotting Run Times", "Array Size", "Sort Time (ms)", "Quick Sort", xDataQ, yDataQ);
        // add other line to graph

        chart.addSeries("Radix Sort", xDataR, yDataR);
        chart.addSeries("Bucket Sort", xDataB, yDataB);

        // Show it
        new SwingWrapper(chart).displayChart();

        // Save it
        BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);
    }
    
    public static long measureSortTime(int[] arr, String sortName, SortFunction sortFunction) {
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        long startTime = System.currentTimeMillis();
        sortFunction.sort(arrCopy);
        long endTime = System.currentTimeMillis();
        System.out.println(sortName + " took " + (endTime - startTime) + " ms");
        return endTime - startTime;
    }
    
    public static int[] generateRandomArray(int size, int minVal, int maxVal) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxVal - minVal + 1) + minVal;
        }
        return arr;
    }
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int low, int high) {
        int pivotValue = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivotValue) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    
    
    // Radixsort implementation
    static void countingSort(int array[], int size, int place) {
        int[] output = new int[size + 1];
        int max = array[0];
        for (int i = 1; i < size; i++) {
          if (array[i] > max)
            max = array[i];
        }
        int[] count = new int[max + 1];
    
        for (int i = 0; i < max; ++i)
          count[i] = 0;
    
        // Calculate count of elements
        for (int i = 0; i < size; i++)
          count[(array[i] / place) % 10]++;
    
        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
          count[i] += count[i - 1];
    
        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--) {
          output[count[(array[i] / place) % 10] - 1] = array[i];
          count[(array[i] / place) % 10]--;
        }
    
        for (int i = 0; i < size; i++)
          array[i] = output[i];
      }
    
      // Function to get the largest element from an array
        static int getMax(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < n; i++)
          if (array[i] > max)
            max = array[i];
        return max;
      }
    
      // Main function to implement radix sort
      static void radixSort(int array[], int size) {
        // Get maximum element
        int max = getMax(array, size);
    
        // Apply counting sort to sort elements based on place value.
        for (int place = 1; max / place > 0; place *= 10)
          countingSort(array, size, place);
      }
    
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
    


    interface SortFunction {
        void sort(int[] arr);
    }
}

