package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: Palindrome Index
 * https://www.hackerrank.com/challenges/palindrome-index
 */
public class PalindromeIndex {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			String str = in.next();
			answer.append(removeIndexToPalindrome(str)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static int removeIndexToPalindrome(String str) {
		int index = -1;
		for (int i = 0; i < str.length() / 2; i++) {
			int j = str.length() - 1 - i;
			if (str.charAt(i) != str.charAt(j)) {
				if (isPalindrome(str, i + 1, j)) {
					index = i;
				} else {
					index = j;
				}
				break;
			}
		}
		return index;
	}

	private static boolean isPalindrome(String str, int i, int j) {
		for (int it = i; it < i + (j - i + 1) / 2; it++) {
			int jt = j - it + i;
			if (str.charAt(it) != str.charAt(jt)) {
				return false;
			}
		}
		return true;
	}

}
