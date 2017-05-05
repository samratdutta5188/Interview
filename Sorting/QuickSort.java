package com.sorting;

import java.util.ArrayList;

public class QuickSort {

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
		quickSort(arr, 0, arr.size() -1);
		display(arr);
	}

	/**
	 * 
	 * Partitions the list based on a pivot with all elements greater than the pivot are
	 * on the right and all elements smaller than pivot are on the left of the pivot.
	 * gets this partition index.
	 * Then recursively calls quickSort with the left and right halves of the partition index.
	 * 
	 * Divide and Conquer
	 * Recursion
	 * In place sorting
	 * 
	 * Average Case TC - O(nlogn)
	 * Worst Case TC - O(n^2)
	 * Space Complexity - O(1)
	 * 
	 *  
	 * Better than Selection Sort, Insertion Sort, Bubble Sort
	 * 
	 */
	public static void quickSort(ArrayList<Integer> a, int start, int end){
		if(start < end){
			int pIndex = partition(a, start, end);
			quickSort(a, start, pIndex-1);
			quickSort(a, pIndex+1, end);
		}
	}
	
	public static int partition(ArrayList<Integer> a, int start, int end){
		int pivot = a.get(end);
		int pIndex = start;
		for(int i=start; i<end; i++){
			if(a.get(i) <= pivot){
				int temp = a.get(i);
				a.set(i, a.get(pIndex));
				a.set(pIndex, temp);
				pIndex++;
			}
		}
		a.set(end, a.get(pIndex));
		a.set(pIndex, pivot);
		return pIndex;
	}
	
	public static void display(ArrayList<Integer> a){
		int l = a.size();
		for(int i=0;i<l;i++){
			System.out.print(a.get(i) + " ");
		}
	}

}
