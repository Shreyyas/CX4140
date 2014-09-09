package Problem_1_Revised;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesMarker;
 

public class Plotting 
{
	public static void main(String[] args) throws Exception {
		 
	    LinkedListComplexity_2 listData = new LinkedListComplexity_2();
	    ArrayList<Integer> xList = LinkedListComplexity_2.getInputs(5, 24);
	    ArrayList<Double> yList = listData.calculateListComplexity(5, 24);
	    
	    double[] xData = LinkedListComplexity_2.convertToIntegerArray(xList);
	    double[] yListData = LinkedListComplexity_2.convertToDoubleArray(yList);
	    
	    Chart chart = new Chart(500, 400);
	    chart.setChartTitle("Normalized Running Time of Linked-List and Array Traversal");
	    chart.setXAxisTitle("Number Of Nodes (Log Base 2)");
	    chart.setYAxisTitle("Access Time per Element (s)");

	    Series linkedListSeries = chart.addSeries("Linked List", xData, yListData);
	    linkedListSeries.setMarker(SeriesMarker.CIRCLE);
	    linkedListSeries.setLineColor(Color.BLUE);
	    
	    BitmapEncoder.saveJPGWithQuality(chart, "C:\\Users\\ShreyyasV\\Desktop\\Traversal_Comparison.jpg", 0.95f);
	  }

}
