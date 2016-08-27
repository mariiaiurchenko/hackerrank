package com.spring.tutorial.HakerRank.strings;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Hakerrank: Yet Another KMP Problem
 * https://www.hackerrank.com/challenges/kmp-problem
 */
public class YetAnotherKMPProblem {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] chars = new int[26];
		for (int i = 0; i < 26; i++) {
			chars[i] = in.nextInt();
		}
		System.out.println(calcString(chars));
		in.close();
	}

	private static String calcString(int[] chars) {
		int size = 26;
		int first = -1;
		int min_index = -1;
		for (int i = 0; i < size; i++) {
			if (chars[i] > 0) {
				if (min_index < 0 || chars[i] < chars[min_index]) {
					min_index = i;
				}
				if (first == -1) {
					first = i;
				}
			}
		}
		StringBuilder str = new StringBuilder();
		if (min_index != first) {
			appendLetter(str, min_index);
			for (int i = 0; i < size; i++) {
				if (i != min_index) {
					str.append(buildStringSameChar(chars[i], i));
				} else {
					str.append(buildStringSameChar(chars[i] - 1, i));
				}
			}

		} else {
			str = minFirst(chars, min_index);
		}
		return str.toString();
	}

	private static StringBuilder minFirst(int[] chars, int min) {
		StringBuilder str = new StringBuilder();
		int len = 0;
		for (int el : chars) {
			len += el;
		}
		if (chars[min] == 1) {
			appendLetter(str, min);
			chars[min]--;
		} else if (chars[min] > 1) {
			int copyBeg = 2;
			if (len - chars[min] < chars[min] - 2) {
				copyBeg = 2 * chars[min] - len;
			}
			str.append(buildStringSameChar(copyBeg, min));
			chars[min] -= copyBeg;
		}
		for (int i = min + 1; i < 26; i++) {
			if (chars[min] > 0) {
				if (chars[i] <= chars[min]) {
					str.append(buildStringPairsOfChars(chars[i], i, min));
					chars[min] -= chars[i];
				} else {
					str.append(buildStringPairsOfChars(chars[min], i, min));
					chars[i] -= chars[min];
					chars[min] = 0;
					str.append(buildStringSameChar(chars[i], i));
				}
			} else {
				str.append(buildStringSameChar(chars[i], i));
			}
		}
		return str;
	}

	private static String buildStringSameChar(int len, int index) {
		char[] chars = new char[len];
		Arrays.fill(chars, (char) ('a' + index));
		return new String(chars);
	}

	private static String buildStringPairsOfChars(int count, int fi, int si) {
		StringBuilder str = new StringBuilder();
		StringBuilder pair = new StringBuilder();
		appendLetter(pair, fi);
		appendLetter(pair, si);
		for (int i = 0; i < count; i++) {
			str.append(pair);
		}
		return str.toString();
	}

	private static void appendLetter(StringBuilder str, int index) {
		str.append((char) ('a' + index));
	}
}
