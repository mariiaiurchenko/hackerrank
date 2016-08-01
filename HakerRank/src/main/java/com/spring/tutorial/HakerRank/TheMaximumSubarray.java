package com.spring.tutorial.HakerRank;

import java.util.Scanner;

public class TheMaximumSubarray {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int n = in.nextInt();
			int arr[] = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}
			answer.append(maxSubArrays(arr)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String maxSubArrays(int[] arr) {
		long contMaxArr = 0;
		long maxArr = 0;
		long tmpContMaxArr = 0;
		int min = Integer.MIN_VALUE;
		for (int el : arr) {
			if (el > min) {
				min = el;
			}
			if (el > 0) {
				maxArr += el;
			}
			if (tmpContMaxArr + el > 0) {
				tmpContMaxArr += el;
			} else {
				tmpContMaxArr = 0;
			}
			if (tmpContMaxArr > contMaxArr) {
				contMaxArr = tmpContMaxArr;
			}
		}
		if (min < 0) {
			contMaxArr = min;
			maxArr = min;
		}
		return String.valueOf(contMaxArr) + " " + String.valueOf(maxArr);
	}
}
