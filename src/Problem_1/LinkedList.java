package Problem_1;

public class LinkedList {
	private static Node head;
	private int listSize;
	
	public LinkedList() {
		head = new Node(0);
		listSize = 0;
	}
	
	public void add(int data) {
		if(listSize == 0) {
			head.setData(data);
		}
		else {
			Node addNode = new Node(data);
			Node currentNode = head;
			while(null != currentNode.getNextNode()) {
				currentNode = currentNode.getNextNode();
			}
			currentNode.setNextNode(addNode);
		}
		listSize++;
	}
	
	public Node remove() {
		Node currentNode = head;
		if(listSize == 0) {
			currentNode.setNextNode(null);
		}
		else {
			Node previousNode = currentNode;
			while(null != currentNode.getNextNode()) {
				previousNode = currentNode;
				currentNode = currentNode.getNextNode();
			}
			previousNode.setNextNode(null);
		}
		listSize--;
		return currentNode;
	}
	
	public Node traverse(long index)
	{
		Node currentNode = head;
		for(int i = 1; (i < index && currentNode.getNextNode() != null); i++) {
			currentNode = currentNode.getNextNode();
		}
		return currentNode;
	}
	
	public static void printList(LinkedList list) {
		Node currentNode = head;
		for(int i = 1; !list.isEmpty() && i <= list.size(); i++) {
			System.out.println("Node Number: " + i);
			System.out.println("Value:       " + currentNode.getData());
			currentNode = currentNode.getNextNode();
			System.out.println();
		}
		if(list.isEmpty()) {
			System.out.println("List is Empty!");
		}
	}

	public boolean isEmpty() {
		return listSize == 0;
	}
	
	public int size() {
		return listSize;
	}
	
	public static void main(String[] args) {
		//Add in time complexity analysis check
		LinkedList list = new LinkedList();
		System.out.println("Size of list: " + list.size());
		LinkedList.printList(list);
		int numNodes = 2000;
		
		for(int i = 1; i <= numNodes; i++) {
			list.add(i);
		}
		
		System.out.println("\nSize of list: " + list.size());
		//LinkedList.printList(list); /*Prints out entirety of list*/
		
		double startTime = System.nanoTime();
		Node finalNode = list.traverse(numNodes);
		double endTime = System.nanoTime();
		
		double estimatedRunTime = endTime - startTime;
		double normalizingRunningTime = estimatedRunTime/numNodes;
		
		//Need to plot the data for normalizingRunningTime for numLinks varying from 25, 32, 64, 128, 224
		
		System.out.println("I've traversed to the node with value of: " +finalNode.getData());
		System.out.println("Start Time: " +startTime/1000); 
		System.out.println("End Time: " +endTime/1000);
		System.out.println("Estimated Run Time: " +estimatedRunTime/1000);
		System.out.println("Normalizing Run Time: " +normalizingRunningTime);
	}
}

