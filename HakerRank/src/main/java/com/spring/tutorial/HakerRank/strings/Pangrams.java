package com.spring.tutorial.HakerRank.strings;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Hakerrank: Pangrams
 * https://www.hackerrank.com/challenges/pangrams
 */
public class Pangrams {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(isPangrams(in.nextLine()));
		in.close();
	}

	private static String isPangrams(String str) {
		String yes = "pangram";
		String no = "not pangram";
		Set<Character> set = new HashSet<Character>();
		for (Character ch : str.toCharArray()) {
			if (Character.isLetter(ch)) {
				set.add(Character.toLowerCase(ch));
			}
		}
		return set.size() < 26 ? no : yes;
	}
}
