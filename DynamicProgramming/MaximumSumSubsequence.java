package com.dynamicprogramming;

/** 
 * Given an array of n positive integers. Write a program to find 
 * the sum of maximum sum subsequence of the given array such that 
 * the integers in the subsequence are in increasing order.
 */
public class MaximumSumSubsequence {

	public static void main(String[] args) {
		System.out.println(maxSum(new int[]{1, 101, 10, 2, 3, 100,4}));
	}
	
	/**
	 * initialize T[i] = arr[i]
	 * T[i] = max(T[i], T[j] + arr[i]), if(arr[j] < arr[i])
	 */
	public static int maxSum(int arr[]){
        int T[] = new int[arr.length];
        for (int i = 0; i < T.length; i++) {
            T[i] = arr[i];
        }
        for(int i=1; i < T.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]){
                    T[i] = Math.max(T[i], T[j] + arr[i]);
                }
            }
        }
        int max = T[0];
        for (int i=1; i < T.length; i++){
            if(T[i] > max){
                max = T[i];
            }
        }
        return max;
    }

}
