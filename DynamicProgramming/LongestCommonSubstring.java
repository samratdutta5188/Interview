package com.dynamicprogramming;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		System.out.println(longestCommonSubstring("abcdaf", "zbcdf"));
	}

	/**
	 * initialize T[][] = T[s2.length+1][s1.length+1]
	 * if(i==0 || j==0), T[i][j] = 0
	 * if(s2.charAt(i-1) != s1.charAt(j-1)), T[i][j] = 0
	 * else, T[i][j] = 1 + T[i-1][j-1];
	 * Then iterate through T[][] and find max
	 */
	private static int longestCommonSubstring(String s1, String s2) {
		int T[][] = new int[s2.length()+1][s1.length()+1];
		for(int i=0; i<=s2.length(); i++){
			for (int j=0; j<=s1.length(); j++) {
				if(i == 0 || j == 0)
					T[i][j] = 0;
				else if(s2.charAt(i-1) != s1.charAt(j-1))
					T[i][j] = 0;
				else
					T[i][j] = 1 + T[i-1][j-1];
			}
		}
		int max = 0;
		for(int i=0; i<=s2.length(); i++){
			for (int j=0; j<=s1.length(); j++) {
				if(T[i][j] > max)
					max = T[i][j];
					
			}
		}
		return max;
	}

}
