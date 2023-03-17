package com.pack;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataPlotter {

    public static void mainn( double[][] dataset,String clr) {
        // Initialize dataset
        //double[][] dataset = {{1,2}, {3,4}, {5,6}, {7,8}, {9,10}};

        // Create XYSeries object
        XYSeries series = new XYSeries("Dataset");

        for (int i = 0; i < dataset.length; i++) {
            double[] point = dataset[i];
            series.add(point[0], point[1]);
        }

        // Add series to collection
        XYSeriesCollection datasetCollection = new XYSeriesCollection();
        datasetCollection.addSeries(series);

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot("Dataset Plot", "X", "Y", datasetCollection, PlotOrientation.VERTICAL, true, true, false);

        // Set plot properties
        XYPlot plot = chart.getXYPlot();
        Shape shape = new Ellipse2D.Double(-2.5, -2.5, 5, 5);
        plot.getRenderer().setSeriesShape(0, shape);
        
        switch (clr) {
		case "blue": {
			
			 plot.getRenderer().setSeriesPaint(0, Color.blue);
		}case "green": {
			
			 plot.getRenderer().setSeriesPaint(0, Color.green);
		}case "red": {
			
			 plot.getRenderer().setSeriesPaint(0, Color.red);
		}
		default:
			  plot.getRenderer().setSeriesPaint(0, Color.black);
		}
   
        // Create chart panel
        ChartPanel panel = new ChartPanel(chart);

        // Create frame and add chart panel
        JFrame frame = new JFrame("Dataset Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
