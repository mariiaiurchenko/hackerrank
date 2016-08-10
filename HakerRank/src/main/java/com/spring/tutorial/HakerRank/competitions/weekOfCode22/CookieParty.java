package com.spring.tutorial.HakerRank.competitions.weekOfCode22;

import java.util.Scanner;

public class CookieParty {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int mod = n % m;
		int res = (mod == 0) ? 0 : m - mod;
		System.out.println(res);
		in.close();
	}
}
