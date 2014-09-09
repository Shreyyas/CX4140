package Problem_1;

import java.util.Random;
import java.util.ArrayList;

public class TimeComplexity 
{
	public static final int ORDER = 2;
	public static final int START = 5;
	public static final int END   = 24;

	public TimeComplexity() {
	}
	//for array of size 32 traverse
	//for array of size 64 traverse
	/**
	 * Repeat the same experiment using a contiguous array of integers with
	 * array length n. Each element of the array contains the index of the next
	 * element, and a -1 value represents the end of the list. If the array is
	 * indexed from "0" to "n-1" then the inputs to test would be as follows,
	 * for an array with 16 elements:
	 * 
	 * Sequential Order: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1
	 * Stride-2 Order: 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, -1
	 * Stride-4 Order: 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 2, 3, -1
	 * Random Order: 9, 6, 1, 10, 2, 11, 7, 15, 5, 8, 14, 12, 3, 4, 13, -1
	 * 
	 * @param numberOfElements
	 */
	public ArrayList<Double> sequential(int indexStart, int indexEnd) 
	{
		int endPoint = 0;
		double startTime = 0;
		double endTime = 0;
		int initialIndex = indexStart;
		int[] array = null; 
		ArrayList<Double> runningTimeList = new ArrayList<Double>();
		ArrayList<Double> normalizedList  = new ArrayList<Double>();

		for(int j = indexStart; j <= indexEnd; j++) {
			endPoint = (int)Math.pow(2,j);
			array = generateArray(endPoint, 1);
			System.out.println("EndPoint: " +j);
			startTime = System.nanoTime();
			
			/* Sequential Traversal */
			startTime = System.nanoTime();
			for (int i = 0; i != -1;) {
				i = array[i];
			}
			
			endTime = System.nanoTime();
			runningTimeList.add(endTime-startTime);
		}
		normalizedList = getNormalizedArray(runningTimeList, initialIndex);
		return normalizedList;
	}

	public ArrayList<Double> stride(int indexStart, int indexEnd, int order) 
	{
		int endPoint = 0;
		double startTime = 0;
		double endTime = 0;
		int initialIndex = indexStart;
		int[] array = null; 
		ArrayList<Double> runningTimeList = new ArrayList<Double>();
		ArrayList<Double> normalizedList  = new ArrayList<Double>();

		for(int j = indexStart; j <= indexEnd; j++) {
			endPoint = (int)Math.pow(2,j);
			array = generateArray(endPoint, order);
			System.out.println("EndPoint: " +j);
			startTime = System.nanoTime();
			traverse(array, order);
			endTime = System.nanoTime();
			runningTimeList.add(endTime-startTime);
		}
		normalizedList = getNormalizedArray(runningTimeList, initialIndex);
		return normalizedList;
	}

	public ArrayList<Double> random(int indexStart, int indexEnd) 
	{
		int endPoint = 0;
		double startTime = 0;
		double endTime = 0;
		int initialIndex = indexStart;
		int[] array = null; 
		ArrayList<Double> runningTimeList = new ArrayList<Double>();
		ArrayList<Double> normalizedList  = new ArrayList<Double>();

		for(int j = indexStart; j <= indexEnd; j++) {
			endPoint = (int)Math.pow(2,j);
			array = generateArray(endPoint, 0);
			System.out.println("EndPoint: " +j);
			startTime = System.nanoTime();
			traverse(array, 0);
			endTime = System.nanoTime();
			runningTimeList.add(endTime-startTime);
		}
		normalizedList = getNormalizedArray(runningTimeList, initialIndex);
		return normalizedList;
	}
/*
	private ArrayList<Double> calculateTraversalTime(int numberOfElements, int[] array) 
	{
		ArrayList<Double> runningTimeList = new ArrayList<Double>();

		startTime = System.nanoTime();
		traverse(array, numberOfElements);
		endTime = System.nanoTime();

		estimatedRunTime = endTime - startTime;
		normalizingRunningTime = estimatedRunTime / numberOfElements;

		runningTimeList.add(normalizingRunningTime);
		return runningTimeList;
	}
*/
	public void traverse(int[] array, int numberOfElements) 
	{
		int visited = 0;
		int j = 0;
		boolean exitCondition = false;
		boolean[] visitedArray = new boolean[numberOfElements];

		while (visited != numberOfElements) {
			// System.out.println("Visited Element: "+array[j]);
			// System.out.println("Visit Count: "+visited);
			if (array[j] != -1) {
				visitedArray[j] = true;
				visited++;
				// System.out.println("Updated Visit Count: "+visited);
				j = array[j];
			}
			if ((numberOfElements - visited) > 1) {
				// System.out.println("j: " +j);
				if (j == numberOfElements - 1 || visitedArray[j]) {
					for (int k = 0; (k < numberOfElements) && (!exitCondition); k++) {
						if (!visitedArray[k]) {
							j = k;
							exitCondition = true;
						}
					}
					exitCondition = false;
				}
			} else if (visited == numberOfElements - 1) {
				// System.out.println("Should be last value set to true.");
				visitedArray[numberOfElements - 1] = true;
				visited++;
			}
			// System.out.println("New J is equal to: "+j);
			// System.out.println("Next Visit: "+array[j] +"\n");
		}

		// for(int i = 0; i < numberOfElements; i++)
		// {
		// System.out.println("Visited: " +array[i] + " Value: "
		// +visitedArray[i]);
		// }
	}

	public int[] generateArray(int endPoint, int order) 
	{
		int[] array = new int[endPoint];
		if (order == 1) {
			for (int i = 1; i < endPoint; i++) {
				array[i - 1] = i;
			}
		} 
		else if (order >= 2) {
			int index = 0;
			int restart = 1;
			for (int i = order; i != endPoint; i++) {
				array[index] = i;
				index++;
			}
			for (int j = endPoint - order; j < endPoint - 1; j++) {
				array[j] = restart;
				restart++;
			}
		} 
		else {
			for (int i = 1; i < endPoint; i++) {
				array[i - 1] = i;
			}
			array = shuffle(array);
		}
		array[endPoint - 1] = -1;
		return array;
	}

	private int[] shuffle(int[] array) 
	{
		Random random = new Random();
		for (int i = 1; i < array.length; i++) {
			int index = random.nextInt(i + 1);

			int swap = array[index];
			array[index] = array[i];
			array[i] = swap;
		}
		return array;
	}
	
	public ArrayList<Double> getNormalizedArray(ArrayList<Double> timesList, int initialIndex) {
		double nodeSize = 0;
		double normalizedTime;
		ArrayList<Double> normalizedList = new ArrayList<Double>();
		for(int x = 0; x < timesList.size(); x++) {
			nodeSize = Math.pow(2, initialIndex);
			normalizedTime = timesList.get(x)/(nodeSize);
			System.out.println("Node Size: " +nodeSize);
			initialIndex++;
			normalizedTime = normalizedTime/1000000000.0;
			normalizedList.add(normalizedTime);
		} 
		for(int m = 0; m < timesList.size(); m++) {
			System.out.println("Normalized List at: " +(START+m) +" Value: " +normalizedList.get(m));
		}
		return normalizedList;
	}

	public static void main(String[] args) 
	{
		TimeComplexity analysis = new TimeComplexity();		
		analysis.sequential(5, 24);
		analysis.stride(5,24,2);
		analysis.random(5, 24);

		/*
		File file = new File("C:\\Users\\ShreyyasV\\Desktop\\ArrayData.txt");			
		if(!file.exists()) {
			try { file.createNewFile(); } 
			catch (IOException e) {	System.out.println("Hey, check the file again. Your filepath may be incorrect."); }
		}

		System.out.println("--------------SEQUENTIAL----------------");
		sequentialList = analysis.sequential(NUM_ELEMENTS);
		System.out.println("----------------------------------------\n");

		System.out.println("----------------STRIDE----------------");
		strideList = analysis.stride(NUM_ELEMENTS, ORDER);
		System.out.println("\n------------------------------------\n");

		System.out.println("---------------RANDOM-----------------");
		randomList = analysis.random(NUM_ELEMENTS);
		System.out.println("\n------------------------------------\n");
		
		try {
			FileWriter writer = new FileWriter(file.getAbsolutePath(), false);
			writer.append(NUM_ELEMENTS +" " +ORDER);
			writer.append("\n");
			for(int i = 0; i < sequentialList.size(); i++) {
				writer.append(sequentialList.get(i).toString() + " ");
			}
			writer.append("\n");
			for(int i = 0; i < strideList.size(); i++) {
				writer.append(strideList.get(i).toString() + " ");
			}
			writer.append("\n");
			for(int i = 0; i < randomList.size(); i++) {
				writer.append(randomList.get(i).toString() + " ");
			}
			writer.append("\n");
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Hey, check how you're writing to your files. It might not be correct.");
		}
		*/
	}
}
