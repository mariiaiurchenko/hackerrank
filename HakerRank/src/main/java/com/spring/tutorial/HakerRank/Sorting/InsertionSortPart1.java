package com.spring.tutorial.HakerRank.Sorting;

public class InsertionSortPart1 {

	public static void main(String[] args) {
		int[] ar = { 1, 3, 5, 9, 13, 22, 27, 35, 46, 51, 55, 83, 87, 23 };
		// { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };// { 2, 3, 4,
		// 5, 6 };// {
		// 2, 3, 4, 5, 1 };// {
		// 2, 4, 6, 8, 3 };
		insertIntoSorted(ar);
	}

	public static void insertIntoSorted(int[] ar) {
		int el = ar[ar.length - 1];
		int beg = 0;
		int end = ar.length - 2;
		int pos = -1;
		if (ar.length < 2) {
			printArray(ar);
			return;
		}
		if (el < ar[0]) {
			pos = 0;
		} else if (el > ar[end]) {
			pos = ar.length - 1;
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
		for (int i = ar.length - 2; i >= pos; i--) {
			ar[i + 1] = ar[i];
			printArray(ar);
		}
		ar[pos] = el;
		printArray(ar);
	}

	private static void printArray(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i] + " ");
		}
		System.out.println("");
	}
}
