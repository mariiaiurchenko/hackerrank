package com.spring.tutorial.HakerRank;

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BigInteger a1 = BigInteger.valueOf(in.nextInt());
		BigInteger a2 = BigInteger.valueOf(in.nextInt());
		int n = in.nextInt();
		for (int i = 3; i <= n; i++) {
			BigInteger newVal = a2.pow(2).add(a1);
			a1 = a2;
			a2 = newVal;
		}

		System.out.println(a2.toString());
		in.close();
	}

}
