package com.spring.tutorial.HakerRank.sorting;

import java.util.Scanner;

public class QuicksortInPlace {

	private static void quickSort_LomutoPartitioning(int[] A) {
		quickSort_LomutoPartitioning(A, 0, A.length - 1);
	}

	private static void quickSort_LomutoPartitioning(int[] A, int lo, int hi) {
		if (lo < hi) {
			int p = partition_LomutoPartitioning(A, lo, hi);
			printArray(A);
			quickSort_LomutoPartitioning(A, lo, p - 1);
			quickSort_LomutoPartitioning(A, p + 1, hi);
		}
	}

	private static int partition_LomutoPartitioning(int[] A, int lo, int hi) {
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

	private static void printArray(int[] ar) {
		for (int el : ar) {
			System.out.print(el + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		quickSort_LomutoPartitioning(ar);
		in.close();
	}

	private static void quickSort(int[] ar) {
		if (ar.length == 1) {
			System.out.println(ar[0]);
		} else {
			partition(ar, 0, ar.length);
		}
	}

	static void partition(int[] ar, int beg, int end) {
		int p = ar[end - 1];
		int smaller = beg;
		int bigger = end - 2;
		while (smaller < bigger) {
			if (ar[smaller] < p) {
				smaller++;
			} else if (ar[bigger] > p) {
				bigger--;
			}
			if (ar[smaller] > p && ar[bigger] < p) {
				swap(ar, bigger, smaller);
			}
		}
		int sortSmal = 1;
		if (ar[bigger] > p) {
			swap(ar, bigger, end - 1);
			sortSmal = 0;
		}
		for (int el : ar) {
			System.out.print(el + " ");
		}
		System.out.println("");
		if (smaller - beg + sortSmal > 1) {
			partition(ar, beg, smaller + sortSmal);
		}
		if (end - bigger > 2) {
			partition(ar, bigger + 1, end);
		}
	}

}
