package com.spring.tutorial.HakerRank.strings;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Hakerrank: Gemstones
 * https://www.hackerrank.com/challenges/gem-stones
 */
public class Gemstones {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		String[] strs = new String[T];
		for (int a0 = 0; a0 < T; a0++) {
			strs[a0] = in.next();
		}
		System.out.println(countGemElements(strs));
		in.close();
	}

	private static int countGemElements(String[] strs) {
		Set<Character> intersect = stringToCharacterSet(strs[0]);
		for (String str : strs) {
			intersect.retainAll(stringToCharacterSet(str));
		}
		return intersect.size();
	}

	private static Set<Character> stringToCharacterSet(String str) {
		Set<Character> chars = new HashSet<Character>();
		for (char ch : str.toCharArray()) {
			chars.add(ch);
		}
		return chars;
	}
}
