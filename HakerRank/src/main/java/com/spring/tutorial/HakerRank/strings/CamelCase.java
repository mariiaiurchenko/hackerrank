package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: CamelCase
 * https://www.hackerrank.com/challenges/camelcase
 */
public class CamelCase {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		System.out.println(countWords(str));
		in.close();
	}

	private static int countWords(String str) {
		int count = 1;
		for (char ch : str.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				count++;
			}
		}
		return count;
	}
}
