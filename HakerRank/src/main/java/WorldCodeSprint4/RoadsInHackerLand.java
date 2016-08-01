package WorldCodeSprint4;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RoadsInHackerLand {

	private static int getIngex(int a, int b, int cities) {
		return Math.min(a, b) * cities + Math.max(a, b);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cities = in.nextInt();
		int roads = in.nextInt();
		Map<Integer, List<Node>> map = new HashMap<Integer, List<Node>>();
		for (int i = 0; i < roads; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int dist = in.nextInt();
		}

		BigInteger dist = BigInteger.ZERO;
		for (int i = 1; i <= cities; i++) {
			for (int j = i; j <= cities; j++) {

			}
		}
	}

	private static class CityDist implements Comparable<CityDist> {
		int city;
		BigInteger dist;

		CityDist(int city, BigInteger dist) {
			this.city = city;
			this.dist = dist;
		}
		
		public int compareTo(CityDist o) {
			return this.dist.compareTo(o.dist);
		}

	}

	private static class Node {
		long name;
		List<Long> conections;
	}
}
