package com.spring.tutorial.HakerRank.greedy;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Hackerrank: Mark and Toys
 * https://www.hackerrank.com/challenges/mark-and-toys
 */
public class MarkAndToys {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int money = in.nextInt();
		int[] prices = new int[n];
		for (int i = 0; i < n; i++) {
			prices[i] = in.nextInt();
		}
		System.out.println(countToys(prices, money));
		in.close();
	}

	private static int countToys(int[] prices, int money) {
		Arrays.sort(prices);
		int count = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] <= money) {
				money -= prices[i];
				count++;
			} else {
				break;
			}
		}
		return count;
	}
}
