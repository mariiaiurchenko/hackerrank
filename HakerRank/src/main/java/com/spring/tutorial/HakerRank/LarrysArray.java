package com.spring.tutorial.HakerRank;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class LarrysArray {

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
			answer.append(hasSolution(q)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String hasSolution(List<Integer> q) {
		return (countInverces(q) & 1) == 0 ? "YES" : "NO";
	}

	private static int countInverces(List<Integer> q) {
		int count = 0;
		TreeSet<Integer> appeard = new TreeSet<Integer>();
		appeard.addAll(q);
		for (Integer el : q) {
			int bribes = appeard.headSet(el).size();
			count += bribes;
			appeard.remove(el);
		}
		return count;
	}

}
