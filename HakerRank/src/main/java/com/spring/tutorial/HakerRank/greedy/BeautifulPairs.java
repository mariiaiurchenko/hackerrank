package com.spring.tutorial.HakerRank.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Hackerrank: Luck Balance
 * https://www.hackerrank.com/challenges/luck-balance
 */
public class BeautifulPairs {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		int b[] = new int[n];
		for (int b_i = 0; b_i < n; b_i++) {
			b[b_i] = in.nextInt();
		}
		System.out.println(numberOfPairwise(a, b));
		in.close();
	}

	private static int numberOfPairwise(int[] a, int[] b) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int el : a) {
			if (!map.containsKey(el)) {
				map.put(el, 0);
			}
			map.put(el, map.get(el) + 1);
		}
		for (int key : b) {
			if (map.containsKey(key)) {
				count++;
				if (map.get(key) == 1) {
					map.remove(key);
				} else {
					map.put(key, map.get(key) - 1);
				}
			}
		}
		if (map.size() > 0) {
			count++;
		} else {
			count--;
		}
		return count;
	}
}
