package com.ds;

public class LinkedList {

	Node head;
	
	class Node {
		int data;
		Node next;
		
		Node(int d){
			data = d;
			next = null;
		}
	}
	
	public void insertAtFront(int data){
		Node n = new Node(data);
		n.next = head;
		head = n;
	}
	
	public void listAllNodes(){
		Node n = head;
		if(n == null){
			System.out.println("Node data");
		}
		else{
			while(n.next != null){
				System.out.print(n.data + " -> ");
				n = n.next;
			}
			System.out.println(n.data);
		}
	}
	
	public int length(){
		Node n = head;
		if (n == null)
			return 0;
		else{
			int i = 0;
			while(n.next != null){
				n = n.next;
				i++;
			}
			i++;
			return i;
		}
	}
	
	public void insertAtX(int data, int pos){
		Node n = head;
		int i=1;
		while(i<pos-1 && n.next != null){
			n = n.next;
			i++;
		}
		Node node = new Node(data);
		node.next = n.next;
		n.next = node;
	}
	
	public void insertAtEnd(int data){
		Node n = head;
		Node node = new Node(data);
		while(n.next != null){
			n = n.next;
		}
		n.next = node;
	}
	
	public void deleteFirst(){
		head = head.next;
	}
	
	public void deleteLast(){
		Node n = head;
		Node p = n;
		while(n.next != null){
			p = n;
			n = n.next;
		}
		p.next = null;
	}
	
	public void deleteAtX(int pos){
		Node n = head, p=n;
		int i = 1;
		while(i<pos && n.next != null){
			i++;
			p = n;
			n = n.next;
		}
		p.next = n.next;
	}
	
	public void deleteKeyFirst(int key){
		if(head.data == key){
			head = head.next;
		}
		else{
			Node n = head.next, p=head;
			while(n.next != null){
				if(n.data == key){
					p.next = n.next;
					break;
				}
				else{
					p = n;
					n = n.next;
				}
			}
		}
	}
	
	/** Search for the position of the key
	 * Iterate again to delete the node at that position
	 */
	public void deleteKeyLast(int key){
		if(head.data == key && head.next == null){
			head = null;
		}
		else{
			Node n = head, p=n;
			int i=1, pos=0;
			while(n.next != null){
				if(n.data == key){
					pos = i;
				}
				n = n.next;
				i++;
			}
			n = head;
			i=1;
			while(i<pos && n.next != null){
				p = n;
				n = n.next;
				i++;
			}
			p.next = n.next;
		}
	}
	
	/** Search X and Y nodes and keep track of current and next
	 * First swap the next pointers
	 * Then swap the current pointers using temp
	 */
	public void swapNodesByKeys(int x, int y){
		Node currX = head, prevX = null;
		while(currX != null && currX.data != x){
			prevX = currX;
			currX = currX.next;
		}
		Node currY = head, prevY = null;
		while(currY != null && currY.data != y){
			prevY = currY;
			currY = currY.next;
		}
		if(prevX != null)
			prevX.next = currY;
		else
			currY = head;
		if(prevY != null)
			prevY.next = currX;
		else
			currY = head;
		
		Node temp = currX.next;
		currX.next = currY.next;
		currY.next = temp;
	}
	
	/** Take 2 pointers. 
	 * One moves 1 space and other 2 spaces till 2nd pointer is not null
	 */
	public int printMiddle(){
		Node n = head, p = head.next;
		int x = -1;
		while(p!=null){
			if(p.next == null){
				x = n.data;
				break;
			}
			else if(p.next.next == null){
				x = n.next.data;
				break;
			}
			else{
				n = n.next;
				p = p.next.next;
			}
		}
		return x;
	}
	
	/** Current is head, Next & Previous are null
	 * Iterate trough the linked list. 
	 * In loop, change next to prev, prev to current and current to next.
	 * Change head to prev
	 */
	/*public void reverse(){
		Node p=null, c=head, n=null;
		while(c!=null){
			n = c.next;
			c.next = p;
			p = c;
			c = n;
		}
		head = p;
	}*/
	
	public void reverse(){
		if(head == null || head.next == null)
			return;
		Node p=null, c=head, n=head.next;
		reverseUtil(p, c, n);
	}
	
	private void reverseUtil(Node p, Node c, Node n) {
		if(n == null) {
			c.next = p;
			head = c;
			return;
		}
		c.next = p;
		p = c;
		c = n;
		reverseUtil(p, c, c.next);
	}

	/** Move 1st pointer 1 ahead and 2nd pointer 2 ahead.
	 * If at any point 1st and 2nd pointer are same node, then loop is present
	 */
	public boolean isLoop(){
		Node n1=head, n2=head;
		int i=0;
		while (n2 != null && n1 != null && n2.next != null){
			n1 = n1.next;
			n2 = n2.next;
			if(n2.next != null){
				n2 = n2.next;
			}
			else{
				i=1;
				break;
			}
		}
		if(n1 != n2 || i==1)
			return false;
		else
			return true;
	}
	
	/** Check if either of the nodes is null.
	 * If any node is null, return the other
	 * else if both are not null, check which is smaller
	 * result = smaller
	 * result.next = mergeSort(bigger, smaller.next)
	 * return result
	 */
	public static Node mergeSort(Node p, Node n){
		Node t=null;
		if(p == null)
			return n;
		if(n == null)
			return p;
		if(p.data <= n.data){
			t = p;
			t.next = mergeSort(n, p.next);
		}
		else{
			t = n;
			t.next = mergeSort(p, n.next);
		}
		return t;
	}
	
	/** Find middle node using 2 ptrs. 
	 * If length is even (fast_ptr will be null) the move slow_ptr to next node.
	 * Reverse second half (starting from slow_ptr)
	 * Make next of first_ptr null, thus dividing the list in 2
	 * Then check first list and second list
	 * If all corresponding (first & second lists) nodes data matches then true 
	 * else false
	 */
	public boolean isPalindrome(){
		Node n = head, b=head, p = head, prev=null, middle=null;
		int x = -1;
		while(p!=null && p.next != null){
			p = p.next.next;
			prev = n;
			n = n.next;
		}
		if(p != null){
			middle = n;
			n = n.next;
		}
		prev.next = null;
		Node z=null, c=n, y=null;
		while(c!=null){
			y = c.next;
			c.next = z;
			z = c;
			c = y;
		}
		while(z.next != null || b.next != null){
			if(z.data != b.data){
				x = 0;
				break;
			}
			else{
				z = z.next;
				b = b.next;
			}
		}
		if(x == 0)
			return false;
		else
			return true;
	}
	
	/** Recursive reverse k nodes
	 * After reversal, if n is not null, then head.next = reverseInGroupsOfK(n,k)
	 * return p
	 */
	public Node reverseInGroupsOfK(Node head, int k){
		Node c = head, p=null, n=null;
		int i=0;
		while(i<k && c != null){
			n = c.next;
			c.next = p;
			p = c;
			c = n;
			i++;
		}
		if(n != null){
			head.next = reverseInGroupsOfK(n, k);
		}
		return p;
	}
	
	/** Recursive reverse k nodes
	 * Skip the next K nodes
	 * if current is not null, then current points to end of 2nd list
	 * current.next = reverseAlternateKNodes(c.next, k);
	 * return p
	 */
	public Node reverseAlternateKNodes(Node head, int k){
		Node c = head, p=null, n=null;
		int i=0;
		while(i<k & c != null){
			n = c.next;
			c.next = p;
			p = c;
			c = n;
			i++;
		}
		if(n != null){
			head.next = c;
		}
		int count=0;
		while(count<k-1 && c != null){
			c = c.next;
			count++;
		}
		if(c != null){
			c.next = reverseAlternateKNodes(c.next, k);
		}
		return p;
	}
	
	/** Reverse the list
	 * Traverse the list and keep track of MAX
	 * If next.data < max, delete next
	 * else MAX = next.data
	 * Again Reverse the list
	 */
	public void deleteNodesWithGreaterValueOnRight(){
		reverse();
		while(head.data < head.next.data){
			head = head.next;
		}
		Node n = head.next, p = head;
		int max = p.data;
		while(n != null){
			if(n.data < max){
				n = n.next;
				p.next = n;
			}
			else{
				max = n.data;
				p = n;
				n = n.next;
			}
		}
		reverse();
	}
	
	/** Move tail to end.
	 * Move head to first even and if any odds before first even, move them all to end
	 * reorganize tail and store it
	 * Till the tail is reached, for every node, if it is odd, move to end
	 */
	public void moveEvenBeforeOddInSameOrder(){
		Node n = head, p=null, tail=head, t=null, a=null, x=null, y=null;
		while(tail.next != null){
			tail = tail.next;
		}
		while(head != null){
			if(head.data % 2 != 0){
				p = head;
				head = head.next;
			}
			else
				break;
		}
		if(p != null){
			p.next = null;
			tail.next = n;
			tail = p;
		}
		a = head;
		t = head.next;
		x = tail;
		while(t != null){
			y = t;
			if(t.data % 2 != 0){
				a.next = t.next;
				t.next = null;
				tail.next = t;
				tail = t;
				t = a.next;
			}
			else{
				a = a.next;
				t = t.next;
			}
			if(y == x)
				break;
		}
	}
	
	/** Take temp node and point it to 1st list and 2nd list at alternate positions
	 * by recursively calling the function.
	 * Take a flag, to check which one was last accessed
	 */
	public static Node mergeTwoListsAlternatePostions(Node a, Node b, int c){
		Node res = null;
		if(a == null)
			return b;
		if(b == null)
			return a;
		if(c == 0){
			res = a;
			res.next = mergeTwoListsAlternatePostions(a.next, b, 1);
		}
		if(c == 1){
			res = b;
			res.next = mergeTwoListsAlternatePostions(a, b.next, 0);
		}
		return res;
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.insertAtFront(5);
		ll.insertAtFront(4);
		ll.insertAtFront(3);
		ll.insertAtFront(2);
		ll.insertAtFront(1);
		ll.listAllNodes();
		System.out.println(ll.length());
		ll.insertAtX(7, 3);
		ll.listAllNodes();
		ll.insertAtEnd(8);
		ll.listAllNodes();
		ll.deleteFirst();
		ll.listAllNodes();
		ll.deleteLast();
		ll.listAllNodes();
		ll.deleteAtX(3);
		ll.listAllNodes();
		ll.insertAtFront(4);
		ll.insertAtFront(3);
		ll.insertAtFront(2);
		ll.insertAtFront(1);
		ll.listAllNodes();
		ll.deleteKeyFirst(2);
		ll.listAllNodes();
		ll.deleteKeyLast(2);
		ll.listAllNodes();
		ll.swapNodesByKeys(3, 7);
		ll.listAllNodes();
		System.out.println(ll.printMiddle());
		System.out.println("Reverse");
		ll.reverse();
		ll.listAllNodes();
		LinkedList ll1 = new LinkedList();
		ll1.insertAtFront(9);
		ll1.insertAtFront(7);
		ll1.insertAtFront(5);
		ll1.insertAtFront(3);
		ll1.insertAtFront(1);
		LinkedList ll2 = new LinkedList();
		ll2.insertAtFront(8);
		ll2.insertAtFront(6);
		ll2.insertAtFront(2);
		LinkedList ll3 = new LinkedList();
		ll3.head = mergeSort(ll1.head, ll2.head);
		ll3.listAllNodes();
		LinkedList ll4 = new LinkedList();
		ll4.insertAtFront(1);
		ll4.insertAtFront(2);
		ll4.insertAtFront(3);
		ll4.insertAtFront(2);
		ll4.insertAtFront(1);
		System.out.println(ll4.isPalindrome());
		ll1.listAllNodes();
		ll1.head = ll1.reverseInGroupsOfK(ll1.head, 3);
		ll1.listAllNodes();
		ll1.head = ll1.reverseAlternateKNodes(ll1.head, 3);
		ll1.listAllNodes();
		ll1 = new LinkedList();
		ll1.insertAtFront(3);
		ll1.insertAtFront(2);
		ll1.insertAtFront(6);
		ll1.insertAtFront(5);
		ll1.insertAtFront(11);
		ll1.insertAtFront(10);
		ll1.insertAtFront(15);
		ll1.insertAtFront(12);
		ll1.deleteNodesWithGreaterValueOnRight();
		ll1.listAllNodes();
		ll1 = new LinkedList();
		ll1.insertAtFront(3);
		ll1.insertAtFront(2);
		ll1.insertAtFront(6);
		ll1.insertAtFront(5);
		ll1.insertAtFront(11);
		ll1.insertAtFront(10);
		ll1.insertAtFront(15);
		ll1.insertAtFront(12);
		ll1.listAllNodes();
		ll1.moveEvenBeforeOddInSameOrder();
		ll1.listAllNodes();
		ll2.listAllNodes();
		LinkedList ll5 = new LinkedList();
		ll5.head = mergeTwoListsAlternatePostions(ll1.head, ll2.head, 0);
		ll5.listAllNodes();
		
	}
}
