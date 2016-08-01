package com.spring.tutorial.HakerRank.sorting;

import java.util.Scanner;

public class CountingSort3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
			in.next();
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
		int sum = 0;
		for (int i = 0; i < size; i++) {
			sum += count[i];
			System.out.print(sum + " ");
		}
		System.out.println("");
	}

}
