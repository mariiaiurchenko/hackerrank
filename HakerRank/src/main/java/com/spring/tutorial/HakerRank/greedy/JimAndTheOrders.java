package com.spring.tutorial.HakerRank.greedy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * Hackerrank: Jim and the Orders
 * https://www.hackerrank.com/challenges/jim-and-the-orders
 */
public class JimAndTheOrders {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] orders = new int[n][2];
		for (int i = 0; i < n; i++) {
			orders[i][0] = in.nextInt();
			orders[i][1] = in.nextInt();
		}
		printOrdersInOrder(orders);
		in.close();
	}

	private static void printOrdersInOrder(int[][] orders) {

		int[] sorted = sortByEnd(orders);
		for (int el : sorted) {
			System.out.print(el + " ");
		}
	}

	private static int[] sortByEnd(int[][] orders) {
		Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
		for (int i = 0; i < orders.length; i++) {
			int key = orders[i][0] + orders[i][1];
			if (map.containsKey(key)) {
				map.get(key).add(i);
			} else {
				List<Integer> list = new LinkedList<Integer>();
				list.add(i);
				map.put(key, list);
			}
		}
		int[] sorted = new int[orders.length];
		int index = 0;
		for (int key : map.keySet()) {
			List<Integer> list = map.get(key);
			for (int val : list) {
				sorted[index] = val + 1;
				index++;
			}
		}
		return sorted;
	}
}
