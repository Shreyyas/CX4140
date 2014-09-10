package Problem_1;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
//Create one list. Find the points you want to go between. For now just go from 2^5 to 2^24 as a base.
public class ListFastComplexity 
{
	private LinkedList<Long> list;
	private ArrayList<Double> timesList;
	public ListFastComplexity()
	{
		list = new LinkedList<Long>();
		timesList = new ArrayList<Double>();
	}
	
	public void calculateListComplexity(int indexStart, int indexEnd) {
		long i = (int)Math.pow(2, indexStart);
		long endPoint = (long)Math.pow(2, indexEnd);
		long value = 0;
		while(i <= endPoint) {
			list.add(i);
			i++;
		}
		/*for(int j = 0; j < list.size(); j++) {
			System.out.println("Original List at: " +(j+1) +" Value: " +list.get(j));
		}
*/		
		ListIterator<Long> listIterator = list.listIterator();
		double start = System.nanoTime();
		double end = 0;
		while(listIterator.hasNext())
		{
			value = listIterator.next();
			if((value & (value - 1)) == 0) {
				end = System.nanoTime(); //marks values as one passes through the list
				//System.out.println("Value: " +value);
				timesList.add(end);
			}
		}
		/*
		for(int m = 0; m < timesList.size(); m++) {
			System.out.println("Times List at: " +(m+1) +" Value: " +timesList.get(m));
		}*/
		
		ArrayList<Double> subtractList = new ArrayList<Double>();
		double timeElapsed = 0;
		for(int k = 0; k < timesList.size(); k++) {
			timeElapsed = timesList.get(k) - start;
			subtractList.add(timeElapsed);
		}
		
		/*for(int m = 0; m < subtractList.size(); m++) {
			System.out.println("Subtract List at: " +(m+1) +" Value: " +subtractList.get(m));
		}*/
		/*
		 * Determines the amount of extra time it takes to perform the above code in the while loop.
		 */
		long longTestCase = 999999999999999999l;
		LinkedList<Long> secondTestList = new LinkedList<Long>();
		secondTestList.add(longTestCase);
		ListIterator<Long> secondTestIterator = secondTestList.listIterator();
		//System.out.println("Time to Get Value: ");

		//Time to get Value
		double startTimeToGetValue = System.nanoTime();
		while(secondTestIterator.hasNext()) {
			secondTestIterator.next();
		}
		double endTimeToGetValue = System.nanoTime();
		double getValueDifference = endTimeToGetValue-startTimeToGetValue;
		//System.out.println("Start Time to Get Value: " +startTimeToGetValue);
		//System.out.println("Start Time to Get Value: " +endTimeToGetValue);
		//System.out.println("Difference: " +getValueDifference);

		//Time to check
		double startTimeToCheck = System.nanoTime();
		if((longTestCase & (longTestCase - 1)) == 0) {
			
		}
		double endTimeToCheck = System.nanoTime();
		double checkTimeDifference = endTimeToCheck-startTimeToCheck;
		//System.out.println("Start Time to check: " +startTimeToCheck);
		//System.out.println("Start Time to Get Value: " +endTimeToCheck);
		//System.out.println("Difference: " +checkTimeDifference);
		

		//Time to add to a list;
		double startListTime = System.nanoTime();
		timesList.add(-1.0);
		double endListTime = System.nanoTime();
		double addListDifference = endListTime-startListTime;
		//System.out.println("Start Time to Get Value: " +startListTime);
		//System.out.println("Start Time to Get Value: " +endListTime);
		//System.out.println("Difference: " +addListDifference);
		
		double totalDifference = addListDifference - checkTimeDifference - getValueDifference*(list.size());
		double adjustedTraversalTime = 0;
		
		ArrayList<Double> adjustedList = new ArrayList<Double>();
		for(int x = 0; x < subtractList.size(); x++) {
			adjustedTraversalTime = subtractList.get(x) - totalDifference;
				adjustedList.add(adjustedTraversalTime);
			}
		/*for(int m = 0; m < subtractList.size(); m++) {
			System.out.println("Adjusted List at: " +(m+1) +" Value: " +adjustedList.get(m));
		}*/
		
		double normalizedTime = 0;
		int initialIndex = indexStart;
		ArrayList<Double> normalizedList = new ArrayList<Double>();
		double nodeSize = 0; 
		for(int x = 0; x < adjustedList.size(); x++) {
			nodeSize = Math.pow(2, initialIndex);
			normalizedTime = adjustedList.get(x)/(nodeSize);
			//System.out.println("Node Size: " +nodeSize);
			initialIndex++;
			normalizedTime = normalizedTime/1000000000.0;
			normalizedList.add(normalizedTime);
		}
		for(int m = 0; m < adjustedList.size(); m++) {
			System.out.println("Normalized List at: " +(indexStart+m) +" Value: " +normalizedList.get(m));
		}
	}
	
	public static void main(String[] args) {
		ListFastComplexity listComplexity = new ListFastComplexity();
		listComplexity.calculateListComplexity(5, 24);
	}
	
}