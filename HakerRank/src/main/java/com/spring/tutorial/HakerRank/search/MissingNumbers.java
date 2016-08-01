package com.spring.tutorial.HakerRank.search;

import java.util.Scanner;

/*
 * Hakerrank: Missing Numbers
 * https://www.hackerrank.com/challenges/missing-numbers
 */
public class MissingNumbers {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int A[] = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}
		int m = in.nextInt();
		int B[] = new int[m];
		for (int i = 0; i < m; i++) {
			B[i] = in.nextInt();
		}
		System.out.println(findMissingNumbers(A, B));
		in.close();
	}

	private static String findMissingNumbers(int[] a, int[] b) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < b.length; i++) {
			min = b[i] < min ? b[i] : min;
			max = b[i] > max ? b[i] : max;
		}
		for (int i = 0; i < b.length; i++) {
			b[i] = b[i] - min;
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = a[i] - min;
		}
		int[] nums = new int[max - min + 1];
		for (int i = 0; i < b.length; i++) {
			nums[b[i]]++;
		}
		for (int i = 0; i < a.length; i++) {
			nums[a[i]]--;
		}
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				answer.append(i + min).append(" ");
			}
		}
		return answer.toString();
	}
}
