package Problem_1;
/**
 * Node class for Linked List
 * @author ShreyyasV
 */
public class Node {
	private int data;
	private Node next;
	private Node previous; 
	
	public Node(int data) {
		this.data = data;
		next = null;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	public void setNextNode(Node nextNode) {
		this.next = nextNode;
	}
	public void setPreviousNode(Node node) {
		this.previous = node.previous;	
	}
	
	public Node getNextNode() {
		return next;
	}
	public Node getPreviousNode() {
		return previous;
	}
	public int getData() {
		return data;
	}

}
