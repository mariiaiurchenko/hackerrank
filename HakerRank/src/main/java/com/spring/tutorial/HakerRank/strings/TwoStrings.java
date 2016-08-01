package com.spring.tutorial.HakerRank.strings;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Hakerrank: Two Strings
 * https://www.hackerrank.com/challenges/two-strings
 */
public class TwoStrings {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			String s1 = in.next();
			String s2 = in.next();
			answer.append(hasSubString(s1, s2)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	public static String hasSubString(String s1, String s2) {
		Set<Character> set1 = stringToSet(s1);
		Set<Character> set2 = stringToSet(s2);
		set1.retainAll(set2);
		return set1.size() > 0 ? "YES" : "NO";
	}

	private static Set<Character> stringToSet(String s) {
		Set<Character> set = new HashSet<Character>();
		for (int i = 0; i < s.length(); i++) {
			set.add(s.charAt(i));
		}
		return set;
	}
}
