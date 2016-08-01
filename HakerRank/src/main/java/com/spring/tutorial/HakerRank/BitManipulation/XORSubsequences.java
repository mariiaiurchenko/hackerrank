package com.spring.tutorial.HakerRank.BitManipulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Hakerrank: XOR Subsequences
 * https://www.hackerrank.com/challenges/xor-subsequence
 * 
 * This solution is to slow O(n^2)
 * should be redone according to 
 * http://cs.stackexchange.com/questions/30595/computing-the-mode-of-xor-subsequences
 * look here http://www.jjj.de/fxt/fxtpage.html may be helpful
 */
public class XORSubsequences {

	private static int TOW_POW_16 = 65536;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int arr[] = new int[T];
		int xor = 0;
		for (int i = 0; i < T; i++) {
			xor = xor ^ in.nextInt();
			arr[i] = xor;
		}

		System.out.println(checkSubsequence(arr));
		in.close();
	}

	private static int[] createXVector(int[] arrXor) {
		int[] xVector = new int[TOW_POW_16];
		for (int i = 0; i < arrXor.length; i++) {
			xVector[arrXor[i]]++;
		}
		return xVector;
	}

	private static String checkSubsequence(int[] arrXor) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arrXor.length; i++) {
			addToMap(map, arrXor[i]);
			for (int j = i + 1; j < arrXor.length; j++) {
				addToMap(map, arrXor[i] ^ arrXor[j]);
			}
		}
		int maxKey = Integer.MAX_VALUE;
		int maxVal = Integer.MIN_VALUE;
		for (int key : map.keySet()) {
			if (map.get(key) > maxVal) {
				maxVal = map.get(key);
				maxKey = key;
			} else if (map.get(key) == maxVal && key < maxKey) {
				maxKey = key;
			}
		}
		return maxKey + " " + maxVal;

	}

	private static void addToMap(Map<Integer, Integer> map, int el) {
		if (map.containsKey(el)) {
			map.put(el, map.get(el) + 1);
		} else {
			map.put(el, 1);
		}
	}
}
