package com.spring.tutorial.HakerRank.greedy;

import java.util.Scanner;
import java.util.TreeSet;

/*
 * Hackerrank: Priyanka and Toys
 * https://www.hackerrank.com/challenges/priyanka-and-toys
 */
public class PriyankaAndToys {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Integer[] w = new Integer[n];
		for (int a0 = 0; a0 < n; a0++) {
			w[a0] = in.nextInt();
		}
		System.out.println(countUnits(w));
		in.close();
	}

	private static int countUnits(Integer[] weight) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (Integer w : weight) {
			set.add(w);
		}
		int count = 1;
		int start = set.first();
		for (Integer w : set) {
			if (w - start > 4) {
				count++;
				start = w;
			}
		}

		return count;
	}
}
