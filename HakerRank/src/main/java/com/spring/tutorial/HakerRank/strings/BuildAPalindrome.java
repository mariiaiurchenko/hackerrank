package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hackerrank: Build a Palindrome
 * https://www.hackerrank.com/challenges/challenging-palindromes
 * 
 * should be revisited

 input:
 10
 ottloictodtdtloloollllyocidyiodttoacoctcdcidcdttyoiilocltacdlydaailaiylcttilld
 jevgfsuujwrunvgvgwpfbknkruvwzgxxgksmexqvxbghfffseuugxkwexhzfbpu
 qquhuwqhdswxxrxuzzfhkplwunfagppcoildagktgdarveusjuqfistulgbglwmfgzrnyxryetwzhlnfewczmnoozlqatugmd
 jwgzcfabbkoxyjxkatjmpprswkdkobdagwdwxsufeesrvncbszcepigpbzuzoootorzfskcwbqorvw
 dczatfarqdkelalxzxillkfdvpfpxabqlngdscrentzamztvvcvrtcm
 bqlizijdwtuyfrxolsysxlfebpolcmqsppmrfkyunydtmwbexsngxhwvroandfqjamzkpttslildlrkjoyrpxugiceahgiakev
 kfnfolpcfblpncetyhtrwxkbosccskxbuvcrosavnpxzoeoyyghbbqkflslfkqbbhgyyjj
 qrxpxnloeozxpnvasorcvubxksccsobkxwrthytecnplbfcplofx
 mlfcpidlqrvngnvttaifcbopnwezesomkxhaiafmvkbjaisyr
 btultpnxbcrmornqumatserhieqggrivouwfnbnghdfall
 pb
 kkb
 rfq
 xzj
 zlc
 zdw
 s
 k
 w
 d

 output:
 -1
 oozlzoo
 lxsysxl
 folpcfblpncetyhtrwxkbosccskxbuvcrosavnpxzoeoyyghbbqkflslfkqbbhgyyoeozxpnvasorcvubxksccsobkxwrthytecnplbfcplof
 rvngnvr
 bkkb
 -1
 zlz
 -1
 -1
 */
public class BuildAPalindrome {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			String strA = in.next();
			String strB = in.next();
			answer.append(buildPalindrome(strA, strB)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static Object buildPalindrome(String strA, String strB) {
		String reverseStrB = new StringBuilder(strB).reverse().toString();
		String longestCS = longestCommonSubString(strA, reverseStrB);
		StringBuilder res = new StringBuilder();
		if (longestCS.length() > 0) {
			String reverseLongestCS = new StringBuilder(longestCS).reverse()
					.toString();
			int indexStart = strA.indexOf(longestCS);
			int indexEnd = indexStart + longestCS.length();
			Character chA = findMinNextChar(strA, longestCS);
			Character chB = findMinPrevChar(strB, reverseLongestCS);
			StringBuilder middle = findMinChar(chA, chB);
			res.append(strA.substring(indexStart, indexEnd)).append(middle)
					.append(reverseLongestCS);
		} else {
			res.append("-1");
		}
		return res.toString();
	}

	private static StringBuilder findMinChar(Character a, Character b) {
		StringBuilder res = new StringBuilder();
		if (a != null && (b == null || a < b)) {
			res.append(a);
		} else if (b != null) {
			res.append(b);
		}
		return res;
	}

	private static Character findMinNextChar(String str, String subStr) {
		Character ch = null;
		int index = str.indexOf(subStr);
		while (index != -1) {
			int newCharIndex = index + subStr.length();
			if (newCharIndex < str.length()
					&& (ch == null || ch > str.charAt(newCharIndex))) {
				ch = str.charAt(newCharIndex);
			}
			index = str.indexOf(subStr, index + 1);
		}
		return ch;
	}

	private static Character findMinPrevChar(String str, String subStr) {
		Character ch = null;
		int index = str.indexOf(subStr);
		while (index != -1) {
			int newCharIndex = index - 1;
			if (newCharIndex >= 0
					&& (ch == null || ch > str.charAt(newCharIndex))) {
				ch = str.charAt(newCharIndex);
			}
			index = str.indexOf(subStr, index + 1);
		}
		return ch;
	}

	private static String longestCommonSubString(String a, String b) {
		if (a == null || b == null || a.length() == 0 || b.length() == 0) {
			return "";
		}
		if (a.equals(b)) {
			return a;
		}
		int[][] matrix = new int[a.length()][];

		int maxLength = 0;
		int maxI = 0;

		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = new int[b.length()];
			for (int j = 0; j < matrix[i].length; j++) {
				if (a.charAt(i) == b.charAt(j)) {
					if (i != 0 && j != 0) {
						matrix[i][j] = matrix[i - 1][j - 1] + 1;
					} else {
						matrix[i][j] = 1;
					}
					if (matrix[i][j] > maxLength
							|| (matrix[i][j] == maxLength && a.substring(
									maxI - maxLength + 1, maxI + 1).compareTo(
									a.substring(i - maxLength + 1, i + 1)) > 0)) {
						maxLength = matrix[i][j];
						maxI = i;
					}
				}
			}
		}
		return a.substring(maxI - maxLength + 1, maxI + 1);
	}

}
