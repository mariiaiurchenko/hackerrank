package com.spring.tutorial.HakerRank.sorting;

import java.util.Scanner;

public class RunningTimeOfQuicksort {

	private static int countSwap;

	private static void quickSort(int[] A) {
		countSwap = 0;
		quickSort(A, 0, A.length - 1);
	}

	private static void quickSort(int[] A, int lo, int hi) {
		if (lo < hi) {
			int p = partition(A, lo, hi);
			quickSort(A, lo, p - 1);
			quickSort(A, p + 1, hi);
		}
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
		countSwap++;
		int tmp = ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}

	public static int countShiftsInsertSort(int[] ar) {
		if (ar.length < 2) {
			return 0;
		}
		int count = 0;
		for (int len = 2; len <= ar.length; len++) {
			count += insertIntoSorted(ar, len);
		}
		return count;
	}

	public static int insertIntoSorted(int[] ar, int len) {
		int el = ar[len - 1];
		int beg = 0;
		int end = len - 2;
		int pos = -1;
		if (el < ar[0]) {
			pos = 0;
		} else if (el >= ar[end]) {
			pos = len - 1;
		}
		while (pos == -1) {
			int mid = (end - beg) / 2 + beg;
			if (ar[mid] < el) {
				beg = mid;
			} else {
				end = mid;
			}
			if (end - beg == 1) {
				pos = end;
			}
		}
		while (pos < len - 1 && ar[pos] == el) {
			pos++;
		}
		for (int i = len - 2; i >= pos; i--) {
			ar[i + 1] = ar[i];
		}
		ar[pos] = el;
		int count = len - 1 - pos;
		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar1 = new int[n];
		int[] ar2 = new int[n];
		for (int i = 0; i < n; i++) {
			ar1[i] = in.nextInt();
			ar2[i] = ar1[i];
		}
		quickSort(ar1);
		int shift = countShiftsInsertSort(ar2);
		System.out.println(shift - countSwap);
		in.close();
	}

}
