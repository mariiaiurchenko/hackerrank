package com.spring.tutorial.HakerRank.Sorting;

import java.util.Scanner;

public class CountingSortTheFull {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		String[] strAr = new String[n];
		for (int i = 0; i < n; i++) {
			ar[i] = in.nextInt();
			strAr[i] = in.next();
		}
		countSort(ar, strAr);
		in.close();
	}

	private static void countSort(int[] ar, String[] strAr) {
		int size = 100;
		int[] count = new int[size];
		for (int i = 0; i < ar.length; i++) {
			count[ar[i]]++;
		}
		int sum = 0;
		int[] pos = new int[size];
		for (int i = 0; i < size; i++) {
			pos[i] = sum;
			sum += count[i];
		}
		String[] res = new String[ar.length];
		for (int i = 0; i < ar.length; i++) {
			String str = (i < ar.length / 2) ? "-" : strAr[i];
			int strPlace = pos[ar[i]];
			res[strPlace] = str;
			pos[ar[i]]++;
		}

		printArray(res);
	}

	private static void printArray(String[] ar) {
		StringBuilder res = new StringBuilder("");
		for (String str : ar) {
			res.append(str).append(" ");
		}
		System.out.println(res.toString());
	}

}
