package com.spring.tutorial.HakerRank.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Hakerrank: Making Anagrams
 * https://www.hackerrank.com/challenges/making-anagrams
 */
public class MakingAnagrams {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s1 = in.next();
		String s2 = in.next();
		System.out.println(removeToAnagrams(s1, s2));
		in.close();
	}

	private static int removeToAnagrams(String s1, String s2) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s1.length(); i++) {
			char ch = s1.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			}else {
				map.put(ch, 1);
			}
		}
		int remove = 0;
		for (int i = 0; i < s2.length(); i++) {
			char ch = s2.charAt(i);
			if (map.containsKey(ch) && map.get(ch) > 0) {
				map.put(ch, map.get(ch) - 1);
			} else {
				remove++;
			}
		}
		
		for (char ch : map.keySet()) {
			remove += map.get(ch);
		}
		return remove;
	}
}
