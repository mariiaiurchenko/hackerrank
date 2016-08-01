package com.spring.tutorial.HakerRank.Sorting;

import java.util.Scanner;

public class FindTheMedian {

	private static int findMedian(int[] ar) {
		int k = ar.length / 2;
		int p = -1;
		int lo = 0;
		int hi = ar.length - 1;
		while (p != k) {
			if (p < k) {
				lo = p + 1;
			} else {
				hi = p - 1;
			}
			p = partition(ar, lo, hi);
		}
		return ar[p];
	}

	private static int partition(int[] A, int lo, int hi) {
		int pivot = A[hi];
		int i = lo;
		for (int j = lo; j <= hi - 1; j++) {
			if (A[j] <= pivot) {
				swap(A, i, j);
				i = i + 1;
			}
		}
		swap(A, i, hi);
		return i;
	}

	private static void swap(int[] ar, int i, int j) {
		int tmp = ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		System.out.println(findMedian(ar));
		in.close();
	}
}
