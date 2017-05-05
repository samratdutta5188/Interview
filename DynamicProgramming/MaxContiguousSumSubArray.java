package com.dynamicprogramming;

public class MaxContiguousSumSubArray {

	public static void main(String[] args) {
		System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
	}
	
	/**
	 * Find the contiguous subarray within an array (containing at least one number) 
	 * which has the largest sum.
	 * 
	 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
	 */
	/**
	 * initialize T[0] = A[0], max = T[0]
	 * T[i] = A[i], if T[i-1] < 0
	 * T[i] = A[i] + T[i-1]
	 * max = max(T[i], max)
	 */
	public static int maxSubArray(int arr[]){
		int T[] = new int[arr.length];
		T[0] = arr[0];
		int max = T[0];
		for(int i=1; i<arr.length; i++){
			T[i] = arr[i] + T[i-1] > 0 ? T[i-1] : 0;
			max = Math.max(T[i], max);
		}
		return max;
	}

}
