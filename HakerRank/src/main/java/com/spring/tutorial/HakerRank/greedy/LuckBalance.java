package com.spring.tutorial.HakerRank.greedy;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * Hackerrank: Luck Balance
 * https://www.hackerrank.com/challenges/luck-balance
 */
public class LuckBalance {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] ar = new int[n][2];
		for (int i = 0; i < n; i++) {
			ar[i] = new int[2];
			ar[i][0] = in.nextInt();
			ar[i][1] = in.nextInt();
		}
		System.out.println(countLuck(ar, k));
		in.close();
	}

	private static int countLuck(int[][] ar, int k) {
		int count = 0;
		int heapSize = ar.length - k;
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Math.min(1,
				heapSize), Collections.reverseOrder());
		for (int i = 0; i < ar.length; i++) {
			if (ar[i][1] == 0) {
				heapSize--;
				count += ar[i][0];
				if (heapSize >= 0 && heap.size() > heapSize) {
					count += 2 * heap.poll();
				}
			} else if (heapSize > 0
					&& (heap.size() < heapSize || ar[i][0] < heap.peek())) {
				if (heap.size() == heapSize) {
					count += 2 * heap.poll();
				}
				heap.add(ar[i][0]);
				count -= ar[i][0];
			} else {
				count += ar[i][0];
			}
		}
		return count;
	}
}
