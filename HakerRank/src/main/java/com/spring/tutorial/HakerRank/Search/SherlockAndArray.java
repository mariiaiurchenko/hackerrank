package com.spring.tutorial.HakerRank.Search;

import java.util.Scanner;

public class SherlockAndArray {

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
			answer.append(hasMidEl(arr)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String hasMidEl(int[] arr) {
		String yes = "YES";
		String no = "NO";
		int rightSum = 0;
		for (int el : arr) {
			rightSum += el;
		}

		int leftSum = 0;
		for (int el : arr) {
			rightSum -= el;
			if (leftSum == rightSum) {
				return yes;
			}
			leftSum += el;
		}
		return no;
	}
}
