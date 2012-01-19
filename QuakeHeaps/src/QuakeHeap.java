import java.util.LinkedList;


public class QuakeHeap<O> {
	
	LinkedList<Node>[] t;
	int[] n;
	
	
	class Node{
		
		//leaf properties
		O obj;
		int value;
		Node highestNodeWithMyValue;
		
		//node properties
		int higth;
		Node smallestValueNode;
		Node parent;
		Node childR;
		Node childL;

	}
	
	private O insert(O obj, int prior) {
		return obj;
		
	}
	
	
	private void decreaseKey(Object obj, int i) {
		// TODO Auto-generated method stub
		
	}
	
	private void deleteMin() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// Testing
		
		
		QuakeHeap qh = new QuakeHeap();

		
		qh.insert("zwei", 2);
		qh.insert("vier", 4);
		Object sechs = qh.insert("sechs", 6);
		qh.insert("acht", 8);
		qh.insert("zwoelf", 12);
		qh.insert("null", 0);
		
		qh.decreaseKey(sechs, 3);
		
		qh.deleteMin();
		
	}


	


	

	

}
