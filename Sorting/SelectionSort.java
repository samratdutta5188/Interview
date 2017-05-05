package com.sorting;

import java.util.ArrayList;

public class SelectionSort {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(2);
		arr.add(6);
		arr.add(3);
		arr.add(8);
		arr.add(9);
		arr.add(1);
		arr.add(7);
		arr.add(5);
		arr = selectionSort(arr);
		display(arr);
	}
	
	/**
	 * Take two loops
	 * For inner loop search for the min number and store the position
	 * Interchange it with the outer loop current position
	 * 
	 * In place sorting
	 * 
	 * Best Case - O(n^2)
	 * Average Case - O(n^2)
	 * Best Case - O(n^2)
	 * 
	 */
	public static ArrayList<Integer> selectionSort(ArrayList<Integer> a){
		int l = a.size();
		for(int i=0;i<l-1;i++){
			int min = i;
			for(int j=i;j<l;j++){
				if(a.get(j) < a.get(min)){
					min = j;
				}
			}
			int temp = a.get(i);
			a.set(i, a.get(min));
			a.set(min, temp);
		}
		return a;
	}
	
	public static void display(ArrayList<Integer> a){
		int l = a.size();
		for(int i=0;i<l;i++){
			System.out.print(a.get(i) + " ");
		}
	}

}
