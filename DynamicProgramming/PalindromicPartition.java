package com.dynamicprogramming;

public class PalindromicPartition {

	public static void main(String[] args) {
		System.out.println(minCut("abcbm"));
	}
	
	/**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * https://leetcode.com/problems/palindrome-partitioning-ii/
     * 
     * if(str.substring(i,j) is palindrome), T[i][j] = 0
     * T[i][j] = min(1 + T[i][k] + T[k+1][j]), where k = i..j-1
     */
    public static int minCut(String str){
        if (str.length() == 0) {
            return 0;
        }
        int[] cut = new int[str.length()];
        boolean isPal[][] = new boolean[str.length()][str.length()];
        for (int i = 1; i < str.length(); i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (str.charAt(i) == str.charAt(j) && (i <= j + 1 || isPal[i - 1][j + 1])) {
                    isPal[i][j] = true;
                    min = Math.min(min, j == 0 ? 0 : 1 + cut[j - 1]);
                }
            }
            cut[i] = min;
        }
        return cut[str.length() - 1];
    }

}
