package com.spring.tutorial.HakerRank.BitManipulation;

import java.util.Scanner;

/*
 * Hakerrank: A or B
 * https://www.hackerrank.com/challenges/aorb
 */
public class AorB {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < q; i++) {
			Input input = new Input();
			input.k = in.nextInt();
			input.A = strToByteArray(in.next());
			input.B = strToByteArray(in.next());
			input.C = strToByteArray(in.next());
			answer.append(findAandB(input)).append(
					System.getProperty("line.separator"));
		}
		System.out.println(answer);
		in.close();
	}

	private static String findAandB(Input in) {
		int length = in.C.length;
		byte[] dashA = new byte[length];
		byte[] dashB = new byte[length];
		int indexA = in.A.length - length;
		int indexB = in.A.length - length;

		int count = 0;
		for (int i = 0; i < in.A.length - length; i++) {
			count = Integer.bitCount(in.A[i]);
		}
		for (int i = 0; i < in.B.length - length; i++) {
			count = Integer.bitCount(in.B[i]);
		}
		for (int i = 0; i < length; i++) {
			if (i + indexA >= 0) {
				dashA[i] = (byte) (in.A[i + indexA] & in.C[i]);
				count += countBitDiff(dashA[i], in.A[i + indexA]);
			} else {
				dashA[i] = 0;
			}
			byte dashAxorC = (byte) (dashA[i] ^ in.C[i]);
			if (i + indexB >= 0) {
				dashB[i] = (byte) (in.B[i + indexB] & in.C[i] | dashAxorC);
				count += countBitDiff(dashB[i], in.B[i + indexB]);
			} else {
				dashB[i] = dashAxorC;
				count += countBitDiff(dashB[i], (byte) 0);
			}
		}

		if (count > in.k) {
			return "-1";
		}

		for (int i = 0; i < dashA.length; i++) {
			if (count >= in.k) {
				break;
			}
			int maxChange = countBitDiff(dashA[i], (byte) 0)
					+ countBitDiff(in.C[i], dashB[i]);
			if (count + maxChange <= in.k) {
				count += maxChange;
				dashA[i] = 0;
				dashB[i] = in.C[i];
			} else {
				for (int bit = 3; bit >= 0 && count < in.k; bit--) {
					if (checkBit(in.C[i], bit)) {
						if (checkBit(dashA[i], bit) && checkBit(dashB[i], bit)) {
							dashA[i] = removeBit(dashA[i], bit);
							count++;
						} else if (checkBit(dashA[i], bit)
								&& !checkBit(dashB[i], bit)
								&& (count + 2) <= in.k) {
							dashA[i] = removeBit(dashA[i], bit);
							;
							dashB[i] = setBit(dashB[i], bit);
							count += 2;
						}
					}
				}
			}
		}
		return byteArrayToStr(dashA)
				.append(System.getProperty("line.separator"))
				.append(byteArrayToStr(dashB)).toString();

	}

	private static int countBitDiff(byte a, byte b) {
		return Integer.bitCount(a ^ b);
	}

	private static byte[] strToByteArray(String str) {
		byte[] arr = new byte[str.length()];
		for (int i = 0; i < str.length(); i++) {
			arr[i] = (byte) Character.digit(str.charAt(i), 16);
		}
		return arr;
	}

	private static StringBuilder byteArrayToStr(byte[] arr) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (!(str.length() == 0 && arr[i] == 0)) {
				str.append(Integer.toHexString((int) arr[i]).toUpperCase());
			}
		}
		if (str.length() == 0) {
			str.append('0');
		}
		return str;
	}

	private static boolean checkBit(byte num, int bit) {
		return ((num >> bit) & 1) == 1;
	}

	private static byte setBit(byte num, int bit) {
		return (byte) (num | (1 << bit));
	}

	private static byte removeBit(byte num, int bit) {
		return (byte) (num ^ (1 << bit));
	}

	static class Input {
		byte[] A;
		byte[] B;
		byte[] C;
		int k;

	}

}
