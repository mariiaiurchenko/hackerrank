package com.spring.tutorial.HakerRank.Search;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Hakerrank: "Ice Cream Parlor"
 * https://www.hackerrank.com/challenges/icecream-parlor
 */
public class IceCreamParlor {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int m = in.nextInt();
			int n = in.nextInt();
			int arr[] = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}
			answer.append(findIndexSum(arr, m)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String findIndexSum(int[] ar, int sum) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String res = "";
		for (int i = 0; i < ar.length; i++) {
			if (map.containsKey(ar[i])) {
				res = map.get(ar[i]) + " " + (i + 1);
				break;
			}
			map.put(sum - ar[i], i + 1);
		}
		return res;
	}
}
