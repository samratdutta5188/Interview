package com.dynamicprogramming;

public class FindMaximumConsecutiveOnes {

	public static void main(String[] args) {
		System.out.println(findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
	}
	
	/**
	 * initialize T[0] = A[0], max = T[0]
	 * T[i] = 0, if A[i] = 0
	 * T[i] = T[i-1] + 1
	 */
	public static int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int T[] = new int[n];
        T[0] = nums[0];
        int  max = T[0];
        for(int i=1; i<n; i++){
            T[i] = nums[i] == 1 ? T[i-1] + 1 : 0;
            max = Math.max(max, T[i]);
        }
        return max;
    }

}
