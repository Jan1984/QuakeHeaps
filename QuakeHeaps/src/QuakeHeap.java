import java.util.LinkedList;
import java.util.NoSuchElementException;


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
	
	private Node insert(O obj, int prior) {
		Node tmp = new Node(obj, prior);
		tmp.hight = 0;
		tmp.highestNodeWithMyValue = tmp;
		insertIntoT(tmp,0);

		System.out.println("+INSERT: " + obj + ", p:"+ prior);

		return tmp;
	}
	
	
	public void giveInfo(){
		 System.out.println("T[] contents:");
			for (LinkedList<Node> l: t){
				System.out.println("\tT["+t.indexOf(l)+"] contents:");
				for (Node n: l) 
					   System.out.println("\t\t" + n.obj + ", prior:" + n.value);
			}
	}
	
	
	private void insertIntoT(Node n, int hight){
		
		try{
			t.get(hight);
		}
			 catch ( NoSuchElementException e )
	      {
				 t.add(0, new LinkedList<Node>()); // create new List if there are no trees at t.get(hight)
	      }
		 catch ( IndexOutOfBoundsException e )
	      {
				 t.add(0, new LinkedList<Node>()); // create new List if there are no trees at t.get(hight)
				
	      }
			
		t.get(hight).add(n);
	}
	
	/*
	private void decreaseKey(O obj, int i) {
		for (LinkedList<Node> l: t)
			if (l.contains(obj)) ; 
		
	}
	*/
	
	private void decreaseKey(Object obj, int i) {
		Node target = (Node) obj;
		Node highestOne = target.highestNodeWithMyValue;
		Node highestOneParent = target.highestNodeWithMyValue.parent;
		
		if (highestOneParent != null){ // no parent: must be root
			if (highestOneParent.childR == null || highestOneParent.childR.equals(highestOne)) highestOneParent.childR = null;
			else if (highestOneParent.childL == null || highestOneParent.childL.equals(highestOne)) highestOneParent.childL = null;
			else {
				// no children: must be leaf
			}
		}		
		highestOne.parent = null;
		
		
		
		insertIntoT(highestOne, highestOne.hight);
		target.value = i;
		
		
		
		System.out.println("+DECREASEKEY: " + target.obj + ", p:"+ target .value+ "\n");
	
		
	
	}
	
	
	private void deleteMin() {
		System.out.println("+DELETEMIN: " + "\n");
		
	}
	
	
	private void consolidation(){
		for (int i = 0;i < t.size();++i){
			for (Node n: t.get(i)){
				
			
			}
			
		}
		
	}
	private int testQuakeCondition(){
		
		for (int i = 0; i < n.size();++i) 
			if ((n.get(i+1)==null?0:n.get(i+1)) >= (n.get(i+1)==null?0:(3 * n.get(i+1))/4) ) return i; // catch null if no integer in linkedlist
		
		return -1;
		
	}
	
	public static void main(String[] args) {
		// Testing
		
		
		QuakeHeap<String> qh = new QuakeHeap<String>();

		
		qh.insert("zwei", 2);
		qh.insert("vier", 4);qh.giveInfo();
		Object sechs = qh.insert("sechs", 6);
		qh.insert("acht", 8);
		qh.insert("zwoelf", 12);
		qh.insert("null", 0);qh.giveInfo();
		
		qh.decreaseKey(sechs, 3);qh.giveInfo();
		
		qh.deleteMin();qh.giveInfo();
		
	}


	


	

	

}
