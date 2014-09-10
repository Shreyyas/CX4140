package Homework_1;

import java.util.Random;
import java.util.ArrayList;

public class ArrayTimeComplexity 
{
	public static int START = 5;
	public static int END   = 24;
	
	public static int RANDOM_ORDER = 0;
	public static int SEQUENTIAL_ORDER = 1;
	public static int STRIDE_ORDER = 2;

	public ArrayTimeComplexity(int start, int end, int strideOrder) 
	{
		START = start;
		END = end;
		STRIDE_ORDER = strideOrder;
	}

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
	public double[] sequential() 
	{
		ArrayList<Double> sequential = calculateArrayTraversalTime(SEQUENTIAL_ORDER);
		return convertToDoubleArray(sequential);
	}

	public double[] stride() 
	{
		ArrayList<Double> stride = calculateArrayTraversalTime(STRIDE_ORDER);
		return convertToDoubleArray(stride);
	}

	public double[] random() 
	{
		ArrayList<Double> random = calculateArrayTraversalTime(RANDOM_ORDER);
		return convertToDoubleArray(random);
	}
	public static double[] convertToDoubleArray(ArrayList<Double> list) {
		double[] array= new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (double) list.get(i);
		}
	    return array;
	}
	private ArrayList<Double> calculateArrayTraversalTime(int order) 
	{
		int endPoint = 0;
		double startTime = 0;
		double endTime = 0;
		double nodeSize = 0;
		double normalizedTime;
		int initialIndex = START;
		int[] array = null; 
		
		ArrayList<Double> runningTimeList = new ArrayList<Double>();
		ArrayList<Double> normalizedList  = new ArrayList<Double>();

		for(int j = START; j <= END; j++) {
			endPoint = (int)Math.pow(2,j);
			array = generateArray(endPoint, order);
			startTime = System.nanoTime();
			traverse(array, endPoint, order);
			endTime = System.nanoTime();
			//System.out.println("Array_Size: " +j +" Difference_In_Time: " +(endTime-startTime));
			runningTimeList.add(endTime-startTime);
		}

		for(int x = 0; x < runningTimeList.size(); x++) {
			nodeSize = Math.pow(2, initialIndex);
			normalizedTime = runningTimeList.get(x)/(nodeSize);
			//System.out.println("Array_Elements: " +nodeSize);
			initialIndex++;
			normalizedTime = normalizedTime/1000000000.0;
			normalizedList.add(normalizedTime);
		} 
		/*for(int m = 0; m < runningTimeList.size(); m++) {
			System.out.println("Average_Access_Time at: " +(START+m) +" Value: " +normalizedList.get(m));
		}*/
		return normalizedList;
	}

	public void traverse(int[] array, int numberOfElements, int order) 
	{
		int visited = 0;
		int j = 0;
		boolean exitCondition = false;
		boolean[] visitedArray = new boolean[numberOfElements];
		if(order == 1) {
			for (int i = 0; i != -1;) {
				i = array[i];
			}
		}
		else if(order != 1) 
		{
			while (visited != numberOfElements) {
				if (array[j] != -1) {
					visitedArray[j] = true;
					visited++;
					j = array[j];
				}
				if ((numberOfElements - visited) > 1) {
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
					visitedArray[numberOfElements - 1] = true;
					visited++;
				}
			}
		}
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
	
	public static void main(String[] args) 
	{
		ArrayTimeComplexity analysis = new ArrayTimeComplexity(START, END, 2);		
		analysis.sequential();
		analysis.stride();
		analysis.random();
	}
}
