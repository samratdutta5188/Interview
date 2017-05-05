package com.dynamicprogramming;

public class EggDropping {

	public static void main(String[] args) {
		System.out.println(calculateMinEggs(2, 6));
	}
	
	/**
	 * T[i][j] = j, if(i == 1)
	 * if(i>j), T[i][j] = T[i-1][j]
	 * else, T[i][j] = 1 + max(min(T[i-1][k-1], T[i][j-k])), k = 1..j
	 */
	public static int calculateMinEggs(int eggs, int floors){
        
        int T[][] = new int[eggs+1][floors+1];
        int c =0;
        for(int i=0; i <= floors; i++){
            T[1][i] = i;
        }
        
        for(int i = 2; i <= eggs; i++){
            for(int j = 1; j <=floors; j++){
                T[i][j] = Integer.MAX_VALUE;
                for(int k = 1; k <=j ; k++){
                    c = 1 + Math.max(T[i-1][k-1], T[i][j-k]);
                    if(c < T[i][j]){
                        T[i][j] = c;
                    }
                }
            }
        }
        return T[eggs][floors];
    }

}
