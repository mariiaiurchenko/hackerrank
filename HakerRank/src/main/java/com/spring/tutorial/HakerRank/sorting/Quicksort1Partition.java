package com.spring.tutorial.HakerRank.sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Quicksort1Partition {

	static void partition_InPlace(int[] ar) {
		int p = ar[0];
		int smaller = 0;
		int bigger = ar.length - 1;
		int equal = 0;
		while (equal < bigger) {
			if (ar[equal] < p) {
				swap(ar, smaller, equal);
				smaller++;
			} else if (ar[equal] > p) {
				swap(ar, bigger, equal);
				bigger--;
			}
			equal++;
		}
	}

	private static void swap(int[] ar, int i, int j) {
		int tmp = ar[i];
		ar[i] = ar[j];
		ar[j] = tmp;
	}

	static void partition(int[] ar) {
		int p = ar[0];
		List<Integer> smaller = new LinkedList<Integer>();
		List<Integer> equal = new LinkedList<Integer>();
		List<Integer> bigger = new LinkedList<Integer>();
		for (int i = 0; i<ar.length;i++) {
			if (ar[i] < p) {
				smaller.add(ar[i]);
			} else if (ar[i] == p) {
				equal.add(ar[i]);
			} else {
				bigger.add(ar[i]);
			}
		}
		smaller.addAll(equal);
		smaller.addAll(bigger);
		for (int el : smaller) {
			System.out.print(el + " ");
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
		partition(ar);
		in.close();
	}
}
