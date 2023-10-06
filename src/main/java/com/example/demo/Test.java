package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void search(String txt, String pat) {
        int M = pat.length(); //3
        int N = txt.length(); //18

        //int i = 0; i <= 18-3; i++
        for (int i = 0; i < (N - M) + 1; i++) {
            int j;
            // M = 3
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
//                    System.out.println("i: " + i + ", j:" + j);
                    break;
                }
                if (j == M - 1) {
                    System.out.println("Pattern found at index " + i + " ,j: " + j);
                }
            }

        }
    }

    public static void next(String str) {

        //取得 str 長度 13
        List<Integer> next = new ArrayList<>(List.of(0));
        System.out.println(next);
        int prefix_len = 0;
        int i = 1;

        while (i < str.length()) {
//            System.out.println(i);
//            System.out.println(str.charAt(prefix_len) +" , "+ str.charAt(i));
//            i++;

            if (str.charAt(prefix_len) == str.charAt(i)) {
                prefix_len += 1;
                next.add(prefix_len);
                i += 1;
            }else {
                if (prefix_len == 0) {
                    next.add(0);
                    i += 1;
                } else {
                    prefix_len = next.get(prefix_len - 1);
                }
            }
        }
        System.out.println(next);
    }

    public static void main(String[] args) {
        String txt = "AABAACAADAABDAABAA";
        String pat = "DAA";
//        search(txt, pat);
        next("abcabffabcabc");
    }
}
