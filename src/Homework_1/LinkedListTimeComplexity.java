package Homework_1;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

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
	
	public double[] calculateListTraversalTime() {
		
		/*for(int j = 0; j < list.size(); j++) {
			System.out.println("Original List at: " +(j+1) +" Value: " +list.get(j));
		}*/
		long k = 1;
		long endPoint = 0;
		double startTime = 0;
		double endTime = 0;
		double nodeSize = 0; 
		double normalizedTime = 0;
		int initialIndex = START;
		ListIterator<Long> listIterator = null; 

		for(int j = START; j <= END; j++) {
			endPoint = (long)Math.pow(2,j);
			list = new LinkedList<Long>();
			while(k <= endPoint) {
				list.add(k);
				k++;
			}
			listIterator = (ListIterator<Long>) list.iterator();
//			System.out.println("Power: " +j);
			startTime = System.nanoTime();
			while(listIterator.hasNext()) {
				listIterator.next();//System.out.println("Traversing: " +listIterator.next());
			}
			endTime = System.nanoTime();
			timesList.add(endTime-startTime);
		}
		/*for(int m = 0; m < timesList.size(); m++) {
			System.out.println("Times List at: " +(Math.pow(2, START+m)) +" Value: " +timesList.get(m));
		}*/
		
		ArrayList<Double> normalizedList = new ArrayList<Double>();
		for(int x = 0; x < timesList.size(); x++) {
			nodeSize = Math.pow(2, initialIndex);
			normalizedTime = timesList.get(x)/(nodeSize);
//			System.out.println("Node Size: " +nodeSize);
			initialIndex++;
			normalizedTime = normalizedTime/1000000000.0;
			normalizedList.add(normalizedTime);
		}
		/*for(int m = 0; m < timesList.size(); m++) {
			System.out.println("Normalized List at: " +(START+m) +" Value: " +normalizedList.get(m));
		}*/
		return convertToDoubleArray(normalizedList);
	}
	
	public double[] getInputs() {
		double[] array= new double[END-START+1];
		for (int i = 0; i < array.length; i++) {
			array[i] = START+i;
			//System.out.println(array[i]);
		}
	    return array;
	}
	
	public double[] convertToDoubleArray(ArrayList<Double> list) {
		double[] array= new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (double) list.get(i);
		}
	    return array;
	}

	public static void main(String[] args) {
		LinkedListTimeComplexity listComplexity = new LinkedListTimeComplexity(START,END);
		listComplexity.calculateListTraversalTime();
	}
		
}
