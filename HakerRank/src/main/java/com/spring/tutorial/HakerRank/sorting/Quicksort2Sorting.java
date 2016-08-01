package com.spring.tutorial.HakerRank.sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Quicksort2Sorting {

	static void quickSort(int[] ar) {
		partition(ar, 0, ar.length);
	}

	static void partition(int[] ar, int beg, int end) {
		if (end - beg <= 1) {
			return;
		}
		int p = ar[beg];
		List<Integer> smaller = new LinkedList<Integer>();
		List<Integer> equal = new LinkedList<Integer>();
		List<Integer> bigger = new LinkedList<Integer>();
		for (int i = beg; i < end; i++) {
			if (ar[i] < p) {
				smaller.add(ar[i]);
			} else if (ar[i] == p) {
				equal.add(ar[i]);
			} else {
				bigger.add(ar[i]);
			}
		}
		for (int i = beg; i < end; i++) {
			if (i < (beg + smaller.size())) {
				ar[i] = smaller.get(i - beg);
			} else if (i >= (beg + smaller.size())
					&& i < (beg + smaller.size() + equal.size())) {
				ar[i] = equal.get(i - beg - smaller.size());
			} else {
				ar[i] = bigger.get(i - beg - smaller.size() - equal.size());
			}
		}
		if (smaller.size() > 1) {
			partition(ar, beg, beg + smaller.size());
		}
		if (bigger.size() > 1) {
			partition(ar, beg + smaller.size() + equal.size(), end);
		}
		smaller.addAll(equal);
		smaller.addAll(bigger);
		for (int i = beg; i < end; i++) {
			System.out.print(ar[i] + " ");
		}
		System.out.println("");
	}

	static void printArray(int[] ar) {
		for (int n : ar) {
			System.out.print(n + " ");
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
		quickSort(ar);
		in.close();
	}
}
