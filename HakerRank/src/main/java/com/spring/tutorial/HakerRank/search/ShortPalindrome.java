package com.spring.tutorial.HakerRank.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * Hackerrank: Short Palindrome
 * https://www.hackerrank.com/challenges/short-palindrome/forum
 * 
 * not finished
 */
public class ShortPalindrome {

	private static long mod = 1000000007;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		System.out.println(countTuples(str));
		in.close();
	}

	private static long countTuples(String str) {
		Map<Character, TreeSet<Integer>> map = initMap(str);
		long count = 0;
		for (char ch = 'a'; ch <= 'z'; ch++) {
			if (map.get(ch).size() > 0) {
				int indexFirst = map.get(ch).first();
				int indexLast = map.get(ch).last();
				map.get(ch).remove(indexFirst);
				while (indexFirst != indexLast) {
					map.get(ch).remove(indexLast);
					count += numOfCouple(indexFirst, indexLast, ch, map);
					count %= mod;
					map.get(ch).add(indexLast);
					indexFirst = map.get(ch).first();
					map.get(ch).remove(indexFirst);
				}
			}
		}

		return count;
	}

	private static long numOfCouple(int from, int to, char fromChar,
			Map<Character, TreeSet<Integer>> map) {
		long count = 0;
		for (char ch = fromChar; ch <= 'z'; ch++) {
			int sizeOfTree = map.get(ch).subSet(from + 1, to).size();
			if (sizeOfTree >= 2) {
				count += binomialCoeff(sizeOfTree, 2);
				count %= mod;
			}
		}
		return count;
	}

	private static long binomialCoeff(int n, int k) {
		long res = 1;
		// Since C(n, k) = C(n, n-k)
		if (k > n - k)
			k = n - k;
		// Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
		for (int i = 0; i < k; ++i) {
			res *= (n - i);
			res /= (i + 1);
		}

		return res;
	}

	private static Map<Character, TreeSet<Integer>> initMap(String str) {
		Map<Character, TreeSet<Integer>> map = new HashMap<Character, TreeSet<Integer>>();
		for (char ch = 'a'; ch <= 'z'; ch++) {
			map.put(ch, new TreeSet<Integer>());
		}
		for (int i = 0; i < str.length(); i++) {
			map.get(str.charAt(i)).add(i);
		}
		return map;
	}
}
