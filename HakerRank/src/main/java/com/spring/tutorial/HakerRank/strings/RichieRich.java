package com.spring.tutorial.HakerRank.strings;

import java.util.Scanner;

/*
 * Hakerrank: Richie Rich
 * https://www.hackerrank.com/challenges/richie-rich
 */
public class RichieRich {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		System.out.println(calcUpdatedNum(in.next(), k));
		in.close();
	}

	private static String calcUpdatedNum(String str, int k) {
		StringBuilder updated = new StringBuilder(str);
		int size = str.length();
		int count = 0;
		char mark = '-';
		for (int i = 0; i < size / 2; i++) {
			int first = Character.getNumericValue(updated.charAt(i));
			int last = Character.getNumericValue(updated.charAt(size - 1 - i));
			if (first != last) {
				int maxVal = Math.max(first, last);
				int minIndex = (maxVal == first) ? size - 1 - i : i;
				updated.setCharAt(minIndex, mark);
				count++;
			}
		}
		char maxDig = '9';
		if (count <= k) {
			for (int i = 0; i < size / 2; i++) {
				if (count < k) {
					if (updated.charAt(i) == mark
							|| updated.charAt(size - 1 - i) == mark) {
						if (updated.charAt(i) != maxDig
								&& updated.charAt(size - 1 - i) != maxDig) {
							count++;
						}
						setDigToBothSite(updated, i, maxDig);
					} else if (count + 1 < k && updated.charAt(i) != maxDig) {
						setDigToBothSite(updated, i, maxDig);
						count += 2;
					}
				} else if (updated.charAt(i) == mark
						|| updated.charAt(size - 1 - i) == mark) {
					char ch = updated.charAt(i) == mark ? updated.charAt(size
							- 1 - i) : updated.charAt(i);
					setDigToBothSite(updated, i, ch);
				}
			}
			if (isOdd(size) && count < k) {
				updated.setCharAt(size / 2, maxDig);
			}

		} else {
			updated = new StringBuilder("-1");
		}
		return updated.toString();
	}

	private static void setDigToBothSite(StringBuilder str, int pos, char ch) {
		str.setCharAt(pos, ch);
		str.setCharAt(str.length() - 1 - pos, ch);
	}

	private static boolean isOdd(int size) {
		return (size & 1) == 1;
	}
}
