package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: Game of Thrones - I
 * https://www.hackerrank.com/challenges/game-of-thrones
 */
public class GameOfThrones1 {

	public static void main(String[] args) {
		Scanner myScan = new Scanner(System.in);
		String inputString = myScan.nextLine();
		System.out.println(posiblePolindrome(inputString));
		myScan.close();
	}

	private static String posiblePolindrome(String inputString) {
		int[] count = new int[26];
		for (int i = 0; i < inputString.length(); i++) {
			count[inputString.charAt(i) - 'a'] += 1;
		}
		String answer = "YES";
		boolean hasOdd = false;
		for (int el : count) {
			if (isOdd(el) && hasOdd) {
				answer = "NO";
				break;
			}
			if (isOdd(el)) {
				hasOdd = true;
			}
		}
		return answer;
	}

	private static boolean isOdd(int n) {
		return (n & 1) == 1;
	}

}
