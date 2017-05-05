package com.ds;

public class Heap {
	
	private static int size;
	private static int last_filled;
	private static int heap_arr[];
	
	Heap(int s){
		size = s;
		last_filled = -1;
		heap_arr = new int[size];
	}
	
	private static int parent(int i){ 
		return i/2;
	}
	
	private static int leftChild(int i){
		return 2*i;
	}
	
	private static int rightChild(int i){
		return 2*i +1;
	}
	
	public int getMax(){
		if(last_filled != -1)
			return heap_arr[0];
		return -1;
	}
	
	public void insert(int p){
		if(last_filled == size-1)
			return;
		last_filled++;
		heap_arr[last_filled] = p;
		shiftUp(last_filled);
	}

	private void shiftUp(int i) {
		while(i > 0 && heap_arr[i] > heap_arr[parent(i)]){
			int temp = heap_arr[i];
			heap_arr[i] = heap_arr[parent(i)];
			heap_arr[parent(i)] = temp;
			i = parent(i);
		}
	}
	
	private void shiftDown(int i) {
		int n=i, l=leftChild(i), r=rightChild(i);
		if(l < size && heap_arr[l] > heap_arr[n])
			n = l;
		if(r < size && heap_arr[r] > heap_arr[n])
			n = r;
		if(i != n){
			int temp = heap_arr[i];
			heap_arr[i] = heap_arr[n];
			heap_arr[n] = temp;
			shiftDown(n);
		}
	}
	
	public void remove(int i){
		heap_arr[i] = Integer.MAX_VALUE;
		shiftUp(i);
		extractMax();
	}

	public int extractMax() {
		int n = heap_arr[0];
		heap_arr[0] = heap_arr[last_filled];
		last_filled--;
		shiftDown(0);
		return n;
	}
	
	public void changePriority(int i, int n){
		if(i <= last_filled){
			int old = heap_arr[i];
			heap_arr[i] = n;
			if(old > n)
				shiftUp(i);
			else
				shiftDown(i);
		}
	}
	
	public void print(){
		System.out.println();
		for(int i=0; i<=last_filled; i++)
			System.out.print(heap_arr[i] + ", ");
	}

	public static void main(String[] args) {
		Heap max_heap = new Heap(9);
		max_heap.insert(18);
		max_heap.insert(29);
		max_heap.insert(7);
		max_heap.insert(11);
		max_heap.print();
		max_heap.insert(14);
		max_heap.insert(42);
		max_heap.insert(12);
		max_heap.print();
		max_heap.insert(13);
		max_heap.insert(18);
		max_heap.print();
		System.out.println();
		System.out.println(max_heap.getMax());
		System.out.println(max_heap.extractMax());
		max_heap.print();
		max_heap.changePriority(4, 37);
		max_heap.print();
		max_heap.remove(7);
		max_heap.print();
	}

}
