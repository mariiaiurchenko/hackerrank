package com.spring.tutorial.HakerRank.Sorting;

import java.util.Scanner;

public class RunningTimeOfAlgorithms {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int[] ar = new int[s];
		for (int i = 0; i < s; i++) {
			ar[i] = in.nextInt();
		}
		System.out.println(countShifts(ar));
		in.close();
	}

	public static int countShifts(int[] ar) {
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

}
