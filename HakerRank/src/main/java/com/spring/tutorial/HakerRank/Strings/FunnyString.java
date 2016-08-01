package com.spring.tutorial.HakerRank.Strings;

import java.util.Scanner;

public class FunnyString {

	private static final String FUNNY = "Funny";
	private static final String NOT_FUNNY = "Not Funny";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			String str = in.next();
			answer.append(isFunny(str)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String isFunny(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (Math.abs(str.charAt(i) - str.charAt(i - 1)) != Math.abs(str
					.charAt(str.length() - 1 - i)
					- str.charAt(str.length() - i))) {
				return NOT_FUNNY;
			}
		}
		return FUNNY;
	}
}
