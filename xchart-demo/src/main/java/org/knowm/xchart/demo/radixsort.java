package org.knowm.xchart.demo;
import java.io.*;
import java.util.*;

public class radixsort {
    /*Radix Sort sorts digit by digit starting from
     * the least significant digit to the most significant digit. 
     * Radix sort uses counting sort as a "subroutine" to sort.
     * Radix sort is NOT an in-place algorithm
    */
    // Using counting sort to sort the elements in the basis of significant places
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

  // Driver code
  public static void main(String args[]) {
    int[] data = { 121, 432, 564, 23, 1, 45, 788 };
    int size = data.length;
    radixsort rs = new radixsort();
    rs.radixSort(data, size);
    System.out.println("Sorted Array in Ascending Order: ");
    System.out.println(Arrays.toString(data));
  }

    // static int getMax(int arr[], int n){
    //     /// this pulls the max value in the arr[]
    //     int maxX = arr[0];
    //     for(int i = 1; i <n; i++)
    //     if(arr[i]> maxX)
    //         maxX = arr[i];
    //     return maxX;
    // }
    // // a function to do a counting sort of arr[] according
    // // to the digist represented by the exponent
    // static void countingSort(int arr[], int n, int exp){
    //     int output[] = new int[n];
    //     int i;
    //     int countArray[] = new int[10];
    //     Arrays.fill(countArray, 0);

    //     // store number of occurances of countArray
    //     for(i=0; i<n; i++)
    //     countArray[(arr[i]/exp) % 10]++;

    //     // Build the array (for length 10)
    //     for (i = 1; i < 10; i++)
    //     countArray[i] += countArray[i-1];

    //     //Build the output array
    //     for (i = n - 1; i >= 0; i--)
    //         output[countArray[(arr[i] / exp) % 10] - 1] = arr[i];
    //         countArray[(arr[i] / exp) % 10]--;        
    //     // count the output array so that arr[] now contains sorted numbers according to current digit
    //     for(i = 0; i <n; i++)
    //     arr[i] = output[i];
    // }

    // // main function to sort arr[] of size n
    // static void radixSort(int arr[], int n){
    //     int m = getMax(arr, n);
    //     for(int exp = 1; m /exp > 0; exp *= 10)
    //     countingSort(arr, n, exp);

    // }

    // static void print(int arr[], int n)
    // {
    //     for (int i = 0; i < n; i++)
    //         System.out.print(arr[i] + " ");
    // }


    //    // Main driver method
    //    public static void main(String[] args)
    //    {
    //        int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
    //        int n = arr.length;
    
    //        // Function Call
    //        radixSort(arr, n);
    //        print(arr, n);
    //    }
   }
