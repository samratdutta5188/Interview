package com.dynamicprogramming;

public class KnapsackProblem {
	
	private static int T[][];

	public static void main(String[] args) {
		int weight[] = {1, 3, 4, 5}, value[] = {1, 4, 5, 7};
		int W = 7, n = value.length;
		System.out.println(knapsack(weight, value, W, n));
	}

	/**
	 * Recurrence relation
	 * T[i][j] = T[i-1][j], if(j < wt[i])
	 * T[i][j] = max(val[i] + T[i-1][j-wt[i]], T[i-1][j])
	 */
	private static int knapsack(int[] weight, int[] value, int W, int n) {
		T = new int[n+1][W+1];
		for(int i=0; i<=n; i++){
			for(int j=0; j<=W; j++){
				if(i == 0 || j == 0)
					T[i][j] = 0;
				else if(j < weight[i-1])
					T[i][j] = T[i-1][j];
				else
					T[i][j] = Math.max(value[i-1] + T[i-1][j-weight[i-1]], T[i-1][j]);
			}
		}
		return T[n][W];
	}

}
