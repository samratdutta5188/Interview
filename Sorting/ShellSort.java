package com.sorting;

import java.util.ArrayList;

public class ShellSort {

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
		arr = shellSort(arr);
		display(arr);
	}
	
	public static ArrayList<Integer> shellSort(ArrayList<Integer> a){
		int l = a.size(), h=1;
		while(h < l/3) 
			h = 3*h +1;
		while(h >= 1){
			for(int i=h; i<l; i++){
				for(int j=i; j>=h && a.get(j) < a.get(j-h); j-=h){
					int temp = a.get(j);
					a.set(j, a.get(j-h));
					a.set(j-h, temp);
				}
			}
			h = h/3;
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
