package com.dynamicprogramming;

public class NumberOfPathsFromTopLeftToRightBottom {

	public static void main(String[] args) {
		System.out.println(uniquePaths(3, 7));
		System.out.println(uniquePathsWithObstacles(new int[][]{{0}}));
	}
	
	/**
	 * T[i][j] = 1, if i==0 || j==0
	 * T[i][j] = T[i-1][j] + T[i][j-1]
	 */
	public static int uniquePaths(int m, int n) {
        int T[][] = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
            	if(i==0 || j==0)
            		T[i][j] = 1;
            	else
            		T[i][j] = T[i - 1][j] + T[i][j - 1];
            }
        }
        return T[m-1][n-1];
    }
	
	/**
	 * Create 2D matrix of size [m+1][n+1]
	 * fill all values of 0th row and 0th column with 0
	 * T[i][j] = 1, if i==1 && j==1
	 * T[i][j] = 0, if obstacleGrid[i-1][j-1] == 1
	 * T[i][j] = T[i-1][j] + T[i][j-1]
	 */
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if(m == 0 || obstacleGrid[0][0] == 1)
            return 0;
        else {
            int T[][] = new int[m+1][n+1];
            for (int i = 0; i <= m; i++){
                T[i][0] = 0;
            }
            for (int i = 0; i <= n; i++){
                T[0][i] = 0;
            }
            for (int i = 1; i <= m; i++){
                for (int j = 1; j <= n; j++){
                    if(obstacleGrid[i-1][j-1] == 1)
                        T[i][j] = 0;
                    else if(i == 1 && j == 1)
                    	T[i][j] = 1;
                	else
                		T[i][j] = T[i - 1][j] + T[i][j - 1];
                }
            }
            return T[m][n];
        }
    }

}
