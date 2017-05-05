package com.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class InsertionSort {

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
		arr = insertionSort(arr);
		display(arr);
	}
	
	/**
	 * Partition the array into two halves, 
	 * first storing the sorted part of the array and second the rest of the unsorted elements
	 * For every element of the second half, 
	 * check its correct position in the first half 
	 * and then move all the elements greater than this element one place to the right 
	 * to make way for the correct position for this element 
	 * 
	 * In place sorting
	 * 
	 * Best Case - O(n)
	 * Average Case - O(n^2)
	 * Best Case - O(n^2)
	 * 
	 * Better than Selection and Bubble sorts
	 * 
	 */
	public static ArrayList<Integer> insertionSort(ArrayList<Integer> a){
		int j=1,l=a.size();
		if(l < 2)
			return a;
		else{
			while(j<l){
				int temp = a.get(j), i=j-1;
				while(a.get(i) > temp){
					a.set(i+1, a.get(i));
					i--;
					if(i<0)
						break;
				}
				a.set(i+1, temp);
				j++;
			}
			return a;
		}
	}
	
	public static void display(ArrayList<Integer> a){
		int l = a.size();
		for(int i=0;i<l;i++){
			System.out.print(a.get(i) + " ");
		}
	}


}
