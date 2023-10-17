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
        int prefix_len = 0;
        int i = 1;

        while (i < str.length()) {
            if (str.charAt(prefix_len) == str.charAt(i)) {
                prefix_len += 1;
                next.add(prefix_len);
                i += 1;
            } else {
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

    public static void kmpSearch(String text, String str) {

        //計算 next
        List<Integer> next = new ArrayList<>(List.of(0));
        int prefix_len = 0;
        int i = 1;

        while (i < str.length()) {
            if (str.charAt(prefix_len) == str.charAt(i)) {
                prefix_len += 1;
                next.add(prefix_len);
                i += 1;
            } else {
                if (prefix_len == 0) {
                    next.add(0);
                    i += 1;
                } else {
                    prefix_len = next.get(prefix_len - 1);
                }
            }
        }
        System.out.println(next);

        //----------
        //匹配
        int textIndex = 0;
        int strIndex = 0;
        int record = 0;
        List<String> currentSame = new ArrayList<>();
        List<String> sameList = new ArrayList<>();
        while (textIndex < text.length()) {
            if (str.charAt(strIndex) == text.charAt(textIndex)) {
//                System.out.println("record: " + record + " strIndex: " + strIndex + ", str " + str.charAt(strIndex) + ", text " + textIndex + ", " + text.charAt(textIndex));
                if (strIndex == 0) {
                    currentSame.clear();
                }

                currentSame.add(String.valueOf(text.charAt(textIndex)));
                //要把已找符合匹配的pattern 存起來
                if (record != strIndex && strIndex != 0) {
                    int j = 0;
                    while (j < strIndex) {
                        currentSame.remove(0);
                        j++;
                    }
                }
                strIndex++;
                textIndex++;
                record = strIndex;
                //因為我們透過 next() 可以知道關鍵字本身 str 各文字的數組
                //由底下傳入的DAA 生成next數組為 0 0 0
                //next 數組意思是 匹配到該字元時假如str不符合就表示可以跳過的字元個數

            } else {
                if (strIndex != 0) {
                    //這段要是 匹配字符時其中一個字符不符合 進來這裡 子字串的index-1 為要取得的value
                    //然後設定strIndex 為該 value
                    strIndex = next.get(strIndex - 1);
                } else {
                    textIndex++;
                }
            }
            //如果strIndex不歸0 會停在找到第一個符合子字串的位置
            if (textIndex < text.length() && strIndex == str.length()) {
                String same = String.join("", currentSame);
                sameList.add(same);
                strIndex = 0;
            }
        }
        System.out.println(currentSame);
        System.out.println(sameList);
    }

    public static void main(String[] args) {
        String txt = "ABAABABABCAABAABABABCAABAABABABCAABAABABABCAABAABABABCA";
        String pat = "ABABC";
//        next("abcabffabcabc");
//        next(txt);
        kmpSearch(txt, pat);
    }
}
