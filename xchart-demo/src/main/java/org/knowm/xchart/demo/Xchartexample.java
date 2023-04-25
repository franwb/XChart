package org.knowm.xchart.demo;

import java.io.IOException;


import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;



public class Xchartexample{

// I (Francesca) wrote this class to see if we could properly use XChart. Sorry thr name is so similar to many other classes.
    public static void main(String[] args) throws IOException{
        double[] xData = new double[] { 0.0, 1.0, 2.0 };
        double[] yData = new double[] { 2.0, 3.0, 0.0 };

        double[] xData2 = new double[] { 0.0, 1.0, 2.0 };
        double[] yData2 = new double[] { 1.0, 2.0, 0.0 };

        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        // add other line to graph
        chart.addSeries("Series 2", xData2, yData2);

        // Show it
        new SwingWrapper(chart).displayChart();

        // Save it
        BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);
        
    }
}