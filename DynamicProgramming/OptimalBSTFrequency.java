package com.dynamicprogramming;

public class OptimalBSTFrequency {
	
	private static int T[][];

	public static void main(String[] args) {
		int input[] = {10,12,16,21};
        int freq[] = {4,2,6,3};
        System.out.println(minCost(input, freq));
	}

	private static int minCost(int[] input, int[] freq) {
		int l = input.length;
		T = new int[l][l];
		for (int i=0; i<l; i++) {
			T[i][i] = freq[i];
		}
		for(int k=2; k<=l; k++){
            for(int i=0; i<=l-k; i++){
                int j = i+k-1;
                T[i][j] = Integer.MAX_VALUE;
                int sum = getSum(freq, i, j);
                for(int p=i; p <= j; p++){
                     int val = sum + (p-1 < i ? 0 : T[i][p-1]) + (p+1 > j ? 0 : T[p+1][j]) ;
                     if(val < T[i][j]){
                         T[i][j] = val;
                     }
                }
            }
        }
		return T[0][l-1];
	}

	private static int getSum(int[] freq, int j, int k) {
		int sum = 0;
		for (int i=j; i<=k; i++) {
			sum += freq[i];
		}
		return sum;
	}

}
