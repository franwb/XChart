package org.knowm.xchart.demo;
import java.util.*;
import java.util.List;
import java.awt.*;  
import javax.swing.*;  
import java.awt.geom.*;  

import java.io.IOException;


import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
//import org.knowm.xchart;

public class sortingComparison {
    
    public static void main(String[] args) throws IOException{
        // sortingComparison sortComp = new sortingComparison();
        int[] arr = generateRandomArray(1000000, 0, 10000000);
        // int x = measureSortTime(arr, "Quick Sort", quickSort(arr, arr[0], arr[(arr.length-1)]));
        double x = 1000000;
        // int x2 = measureSortTime(arr, "Radix Sort", countingSort(arr, arr[0], arr[(arr.length-1)]));
        // int y2 = 10;
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<arr.length;i++)
        {
            list.add(arr[i]);
        }
        // int x3 = measureSortTime(arr, "Radix Sort", bucketSort(arr, (Collections.max(list)-Collections.min(list))/arr.length));
        // int y3 = 10;


        double quickSortTime = measureSortTime(arr, "Quick Sort", (a) -> quickSort(a, 0, a.length - 1));
        double radixSortTime = measureSortTime(arr, "Radix Sort", (a) -> radixSort(a, 10));
        double bucketSortTime = measureSortTime(arr, "Bucket Sort", (a) -> bucketSort(a, (Collections.max(list)-Collections.min(list))/arr.length));



        double[] xData = new double[] {x};
        double[] yData = new double[] {quickSortTime};

        double[] xData2 = new double[] {x};
        double[] yData2 = new double[] {radixSortTime};

        double[] xData3 = new double[] {x};
        double[] yData3 = new double[] {bucketSortTime};

        // double[] xData2 = new double[] { 0.0, 1.0, 2.0 };
        // double[] yData2 = new double[] { 1.0, 2.0, 0.0 };

        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        // add other line to graph
        chart.addSeries("Series 2", xData2, yData2);
        chart.addSeries("Series 3", xData3, yData3);


        // Show it
        new SwingWrapper(chart).displayChart();

        // Save it
        BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);
        
        //CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Sorting Comparison").xAxisTitle("Algorithm").yAxisTitle("Time (ms)").build();
        //chart.addSeries("Sort Times", new String[] {"Quick Sort", "Radix Sort", "Bucket Sort"}, new Long[] {quickSortTime, radixSortTime, bucketSortTime});
        
        //new SwingWorker<>(chart).displayChart();

        // add some lines here to test and probabaly print each of the different sort methods
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
    
   
      // Bucketsort implementation

    // public static void swap(int[] arr, int i, int j) {
    //     int temp = arr[i];
    //     arr[i] = arr[j];
    //     arr[j] = temp;
    // }
    // (maxValue - minValue) / n
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

