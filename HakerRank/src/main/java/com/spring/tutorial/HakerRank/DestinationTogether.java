package com.spring.tutorial.HakerRank;

import java.math.BigInteger;
import java.util.Scanner;

public class DestinationTogether {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int c = in.nextInt();
		System.out.println(countSequences(n, m, c));
		in.close();
	}

	private static String countSequences(int n, int m, int c) {
		int first = n - c;
		int second = m - c;
		return factorial(first + second + c - 1);
	}

	public static String factorial(int n) {
		BigInteger fact = BigInteger.ONE;
		for (int i = 1; i <= n; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		return fact.toString();
	}
}
