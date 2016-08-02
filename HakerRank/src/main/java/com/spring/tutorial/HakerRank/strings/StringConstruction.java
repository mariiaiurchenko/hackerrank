package com.spring.tutorial.HakerRank.strings;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Hakerrank: String Construction
 * https://www.hackerrank.com/challenges/string-construction
 */
public class StringConstruction {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < n; a0++) {
			String s = in.next();
			answer.append(countLetters(s)).append(
					System.getProperty("line.separator"));

		}
		System.out.println(answer);
		in.close();
	}

	private static int countLetters(String str) {
		Set<Character> set = new HashSet<Character>();
		for (Character ch : str.toCharArray()) {
			set.add(ch);
		}
		return set.size();
	}
}
