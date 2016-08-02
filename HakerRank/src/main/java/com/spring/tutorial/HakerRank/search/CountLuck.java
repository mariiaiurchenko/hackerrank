package com.spring.tutorial.HakerRank.search;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Hakerrank: Count Luck
 * https://www.hackerrank.com/challenges/count-luck
 */
public class CountLuck {

	private static Set<Integer> set;
	private static int rows;
	private static int cols;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int rows = in.nextInt();
			int cols = in.nextInt();
			String[] matrix = new String[rows];
			for (int i = 0; i < rows; i++) {
				matrix[i] = in.next();
			}
			int guess = in.nextInt();
			answer.append(correctGuesses(matrix, guess)).append(
					System.getProperty("line.separator"));

		}
		System.out.println(answer);
		in.close();
	}

	private static String correctGuesses(String[] matrix, int guess) {
		String yes = "Impressed";
		String no = "Oops!";
		set = new HashSet<Integer>();
		rows = matrix.length;
		cols = matrix[0].length();
		int startRow = -1;
		int startCol = -1;
		char[][] charArray = new char[matrix.length][matrix[0].length()];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length(); j++) {
				charArray[i][j] = matrix[i].charAt(j);
				if (matrix[i].charAt(j) == 'M') {
					startRow = i;
					startCol = j;
				}
			}
		}
		if (countIntersection(charArray, startRow, startCol) == guess) {
			return yes;
		}
		return no;
	}

	private static int countIntersection(char[][] matrix, int row, int col) {
		set.add(getIndex(row, col));
		if (matrix[row][col] == '*') {
			return 0;
		}

		Set<int[]> neighbor = new HashSet<int[]>();
		if (row - 1 >= 0) {
			neighbor.add(new int[] { row - 1, col });
		}
		if (row + 1 < rows) {
			neighbor.add(new int[] { row + 1, col });
		}
		if (col - 1 >= 0) {
			neighbor.add(new int[] { row, col - 1 });
		}
		if (col + 1 < cols) {
			neighbor.add(new int[] { row, col + 1 });
		}

		int count = 0;
		int hasPath = -1;
		for (int[] cell : neighbor) {
			if ((matrix[cell[0]][cell[1]] == '.' || matrix[cell[0]][cell[1]] == '*')
					&& !set.contains(getIndex(cell[0], cell[1]))) {
				count++;
				int curPath = countIntersection(matrix, cell[0], cell[1]);
				if (curPath > -1) {
					hasPath = curPath;
				}
			}
		}

		if (hasPath > -1 && count > 1) {
			hasPath++;
		}
		return hasPath;
	}

	private static int getIndex(int i, int j) {
		return i * cols + j;
	}

}
