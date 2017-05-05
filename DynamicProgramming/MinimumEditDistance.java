package com.dynamicprogramming;

public class MinimumEditDistance {
	
	private static int T[][];

	public static void main(String[] args) {
		System.out.println(minimumEditDistance("abcdef","azced"));
	}

	/**
	 * Recurrence Relation
	 * T[i][j] = T[i-1][j-1], if(str1[i-1] == str2[j-1])
	 * T[i][j] = Min (T[i-1][j], T[i-1][j-1], T[i][j-1] )
	 */
	private static int minimumEditDistance(String a, String b) {
		int la = a.length(), lb = b.length();
		T = new int[lb+1][la+1];
		for(int i=0; i<la+1; i++){
			T[0][i] = i;
		}
		for(int i=0; i<lb+1; i++){
			T[i][0] = i;
		}
		for(int i=1; i<lb+1; i++){
			for(int j=1; j<la+1; j++){
				if(b.charAt(i-1) == a.charAt(j-1))
					T[i][j] = T[i-1][j-1];
				else
					T[i][j] = 1 + Math.min(T[i-1][j], Math.min(T[i-1][j-1], T[i][j-1]));
			}
		}
		return T[lb][la];
	}

}
