import java.util.LinkedList;


public class QuakeHeap {
	
	LinkedList[] t;
	int[] n;
	
	
	class Node{
		
		//leaf properties
		Object obj;
		int value;
		Node highestNodeWithMyValue;
		
		//node properties
		int higth;
		Node smallestValueNode;
		Node parent;
		Node childR;
		Node childL;

	}
	
	private void insert(Integer integer) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		// Testing
		
		
		QuakeHeap qh = new QuakeHeap();

		
		qh.insert(new Integer(2));
		qh.insert(new Integer(4));
		qh.insert(new Integer(6));
		qh.insert(new Integer(8));
		qh.insert(new Integer(12));
	}

	

}
