package com.spring.tutorial.HakerRank.greedy;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Hackerrank: Sherlock and The Beast
 * https://www.hackerrank.com/challenges/sherlock-and-the-beast
 */
public class SherlockAndTheBeast {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int dig = in.nextInt();
			answer.append(createNum(dig)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String createNum(int dig) {
		int first = 5;
		int second = 3;
		int countFive = dig / second;
		int countThree = dig % second;
		while (countFive >= 0 && countThree % first != 0) {
			countFive -= 1;
			countThree += second;
		}
		String res = "-1";
		if (countFive >= 0 && countThree % first == 0) {
			char[] fives = new char[countFive * 3];
			Arrays.fill(fives, '5');
			char[] threes = new char[countThree];
			Arrays.fill(threes, '3');
			res = new String(fives) + new String(threes);
		}
		return res;
	}
}
