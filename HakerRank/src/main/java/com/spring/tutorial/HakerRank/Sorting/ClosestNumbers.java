package com.spring.tutorial.HakerRank.Sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ClosestNumbers {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		closestNumbers(ar);
		in.close();
	}

	private static void closestNumbers(int[] ar) {
		Arrays.sort(ar);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < ar.length - 1; i++) {
			if (ar[i + 1] - ar[i] < min) {
				min = ar[i + 1] - ar[i];
			}
		}
		List<Integer> nums = new LinkedList<Integer>();
		for (int i = 0; i < ar.length - 1; i++) {
			if (ar[i + 1] - ar[i] == min) {
				nums.add(ar[i + 1]);
				nums.add(ar[i]);
			}
		}
		Collections.sort(nums);
		StringBuilder res = new StringBuilder("");
		for (int num: nums) {
			res.append(num).append(" ");
		}
		System.out.println(res);
	}
}
