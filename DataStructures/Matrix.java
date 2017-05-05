package com.ds;

import java.util.ArrayList;

public class Matrix {

	public static void searchInRowWiseColumnWiseSortedMatrix(int a[][], int n, int x){
		int i=0, j=n-1;
		while(i<n && j>=0){
			if(a[i][j] == x){
				System.out.println("Found at [" + i + ", " + j + "]");
				break;
			}
			else if(a[i][j] > x){
				j--;
			}
			else
				i++;
		}
	}
	
	public static void printSpiral(int a[][], int n, int m){
		int k=0, l=0;
		while(k<m && l<n){
			for(int i=l; i<n; i++){
				System.out.print(a[k][i] + ", ");
			}
			k++;
			for(int i=k; i<m; i++){
				System.out.print(a[i][n-1] + ", ");
			}
			n--;
			if(k<m){
				for(int i=n-1; i>=l; i--){
					System.out.print(a[m-1][i] + ", ");
				}
				m--;
			}
			if(l<n){
				for(int i=m-1; i>=k; i--){
					System.out.print(a[i][l] + ", ");
				}
				l++;
			}
		}
	}
	
	/**
	 * First create transpose : swap(matrix[i][j], matrix[j][i])
	 * Flip: swap(matrix[i][j], matrix[i][matrix.length-1-j]
	 */
	public void rotate90degree(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
	
	/**
	 * Given a 2D board and a word, find if the word exists in the grid.
	 * The word can be constructed from letters of sequentially adjacent cell, 
	 * where "adjacent" cells are those horizontally or vertically neighboring. 
	 * The same letter cell may not be used more than once.
	 * 
	 * For example,
	 * Given board =
	 * [
	 * 	['A','B','C','E'],
	 * 	['S','F','C','S'],
	 * 	['A','D','E','E']
	 * ]
	 * word = "ABCCED", -> returns true,
	 * word = "SEE", -> returns true,
	 * word = "ABCB", -> returns false.
	 */
	static boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || 
        		board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }
        visited[i][j] = true;
        if(search(board, word, i-1, j, index+1) || 
           search(board, word, i+1, j, index+1) ||
           search(board, word, i, j-1, index+1) || 
           search(board, word, i, j+1, index+1)){
            return true;
        }
        visited[i][j] = false;
        return false;
    }

	public static void main(String[] args) {
		int mat[][] = new int[][]{	{10, 20, 30, 40},
									{15, 25, 35, 45},
									{27, 29, 37, 48},
									{32, 33, 39, 50}
								 };
		searchInRowWiseColumnWiseSortedMatrix(mat, 4, 29);
		printSpiral(mat, 4, 4);
	}

}
