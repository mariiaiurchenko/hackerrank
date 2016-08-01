package com.spring.tutorial.HakerRank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class SnakesAndLaddersTheQuickestWayUp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int n = in.nextInt();
			Map<Integer, Integer> jumps = new HashMap<Integer, Integer>();
			for (int i = 0; i < n; i++) {
				jumps.put(in.nextInt(), in.nextInt());
			}
			n = in.nextInt();
			for (int i = 0; i < n; i++) {
				jumps.put(in.nextInt(), in.nextInt());
			}
			answer.append(countSteps(jumps)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String countSteps(Map<Integer, Integer> jumps) {
		Set<Integer> visited = new HashSet<Integer>();
		Set<Integer> mark = new HashSet<Integer>();
		PriorityQueue<Way> path = new PriorityQueue<Way>();
		path.add(new Way(1, 0));
		int res = -1;
		while (!path.isEmpty() && res == -1) {
			Way next = path.poll();
			if (!visited.contains(next.pos)) {
				visited.add(next.pos);
				for (int i = 1; i <= 6; i++) {
					int newPos = next.pos + i;
					if (jumps.containsKey(newPos)) {
						newPos = jumps.get(newPos);
					}
					if (newPos == 100) {
						res = next.steps + 1;
					} else if (!visited.contains(newPos)
							&& !mark.contains(newPos) && newPos < 100) {
						{
							mark.add(newPos);
							path.add(new Way(newPos, next.steps + 1));
						}
					}
				}
			}
		}
		return String.valueOf(res);
	}

	static class Way implements Comparable<Way> {
		int pos;
		int steps;

		Way(int pos, int steps) {
			this.pos = pos;
			this.steps = steps;
		}

		public int compareTo(Way o) {
			int res = this.steps - o.steps;
			if (res == 0) {
				res = this.pos - o.pos;
			}
			return res;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + pos;
			result = prime * result + steps;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Way other = (Way) obj;
			if (pos != other.pos)
				return false;
			if (steps != other.steps)
				return false;
			return true;
		}
	}
}
