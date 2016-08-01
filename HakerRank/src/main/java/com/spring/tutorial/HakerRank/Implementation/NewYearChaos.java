package com.spring.tutorial.HakerRank.Implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * Hakerrank: New Year Chaos
 * https://www.hackerrank.com/challenges/new-year-chaos
 */
public class NewYearChaos {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int n = in.nextInt();
			List<Integer> q = new LinkedList<Integer>();
			for (int q_i = 0; q_i < n; q_i++) {
				q.add(in.nextInt());
			}
			answer.append(countSwitch(q)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String countSwitch(List<Integer> q) {
		int count = 0;
		TreeSet<Integer> appeard = new TreeSet<Integer>();
		appeard.addAll(q);
		for (Integer el : q) {
			int bribes = appeard.headSet(el).size();
			if (bribes <= 2) {
				count += bribes;
			} else {
				count = -1;
				break;
			}
			appeard.remove(el);
		}
		return count == -1 ? "Too chaotic" : String.valueOf(count);
	}
}
