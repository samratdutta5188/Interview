package com.sorting;

import java.util.ArrayList;

public class BubbleSort {

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
		arr = bubbleSort(arr);
		display(arr);
	}

	/**
	 * 
	 * Take two loops
	 * In the inner loop keep checking two adjacent elements and if a left element is greater than right element, swap them.
	 * The above step will make sure for every pass in the outer loop, the largest number of that subarray is placed at the end.
	 * The flag check is added to avoid checking already sorted lists
	 * 
	 * In place sorting
	 * 
	 * Best Case - O(n)
	 * Average Case - O(n^2)
	 * Best Case - O(n^2)
	 *  
	 * Better than Selection Sort
	 * 
	 */
	public static ArrayList<Integer> bubbleSort(ArrayList<Integer> a){
		int l = a.size();
		for(int i=0; i<l; i++){
			boolean flag = false;
			for(int j=0; j<l-i-1; j++){
				if(a.get(j) > a.get(j+1)){
					int temp = a.get(j);
					a.set(j, a.get(j+1));
					a.set(j+1, temp);
					flag = true;
				}
			}
			if(flag == false)
				break;
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
