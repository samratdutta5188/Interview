package com.sorting;

import java.util.ArrayList;

public class HeapSort {
	
	private static ArrayList<Integer> arr = new ArrayList<Integer>();

	public static void main(String[] args) {
		arr.add(2);
		arr.add(6);
		arr.add(3);
		arr.add(8);
		arr.add(9);
		arr.add(1);
		arr.add(7);
		arr.add(5);
		heapSort();
		display(arr);
	}
	
	public static void heapSort(){
		int l=arr.size();
		for(int i=l/2-1; i>=0; i--){
			shiftDown(i, l);
		}
		for(int i=l-1; i>=0; i--){
			int temp = arr.get(0);
			arr.set(0, arr.get(i));
			arr.set(i, temp);
			shiftDown(0, i);
		}
	}
	
	private static int leftChild(int i){
		return 2*i + 1;
	}
	
	private static int rightChild(int i){
		return 2*i + 2;
	}
	
	private static void shiftDown(int i, int size) {
		int n=i, l=leftChild(i), r=rightChild(i);
		if(l < size && arr.get(l) > arr.get(n))
			n = l;
		if(r < size && arr.get(r) > arr.get(n))
			n = r;
		if(i != n){
			int temp = arr.get(i);
			arr.set(i, arr.get(n));
			arr.set(n, temp);
			shiftDown(n, size);
		}
	}
	
	public static void display(ArrayList<Integer> a){
		int l = a.size();
		for(int i=0;i<l;i++){
			System.out.print(a.get(i) + " ");
		}
	}

}
