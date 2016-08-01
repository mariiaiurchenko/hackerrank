package com.spring.tutorial.HakerRank.Search;

import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
 */
public class ConnectedCellInAGrid {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int[][] matrex = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrex[i][j] = in.nextInt();
			}
		}
		System.out.println(maxConectedArea(matrex));
		in.close();
	}

	private static int maxConectedArea(int[][] matrex) {
		int max = 0;
		for (int i = 0; i < matrex.length; i++) {
			for (int j = 0; j < matrex[0].length; j++) {
				if (matrex[i][j] == 1) {
					int cur = countArea(matrex, i, j);
					max = Math.max(max, cur);
				}
			}
		}

		return max;
	}

	private static int countArea(int[][] matrex, int row, int col) {
		matrex[row][col] = 0;
		int rowStart = Math.max(row - 1, 0);
		int rowFinish = Math.min(row + 1, matrex.length - 1);
		int colStart = Math.max(col - 1, 0);
		int colFinish = Math.min(col + 1, matrex[0].length - 1);

		int count = 0;
		for (int i = rowStart; i <= rowFinish; i++) {
			for (int j = colStart; j <= colFinish; j++) {
				if (matrex[i][j] == 1) {
					count += countArea(matrex, i, j);
				}
			}
		}
		return count + 1;
	}
}
