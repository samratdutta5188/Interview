package com.dynamicprogramming;

public class LongestCommonSubsequence {
	
	private static int T[][];

	public static void main(String[] args) {
		String a = "abcdaf", b = "acbdf";
		System.out.println(longestCommonSubsequence(a, b));
		System.out.println(printLCS(a, b));
	}

	/**
	 * Recurrence relation
	 * T[i][j] = 1 + T[i-1][j-1], if(a[i] == b[i])
	 * T[i][j] = max(T[i-1][j], T[i][j-1])
	 */
	private static int longestCommonSubsequence(String a, String b) {
		int m = a.length(), n = b.length();
		T = new int[m+1][n+1];
		for(int i=0; i<=m; i++){
			for(int j=0; j<=n; j++){
				if(i==0 || j==0)
					T[i][j] = 0;
				else if(a.charAt(i-1) == b.charAt(j-1))
					T[i][j] = 1 + T[i-1][j-1];
				else
					T[i][j] = Math.max(T[i-1][j], T[i][j-1]);
			}
		}
		return T[m][n];
	}
	
	private static String printLCS(String a, String b) {
		int i=a.length(), j=b.length();
		String res = "";
		while(i > 0 && j > 0){
			if(a.charAt(i-1) == b.charAt(j-1)){
				res = a.charAt(i-1) + res;
				i--; j--;
			} else if(T[i-1][j] > T[i][j-1])
				i--;
			else
				j--;
		}
		return res;
	}

}
