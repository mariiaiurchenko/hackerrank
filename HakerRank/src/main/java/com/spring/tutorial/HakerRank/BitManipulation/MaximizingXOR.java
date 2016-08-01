package com.spring.tutorial.HakerRank.BitManipulation;

import java.math.BigInteger;
import java.util.Scanner;

public class MaximizingXOR {

    static int maxXor(int l, int r) {
		int diff = r ^ l;
		int res = (int) Math.pow(2, BigInteger.valueOf(diff).bitLength()) - 1;
		return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;
        int _l;
        _l = Integer.parseInt(in.nextLine());
        
        int _r;
        _r = Integer.parseInt(in.nextLine());
        
        res = maxXor(_l, _r);
        System.out.println(res);
        in.close();
    }
}
