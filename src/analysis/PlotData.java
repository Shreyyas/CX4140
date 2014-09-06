package analysis;

public class PlotData 
{
	public static final int ROW    = 2;
	public static final int COLUMN = 6;
	
	public static int NUM_NODES; 
	public static int STARTING_VALUE;
	public static long STOPPING_VALUE;
	
	private LinkedList list;
	private double[] numberOfNodes;
	private double[] normalizedRunningTime;
	
	private PlotData(int numNodes, int startingValue, long stoppingValue) 
	{
		NUM_NODES			  = numNodes;
		STARTING_VALUE 		  = startingValue;
		STOPPING_VALUE 		  = stoppingValue;
		
		list           	 	  = new LinkedList();
		numberOfNodes         = new double[NUM_NODES];
		normalizedRunningTime = new double[NUM_NODES];
	}
	
	public double[] calculateNodesArray() {
		int value = STARTING_VALUE;
		int powerOf2 = 16;
		int i;
		
		for (i = 0; STOPPING_VALUE > value; i++) {
			System.out.println("i: "+i);
			System.out.println("Value: "+ value);
			System.out.println("PowerOf2: "+powerOf2);
			
			while(value > powerOf2 && STOPPING_VALUE > powerOf2) {
				powerOf2 *= 2;
			}
			numberOfNodes[i] = value;
			value = powerOf2;
			powerOf2 *= 2;
		}
		numberOfNodes[NUM_NODES-1] = STOPPING_VALUE;
		for(int j = 0; j < numberOfNodes.length; j++)
		{
				System.out.println(numberOfNodes[j]);
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
		
		for(int i = 0; i < NUM_NODES; i++) {
			normalizedRunningTime[i] = normalizingRunningTime;
		}
		 ///NEEDEED TO FIXX FOR EVERY TIME IT RUNS
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
	public void setNumNodes(int value) {
		NUM_NODES = value;
	}
	
	public static void main(String[] args) {
		PlotData data = new PlotData(6, 25, 390);
		data.calculateNodesArray();
	}
}
