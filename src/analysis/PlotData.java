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
	private long[] numberOfNodes;
	private double[] normalizedRunningTime;
	
	private PlotData(int startingValue, long stoppingValue) 
	{
		STARTING_VALUE 		  = startingValue;
		STOPPING_VALUE 		  = stoppingValue;		
	}

	public long[] calculateNodesArray() {
		int value = STARTING_VALUE;
		int powerOf2 = 2;
		ArrayList<Long> arrayList = new ArrayList<Long>();
		arrayList.add((long) STARTING_VALUE);
		
		while(STOPPING_VALUE >= powerOf2)
		{
			System.out.println("Value: "+ value);
			System.out.println("PowerOf2: "+powerOf2);
			
			while(value >= powerOf2) {
				powerOf2 *= 2;
			}
			if(STOPPING_VALUE < value) {
				System.out.println("STOPPINGVAL < VALUE");
				arrayList.add((long) STOPPING_VALUE);
			}
			
			value = powerOf2;
			if(STOPPING_VALUE > value) {
				arrayList.add((long) value);
			}
			powerOf2 *= 2;
			
			if(STOPPING_VALUE > value && STOPPING_VALUE <= powerOf2) {
				arrayList.add((long) STOPPING_VALUE);
			}
		}
		for(int i = 0; i < arrayList.size(); i++) 
		{
			System.out.println(arrayList.get(i));
		}
		NUM_DATA_PTS = arrayList.size();
		return convertToLongArray(arrayList);
	}
	
	public double[] calculateTraversalTime() 
	{	
		double startTime;
		double endTime;
		double estimatedRunTime;
		double normalizingRunningTime;
		Node finalNode;

		numberOfNodes = calculateNodesArray();
		int lengthOfNodesArray = numberOfNodes.length;
		ArrayList<Double> runningTimeList = new ArrayList<Double>();
		
		for(int i = 0; i < lengthOfNodesArray; i++)
		{
			for(int j = 1; j <= numberOfNodes[i]; j++) {
				list = new LinkedList();
				list.add(j);
			}
			startTime = System.nanoTime();
			finalNode = list.traverse(numberOfNodes[i]);
			endTime = System.nanoTime();
		
			estimatedRunTime = endTime - startTime;
			normalizingRunningTime = estimatedRunTime/numberOfNodes[i];
		
			runningTimeList.add((double) normalizingRunningTime);
			
			System.out.println("I've traversed to the node with value of: " +finalNode.getData());
			System.out.println("Start Time: " +startTime/100000); 
			System.out.println("End Time: " +endTime/100000);
			System.out.println("Estimated Run Time: " +estimatedRunTime/100000);
			System.out.println("Normalizing Run Time: " +normalizingRunningTime);
		}
		convertToDoubleArray(runningTimeList);
		return normalizedRunningTime;
	}
	
	public long[] convertToLongArray(ArrayList<Long> arrayList) {
		long[] result = new long[arrayList.size()];
    	int i = 0;
    	for(Long l : arrayList) {
    		result[i++] = l.longValue();
    	}
    	return result;
	}
	
	public double[] convertToDoubleArray(ArrayList<Double> arrayList) {
		double[] result = new double[arrayList.size()];
    	int i = 0;
    	for(Double l : arrayList) {
    		result[i++] = l.doubleValue();
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
		PlotData data = new PlotData(2, 256);
		data.calculateTraversalTime();
	}
}
