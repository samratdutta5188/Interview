package com.dynamicprogramming;

public class MaximumSizeSubMatrixWithAllOnes {

	public static void main(String[] args) {
		int a[][] = {
					 {0, 1, 1, 0, 1}, 
                	 {1, 1, 0, 1, 0}, 
                	 {0, 1, 1, 1, 0},
                	 {1, 1, 1, 1, 0},
                	 {1, 1, 1, 1, 1},
                	 {0, 0, 0, 0, 0}
                	};
		printMaxSubMatrix(a);
	}

	private static void printMaxSubMatrix(int[][] a) {
		int r = a.length, c = a[0].length;
		int temp[][] = new int[r][c];
		for(int i=0; i<r; i++){
			temp[i][0] = a[i][0];
		}
		for(int i=0; i<c; i++){
			temp[0][i] = a[0][i];
		}
		for(int i=1; i<r; i++){
			for(int j=1; j<c; j++){
				if(a[i][j] == 1)
					temp[i][j] = 1 + Math.min(temp[i-1][j], Math.min(temp[i][j-1], temp[i-1][j-1]));
				else
					temp[i][j] = 0;
			}
		}
		int max=temp[0][0], max_i=0, max_j=0;
		for(int i=0; i<r; i++){
			for(int j=0; j<c; j++){
				if(temp[i][j] > max){
					max = temp[i][j];
					max_i = i;
					max_j = j;
				}
			}
		}
		for(int i=max_i; i>max_i - max; i--){
			for(int j=max_j; j>max_j - max; j--){
				System.out.print(a[i][j]+", ");
			}
			System.out.println();
		}
	}

}
