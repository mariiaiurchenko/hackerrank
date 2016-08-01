package com.spring.tutorial.HakerRank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class stares {

	/*
	 * Complete the function below.
	 */

	static void StairCase(int n) {
		char[] array = new char[n];
		Arrays.fill(array, ' ');
		for (int i = n - 1; i >= 0; i--) {
			array[i] = '#';
			System.out.println(new String(array));
		}

	}

	/*
	 * Complete the function below.
	 */

	static int sum(int[] numbers) {
		int sum = 0;
		for (int el : numbers) {
			sum += el;
		}
		return sum;
	}

	static int countPairs(int[] numbers, int k) {
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int count = 0;
		for (int el : numbers) {
			if (map.containsKey((long)el)) {
				count += map.get((long)el);
			}
			long el1 = el + k;
			long el2 = el - k;

			if (map.containsKey(el1)) {
				map.put(el1, map.get(el1) + 1);
			} else {
				map.put(el1, 1);
			}

			if (map.containsKey(el2)) {
				map.put(el2, map.get(el2) + 1);
			} else {
				map.put(el2, 1);
			}

		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(countPairs(new int[] { 1, 5, 3, 4, 2 }, 2));
	}

}
