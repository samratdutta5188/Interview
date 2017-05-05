package com.dynamicprogramming;

public class SubsetSum {
	
	private static boolean T[][];

	public static void main(String[] args) {
		int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = set.length;
        System.out.println(isSubsetSum(set, n, sum));
	}
	
	/**
	 * Recurrence Relation
	 * T[i][j] = T[i-1][j], if i > set(j-1)
	 * T[i][j] = T[i-1][j] || T[i-1][j-set[i]
	 */
	private static boolean isSubsetSum(int[] set, int n, int sum) {
		T = new boolean[sum+1][n+1];
		for(int i=0; i<n+1; i++){
			T[0][i] = true;
		}
		for(int i=1; i<sum+1; i++){
			T[i][0] = false;
		}
		for(int i=1; i<sum+1; i++){
			for(int j=1; j<n+1; j++){
				T[i][j] = T[i][j-1];
				if(i >= set[j-1]){
					T[i][j] = T[i][j] || T[i - set[j-1]][j-1];
				}
			}
		}
		return T[sum][n];
	}

}
