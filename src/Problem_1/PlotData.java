package Problem_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlotData 
{
	public static final int TRIALS = 5;

	public static int STARTING_VALUE;
	public static long STOPPING_VALUE;
	
	private PlotData(int startingValue, long stoppingValue) 
	{
		STARTING_VALUE 		  = startingValue;
		STOPPING_VALUE 		  = stoppingValue;		
	}

	public ArrayList<Long> calculateNodesArray() {
		int value = STARTING_VALUE;
		int powerOf2 = 2;
		ArrayList<Long> arrayList = new ArrayList<Long>();
		arrayList.add((long) STARTING_VALUE);
		
		while(STOPPING_VALUE >= powerOf2)
		{	
			while(value >= powerOf2) {
				powerOf2 *= 2;
			}
			if(STOPPING_VALUE <= value) { arrayList.add((long) STOPPING_VALUE); }
			value = powerOf2;
			if(STOPPING_VALUE > value) { arrayList.add((long) value); }
			powerOf2 *= 2; 
			
			if(STOPPING_VALUE > value && STOPPING_VALUE <= powerOf2) {
				arrayList.add((long) STOPPING_VALUE);
			}
		}
		return arrayList;
	}
	
	public void calculateTraversalTime(File file) 
	{	
		double startTime;
		double endTime;
		double estimatedRunTime;
		double normalizingRunningTime;
		Node finalNode;
		LinkedList list = new LinkedList();

		ArrayList<Long>numberOfNodes = calculateNodesArray();
		int lengthOfNodesArray = numberOfNodes.size();
		
		ArrayList<Double> runningTimeList = new ArrayList<Double>();
		
		for(int i = 0; i < lengthOfNodesArray; i++)
		{
			//LinkedList.printList(list);
			if(i>0 && !list.isEmpty()) 
			{
				list = new LinkedList();
			}
			for(int j = 1; j <= (long)numberOfNodes.get(i); j++) {
				list.add(j);
				//System.out.println("j: " +j);
			}

			System.out.println("\nSize of list: " + list.size());
			
			startTime = System.nanoTime();
			finalNode = list.traverse(numberOfNodes.get(i));
			endTime = System.nanoTime();
		
			estimatedRunTime = endTime - startTime;
			normalizingRunningTime = estimatedRunTime/numberOfNodes.get(i);
		
			runningTimeList.add((double) normalizingRunningTime);
			
			System.out.println("I've traversed to the node with value of: " +finalNode.getData());
			System.out.println("Start Time: " +startTime/1000); 
			System.out.println("End Time: " +endTime/1000);
			System.out.println("Estimated Run Time: " +estimatedRunTime/1000);
			System.out.println("Normalizing Run Time: " +normalizingRunningTime + "\n");
		}
		writeToFile(numberOfNodes, runningTimeList, file);
	}
	
	private void writeToFile(ArrayList<Long> numberOfNodes, ArrayList<Double> runningTimeList, File file) {
		try {
			FileWriter writer = new FileWriter(file.getAbsolutePath(), true);
			for(int i = 0; i < numberOfNodes.size(); i++) {
				writer.append(numberOfNodes.get(i).toString() + " ");
			}
			writer.append("\n");
			for(int i = 0; i < runningTimeList.size(); i++) {
				writer.append(runningTimeList.get(i).toString() + " ");
			}
			writer.append("\n");
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Hey, check how you're writing to your files. It might not be correct.");
		}
	}

	public static void main(String[] args) {
		PlotData data = new PlotData(25, 2049);
		File file = new File("C:\\Users\\ShreyyasV\\Desktop\\PlotData.txt");			
		if(!file.exists()) {
			try { file.createNewFile(); } 
			catch (IOException e) {	System.out.println("Hey, check the file again. Your filepath may be incorrect."); }
		}
		for(int i = 0; i < TRIALS; i++) {
			data.calculateTraversalTime(file);
		}
	}
}


