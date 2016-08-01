package com.spring.tutorial.HakerRank.Sorting;

import java.util.Scanner;

public class InsertionSortPart2 {
	public static void insertionSortPart2(int[] ar) {
		if (ar.length < 2) {
			printArray(ar);
			return;
		}

		for (int len = 2; len <= ar.length; len++) {
			insertIntoSorted(ar, len);
			printArray(ar);
		}
	}

	public static void insertIntoSorted(int[] ar, int len) {
		int el = ar[len - 1];
		int beg = 0;
		int end = len - 2;
		int pos = -1;
		if (el < ar[0]) {
			pos = 0;
		} else if (el > ar[end]) {
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
		for (int i = len - 2; i >= pos; i--) {
			ar[i + 1] = ar[i];
		}
		ar[pos] = el;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int[] ar = new int[s];
		for (int i = 0; i < s; i++) {
			ar[i] = in.nextInt();
		}
		insertionSortPart2(ar);
		in.close();
	}

	private static void printArray(int[] ar) {
		for (int n : ar) {
			System.out.print(n + " ");
		}
		System.out.println("");
	}
}
