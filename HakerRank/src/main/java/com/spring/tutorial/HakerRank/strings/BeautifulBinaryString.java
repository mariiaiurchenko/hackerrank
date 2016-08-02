package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: Beautiful Binary String
 * https://www.hackerrank.com/challenges/beautiful-binary-string
 */
public class BeautifulBinaryString {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String str = in.next();
		System.out.println(countChange(str));
		in.close();
	}

	private static int countChange(String str) {
		int count = 0;
		for (int i = 0; i < str.length() - 2; i++) {
			if (str.substring(i, i + 3).equals("010")) {
				count++;
				i += 2;
			}
		}
		return count;
	}

}
