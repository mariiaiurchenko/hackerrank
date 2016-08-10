package com.spring.tutorial.HakerRank.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
 * Hackerrank: Two arrays
 * https://www.hackerrank.com/challenges/two-arrays
 */
public class TwoArrays {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int n = in.nextInt();
			int k = in.nextInt();
			Integer[] a = new Integer[n];
			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
			Integer[] b = new Integer[n];
			for (int i = 0; i < n; i++) {
				b[i] = in.nextInt();
			}
			answer.append(isPosible(a, b, k)).append(
					System.getProperty("line.separator"));

		}
		System.out.println(answer);
		in.close();
	}

	private static String isPosible(Integer[] a, Integer[] b, int k) {
		String yes = "YES";
		String no = "NO";
		Arrays.sort(a);
		Arrays.sort(b, Collections.reverseOrder());
		for (int i = 0; i < a.length; i++) {
			if (a[i] + b[i] < k) {
				return no;
			}
		}
		return yes;
	}
}
