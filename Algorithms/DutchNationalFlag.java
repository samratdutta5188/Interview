package com.algorithms;

public class DutchNationalFlag {

	public static void main(String[] args) {
		int arr[] = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        sort012_DutchNationalFlag(arr, arr.length);
        printArray(arr, arr.length);
	}

	/**
	 * Lo := 1; Mid := 1; Hi := N;
	 * while Mid <= Hi do
	 * Invariant: a[1..Lo-1]=0 and a[Lo..Mid-1]=1 and a[Hi+1..N]=2; a[Mid..Hi] are unknown.
	 * case a[Mid] in
	 * 	 0: swap a[Lo] and a[Mid]; Lo++; Mid++
	 * 	 1: Mid++
	 * 	 2: swap a[Mid] and a[Hi]; Hi–
	 */
	private static void sort012_DutchNationalFlag(int[] arr, int length) {
		int low = 0, mid = 0, temp = 0, high = length-1;
		while(mid <= high){
			switch(arr[mid]) {
				case 0: 
					if(arr[mid] != arr[low]){
						temp = arr[mid];
						arr[mid] = arr[low];
						arr[low] = temp;
					}
					mid++;
					low++;
					break;
				case 1:
					mid++;
					break;
				case 2:
					if(arr[mid] != arr[high]){
						temp = arr[mid];
						arr[mid] = arr[high];
						arr[high] = temp;
					}
					high--;
					break;
			}
		}
	}
	
	private static void printArray(int[] arr, int length) {
		for(int i=0; i<length; i++){
			System.out.print(arr[i]+", ");
		}
	}

}
