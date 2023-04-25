package org.knowm.xchart.demo;
import java.util.*;
import java.awt.*;  
import javax.swing.*;  
import java.awt.geom.*;  
//import org.knowm.xchart;

public class sortingComparison {
    
    public static void main(String[] args) {
        int[] arr = generateRandomArray(10000, 0, 100000);
        
        //long quickSortTime = measureSortTime(arr, "Quick Sort", (a) -> quickSort(a, 0, a.length - 1));
        //long radixSortTime = measureSortTime(arr, "Radix Sort", (a) -> radixSort(a));
        //long bucketSortTime = measureSortTime(arr, "Bucket Sort", (a) -> bucketSort(a, 100));
        
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
    void countingSort(int array[], int size, int place) {
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
      int getMax(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < n; i++)
          if (array[i] > max)
            max = array[i];
        return max;
      }
    
      // Main function to implement radix sort
      void radixSort(int array[], int size) {
        // Get maximum element
        int max = getMax(array, size);
    
        // Apply counting sort to sort elements based on place value.
        for (int place = 1; max / place > 0; place *= 10)
          countingSort(array, size, place);
      }
    
   
      // Bucketsort implementation

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    interface SortFunction {
        void sort(int[] arr);
    }
}

