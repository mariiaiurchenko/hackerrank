package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: Super Reduced String
 * https://www.hackerrank.com/challenges/reduced-string
 */
public class SuperReducedString {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		System.out.println(reduceString(str));
		in.close();
	}

	private static String reduceString(String str) {
		String emptyString = "Empty String";
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			int last = res.length() - 1;
			if (last < 0 || res.charAt(last) != str.charAt(i)) {
				res.append(str.charAt(i));
			} else {
				res.deleteCharAt(last);
			}
		}
		return res.length() > 0 ? res.toString() : emptyString;
	}

}
