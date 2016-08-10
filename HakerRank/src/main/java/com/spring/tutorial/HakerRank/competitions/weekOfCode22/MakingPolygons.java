package com.spring.tutorial.HakerRank.competitions.weekOfCode22;

import java.util.Scanner;

public class MakingPolygons {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		System.out.println(countCuts(a));
		in.close();
	}

	private static int countCuts(int[] a) {
		int count = 0;
		float sum = 0;
		float max = 0;
		for (int el : a) {
			sum += el;
			max = Math.max(max, el);
		}
		sum -= max;
		while (max >= sum) {
			count++;
			max = max / 2;
			sum = sum + max;
		}
		return count;
	}
}
