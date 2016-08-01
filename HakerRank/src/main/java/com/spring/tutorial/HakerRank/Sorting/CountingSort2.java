package com.spring.tutorial.HakerRank.Sorting;

import java.util.Scanner;

public class CountingSort2 {
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
		int size = 100;
		int[] count = new int[size];
		for (int i = 0; i < ar.length; i++) {
			count[ar[i]]++;
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < count[i]; j++) {
				System.out.print(i + " ");
			}
		}
		System.out.println("");
	}
}
