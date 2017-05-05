package com.dynamicprogramming;

public class MaximumWaysCoinChange {

	public static void main(String[] args) {
		int coins[] = {1, 2, 3}, sum = 5;
		System.out.println(maximumWaysCoinChange(coins, sum));
	}

	private static int maximumWaysCoinChange(int[] coins, int sum) {
		int T[][] = new int[coins.length+1][sum+1];
		for(int i=0; i<=coins.length; i++){
			for(int j=0; j<=sum; j++){
				if(i == 0 || j == 0)
					T[i][j] = 0;
				else if(j < coins[i-1])
					T[i][j] = T[i-1][j];
				else if(j % i == 0)
					T[i][j] = Math.max(T[i-1][j], T[i][j-i]) + 1;
				else
					T[i][j] = Math.max(T[i-1][j], T[i][j-i]);
			}
		}
		return T[coins.length][sum];
	}
	
	private static int maximumWaysCoinChangeII(int[] coins, int sum) {
		int T[][] = new int[coins.length+1][sum+1];
		for(int i=0; i<coins.length; i++){
			for(int j=0; j<=sum; j++){
				if(i == 0 || j == 0)
					T[i][j] = 1;
				else if(j < coins[i-1])
					T[i][j] = T[i-1][j];
				else 
					T[i][j] = T[i-1][j] + T[i][j-coins[i-1]];
			}
		}
		return T[coins.length][sum];
	}

}
