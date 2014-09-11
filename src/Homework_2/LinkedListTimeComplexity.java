package Homework_2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * Calculates the time required per node for linked list travesal.
 * START notates the number of nodes to start at.
 * END   notates the number of nodes to end at.
 * @author ShreyyasV
 *
 */
public class LinkedListTimeComplexity {

	public static int START = 5;
	public static int END   = 12;
	
	private LinkedList<Long> list;
	private ArrayList<Double> timesList;
	public LinkedListTimeComplexity(int start, int end)
	{
		START = start;
		END   = end;
		timesList = new ArrayList<Double>();
	}
	/**
	 * Performs the actual linked list traversal. Traversal is done by creating 
	 * a new linked list of size n every time based on the starting 
	 * number of elements and running through each element. ListIterator
	 * provides a fast O(n) traversal through linked lists of large sizes
	 * so this traversal methodology is used. 
	 * 
	 * System.nanoTime() is used to obtain current clock value before and 
	 * after the traversal. These values are subtracted to find the running time 
	 * of the list and then divided by the number of elements currently in the list.
	 * 
	 * This returns an array of the traversal times per node. Array is returned for
	 * easy plotting.
	 * @return array
	 */
	public double[] calculateListTraversal() 
	{
		long k = 1;
		long endPoint = 0;
		double startTime = 0;
		double endTime = 0;
		double nodeSize = 0; 
		double normalizedTime = 0;
		int initialIndex = START;
		ListIterator<Long> listIterator = null; 
		
		/*Generate List of Nodes*/
		for(int j = START; j <= END; j++) {
			endPoint = (long)Math.pow(2,j);
			list = new LinkedList<Long>();
			while(k <= endPoint) {
				list.add(k);
				k++;
			}
			listIterator = (ListIterator<Long>) list.iterator();
			
			/*Traverse and Clock the Times*/
			startTime = System.nanoTime();
			while(listIterator.hasNext()) {
				listIterator.next();
			}
			endTime = System.nanoTime();
			
			timesList.add(endTime-startTime);
		}
		
		ArrayList<Double> normalizedList = new ArrayList<Double>();
		for(int x = 0; x < timesList.size(); x++) {
			nodeSize = Math.pow(2, initialIndex);
			normalizedTime = timesList.get(x)/(nodeSize);
			initialIndex++;
			normalizedTime = normalizedTime/1000000000.0;
			normalizedList.add(normalizedTime);
		}
		return convertToDoubleArray(normalizedList);
	}
	/**
	 * Get the start number of nodes to end number of nodes into an array.
	 * @return array
	 */
	public double[] getInputs() {
		double[] array= new double[END-START+1];
		for (int i = 0; i < array.length; i++) {
			array[i] = START+i;
		}
	    return array;
	}
	/**
	 * Converts a list of Doubles to double array
	 * @param list
	 * @return array
	 */
	public double[] convertToDoubleArray(ArrayList<Double> list) {
		double[] array= new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (double) list.get(i);
		}
	    return array;
	}
	/**
	 * Tests implementation
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedListTimeComplexity listComplexity = new LinkedListTimeComplexity(START,END);
		listComplexity.calculateListTraversal();
	}
		
}
