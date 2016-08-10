package com.spring.tutorial.HakerRank.greedy;

import java.util.Scanner;

/*
 * Hackerrank: Largest Permutation
 * https://www.hackerrank.com/challenges/largest-permutation
 */
public class LargestPermutation {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[] nums = new int[n];
		for (int a0 = 0; a0 < n; a0++) {
			nums[a0] = in.nextInt();
		}
		largestPermutation(nums, k);
		in.close();
	}

	private static void largestPermutation(int[] nums, int k) {
		int size = nums.length;
		int[] positions = new int[size + 1];
		for (int i = 0; i < size; i++) {
			positions[nums[i]] = i;
		}
		for (int num = size; num > 0 && k > 0; num--) {
			if (positions[num] != size - num) {
				nums[positions[num]] = nums[size - num];
				positions[nums[size - num]] = positions[num];
				positions[num] = size - num;
				nums[size - num] = num;
				k--;
			}
		}
		StringBuilder str = new StringBuilder();
		for (int j = 0; j < size; j++) {
			str.append(nums[j] + " ");
		}
		System.out.println(str.toString());
	}
}
