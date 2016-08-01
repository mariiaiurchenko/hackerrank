package com.spring.tutorial.HakerRank.Implementation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Hakerrank: Equal Stacks
 * https://www.hackerrank.com/challenges/equal-stacks
 */
public class EqualStacks {

	private static int getHeigth(Queue<Integer> q) {
		int height = 0;
		for (Integer el : q) {
			height += el;
		}
		return height;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		Queue[] q = new LinkedList[3];
		q[0] = new LinkedList<Integer>();
		q[1] = new LinkedList<Integer>();
		q[2] = new LinkedList<Integer>();

		for (int i = 0; i < a; i++) {
			q[0].add(in.nextInt());
		}
		for (int i = 0; i < b; i++) {
			q[1].add(in.nextInt());
		}
		for (int i = 0; i < c; i++) {
			q[2].add(in.nextInt());
		}
		int res = 0;
		int[] height = new int[3];
		for (int i = 0; i < 3; i++) {
			height[i] = getHeigth(q[i]);
		}
		while (!(height[0] == height[1] && height[1] == height[2])) {
			int maxIdx = -1;
			if (height[0] > height[1]) {
				if (height[0] > height[2]) {
					maxIdx = 0;
				} else {
					maxIdx = 2;
				}
			} else {
				if (height[1] > height[2]) {
					maxIdx = 1;
				} else {
					maxIdx = 2;
				}
			}
			height[maxIdx] = height[maxIdx] - (Integer) q[maxIdx].poll();
		}

		System.out.println(height[0]);
	}
}
