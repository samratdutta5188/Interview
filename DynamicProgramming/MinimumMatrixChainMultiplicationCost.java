package com.dynamicprogramming;

public class MinimumMatrixChainMultiplicationCost {

	public static void main(String[] args) {
		System.out.println(findCost(new int[]{4,2,3,5,3}));
	}
	
	/**
	 * Recurrence Relation
	 * T[i][j] = min(T[i][k] + T[k+1][j] + (arr[i].first * arr[k].second * arr[j].second))
	 */
	public static int findCost(int arr[]){
        int T[][] = new int[arr.length][arr.length];
        int q = 0;
        for(int l=2; l < arr.length; l++){
            for(int i=0; i < arr.length - l; i++){
                int j = i + l;
                T[i][j] = 1000000;
                for(int k=i+1; k < j; k++){
                    q = T[i][k] + T[k][j] + arr[i]*arr[k]*arr[j];
                    if(q < T[i][j]){
                        T[i][j] = q;
                    }
                }
            }
        }
        return T[0][arr.length-1];
    }

}
