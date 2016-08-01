package com.spring.tutorial.HakerRank.search;

import java.util.Scanner;
import java.util.TreeSet;

/*
 * Hakerrank: Maximum Subarray Sum
 * https://www.hackerrank.com/challenges/maximum-subarray-sum
 */
public class MaximumSubarraySum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int n = in.nextInt();
			long modulo = in.nextLong();
			long arr[] = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}
			answer.append(maxSubArraySum(arr, modulo)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static long maxSubArraySum(long[] ar, long modulo) {
		TreeSet<Long> tree = new TreeSet<Long>();
		long currSum = ar[0] % modulo;
		tree.add(currSum);
		long result = currSum;
		for (int i = 1; i < ar.length; i++) {
			currSum = currSum + (ar[i] % modulo);
			currSum %= modulo;
			Long minLargerValue = tree.higher(currSum);
			long maxSubArrToIndexI = minLargerValue != null ? (currSum
					- minLargerValue + modulo)
					% modulo : currSum;
			result = Math.max(maxSubArrToIndexI, result);
			tree.add(currSum);
		}
		return result;
	}
}
