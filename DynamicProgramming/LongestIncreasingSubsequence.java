package com.dynamicprogramming;

public class LongestIncreasingSubsequence {
	static int T[];

	public static void main(String[] args) {
		int a[] = {3, 4, -1, 0, 6, 2, 3};
		System.out.println(longestIncreasingSubsequence(a));
	}

	/**
	 * Create temp array of size equal to length of input array
	 * Make all entries of temp as 1
	 * i = 1, j = 0
	 * iterate the input array and update count of temp array if a[j] < a[i]
	 * 
	 * Recurrence Relation
	 * T[i] = T[j] + 1, if(a[j] < a[i])
	 */
	private static int longestIncreasingSubsequence(int[] a) {
		int l = a.length, result=0;
		T = new int[l];
		for(int i=0; i<l; i++){
			T[i] = 1;
		}
		for(int i=1; i<l; i++){
			for(int j=0; j<i; j++){
				if(a[j] < a[i])
					T[i] = T[j]+1;
			}
		}
		for(int i=0; i<l; i++){
			if(T[i] > result)
				result = T[i];
		}
		return result;
	}

}
