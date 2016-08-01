package com.spring.tutorial.HakerRank.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Hakerrank: Bear and Steady Gene
 * https://www.hackerrank.com/challenges/bear-and-steady-gene
 */
public class BearAndSteadyGene {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		String str = in.next();
		System.out.println(minReplace(str));
		in.close();
	}

	private static int minReplace(String str) {
		int maxCharCount = str.length() / 4;
		Map<Character, Integer> charCount = new HashMap<Character, Integer>();
		charCount.put('A', 0);
		charCount.put('C', 0);
		charCount.put('T', 0);
		charCount.put('G', 0);
		charCount.put('M', maxCharCount);
		int maxSubStr = 0;
		int i = 0;
		for (i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (charCount.get(ch) == maxCharCount) {
				break;
			} else {
				charCount.put(ch, charCount.get(ch) + 1);
			}
		}
		if (i == str.length()) {
			return 0;
		}
		maxSubStr = i;
		i--;
		int j = str.length() - 1;
		for (j = str.length() - 1; j >= 0; j--) {
			char ch = str.charAt(j);
			if (charCount.get(ch) == maxCharCount) {
				int currlength = str.length() - j  + i;
				if (currlength > maxSubStr) {
					maxSubStr = currlength;
				}
				char begCh = ' ';
				for (; i >= 0 && begCh != ch; i--) {
					begCh = str.charAt(i);
					charCount.put(begCh, charCount.get(begCh) - 1);
				}
			}
			if (charCount.get(ch) < maxCharCount) {
				charCount.put(ch, charCount.get(ch) + 1);
			} else {
				break;
			}
		}
		return str.length() - maxSubStr;
	}

}
