package Problem_1_Revised;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListComplexity_2 {

	//Create one list. Find the points you want to go between. For now just go from 2^5 to 2^24 as a base.
	public final static int START = 5;
	public final static int END   = 12;
	
	private LinkedList<Long> list;
	private ArrayList<Double> timesList;
	public LinkedListComplexity_2()
	{
		timesList = new ArrayList<Double>();
	}
	
	public ArrayList<Double> calculateListComplexity(int indexStart, int indexEnd) {
		
		/*for(int j = 0; j < list.size(); j++) {
			System.out.println("Original List at: " +(j+1) +" Value: " +list.get(j));
		}*/
		long k = 1;
		long endPoint = 0;
		double startTime = 0;
		double endTime = 0;
		double nodeSize = 0; 
		double normalizedTime = 0;
		int initialIndex = indexStart;
		ListIterator<Long> listIterator = null; 

		for(int j = indexStart; j <= indexEnd; j++) {
			endPoint = (long)Math.pow(2,j);
			list = new LinkedList<Long>();
			while(k <= endPoint) {
				list.add(k);
				k++;
			}
			listIterator = (ListIterator<Long>) list.iterator();
			System.out.println("Power: " +j);
			startTime = System.nanoTime();
			while(listIterator.hasNext()) {
				System.out.println("Currently traversing: " +listIterator.next());
			}
			endTime = System.nanoTime();
			timesList.add(endTime-startTime);
		}
		for(int m = 0; m < timesList.size(); m++) {
			System.out.println("Times List at: " +(Math.pow(2, indexStart+m)) +" Value: " +timesList.get(m));
		}
		
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
			System.out.println("Normalized List at: " +(indexStart+m) +" Value: " +normalizedList.get(m));
		}
		
		return normalizedList;
	}
	
	public static ArrayList<Integer> getInputs(int start, int end) {
		ArrayList<Integer> inputs = new ArrayList<Integer>();
		for(int i = start; i <= end; i++) {
			inputs.add(i);
		}
		return inputs;
	}
	
	public static double[] convertToDoubleArray(ArrayList<Double> list) {
		double[] array= new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (double) list.get(i);
		}
	    return array;
	}
	
	public static double[] convertToIntegerArray(ArrayList<Integer> list) {
		double[] array= new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = (double) list.get(i);
		}
	    return array;
	}
	
	public static void main(String[] args) {
		LinkedListComplexity listComplexity = new LinkedListComplexity();
		listComplexity.calculateListComplexity(START, END);
		ArrayList<Integer> inputs = getInputs(START, END);
		
		for(int m = 0; m < inputs.size(); m++) {
			System.out.println("Inputs List at: " +(START+m) +" Value: " +inputs.get(m));
		}
	}
		
}
