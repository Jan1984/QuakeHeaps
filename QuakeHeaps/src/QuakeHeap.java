import java.util.LinkedList;


public class QuakeHeap<O> {
	
	LinkedList<LinkedList<Node>> t;
	LinkedList<Integer> n;
	
	
	public QuakeHeap(){
		t = new LinkedList<LinkedList<Node>>();
		n = new LinkedList<Integer>();
	}
	
	
	
	
	public class Node{
		
		//leaf properties
		public O obj;
		public int value;
		public Node highestNodeWithMyValue;
		
		//node properties
		public int hight;
		public Node smallestValueNode;
		public Node parent;
		public Node childR;
		public Node childL;
		
		public Node(O obj, int prior)
		{
			this.obj = obj;
			value = prior;
		}

	}
	
	private O insert(O obj, int prior) {
		Node tmp = new Node(obj, prior);
		tmp.hight = 0;
		insertIntoT(tmp,0);
		return obj;
	}
	
	
	private void insertIntoT(Node n, int hight){
		if (t.get(hight) == null) t.add(0, new LinkedList<Node>()); // if there are no trees at t.get(hight)
		t.get(hight).add(n);
	}
	
	
	private void decreaseKey(Object obj, int i) {
		// TODO Auto-generated method stub
		
	}
	
	private void deleteMin() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// Testing
		
		
		QuakeHeap<String> qh = new QuakeHeap<String>();

		
		qh.insert("zwei", 2);
		qh.insert("vier", 4);
		String sechs = qh.insert("sechs", 6);
		qh.insert("acht", 8);
		qh.insert("zwoelf", 12);
		qh.insert("null", 0);
		
		qh.decreaseKey(sechs, 3);
		
		qh.deleteMin();
		
	}


	


	

	

}
