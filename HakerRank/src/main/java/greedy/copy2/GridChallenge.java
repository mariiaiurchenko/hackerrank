package greedy.copy2;

import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int a0 = 0; a0 < T; a0++) {
			int size = in.nextInt();
			String[] strs = new String[size];
			for (int i = 0; i < size; i++) {
				strs[i] = in.next();
			}
			answer.append(posibleToSort(strs)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static Object posibleToSort(String[] strs) {
		String yes = "YES";
		String no = "NO";
		int size = strs.length;
		char[][] chars = new char[size][size];
		for (int i = 0; i < size; i++) {
			chars[i] = strs[i].toCharArray();
			Arrays.sort(chars[i]);
		}
		for (int i = 1; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (chars[i - 1][j] > chars[i][j]) {
					return no;
				}
			}
		}
		return yes;
	}
}
