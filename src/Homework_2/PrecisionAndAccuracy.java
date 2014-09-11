package Homework_2;

import java.util.ArrayList;
/**
 * Determines accuracy and precision by running each traversal TRIALS number of times.
 * @author ShreyyasV
 *
 */
public class PrecisionAndAccuracy 
{
	public static int START  = 5;
	public static int END    = 15;
	public static int ORDER  = 2;
	public static int TRIALS = 50;

	/**
	 * Prints out the averages for running times to predict precision and accuracy
	 * @param args
	 */
	public static void main(String[] args) 
	{
		LinkedListTimeComplexity listData = null; 
	    ArrayTimeComplexity arrayData = null;  
	    
	    double listSum = 0;
	    double sequentialSum = 0;
	    double strideSum = 0;
	    double randomSum = 0;
	    
	    ArrayList<Double> list = new ArrayList<Double>();
	    ArrayList<Double> sequential = new ArrayList<Double>();
	    ArrayList<Double> stride = new ArrayList<Double>();
	    ArrayList<Double> random = new ArrayList<Double>();

	    double[] timesArray = new double[TRIALS];
	    
	    /*Theoretical derivation of values. Calculates average of n number of Trials
	     * per element amount. E.g. 50 trials for 2^5, 50 trials for 2^6, etc.
	     */
	    
	    for(int i = 0; i <= END-START; i++) 
	    {
	    	listData = new LinkedListTimeComplexity(START+i, START+i);	
	    	arrayData = new ArrayTimeComplexity(START+i, START+i, ORDER);
	    	listSum = 0;
	    	sequentialSum = 0;
	    	strideSum = 0;
	    	randomSum = 0;
	    	for(int j = 0; j < TRIALS; j++) 
	    	{
	    		timesArray = listData.calculateListTraversal();
	    		listSum += timesArray[j];

	    		timesArray = arrayData.calculateSequentialTraversal();
	    		sequentialSum += timesArray[0];

	    		timesArray = arrayData.calculateStrideTraversal();
	    		strideSum += timesArray[0];
	    		
	    		timesArray = arrayData.calculateRandomTraversal();
	    		randomSum += timesArray[0];
	    	}

	    	System.out.println("At: " +(START+i));
	    	System.out.println("List Average: 		  " +listSum/TRIALS);
	    	System.out.println("Stride Array Average: 	  " +sequentialSum/TRIALS);
	    	System.out.println("Random Array Average: 	  " +strideSum/TRIALS);
	    	System.out.println("Sequential Array Average: " +randomSum/TRIALS);
	    	System.out.println();

	    	list.add(listSum/TRIALS);
	    	sequential.add(sequentialSum/TRIALS);
	    	stride.add(strideSum/TRIALS);
	    	random.add(randomSum/TRIALS);
	    }

	    /*Experimental Test*/
	    LinkedListTimeComplexity experimentalListData = new LinkedListTimeComplexity(START, END); 
	    ArrayTimeComplexity experimentalArrayData = new ArrayTimeComplexity(START, END, ORDER);  
	    
	    double[] listExperimental 		= experimentalListData.calculateListTraversal();
	    double[] strideExperimental 	= experimentalArrayData.calculateStrideTraversal();
	    double[] randomExperimental 	= experimentalArrayData.calculateRandomTraversal();
	    double[] sequentialExperimental = experimentalArrayData.calculateSequentialTraversal();

	    for(int m = 0; m < listExperimental.length; m++) {
			System.out.println("Test Linked List Times at: " +(START+m) +" Value: " +listExperimental[m]);
		}
	    for(int m = 0; m < sequentialExperimental.length; m++) {
			System.out.println("Test Sequential Array Times at: " +(START+m) +" Value: " +sequentialExperimental[m]);
		}
	    for(int m = 0; m < strideExperimental.length; m++) {
			System.out.println("Test Stride Array Times at: " +(START+m) +" Value: " +strideExperimental[m]);
		}
	    for(int m = 0; m < randomExperimental.length; m++) {
			System.out.println("Test Random Array Times at: " +(START+m) +" Value: " +randomExperimental[m]);
		}
	  
	}
	
	private double[] calculateError(double[] experimental, ArrayList<Double> theoretical) 
	{
		double error      = 0;
		double difference = 0;
		double[] errorArray = new double[experimental.length];
		System.out.println("Experimental: "+experimental.length);
		System.out.println("Theoretical: "+theoretical.size());
		for(int i = 0; i < experimental.length; i++) {
				System.out.println("Experimental: " +experimental[i]);
		    	difference = Math.abs(experimental[i] - theoretical.get(i));
		    	System.out.println("Difference:   " +difference);
		    	error = difference/theoretical.get(i);
		    	System.out.println("Theoretical:  " +theoretical.get(i));
		    	errorArray[i] = error;
		}
		return errorArray;
	}
	
}
