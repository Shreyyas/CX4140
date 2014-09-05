package analysis;

public class PlotData 
{
	public static final int ROW    = 2;
	public static final int COLUMN = 6;
	
	public static int NUM_NODES = 5;
	
	private LinkedList list;
	private double[] numberOfNodes;
	private double[] normalizedRunningTime;
	
	private PlotData() 
	{
		list           		  = new LinkedList();
		numberOfNodes 		  = new double[NUM_NODES];
		normalizedRunningTime = new double[NUM_NODES];
	}
	
	public double[] calculateNumberOfNodes(int startingValue, long stoppingValue) {
		int value = startingValue;
		int powerOf2 = 2;
		for (int i = 0; value < stoppingValue; i++) {
			while(value > powerOf2) {
				powerOf2 *= 2;
			}
			numberOfNodes[i] = value;
			value = powerOf2;
		}
		return numberOfNodes;
	}
	public double[] calculateTraversalTime() 
	{	
		for(int i = 1; i <= NUM_NODES; i++) {
			list.add(i);
		}
				
		double startTime = System.nanoTime();
		Node finalNode = list.traverse(NUM_NODES);
		double endTime = System.nanoTime();
		
		double estimatedRunTime = endTime - startTime;
		double normalizingRunningTime = estimatedRunTime/NUM_NODES;
		
		
		//Need to plot the data for normalizingRunningTime for numLinks varying from 25, 32, 64, 128, 224
		
		System.out.println("I've traversed to the node with value of: " +finalNode.getData());
		System.out.println("Start Time: " +startTime/1000); 
		System.out.println("End Time: " +endTime/1000);
		System.out.println("Estimated Run Time: " +estimatedRunTime/1000);
		System.out.println("Normalizing Run Time: " +normalizingRunningTime);
		
		return normalizedRunningTime;
	}
	
	public long getNumNodes() {
		return NUM_NODES;
	}
	public void setNumNodes(long value) {
		NUM_NODES = value;
	}
}
