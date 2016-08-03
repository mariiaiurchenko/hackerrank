package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: Mars Exploration
 * https://www.hackerrank.com/challenges/mars-exploration
 */
public class MarsExploration {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(countAlteredLetters(in.nextLine()));
		in.close();
	}

	private static int countAlteredLetters(String str) {
		int count = 0;
		String orig = "SOS";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != orig.charAt(i % orig.length())) {
				count++;
			}
		}
		return count;
	}
}
