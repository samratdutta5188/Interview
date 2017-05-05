package com.ds;


public class Queue<K> {
	
	class QueueUsingStacks {
		
		Stack<Integer> stack;
		
		public QueueUsingStacks() {
			this.stack = new Stack<Integer>();
		}
		
		public void push(int x) {
	        stack.push(x);
	    }
	    
	    /** Removes the element from in front of queue and returns that element. */
	    public int pop() throws Exception {
	    	Stack<Integer> s = new Stack<Integer>();
	        while(stack.size() > 1)
	        	s.push(stack.pop().data);
	        int res = stack.pop().data;
	        while(!s.isEmpty())
	        	stack.push(s.pop().data);
	        return res;
	    }
	    
	    /** Get the front element. */
	    public int peek() throws Exception {
	    	Stack<Integer> s = new Stack<Integer>();
	        while(stack.size() > 1)
	        	s.push(stack.pop().data);
	        int res = stack.peek();
	        while(!s.isEmpty())
	        	stack.push(s.pop().data);
	        return res;
	    }
	    
	    /** Returns whether the queue is empty. */
	    public boolean empty() {
	        return stack.isEmpty();
	    }
	}
	
	@SuppressWarnings("hiding")
	class Node<K>{
		K data;
		Node<K> next;
		
		public Node(K data){
			this.data = data;
			this.next = null;
		}
	}
	
	Node<K> front, rear;
	private int size;
	public Queue(){
		front = rear = null;
		size=0;
	}
	
	public void enQueue(K n){
		Node<K> node = new Node<K>(n);
		if(front == null){
			front = rear = node;
		} else {
			rear.next = node;
			rear = node;
		}
		size++;
	}
	
	public K deQueue() throws Exception{
		if(!isEmpty()){
			Node<K> n = front;
			front = front.next;
			size--;
			return n.data;
		} else throw new Exception("Queue is Empty");
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return front == null;
	}
	
	public void print(){
		Node<K> n = front;
		while(n!=null){
			System.out.print(n.data + ", ");
			n = n.next;
		}
	}
	
	public Node<K> peek(){
		return isEmpty() ? null : new Node<K>(rear.data);
	}
	
	public Node<K> poll(){
		if(!isEmpty()){
			Node<K> n = front;
			front = front.next;
			return n;
		} else return null;
	}

	public static void main(String[] args) throws Exception {
		Queue<Integer> q = new Queue<Integer>();
		q.enQueue(10);
		q.enQueue(5);
		q.enQueue(7);
		q.print();
		System.out.println();
		System.out.println(q.deQueue());
		q.print();
		q.enQueue(2);
		System.out.println();
		q.print();
		System.out.println();
		System.out.println(q.peek().data);
		System.out.println();
		System.out.println(q.poll().data);
		System.out.println();
		q.print();
	}

}
