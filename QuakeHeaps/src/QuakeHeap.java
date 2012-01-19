import java.util.Collection;
import java.util.Iterator;
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
		public int height;
		public Node smallestValueNode;
		public Node parent;
		public Node childR;
		public Node childL;
		
		public Node()
		{
			
		}

	}
	
	private Node insert(O obj, int prior) {
		Node tmp = new Node();
		tmp.obj = obj;
		tmp.value = prior;

		tmp.height = 0;
		tmp.highestNodeWithMyValue = tmp;
		tmp.smallestValueNode = tmp;
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
	
	
	private void insertIntoT(Node n, int height){
		
		
		for(int i = 0; i <= height;++i){
			try{
				t.get(i);
			}
				 catch ( NoSuchElementException e )
		      {
					 t.add(i, new LinkedList<Node>()); // create new List if there are no trees at t.get(hight)
		      }
			 catch ( IndexOutOfBoundsException e )
		      {
					 t.add(i, new LinkedList<Node>()); // create new List if there are no trees at t.get(hight)
		      }
		}
			
		t.get(height).add(n);
	}
	
	private void deleteInT(Node n){
		for (LinkedList<Node> l: t)
			if (l.contains(n)) l.remove(n);
		
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
		
		
		
		insertIntoT(highestOne, highestOne.height);
		target.value = i;
		
		
		
		System.out.println("+DECREASEKEY: " + target.obj + ", p:"+ target .value+ "\n");
	
		
	
	}
	
	
	private void deleteMin() {
		
		//get smallest element
		Node min = null;
		for (LinkedList<Node> l: t)
			for (Node n: l) 
				if (min == null || min.smallestValueNode.value > n.smallestValueNode.value) min = n;
		
		System.out.println("-MIN "+  min.obj + ", "+ min.value);
		
		//delete path
		Node tmp = min;
		while(tmp != min.smallestValueNode ){
			
			System.out.println("-DELPATH "+ tmp.obj + ", "+ tmp.value);
			
			if (tmp.childL.smallestValueNode == min.smallestValueNode){
				
				Node orphan = tmp.childR;
				tmp.childR = null; 
				orphan.parent = null;
				insertIntoT(orphan, orphan.height);
				decN(tmp.height);
				deleteInT(tmp);
				
				tmp = tmp.childL;
				
			}else {
				
				Node orphan = tmp.childL;
				tmp.childL = null; 
				orphan.parent = null;
				insertIntoT(orphan, orphan.height);
				decN(tmp.height);
				deleteInT(tmp);
				
				tmp = tmp.childR;
				
			}
			
		}
		
		deleteInT(min.smallestValueNode);
		
		consolidation();
		if (testQuakeCondition() != -1) {
			
			testQuakeCondition();
			
			
		}
		
		
		
		
		
		
		System.out.println("+DELETEMIN: " + "\n");
	}
	
	
	private void consolidation(){
		for (int i = 0;i < t.size();++i){
			LinkedList<Node> tmplist = t.get(i);
			
			
				System.out.println("-CONSOL level(" +i+ ") , size: " + tmplist.size());
				
				while (tmplist.size() > 1) {
				
					
				
					Node n1 = tmplist.poll();
					Node n2 = tmplist.poll();
					
					System.out.println("n1: "+ n1.obj + ", n2: "+ n2.obj);
					
					link(n1, n2);
					t.get(i).remove(n1);t.get(i).remove(n2);
					
					
					}


				}
	
			
		}
		
	
	private void link(Node n1, Node n2) {
		Node newparent = new Node();
		if (n1.smallestValueNode.value <= n2.smallestValueNode.value )
			newparent.smallestValueNode = n1.smallestValueNode;	
		else newparent.smallestValueNode = n2.smallestValueNode;	
		
		newparent.height = n1.height+1;
		newparent.highestNodeWithMyValue = newparent;
		insertIntoT(newparent,newparent.height);
		
		incN(newparent.height);
		
		
		
	}


	private void incN(int height) {
		
		
		for(int i = 0; i <= height;++i){
			try{
				n.get(i);
			}
				 catch ( NoSuchElementException e )
		      {
					 n.add(i, new Integer(0)); // create new List if there are no trees at t.get(hight)
		      }
			 catch ( IndexOutOfBoundsException e )
		      {
					 n.add(i, new Integer(0)); // create new List if there are no trees at t.get(hight)
		      }
		}
		
		
		Integer inte = n.get(height) + 1 ;
		n.add(height, inte);
		
	}
	
	private void decN(int height) {
		
		
		for(int i = 0; i <= height;++i){
			try{
				n.get(i);
			}
				 catch ( NoSuchElementException e )
		      {
					 n.add(i, new Integer(0)); // create new List if there are no trees at t.get(hight)
		      }
			 catch ( IndexOutOfBoundsException e )
		      {
					 n.add(i, new Integer(0)); // create new List if there are no trees at t.get(hight)
		      }
		}
		
		
		Integer inte = n.get(height) - 1 ;
		n.add(height, inte);
		
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
