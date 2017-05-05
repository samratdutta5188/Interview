package com.dynamicprogramming;

public class LongestPalindromicSubsequence {
	
	private static int T[][];

	public static void main(String[] args) {
		System.out.println(longestPalindromicSubsequence("agbdba"));
	}

	private static int longestPalindromicSubsequence(String a) {
		int l = a.length();
		T = new int[l][l];
		for(int i=0; i<l; i++){
			T[i][i] = 1;
		}
		for(int k=2; k<=l; k++){
			for(int i=0; i<l-k+1; i++){
				int j = i+k-1;
				if(k == 2 && a.charAt(i) == a.charAt(j))
					T[i][j] = 2;
				else if(a.charAt(i) == a.charAt(j))
					T[i][j] = 2 + T[i+1][j-1];
				else
					T[i][j] = Math.max(T[i][j-1], T[i+1][j]);
			}
		}
		return T[0][l-1];
	}

}
