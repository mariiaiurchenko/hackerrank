package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: The Love-Letter Mystery
 * https://www.hackerrank.com/challenges/the-love-letter-mystery
 */
public class TheLoveLetterMystery {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			String str = in.next();
			answer.append(countReduce(str)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static int countReduce(String str) {
		int count = 0;
		for (int i = 0; i < str.length() / 2; i++) {
			count += Math.abs(str.charAt(i) - str.charAt(str.length() - 1 - i));
		}
		return count;
	}
}
