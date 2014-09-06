package analysis;

import java.util.ArrayList;

public class PlotData 
{
	public static final int ROW    = 2;
	public static final int COLUMN = 6;
	
	public static int NUM_DATA_PTS; 
	public static int STARTING_VALUE;
	public static long STOPPING_VALUE;
	
	private LinkedList list;
	private double[] normalizedRunningTime;
	
	private PlotData(int startingValue, long stoppingValue) 
	{
		STARTING_VALUE 		  = startingValue;
		STOPPING_VALUE 		  = stoppingValue;		
		list           	 	  = new LinkedList();
	}

	public double[] calculateNodesArray() {
		int value = STARTING_VALUE;
		int powerOf2 = 2;
		ArrayList<Double> arrayList = new ArrayList<Double>();
		arrayList.add((double) STARTING_VALUE);
		
		while(STOPPING_VALUE >= powerOf2)
		{
			System.out.println("Value: "+ value);
			System.out.println("PowerOf2: "+powerOf2);
			
			while(value >= powerOf2) {
				powerOf2 *= 2;
			}
			if(STOPPING_VALUE < value) {
				System.out.println("STOPPINGVAL < VALUE");
				arrayList.add((double) STOPPING_VALUE);
			}
			
			value = powerOf2;
			if(STOPPING_VALUE > value) {
				arrayList.add((double) value);
			}
			powerOf2 *= 2;
			
			if(STOPPING_VALUE > value && STOPPING_VALUE <= powerOf2) {
				arrayList.add((double) STOPPING_VALUE);
			}
		}
		for(int i = 0; i < arrayList.size(); i++) 
		{
			System.out.println(arrayList.get(i));
		}
		NUM_DATA_PTS = arrayList.size();
		return convertToArray(arrayList);
	}
	
	public double[] calculateTraversalTime(int index, long node) 
	{	
		for(int i = 1; i <= node; i++) {
			list.add(i);
		}
				
		double startTime = System.nanoTime();
		Node finalNode = list.traverse(node);
		double endTime = System.nanoTime();
		
		double estimatedRunTime = endTime - startTime;
		double normalizingRunningTime = estimatedRunTime/node;
		
		normalizedRunningTime[index] = normalizingRunningTime;
			///NEEDEED TO FIXX FOR EVERY TIME IT RUNS
		//Need to plot the data for normalizingRunningTime for numLinks varying from 25, 32, 64, 128, 224
		
		System.out.println("I've traversed to the node with value of: " +finalNode.getData());
		System.out.println("Start Time: " +startTime/1000); 
		System.out.println("End Time: " +endTime/1000);
		System.out.println("Estimated Run Time: " +estimatedRunTime/1000);
		System.out.println("Normalizing Run Time: " +normalizingRunningTime);
		
		return normalizedRunningTime;
	}
	
	public double[] convertToArray(ArrayList<Double> arrayList) {
		double[] result = new double[arrayList.size()];
    	int i = 0;
    	for(Double d : arrayList) {
    		result[i++] = d.doubleValue();
    	}
    	return result;
	}
	
	public long getNumDataPoints() {
		return NUM_DATA_PTS;
	}
	public void setNumDataPoints(int value) {
		NUM_DATA_PTS = value;
	}
	
	public static void main(String[] args) {
		PlotData data = new PlotData(25, 254);
		data.calculateNodesArray();
	}
}
