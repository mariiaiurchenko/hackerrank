package com.spring.tutorial.HakerRank.Sorting;

import java.util.Scanner;

public class CountingSort1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
		}
		countSort(ar);
		in.close();
	}

	private static void countSort(int[] ar) {
		int[] count = new int[100];
		for (int i = 0; i < ar.length; i++) {
			count[ar[i]]++;
		}
		printArray(count);
	}

	private static void printArray(int[] ar) {
		for (int el : ar) {
			System.out.print(el + " ");
		}
		System.out.println("");
	}
}
