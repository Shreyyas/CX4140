package Problem_1_Revised;

import java.util.ArrayList;

import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesMarker;
 

public class Plotting 
{
	public static void main(String[] args) throws Exception {
		 
	    LinkedListComplexity_2 listData = new LinkedListComplexity_2();
	    ArrayList<Integer> xList = LinkedListComplexity_2.getInputs(5, 12);
	    ArrayList<Double> yList = listData.calculateListComplexity(5, 12);
	    
	    double[] xData = LinkedListComplexity_2.convertToIntegerArray(xList);
	    double[] yData = LinkedListComplexity_2.convertToDoubleArray(yList);

	    Chart chart = new Chart(500, 400);
	    chart.setChartTitle("Sample Chart");
	    chart.setXAxisTitle("X");
	    chart.setYAxisTitle("Y");
	    Series series = chart.addSeries("y(x)", xData, yData);
	    series.setMarker(SeriesMarker.CIRCLE);
	 
	    BitmapEncoder.saveJPGWithQuality(chart, "C:\\Users\\ShreyyasV\\Desktop\\Sample_Chart.jpg", 0.95f);
	  }

}
