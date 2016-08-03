package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: Alternating Characters
 * https://www.hackerrank.com/challenges/alternating-characters
 */
public class AlternatingCharacters {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			String str = in.next();
			answer.append(countDeletedChar(str)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static int countDeletedChar(String str) {
		int count = 0;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i - 1) == str.charAt(i)) {
				count++;
			}
		}
		return count;
	}
}
