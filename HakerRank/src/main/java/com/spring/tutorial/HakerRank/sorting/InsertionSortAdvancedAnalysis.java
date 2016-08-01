package com.spring.tutorial.HakerRank.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class InsertionSortAdvancedAnalysis {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		StringBuilder res = new StringBuilder("");
		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			int[] ar = new int[n];
			for (int j = 0; j < n; j++) {
				ar[j] = in.nextInt();
			}
			res.append(invCount(ar)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(res);
		in.close();
	}

	private static long merge(int[] arr, int[] left, int[] right) {
		int i = 0;
		int j = 0;
		long count = 0;
		while (i < left.length || j < right.length) {
			if (i == left.length) {
				arr[i + j] = right[j];
				j++;
			} else if (j == right.length) {
				arr[i + j] = left[i];
				i++;
			} else if (left[i] <= right[j]) {
				arr[i + j] = left[i];
				i++;
			} else {
				arr[i + j] = right[j];
				count += left.length - i;
				j++;
			}
		}
		return count;
	}

	private static long invCount(int[] arr) {
		if (arr.length < 2) {
			return 0;
		}
		int m = (arr.length + 1) / 2;
		int left[] = Arrays.copyOfRange(arr, 0, m);
		int right[] = Arrays.copyOfRange(arr, m, arr.length);

		return invCount(left) + invCount(right) + merge(arr, left, right);
	}

}
