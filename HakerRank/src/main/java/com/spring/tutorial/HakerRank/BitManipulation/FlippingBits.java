package com.spring.tutorial.HakerRank.BitManipulation;

import java.util.Scanner;

public class FlippingBits {

	static final long TWO_POW_32 = (long) Math.pow(2, 32) - 1;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			long n = in.nextLong();
			answer.append(FlipBits(n)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static long FlipBits(long n) {
		long f = TWO_POW_32 ^ n;
		return f;
	}
}
