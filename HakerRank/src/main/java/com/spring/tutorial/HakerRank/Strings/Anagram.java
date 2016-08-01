package com.spring.tutorial.HakerRank.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagram {

		public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int T = in.nextInt();
			StringBuilder answer = new StringBuilder();
			for (int a0 = 0; a0 < T; a0++) {
				String str = in.next();
				answer.append(changeToAnagram(str)).append(
						System.getProperty("line.separator"));
			}
			System.out.println(answer);
			in.close();
		}
	
		private static int changeToAnagram(String str) {
			if (isOdd(str.length())) {
				return -1;
			}
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			for (int i = 0; i < str.length() / 2; i++) {
				if (map.containsKey(str.charAt(i))) {
					map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
				} else {
					map.put(str.charAt(i), 1);
				}
				int secondIndex = str.length() - 1 - i;
				if (map.containsKey(str.charAt(secondIndex))) {
					map.put(str.charAt(secondIndex),
							map.get(str.charAt(secondIndex)) - 1);
				} else {
					map.put(str.charAt(secondIndex), -1);
				}
			}
			int count = 0;
			for (int val : map.values()) {
				if (val > 0) {
					count += val;
				}
			}
			return count;
		}
	
		private static boolean isOdd(int n) {
			return (n & 1) == 1;
		}
}
