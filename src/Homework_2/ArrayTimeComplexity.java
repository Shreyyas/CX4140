package Homework_2;

import java.util.Random;
import java.util.ArrayList;
/**
 * Repeated the same experiment using a contiguous array of integers with
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
 * @author ShreyyasV
 *
 */
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
	 * Performs sequential traversal
	 * @return array
	 */
	public double[] calculateSequentialTraversal() 
	{
		ArrayList<Double> sequential = calculateArrayTraversalTime(SEQUENTIAL_ORDER);
		return convertToDoubleArray(sequential);
	}
	/**
	 * Performs stride traversal
	 * @return array
	 */
	public double[] calculateStrideTraversal() 
	{
		ArrayList<Double> stride = calculateArrayTraversalTime(STRIDE_ORDER);
		return convertToDoubleArray(stride);
	}
	/**
	 * Performs random traversal
	 * @return array
	 */
	public double[] calculateRandomTraversal() 
	{
		ArrayList<Double> random = calculateArrayTraversalTime(RANDOM_ORDER);
		return convertToDoubleArray(random);
	}
	/**
	 * Converts to double array
	 * @return array
	 */
	public static double[] convertToDoubleArray(ArrayList<Double> list) {
		double[] array= new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (double) list.get(i);
		}
	    return array;
	}
	/**
	 * Performs the actual traversal and time calculation
	 * @param order
	 * @return
	 */
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
		
		/*Generate List of Nodes*/
		for(int j = START; j <= END; j++) {
			endPoint = (int)Math.pow(2,j);
			array = generateArray(endPoint, order);
			startTime = System.nanoTime();
			traverse(array, endPoint, order); //Traversal is performed based on order
			endTime = System.nanoTime();
			runningTimeList.add(endTime-startTime);
		}
		
		for(int x = 0; x < runningTimeList.size(); x++) {
			nodeSize = Math.pow(2, initialIndex);
			normalizedTime = runningTimeList.get(x)/(nodeSize);
			initialIndex++;
			normalizedTime = normalizedTime/1000000000.0;
			normalizedList.add(normalizedTime);
		} 

		return normalizedList;
	}
	/**
	 * Based on the order, the traversal is classified as either sequential, 
	 * stride or random. The sequential is order 1, and 
	 * stride and random are any value but 1.
	 * 
	 * Keep a visited array of boolean values signifying a location in the original
	 * values array has been visited or not. Based on the next index we will traverse 
	 * to, if it's already visited or if the next location we will visit will be -1 and
	 * we haven't visited everything else yet, then we search for the first index in the
	 * visited array that is false. This would mean we haven't visited that index yet so 
	 * visit it. Continue until the number of visits equals the number of elements in the
	 * array.
	 * 
	 * @param array
	 * @param numberOfElements
	 * @param order
	 */
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
	
	/**
	 * Generates an array based on the order. Sequential is 1 order, 
	 * Stride is order 2 and above, and Random is 0 order. 
	 * 
	 * Sequential - Add values sequentially into an array up to 
	 *              number of elements – 1. Add -1 to the end of the array. 
	 *              
	 * Stride Order – Strided arrays were generated by creating an array starting 
	 * with some order and ending with the number of elements – 1. Then from the 
	 * following index, values were added until we reached the order – 1. At the end
	 * of the array, we add a -1 to signify the end of the array. So for order 4 
	 * with 5 elements in the array we first add 5 to the array. Then we add the 
	 * elements up to order – 1, so simply add 1, 2, 3 to the array. Finally add -1 
	 * to the end of the array.
	 * 
	 * Random - Generate an array with sequential numbers up to number of elements – 1.
	 * Then, using the Fisher-Yates algorithm, this array was shuffled. 
	 * 
	 * @param endPoint
	 * @param order
	 * @return
	 */
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
	
	/**
	 * Performs Fisher-Yates shuffle
	 * @param array
	 * @return
	 */
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
	
	/**
	 * Tests implementation
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ArrayTimeComplexity analysis = new ArrayTimeComplexity(START, END, 2);		
		analysis.calculateSequentialTraversal();
		analysis.calculateStrideTraversal();
		analysis.calculateRandomTraversal();
	}
}
