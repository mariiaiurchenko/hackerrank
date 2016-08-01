package com.spring.tutorial.HakerRank.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Hakerrank: Sherlock and Anagrams
 * https://www.hackerrank.com/challenges/sherlock-and-anagrams
 */
public class SherlockAndAnagrams {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			String str = in.next();
			answer.append(countAnagrams(str)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static int countAnagrams(String str) {
		int count = 0;
		for (int i = 1; i < str.length(); i++) {
			count += countAnagramsSubStr(str, i);
		}
		return count;
	}

	private static int countAnagramsSubStr(String str, int size) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int count = 0;
		for (int i = 0; i <= str.length() - size; i++) {
			char[] chars = str.substring(i, i + size).toCharArray();
			Arrays.sort(chars);
			String sub = new String(chars);
			if (map.containsKey(sub)) {
				count += map.get(sub);
				map.put(sub, map.get(sub) + 1);
			} else {
				map.put(sub, 1);
			}
		}
		return count;
	}
}
