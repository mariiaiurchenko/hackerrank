package testInterviews;

import java.util.Arrays;

public class StairCase {

	public static void main(String[] args) {
		stairCase(6);
	}
	
	static void stairCase(int n) {
		char[] stair = new char[n];
		Arrays.fill(stair, ' ');
		for (int i = 0; i < n; i++) {			
			stair[n - 1 - i] = '#';
			printArray(stair);
		}
	}

	private static void printArray(char[] ar) {
		for (char ch : ar) {
			System.out.print(ch);
		}
		System.out.println();
	}
}
