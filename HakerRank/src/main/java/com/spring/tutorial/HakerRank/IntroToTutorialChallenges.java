package com.spring.tutorial.HakerRank;

import java.util.Scanner;

public class IntroToTutorialChallenges {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int el = in.nextInt();
		int n = in.nextInt();
		int answer = -1;
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			if (arr[i] == el) {
				answer = i;
			}
		}
		System.out.println(answer);
		in.close();
	}

}
