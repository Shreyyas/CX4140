package Homework_1;

import java.awt.Color;

import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesMarker;
 

public class Plotting 
{
	public static final int START = 5;
	public static final int END   = 24;
	public static final int ORDER = 2;
	public static void main(String[] args) throws Exception {
		 
	    LinkedListTimeComplexity listData = new LinkedListTimeComplexity(START, END);	    
	    ArrayTimeComplexity analysis = new ArrayTimeComplexity(START, END, ORDER);		
		
	    System.out.println("STATE: [Starting Traversal Through List]");
	    double[] xData = listData.getInputs();
	    double[] yListData = listData.calculateListTraversalTime();
	    System.out.println("STATE: [List Traversal Is Now Complete!]");

	    System.out.println("STATE: [Starting Traversal Through Arrays]");
	    double[] ySequentialData = analysis.sequential();
	    double[] yStrideData = analysis.stride();
	    double[] yRandomData = analysis.random();
	    System.out.println("STATE: [Array Traversal Is Now Complete!]");

	    System.out.println("STATE: [Creating Charts]");
	    Chart chart = new Chart(1000, 1000);
	    chart.setChartTitle("Normalized Running Time of Linked-List and Array Traversal");
	    chart.setXAxisTitle("Number Of Nodes (Log Base 2)");
	    chart.setYAxisTitle("Access Time per Element (s)");

	    Series linkedListSeries = chart.addSeries("Linked List", xData, yListData);
	    linkedListSeries.setMarker(SeriesMarker.CIRCLE);
	    linkedListSeries.setLineColor(Color.BLUE);
	    
	    Series sequentialArraySeries = chart.addSeries("Sequential Array", xData, ySequentialData);
	    sequentialArraySeries.setMarker(SeriesMarker.DIAMOND);
	    sequentialArraySeries.setLineColor(Color.BLACK);
	    
	    Series strideArraySeries = chart.addSeries("Stride Array", xData, yStrideData);
	    strideArraySeries.setMarker(SeriesMarker.SQUARE);
	    strideArraySeries.setLineColor(Color.GREEN);
	    
	    Series randomArraySeries = chart.addSeries("Random Array", xData, yRandomData);
	    randomArraySeries.setMarker(SeriesMarker.TRIANGLE_UP);
	    randomArraySeries.setLineColor(Color.RED);
	    
	    System.out.println("STATE: [Saving to Image]");
	    BitmapEncoder.saveJPGWithQuality(chart, "C:\\Users\\ShreyyasV\\Desktop\\Traversal_Comparison.jpg", 0.95f);
	    System.out.println("STATE: [Complete!]");

	}

}
