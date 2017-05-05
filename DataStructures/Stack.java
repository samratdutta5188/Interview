package com.ds;

import java.util.Iterator;


public class Stack<K> implements Iterable<K> {
	
	public class MinStack {

	    Stack<Integer> stack;
	    int min = Integer.MAX_VALUE;
	    
	    public MinStack() {
	        stack = new Stack<Integer>();
	    }
	    
	    public void push(int x) {
	        if(x <= min){
	            stack.push(min);
	            min = x;
	        }
	        stack.push(x);
	    }
	    
	    public void pop() throws Exception {
	        if(stack.pop().data == min)
	            min = stack.pop().data;
	    }
	    
	    public int top() throws Exception {
	        return stack.peek();
	    }
	    
	    public int getMin() {
	        return min;
	    }
	}
	
	class StackUsingQueues<K> {
		
		Queue<Integer> queue;
		
		public StackUsingQueues() {
			queue = new Queue<Integer>();
		}
		
		/** Push element x onto stack. */
		public void push(K x) {
	        queue.enQueue((Integer) x);
	    }
	    
	    /** Removes the element on top of the stack and returns that element. */
	    public int pop() throws Exception {
	    	Queue<Integer> q = new Queue<Integer>();
	        if(!empty()){
	        	while(queue.size() > 1){
	        		q.enQueue(queue.deQueue());
	        	}
	        	int res = queue.deQueue();
	        	while(!q.isEmpty()) {
	        		queue.enQueue(q.deQueue());
	        	}
	        	return res;
	        } else throw new Exception("Queue is Empty");
	    }
	    
	    /** Get the top element. */
	    public int top() throws Exception {
	    	if(!empty()){
	        	return queue.peek().data;
	        } else throw new Exception("Queue is Empty");
	    }
	    
	    /** Returns whether the stack is empty. */
	    public boolean empty() {
	        return queue.isEmpty();
	    }
	}
	
	class Node {
		K data;
		Node next;
		public Node(K data) {
			this.data = data;
			this.next = null;
		}
	}
	
	Node head;
	
	Stack(){
		head = null;
	}
	
	public void push(K n){
		Node node = new Node(n);
		node.next = head;
		head = node;
	}
	
	public Node pop() throws Exception{
		if(head != null){
			Node n = head;
			head = head.next;
			return n;
		}
		throw new Exception("Stack is empty");
	}
	
	public K peek() throws Exception{
		if(head != null){
			return head.data;
		}
		throw new Exception("Stack is empty");
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public int size(){
		Node n = head;
		int count = 0;
		while(n!=null){
			count++;
			n=n.next;
		}
		return count;
	}
	
	public void print(){
		Node n = head;
		while(n!=null){
			System.out.print(n.data + ", ");
			n=n.next;
		}
	}
	
	public void printIterator(Stack<K> s){
		Iterator<K> itr = s.iterator();
		while(itr.hasNext()){
			System.out.print(itr.next() + ", ");
		}
	}
	
	public void printForeach(Stack<K> s){
		for(K k : s){
			System.out.print(k + ", ");
		}
	}
	
	@Override
	public Iterator<K> iterator() {
		return new ListIterator();
	}
	
	class ListIterator<K> implements Iterator<K>{
		
		private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public K next() {
			K data = (K) current.data;
			current = current.next;
			return data;
		}

		@Override
		public void remove() {
			
		}
		
	}

	public static void main(String[] args) throws Exception {
		Stack<Integer> s = new Stack<Integer>();
		s.push(10);
		s.push(5);
		s.push(7);
		s.push(2);
		s.print();
		System.out.println();
		System.out.println(s.peek());
		System.out.println(s.pop().data);
		System.out.println(s.peek());
		s.push(21);
		s.push(9);
		System.out.println();
		s.print();
		System.out.println();
		System.out.println(s.size());
		System.out.println();
		System.out.println(s.isEmpty());
		System.out.println();
		s.printIterator(s);
		System.out.println();
		s.printForeach(s);
	}

}
