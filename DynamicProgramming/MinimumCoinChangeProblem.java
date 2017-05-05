package com.dynamicprogramming;

public class MinimumCoinChangeProblem {
	
	private static int T[][];

	public static void main(String[] args) {
		int coins[] = {1, 5, 6, 8}, sum = 11;
		System.out.println(minimumCoinChange(coins, sum));
	}

	/**
	 * Recurrence Relation
	 * T[i][j] = T[i-1][j], if(j < coins[i])
	 * T[i][j] = Min ( T[i-1][j], 1 + T[i][j-coins[i]] )
	 */
	private static int minimumCoinChange(int[] coins, int sum) {
		int l = coins.length; 
		T = new int[l+1][sum+1];
		for(int j=1; j<sum+1; j++){
			T[0][j] = Integer.MAX_VALUE;
		}
		for(int i=1; i<l+1; i++){
			for(int j=1; j<sum+1; j++){
				if(j < coins[i-1]){
					T[i][j] = T[i-1][j];
				} else {
					T[i][j] = Math.min(T[i-1][j], 1 + T[i][j-coins[i-1]]);
				}
			}
		}
		return T[l][sum];
	}

}
