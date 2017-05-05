package com.sorting;

import java.util.ArrayList;

public class MergeSort {

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
		mergeSort(arr);
		display(arr);
		System.out.println();
		arr = new ArrayList<Integer>();
		arr.add(2);
		arr.add(6);
		arr.add(3);
		arr.add(8);
		arr.add(9);
		arr.add(1);
		arr.add(7);
		arr.add(5);
		bottomUpMergeSort(arr);
		display(arr);
	}

	/**
	 * 
	 * Divides the initial array into two halves and recursively keeps on call 
	 * mergeSort the further divide the arrays till there are not more than 1 element in the array
	 * Then it calls the merge function to merge the lists into sorted order
	 * 
	 * Divide and Conquer
	 * Recursion
	 * Stable
	 * Not in place sorting
	 * 
	 * Worst Case TC - O(nlogn)
	 * Space Complexity - O(n)
	 * 
	 *  
	 * Better than Selection Sort, Insertion Sort, Bubble Sort
	 * 
	 */
	public static void mergeSort(ArrayList<Integer> a){
		int l = a.size(), i;
		if(l < 2)
			return;
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		int mid = l/2;
		for(i=0;i<mid;i++){
			left.add(a.get(i));
		}
		for(i=mid;i<l;i++){
			right.add(a.get(i));
		}
		mergeSort(left);
		mergeSort(right);
		merge(a, left, right);
	}

	public static void merge(ArrayList<Integer> a, ArrayList<Integer> left, ArrayList<Integer> right){
		int ll = left.size(), lr = right.size(), i=0, j=0, k=0;
		while(i<ll && j<lr){
			if(left.get(i) <= right.get(j)){
				a.set(k, left.get(i));
				i++;
			} else {
				a.set(k, right.get(j));
				j++;
			}
			k++;
		}
		while(i<ll){
			a.set(k, left.get(i));
			i++;
			k++;
		}
		while(j<lr){
			a.set(k, right.get(j));
			j++;
			k++;
		}
	}
	
	public static void bottomUpMergeSort(ArrayList<Integer> a){
		int l = a.size();
		for(int i=1; i<l; i=i+i){
			for(int j=0; j<l-i; j+=i+i){
				merge(a, getArray(a, j, j+i-1), getArray(a, j+i, Math.min(j+i+i-1, l-1)));
			}
		}
	}
	
	private static ArrayList<Integer> getArray(ArrayList<Integer> a, int start, int end) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i=start; i<=end; i++){
			res.add(a.get(i));
		}
		return res;
	}
	
	public static void display(ArrayList<Integer> a){
		int l = a.size();
		for(int i=0;i<l;i++){
			System.out.print(a.get(i) + " ");
		}
	}

}
