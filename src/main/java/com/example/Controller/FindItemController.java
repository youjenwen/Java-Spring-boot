package com.example.Controller;

import com.example.Model.FindItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@RestController
@RequestMapping("/api")
public class FindItemController {
    //用 get 方式取得欲搜尋詞組
    @GetMapping("/findItem")
    public FindItem getFindItem(@RequestParam(value = "name") String itemName) {
        System.out.println("itemName " + itemName); //顯示 name 的名字
        FindItem findItem = new FindItem(); //帶入findItem module

        //讀取文件
        File doc = new File("C:\\Users\\jouven.liao\\Desktop\\Harper.wen\\java\\items.txt");
        String[] strArray = new String[0];
        BufferedReader reader = null;
        List<String> conformProducts = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(doc));
            String line;
            StringBuilder longStr = new StringBuilder();

            //每行文字去掉空格，然後串成長字串
            while ((line = reader.readLine()) != null) { //每行進行
                String str = line.replaceAll(" ", "");
                longStr.append(str);
            }

            System.out.println(longStr);
            conformProducts = kmpSearch(String.valueOf(longStr), itemName);
            //字串切成陣列
//            strArray = longStr.toString().split(",");
//            System.out.println(Arrays.toString(strArray));
            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        //陣列一個一個比對，找到存進 conformProducts 陣列

//        for (String item : strArray) {
//            if (item.contains(itemName)) {
//                conformProducts.add(item);
//            }
//        }
        findItem.setCount(conformProducts.size());
        findItem.setItemList(conformProducts);
        return findItem;
    }

    public static List<String> kmpSearch(String text, String pattern) {

        //計算 next
        List<Integer> next = new ArrayList<>(List.of(0));
        int prefix_len = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(prefix_len) == pattern.charAt(i)) {
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
        int patternIndex = 0;
        int record = 0;
        String nextStr = "";
        String same = "";
        List<String> currentSame = new ArrayList<>();
        List<String> sameList = new ArrayList<>();
        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
//                System.out.println("record: " + record + " strIndex: " + strIndex + ", pattern " + pattern.charAt(strIndex) + ", text " + textIndex + ", " + text.charAt(textIndex));
                if (patternIndex == 0) {
                    currentSame.clear();
                }
                String textStr = String.valueOf(text.charAt(textIndex));
                if (!nextStr.equals(textStr)) {
                    currentSame.add(textStr);
                }

                nextStr = String.valueOf(text.charAt(textIndex + 1)); //紀錄 當前字符index+1
                String prefStr = String.valueOf(text.charAt(textIndex - pattern.length())); //往前找 往後是逗號往前找pattern的長度
                if (!nextStr.equals(",")) {
                    currentSame.add(nextStr);
                } else {
//                    if (prefStr.equals(",")) {
//                        return currentSame;
//                    }
                    currentSame.add(0, prefStr);
                }

                if (record != patternIndex && patternIndex != 0) {
                    int j = 0;
                    while (j < patternIndex) {
                        currentSame.remove(0);
                        j++;
                    }
                }
                patternIndex++;
                textIndex++;
                record = patternIndex;

            } else {
                if (patternIndex != 0) {
                    patternIndex = next.get(patternIndex - 1);
                } else {
                    textIndex++;
                }
            }
            //如果strIndex不歸0 會停在找到第一個符合子字串的位置
            if (textIndex < text.length() && patternIndex == pattern.length()) {
                same = String.join("", currentSame);
                //TODO: 再比對一次 (感覺不行 跑太多次)
//                kmpSearch(text, same);
                sameList.add(same);
                patternIndex = 0;
            }
        }
//        System.out.println(currentSame);
        System.out.println("sameList" + sameList);
        return sameList;
    }

    public static void KMP(String text, String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            System.out.println("The pattern occurs with shift 0");
        }

        if (text == null || (pattern != null ? pattern.length() : 0) > text.length()) {
            System.out.println("Pattern not found");
        }

        char[] chars = {};
        if (pattern != null) {
            chars = pattern.toCharArray();
        }
        System.out.println("chars " + Arrays.toString(chars));

        //next[i] 存儲下一個最佳部分匹配的索引
        int[] next = new int[(pattern != null ? pattern.length() : 0) + 1];
        for (int i = 1; i < (pattern != null ? pattern.length() : 0); i++) {
            int j = next[i];
            while (j > 0 && chars[j] != chars[i]) {
                j = next[j];
            }
            if (j > 0 || chars[j] == chars[i]) {
                next[i + 1] = j + 1;
            }
        }
        for (int i = 0, j = 0; i < (text != null ? text.length() : 0); i++) {
            if (j < pattern.length() && text.charAt(i) == pattern.charAt(j)) {
                if (++j == pattern.length()) {
                    System.out.println("The pattern occurs with shift " + (i - j + 1));
                }
            } else if (j > 0) {
                j = next[j];
                i--;
            }
        }
    }
}