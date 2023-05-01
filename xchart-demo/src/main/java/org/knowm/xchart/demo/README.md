Authors: Cecelia Kaufmann and Francesca Brown
Our project: Our project compares QuickSort, BucketSort, and RadixSort algorithms
to see which of the different sorts have the quickest runtime across arrays of various 
sizes. Then, we used Xchart (a graphing download) to visually respresent the runtime
comparisons (Github link below). 

https://github.com/knowm/XChart 

These are the libraries are included to run our code. The code needs to be run in this folder/Github repository
for the Xchart outputs to work. Here are the packages/ libraries needed to be included: 

package org.knowm.xchart.demo;
import java.util.*;
import java.util.List;
import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

In order to run the code, go into the sortingComparison class just below this README and run the main method. 
You will see the following line of code at the top:

int n = 6;

The main method will time and plot each sorting algorithm on arrays size 10 ^ (1 through n). In this case, n would be 6. To change this, you can change the value of int n = 6 to whichever value you desire.