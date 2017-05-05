package com.codingwebsites.Leetcode;

import java.util.Arrays;

public class DivideAndConquer {

	public static void main(String[] args) {
		int nums[] = {2,4,3,5,1};
		System.out.println(reversePairs(nums));
	}
	
	/**
	 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
	 * You need to return the number of important reverse pairs in the given array.
	 */
	public static int reversePairs(int[] nums) {
		return mergeSoln(nums, 0, nums.length-1);
    }

	private static int mergeSoln(int[] nums, int s, int e) {
		if(s>=e) return 0;
		int mid = s + (e-s)/2;
		int c = mergeSoln(nums, s, mid) + mergeSoln(nums, mid+1, e);
		for(int i=s, j=mid+1; i<mid+1; i++){
			while(j<=e && (nums[i]/2.0 > nums[j])) j++;
			c += j-(mid+1);
		}
		Arrays.sort(nums, s, e+1);
		return c;
	}
	
	/* O(n^2) solution
	public static int reversePairs(int[] nums) {
        int l = nums.length, c=0;
        for(int i=0; i<l-1; i++){
        	for(int j=i+1; j<l; j++){
        		float x = (float) (nums[i]/2.0), y = nums[j];
            	if(x > y){
            		c++;
            	}
            }
        }
        return c;
    }*/

}
