package WorldCodeSprint4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimumDistances {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int A[] = new int[n];
		for (int A_i = 0; A_i < n; A_i++) {
			A[A_i] = in.nextInt();
		}
		int res = -1;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++) {
			int el = A[i];
			if (map.get(el) != null) {
				int dist = i - map.get(el);
				if ((res > 0 && res > dist) || res < 0) {
					res = dist;
				}
			}
			map.put(el, i);
		}
		System.out.println(res);
	}
}
